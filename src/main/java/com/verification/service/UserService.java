package com.verification.service;

import com.verification.entity.User;
import com.verification.payload.RequestDto;
import com.verification.payload.ResponseDto;
import com.verification.repo.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Random;

@Service
@AllArgsConstructor
public class UserService {

    private UserRepo userRepo;
    private SendOTPtoMailService sendOTPtoMailService;

    public ResponseDto registerUser(RequestDto requestDto){
        ResponseDto responseDto = new ResponseDto();

        User existingUser = userRepo.findByEmail(requestDto.getEmail());

        if (existingUser != null){
            responseDto.setMessage("User already registered");
        }else{
            //generate new OTP for new user first
            String generatedOtp = sendOTPtoMailService.generateOtp();

            User newUser = new User();
            newUser.setUsername(requestDto.getUsername());
            newUser.setEmail(requestDto.getEmail());
            newUser.setOtp(generatedOtp);
            newUser.setVerified(false);

            User savedUser = userRepo.save(newUser);
            //email and OTP sending
            sendOTPtoMailService.sendOtpToMail(savedUser.getEmail(), generatedOtp);

            responseDto.setUserid(savedUser.getUserid());
            responseDto.setUsername(savedUser.getUsername());
            responseDto.setEmail(savedUser.getEmail());
            responseDto.setOtp(generatedOtp);
            responseDto.setMessage("OTP sent successfully!");
        }
        return responseDto;
    }

    public String verifyUser(String email , String otp){
        String response = "";
        User user = userRepo.findByEmail(email);
        if(user != null && user.isVerified()){
            response = "User already verified!";
        } else if (otp.equals(user.getOtp())) {
            user.setVerified(true);
            userRepo.save(user);
            response = "User verified now!";
        }else{
            if (user == null) {
                response = "No such user not found";
            }else {
                response = "Wrong OTP... TRY AGAIN!";
            }
        }
        return response;
    }
}