package com.senacor.tecco.ilms.katas.peripheralsystems.e02_jackson_mapping.backend.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Address {

    /**
     * Different mappings can be provided using @JsonAlias
     */
    @JsonAlias({"street", "streetName"})
    private String street;

    /**
     * A different property name for serialization/deserialization can be applied using @JsonProperty
     */
    @JsonProperty("number")
    private String houseNumber;

    private String postcode;
    private String city;
    private AddressType type;

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public AddressType getType() {
        return type;
    }

    public void setType(AddressType type) {
        this.type = type;
    }
}
