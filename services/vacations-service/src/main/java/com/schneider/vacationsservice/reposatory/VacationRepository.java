package com.schneider.vacationsservice.reposatory;

import com.schneider.vacationsservice.entity.Employee;
import com.schneider.vacationsservice.entity.Vacation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VacationRepository extends CrudRepository<Vacation, Long> {


}
