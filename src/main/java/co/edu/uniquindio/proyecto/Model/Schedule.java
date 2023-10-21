package co.edu.uniquindio.proyecto.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
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

    @Column(nullable = false)
    private String day;

    @Column(nullable = false)
    private LocalTime initialTime;

    @Column(nullable = false)
    private LocalTime finalTime;

    @ManyToOne
    private Doctor doctor;

}
