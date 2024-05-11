package com.example.cachingdemo.aspect;


import com.example.cachingdemo.Entity.Book;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Slf4j
@Component
public class GeneralAspectClass {

    //Expression laguage where to be executed
    @Pointcut("execution(* com.example.cachingdemo.controller.*.*(..))")
    public void loggingPointCut(){
    }

    @Before("loggingPointCut()")
    public void before(JoinPoint joinPoint){
        log.info("Before method invoked : {}",joinPoint.getSignature());
    }

    @After("loggingPointCut()")
    public void after(JoinPoint joinPoint){
        log.info("After Method Invoked : {}",joinPoint.getSignature());
    }

    /*@AfterReturning(value = "execution(* com.example.cachingdemo.controller.*.*(..))",
        returning = "book")
    public void afterReturn(JoinPoint joinPoint, Book book){
        log.info("After method returned object : {}",book);
    }*/

    /*@AfterThrowing(value = "execution(* com.example.cachingdemo.controller.*.*(..))",
            throwing = "e")
    public void afterThrow(JoinPoint joinPoint, Exception e){
        log.info("After method thrown exception : {}",e.getCause());
    }*/

    @Around("@annotation(com.example.cachingdemo.annotations.ResponseTimeCalculator)")
    public Object logExeceutionTimeCalculator(ProceedingJoinPoint joinPoint) throws Throwable {
        final StopWatch stopWatch = new StopWatch();

        stopWatch.start();

        Object proceed = joinPoint.proceed();

        stopWatch.stop();

        log.info("{} : executed im {} :ms",joinPoint.getSignature(),stopWatch.getTotalTimeMillis());

        return proceed;
    }
}
