package co.edu.uniquindio.proyecto.Model;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)

public class Admin extends Account implements Serializable {

}
