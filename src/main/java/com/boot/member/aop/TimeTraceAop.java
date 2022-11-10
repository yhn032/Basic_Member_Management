package com.boot.member.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TimeTraceAop {


    //시간 측정을 적용할 범위를 지정하는 것이다. 보통 패키지 레벨로 많이 지정한다.
    //메서드를 호출할때마다 인터셉트해서 시간 측정 메서드를 실행시킨다.
    @Around("execution(* com.boot..*(..))")
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable{
        long start = System.currentTimeMillis();
        System.out.println("START : " + joinPoint.toString());

        try {
            return joinPoint.proceed();
        }finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("END : " + joinPoint.toString() +  timeMs  + "ms");
        }
    }
}
