package com.boot.member.controller;

import com.boot.member.domain.Member;
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

    //URL에 직접 path를 입력하는 GET방식으 통신의 경우 매핑되는 메서드
    @GetMapping("/members/new")
    public String createForm(){
        return "members/createMemberForm";
    }

    //데이터를 form에 넣어서 전달할때 POST를 사용한다. 조회 혹은 URL에 직접 입력할 때는 GET을 사용한다.
    //전송 방법에 따라 다르게 매핑할 수 있다.
    @PostMapping("/members/new")
    public String create(MemberForm form){
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);
        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model){
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);

        return "members/memberList";
    }
}
//컨트롤러는 컨포넌트 스캔을 사용할 수 밖에 없다. 그러므로 autowired또한 사용해야 한다.

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
        @Configuration 애노테이션이 있는 클래스 파일을 만들어서 @Bean 애노테이션을 사용하여 수동으로 의존성을 주입한다.
        XML로 주입하는 방법도 있지만 요즘에는 사용하지 않아서 자바 코드로 생성하는 방법을 읽혀보자.
        의존성을 주입하는 방법으로는 필드 주입, 세터 주입, 생성자 주입이 있다.
        의존관계가 실행중에 동적으로 변하는 경우는 거의 없으므로 ""생성자 주입""을 권장한다.
        생성자 주입 : 생성자를 통해서 의존관계를 주입하는 방법
        필드   주입 : 필드에 @Autowired를 사용하는 방법 > 별로 안 좋음 중간에 동적으로 주입내용 변경 불가
        세터   주입 : 세터함수를 통해 주입하는 방법 > 게터 세터는 public이 원칙이라 누구나 접근해서 중간에 주입 내용이 변경할 수 있는 단점이 있음


   주의 : @Autowired를 통한 DI는 스프링이 관리하는 컨테이너에서만 동작한다. 따라서 스프링 빈으로 등록하지 않은 클래스
   에서 사용하지 않고 내가 직접 생성한 POJO객체에서는 동작하지 않는다.

   참고 : 실무에서는 주로 정형화된 컨트롤러, 서비스, 리포지토리 같은 코드는 컴포넌트 스캔을 사용한다.
         하지만 정형화되지 않거나, 상황에 따라 구현 클래스를 변경해야 하면 설정을 통해 직접 스프링 빈으로 등록한다.(그래야만 다른 코드에 영향을 주지 않고, 변경해야 하는 구현 클래스만 바꿔치기 할 수 있다. ★★★★★)
* */