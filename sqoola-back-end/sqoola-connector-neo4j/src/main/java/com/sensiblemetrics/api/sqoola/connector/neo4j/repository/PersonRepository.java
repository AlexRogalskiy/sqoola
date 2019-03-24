package com.sensiblemetrics.api.sqoola.connector.neo4j.repository;

import com.sensiblemetrics.api.sqoola.connector.neo4j.model.Person;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PersonRepository extends CrudRepository<Person, Long> {

    Optional<Person> findByName(String name);
}
