package com.example.bookMyShow.Transformers;

import com.example.bookMyShow.Models.Theater;
import com.example.bookMyShow.RequestDto.TheaterEntryDto;

public class TheaterTransformers {

    public static Theater convertDtoToEntity(TheaterEntryDto theaterEntryDto){

        Theater theater = Theater.builder().location(theaterEntryDto.getLocation())
                .name(theaterEntryDto.getName()).build();

        return theater;
    }

}
