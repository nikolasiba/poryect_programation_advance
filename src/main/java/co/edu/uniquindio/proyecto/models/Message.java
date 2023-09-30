package co.edu.uniquindio.proyecto.models;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)

public class Message implements Serializable {

    @Id
    private String code;

    private LocalDateTime createdDate;

    @Lob
    @Column(length= 500)
    private String message;

    @ManyToOne
    private Account account;

    @ManyToOne
    private Petition petition;

    @OneToOne
    private Message nextMessage;
}
