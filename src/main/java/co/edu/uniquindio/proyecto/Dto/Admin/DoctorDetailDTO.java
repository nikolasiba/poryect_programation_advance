package co.edu.uniquindio.proyecto.Dto.Admin;

import co.edu.uniquindio.proyecto.Model.Enum.City;
import co.edu.uniquindio.proyecto.Model.Enum.Specialization;
import jakarta.persistence.Column;
import jakarta.persistence.Lob;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.List;

public record DoctorDetailDTO(

        @Positive
        int code,
        @NotEmpty
        @Column(length = 30, nullable = false)
        String name,
        @NotEmpty
        @Column(unique = true)
        int identification,
        @NotEmpty
        @Column(length = 10, nullable = false)
        String phone,
        @Lob
        @Column(nullable = false)
        String urlPicture,
        @NotNull
        City city,

        @NotNull
        Specialization specialization,
        @NotEmpty
        List<ScheduleDTO> schedules,

        @NotEmpty
        @Email
        @Column(length = 50, unique = true)
        String email



) {
}
