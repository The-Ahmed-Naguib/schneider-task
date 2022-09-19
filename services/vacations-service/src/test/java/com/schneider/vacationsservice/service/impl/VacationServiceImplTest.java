package com.schneider.vacationsservice.service.impl;

import com.schneider.vacationsservice.exception.VacationsServiceException;
import com.schneider.vacationsservice.reposatory.EmployeeRepository;
import com.schneider.vacationsservice.reposatory.VacationRepository;
import com.schneider.vacationsservice.reposatory.VacationTypeRepository;
import com.schneider.vacationsservice.reposatory.WeekendsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.text.SimpleDateFormat;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class VacationServiceImplTest {

    @Mock
    VacationTypeRepository vacationTypeRepository;

    @Mock
    EmployeeRepository employeeRepository;
    @Mock
    VacationRepository vacationRepository;
    @Mock
    WeekendsRepository weekendsRepository;
    @InjectMocks
    VacationServiceImpl vacationService;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testValidateStartAndDateWithInvalidDates() {
        assertThrows(VacationsServiceException.class,
                () -> {
                    vacationService.validateStartAndDate(new SimpleDateFormat("yyyy-MM-dd").parse("2022-09-18"),
                            new SimpleDateFormat("yyyy-MM-dd").parse("2022-09-17"));
                });
    }

    @Test
    void testValidateStartAndDateWithValidDates() {
        assertDoesNotThrow(
                () -> {
                    vacationService.validateStartAndDate(new SimpleDateFormat("yyyy-MM-dd").parse("2022-09-18"),
                            new SimpleDateFormat("yyyy-MM-dd").parse("2022-09-21"));
                });
    }

    @Test
    void calculateVacationDuration1day() {
        when(weekendsRepository.countByDateGreaterThanEqualAndDateLessThanEqual(any(), any())).thenReturn(0);
        assertEquals(1, vacationService.calculateVacationDuration("2022-09-18", "2022-09-18"));
    }

    @Test
    void calculateVacationDuration3daysWithWeekend() {
        when(weekendsRepository.countByDateGreaterThanEqualAndDateLessThanEqual(any(), any())).thenReturn(1);
        assertEquals(3, vacationService.calculateVacationDuration("2022-09-18", "2022-09-21"));
    }

    @Test
    void calculateVacationDuration0day() {
        when(weekendsRepository.countByDateGreaterThanEqualAndDateLessThanEqual(any(), any())).thenReturn(1);
        assertThrows(VacationsServiceException.class,
                () -> {
                    assertEquals(1, vacationService.calculateVacationDuration("2022-09-18", "2022-09-18"));
                });
    }
}