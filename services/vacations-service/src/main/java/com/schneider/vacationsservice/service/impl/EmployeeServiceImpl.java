package com.schneider.vacationsservice.service.impl;

import com.schneider.vacationsservice.adapter.EmployeeAdapter;
import com.schneider.vacationsservice.dto.EmployeeDto;
import com.schneider.vacationsservice.entity.Employee;
import com.schneider.vacationsservice.reposatory.EmployeeRepository;
import com.schneider.vacationsservice.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    EmployeeRepository employeeRepository;


    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;

    }

    @Override
    public EmployeeDto getEmployeeId(long employeeId) {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow();
        return EmployeeAdapter.getEmployeeDtoFromEmployeeEntity(employee);
    }
}
