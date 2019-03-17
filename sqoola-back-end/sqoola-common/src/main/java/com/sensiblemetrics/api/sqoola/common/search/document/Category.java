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

import com.wildbeeslabs.sensiblemetrics.supersolr.search.document.interfaces.SearchableCategory;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.solr.core.mapping.Indexed;
import org.springframework.data.solr.core.mapping.SolrDocument;

import java.util.*;

/**
 * Custom full-text search category document {@link BaseDocument}
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@SolrDocument(solrCoreName = SearchableCategory.CORE_ID, collection = SearchableCategory.COLLECTION_ID)
public class Category extends BaseDocument<String> implements SearchableCategory {

    /**
     * Default explicit serialVersionUID for interoperability
     */
    private static final long serialVersionUID = -107452074862198456L;

    @Indexed(name = ID_FIELD_NAME)
    private String id;

    @Indexed(name = INDEX_FIELD_NAME)
    private Integer index;

    @Indexed(name = TITLE_FIELD_NAME, type = "text_general")
    private String title;

    @Indexed(name = DESCRIPTION_FIELD_NAME, type = "text_general")
    private String description;

    @Indexed(name = PRODUCTS_FIELD_NAME)
    private Set<Product> products = new HashSet<>();

    @Indexed(name = MAIN_PRODUCTS_FIELD_NAME)
    private Set<Product> mainProducts = new HashSet<>();

    public void setProducts(final Collection<? extends Product> products) {
        this.getProducts().clear();
        Optional.ofNullable(products)
                .orElseGet(Collections::emptyList)
                .forEach(product -> this.addProduct(product));
    }

    public void addProduct(final Product product) {
        if (Objects.nonNull(product)) {
            this.getProducts().add(product);
        }
    }

    public void setMainProducts(final Collection<? extends Product> mainProducts) {
        this.getMainProducts().clear();
        Optional.ofNullable(mainProducts)
                .orElseGet(Collections::emptyList)
                .forEach(mainProduct -> this.addMainProduct(mainProduct));
    }

    public void addMainProduct(final Product mainProduct) {
        if (Objects.nonNull(mainProduct)) {
            this.getMainProducts().add(mainProduct);
        }
    }
}
