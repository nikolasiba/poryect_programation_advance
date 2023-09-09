package co.edu.uniquindio.proyecto.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.*;

import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class Attention implements Serializable {

    @Id
    private String code;

    @Column(length = 300)
    private String diagnosis;

    @Column(length = 300)
    private String treatment;

    @Column(length = 300)
    private String medicalNotes;

    @OneToOne
    private Appointment appointment;
}
