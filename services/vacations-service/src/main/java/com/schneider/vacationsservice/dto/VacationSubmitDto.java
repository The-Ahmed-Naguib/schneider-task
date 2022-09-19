package com.schneider.vacationsservice.dto;

import com.schneider.vacationsservice.enums.VacationType;
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
    private VacationType vacationType;
}