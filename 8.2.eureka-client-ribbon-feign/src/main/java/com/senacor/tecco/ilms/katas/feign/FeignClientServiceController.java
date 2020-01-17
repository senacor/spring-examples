package com.senacor.tecco.ilms.katas.feign;

import com.senacor.tecco.ilms.katas.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by fsubasi on 26.01.2016.
 */
@RestController
public class FeignClientServiceController {

    private final UserClient userClient;

    public FeignClientServiceController(UserClient userClient) {
        this.userClient = userClient;
    }

    @RequestMapping("/feign/getUserFromUsersService")
    public User getUserFromUsersService(){
        User user = userClient.getUser();
        return user;
    }
}
