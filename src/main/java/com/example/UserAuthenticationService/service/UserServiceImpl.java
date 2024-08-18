package com.example.UserAuthenticationService.service;

import com.example.UserAuthenticationService.domain.User;
import com.example.UserAuthenticationService.exceptions.InvalidUsernameAndPasswordException;
import com.example.UserAuthenticationService.exceptions.UserAlreadyExitsException;
import com.example.UserAuthenticationService.exceptions.UserNotFoundException;
import com.example.UserAuthenticationService.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService{
    IUserRepository iUserRepository;


    @Autowired
    public UserServiceImpl(IUserRepository iUserRepository) {

        this.iUserRepository = iUserRepository;
    }

    @Override
    public User saveUser(User user) throws UserAlreadyExitsException {
        if(iUserRepository.findByUsername(user.getUsername()).isPresent())
        {
            throw new UserAlreadyExitsException();
        }
            return iUserRepository.save(user);

    }

    @Override
    public User findUserByUsernameAndPassword(String username, String password) throws InvalidUsernameAndPasswordException {
    User user=iUserRepository.findByUsernameAndPassword(username,password);
    if(user==null)
    {
    throw new InvalidUsernameAndPasswordException();
    }
     return user;
    }

    @Override
    public String getUserProfilePicture(String username) throws UserNotFoundException {
    if(iUserRepository.findByUsername(username).isEmpty())
        {
        throw new  UserNotFoundException();
        }
       User user=iUserRepository.findByUsername(username).get();

        return user.getImageUrl();
    }


}
