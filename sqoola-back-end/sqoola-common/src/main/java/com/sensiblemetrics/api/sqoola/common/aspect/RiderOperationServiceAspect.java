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
@Component(RiderOperationServiceAspect.COMPONENT_ID)
public class RiderOperationServiceAspect {

    /**
     * Default component ID
     */
    public static final String COMPONENT_ID = "riderOperationServiceAspect";

    @Before(value = "execution(* com.sensiblemetrics.api.sqoola.common.service.dao.impl.rider.period.RiderOperationPeriodServiceImpl.*(..))")
    public void riderOperationPeriodBeforeAdvice(final JoinPoint joinPoint) {
        log.info(String.format("RiderOperationServiceAspect: processing model={%s} by method={%s} with args={%s}", joinPoint.getTarget(), joinPoint.getSignature(), joinPoint.getArgs()));
    }

    @After(value = "execution(* com.sensiblemetrics.api.sqoola.common.service.dao.impl.rider.period.RiderOperationPeriodServiceImpl.*(..))")
    public void riderOperationPeriodAfterAdvice(final JoinPoint joinPoint) {
        log.info(String.format("RiderOperationServiceAspect: model={%s} has been processed", joinPoint.getTarget()));
    }

    @Before(value = "execution(* com.sensiblemetrics.api.sqoola.common.service.dao.impl.rider.RiderOperationServiceImpl.*(..))")
    public void riderOperationBeforeAdvice(final JoinPoint joinPoint) {
        log.info(String.format("RiderOperationServiceAspect: processing model={%s} by method={%s} with args={%s}", joinPoint.getTarget(), joinPoint.getSignature(), joinPoint.getArgs()));
    }

    @After(value = "execution(* com.sensiblemetrics.api.sqoola.common.service.dao.impl.rider.RiderOperationServiceImpl.*(..))")
    public void riderOperationAfterAdvice(final JoinPoint joinPoint) {
        log.info(String.format("RiderOperationServiceAspect: model={%s} has been processed", joinPoint.getTarget()));
    }
}
