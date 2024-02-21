package com.otpExample.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import static com.otpExample.service.EmailVerificationService.emailOtpMapping;
@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private UserService userService;

    public String generateOtp(){
        return String.format("%06d", new java.util.Random().nextInt(1000000));
    }
    public void sendOtpEmail(String email) {
        String opt = generateOtp();
        emailOtpMapping.put(email, opt);

        sendEmail(email, "OTP for email verification", "Your OTP is : "+opt);
    }

    private void sendEmail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        //message.setFrom("your.gmail@gmail.com");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        javaMailSender.send(message);
    }
}
