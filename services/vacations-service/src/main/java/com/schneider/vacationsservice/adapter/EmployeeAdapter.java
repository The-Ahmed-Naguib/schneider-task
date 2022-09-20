package com.schneider.vacationsservice.adapter;

import com.schneider.vacationsservice.dto.EmployeeDto;
import com.schneider.vacationsservice.entity.Employee;

import java.util.List;
import java.util.stream.Collectors;


public class EmployeeAdapter {

    public static EmployeeDto getEmployeeDtoFromEmployeeEntity(Employee employee) {
        return (employee != null) ?
                new EmployeeDto(
                        employee.getName(),
                        employee.getAnnualVacationBalance(),
                        employee.getSickVacationBalance(),
                        VacationAdapter.getVacationDtoFromVacationEntity(employee.getVacations())
                ) : null;
    }

    public static List<EmployeeDto> getEmployeeDtoFromEmployeeEntity(List<Employee> employees) {
        return (employees != null) ? employees.stream()
                .map(EmployeeAdapter::getEmployeeDtoFromEmployeeEntity)
                .collect(Collectors.toList()) : null;
    }
}
