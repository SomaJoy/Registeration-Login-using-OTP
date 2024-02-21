package com.otpExample.service;

import com.otpExample.entity.User;
import com.otpExample.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User registerUser(User user){
        return userRepository.save(user);
    }

    public User getUserByEmail(String email) {
        User user = userRepository.findByEmail(email);
        return user;
    }

    public void verifyByEmail(User user) {
        user.setEmailVerify(true);
        userRepository.save(user);
    }

    public boolean isEmailVerified(String email) {

        User user = userRepository.findByEmail(email);
        return user != null && user.isEmailVerify();
    }
}
