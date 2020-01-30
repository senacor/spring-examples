package com.senacor.tecco.ilms.katas.peripheralsystems.e02_jackson_mapping.objectmapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.senacor.tecco.ilms.katas.peripheralsystems.e02_jackson_mapping.backend.model.Address;
import com.senacor.tecco.ilms.katas.peripheralsystems.e02_jackson_mapping.backend.model.AddressType;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

class CustomObjectMapperTest {

    @Test
    void mappingOfAdditionalFields_withDefaultMapper_throwsException() {
        String json = "{\n" +
                "        \"streetName\": \"testStreet\",\n" +
                "        \"number\": \"testNumber\",\n" +
                "        \"postcode\": \"testPostcode\",\n" +
                "        \"city\": \"testCity\",\n" +
                "        \"type\": \"HOME\",\n" +
                "        \"additionalInfo\": \"this is not part of the java object\"\n" +
                "}";

        ObjectMapper objectMapper = new ObjectMapper();

        Throwable throwable = catchThrowable(() -> objectMapper.readValue(json, Address.class));

        assertThat(throwable).isInstanceOf(JsonMappingException.class);

        System.out.println(throwable);
    }

    @Test
    void mappingOfAdditionalFields_withCustomMapper_succeeds() throws JsonProcessingException {
        String json = "{\n" +
                "        \"streetName\": \"testStreet\",\n" +
                "        \"number\": \"testNumber\",\n" +
                "        \"postcode\": \"testPostcode\",\n" +
                "        \"city\": \"testCity\",\n" +
                "        \"type\": \"HOME\",\n" +
                "        \"additionalInfo\": \"this is not part of the java object\"\n" +
                "}";

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        Address address = objectMapper.readValue(json, Address.class);


        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(address.getStreet()).isEqualTo("testStreet");
            softly.assertThat(address.getHouseNumber()).isEqualTo("testNumber");
            softly.assertThat(address.getPostcode()).isEqualTo("testPostcode");
            softly.assertThat(address.getCity()).isEqualTo("testCity");
            softly.assertThat(address.getType()).isEqualTo(AddressType.HOME);
        });
    }

    @Test
    void mappingOfJsonAlias_withCustomMapper_succeeds() throws JsonProcessingException {
        String json = "{\n" +
                "        \"street\": \"testStreet\",\n" +
                "        \"number\": \"testNumber\",\n" +
                "        \"postcode\": \"testPostcode\",\n" +
                "        \"city\": \"testCity\",\n" +
                "        \"type\": \"HOME\",\n" +
                "        \"additionalInfo\": \"this is not part of the java object\"\n" +
                "}";

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        Address address = objectMapper.readValue(json, Address.class);

        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(address.getStreet()).isEqualTo("testStreet");
            softly.assertThat(address.getHouseNumber()).isEqualTo("testNumber");
            softly.assertThat(address.getPostcode()).isEqualTo("testPostcode");
            softly.assertThat(address.getCity()).isEqualTo("testCity");
        });
    }

    @Test
    void mappingOfJsonPropertyWithDifferingFieldName_withCustomMapper_fails() throws JsonProcessingException {
        String json = "{\n" +
                "        \"streetName\": \"testStreet\",\n" +
                "        \"houseNumber\": \"testNumber\",\n" +
                "        \"postcode\": \"testPostcode\",\n" +
                "        \"city\": \"testCity\",\n" +
                "        \"type\": \"HOME\",\n" +
                "        \"additionalInfo\": \"this is not part of the java object\"\n" +
                "}";

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        Address address = objectMapper.readValue(json, Address.class);

        assertThat(address.getHouseNumber()).isNull();
    }

    @Test
    void mappingOfEnum_withUsageOfOrdinalDeactivated_fails() throws JsonProcessingException {
        String json = "{\n" +
                "        \"streetName\": \"testStreet\",\n" +
                "        \"number\": \"testNumber\",\n" +
                "        \"postcode\": \"testPostcode\",\n" +
                "        \"city\": \"testCity\",\n" +
                "        \"type\": 1\n" +
                "}";

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(DeserializationFeature.FAIL_ON_NUMBERS_FOR_ENUMS);

        Throwable throwable = catchThrowable(() -> objectMapper.readValue(json, Address.class));

        assertThat(throwable).isInstanceOf(InvalidFormatException.class);

        System.out.println(throwable);
    }

    @Test
    void mappingOfUnknownEnumToDefaultValue_withCustomMapper_succeeds() throws JsonProcessingException {
        String json = "{\n" +
                "        \"streetName\": \"testStreet\",\n" +
                "        \"number\": \"testNumber\",\n" +
                "        \"postcode\": \"testPostcode\",\n" +
                "        \"city\": \"testCity\",\n" +
                "        \"type\": \"THIS_CANNOT_BE_MAPPED_TO_ANYTHING\"\n" +
                "}";

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_USING_DEFAULT_VALUE);

        Address address = objectMapper.readValue(json, Address.class);

        assertThat(address.getType()).isEqualTo(AddressType.UNKNOWN);
    }

    @Test
    void mappingOfUnknownEnumToNull_withCustomMapper_succeeds() throws JsonProcessingException {
        String json = "{\n" +
                "        \"streetName\": \"testStreet\",\n" +
                "        \"number\": \"testNumber\",\n" +
                "        \"postcode\": \"testPostcode\",\n" +
                "        \"city\": \"testCity\",\n" +
                "        \"type\": \"THIS_CANNOT_BE_MAPPED_TO_ANYTHING\"\n" +
                "}";

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL);

        Address address = objectMapper.readValue(json, Address.class);

        assertThat(address.getType()).isNull();
    }

    @Test
    void mappingOfUnknownEnum_withDefaultMapper_fails() {
        String json = "{\n" +
                "        \"streetName\": \"testStreet\",\n" +
                "        \"number\": \"testNumber\",\n" +
                "        \"postcode\": \"testPostcode\",\n" +
                "        \"city\": \"testCity\",\n" +
                "        \"type\": \"THIS_CANNOT_BE_MAPPED_TO_ANYTHING\"\n" +
                "}";

        ObjectMapper objectMapper = new ObjectMapper();

        Throwable throwable = catchThrowable(() -> objectMapper.readValue(json, Address.class));

        assertThat(throwable).isInstanceOf(InvalidFormatException.class);

        System.out.println(throwable);
    }


}
