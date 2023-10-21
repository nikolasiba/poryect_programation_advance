package co.edu.uniquindio.proyecto.Model;

import co.edu.uniquindio.proyecto.Model.Enum.Day;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
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

    @Enumerated(EnumType.STRING)
    private Day day;

    @Column(nullable = false)
    private LocalTime initialTime;

    @Column(nullable = false)
    private LocalTime finalTime;

    @ManyToOne
    private Doctor doctor;

}
