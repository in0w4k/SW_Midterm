package com.example.midterm_sw.dto;

import com.example.midterm_sw.model.Role;
import lombok.Data;

@Data
public class UserDto {
    private Long id;
    private String name;
    private String email;
    private String password;
    private Role role;
}
