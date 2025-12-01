package com.example.midterm_sw.service;

import com.example.midterm_sw.dto.UserDto;

import java.util.List;

public interface UserService {
    List<UserDto> getAllUsers();
    UserDto getUserById(Long id);
    UserDto addUser(UserDto dto);
    void updateUser(Long id, UserDto dto);
    boolean deleteUser(Long id);
}
