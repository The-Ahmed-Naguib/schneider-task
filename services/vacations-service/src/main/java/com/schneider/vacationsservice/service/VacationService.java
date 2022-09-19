package com.schneider.vacationsservice.service;

import com.schneider.vacationsservice.dto.VacationDto;
import com.schneider.vacationsservice.dto.VacationSubmitDto;

import java.util.List;

public interface VacationService {

    List<VacationDto> getAllVacationsByEmployeeId(long employeeId);
    VacationDto addVacation(VacationSubmitDto dto);
    int calculateVacationDuration(String startDate, String endDate);
}