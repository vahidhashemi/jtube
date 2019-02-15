package com.devcrutch.controller;

import com.devcrutch.model.User;
import com.devcrutch.repository.UserRepository;
import com.devcrutch.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/")
public class UserController {

    private static final String ERROR_USER_NOT_FOUND = "Error 404 - No Such User";
    @Autowired
    private UserService userService;

    @PostMapping(value = "users")
    public User create(@RequestBody User user) {
        return userService.createUser(user);
    }

    @GetMapping(value = "users")
    public List<User> get() {
        return userService.getAllusers();
    }

    @GetMapping(value = "users/{id}")
    public User get(@PathVariable Long id) {
        User user = userService.getUserInfo(id);
        if (user == null)
            throw new UserNotFoundException(ERROR_USER_NOT_FOUND);
        return user;
    }
    @PutMapping(value = "users")
    public User updateProfile(@RequestBody User user) {
        User updatedUser = userService.updateUserProfile(user);
        if (updatedUser == null)
            throw new UserNotFoundException(ERROR_USER_NOT_FOUND);
        return updatedUser;
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
        User existedUser = userService.makeUserAdmin(id, isAdmin);
        if(existedUser == null)
            throw new UserNotFoundException(ERROR_USER_NOT_FOUND);
        return existedUser;
    }

}
