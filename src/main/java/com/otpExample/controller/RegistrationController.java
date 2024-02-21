package com.otpExample.controller;

import com.otpExample.entity.User;
import com.otpExample.service.EmailService;
import com.otpExample.service.EmailVerificationService;
import com.otpExample.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class RegistrationController {

    @Autowired
    private UserService userService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private EmailVerificationService emailVerificationService;

    @PostMapping("/register")
    public Map<String, String> registerUser(@RequestBody User user){

        //Register user without email verification.
        User registerUser = userService.registerUser(user);

        //Send OTP to email for email verification.
        emailService.sendOtpEmail(user.getEmail());

        Map<String, String> response = new HashMap<>();
        response.put("status", "success");
        response.put("message", "User registered successfully. Check your email for verification.");
        return response;
    }

    @PostMapping("/verify-otp")
    public Map<String, String> verifyOtp(@RequestParam String email, @RequestParam String otp){
        return emailVerificationService.verifyOtp(email, otp);
    }
}
