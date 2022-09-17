package com.schneider.vacationsservice.service.impl;

import com.schneider.vacationsservice.entity.VacationType;
import com.schneider.vacationsservice.reposatory.EmployeeRepository;
import com.schneider.vacationsservice.reposatory.VacationRepository;
import com.schneider.vacationsservice.reposatory.VacationTypeRepository;
import com.schneider.vacationsservice.reposatory.WeekendsRepository;
import com.schneider.vacationsservice.service.LookupsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class LookupServiceImpl implements LookupsService {

    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    VacationRepository vacationRepository;
    @Autowired
    VacationTypeRepository vacationTypeRepository;
    @Autowired
    WeekendsRepository weekendsRepository;

    @PostConstruct
    public void init() {
        System.out.println("employees : ");
        System.out.println(employeeRepository.findAll());
        System.out.println("vacationTypes : ");
        System.out.println(vacationTypeRepository.findAll());
        System.out.println("weekends : ");
        System.out.println(weekendsRepository.findAll());
    }

    @Override
    public List<VacationType> getVacationTypes() {
        System.out.println("employees : ");
        System.out.println(employeeRepository.findAll());
        System.out.println("vacationTypes : ");
        System.out.println(vacationTypeRepository.findAll());
        System.out.println("weekends : ");
        System.out.println(weekendsRepository.findAll());
        return (List<VacationType>) vacationTypeRepository.findAll();
    }
}
