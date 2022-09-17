package com.schneider.vacationsservice.reposatory;

import com.schneider.vacationsservice.entity.VacationType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VacationTypeRepository extends CrudRepository<VacationType, Long> {
}
