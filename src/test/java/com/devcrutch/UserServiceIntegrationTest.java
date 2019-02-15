package com.devcrutch;

import com.devcrutch.model.User;
import com.devcrutch.repository.UserRepository;
import com.devcrutch.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
@TestPropertySource(locations = "classpath:application-test.properties")
public class UserServiceIntegrationTest {
    @Autowired
    private UserService userService;

    protected User createUser(String username, String password, boolean isAdmin) {
        return new User(username, password, isAdmin);
    }
    @Test
    public void addNonAdminUser() {
        User user = createUser("user1", "qwerty",false);
        User savedUser = userService.createUser(user);
        assertNotNull(savedUser);
        assertEquals(user, savedUser);
    }

    @Test
    public void addAdminUser() {
        User user = createUser("admin", "qwerty", true);
        User savedUser = userService.createUser(user);
        assertNotNull(savedUser);
        assertEquals(user, savedUser);
    }

    @Test
    public void changePassword() {
        String newPassword = "qwerty";
        User user = createUser("dummy", " qwe", false);
        User savedUser = userService.createUser(user);
        Long savedUserId = savedUser.getId();
        savedUser.setPassword(newPassword);
        userService.createUser(savedUser);
        User retrievedUser =  userService.getUserInfo(savedUserId);
        assertEquals(newPassword, retrievedUser.getPassword());
    }

    @Test
    public void makeUserAdmin() {
        User user = createUser("dummy2", "qwe", false);
        User savedUser = userService.createUser(user);
        User adminUser = userService.makeUserAdmin(savedUser.getId(), true);
        assertEquals(true, adminUser.isAdmin());
    }


}
