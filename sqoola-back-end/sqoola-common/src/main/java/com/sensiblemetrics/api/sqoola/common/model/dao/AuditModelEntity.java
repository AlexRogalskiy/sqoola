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

import com.sensiblemetrics.api.sqoola.common.model.constraint.annotation.ChronologicalDates;
import com.sensiblemetrics.api.sqoola.common.model.dao.interfaces.PersistableAuditModel;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.domain.Auditable;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Objects;
import java.util.Optional;

import static com.sensiblemetrics.api.sqoola.common.utility.DateUtils.DEFAULT_DATE_FORMAT_PATTERN_EXT;

/**
 * Audit model entity {@link Auditable}
 *
 * @param <ID> type of model identifier {@link Serializable}
 */
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@DynamicInsert
@DynamicUpdate
@MappedSuperclass
@ChronologicalDates
@EntityListeners(AuditingEntityListener.class)
public abstract class AuditModelEntity<ID extends Serializable> implements Auditable<String, ID, LocalDateTime>, PersistableAuditModel {

    /**
     * Default explicit serialVersionUID for interoperability
     */
    private static final long serialVersionUID = 431774856692738135L;

    @CreationTimestamp
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME, pattern = DEFAULT_DATE_FORMAT_PATTERN_EXT)
    @NotBlank(message = "{audit.created.notBlank}")
    @Column(name = CREATED_FIELD_NAME, nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Setter
    @CreatedBy
    @ManyToOne
    @NotBlank(message = "{audit.createdBy.notBlank}")
    @Column(name = CREATED_BY_FIELD_NAME, nullable = false, updatable = false)
    private String createdBy;

    @UpdateTimestamp
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME, pattern = DEFAULT_DATE_FORMAT_PATTERN_EXT)
    @Column(name = CHANGED_FIELD_NAME, insertable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;

    @Setter
    @LastModifiedBy
    @ManyToOne
    @Column(name = CHANGED_BY_FIELD_NAME, insertable = false)
    private String lastModifiedBy;

    @Override
    public Optional<LocalDateTime> getCreatedDate() {
        return Objects.isNull(this.createdDate)
            ? Optional.empty()
            : Optional.of(LocalDateTime.ofInstant(this.createdDate.toInstant(), ZoneId.systemDefault()));
    }

    @Override
    public void setCreatedDate(@NonNull final LocalDateTime createdDate) {
        this.createdDate = Date.from(createdDate.atZone(ZoneId.systemDefault()).toInstant());
    }

    @Override
    public Optional<String> getCreatedBy() {
        return Optional.ofNullable(this.createdBy);
    }

    @Override
    public Optional<LocalDateTime> getLastModifiedDate() {
        return Objects.isNull(this.lastModifiedDate)
            ? Optional.empty()
            : Optional.of(LocalDateTime.ofInstant(this.lastModifiedDate.toInstant(), ZoneId.systemDefault()));
    }

    @Override
    public void setLastModifiedDate(@NonNull final LocalDateTime lastModifiedDate) {
        this.lastModifiedDate = Date.from(lastModifiedDate.atZone(ZoneId.systemDefault()).toInstant());
    }

    @Override
    public Optional<String> getLastModifiedBy() {
        return Optional.ofNullable(this.lastModifiedBy);
    }

    @PrePersist
    protected void onCreate() {
        setCreatedBy(this.getClass().getName());
    }

    @PreUpdate
    protected void onUpdate() {
        setLastModifiedBy(this.getClass().getName());
    }
}
