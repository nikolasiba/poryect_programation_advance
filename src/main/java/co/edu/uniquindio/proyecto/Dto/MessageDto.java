package co.edu.uniquindio.proyecto.Dto;

public record MessageDto<T>(
        boolean error,
        T response
) {


}
