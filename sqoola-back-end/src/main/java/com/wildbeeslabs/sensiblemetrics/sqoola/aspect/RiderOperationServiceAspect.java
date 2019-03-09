package com.wildbeeslabs.sensiblemetrics.sqoola.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component(RiderOperationServiceAspect.COMPONENT_ID)
public class RiderOperationServiceAspect {

    /**
     * Default component ID
     */
    public static final String COMPONENT_ID = "riderOperationServiceAspect";

    @Before(value = "execution(* com.wildbeeslabs.sensiblemetrics.sqoola.service.impl.rider.period.RiderOperationPeriodServiceImpl.*(..))")
    public void riderOperationPeriodBeforeAdvice(final JoinPoint joinPoint) {
        log.info(String.format("RiderOperationServiceAspect: processing model={%s} by method={%s} with args={%s}", joinPoint.getTarget(), joinPoint.getSignature(), joinPoint.getArgs()));
    }

    @After(value = "execution(* com.wildbeeslabs.sensiblemetrics.sqoola.service.impl.rider.period.RiderOperationPeriodServiceImpl.*(..))")
    public void riderOperationPeriodAfterAdvice(final JoinPoint joinPoint) {
        log.info(String.format("RiderOperationServiceAspect: model={%s} has been processed", joinPoint.getTarget()));
    }

    @Before(value = "execution(* com.wildbeeslabs.sensiblemetrics.sqoola.service.impl.rider.RiderOperationServiceImpl.*(..))")
    public void riderOperationBeforeAdvice(final JoinPoint joinPoint) {
        log.info(String.format("RiderOperationServiceAspect: processing model={%s} by method={%s} with args={%s}", joinPoint.getTarget(), joinPoint.getSignature(), joinPoint.getArgs()));
    }

    @After(value = "execution(* com.wildbeeslabs.sensiblemetrics.sqoola.service.impl.rider.RiderOperationServiceImpl.*(..))")
    public void riderOperationAfterAdvice(final JoinPoint joinPoint) {
        log.info(String.format("RiderOperationServiceAspect: model={%s} has been processed", joinPoint.getTarget()));
    }
}
