package com.boot.member.controller;

import com.boot.member.service.MemberService;

@Controller
public class MemberController {
    //하나만 생성해서 공통적으로 사용 -> 스프링 컨테이너에 등록한다.
    private final MemberService memberService;

    //스프링 컨테이너에 있는 멤버 서비스를 스프링이 가져와서 자동으로 주입해주는 어노테이션
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
}

/*
    기본적으로 스프링은 스프링 컨테이너에 스프링 빈을 등록할 때, 싱글톤으로 등록한다.
    따라서 같은 스프링 빈이면 모두 같은 인스턴스다.

    스프링 빈을 등록하는 두 가지 방법
    1. 컴포넌트 스캔과 자동 의존관계 설정
        @Component 애노테이션이 있으면 스프링 빈으로 자동 등록된다.
        @Controller 컨트롤러가 스프링 빈으로 자동 등록된 이유도 컴포넌트 스캔 때문이다.
        @Component 를 포함하는 다음 애노테이션도 스프링 빈으로 자동 등록된다. 각 애노테이션에 @Component가 선언되어 있다.
            @Controller
            @Service
            @Repository
        기본적인 컴포넌트 스캔의 범위는 초반 MVC 구성할 때, 지정한 패키지 아키팩트 하위의 모든 파일을 스캔한다.
        사실 @ComponentScan이라는 애노테이션이 있는 클래스를 스캔하는데, 위에서 언급한 하위의 모든 파일은 이 애노테이션이 존재한다.
    2. 자바 코드로 직접 스프링 빈 등록하기
* */