package com.example.UserAuthenticationService.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CREATED, reason = "Specified user already exists.")
public class UserAlreadyExitsException extends Exception{
}
