package co.edu.uniquindio.proyecto.Model;

import co.edu.uniquindio.proyecto.Model.Enum.City;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.io.Serializable;


@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@MappedSuperclass

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class User extends Account implements Serializable {

    @NotEmpty
    @Column(length = 30, nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private int identification;

    @Column(length = 10, nullable = false)
    private String phone;

    /**
     * como no sirve el varchar en mysql para textos muy largos, lob(large object), interpretará esto como un texto
     * o podemos usar columnDefinition para hacer que un dato sea el que deseamos
    */

    @Lob
    @Column(nullable = false)
    private String urlPicture;

    @Enumerated(EnumType.STRING)
    private City city;

}
