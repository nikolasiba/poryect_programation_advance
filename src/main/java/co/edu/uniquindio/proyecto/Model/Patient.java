package co.edu.uniquindio.proyecto.Model;

import co.edu.uniquindio.proyecto.Model.Enum.BloodType;
import co.edu.uniquindio.proyecto.Model.Enum.Eps;
import co.edu.uniquindio.proyecto.Model.Enum.PatientState;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class Patient extends User implements Serializable {

    private LocalDate birthday;

    @Lob
    @Column(length = 200)
    private String allergies;

    @Enumerated(EnumType.STRING)
    private BloodType bloodType;

    @Enumerated(EnumType.STRING)
    private Eps eps;

    @Enumerated
    private PatientState patientState;

    @OneToMany(mappedBy = "patient")
    private List<Appointment>appointmentList;


}
