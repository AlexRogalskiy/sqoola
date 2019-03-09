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
package com.wildbeeslabs.sensiblemetrics.sqoola.common.model.dao;

import com.wildbeeslabs.sensiblemetrics.sqoola.common.model.dao.interfaces.PersistableBaseModel;
import com.wildbeeslabs.sensiblemetrics.sqoola.common.model.dao.interfaces.PersistableOrder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.*;

/**
 * Order model {@link BaseModel}
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Entity(name = PersistableOrder.MODEL_ID)
@BatchSize(size = 10)
@Table(name = PersistableOrder.TABlE_NAME, catalog = "public")
@AttributeOverrides({
    @AttributeOverride(name = PersistableBaseModel.ID_FIELD_NAME, column = @Column(name = PersistableOrder.ID_FIELD_NAME, unique = true, nullable = false))
})
@Inheritance(strategy = InheritanceType.JOINED)
public class Order extends BaseModel<Long> implements PersistableOrder {

    /**
     * Default explicit serialVersionUID for interoperability
     */
    private static final long serialVersionUID = -5055264765286046442L;

    @Column(name = TITLE_FIELD_NAME)
    private String title;

    @Lob
    @Type(type = "org.hibernate.type.TextType")
    @Column(name = DESCRIPTION_FIELD_NAME, columnDefinition = "text")
    private String description;

    @Size(min = 3, max = 100, message = "{order.clientName.Size}")
    @Column(name = CLIENT_NAME_FIELD_NAME)
    private String clientName;

    @Size(min = 3, max = 50, message = "{order.clientMobile.Size}")
    @Column(name = CLIENT_MOBILE_FIELD_NAME)
    private String clientMobile;

    @ManyToMany(mappedBy = ORDERS_REF_FIELD_NAME, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private final Set<Product> products = new HashSet<>();

    public void setProducts(final Collection<? extends Product> products) {
        this.getProducts().clear();
        Optional.ofNullable(products)
            .orElseGet(Collections::emptyList)
            .forEach(this::addProduct);
    }

    public void addProduct(final Product product) {
        if (Objects.nonNull(product)) {
            this.getProducts().add(product);
        }
    }
}
