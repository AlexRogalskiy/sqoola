package com.sensiblemetrics.api.sqoola.common.validation.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;

/**
 * Param {@link GeneralRequest} entity
 *
 * @param <T> type of search item
 */
@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class GeneralParamRequest<T extends Serializable> extends GeneralRequest<SearchParam<T>> {
}
