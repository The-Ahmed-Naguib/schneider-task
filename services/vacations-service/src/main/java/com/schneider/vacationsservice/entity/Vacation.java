package com.schneider.vacationsservice.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity(name = "vacation")
public class Vacation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "vacation_start_date", nullable = false)
    private Date vacationStartDate;

    @Column(name = "vacation_end_date", nullable = false)
    private Date  vacationEndDate;

    @Column(name = "number_of_days", nullable = false)
    private int  numberOfDays;

    @JoinColumn(name = "employee_id")
    @ManyToOne
    private Employee employee;

    @JoinColumn(name = "vacation_type_id")
    @ManyToOne(fetch = FetchType.EAGER)
    private VacationType vacationType;
}
