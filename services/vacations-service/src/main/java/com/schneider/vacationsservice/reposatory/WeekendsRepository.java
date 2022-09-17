package com.schneider.vacationsservice.reposatory;

import com.schneider.vacationsservice.entity.Vacation;
import com.schneider.vacationsservice.entity.WeekendsAndHolidays;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeekendsRepository extends CrudRepository<WeekendsAndHolidays, Long> {


}
