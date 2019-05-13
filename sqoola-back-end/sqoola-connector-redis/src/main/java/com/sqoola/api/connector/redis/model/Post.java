package com.sqoola.api.connector.redis.model;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@RedisHash("posts")
class Post {
    
    @Id
    private String id;
    private String title;
    private String content;
}