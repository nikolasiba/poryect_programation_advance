package co.edu.uniquindio.proyecto.models;

import jakarta.persistence.Entity;
import lombok.*;

import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor

@ToString(callSuper = true)
public class Admin extends Account implements Serializable {

}
