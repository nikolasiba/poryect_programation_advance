package co.edu.uniquindio.proyecto.Dto;

import co.edu.uniquindio.proyecto.Model.Enum.AppointmentState;
import jakarta.persistence.Column;
import jakarta.persistence.Lob;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ItemAttentionDTO(

      @Positive
      int codeAttention,
      @Lob
      @Column(length = 300)
      String diagnosis,
      @Lob
      @Column(length = 300)
      String treatment,
      @Lob
      @Column(length = 300)
      String medicalNotes,
      @Positive
      int codeAppointment,
      @NotNull
      AppointmentState appointmentState

) {
}
