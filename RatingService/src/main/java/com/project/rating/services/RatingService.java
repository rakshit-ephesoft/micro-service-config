package com.project.rating.services;

import com.project.rating.entities.Rating;

import java.util.List;

public interface RatingService {

    //create rating

    Rating createRating(Rating rating);
    //getall

    List<Rating> getRatings();

    //getAll rating by userId
    List<Rating> getRatingByUserId(String userId);

    //getAll rating by hotelId
    List<Rating> getRatingByHotelId(String hotelId);
}
