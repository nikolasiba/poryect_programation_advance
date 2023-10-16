package co.edu.uniquindio.proyecto.Model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)

public class Message implements Serializable {

    @Id
    private int code;

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
