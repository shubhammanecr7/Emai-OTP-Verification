package com.verification.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;

@Service
public class SendOTPtoMailService {
    @Autowired
    JavaMailSender javaMailSender;

    /**
     * Generates a 6-digit One Time Password (OTP) using a secure random number generator.
     * @return a string representing the generated OTP.
     */
    public String generateOtp(){
        //generate 6 digit otp
        SecureRandom secureRandom = new SecureRandom();
        int otp = 100000 +  secureRandom.nextInt(900000);
        return String.valueOf(otp);
    }

    private void sendOtpToProvidedEmail(String email , String otp) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeHelper = new MimeMessageHelper(mimeMessage);
        mimeHelper.setTo(email);
        mimeHelper.setSubject("OTP from Email-OTP-Verification-App by '©ShubhamMane'");
        mimeHelper.setText(" Your OTP is - "+otp);

        javaMailSender.send(mimeMessage);
    }

    public void sendOtpToMail(String email, String otp) {
        try {
            sendOtpToProvidedEmail(email, otp);
        } catch (MessagingException e) {
            throw new RuntimeException("Unable to send otp message");
        }
    }
}