package com.schneider.vacationsservice.service.impl;

import com.schneider.vacationsservice.adapter.VacationAdapter;
import com.schneider.vacationsservice.dto.VacationDto;
import com.schneider.vacationsservice.dto.VacationSubmitDto;
import com.schneider.vacationsservice.entity.Employee;
import com.schneider.vacationsservice.entity.Vacation;
import com.schneider.vacationsservice.entity.VacationTypeEntity;
import com.schneider.vacationsservice.exception.VacationsServiceException;
import com.schneider.vacationsservice.reposatory.EmployeeRepository;
import com.schneider.vacationsservice.reposatory.VacationRepository;
import com.schneider.vacationsservice.reposatory.VacationTypeRepository;
import com.schneider.vacationsservice.reposatory.WeekendsRepository;
import com.schneider.vacationsservice.service.VacationService;
import com.schneider.vacationsservice.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.schneider.vacationsservice.enums.VacationType.ANNUAL;
import static com.schneider.vacationsservice.enums.VacationType.SICK;
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
        VacationTypeEntity vacationTypeEntity = vacationTypeRepository.findById(submitDto.getVacationType()).orElseThrow();
        Employee employee = employeeRepository.findById(SecurityUtil.getCurrentUserIdFromSession()).orElseThrow();
        Date vacationStartDate = getDateFromString(submitDto.getVacationStartDate(), DATE_FORMAT_DATE_ONLY);
        Date vacationEndDate = getDateFromString(submitDto.getVacationEndDate(), DATE_FORMAT_DATE_ONLY);
        int numberOfOffDays = calculateVacationDuration(submitDto.getVacationStartDate(), submitDto.getVacationEndDate());
        validateStartAndDate(vacationStartDate, vacationEndDate);
        validateNumberOfOffDaysWithEmployee(numberOfOffDays, employee, vacationTypeEntity);
        Vacation vacation = new Vacation();
        vacation.setVacationTypeEntity(vacationTypeEntity);
        vacation.setEmployee(employee);
        vacation.setVacationStartDate(vacationStartDate);
        vacation.setVacationEndDate(vacationEndDate);
        vacation.setNumberOfDays(numberOfOffDays);
        return VacationAdapter.getVacationDtoFromVacationEntity(saveVacationUpdateBalance(vacation, employee));
    }

    @Override
    public int calculateVacationDuration(String startDate, String endDate) {
        Date fromDate = getDateFromString(startDate, DATE_FORMAT_DATE_ONLY);
        Date toDate = getDateFromString(endDate, DATE_FORMAT_DATE_ONLY);
        validateStartAndDate(fromDate, toDate);
        long diff = toDate.getTime() - fromDate.getTime();
        int diffInDays = (int) TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
        int numberOfOffDaysInThisPeriod = weekendsRepository.countByDateGreaterThanEqualAndDateLessThanEqual(fromDate, toDate);
        if ((diffInDays - numberOfOffDaysInThisPeriod + 1) <= 0)
            throw new VacationsServiceException("vacations-service-411", "Duration should be at least 1");
        return diffInDays - numberOfOffDaysInThisPeriod + 1;
    }

    protected void validateStartAndDate(Date startDate, Date endDate) {
        if (startDate.after(endDate))
            throw new VacationsServiceException("vacations-service-410", "Start-date is after end-date");
    }

    protected void validateNumberOfOffDaysWithEmployee(int numberOfOffDays, Employee employee, VacationTypeEntity vacationTypeEntity) {
        if ((vacationTypeEntity.getType() == SICK && numberOfOffDays > employee.getSickVacationBalance())
                || (vacationTypeEntity.getType() == ANNUAL && numberOfOffDays > employee.getAnnualVacationBalance())
        ) throw new VacationsServiceException("vacations-service-412", "No sufficient balance");
    }

    @Transactional
    public Vacation saveVacationUpdateBalance(Vacation vacation, Employee employee) {
        if (vacation.getVacationTypeEntity().getType() == SICK) {
            employee.setSickVacationBalance(employee.getSickVacationBalance() - vacation.getNumberOfDays());
        } else if (vacation.getVacationTypeEntity().getType() == ANNUAL) {
            employee.setAnnualVacationBalance(employee.getAnnualVacationBalance() - vacation.getNumberOfDays());
        }
        employeeRepository.save(employee);
        return vacationRepository.save(vacation);
    }
}