package com.senacor.tecco.ilms.katas.aspects.e01_advice.e02_around;

/**
 * Created by fsubasi on 05.01.2016.
 */

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
@Aspect
public class Audience {

    @Pointcut("execution(** demo.intern.aspects.Performance.perform(..))")
    public void performance(){}

    // @Around advice wraps the advised method.
    @Around("performance()")
    public void watchPerformance(ProceedingJoinPoint jp){
        try{
            System.out.println("Silencing cell phones");
            System.out.println("Taking seats");
            jp.proceed(); // The call of the advised method
            System.out.println("CLAP CLAP CLAP!!!");
        } catch(Throwable e){
            System.out.println("Demanding a refund");
        }
    }
}