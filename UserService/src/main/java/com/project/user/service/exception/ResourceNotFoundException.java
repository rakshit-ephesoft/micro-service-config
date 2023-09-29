package com.project.user.service.exception;

public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(){
        super("Resource not Found on server");
    }

    public ResourceNotFoundException(String message){
        super(message);
    }
}
