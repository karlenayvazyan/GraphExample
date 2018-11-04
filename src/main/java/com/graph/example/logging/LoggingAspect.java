package com.graph.example.logging;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.stream.Collectors;

@Aspect
@Component
public class LoggingAspect {

    private static final Logger logger = LogManager.getLogger(LoggingAspect.class);

    @Around("execution(* com.graph.example.service..*(..))")
    public Object methodNameAndArgumentsLogging(ProceedingJoinPoint joinPoint) throws Throwable {
        Object proceed = joinPoint.proceed();
        Object[] args = joinPoint.getArgs();
        String arguments = Arrays.stream(args).map(Object::toString).collect(Collectors.joining(", "));
        logger.debug(joinPoint.getSignature() + " with params " + arguments);
        return proceed;
    }

    @AfterThrowing(pointcut = "execution(* com.graph.example.service..*(..))", throwing = "ex")
    public void logAfterThrowingAllMethods(RuntimeException ex) {
        logger.error(ex);
    }
}
