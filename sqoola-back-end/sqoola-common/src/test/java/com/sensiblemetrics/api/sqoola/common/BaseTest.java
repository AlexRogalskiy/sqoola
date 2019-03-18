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
package com.sensiblemetrics.api.sqoola.common;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sensiblemetrics.api.sqoola.common.model.dao.*;
import com.sensiblemetrics.sqoola.common.model.dao.*;
import com.wildbeeslabs.sensiblemetrics.sqoola.common.model.dao.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Base test implementation
 */
@Slf4j
public abstract class BaseTest {

    /**
     * Default xml type with utf-8 charset encoding
     */
    public static final String APPLICATION_XML_UTF8_VALUE = "application/xml;charset=UTF-8";

    /**
     * Mock security context implementation {@link SecurityContext}
     */
    @Data
    @EqualsAndHashCode
    @ToString
    public static class MockSecurityContext implements SecurityContext {

        /**
         * Default explicit serialVersionUID for interoperability
         */
        private static final long serialVersionUID = 3405852283390845327L;

        /**
         * Default authentication instance {@link Authentication}
         */
        private Authentication authentication;

        public MockSecurityContext(final Authentication authentication) {
            this.authentication = authentication;
        }
    }

    protected CategoryEntity createCategory(
        final Long id,
        final Integer index,
        final String title,
        final String description,
        final ProductEntity... products) {
        final CategoryEntity category = new CategoryEntity();
        category.setId(id);
        category.setIndex(index);
        category.setTitle(title);
        category.setDescription(description);
        category.setProducts(Arrays.asList(Optional.ofNullable(products).orElse(new ProductEntity[0])));
        return category;
    }

    protected ProductEntity createProduct(
        final Long id,
        final String name,
        final String shortDescription,
        final String longDescription,
        final String priceDescription,
        final String catalogNumber,
        final String pageTitle,
        final Integer rating,
        double price,
        double recommendedPrice,
        boolean available,
        final AttributeEntity... attributes) {
        final ProductEntity product = new ProductEntity();
        product.setId(id);
        product.setName(name);
        product.setShortDescription(shortDescription);
        product.setLongDescription(longDescription);
        product.setPriceDescription(priceDescription);
        product.setCatalogNumber(catalogNumber);
        product.setPageTitle(pageTitle);
        product.setRating(rating);
        product.setPrice(price);
        product.setRecommendedPrice(recommendedPrice);
        product.setAvailable(available);
        product.setAttributes(Arrays.asList(Optional.ofNullable(attributes).orElse(new AttributeEntity[0])));
        return product;
    }

    protected OrderEntity createOrder(
        final Long id,
        final String clientMobile,
        final String clientName,
        final String title,
        final String description,
        final ProductEntity... products) {
        final OrderEntity order = new OrderEntity();
        order.setId(id);
        order.setClientMobile(clientMobile);
        order.setClientName(clientName);
        order.setTitle(title);
        order.setDescription(description);
        order.setProducts(Arrays.asList(Optional.ofNullable(products).orElse(new ProductEntity[0])));
        return order;
    }

    protected <E extends BaseModelEntity<ID>, ID extends Serializable> boolean containsIds(final List<? extends E> models, final String... idsToCheck) {
        final String[] categoryIds = models.stream().map(category -> category.getId()).toArray(String[]::new);
        Arrays.sort(categoryIds);
        Arrays.sort(idsToCheck);
        return Arrays.equals(categoryIds, idsToCheck);
    }

    protected UsernamePasswordAuthenticationToken getPrincipal(final UserDetailsService userDetailsService, final String username) {
        final UserDetails user = userDetailsService.loadUserByUsername(username);
        final UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, user.getPassword(), user.getAuthorities());
        return authentication;
    }

    protected MockHttpSession getSession(final UserDetailsService userDetailsService, final String username) {
        final UsernamePasswordAuthenticationToken principal = this.getPrincipal(userDetailsService, username);
        final MockHttpSession session = new MockHttpSession();
        session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, new BaseTest.MockSecurityContext(principal));
        return session;
    }

    protected Gson getGsonSerializer() {
        return new GsonBuilder()
            .setPrettyPrinting()
            .setLenient()
            .create();
    }
}
