package com.example.bookMyShow.Transformers;

import com.example.bookMyShow.Models.User;
import com.example.bookMyShow.RequestDto.AddUserDto;
import com.example.bookMyShow.ResponseDto.UserResponseDto;

public class UserTransformer {

    public static User convertDtoToEntity(AddUserDto userDto){

        User userObj = User.builder().age(userDto.getAge()).email(userDto.getEmailId()).mobNo(userDto.getMobNo())
                .name(userDto.getName()).build();
        return userObj;
    }

    public static UserResponseDto convertEntityToDto(User user){

        UserResponseDto userResponseDto = UserResponseDto.builder().age(user.getAge()).name(user.getName())
                .mobNo(user.getMobNo()).build();
        return userResponseDto;
    }


}