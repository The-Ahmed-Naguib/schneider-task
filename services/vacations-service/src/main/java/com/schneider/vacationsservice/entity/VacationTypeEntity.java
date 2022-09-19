package com.schneider.vacationsservice.entity;

import com.schneider.vacationsservice.enums.VacationType;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity(name = "vacation_type")
public class VacationTypeEntity {
    @Id
    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private VacationType type;
}
