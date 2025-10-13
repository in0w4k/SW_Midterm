package com.example.midterm_sw.mapper;

import com.example.midterm_sw.dto.UserDto;
import com.example.midterm_sw.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toDto(User user);
    User toEntity(UserDto dto);
}
