package com.boot.member;

import com.boot.member.aop.TimeTraceAop;
import com.boot.member.repository.*;
import com.boot.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    //private DataSource dataSource;

    private final MemberRepository memberRepository;

    @Autowired
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Autowired
    /*public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }*/

    //컴포넌트 스캔을 쓰기도 하지만 수동으로 등록하는 편이 더 좋음
    /*@Bean
    public TimeTraceAop timeTraceAop(){
        return new TimeTraceAop();
    }*/



    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository);
    }

//    @Bean
//    public MemberRepository memberRepository(){
//        //return new MemoryMemberRepository(dataSource);
//        //return new JdbcMemberRepository(dataSource);
//        //return new JdbcTemplateMemberRepository(dataSource);
//        //return new JpaMemberRepository(em);
//    }


}
