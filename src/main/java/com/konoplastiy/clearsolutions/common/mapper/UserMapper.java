package com.konoplastiy.clearsolutions.common.mapper;

import com.konoplastiy.clearsolutions.entity.User;
import com.konoplastiy.clearsolutions.payload.user.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

/**
 * Mapper for converting user to user dto and vice versa.
 */
@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toDto(User user);

    List<UserDto> toListDto(List<User> users);

    void updateUser(@MappingTarget User user, UserDto userDto);
}
