package com.example.midterm_sw.mapper;

import com.example.midterm_sw.dto.UserDto;
import com.example.midterm_sw.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toDto(User user);
    @Mapping(target = "orders", ignore = true)
    @Mapping(target = "authorities", ignore = true)
    User toEntity(UserDto dto);
}
