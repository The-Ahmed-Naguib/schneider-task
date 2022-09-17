package com.schneider.vacationsservice.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity(name = "vacation_type")
public class VacationType {

    @Id
    private Long id;

    @Column(name = "name", nullable = false)
    private String type;
}
