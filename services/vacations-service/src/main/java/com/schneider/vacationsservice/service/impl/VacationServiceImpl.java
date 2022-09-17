package com.schneider.vacationsservice.service.impl;

import com.schneider.vacationsservice.adapter.VacationAdapter;
import com.schneider.vacationsservice.dto.VacationDto;
import com.schneider.vacationsservice.dto.VacationSubmitDto;
import com.schneider.vacationsservice.entity.Employee;
import com.schneider.vacationsservice.entity.Vacation;
import com.schneider.vacationsservice.entity.VacationType;
import com.schneider.vacationsservice.exception.VacationsServiceException;
import com.schneider.vacationsservice.reposatory.EmployeeRepository;
import com.schneider.vacationsservice.reposatory.VacationRepository;
import com.schneider.vacationsservice.reposatory.VacationTypeRepository;
import com.schneider.vacationsservice.reposatory.WeekendsRepository;
import com.schneider.vacationsservice.service.VacationService;
import com.schneider.vacationsservice.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.schneider.vacationsservice.util.Constants.DATE_FORMAT_DATE_ONLY;
import static com.schneider.vacationsservice.util.Util.getDateFromString;

@Service
public class VacationServiceImpl implements VacationService {

    EmployeeRepository employeeRepository;
    VacationRepository vacationRepository;
    VacationTypeRepository vacationTypeRepository;
    WeekendsRepository weekendsRepository;

    @Autowired
    public VacationServiceImpl(EmployeeRepository employeeRepository,
                               VacationRepository vacationRepository,
                               VacationTypeRepository vacationTypeRepository,
                               WeekendsRepository weekendsRepository) {
        this.employeeRepository = employeeRepository;
        this.vacationRepository = vacationRepository;
        this.vacationTypeRepository = vacationTypeRepository;
        this.weekendsRepository = weekendsRepository;
    }

    @Override
    public List<VacationDto> getAllVacationsByEmployeeId(long employeeId) {
        List<Vacation> vacations = vacationRepository.findByEmployee_Id(employeeId);
        return VacationAdapter.getVacationDtoFromVacationEntity(vacations);
    }

    @Override
    public VacationDto addVacation(VacationSubmitDto submitDto) {

        VacationType vacationType = vacationTypeRepository.findById(submitDto.getVacationTypeId()).orElseThrow();
        Employee employee = employeeRepository.findById(SecurityUtil.getCurrentUserIdFromSession()).orElseThrow();
        Date vacationStartDate = getDateFromString(submitDto.getVacationStartDate(), DATE_FORMAT_DATE_ONLY);
        Date vacationEndDate = getDateFromString(submitDto.getVacationEndDate(), DATE_FORMAT_DATE_ONLY);
        validateStartAndDate(vacationStartDate,vacationEndDate);
        Vacation vacation = new Vacation();
        vacation.setVacationType(vacationType);
        vacation.setEmployee(employee);
        vacation.setVacationStartDate(vacationStartDate);
        vacation.setVacationEndDate(vacationEndDate);
        vacation.setNumberOfDays(calculateVacationDuration(submitDto.getVacationStartDate(), submitDto.getVacationEndDate()));
        return  VacationAdapter.getVacationDtoFromVacationEntity(vacationRepository.save(vacation));
    }

    @Override
    public Integer calculateVacationDuration(String startDate, String endDate) {
        Date fromDate = getDateFromString(startDate, DATE_FORMAT_DATE_ONLY);
        Date toDate = getDateFromString(endDate, DATE_FORMAT_DATE_ONLY);
        validateStartAndDate(fromDate,toDate);
        long diff = toDate.getTime() - fromDate.getTime();
        int diffInDays = (int) TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
        int numberOfOffDaysInThisPeriod = weekendsRepository.countByDateGreaterThanEqualAndDateLessThanEqual(fromDate, toDate);
        return diffInDays - numberOfOffDaysInThisPeriod + 1;
    }

    public void validateStartAndDate(Date startDate, Date endDate){
        if (startDate.after(endDate))
            throw new VacationsServiceException("410","Start-date is after end-date");
    }
}