package com.common;

import com.exercise.solution.UserNotFoundSolutionException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by amishra on 18.03.16.
 */
public class UserInMemoryDB {

    static HashMap<Integer,User> userMap = new HashMap<Integer,User>();

    public static void initialiseUsers()
    {
        User user1 = new User();
        user1.setUserId(1);
        user1.setFirstName("Jack");
        user1.setLastName("Doe");
        user1.setEmail("jackdoe@example.com");
        userMap.put(1,user1);

        User user2 = new User();
        user2.setUserId(2);
        user2.setFirstName("Brett");
        user2.setLastName("Lee");
        user2.setEmail("brettlee@example.com");
        userMap.put(2,user2);

        User user3 = new User();
        user3.setUserId(3);
        user3.setFirstName("Maria");
        user3.setLastName("Liu");
        user3.setEmail("marialiu@example.com");
        userMap.put(3,user3);

        User user4 = new User();
        user4.setUserId(4);
        user4.setFirstName("Shane");
        user4.setLastName("Lee");
        user4.setEmail("shanelee@example.com");
        userMap.put(4,user4);

        User user5 = new User();
        user5.setUserId(5);
        user5.setFirstName("Sophie");
        user5.setLastName("West");
        user5.setEmail("sophiewest@example.com");
        userMap.put(5,user5);
    }

    public static User createUser(){
        User user = new User();
        user.setUserId(123);
        user.setFirstName("Dummy");
        user.setLastName("User");
        user.setEmail("dummyuser@example.com");
        return user;
    }

    public User getUserFromID(int uId)
    {
        User userx = new User();
        if(userMap.containsKey(uId)) {
            userx = userMap.get(uId);
        }
        else {
            throw new UserNotFoundSolutionException("Invalid User Id. Enter an Id between 1-5");
        }
        return userx;
    }

    public User saveUsers(User user){
        int uid = userMap.size()+1;
        user.setUserId(uid);
        userMap.put(uid, user);
        return user;
    }

    public void updateUser(User user)
    {
        int uid= user.getUserId();
        userMap.remove(uid);
        userMap.put(uid,user);
    }

    public void deleteUser(int uid)
    {
        userMap.remove(uid);
    }

}
