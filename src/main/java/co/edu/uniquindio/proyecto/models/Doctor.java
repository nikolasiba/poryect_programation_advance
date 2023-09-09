package co.edu.uniquindio.proyecto.models;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class Doctor implements Serializable {
    @Id
    private String code;

    @Enumerated(EnumType.STRING)
    private Specialization specialization;

}
