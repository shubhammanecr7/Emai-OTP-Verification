package com.verification.payload;

import lombok.Data;
/**
 * DTO for userid, username, email, verified, message, ~otp~ */
@Data
public class ResponseDto {
    private Long userid;
    private String username;
    private String email;
    private boolean verified;
    private String message;
    private String otp;
}