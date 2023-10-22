package co.edu.uniquindio.proyecto.Model;

import co.edu.uniquindio.proyecto.Model.Enum.DayOffState;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class DayOff implements Serializable {

    @Id
    private String code;

    @Column(nullable = false)
    private LocalDate day;

    @ManyToOne
    private Doctor doctor;

    @Enumerated(EnumType.STRING)
    private DayOffState dayOffState;


}
