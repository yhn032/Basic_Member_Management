package com.boot.member.service;

import com.boot.member.domain.Member;
import com.boot.member.repository.MemberRepository;
import com.boot.member.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;


//통합 테스트 이전까지는 단위 테스트
//단위테스트로만 가능한 테스트가 좋은 테스트코드일 확률이 높다.
@SpringBootTest
@Transactional
class MemberServiceIntegrationTest {

    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;


    //테스트는 여러번 돌려도 같은 결과를 받아야 하는데
    //db는 메모리처럼 매번 db를 삭제할 순 없다.(할 순 있지만 귀찮음)
    //스프링이 제공해주는 강력한 장치를 사용하자.
    //@Transactional 애노테이션이 있다면 insert든 update든 delete든 날려 놓고, 테스트가 끝나면 롤백을 시켜버린다.!!
    //테스트 케이스에 위 애노태이션이 있으면, 테스트 시작 전에 트랜잭션을 시작하고, 테스트 완료 후에 항상 롤백한다.
    //따라서 다음 테스트에 영향을 주지 않는 것이다.
    //@SpringBootTest 스프링 컨테이너와 테스트를 함께 실행한다. 실제로 스프링이 실행된다.
    @Test
    void 회원가입() {
        //given(주어진 상황)
        Member member = new Member();
        member.setName("spring");

        //when (실행했을 때)
        Long saveId = memberService.join(member);

        //then (결과는 이렇게)
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    //테스트는 예외 플로우가 더 중요하다.
    @Test
    public void 중복회원예외() {
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
    }
}