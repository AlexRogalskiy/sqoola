package com.sensiblemetrics.api.sqoola.connector.mongodb.model;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
public class User {

    @Id
    private String id;
    private String firstName;
    private String lastName;
}
