package co.edu.uniquindio.proyecto.Model;

import co.edu.uniquindio.proyecto.Model.Enum.PetitionState;
import co.edu.uniquindio.proyecto.Model.Enum.TypePetition;
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
public class Petition implements Serializable {

    @Id
    private int code;

    @NotNull
    private LocalDateTime createdDate;

    @Column(length= 500, nullable = false)
    private String reason;

    @Enumerated(EnumType.STRING)
    private TypePetition typePetition;

    @Enumerated(EnumType.STRING)
    private PetitionState petitionState;

    @ManyToOne
    private Appointment appointment;

    @OneToMany(mappedBy = "petition")
    private List<Message>messageList;


}
