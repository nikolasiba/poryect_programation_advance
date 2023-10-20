package co.edu.uniquindio.proyecto.test;
import co.edu.uniquindio.proyecto.Dto.Patient.PatientDTO;
import co.edu.uniquindio.proyecto.Model.Enum.BloodType;
import co.edu.uniquindio.proyecto.Model.Enum.City;
import co.edu.uniquindio.proyecto.Model.Enum.Eps;
import co.edu.uniquindio.proyecto.Repository.PatientRepo;
import co.edu.uniquindio.proyecto.Services.Interfaces.PatientServices;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDate;

@Transactional
@SpringBootTest
@Sql(value = "/dataset.sql")
public class PatientTest {
    @Autowired
    private PatientServices patientServices;
    //@Autowired
   // private PatientRepo patientRepo;


    @Test
    public void sigInPatientTest(){

      //  Patient patient = patientRepo.findByIdentification(123);

;
        PatientDTO patientDto = new PatientDTO(
                "pepito",//                patient.getName(),
                123,//                patient.getIdentification(),
                "31243",//                patient.getPhone(),
                "",//                patient.getUrlPicture(),
                City.ARMENIA,//                patient.getCity(),
                LocalDate.now(),//                patient.getBirthday(),
                "muchas",//                patient.getAllergies(),
                BloodType.B_POSITIVE,//                patient.getBloodType(),
                Eps.SANIDAD,//                patient.getEps(),
                "ahdhd@gmail.com",//                patient.getEmail(),
                "235148"//                patient.getPassword()
        );

        try {
            patientServices.sigIn(patientDto);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
