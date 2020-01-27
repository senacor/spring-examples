package com.senacor.tecco.ilms.katas.testing.service;

import com.senacor.tecco.ilms.katas.testing.backend.AddressServiceClient;
import com.senacor.tecco.ilms.katas.testing.backend.model.Address;
import com.senacor.tecco.ilms.katas.testing.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
public class UserService {

    public static final String UID_CANNOT_BE_NULL = "uid cannot be null";

    private final AddressServiceClient addressServiceClient;

    HashMap<Integer, User> userMap = new HashMap<>();
    AtomicInteger userIdSequence = new AtomicInteger(0);

    public UserService(AddressServiceClient addressServiceClient) {
        this.addressServiceClient = addressServiceClient;
    }

    public int countUsers() {
        return userMap.size();
    }

    public List<User> getAllUsers() {
        return new ArrayList<>(userMap.values());
    }

    public User getUserFromID(Integer uId) {
        if (uId == null) {
            throw new IllegalArgumentException(UID_CANNOT_BE_NULL);
        }
        return userMap.get(uId);
    }

    public User saveUser(User user) {
        int userId = userIdSequence.incrementAndGet();
        user.setUserId(userId);
        userMap.put(userId, user);
        return user;
    }

    public User updateUser(Integer userId, User user) {
        User userToUpdate = userMap.getOrDefault(userId, user);
        userToUpdate.updateFrom(user);

        userMap.put(userId, userToUpdate);
        return userToUpdate;
    }

    public void deleteUser(int uid) {
        userMap.remove(uid);
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
