package com.sensiblemetrics.api.sqoola.common.specs.impl;


import com.sensiblemetrics.api.sqoola.common.specs.iface.Specification;

/**
 * Abstract base implementation of composite {@link Specification} with default
 * implementations for {@code and}, {@code or} and {@code not}.
 */
public abstract class AbstractSpecification<T> implements Specification<T> {

    /**
     * {@inheritDoc}
     */
    public abstract boolean isSatisfiedBy(T t);

    /**
     * {@inheritDoc}
     */
    public Specification<T> and(final Specification<T> specification) {
        return new AndSpecification<>(this, specification);
    }

    /**
     * {@inheritDoc}
     */
    public Specification<T> or(final Specification<T> specification) {
        return new OrSpecification<>(this, specification);
    }

    /**
     * {@inheritDoc}
     */
    public Specification<T> not(final Specification<T> specification) {
        return new NotSpecification<>(specification);
    }
}
