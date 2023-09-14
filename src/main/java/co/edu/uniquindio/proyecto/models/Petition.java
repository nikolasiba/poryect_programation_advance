package co.edu.uniquindio.proyecto.models;

import co.edu.uniquindio.proyecto.models.Enum.PetitionState;
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
public class Petition implements Serializable {

    @Id
    private String code;

    private LocalDateTime createdDate;

    private String type;

    @Column(length= 500)
    private String reason;

    @Enumerated(EnumType.STRING)
    private PetitionState petitionState;

    @ManyToOne
    private Appointment appointment;

    @OneToMany(mappedBy = "petition")
    private List<Message>messageList;


}
