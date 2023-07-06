package com.example.bookMyShow.Services;

import com.example.bookMyShow.Exception.NoUserFoundException;
import com.example.bookMyShow.Models.Show;
import com.example.bookMyShow.Models.ShowSeat;
import com.example.bookMyShow.Models.Ticket;
import com.example.bookMyShow.Models.User;
import com.example.bookMyShow.Repository.ShowRepository;
import com.example.bookMyShow.Repository.TicketRepository;
import com.example.bookMyShow.Repository.UserRepository;
import com.example.bookMyShow.RequestDto.AddUserDto;
import com.example.bookMyShow.ResponseDto.UserResponseDto;
import com.example.bookMyShow.Transformers.UserTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    TicketRepository ticketRepository;

    @Autowired
    ShowRepository showRepository;
    public String addUser(AddUserDto userDto){

        User user = UserTransformer.convertDtoToEntity(userDto);
        userRepository.save(user);

        return "User has been added successfully ";
    }

    public UserResponseDto getOldestUser()throws NoUserFoundException {
        //Prevent you from exposing the PK
        //Prevents Infinite recursion incase it occurs
        List<User> users = userRepository.findAll();
        Integer maxAge = 0;

        User userAns = null;

        for(User user: users)
        {
            if(user.getAge()>maxAge){
                maxAge = user.getAge();
                userAns = user;
            }
        }

        if(userAns==null){
            throw new NoUserFoundException("No user Found");
        }

        //We need to transform the UserEntity to the userResponse
        UserResponseDto userResponseDto = UserTransformer.convertEntityToDto(userAns);

        return userResponseDto;
    }

    public List<User> getAllUserGreaterThan(Integer age){

        List<User> users = userRepository.findUserWithAgeGreater(age);
        return users;

    }

    public void cancelTicket(int ticketId) {
        Optional<Ticket> ticket =ticketRepository.findById(ticketId);
        String bookedSeats=ticket.get().getBookedSeats();
        String [] seats=bookedSeats.split(", ");
        ArrayList<String> seatsList = new ArrayList<String>(
                Arrays.asList(seats));
        Show show=ticket.get().getShow();
        List<ShowSeat> showSeatList = show.getShowSeatList();

        for(ShowSeat showSeat : showSeatList){

            if(seatsList.contains(showSeat.getSeatNo())){
                showSeat.setAvailable(true);
            }
        }
        User user=ticket.get().getUser();
        user.getTicketList().remove(ticket.get());
        userRepository.save(user);
        showRepository.save(show);
    }
}