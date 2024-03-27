package com.backend.scene.mapper;

import com.backend.scene.dto.UserDto;
import com.backend.scene.user.User;

public class userMapper {

    public static UserDto mapToDto(User user){
        UserDto userDto = new UserDto(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getPassword(),
                user.getRole(),
                user.getDOB(),
                user.getOutstandingAmount(),
                user.getMale()

        );
        return userDto;
    }

    public static User mapToUser(UserDto userDto){
        User user = new User(
                userDto.getId(),
                userDto.getFirstName(),
                userDto.getLastName(),
                userDto.getPassword(),
                userDto.getEmail(),
                userDto.getRole(),
                userDto.getDOB(),
                userDto.getOutstandingAmount(),
                userDto.getMale()


        );
        return user;
    }
}
