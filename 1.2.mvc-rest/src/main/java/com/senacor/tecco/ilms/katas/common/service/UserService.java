package com.senacor.tecco.ilms.katas.common.service;

import com.senacor.tecco.ilms.katas.common.model.User;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 *
 */
@Component
public class UserService {

    HashMap<Integer,User> userMap = new HashMap<Integer,User>();
    AtomicInteger userIdSequence = new AtomicInteger(0);

    public UserService() {
    	saveUser(new User("Jack", "Doe", "jackdoe@example.com"));
        saveUser(new User("Brett", "Lee", "brettlee@example.com"));
        saveUser(new User("Maria", "Liu", "marialiu@example.com"));
        saveUser(new User("Shane", "Lee", "shanelee@example.com"));
        saveUser(new User("Sophie", "West", "sophiewest@example.com"));
    }

    public User getUserFromID(int uId) {
        return userMap.get(uId);
    }

    public List<User> getUsersByLastName(String lastName) {
        return userMap.values()
            .stream()
            .filter(user -> lastName.equals(user.getLastName()))
            .collect(Collectors.toList());
    }

    public User saveUser(User user) {
        int userId = userIdSequence.incrementAndGet();
        user.setUserId(userId);
        userMap.put(userId, user);
        return user;
    }

    public User updateUser(User user) {
        int uid = user.getUserId();
        userMap.put(uid,user);
        return user;
    }

    public void deleteUser(int uid) {
        userMap.remove(uid);
    }
    
}
