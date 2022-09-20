package com.schneider.vacationsservice.dto;

import lombok.*;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EmployeeDto implements Serializable {
    private String name;
    private int annualVacationBalance;
    private int sickVacationBalance;
    private List<VacationDto> vacations;
}
