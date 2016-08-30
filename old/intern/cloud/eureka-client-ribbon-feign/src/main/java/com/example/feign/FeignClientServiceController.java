package com.example.feign;

import com.example.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by fsubasi on 26.01.2016.
 */
@RestController
public class FeignClientServiceController {

    @Autowired
    private UserClient userClient;

    @RequestMapping("/feign/getUserFromUsersService/{id}")
    public User getUserFromUsersService(@PathVariable("id") int id){
        User user = userClient.getUser(id);
        return user;
    }
}
