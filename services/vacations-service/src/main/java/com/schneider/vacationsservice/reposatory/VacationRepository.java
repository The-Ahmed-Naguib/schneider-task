package com.schneider.vacationsservice.reposatory;

import com.schneider.vacationsservice.entity.Vacation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface VacationRepository extends CrudRepository<Vacation, Long> {

    public List<Vacation> findByEmployee_Id(long employeeId);

    @Query(value = "select count (vac) from vacation vac " +
            "where (vac.vacationStartDate <= :startDate and vac.vacationEndDate >= :startDate )" +
            "or (vac.vacationStartDate <= :endDate and vac.vacationEndDate >= :endDate )")
    public int numberOfIntersections(@Param("startDate") Date startDate, @Param("endDate") Date endDate);


}
