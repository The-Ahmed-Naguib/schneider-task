package com.schneider.vacationsservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(Include.NON_NULL)
public class Response<T> {

    private T data;
    private String errorMessage;
    private String errorCode;


    public Response(T data) {
        this.data = data;
    }


    public Response(String errorMessage, String errorCode) {
        this.errorMessage = errorMessage;
        this.errorCode = errorCode;
    }




}