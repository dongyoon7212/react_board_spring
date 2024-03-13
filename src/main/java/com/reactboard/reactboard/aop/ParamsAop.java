package com.reactboard.reactboard.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.CodeSignature;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class ParamsAop {

    @Pointcut("@annotation(com.reactboard.reactboard.aop.annotation.ParamsAspect)")
    private void pointCut() {}

    @Around("pointCut()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Object[] args = proceedingJoinPoint.getArgs(); // 매개변수 정보, 변수의 값은 가져오지만 이름은 모름

        CodeSignature codeSignature = (CodeSignature) proceedingJoinPoint.getSignature();
        String[] paramsName = codeSignature.getParameterNames();
        String className = codeSignature.getDeclaringTypeName(); // 클래스명
        String methodName = codeSignature.getName(); // 메소드명

        for(int i = 0; i < args.length; i++) {
            log.info("{}={} ({}.{})", paramsName[i], args[i], className, methodName);
        }

        Object result = proceedingJoinPoint.proceed(); // 비즈니스 로직

        return result;
    }
}
