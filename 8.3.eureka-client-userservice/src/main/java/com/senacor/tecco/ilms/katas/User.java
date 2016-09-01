package com.senacor.tecco.ilms.katas;

/**
 * Created by fsubasi on 01.02.2016.
 */
@SuppressWarnings("Duplicates")
public class User {
    private String firstName;
    private String lastName;
    private String emailAddress;

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

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public static User createUser(){
        User user = new User();
        user.setFirstName("Andreas");
        user.setLastName("Keefer");
        user.setEmailAddress("andreas.keefer@senacor.com");
        return user;
    }
}
