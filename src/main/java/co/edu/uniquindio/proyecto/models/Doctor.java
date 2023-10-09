package co.edu.uniquindio.proyecto.models;

import co.edu.uniquindio.proyecto.models.Enum.Specialization;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)

public class Doctor extends User implements Serializable {

    @Enumerated(EnumType.STRING)
    private Specialization specialization;

    @OneToMany(mappedBy = "doctor")
    private List<FreeDay>freeDayList;

    @OneToMany(mappedBy = "doctor")
    private List<Schedule>scheduleList;

    @OneToMany(mappedBy = "doctor")
    private List<Appointment>appointmentList;

}
