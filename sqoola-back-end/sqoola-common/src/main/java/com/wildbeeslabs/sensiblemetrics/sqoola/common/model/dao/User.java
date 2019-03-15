package com.wildbeeslabs.sensiblemetrics.sqoola.common.model.dao;

import java.util.List;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

public class User {

   @Size(max = 20, min = 3, message = "{user.name.empty}")
   private String name;

   @Email(message = "{user.email.invalid}")
   private String email;

   @NotEmpty(message = "{user.gender.empty}")
   private String gender;

   @NotEmpty(message = "{user.languages.empty}")
   private List<String> languages;

    private String email;
    private String password;
    private String fname;
    private String mname;
    private String lname;
    private int age;

   // Getter and Setter method

}
