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
package com.sensiblemetrics.api.sqoola.common.model.dao.location;

import com.sensiblemetrics.api.sqoola.common.model.dao.BaseModelEntity;
import com.sensiblemetrics.api.sqoola.common.model.dao.interfaces.PersistableBaseModel;
import com.sensiblemetrics.api.sqoola.common.model.dao.interfaces.location.PersistableCountry;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.BatchSize;

import javax.persistence.*;

/**
 * Country model {@link BaseModelEntity}
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Entity(name = PersistableCountry.MODEL_ID)
@BatchSize(size = 10)
@Table(name = PersistableCountry.TABlE_NAME, catalog = "public")
@AttributeOverrides({
    @AttributeOverride(name = PersistableBaseModel.ID_FIELD_NAME, column = @Column(name = PersistableCountry.ID_FIELD_NAME, unique = true, nullable = false))
})
@Inheritance(strategy = InheritanceType.JOINED)
public class Country extends BaseModelEntity<Long> implements PersistableCountry {

    private String countryCode;

    private String countryName;
}
