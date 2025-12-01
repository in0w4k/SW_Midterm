package com.example.midterm_sw.service;

import com.example.midterm_sw.dto.UserDto;
import com.example.midterm_sw.model.Role;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;

@SpringBootTest
@Transactional
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    void getAllUsersTest() {
        List<UserDto> userDtos = userService.getAllUsers();

        Assertions.assertNotNull(userDtos);
        Assertions.assertNotEquals(0, userDtos.size());

        userDtos.forEach(userDto -> {
            Assertions.assertNotNull(userDto.getId());
            Assertions.assertNotNull(userDto.getName());
            Assertions.assertNotNull(userDto.getEmail());
            Assertions.assertNotNull(userDto.getPassword());
            Assertions.assertNotNull(userDto.getRole());
        });
    }

    @Test
    void getUserByIdTest() {
        Random random = new Random();

        int randomIndex = random.nextInt(userService.getAllUsers().size());
        Long someIndex = userService.getAllUsers().get(randomIndex).getId();
        UserDto userDto = userService.getUserById(someIndex);

        Assertions.assertNotNull(userDto);

        Assertions.assertNotNull(userDto.getId());
        Assertions.assertNotNull(userDto.getName());
        Assertions.assertNotNull(userDto.getEmail());
        Assertions.assertNotNull(userDto.getPassword());
        Assertions.assertNotNull(userDto.getRole());

        UserDto dto = userService.getUserById(-1L);
        Assertions.assertNull(dto);
    }

    @Test
    void addUserTest() {
        UserDto user = new UserDto();
        user.setName("test");
        user.setEmail("test@c.c");
        user.setPassword("pass");
        user.setRole(Role.ADMIN);

        UserDto createdUser = userService.addUser(user);

        Assertions.assertNotNull(createdUser);

        Assertions.assertNotNull(createdUser.getId());
        Assertions.assertEquals(user.getName(), createdUser.getName());
        Assertions.assertEquals(user.getEmail(), createdUser.getEmail());
        Assertions.assertEquals(user.getRole(), createdUser.getRole());
    }

    @Test
    void updateUserTest() {
        List<UserDto> users = userService.getAllUsers();
        Random random = new Random();
        UserDto existing = users.get(random.nextInt(users.size()));

        UserDto update = new UserDto();
        update.setName("updated");
        update.setEmail("updated@c.c");
        update.setPassword("newpass");
        update.setRole(Role.ADMIN);

        userService.updateUser(existing.getId(), update);

        UserDto updated = userService.getUserById(existing.getId());

        Assertions.assertEquals(update.getName(), updated.getName());
        Assertions.assertEquals(update.getEmail(), updated.getEmail());
        Assertions.assertEquals(update.getRole(), updated.getRole());
    }

    @Test
    void deleteUserTest() {
        List<UserDto> users = userService.getAllUsers();
        Random random = new Random();
        Long randomId = users.get(random.nextInt(users.size())).getId();

        boolean deleted = userService.deleteUser(randomId);
        Assertions.assertTrue(deleted);

        UserDto afterDelete = userService.getUserById(randomId);
        Assertions.assertNull(afterDelete);
    }
}