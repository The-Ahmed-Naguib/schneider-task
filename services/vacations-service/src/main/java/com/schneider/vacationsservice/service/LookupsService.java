package com.schneider.vacationsservice.service;
import com.schneider.vacationsservice.entity.VacationType;

import java.util.List;

public interface LookupsService {
   List<VacationType> getVacationTypes();
}