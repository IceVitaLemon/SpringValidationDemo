package com.example.springvalidationdemo.entity;

import lombok.Data;

@Data
public class User {
    
    private Long userId;
    
    private String userName;
    
    private String mobile;

    private String sex;
    
    private String email;
}
