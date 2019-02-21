package com.devcrutch;

import com.devcrutch.model.User;
import com.devcrutch.service.UserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


public class UserServiceTest extends MainTest{
    @Autowired
    private UserService userService;

    @Test
    public void testCreateUser() {
        User user = new User("user", "password", false);
        User createdUser = userService.createUser(user);
        assertNotNull(createdUser);
        assertEquals("user", createdUser.getUsername());
        assertEquals("password", createdUser.getPassword());
        assertEquals(false, createdUser.isAdmin());
    }

    @Test
    public void testGetAllUsers() {
        List<User> userList = userService.getAllusers();
        assertNotNull(userList);
    }

    @Test
    public void testUpdateUserProfile() {
        Long userId;
        User user = new User("user10", "qwerty", false);
        User createdUser = userService.createUser(user);
        userId = createdUser.getId();
        assertNotNull(createdUser);
        createdUser.setPassword("qazwsx");
        userService.updateUserProfile(user);
        User updatedUser = userService.getUserInfo(userId);
        assertEquals("qazwsx", updatedUser.getPassword());
    }

    @Test
    public void testAddNonAdminUser() {
        User user = new User("user1", "qwerty",false);
        User savedUser = userService.createUser(user);
        assertNotNull(savedUser);
        assertEquals(user, savedUser);
    }

    @Test
    public void testAddAdminUser() {
        User user = new User("admin", "qwerty", true);
        User savedUser = userService.createUser(user);
        assertNotNull(savedUser);
        assertEquals(user, savedUser);
    }

    @Test
    public void testChangePassword() {
        String newPassword = "qwerty";
        User user = new User("dummy", " qwe", false);
        User savedUser = userService.createUser(user);
        Long savedUserId = savedUser.getId();
        savedUser.setPassword(newPassword);
        userService.createUser(savedUser);
        User retrievedUser =  userService.getUserInfo(savedUserId);
        assertEquals(newPassword, retrievedUser.getPassword());
    }

    @Test
    public void testMakeUserAdmin() {
        User user = new User("dummy2", "qwe", false);
        User savedUser = userService.createUser(user);
        User adminUser = userService.makeUserAdmin(savedUser.getId(), true);
        assertEquals(true, adminUser.isAdmin());
    }


}
