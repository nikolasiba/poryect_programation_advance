package co.edu.uniquindio.proyecto.Model;

import co.edu.uniquindio.proyecto.Model.Enum.DoctorState;
import co.edu.uniquindio.proyecto.Model.Enum.Specialization;
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

    @Enumerated(EnumType.STRING)
    private DoctorState doctorState;

    @OneToMany(mappedBy = "doctor")
    private List<DayOff>freeDayList;

    @OneToMany(mappedBy = "doctor")
    private List<Schedule>scheduleList;

    @OneToMany(mappedBy = "doctor")
    private List<Appointment>appointmentList;

}
