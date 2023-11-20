package co.edu.uniquindio.proyecto.Dto.Admin;

import co.edu.uniquindio.proyecto.Model.Enum.City;
import co.edu.uniquindio.proyecto.Model.Enum.Specialization;
import jakarta.persistence.Column;
import jakarta.persistence.Lob;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.util.List;

public record DoctorRecordDTO(

        @NotBlank
        @Column(length = 30, nullable = false)
        String name,

        @NotNull
        @Column(nullable = false, unique = true)
        int identification,
        @NotNull
        City city,
        @NotNull
        Specialization specialization,
        @NotBlank
        @Column(length = 10, nullable = false)
        String phone,
        @NotBlank
        @Email
        @Length(max = 80)
        String email,
        @NotBlank
        @Column(length = 10, nullable = false, unique = true)
        String password,
        @Lob
        @NotBlank
        @Column(nullable = false)
        String urlPicture,
        @NotEmpty
        List<ScheduleDTO> schedules

) {
}
