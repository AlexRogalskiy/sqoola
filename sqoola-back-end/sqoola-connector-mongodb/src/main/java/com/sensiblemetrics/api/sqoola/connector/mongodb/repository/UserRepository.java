package com.sensiblemetrics.api.sqoola.connector.mongodb.repository;

import org.faoxis.habrreactivemongo.domain.User;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface UserRepository extends ReactiveMongoRepository<User, String> {
}
