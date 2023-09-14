package co.edu.uniquindio.proyecto.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Account implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String code;

    @Column(length = 50, nullable = false, unique = true)
    @Email
    private String email;

    @NonNull
    @Column(length = 10, nullable = false)
    private String password;

    @OneToMany(mappedBy = "message")
    private List<Message> messageList;
}
