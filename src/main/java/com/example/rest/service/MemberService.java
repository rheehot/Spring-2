package com.example.rest.service;

import com.example.rest.entity.Member;
import com.example.rest.repository.MemberRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class MemberService {
    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Member register(@NotNull Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 유저입니다.");
                });
        memberRepository.save(member);
        return member;
    }

    public List<Member> memberList() {
        return memberRepository.findAll();
    }
}
