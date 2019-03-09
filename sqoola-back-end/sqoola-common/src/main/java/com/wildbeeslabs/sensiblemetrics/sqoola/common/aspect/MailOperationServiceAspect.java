package com.wildbeeslabs.sensiblemetrics.sqoola.common.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component(MailOperationServiceAspect.COMPONENT_ID)
public class MailOperationServiceAspect {

    /**
     * Default component ID
     */
    public static final String COMPONENT_ID = "mailOperationServiceAspect";

    @Before(value = "execution(* com.wildbeeslabs.sensiblemetrics.sqoola.service.dao.impl.mail.period.MailOperationPeriodServiceImpl.*(..))")
    public void mailOperationPeriodBeforeAdvice(final JoinPoint joinPoint) {
        log.info(String.format("MailOperationServiceAspect: processing model={%s} by method={%s} with args={%s}", joinPoint.getTarget(), joinPoint.getSignature(), joinPoint.getArgs()));
    }

    @After(value = "execution(* com.wildbeeslabs.sensiblemetrics.sqoola.service.dao.impl.mail.period.MailOperationPeriodServiceImpl.*(..))")
    public void mailOperationPeriodAfterAdvice(final JoinPoint joinPoint) {
        log.info(String.format("MailOperationServiceAspect: model={%s} has been processed", joinPoint.getTarget()));
    }

    @Before(value = "execution(* com.wildbeeslabs.sensiblemetrics.sqoola.service.dao.impl.mail.MailOperationServiceImpl.*(..))")
    public void mailOperationBeforeAdvice(final JoinPoint joinPoint) {
        log.info(String.format("MailOperationServiceAspect: processing model={%s} by method={%s} with args={%s}", joinPoint.getTarget(), joinPoint.getSignature(), joinPoint.getArgs()));
    }

    @After(value = "execution(* com.wildbeeslabs.sensiblemetrics.sqoola.service.dao.impl.mail.MailOperationServiceImpl.*(..))")
    public void mailOperationAfterAdvice(final JoinPoint joinPoint) {
        log.info(String.format("MailOperationServiceAspect: model={%s} has been processed", joinPoint.getTarget()));
    }
}
