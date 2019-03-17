package com.sensiblemetrics.api.sqoola.common.model.dao;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

public class User {

    @Size(max = 20, min = 3, message = "{user.name.empty}")
    private String name;

    @Email(message = "{user.email.invalid}")
    private String email;

    @NotEmpty(message = "{user.gender.empty}")
    private String gender;

    @NotEmpty(message = "{user.languages.empty}")
    private List<String> languages;

    private String password;
    private String fname;
    private String mname;
    private String lname;
    private int age;
}
