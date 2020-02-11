package com.senacor.tecco.ilms.katas.example.e02_contentnegotiation;

import com.senacor.tecco.ilms.katas.common.model.User;
import com.senacor.tecco.ilms.katas.common.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Dr. Michael Menzel, Senacor Technology AG
 *
 */

@RestController
@RequestMapping("/mapping")
public class UserGetMappingController {

	UserService userService = new UserService();
	
    @GetMapping("/users/{userId}")
    public User createResponseEntity(@PathVariable("userId") int userId){
        // Use userId to get the user
        User user = userService.getUserFromID(userId);

        return user;
    }
}
