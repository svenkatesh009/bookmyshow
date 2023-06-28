package com.example.bookMyShow.Transformers;


import com.example.bookMyShow.Models.Movie;
import com.example.bookMyShow.RequestDto.MovieEntryDto;

public class MovieTransformer {

    public static Movie convertDtoToEntity(MovieEntryDto movieEntryDto){

        Movie movie = Movie.builder().movieName(movieEntryDto.getMovieName())
                .duration(movieEntryDto.getDuration())
                .genre(movieEntryDto.getGenre())
                .language(movieEntryDto.getLanguage())
                .rating(movieEntryDto.getRating()).releaseDate(movieEntryDto.getReleaseDate())
                .build();

        return movie;
    }
}