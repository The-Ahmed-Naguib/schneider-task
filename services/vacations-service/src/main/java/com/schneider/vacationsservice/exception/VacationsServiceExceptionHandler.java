package com.schneider.vacationsservice.exception;

import com.schneider.vacationsservice.dto.Response;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class VacationsServiceExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(VacationsServiceException.class)
    protected ResponseEntity handleGovtPaymentServiceException(VacationsServiceException ex) {
        Response response = new Response(ex.errDescription, ex.errCode);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
}
