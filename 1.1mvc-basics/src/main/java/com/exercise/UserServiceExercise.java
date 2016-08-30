package com.exercise;

import com.common.model.User;
import com.common.service.UserService;

import java.util.List;

/**
 * Created by amishra on 17.03.16.
 * -------------------------------------------------------------------------------------------
 * Exercise to create services to work with Users
 * -------------------------------------------------------------------------------------------
 *
 * Annotate the class to make it as a controller and be able to identify it with "userService".
 *
 *
 * Get : 1. /user?lastName=
 *          Write a method "getUsersByLastName", to get all users who have a particular last name.
 *          You can use the method getUserListByLastName of UserBean class to get the list of users.
 *
 *       2. /user/{id}
 *          Write a method "getUserFromId", to return the user when the id is passed in the request.
 *          The method should return a user only when the id is an integer, otherwise it should
 *          return an error. Use the method "getUser" of UserBean class to get the user by passing the id.
 *
 *
 * Post : 1. /user/
 *           Write a method "postUser" to post the details of a user and return a success message.
 *        2. /user/saveUser
             Write a method "saveUser" to take the details of a new user and save it in the map.
 *           Generate a user id for this particular user using the "generateUserId" method of UserBean class,
 *           update the user object with this paticular id and save it to the map.
 *           If successfully completed, return a response with the user object and a custom location to access the user in the header.
 *
 * Put : 1. /user/
 *          Write a method "putUser" to update the details of a user and return a success message.
 *          Use method "updateUser" of the UserBean class to update the map.
 *
 * Delete : 2. /user/{id}
 *          Write a method "deleteUser" to delete the user whose id is given.
 *          Use method "deleteUser" of the UserBean class to delete the user from the map.
 */

public class UserServiceExercise {

	UserService userService;
	
    public UserServiceExercise() {
    	userService = new UserService();
    }
    
    /**********************************************************************
     *
     * GET
     *
     *********************************************************************/

    // /user?lastName=
    public List<User> getUsersByLastName() {
        return null;
    }

    // /user/{id}
    public User getUserFromId(){
        return null;
    }

    /**********************************************************************
     *
     * POST
     *
     *********************************************************************/

    // /user/
    public String postUser() {

        return "Request Successful";
    }

    // /user/saveUser
    public String saveUsers(){
        //int uid = generateUserId(user);

        return null;
    }

    /**********************************************************************
     *
     * PUT
     *
     *********************************************************************/

    // /user/
    public String putUser() {

        //updateUser(user);
        //String response = "The details have been updated for the user with id : " + Integer.toString(user.getUserId());
        return null;

    }


    /**********************************************************************
     *
     * Delete
     *
     *********************************************************************/

    // /user/{id}
    public String delete() {

        //deleteUser(uId);
        //String response = "The user with id " + Integer.toString(uId) + "has been deleted";
        return null;
    }

}
