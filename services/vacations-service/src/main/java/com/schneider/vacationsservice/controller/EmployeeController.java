package com.schneider.vacationsservice.controller;

import com.schneider.vacationsservice.dto.EmployeeDto;
import com.schneider.vacationsservice.dto.Response;
import com.schneider.vacationsservice.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.schneider.vacationsservice.util.SecurityUtil.getCurrentUserIdFromSession;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    ResponseEntity<Response<EmployeeDto>> get() {
        return ResponseEntity.ok(new Response(
                employeeService.getEmployeeId(getCurrentUserIdFromSession())));
    }



}
