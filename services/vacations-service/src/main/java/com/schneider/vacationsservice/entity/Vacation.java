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

    @JoinColumn(name = "employee_id")
    @ManyToOne
    private Employee employee;
}
