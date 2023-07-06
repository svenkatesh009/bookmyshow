package com.example.bookMyShow.Services;

import com.example.bookMyShow.Enums.SeatType;
import com.example.bookMyShow.Models.Show;
import com.example.bookMyShow.Models.Theater;
import com.example.bookMyShow.Models.TheaterSeat;
import com.example.bookMyShow.Repository.TheaterRepository;
import com.example.bookMyShow.RequestDto.TheaterEntryDto;
import com.example.bookMyShow.RequestDto.TheaterSeatsEntryDto;
import com.example.bookMyShow.ResponseDto.MovieTimeDto;
import com.example.bookMyShow.Transformers.TheaterTransformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TheaterServices {

    @Autowired
    private TheaterRepository theaterRepository;

    public String addTheater(TheaterEntryDto theaterEntryDto){

        //Entity that saves into the db
        //Convert the entryDto --> Entity and then save it
        Theater theater = TheaterTransformers.convertDtoToEntity(theaterEntryDto);
        theaterRepository.save(theater);

        return "Theater Added succesfully";
    }

    public String addTheaterSeats(TheaterSeatsEntryDto theaterSeatsEntryDto){

        //We need to save the TheaterSeat Entity

        int columns = theaterSeatsEntryDto.getNoOfSeatsIn1Row();

        int noOfClassicSeats = theaterSeatsEntryDto.getNofOfClassicSeats();
        int noOfPremiumSeats = theaterSeatsEntryDto.getNoOfPremiumSeats();

        String location = theaterSeatsEntryDto.getLocation();

        Theater theater = theaterRepository.findByLocation(location);

        List<TheaterSeat> theaterSeatList = theater.getTheaterSeatList();

        System.out.println("The value of noOfPremium Seats"+noOfPremiumSeats);


        int counter = 1;
        char ch = 'A';

        //this is done for the classic seats
        for(int count = 1;count<=noOfClassicSeats;count++){

            String seatNo = counter+"";
            seatNo = seatNo + ch;

            ch++;

            if((ch-'A')==columns){
                ch = 'A';
                counter++;
            }


            TheaterSeat theaterSeat = new TheaterSeat();
            theaterSeat.setSeatNo(seatNo);
            theaterSeat.setTheater(theater); //storing parent info in child
            theaterSeat.setSeatType(SeatType.CLASSIC);

            //This is the bidirectional mapping...storing the child entity
            //in the parent entity
            theaterSeatList.add(theaterSeat);
        }

        //Lets to the same for the premium seats
        for(int count=1;count<=noOfPremiumSeats;count++){

            String seatNo = counter+"";
            seatNo = seatNo + ch;
            ch++;
            if((ch-'A')==columns){
                ch = 'A';
                counter++;
            }

            TheaterSeat theaterSeat = new TheaterSeat();
            theaterSeat.setTheater(theater);
            theaterSeat.setSeatType(SeatType.PREMIUM);
            theaterSeat.setSeatNo(seatNo);

            //This is the bidirectional mapping...storing the child entity
            //in the parent entity

            System.out.println("The seatNo is "+seatNo);

            theaterSeatList.add(theaterSeat);
        }
        //We just need to save the parent : theater Entity
        //child will automatically get saved bcz of bidirectional mapping
        theaterRepository.save(theater);

        return "Theater Seats have been successfully added";
    }

    public List<MovieTimeDto> getMovieTiming(int theaterId) {
        Optional<Theater> theater=theaterRepository.findById(theaterId);
        List<Show> showList=theater.get().getShowList();
        List<MovieTimeDto> movieTimeDtoList=new ArrayList<>();
        for (Show show:showList){
            MovieTimeDto movieTimeDto=new MovieTimeDto();
            movieTimeDto.setMovieName(show.getMovie().getMovieName());
            movieTimeDto.setLocalTime(show.getTime());
        }
        return movieTimeDtoList;
    }
}
