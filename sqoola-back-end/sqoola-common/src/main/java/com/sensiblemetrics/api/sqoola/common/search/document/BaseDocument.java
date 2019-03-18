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
package com.sensiblemetrics.api.sqoola.common.search.document;

import com.sensiblemetrics.api.sqoola.common.search.document.interfaces.SearchableBaseDocument;
import lombok.*;
import org.springframework.data.domain.Persistable;
import org.springframework.data.solr.core.mapping.Indexed;
import org.springframework.data.solr.core.mapping.Score;

import java.io.Serializable;
import java.util.Objects;

/**
 * Full-text search base document {@link AuditDocument}
 *
 * @param <ID> type of document identifier {@link Serializable}
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public abstract class BaseDocument<ID extends Serializable> extends AuditDocument implements Persistable<ID>, SearchableBaseDocument {

    /**
     * Default explicit serialVersionUID for interoperability
     */
    private static final long serialVersionUID = 6444143028591284804L;

    @Indexed(name = DOCTYPE_FIELD_NAME)
    private String doctype;

    @Score
    @Setter(AccessLevel.PROTECTED)
    private float score;

    public boolean isNew() {
        return Objects.isNull(this.getId());
    }
}
