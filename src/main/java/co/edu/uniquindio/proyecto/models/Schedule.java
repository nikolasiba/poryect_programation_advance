package co.edu.uniquindio.proyecto.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class Schedule implements Serializable {

    @Id
    private String code;

    private String day;

    private String initialTime;

    private String finalTime;

    @ManyToOne
    private Doctor doctor;
}
