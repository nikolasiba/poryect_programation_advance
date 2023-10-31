package co.edu.uniquindio.proyecto.Dto;

public record MessageDTO<T>(
        boolean error,
        T response
) {


}
