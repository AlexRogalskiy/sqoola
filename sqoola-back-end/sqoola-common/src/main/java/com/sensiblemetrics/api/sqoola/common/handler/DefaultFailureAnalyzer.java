package com.sensiblemetrics.api.sqoola.common.handler;

import org.springframework.beans.factory.BeanNotOfRequiredTypeException;
import org.springframework.boot.diagnostics.AbstractFailureAnalyzer;
import org.springframework.boot.diagnostics.FailureAnalysis;

/**
 * Default failure analyzer {@link AbstractFailureAnalyzer} implementation
 */
public class DefaultFailureAnalyzer extends AbstractFailureAnalyzer<BeanNotOfRequiredTypeException> {

    /**
     * Returns an analysis of the given {@code failure}, or {@code null} if no analysis
     * was possible.
     *
     * @param ex    - initial input {@link Throwable} instance passed to the analyzer
     * @param cause - initial input {@link BeanNotOfRequiredTypeException} cause
     * @return {@link FailureAnalysis} instance
     */
    @Override
    protected FailureAnalysis analyze(final Throwable ex, final BeanNotOfRequiredTypeException cause) {
        return new FailureAnalysis(String.format("ERROR: message=%s", getDescription(cause)), null, cause);
    }

    /**
     * Returns failure description {@link String}
     *
     * @param ex - initial input {@link BeanNotOfRequiredTypeException} cause
     * @return failure description {@link String}
     */
    private String getDescription(final BeanNotOfRequiredTypeException ex) {
        return String.format("The bean %s could not be injected due to %s", ex.getBeanName(), ex.getRequiredType().getName());
    }
}
