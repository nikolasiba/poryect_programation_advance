package co.edu.uniquindio.proyecto.Dto;

import jakarta.persistence.Column;
import org.springframework.format.annotation.DateTimeFormat;

public class FreeDayDTO {

    @Column(nullable = false)
    private String day;

}
