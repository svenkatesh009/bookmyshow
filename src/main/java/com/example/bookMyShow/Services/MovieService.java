package com.example.bookMyShow.Services;

import com.example.bookMyShow.Models.Movie;
import com.example.bookMyShow.Repository.MovieRepository;
import com.example.bookMyShow.RequestDto.MovieEntryDto;
import com.example.bookMyShow.Transformers.MovieTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieService {

    @Autowired
    MovieRepository movieRepository;

    public String addMovie(MovieEntryDto movieEntryDto){

        Movie movie = MovieTransformer.convertDtoToEntity(movieEntryDto);

        movieRepository.save(movie);

        return "Movie added successfully";
    }

}