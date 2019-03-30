package com.sensiblemetrics.api.sqoola.common.service.iface;

import java.io.Serializable;
import java.util.List;

public interface BaseQueryService<E extends Serializable> {

    List<? extends E> executeQuery(final String queryString);

    List<? extends E> executeQuery(final String queryString, final Class<? extends E> queryMapping);
}
