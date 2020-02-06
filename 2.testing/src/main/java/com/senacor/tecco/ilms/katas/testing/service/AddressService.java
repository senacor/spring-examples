package com.senacor.tecco.ilms.katas.testing.service;

import com.senacor.tecco.ilms.katas.testing.backend.AddressServiceClient;
import com.senacor.tecco.ilms.katas.testing.backend.model.Address;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AddressService {

    public static final String UID_CANNOT_BE_NULL = "uid cannot be null";

    private final AddressServiceClient addressServiceClient;

    public AddressService(AddressServiceClient addressServiceClient) {
        this.addressServiceClient = addressServiceClient;
    }

    public List<String> getAddressForUserWithId(int userId) {
        List<Address> userAddress = addressServiceClient.getUserAddresses(userId);

        return userAddress.stream().map(this::addressToFormattedString).collect(Collectors.toList());
    }

    private String addressToFormattedString(Address address) {
        StringBuilder addressString = new StringBuilder();
        addressString.append(address.getStreet());
        addressString.append(" ");
        addressString.append(address.getNumber());

        addressString.append(System.lineSeparator());

        addressString.append(address.getPostcode());
        addressString.append(" ");
        addressString.append(address.getCity());
        return addressString.toString();
    }
}
