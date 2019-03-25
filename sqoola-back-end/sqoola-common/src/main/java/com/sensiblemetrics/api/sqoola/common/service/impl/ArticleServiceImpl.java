package com.sensiblemetrics.api.sqoola.common.service.impl;

import com.concretepage.repository.ArticleRepository;
import com.sensiblemetrics.api.sqoola.common.model.dao.ArticleEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleServiceImpl extends BaseDaoServiceImpl<ArticleEntity, Long> implements ArticleDaoService {
	@Autowired
	private ArticleRepository articleRepository;
	
	@Override	
	@Cacheable(value= "articleCache", key= "#articleId")		
	public ArticleEntity getArticleById(long articleId) {
		System.out.println("--- Inside getArticleById() ---");		
		return  articleRepository.findById(articleId).get();
	}
	@Override	
	@Cacheable(value= "allArticlesCache", unless= "#result.size() == 0")	
	public List<ArticleEntity> getAllArticles(){
		System.out.println("--- Inside getAllArticles() ---");
		List<ArticleEntity> list = new ArrayList<>();
		articleRepository.findAll().forEach(e -> list.add(e));
		return list;
	}

    public Map<Integer, String> findAllPersons() {
        return StreamSupport.stream(personRepository.findAll().spliterator(), false)
            .collect(toMap((Function<Person, Integer>) person -> person.getId(),
                (Function<Person, String>) person -> person.getFirstName() + " " + person.getLastName()));
    }

    public List<String> findAllEmployees() {
        return StreamSupport.stream(employeeRepository.findAll().spliterator(), false)
            .map(e -> e.getDomainName())
            .collect(Collectors.toList());
    }

	@Override	
	@Caching(
		put= { @CachePut(value= "articleCache", key= "#article.articleId") },
		evict= { @CacheEvict(value= "allArticlesCache", allEntries= true) }
	)
	public ArticleEntity addArticle(ArticleEntity article){
		System.out.println("--- Inside addArticle() ---");		
		return articleRepository.save(article);
	}
	@Override	
	@Caching(
		put= { @CachePut(value= "articleCache", key= "#article.articleId") },
		evict= { @CacheEvict(value= "allArticlesCache", allEntries= true) }
	)
	public ArticleEntity updateArticle(ArticleEntity article) {
		System.out.println("--- Inside updateArticle() ---");		
		return articleRepository.save(article);
	}
	@Override	
	@Caching(
		evict= { 
			@CacheEvict(value= "articleCache", key= "#articleId"),
			@CacheEvict(value= "allArticlesCache", allEntries= true)
		}
	)
	public void deleteArticle(long articleId) {
		System.out.println("--- Inside deleteArticle() ---");		
		articleRepository.delete(articleRepository.findById(articleId).get());
	}
} 
