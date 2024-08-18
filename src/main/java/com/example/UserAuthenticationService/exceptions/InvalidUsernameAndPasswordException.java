package com.example.UserAuthenticationService.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT,reason="Invalid Username or Password")
public class InvalidUsernameAndPasswordException extends Exception{
}
