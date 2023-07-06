package com.example.bookMyShow.Controllers;

import com.example.bookMyShow.RequestDto.MovieEntryDto;
import com.example.bookMyShow.Services.MovieService;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/movie")
public class MovieController {

    @Autowired
    MovieService movieService;

    @PostMapping("/add")
    public String addMovie(@RequestBody MovieEntryDto movieEntryDto){
        return movieService.addMovie(movieEntryDto);
    }


    @GetMapping("/totalCollection")
    public int getTotalCollection(@RequestParam int movieId){
        return movieService.getTotalCollection(movieId);

    }

    @GetMapping("/maximumShows")
    public String getMaximumShowMovie(){
        return  movieService.getMovieWithMaximumShow();
    }

}
