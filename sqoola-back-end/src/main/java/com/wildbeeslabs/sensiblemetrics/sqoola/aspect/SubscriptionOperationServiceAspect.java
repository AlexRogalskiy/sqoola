package com.wildbeeslabs.sensiblemetrics.sqoola.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component(SubscriptionOperationServiceAspect.COMPONENT_ID)
public class SubscriptionOperationServiceAspect {

    /**
     * Default component ID
     */
    public static final String COMPONENT_ID = "subscriptionOperationServiceAspect";

    @Before(value = "execution(* com.wildbeeslabs.sensiblemetrics.sqoola.service.dao.impl.subscription.period.SubscriptionOperationPeriodServiceImpl.*(..))")
    public void subscriptionOperationPeriodBeforeAdvice(final JoinPoint joinPoint) {
        log.info(String.format("SubscriptionOperationServiceAspect: processing model={%s} by method={%s} with args={%s}", joinPoint.getTarget(), joinPoint.getSignature(), joinPoint.getArgs()));
    }

    @After(value = "execution(* com.wildbeeslabs.sensiblemetrics.sqoola.service.dao.impl.subscription.period.SubscriptionOperationPeriodServiceImpl.*(..))")
    public void subscriptionOperationPeriodAfterAdvice(final JoinPoint joinPoint) {
        log.info(String.format("SubscriptionOperationServiceAspect: model={%s} has been processed", joinPoint.getTarget()));
    }

    @Before(value = "execution(* com.wildbeeslabs.sensiblemetrics.sqoola.service.dao.impl.subscription.SubscriptionOperationServiceImpl.*(..))")
    public void subscriptionOperationBeforeAdvice(final JoinPoint joinPoint) {
        log.info(String.format("SubscriptionOperationServiceAspect: processing model={%s} by method={%s} with args={%s}", joinPoint.getTarget(), joinPoint.getSignature(), joinPoint.getArgs()));
    }

    @After(value = "execution(* com.wildbeeslabs.sensiblemetrics.sqoola.service.dao.impl.subscription.SubscriptionOperationServiceImpl.*(..))")
    public void subscriptionOperationAfterAdvice(final JoinPoint joinPoint) {
        log.info(String.format("SubscriptionOperationServiceAspect: model={%s} has been processed", joinPoint.getTarget()));
    }
}
