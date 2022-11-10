package com.boot.member.repository;

import com.boot.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


//JpaRepository를 상속받으면 스프링 데이터 jpa가 자동으로 구현체를 만들어준다.
//또한 스프링 빈을 자동으로 등록해준다.
public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {
    //JPQL select m from Member m where m.name = ?
    //작명규칭 findBy~
    @Override
    Optional<Member> findByName(String name);
}
