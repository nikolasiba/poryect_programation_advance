package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.Dto.Admin.DoctorRecordDTO;
import co.edu.uniquindio.proyecto.Dto.Admin.ScheduleDTO;
import co.edu.uniquindio.proyecto.Model.Enum.City;
import co.edu.uniquindio.proyecto.Model.Enum.Specialization;
import co.edu.uniquindio.proyecto.Services.Interfaces.AdminServices;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Transactional
@SpringBootTest
public class AdminTest {
    @Autowired
    private AdminServices adminServices;


    @Test
    public void createDoctorTest(){

        List<ScheduleDTO> schedules = new ArrayList<>();
        schedules.add( new ScheduleDTO("LUNES",
                LocalTime.of(7, 0, 0),
                LocalTime.of(14, 0, 0) ) );

        DoctorRecordDTO doctorRecordDTO = new DoctorRecordDTO(
                "Pepito",
                82872,
                City.ARMENIA,
                Specialization.OPHTHALMOLOGY,
                "78387",
                "pepito@email.com",
                "123a",
                "url_picture",
                schedules
        );

        try {
            adminServices.createDoctor(doctorRecordDTO);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }


}