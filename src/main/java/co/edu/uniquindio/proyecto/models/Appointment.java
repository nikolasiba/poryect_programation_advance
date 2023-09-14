package co.edu.uniquindio.proyecto.models;

import co.edu.uniquindio.proyecto.models.Enum.AppointmentState;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class Appointment implements Serializable {
    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String code;

    private LocalDateTime createdDate;

    private LocalDateTime appointmentDate;

    @Column(length = 500)
    private String reason;

    @ManyToOne
    private Patient patient;

    @ManyToOne
    private Doctor doctor;

    @Enumerated(EnumType.STRING)
    private AppointmentState appointmentState;

    @OneToOne(mappedBy = "appointment")
    private Attention attention;


}
