package com.car.caresalesgarage.config;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
@Component
public class LogAspect {
    private static final Logger logger = LoggerFactory.getLogger(LogAspect.class);

    @Around("execution(* your.package.name.*.*(..))")
    public Object logMethodExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();

        // Log method entry with inputs
        logger.info("Entering: " + joinPoint.getSignature().toShortString());
        logger.info("Method : " + Arrays.toString(joinPoint.getArgs()));

        Object result = joinPoint.proceed();

        long endTime = System.currentTimeMillis();

        // Log method exit with output and processing time
        logger.info("Exiting: " + joinPoint.getSignature().toShortString());
        logger.info("Method output: " + result);
        logger.info("Method execution time: " + (endTime - startTime) + "ms");

        return result;
    }
}
