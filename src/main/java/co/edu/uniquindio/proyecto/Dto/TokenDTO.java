package co.edu.uniquindio.proyecto.Dto;

import jakarta.validation.constraints.NotBlank;
public record TokenDTO (
        @NotBlank
        String token
){
}