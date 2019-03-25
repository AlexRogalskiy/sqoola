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
package com.sensiblemetrics.api.sqoola.common.controller.article.impl;

import com.sensiblemetrics.api.sqoola.common.controller.BaseModelController;
import com.sensiblemetrics.api.sqoola.common.model.dao.ArticleEntity;
import com.sensiblemetrics.api.sqoola.common.service.impl.ArticleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Controller
@RequestMapping("/article")
public class ArticleControllerImpl extends BaseModelController<ArticleEntity, ArticleView, String> {

    @Autowired
    private ArticleServiceImpl articleService;

    @GetMapping("article/{id}")
    public ResponseEntity<ArticleEntity> getArticleById(final @PathVariable("id") Long id) {
        ArticleEntity article = articleService.getArticleById(id);
        return new ResponseEntity<ArticleEntity>(article, HttpStatus.OK);
    }

    @GetMapping("articles")
    public ResponseEntity<List<ArticleEntity>> getAllArticles() {
        List<ArticleEntity> list = articleService.getAllArticles();
        return new ResponseEntity<List<ArticleEntity>>(list, HttpStatus.OK);
    }

    @PostMapping("article")
    public ResponseEntity<?> addArticle(final @RequestBody ArticleEntity article, final UriComponentsBuilder builder) {
        ArticleEntity savedArticle = articleService.addArticle(article);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/article/{id}").buildAndExpand(savedArticle.getArticleId()).toUri());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @PutMapping("article")
    public ResponseEntity<ArticleEntity> updateArticle(final @RequestBody ArticleEntity article) {
        articleService.updateArticle(article);
        return new ResponseEntity<ArticleEntity>(article, HttpStatus.OK);
    }

    @DeleteMapping("article/{id}")
    public ResponseEntity<?> deleteArticle(final @PathVariable("id") Long id) {
        articleService.deleteArticle(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
} 
