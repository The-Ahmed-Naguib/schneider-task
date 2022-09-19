package com.schneider.vacationsservice.service;
import com.schneider.vacationsservice.entity.VacationTypeEntity;

import java.util.List;

public interface LookupsService {
   List<VacationTypeEntity> getVacationTypes();
}