package com.senacor.tecco.ilms.katas.views;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by fsubasi on 18.01.2016.
 * This is a demo class which is used in many examples.
 */
@XmlRootElement
// Dont need this annotation when using MappingJackson2XmlHttpMessageConverter
// Required when using org.springframework.oxm.Marshaller
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
