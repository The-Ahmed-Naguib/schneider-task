package com.schneider.vacationsservice.dto;

import com.schneider.vacationsservice.entity.VacationTypeEntity;
import lombok.*;

import java.io.Serializable;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class VacationDto implements Serializable {
    private Long id;
    private String vacationStartDate;
    private String vacationEndDate;
    private int numberOfDays;
    private VacationTypeEntity vacationTypeEntity;
}