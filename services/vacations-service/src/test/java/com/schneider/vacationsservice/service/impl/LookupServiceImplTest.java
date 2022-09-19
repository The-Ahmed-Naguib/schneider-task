package com.schneider.vacationsservice.service.impl;

import com.schneider.vacationsservice.entity.VacationTypeEntity;
import com.schneider.vacationsservice.enums.VacationType;
import com.schneider.vacationsservice.reposatory.VacationTypeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LookupServiceImplTest {

    @Mock
    VacationTypeRepository vacationTypeRepository;
    @InjectMocks
    LookupServiceImpl lookupService;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getVacationTypes() {
        List<VacationTypeEntity> vacationTypeEntities = new ArrayList<>();
        vacationTypeEntities.add(new VacationTypeEntity(VacationType.SICK));
        when(vacationTypeRepository.findAll()).thenReturn(vacationTypeEntities);
        assertEquals(1, lookupService.getVacationTypes().size());
    }
}