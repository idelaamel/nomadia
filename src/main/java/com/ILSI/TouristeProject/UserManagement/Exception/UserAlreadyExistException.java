package com.ILSI.TouristeProject.UserManagement.Exception;

public class UserAlreadyExistException extends RuntimeException{

    public UserAlreadyExistException(String message){
        super(message);
    }
}
