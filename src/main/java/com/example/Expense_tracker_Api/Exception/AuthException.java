package com.example.Expense_tracker_Api.Exception;


import org.aspectj.bridge.IMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED,reason = "Invalied email id")
public class AuthException extends RuntimeException{

    public AuthException(String message)
    {
        super(message);
    }
}
