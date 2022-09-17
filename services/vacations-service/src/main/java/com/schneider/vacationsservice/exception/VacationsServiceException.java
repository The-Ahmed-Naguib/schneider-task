package com.schneider.vacationsservice.exception;

public class VacationsServiceException extends RuntimeException{
    String errCode;
    String errDescription;

    public VacationsServiceException(String errCode, String errDescription) {
        super(errDescription);
        this.errCode = errCode;
        this.errDescription = errDescription;
    }
}
