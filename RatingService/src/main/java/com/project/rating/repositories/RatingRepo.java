package com.project.rating.repositories;

import com.project.rating.entities.Rating;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface RatingRepo extends MongoRepository<Rating,String> {

//
    List<Rating> findByUserId(String userId);
    List<Rating> findByHotelId(String hotelId);
}
