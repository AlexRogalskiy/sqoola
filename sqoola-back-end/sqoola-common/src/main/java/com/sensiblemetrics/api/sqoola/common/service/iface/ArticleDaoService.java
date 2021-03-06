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
package com.sensiblemetrics.api.sqoola.common.service.iface;

import com.sensiblemetrics.api.sqoola.common.model.dao.ArticleEntity;

import java.io.Serializable;
import java.util.Optional;

/**
 * {@link ArticleEntity} service declaration
 *
 * @param <E>  type of {@link ArticleEntity}
 * @param <ID> type of article document identifier {@link Serializable}
 */
public interface ArticleDaoService<E extends ArticleEntity<ID>, ID extends Serializable> extends BaseDaoService<E, ID> {

    Iterable<? extends E> getAllArticles();

    Optional<? extends E> getArticleById(final ID articleId);

    ArticleEntity addArticle(final E article);

    ArticleEntity updateArticle(final E article);

    void deleteArticle(final ID id);
}
