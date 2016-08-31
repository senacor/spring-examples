package com.example.e03_configurationproperties;

import org.springframework.boot.context.properties.ConfigurationProperties;
/**
 * Created by fsubasi on 13.01.2016
 * Here we see how a bean of class User is instantiated with data from external configuration
 * using @ConfigurationProperties
 */
@ConfigurationProperties("user")
public class User {
    private String firstName;
    private String lastName;
    private String email;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public static User createUser(){
        User user = new User();
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setEmail("johndoe@example.com");
        return user;
    }
}
