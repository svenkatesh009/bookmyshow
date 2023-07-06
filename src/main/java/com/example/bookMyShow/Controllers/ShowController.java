package com.example.bookMyShow.Controllers;

import com.example.bookMyShow.RequestDto.AddShowDto;
import com.example.bookMyShow.RequestDto.ShowSeatsDto;
import com.example.bookMyShow.Services.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/show")
public class ShowController {

    @Autowired
    private ShowService showService;

    @PostMapping("/add")
    public String addShow(@RequestBody AddShowDto addShowDto){
        try{
            return showService.addShow(addShowDto);
        }catch (Exception e){
            return e.getMessage();
        }

    }

    @PostMapping("/associate-seats")
    public String associateSeats(@RequestBody ShowSeatsDto showSeatsDto){

        try{
            return showService.associateShowSeats(showSeatsDto);
        }catch (Exception e){
            return e.getMessage();
        }

    }

    @GetMapping("/most-recommended-movie-name")
    public String getMovieName(){

        return showService.getMovieName();
    }


}
