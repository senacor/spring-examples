package com.senacor.tecco.ilms.katas;

import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by fsubasi on 18.01.2016.
 * This is a demo class which is used in many examples.
 */
@XmlRootElement
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
}
