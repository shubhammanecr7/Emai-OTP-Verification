package com.verification.controller;

import com.verification.payload.RequestDto;
import com.verification.payload.ResponseDto;
import com.verification.service.SendOTPtoMailService;
import com.verification.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {

    private UserService userService;
    private SendOTPtoMailService sendOTPtoMailService;

    @PostMapping("/register")
    public ResponseEntity<ResponseDto> registerNewUser(@RequestBody RequestDto requestDto){
        ResponseDto responseDto = userService.registerUser(requestDto);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @PostMapping("/verify")
    public ResponseEntity<?> verifyUser(
            @RequestParam String email,@RequestParam String otp)
    {
        String responseMsg = userService.verifyUser(email, otp);
        return new ResponseEntity<>(responseMsg,HttpStatus.OK);
    }

    @PostMapping("/sendOtp/{email}")
    public String sendOtpToMail(@PathVariable String email){
        System.out.println(email);
        String generatedOtp = sendOTPtoMailService.generateOtp();
        sendOTPtoMailService.sendOtpToMail(email,generatedOtp);
        return "OTP sent successfully";
    }
}
