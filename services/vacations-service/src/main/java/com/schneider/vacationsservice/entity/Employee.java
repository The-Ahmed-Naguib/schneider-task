package com.schneider.vacationsservice.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 100, nullable = false)
    private String name;

    @Column(name = "annual_vacation_balance", nullable = false)
    private int  annualVacationBalance;

    @Column(name = "sick_vacation_balance", nullable = false)
    private int  sickVacationBalance;

    @OneToMany(mappedBy = "employee")
    private List<Vacation> vacations;

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", annualVacationBalance=" + annualVacationBalance +
                ", sickVacationBalance=" + sickVacationBalance +
                '}';
    }
}
