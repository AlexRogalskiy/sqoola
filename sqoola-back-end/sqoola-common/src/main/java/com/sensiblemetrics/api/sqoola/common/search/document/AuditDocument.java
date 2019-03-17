/*
 * The MIT License
 *
 * Copyright 2019 WildBees Labs, Inc.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
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
package com.sensiblemetrics.api.sqoola.common.search.document;

import com.wildbeeslabs.sensiblemetrics.supersolr.model.annotation.ChronologicalDates;
import com.wildbeeslabs.sensiblemetrics.supersolr.search.document.interfaces.SearchableAuditDocument;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.solr.core.mapping.Indexed;

import java.io.Serializable;
import java.util.Date;

/**
 * Custom full-text search audit document {@link Serializable}
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@ChronologicalDates
public abstract class AuditDocument implements SearchableAuditDocument, Serializable {

    /**
     * Default explicit serialVersionUID for interoperability
     */
    private static final long serialVersionUID = -4511025234589044122L;

    @Indexed(CREATED_FIELD_NAME)
    private Date created;

    @Indexed(CREATED_BY_FIELD_NAME)
    private String createdBy;

    @Indexed(CHANGED_FIELD_NAME)
    private Date changed;

    @Indexed(CHANGED_BY_FIELD_NAME)
    private String changedBy;
}
