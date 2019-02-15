package com.devcrutch.service;

import com.devcrutch.model.User;
import com.devcrutch.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public User createUser(User user) {
        return userRepository.saveAndFlush(user);
    }

    public List<User> getAllusers() {
        return userRepository.findAll();
    }

    public User getUserInfo(Long id) {
        if (userRepository.findById(id).isPresent()) {
            return userRepository.findById(id).get();
        }
        return null;
    }

    public User updateUserProfile(User user) {
        User existedUser = getUserInfo(user.getId());
        if (existedUser != null) {
            return userRepository.saveAndFlush(user);
        }
        return null;
    }

    public User makeUserAdmin(Long id, boolean isAdmin) {
        User user = getUserInfo(id);
        if (user != null) {
            user.setAdmin(true);
            return updateUserProfile(user);
        }
         return null;
    }
}
