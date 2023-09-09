package co.edu.uniquindio.proyecto.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.*;
import org.hibernate.annotations.ManyToAny;

import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class User extends Account implements Serializable {

    @Id
    private String id;

    private String name;

    private String phone;

    private String urlPicture;

    @ManyToOne
    private City city;
}
