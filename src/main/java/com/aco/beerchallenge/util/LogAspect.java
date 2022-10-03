package com.aco.beerchallenge.util;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LogAspect {
    @Before("@annotation(com.aco.beerchallenge.util.LogEvent)")
    public void logEvent (JoinPoint joinPoint) {
        System.out.println("BeerChallenge method '" + joinPoint.getSignature().getName() + "' called.");
    }
}