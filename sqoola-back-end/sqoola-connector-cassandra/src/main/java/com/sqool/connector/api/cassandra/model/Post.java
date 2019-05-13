package com.sqool.connector.api.cassandra.model;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("posts")
class Post {

    @PrimaryKey()
    @Builder.Default
    private String id = UUID.randomUUID().toString();
    private String title;
    private String content;

}