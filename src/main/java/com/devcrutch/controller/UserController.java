package com.devcrutch.controller;

import com.devcrutch.model.User;
import com.devcrutch.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @PostMapping(value = "users")
    public User create(@RequestBody User user) {
        return userRepository.saveAndFlush(user);
    }

    @RequestMapping(value = "users/{id}/password", method = RequestMethod.PUT)
    public User changePassword(@PathVariable Long id, @RequestBody String password) {
        User existingUser = userRepository.findById(id).get();
        existingUser.setPassword(password);
        return userRepository.saveAndFlush(existingUser);
    }
}
