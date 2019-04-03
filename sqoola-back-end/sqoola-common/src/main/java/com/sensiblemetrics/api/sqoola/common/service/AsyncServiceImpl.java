package com.sensiblemetrics.api.sqoola.common.service;

@Service
public class AsyncServiceImpl{
    /** .. Init repository instances .. **/

    @Async(AsyncConfiguration.TASK_EXECUTOR_SERVICE)
    public CompletableFuture<Token> getTokenByUser(Credential credential) {
        return userRepository.getUser(credential)
            .thenCompose(s -> TokenRepository.getToken(s));
    }
}

@Repository
public class UserRepository {

    @Async(AsyncConfiguration.TASK_EXECUTOR_REPOSITORY)
    public CompletableFuture<User> getUser(Credential credentials) {
        return CompletableFuture.supplyAsync(() -> 
            new User(credentials.getUsername())
        );
    }       
}

@Repository
public class TokenRepository {

    @Async(AsyncConfiguration.TASK_EXECUTOR_REPOSITORY)
    public CompletableFuture<Token> getToken(User user) {
        return CompletableFuture.supplyAsync(() -> 
            new Token(user.getUserId())
        );
    }
}
