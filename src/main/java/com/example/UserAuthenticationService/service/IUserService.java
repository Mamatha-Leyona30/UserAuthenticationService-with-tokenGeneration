package com.example.UserAuthenticationService.service;

import com.example.UserAuthenticationService.domain.User;
import com.example.UserAuthenticationService.exceptions.InvalidUsernameAndPasswordException;
import com.example.UserAuthenticationService.exceptions.UserAlreadyExitsException;
import com.example.UserAuthenticationService.exceptions.UserNotFoundException;

public interface IUserService {

    User saveUser(User user) throws UserAlreadyExitsException;
    User findUserByUsernameAndPassword(String username, String password) throws InvalidUsernameAndPasswordException;
    String getUserProfilePicture(String username) throws UserNotFoundException;
}
