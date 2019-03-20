/*
 * The MIT License
 *
 * Copyright 2019 WildBees Labs, Inc.
 *
 * PermissionEntity is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.sensiblemetrics.api.sqoola.common.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component(MailOperationServiceAspect.COMPONENT_ID)
public class MailOperationServiceAspect {

    /**
     * Default component ID
     */
    public static final String COMPONENT_ID = "mailOperationServiceAspect";

    @Before(value = "execution(* com.sensiblemetrics.api.sqoola.common.service.dao.impl.mail.period.MailOperationPeriodServiceImpl.*(..))")
    public void logMailPeriodServiceBeforeAdvice(final JoinPoint joinPoint) {
        log.info(String.format("MailOperationServiceAspect: model={%s}, method={%s}, args={%s}", joinPoint.getTarget(), joinPoint.getSignature().getName(), joinPoint.getArgs()));
    }

    @After(value = "execution(* com.sensiblemetrics.api.sqoola.common.service.dao.impl.mail.period.MailOperationPeriodServiceImpl.*(..))")
    public void logMailPeriodServiceAfterAdvice(final JoinPoint joinPoint) {
        log.info(String.format("MailOperationServiceAspect: model={%s}, method={%s}, args={%s}", joinPoint.getTarget(), joinPoint.getSignature().getName(), joinPoint.getArgs()));
    }

    @AfterReturning(value = "execution(* com.sensiblemetrics.api.sqoola.common.service.dao.impl.mail.period.MailOperationPeriodServiceImpl.*(..))", returning = "resultSet")
    public void logMailPeriodServiceAfterReturningAdvice(final JoinPoint joinPoint, final Object resultSet) {
        log.info(String.format("MailOperationServiceAspect: model={%s}, method={%s}, args={%s}, resultSet={%s}", joinPoint.getTarget(), joinPoint.getSignature().getName(), joinPoint.getArgs(), resultSet));
    }

    @AfterThrowing(pointcut = "execution(* com.sensiblemetrics.api.sqoola.common.service.dao.impl.mail.period.MailOperationPeriodServiceImpl.*(..))", throwing = "error")
    public void logMailPeriodServiceAfterThrowingAdvice(final JoinPoint joinPoint, final Throwable error) {
        log.info(String.format("MailOperationServiceAspect: model={%s}, method={%s}, args={%s}, error={%s}", joinPoint.getTarget(), joinPoint.getSignature().getName(), joinPoint.getArgs(), error));
    }

    @Around("execution(* com.sensiblemetrics.api.sqoola.common.service.dao.impl.mail.period.MailOperationPeriodServiceImpl.*(..))")
    public Object logMailPeriodServiceAroundAdvice(final ProceedingJoinPoint joinPoint) throws Throwable {
        final Object result = joinPoint.proceed();
        log.info(String.format("MailOperationServiceAspect: model={%s}, method={%s}, args={%s}, result={%s}", joinPoint.getTarget(), joinPoint.getSignature().getName(), joinPoint.getArgs(), result));
        return result;
    }

    @Before(value = "execution(* com.sensiblemetrics.api.sqoola.common.service.dao.impl.mail.MailOperationServiceImpl.*(..))")
    public void mailOperationBeforeAdvice(final JoinPoint joinPoint) {
        log.info(String.format("MailOperationServiceAspect: processing model={%s} by method={%s} with args={%s}", joinPoint.getTarget(), joinPoint.getSignature(), joinPoint.getArgs()));
    }

    @After(value = "execution(* com.sensiblemetrics.api.sqoola.common.service.dao.impl.mail.MailOperationServiceImpl.*(..))")
    public void mailOperationAfterAdvice(final JoinPoint joinPoint) {
        log.info(String.format("MailOperationServiceAspect: model={%s} has been processed", joinPoint.getTarget()));
    }
}
