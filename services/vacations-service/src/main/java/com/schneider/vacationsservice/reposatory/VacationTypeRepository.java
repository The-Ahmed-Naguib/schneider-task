package com.schneider.vacationsservice.reposatory;

import com.schneider.vacationsservice.entity.VacationTypeEntity;
import com.schneider.vacationsservice.enums.VacationType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VacationTypeRepository extends CrudRepository<VacationTypeEntity, VacationType> {
}
