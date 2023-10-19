package co.edu.uniquindio.proyecto.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class FreeDay implements Serializable {
    @Id
    private String code;

    @Column(nullable = false)
    private LocalDate day;

    @ManyToOne
    private Doctor doctor;

}
