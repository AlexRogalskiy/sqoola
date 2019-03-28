package com.sensiblemetrics.api.sqoola.connector.mongodb.service.impl;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Override
    public Flux<User> get() {
        return userRepository.findAll();
    }

    @Override
    public Mono<User> save(User user) {
        return userRepository.save(user);
    }

    @Override
    public Flux<User> getByLastName(final String lastName) {
        return userRepository
            .findAll()
            .filter(user -> user.getLastName().equals(lastName));
    }
}
