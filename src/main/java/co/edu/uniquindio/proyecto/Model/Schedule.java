package co.edu.uniquindio.proyecto.Model;

import co.edu.uniquindio.proyecto.Model.Enum.ScheduleState;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class Schedule implements Serializable {

    @Id
    private int code;

    @NotNull
    private LocalDate day;

    @Column(nullable = false)
    private LocalTime initialTime;

    @Column(nullable = false)
    private LocalTime finalTime;

    @ManyToOne
    private Doctor doctor;

    @Enumerated(EnumType.STRING)
    private ScheduleState scheduleState;



}
