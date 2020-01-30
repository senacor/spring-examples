package com.senacor.tecco.ilms.katas.aspects.e01_advice.e01_beforeafter;

import org.aspectj.lang.annotation.*;

/**
 * Created by fsubasi on 16.01.2016.
 * Spring Aspect Demo: In this example, we define an aspect to intercept
 * method calls. @Aspects are where advices and pointcuts are defined.
 *
 * We define a @Pointcut which declares where an advice should be applied (the method to be intercepted)
 *
 * Then we see different advices. Advices declare both what will be done, and when it
 * will be done(i.e. before or after the advised method executes).
 */
@Aspect // Detected by @EnableAspectJProxy to create proxies.
public class Audience {

    /**
     * A pointcut definition(@Pointcut) matches one or more join points at which advice should be woven.
     * Spring AOP is built around dynamic proxies. Consequently, Spring's AOP support is limited
     * to method interception. Pointcuts are defined using AspectJ's pointcut expression language.
     * 'execution' means the advice will be triggered on method execution
     * The '**' means it matches any return type and then the method name
     * '(..)' means it matches any arguments
     */
    @Pointcut("execution(** demo.intern.aspects.Performance.perform(..))")
    public void performance() {} // This function is just used to refer to pointcut(named pointcut) it has no other function


    // @Before advice is called before the adviced method
    @Before("performance()")
    public void silenceCellPhones() {
        System.out.println("Silencing cell phones");
    }
    @Before("performance()")
    public void takeSeats() {
        System.out.println("Taking seats");
    }
    // @AfterReturning advice is called after the advised method successfully returns
    @AfterReturning("performance()")
    public void applause() {
        System.out.println("CLAP CLAP CLAP!!!");
    }
    // @AfterThrowing advice is called after the advised method throws an exception
    @AfterThrowing("performance()")
    public void demandRefund() {
        System.out.println("Demanding a refund");
    }
}