package co.edu.uniquindio.proyecto.models;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ManyToAny;

import java.io.Serializable;


@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@MappedSuperclass

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User extends Account implements Serializable {
    @Column(length = 30, nullable = false)
    private String name;
    @Column(length = 30, nullable = false, unique = true)
    private String id;
    @Column(length = 30, nullable = false)
    private String phone;

    /**
     * como no sirve el varchar en mysql, lob(large object), interpretará esto como un texto
     * o podemos usar columnDefinition para hacer que un dato sea el que deseamos
    */

     @Lob
    @Column(nullable = false)
    private String urlPicture;

    @ManyToOne
    private City city;
}
