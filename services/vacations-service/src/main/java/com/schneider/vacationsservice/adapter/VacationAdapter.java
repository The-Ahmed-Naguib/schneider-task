package com.schneider.vacationsservice.adapter;

import com.schneider.vacationsservice.dto.VacationDto;
import com.schneider.vacationsservice.entity.Vacation;
import com.schneider.vacationsservice.util.Util;

import java.util.List;
import java.util.stream.Collectors;

import static com.schneider.vacationsservice.util.Constants.DATE_FORMAT_DATE_ONLY;


public class VacationAdapter {

    public static VacationDto getVacationDtoFromVacationEntity(Vacation vacation) {
        return (vacation != null) ?
                new VacationDto(
                        vacation.getId(),
                        Util.getDateAsString( vacation.getVacationStartDate(),DATE_FORMAT_DATE_ONLY),
                        Util.getDateAsString( vacation.getVacationEndDate(),DATE_FORMAT_DATE_ONLY),
                        vacation.getNumberOfDays(),
                        vacation.getVacationTypeEntity()
                ) : null;
    }

    public static List<VacationDto> getVacationDtoFromVacationEntity(List<Vacation> vacations) {
        return (vacations != null) ? vacations.stream()
                .map(VacationAdapter::getVacationDtoFromVacationEntity)
                .collect(Collectors.toList()) : null;
    }


}
