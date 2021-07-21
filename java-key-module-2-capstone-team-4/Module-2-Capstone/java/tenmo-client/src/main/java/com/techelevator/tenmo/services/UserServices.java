package com.techelevator.tenmo.services;

import com.techelevator.tenmo.models.User;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/*******************************************************************************************************
 * This is where you code Application Services required by your solution
 * 
 * Remember:  theApp ==> ApplicationServices  ==>  Controller  ==>  DAO
********************************************************************************************************/

public class UserServices {
    private final RestTemplate restTemplate = new RestTemplate();
    private final String BASE_URL;

    public UserServices(String url) {
        BASE_URL = url;
    }

    public User[] getUsers() {
        User[] users = restTemplate.getForObject(BASE_URL + "/users", User[].class);
        return users;
    }

    public String getUsernameFromUserId(Integer userId) {
        return restTemplate.getForObject(BASE_URL + "/users/" + userId, String.class);
    }




}
