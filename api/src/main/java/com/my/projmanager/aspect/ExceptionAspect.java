package com.my.projmanager.aspect;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ExceptionAspect {
    private static final Logger log = Logger.getLogger(ExceptionAspect.class);

    @AfterThrowing(pointcut = "execution(* com.my.projmanager.controller.*.*(..))",
                    throwing = "e")
    public void logAroundMethods(JoinPoint joinPoint, Exception e) {
        log.info(joinPoint.getSignature().getDeclaringTypeName() + "/"
                + joinPoint.getSignature().getName() + "/"
                + e.getMessage() + "/"
                + e.getCause());
    }
}
