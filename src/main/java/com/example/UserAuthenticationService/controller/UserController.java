package com.example.UserAuthenticationService.controller;

import com.example.UserAuthenticationService.domain.User;
import com.example.UserAuthenticationService.exceptions.InvalidUsernameAndPasswordException;
import com.example.UserAuthenticationService.exceptions.UserAlreadyExitsException;
import com.example.UserAuthenticationService.exceptions.UserNotFoundException;
import com.example.UserAuthenticationService.security.SecurityTokenGeneratorI;
import com.example.UserAuthenticationService.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1")
public class UserController {

  IUserService iUserService;
  SecurityTokenGeneratorI securityTokenGeneratorI;

    @Autowired
    public UserController(IUserService iUserService, SecurityTokenGeneratorI securityTokenGeneratorI) {
        this.iUserService = iUserService;
        this.securityTokenGeneratorI = securityTokenGeneratorI;
    }



  @PostMapping("/saveUser")
    public ResponseEntity<?> registerUser(@RequestBody User user) throws UserAlreadyExitsException {
      try
      {
          User registeredUser=iUserService.saveUser(user);
          return new ResponseEntity<>(registeredUser, HttpStatus.CREATED);
      } catch (UserAlreadyExitsException e) {
          throw new UserAlreadyExitsException();
      }
      catch (Exception e)
      {
      return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
      }
  }

    @PostMapping("/loginUser")
    public ResponseEntity<?> loginUser(@RequestBody User user) throws InvalidUsernameAndPasswordException {
        try {
            User foundUser = iUserService.findUserByUsernameAndPassword(user.getUsername(), user.getPassword());

            if (foundUser == null) {
                throw new InvalidUsernameAndPasswordException();
            }
            String token = securityTokenGeneratorI.createToken(foundUser);
            return new ResponseEntity<>(token, HttpStatus.OK);

        } catch (InvalidUsernameAndPasswordException e) {
           throw new InvalidUsernameAndPasswordException();

        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("userProfile/{username}")
    public ResponseEntity<?> getUserProfile(@PathVariable String username) throws UserNotFoundException {
        return new ResponseEntity<>(iUserService.getUserProfilePicture(username),HttpStatus.OK);
    }

}
