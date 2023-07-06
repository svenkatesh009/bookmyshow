package com.example.bookMyShow.ResponseDto;

import lombok.Data;

import java.time.LocalTime;

@Data
public class MovieTimeDto {
    public String movieName;
    public LocalTime localTime;
}
