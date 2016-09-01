package com.senacor.tecco.ilms.katas.feign;

import com.senacor.tecco.ilms.katas.User;
import org.springframework.beans.factory.annotation.Autowired;
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

    @RequestMapping("/feign/getUserFromUsersService")
    public User getUserFromUsersService(){
        User user = userClient.getUser();
        return user;
    }
}
