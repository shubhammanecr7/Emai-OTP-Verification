package com.verification.payload;


import lombok.Data;
/**
 * DTO for (username,email)*/
@Data
public class RequestDto {
    private String username;
    private String email;
}
