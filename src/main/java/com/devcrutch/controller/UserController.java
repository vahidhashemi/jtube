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

    @PutMapping(value = "users/{id}/password")
    public User changePassword(@PathVariable Long id, @RequestBody String password) {
        User existingUser = userRepository.findById(id).get();
        existingUser.setPassword(password);
        return userRepository.saveAndFlush(existingUser);
    }

    @PutMapping(value = "users/{id}/promote")
    public User prompoteUser(@PathVariable Long id) {
        return makeUserAdmin(id, true);
    }

    @PutMapping(value = "users/{id}/demote")
    public User demoteUser(@PathVariable Long id) {
        return makeUserAdmin(id, false);
    }


    private User makeUserAdmin(Long id, boolean isAdmin) {
        User existingUser = userRepository.findById(id).get();
        existingUser.setAdmin(isAdmin);
        return userRepository.saveAndFlush(existingUser);
    }

}
