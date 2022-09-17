package com.schneider.vacationsservice.reposatory;

import com.schneider.vacationsservice.entity.Vacation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VacationRepository extends CrudRepository<Vacation, Long> {

    public List<Vacation> findByEmployee_Id(long employeeId);


}
