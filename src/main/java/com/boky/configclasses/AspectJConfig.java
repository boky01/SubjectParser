package com.boky.configclasses;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class AspectJConfig {

    public static int num = 0;

    @Around("execution (* com.boky.SubjectParser..*.*(..))")
    public Object aroundAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
        Logger logger = LoggerFactory.getLogger(joinPoint.getTarget().getClass());
        logger.trace(crateTabs(num++) + "Start " + "." + joinPoint.getSignature().getName());

        Object result = joinPoint.proceed();

        logger.trace(crateTabs(--num) + "End" + joinPoint.getSignature().getName());
        return result;
    }

    private String crateTabs(int num) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < num; i++) {
            sb.append("\t");
        }
        return sb.toString();
    }
}
