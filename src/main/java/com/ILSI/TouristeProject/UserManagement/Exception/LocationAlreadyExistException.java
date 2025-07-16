package com.ILSI.TouristeProject.UserManagement.Exception;

public class LocationAlreadyExistException extends RuntimeException{

    public LocationAlreadyExistException(String message){
        super(message);
    }
}
