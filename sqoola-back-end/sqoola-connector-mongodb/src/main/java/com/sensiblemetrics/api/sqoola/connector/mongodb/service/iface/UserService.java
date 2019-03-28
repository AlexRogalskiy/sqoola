package com.sensiblemetrics.api.sqoola.connector.mongodb.service.iface;

public interface UserService {

    Flux<User> get();
    Mono<User> save(User user);
}
