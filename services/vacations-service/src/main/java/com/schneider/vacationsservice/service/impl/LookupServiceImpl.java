package com.schneider.vacationsservice.service.impl;

import com.schneider.vacationsservice.entity.VacationType;
import com.schneider.vacationsservice.reposatory.VacationTypeRepository;
import com.schneider.vacationsservice.service.LookupsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LookupServiceImpl implements LookupsService {
    VacationTypeRepository vacationTypeRepository;

    @Autowired
    public LookupServiceImpl(VacationTypeRepository vacationTypeRepository) {
        this.vacationTypeRepository = vacationTypeRepository;
    }


    @Override
    public List<VacationType> getVacationTypes() {
        return (List<VacationType>) vacationTypeRepository.findAll();
    }
}
