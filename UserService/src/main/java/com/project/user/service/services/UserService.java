package com.project.user.service.services;

import com.project.user.service.entities.User;

import java.util.List;

public interface UserService {

    //create user

    User saveUser(User user);

    //get all users

    List<User> getAllUser();

    // get user by id
    User getUser(String userId);


}
