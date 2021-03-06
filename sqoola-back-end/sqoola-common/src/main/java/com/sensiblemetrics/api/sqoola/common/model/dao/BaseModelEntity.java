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
package com.sensiblemetrics.api.sqoola.common.model.dao;

import com.sensiblemetrics.api.sqoola.common.model.dao.interfaces.PersistableBaseModel;
import com.sensiblemetrics.api.sqoola.common.model.dao.listeners.ModelEventListener;
import com.sensiblemetrics.api.sqoola.common.model.iface.Versionable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * Base model entity {@link AuditModelEntity}
 *
 * @param <ID> type of model identifier {@link Serializable}
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@MappedSuperclass
@EntityListeners(ModelEventListener.class)
public abstract class BaseModelEntity<ID extends Serializable> extends AuditModelEntity<ID> implements PersistableBaseModel, Versionable<Long> {

    /**
     * Default explicit serialVersionUID for interoperability
     */
    private static final long serialVersionUID = 6444143028591284804L;

    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@GeneratedValue(generator = "base_generator")
//    @SequenceGenerator(
//            name = "base_generator",
//            sequenceName = "base_generator"
//    )
    @Column(name = ID_FIELD_NAME, unique = true, nullable = false)
    private ID id;

    @Version
    @ColumnDefault("0")
    @Column(name = VERSION_BY_FIELD_NAME, insertable = false, updatable = false)
    //@Generated(GenerationTime.ALWAYS)
    private Long version;

    @Override
    public boolean isNew() {
        return Objects.isNull(this.getId());
    }
}
