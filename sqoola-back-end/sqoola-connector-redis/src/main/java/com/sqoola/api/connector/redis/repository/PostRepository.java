package com.sqoola.api.connector.redis.repository;

public interface PostRepository extends KeyValueRepository<Post, String> {

    @Override
    public List<Post> findAll();
}