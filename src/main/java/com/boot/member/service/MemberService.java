package com.boot.member.service;

import com.boot.member.domain.Member;
import com.boot.member.repository.MemberRepository;
import com.boot.member.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

//2022-11-08
//서비스를 통해서 멤버를 가입하고, 가입한 멤버를 리포지토리에 저장하고 꺼내올 수 있음
//컨트롤러를 만들 생각이다.
//서비스가 컨트롤러를 의존하도록 의존관계를 설정해야 한다.

//ctrl + shift + t : 새로운 테스트케이스 작성하기
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /*
    *   회원가입
    * */
    public Long join(Member member){
        validateDuplicateMember(member); //중복회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    /*
        전체회원 조회
    */
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }


    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}
