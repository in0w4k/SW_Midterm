package com.example.midterm_sw.mapper;

import com.example.midterm_sw.dto.UserDto;
import com.example.midterm_sw.model.Role;
import com.example.midterm_sw.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    void convertEntityToDtoTest(){
        User entityUser = new User(1L, "test", "test@test.test", "test123", Role.ADMIN, null);

        UserDto dtoUser = userMapper.toDto(entityUser);

        Assertions.assertNotNull(dtoUser);

        Assertions.assertNotNull(dtoUser.getId());
        Assertions.assertNotNull(dtoUser.getName());
        Assertions.assertNotNull(dtoUser.getEmail());
        Assertions.assertNotNull(dtoUser.getPassword());
        Assertions.assertNotNull(dtoUser.getRole());

        Assertions.assertEquals(entityUser.getId(), dtoUser.getId());
        Assertions.assertEquals(entityUser.getName(), dtoUser.getName());
        Assertions.assertEquals(entityUser.getEmail(), dtoUser.getEmail());
        Assertions.assertEquals(entityUser.getPassword(), dtoUser.getPassword());
        Assertions.assertEquals(entityUser.getRole(), dtoUser.getRole());
    }

    @Test
    void convertDtoToEntityTest(){
        UserDto dtoUser = new UserDto(1L, "test", "test@test.test", "test123", Role.ADMIN);

        User entityUser = userMapper.toEntity(dtoUser);

        Assertions.assertNotNull(entityUser);

        Assertions.assertNotNull(entityUser.getId());
        Assertions.assertNotNull(entityUser.getName());
        Assertions.assertNotNull(entityUser.getEmail());
        Assertions.assertNotNull(entityUser.getPassword());
        Assertions.assertNotNull(entityUser.getRole());

        Assertions.assertEquals(dtoUser.getId(), entityUser.getId());
        Assertions.assertEquals(dtoUser.getName(), entityUser.getName());
        Assertions.assertEquals(dtoUser.getEmail(), entityUser.getEmail());
        Assertions.assertEquals(dtoUser.getPassword(), entityUser.getPassword());
        Assertions.assertEquals(dtoUser.getRole(), entityUser.getRole());
    }
}