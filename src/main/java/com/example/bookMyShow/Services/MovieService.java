package com.example.bookMyShow.Services;

import com.example.bookMyShow.Models.Movie;
import com.example.bookMyShow.Models.Show;
import com.example.bookMyShow.Models.Ticket;
import com.example.bookMyShow.Repository.MovieRepository;
import com.example.bookMyShow.RequestDto.MovieEntryDto;
import com.example.bookMyShow.Transformers.MovieTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    @Autowired
    MovieRepository movieRepository;

    public String addMovie(MovieEntryDto movieEntryDto){

        Movie movie = MovieTransformer.convertDtoToEntity(movieEntryDto);

        movieRepository.save(movie);

        return "Movie added successfully";
    }

    public int getTotalCollection(int movieId) {
        Optional<Movie> movie =movieRepository.findById(movieId);
        List<Show> showList=movie.get().getShowList();
        int total=0;
        for(Show show:showList){
            List<Ticket> ticketList=show.getTicketList();
            for (Ticket ticket:ticketList){
                total+=ticket.getTotalTicketsPrice();
            }
        }
        return total;
    }

    public String getMovieWithMaximumShow() {
        List<Movie> movieList=movieRepository.findAll();
        int max=0;
        String movieName="";
        for(Movie movie:movieList){
            int showsize=movie.getShowList().size();
            if(showsize>max){
                max=showsize;
                movieName=movie.getMovieName();
            }
        }
        return movieName;
    }
}