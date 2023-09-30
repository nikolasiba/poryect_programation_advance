package co.edu.uniquindio.proyecto.models;

import co.edu.uniquindio.proyecto.models.Enum.BloodType;
import co.edu.uniquindio.proyecto.models.Enum.Eps;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class Patient extends User implements Serializable {

    private LocalDateTime birthday;

    @Column(length = 200)
    private String allergies;

    @Enumerated(EnumType.STRING)
    private BloodType bloodType;

    @Enumerated(EnumType.STRING)
    private Eps eps;

    @OneToMany(mappedBy = "patient")
    private List<Appointment>appointmentList;

}
