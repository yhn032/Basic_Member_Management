package com.boot.member.service;

import com.boot.member.domain.Member;
import com.boot.member.repository.MemberMemoryRepositoryTest;
import com.boot.member.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;
    //서비스에서 사용하는 저장소와 테스트에서 사용하는 저장소가 new로 생성되었기 때문에 서로 다른 객체 즉 서로 다른 저장소이므로 아래와 같이 수정해라.
    //서비스에 생성자를 만들어 놓고 각 테스트를 실행하기 전에 생성자 주입을 진행하여 같은 스토어를 사용하도록 한다.
    @BeforeEach
    public void beforeEach(){
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    public void afterEach(){
        memberRepository.clearStore();
    }

    //테스트코드의 함수명은 과감하게 한글로 해도 됨. 보기가 편하다.
    //빌드될때 포함되지 않기 때문이다.
    @Test
    void 회원가입() {
        //given(주어진 상황)
        Member member = new Member();
        member.setName("hello");

        //when (실행했을 때)
        Long saveId = memberService.join(member);

        //then (결과는 이렇게)
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    //테스트는 예외 플로우가 더 중요하다.
    @Test
    public void 중복회원예외(){
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

/*        try {
            memberService.join(member2);
            fail("예외가 발생해야 합니다.");

        }catch (IllegalStateException e){
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        }*/

        //then
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}