package com.sqoola.api.connector.redis.controller;

@RestController()
@RequestMapping(value = "/favorites")
class FavoriteController {

    private final ReactiveRedisConnectionFactory factory;

    public FavoriteController(ReactiveRedisConnectionFactory factory) {
        this.factory = factory;
    }

    @GetMapping("")
    public Mono<List<String>> all() {
        return this.factory.getReactiveConnection()
                .setCommands()
                .sMembers(ByteBuffer.wrap("users:user:favorites".getBytes()))
                .map(FavoriteController::toString)
                .collectList();
    }

    private static String toString(ByteBuffer byteBuffer) {

        byte[] bytes = new byte[byteBuffer.remaining()];
        byteBuffer.get(bytes);
        return new String(bytes);
    }

}