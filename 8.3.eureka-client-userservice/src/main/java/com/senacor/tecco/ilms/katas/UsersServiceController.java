package com.senacor.tecco.ilms.katas;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by fsubasi on 21.01.2016.
 */
@RestController
public class UsersServiceController {

    @RequestMapping("/user")
    public User byNumber() {
        return User.createUser();
    }

}
