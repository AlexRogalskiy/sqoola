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

import com.sensiblemetrics.api.sqoola.common.model.dao.interfaces.*;
import com.sensiblemetrics.sqoola.common.model.dao.interfaces.*;
import com.wildbeeslabs.sensiblemetrics.sqoola.common.model.dao.interfaces.*;
import com.wildbeeslabs.sensiblemetrics.sqoola.model.dao.interfaces.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.validation.constraints.PositiveOrZero;
import java.util.*;

/**
 * ProductEntity model {@link BaseModelEntity}
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Entity(name = PersistableProduct.MODEL_ID)
@BatchSize(size = 10)
@Table(name = PersistableProduct.TABlE_NAME, catalog = "public",
    indexes = {@Index(name = "product_catalog_number_idx", columnList = PersistableProduct.ID_FIELD_NAME + ", " + PersistableProduct.CATALOG_NUMBER_FIELD_NAME)}
)
@AttributeOverrides({
    @AttributeOverride(name = PersistableBaseModel.ID_FIELD_NAME, column = @Column(name = PersistableProduct.ID_FIELD_NAME, unique = true, nullable = false))
})
@Inheritance(strategy = InheritanceType.JOINED)
public class ProductEntity extends BaseModelEntity<Long> implements PersistableProduct {

    /**
     * Default explicit serialVersionUID for interoperability
     */
    private static final long serialVersionUID = 6034172782528641104L;

    @Column(name = NAME_FIELD_NAME, columnDefinition = "text")
    private String name;

    @Column(name = SHORT_DESCRIPTION_FIELD_NAME, columnDefinition = "text")
    private String shortDescription;

    @Lob
    @Type(type = "org.hibernate.type.TextType")
    @Column(name = LONG_DESCRIPTION_FIELD_NAME, columnDefinition = "text")
    private String longDescription;

    @Column(name = PRICE_DESCRIPTION_FIELD_NAME, columnDefinition = "text")
    private String priceDescription;

    @Column(name = CATALOG_NUMBER_FIELD_NAME)
    private String catalogNumber;

    @Column(name = PAGE_TITLE_FIELD_NAME)
    private String pageTitle;

    @Column(name = AVAILABLE_FIELD_NAME)
    private boolean available;

    @Column(name = PRICE_FIELD_NAME)
    @PositiveOrZero(message = "order.price.PositiveOrZero")
    private double price;

    @Column(name = RECOMMENDED_PRICE_FIELD_NAME)
    @PositiveOrZero(message = "{order.recommendedPrice.PositiveOrZero}")
    private double recommendedPrice;

    @Column(name = RATING_FIELD_NAME, columnDefinition = "int")
    private Integer rating;

    @Column(name = AGE_RESTRICTION_FIELD_NAME, columnDefinition = "int")
    private Integer ageRestriction;

    @Column(name = LOCK_TYPE_FIELD_NAME, columnDefinition = "int")
    private Integer lockType;

//    @Indexed(name = CATEGORY_FIELD_NAME)
//    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE, optional = false)
//    @Fetch(FetchMode.SELECT)
//    @JoinColumn(name = CATEGORY_FIELD_NAME, nullable = false)
//    private CategoryEntity category;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
        name = PRODUCT_CATEGORY_TABLE_NAME,
        joinColumns = {
            @JoinColumn(name = PersistableProduct.PRODUCT_ID_FIELD_NAME, referencedColumnName = PersistableProduct.ID_FIELD_NAME)
        },
        inverseJoinColumns = {
            @JoinColumn(name = PersistableCategory.CATEGORY_ID_FIELD_NAME, referencedColumnName = PersistableCategory.ID_FIELD_NAME)
        }
    )
    @Fetch(FetchMode.SELECT)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private final Set<CategoryEntity> categories = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
        name = PRODUCT_MAIN_CATEGORY_TABLE_NAME,
        joinColumns = {
            @JoinColumn(name = PersistableProduct.PRODUCT_ID_FIELD_NAME, referencedColumnName = PersistableProduct.ID_FIELD_NAME)
        },
        inverseJoinColumns = {
            @JoinColumn(name = PersistableCategory.CATEGORY_ID_FIELD_NAME, referencedColumnName = PersistableCategory.ID_FIELD_NAME)
        }
    )
    @Fetch(FetchMode.SELECT)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private final Set<CategoryEntity> mainCategories = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
        name = PRODUCT_ATTRIBUTE_TABLE_NAME,
        joinColumns = {
            @JoinColumn(name = PersistableProduct.PRODUCT_ID_FIELD_NAME, referencedColumnName = PersistableProduct.ID_FIELD_NAME)
        },
        inverseJoinColumns = {
            @JoinColumn(name = PersistableAttribute.ATTRIBUTE_ID_FIELD_NAME, referencedColumnName = PersistableAttribute.ID_FIELD_NAME)
        }
    )
    @Fetch(FetchMode.SELECT)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private final List<AttributeEntity> attributes = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
        name = PRODUCT_ORDER_TABLE_NAME,
        joinColumns = {
            @JoinColumn(name = PersistableProduct.PRODUCT_ID_FIELD_NAME, referencedColumnName = PersistableProduct.ID_FIELD_NAME)
        },
        inverseJoinColumns = {
            @JoinColumn(name = PersistableOrder.ORDER_ID_FIELD_NAME, referencedColumnName = PersistableOrder.ID_FIELD_NAME)
        }
    )
    @Fetch(FetchMode.SELECT)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private final Set<OrderEntity> orders = new HashSet<>();

    public void setCategories(final Collection<? extends CategoryEntity> categories) {
        this.getCategories().clear();
        Optional.ofNullable(categories)
            .orElseGet(Collections::emptyList)
            .forEach(this::addCategory);
    }

    public void addCategory(final CategoryEntity category) {
        if (Objects.nonNull(category)) {
            this.getCategories().add(category);
        }
    }

    public void setMainCategories(final Collection<? extends CategoryEntity> mainCategories) {
        this.getMainCategories().clear();
        Optional.ofNullable(mainCategories)
            .orElseGet(Collections::emptyList)
            .forEach(this::addMainCategory);
    }

    public void addMainCategory(final CategoryEntity mainCategory) {
        if (Objects.nonNull(mainCategory)) {
            this.getMainCategories().add(mainCategory);
        }
    }

    public void setAttributes(final Collection<? extends AttributeEntity> attributes) {
        this.getAttributes().clear();
        Optional.ofNullable(attributes)
            .orElseGet(Collections::emptyList)
            .forEach(this::addAttribute);
    }

    public void addAttribute(final AttributeEntity attribute) {
        if (Objects.nonNull(attribute)) {
            this.getAttributes().add(attribute);
        }
    }

    public void setOrders(final Collection<? extends OrderEntity> orders) {
        this.getOrders().clear();
        Optional.ofNullable(orders)
            .orElseGet(Collections::emptyList)
            .forEach(this::addOrder);
    }

    public void addOrder(final OrderEntity order) {
        if (Objects.nonNull(order)) {
            this.getOrders().add(order);
        }
    }
}
