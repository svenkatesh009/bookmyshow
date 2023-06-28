package com.example.bookMyShow.Transformers;

import com.example.bookMyShow.Models.Show;
import com.example.bookMyShow.RequestDto.AddShowDto;

public class ShowTransformer {

    public static Show convertDtoToEntity(AddShowDto addShowDto){

        Show show = Show.builder().time(addShowDto.getShowStartTime())
                .date(addShowDto.getShowDate()).build();

        return show;

    }
}
