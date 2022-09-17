package com.schneider.vacationsservice.reposatory;

import com.schneider.vacationsservice.entity.WeekendsAndHolidays;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface WeekendsRepository extends CrudRepository<WeekendsAndHolidays, Long> {

 public int countByDateGreaterThanEqualAndDateLessThanEqual(Date from,Date to);
}
