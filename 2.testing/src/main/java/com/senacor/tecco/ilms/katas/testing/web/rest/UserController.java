package com.senacor.tecco.ilms.katas.testing.web.rest;

import com.senacor.tecco.ilms.katas.testing.model.User;
import com.senacor.tecco.ilms.katas.testing.response.UserResponse;
import com.senacor.tecco.ilms.katas.testing.service.UserService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{userId}")
    public UserResponse getUser(@PathVariable Integer userId) {
        User user = userService.getUserFromID(userId);

        return new UserResponse(user);
    }

    @PostMapping
    public UserResponse createUser(@RequestBody User user) {
        User createdUser = userService.saveUser(user);

        return new UserResponse(createdUser);
    }

    @PutMapping
    public UserResponse updateUser(@RequestBody User user) {
        User updatedUser = userService.updateUser(user);

        return new UserResponse(updatedUser);
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable Integer userId) {
        userService.deleteUser(userId);
    }


}
