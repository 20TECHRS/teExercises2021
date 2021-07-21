package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.UserDAO;
import com.techelevator.tenmo.model.User;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    UserDAO userDAO;

    public UserController(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @RequestMapping(path = "/users", method = RequestMethod.GET)
    public List<User> getUsers() {
        LogAPIRequest.logAPICall("GET - /users");
        return userDAO.findAll();
    }

    @RequestMapping(path = "/users/{id}", method = RequestMethod.GET)
    public String getUsernameFromUserId(@PathVariable Integer id) {
        LogAPIRequest.logAPICall("GET - /users/" + id);
        return userDAO.findUserNameById(id);
    }
}
