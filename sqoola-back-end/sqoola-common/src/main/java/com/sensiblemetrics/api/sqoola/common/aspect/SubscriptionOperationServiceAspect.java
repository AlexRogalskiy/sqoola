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

    @Before(value = "execution(* com.sensiblemetrics.api.sqoola.common.service.dao.impl.subscription.period.SubscriptionOperationPeriodServiceImpl.*(..))")
    public void subscriptionOperationPeriodBeforeAdvice(final JoinPoint joinPoint) {
        log.info(String.format("SubscriptionOperationServiceAspect: processing model={%s} by method={%s} with args={%s}", joinPoint.getTarget(), joinPoint.getSignature(), joinPoint.getArgs()));
    }

    @After(value = "execution(* com.sensiblemetrics.api.sqoola.common.service.dao.impl.subscription.period.SubscriptionOperationPeriodServiceImpl.*(..))")
    public void subscriptionOperationPeriodAfterAdvice(final JoinPoint joinPoint) {
        log.info(String.format("SubscriptionOperationServiceAspect: model={%s} has been processed", joinPoint.getTarget()));
    }

    @Before(value = "execution(* com.sensiblemetrics.api.sqoola.common.service.dao.impl.subscription.SubscriptionOperationServiceImpl.*(..))")
    public void subscriptionOperationBeforeAdvice(final JoinPoint joinPoint) {
        log.info(String.format("SubscriptionOperationServiceAspect: processing model={%s} by method={%s} with args={%s}", joinPoint.getTarget(), joinPoint.getSignature(), joinPoint.getArgs()));
    }

    @After(value = "execution(* com.sensiblemetrics.api.sqoola.common.service.dao.impl.subscription.SubscriptionOperationServiceImpl.*(..))")
    public void subscriptionOperationAfterAdvice(final JoinPoint joinPoint) {
        log.info(String.format("SubscriptionOperationServiceAspect: model={%s} has been processed", joinPoint.getTarget()));
    }
}
