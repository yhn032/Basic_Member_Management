package com.boot.member;

import com.boot.member.repository.MemberRepository;
import com.boot.member.repository.MemoryMemberRepository;
import com.boot.member.service.MemberService;

@Configuration
public class SpringConfig {
    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository(){
        return new MemoryMemberRepository();
    }
}
