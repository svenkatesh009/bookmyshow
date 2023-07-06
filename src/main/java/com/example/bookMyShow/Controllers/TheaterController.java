package com.example.bookMyShow.Controllers;

import com.example.bookMyShow.RequestDto.TheaterEntryDto;
import com.example.bookMyShow.RequestDto.TheaterSeatsEntryDto;
import com.example.bookMyShow.ResponseDto.MovieTimeDto;
import com.example.bookMyShow.Services.TheaterServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/theater")
public class TheaterController {

    @Autowired
    TheaterServices theaterServices;

    @PostMapping("/add")
    public String addTheater(@RequestBody TheaterEntryDto theaterEntryDto){

        return theaterServices.addTheater(theaterEntryDto);
    }

    @PostMapping("/addTheaterSeats")
    public String addTheaterSeats(@RequestBody TheaterSeatsEntryDto entryDto){

        return theaterServices.addTheaterSeats(entryDto);

    }

    @GetMapping("/movie_time")
    public List<MovieTimeDto> getMovieTiming(@RequestParam int theaterId){
        return theaterServices.getMovieTiming(theaterId);
    }

}
