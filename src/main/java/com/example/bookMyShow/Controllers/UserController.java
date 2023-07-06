package com.example.bookMyShow.Controllers;

import com.example.bookMyShow.Models.User;
import com.example.bookMyShow.RequestDto.AddUserDto;
import com.example.bookMyShow.ResponseDto.UserResponseDto;
import com.example.bookMyShow.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/add")
    public String addUser(@RequestBody AddUserDto user){

        try{
            String result = userService.addUser(user);
            return result;
        }catch (Exception e){
            return e.getMessage();
        }
    }

    //Get oldest User Object by age
    @GetMapping("/getOlderUser")
    public UserResponseDto getOldestUser(){

        try{
            UserResponseDto userResponseDto = userService.getOldestUser();

            userResponseDto.setStatusCode("200");
            userResponseDto.setStatusMessage("SUCCESS");
            return userResponseDto;

        }catch (Exception e){
            UserResponseDto responseDto = new UserResponseDto();
            responseDto.setStatusCode("500");
            responseDto.setStatusMessage("Failure");
            return responseDto;
        }
    }

    @GetMapping("/findUsersGreaterThanAAge")
    public List<User> getAllUsers(@RequestParam("age")Integer age){
        return userService.getAllUserGreaterThan(age);
    }

    @DeleteMapping("/cancel ticket")
    public String  cancelTicket(@RequestParam int ticketId){
        userService.cancelTicket(ticketId);
        return "Ticket has been cancelled";
    }

}
