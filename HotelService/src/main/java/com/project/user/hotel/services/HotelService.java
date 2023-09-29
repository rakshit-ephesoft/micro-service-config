package com.project.user.hotel.services;

import com.project.user.hotel.entities.Hotel;
import org.springframework.stereotype.Service;

import java.util.List;


public interface HotelService {
    //create hotel

    Hotel create(Hotel hotel);
// getAll
    List<Hotel> getAll();
    // get single Hotel

    Hotel get(String id);

}
