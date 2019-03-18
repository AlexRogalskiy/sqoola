package com.sensiblemetrics.api.sqoola.common.service.dao;

import com.sensiblemetrics.api.sqoola.common.model.dao.Article;

import java.util.List;

public interface IArticleService {
    List<Article> getAllArticles();

    Article getArticleById(final Long articleId);

    Article addArticle(final Article article);

    Article updateArticle(final Article article);

    void deleteArticle(final Long articleId);
} 
