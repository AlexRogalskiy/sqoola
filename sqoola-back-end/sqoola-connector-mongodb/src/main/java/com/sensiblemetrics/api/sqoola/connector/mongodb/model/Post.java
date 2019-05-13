package com.sensiblemetrics.api.sqoola.connector.mongodb.model;

@Document
@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
class Post {

    @Id
    private String id;
    private String title;
    private String content;


}