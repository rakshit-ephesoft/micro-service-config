package com.project.user.service.services.Impl;

import com.project.user.service.entities.Hotel;
import com.project.user.service.entities.Rating;
import com.project.user.service.entities.User;
import com.project.user.service.exception.ResourceNotFoundException;
import com.project.user.service.repositories.UserRepository;
import com.project.user.service.services.UserService;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;


    @Autowired
    private RestTemplate restTemplate;

    //private Logger logger = (Logger) LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public User saveUser(User user) {
        String randomUserId = UUID.randomUUID().toString();
        user.setUserId(randomUserId);
        return userRepository.save(user);

    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public User getUser(String userId) {
        // get user from db with the help of repo
        User user= userRepository.findById(userId).orElseThrow(() ->new  ResourceNotFoundException("User with given id is not available"));
        //fetch rating of the above user from rating service
        //http://localhost:8083/ratings/users/3db036fd-fe36-4580-bf1e-9ebad6c78ef3
        Rating[] ratingsOfUser=restTemplate.getForObject("http://RATING-SERVICE/ratings/users/"+user.getUserId(), Rating[].class);
        // we have to set ratings for hotel
        List<Rating> ratings=Arrays.stream(ratingsOfUser).collect(Collectors.toList());
        List<Rating> ratingList=ratings.stream().map(rating -> {
            //api call to hotel service to get the hotel
            //http://localhost:8082/hotels/b2e1d210-786b-4bc7-9001-2830516ea0e8
            ResponseEntity<Hotel> forEntity =restTemplate.getForEntity("http://HOTEL-SERVICE/hotels/"+ rating.getHotelId(), Hotel.class);
            Hotel hotel=forEntity.getBody();
            //set the hotel to rating
            // return the rating
            rating.setHotel(hotel);
            return rating;
        }).collect(Collectors.toList());
        user.setRatings(ratingList);
        return user;
    }
}
