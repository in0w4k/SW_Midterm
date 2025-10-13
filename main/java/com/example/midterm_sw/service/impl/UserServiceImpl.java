package com.example.midterm_sw.service.impl;

import com.example.midterm_sw.dto.UserDto;
import com.example.midterm_sw.mapper.UserMapper;
import com.example.midterm_sw.model.Role;
import com.example.midterm_sw.model.User;
import com.example.midterm_sw.repository.UserRepository;
import com.example.midterm_sw.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public List<UserDto> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::toDto)
                .toList();
    }

    @Override
    public UserDto getUserById(Long id) {
        return userMapper.toDto(userRepository.findById(id).orElse(null));
    }

    @Override
    public void addUser(UserDto dto) {
        User user = userMapper.toEntity(dto);
        if (user.getRole() == null) user.setRole(Role.CUSTOMER);
        userRepository.save(user);
    }

    @Override
    public void updateUser(Long id, UserDto dto) {
        User updatedUser = userMapper.toEntity(getUserById(id));
        if (dto.getName() != null) updatedUser.setName(dto.getName());
        if (dto.getEmail() != null) updatedUser.setEmail(dto.getEmail());
        if (dto.getPassword() != null) updatedUser.setPassword(dto.getPassword());
        if (dto.getRole() != null) updatedUser.setRole(dto.getRole());
        userRepository.save(updatedUser);
    }

    @Override
    public boolean deleteUser(Long id) {
        User deletedUser = userMapper.toEntity(getUserById(id));
        if (deletedUser == null) {
            return false;
        } else {
            userRepository.delete(deletedUser);
            return true;
        }
    }
}
