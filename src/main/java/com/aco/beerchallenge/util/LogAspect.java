package com.aco.beerchallenge.util;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LogAspect {
    @Before("@annotation(com.aco.beerchallenge.util.LogEvent)")
    public void logEvent () {
        //LogEvent event = LogAspect.class.getAnnotation(LogEvent.class);
        System.out.println("BeerChallenge method called.");
    }
}