package com.schneider.vacationsservice.dto;

import lombok.*;

import java.io.Serializable;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class VacationSubmitDto implements Serializable {
    private String vacationStartDate;
    private String vacationEndDate;
    private long vacationTypeId;
}