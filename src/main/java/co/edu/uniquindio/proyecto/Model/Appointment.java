package co.edu.uniquindio.proyecto.Model;

import co.edu.uniquindio.proyecto.Model.Enum.AppointmentState;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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
public class Appointment implements Serializable {
    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int code;

    @NotNull
    private LocalDateTime createdDate;

    @NotNull
    private LocalDateTime appointmentDate;

    @Lob
    @NotNull
    @Column(length = 500)
    private String reason;

    @ManyToOne
    private Patient patient;

    @ManyToOne
    private Doctor doctor;

    @Enumerated(EnumType.STRING)
    private AppointmentState appointmentState;

    @OneToMany(mappedBy = "appointment")
    private List<Petition> petitionList;

    @OneToOne(mappedBy = "appointment")
    private Attention attention;


}
