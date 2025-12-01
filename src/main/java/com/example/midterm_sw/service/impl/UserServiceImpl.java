package com.example.midterm_sw.service.impl;

import com.example.midterm_sw.dto.UserDto;
import com.example.midterm_sw.mapper.UserMapper;
import com.example.midterm_sw.model.Role;
import com.example.midterm_sw.model.User;
import com.example.midterm_sw.repository.UserRepository;
import com.example.midterm_sw.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

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
    public UserDto addUser(UserDto dto) {
        User user = userMapper.toEntity(dto);
        if (user.getRole() == null) user.setRole(Role.CUSTOMER);
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        userRepository.save(user);
        return userMapper.toDto(user);
    }

    @Override
    public void updateUser(Long id, UserDto dto) {
        User updatedUser = userRepository.findById(id)
                .orElseThrow();
        if (dto.getName() != null) updatedUser.setName(dto.getName());
        if (dto.getEmail() != null) updatedUser.setEmail(dto.getEmail());
        if (dto.getPassword() != null && !dto.getPassword().isEmpty()) updatedUser.setPassword(passwordEncoder.encode(dto.getPassword()));
        if (dto.getRole() != null) updatedUser.setRole(dto.getRole());
        userRepository.save(updatedUser);
    }

    @Override
    public boolean deleteUser(Long id) {
        User deletedUser = userRepository.findById(id)
                .orElseThrow();
        userRepository.delete(deletedUser);
        return true;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);

        if (Objects.nonNull(user)) {
            return user;
        }
        throw new UsernameNotFoundException("User with this email not found: " + username);
    }
}