package com.senacor.tecco.ilms.katas.common.model;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by fsubasi on 18.01.2016.
 * This is a demo class which is used in many examples.
 */
@XmlRootElement
// Dont need this annotation when using MappingJackson2XmlHttpMessageConverter
// Required when using org.springframework.oxm.Marshaller
public class User {
    private int userId;
    private String firstName;
    private String lastName;
    private String email;

    public User(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public User() {
    }

    public int getUserId() {return userId;}

    public void setUserId(int userId) {this.userId = userId;}

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

    @Override
    public String toString() {
        return getUserId() + " -- " + getFirstName() + " " + getLastName() + " --- " + getEmail();
    }

}
