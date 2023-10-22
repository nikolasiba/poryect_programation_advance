package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.Dto.AppointmentDTO;
import co.edu.uniquindio.proyecto.Dto.ItemAttentionDTO;
import co.edu.uniquindio.proyecto.Dto.Patient.EditedPatientDTO;
import co.edu.uniquindio.proyecto.Dto.Patient.ItemAppointmentPatientDTO;
import co.edu.uniquindio.proyecto.Dto.Patient.ItemPatientPwdDTO;
import co.edu.uniquindio.proyecto.Dto.Patient.PatientDTO;
import co.edu.uniquindio.proyecto.Dto.Petition.PetitionDTO;
import co.edu.uniquindio.proyecto.Exception.AppointmentException.AccountNotFoundException;
import co.edu.uniquindio.proyecto.Exception.AppointmentException.AppointmentsNotFoundException;
import co.edu.uniquindio.proyecto.Exception.AppointmentException.MaxNumAppointmentReachedException;
import co.edu.uniquindio.proyecto.Exception.AppointmentException.PatientPenalizedException;
import co.edu.uniquindio.proyecto.Exception.AttentionNotAssociatedAppointmentException;
import co.edu.uniquindio.proyecto.Exception.AttentionNotFoundException;
import co.edu.uniquindio.proyecto.Exception.DoctorExceptions.AppointmentNotFoundException;
import co.edu.uniquindio.proyecto.Exception.DoctorExceptions.DoctorsNotFoundException;
import co.edu.uniquindio.proyecto.Exception.PatientException.PatientNotFoundException;
import co.edu.uniquindio.proyecto.Model.Appointment;
import co.edu.uniquindio.proyecto.Model.Enum.*;
import co.edu.uniquindio.proyecto.Model.Patient;
import co.edu.uniquindio.proyecto.Model.Petition;
import co.edu.uniquindio.proyecto.Repository.PatientRepo;
import co.edu.uniquindio.proyecto.Services.Interfaces.PatientServices;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Transactional
@SpringBootTest
@Sql(value = "/dataset.sql")
public class PatientTest {
    @Autowired
    private PatientServices patientServices;
    @Autowired
    private PatientRepo patientRepo;


    @Test
    public void sigInPatientTest() {

        //  Patient patient = patientRepo.findByIdentification(123);

        ;
        PatientDTO patientDto = new PatientDTO(
                "pepito",
                123,
                "31243",
                "",
                City.ARMENIA,
                LocalDate.now(),
                "muchas",
                BloodType.B_POSITIVE,
                Eps.SANIDAD,
                "nikolasiba233@gmail.com",
                "235148"
        );

        try {
            patientServices.sigIn(patientDto);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    public void deleteAccount() {


        try {
            patientServices.deleteAccount(2);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void editAccount() {
        Patient patient = patientRepo.findByIdentification(1234);
        System.out.print("\n esteeeeeeee: " + patient.getName() + "\n");

        EditedPatientDTO editedPatientDTO = new EditedPatientDTO(
                patient.getCode(),
                patient.getIdentification(),
                "nicolas ibanez",
                patient.getPhone(),
                patient.getUrlPicture(),
                patient.getCity(),
                patient.getBirthday(),
                patient.getAllergies(),
                patient.getBloodType(),
                patient.getEps()

        );

        try {
            patientServices.editAccount(editedPatientDTO);
            Patient patient2 = patientRepo.findByIdentification(1234);
            System.out.print("\n esteeeeeeee: " + patient2.getName() + "\n");


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void getPatientByIdentification() {
        try {
            PatientDTO patientDto = patientServices.getPatientByIdentification(1234);
            if (patientDto != null) {
                System.out.print(patientDto.name());
            } else {
                throw new RuntimeException();
            }
        } catch (Exception e) {
            throw new RuntimeException();
        }

    }


    @Test
    public void sendLink() {
        try {
            patientServices.sendLink("nikolasiba23@gmail.com");
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    @Test
    public void changePassword() {
        ItemPatientPwdDTO itemPatientPwdDTO = new ItemPatientPwdDTO(
                "nikolasiba213@gmail.com",
                "123123",
                "123123"
        );
        try {
            if (patientServices.changePassword(itemPatientPwdDTO) > 0) {
                System.out.print("\n se cambio correctamente\n");
            }

        } catch (PatientNotFoundException e) {
            System.out.print(e.toString());
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }


    @Test
    public void checkAvailability() {

        Specialization specialization = Specialization.OPHTHALMOLOGY;
        DoctorState doctorState = DoctorState.AVAILABLE;

        try {
            patientServices.checkAvailability(specialization, doctorState);

        } catch (DoctorsNotFoundException e) {
            System.out.print(e.toString());
        } catch (Exception e) {
            throw new RuntimeException();
        }


    }

    @Test
    public void createAppointment() {
        ItemAppointmentPatientDTO itemAppointmentPatientDTO = new ItemAppointmentPatientDTO(
                2,
                4,
                1,
                "hola"

        );
        try {
            patientServices.createAppointment(itemAppointmentPatientDTO);

        } catch (MaxNumAppointmentReachedException | PatientPenalizedException e) {
            System.out.print(e.toString());

        } catch (Exception e) {

            throw new RuntimeException(e);
        }
    }

    @Test
    public void cancelAppointment() {

        try {
            patientServices.cancelAppointment(2);
        } catch (AppointmentNotFoundException e) {
            System.out.print("\n" + e.toString() + "\n");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    public void listAppointment() {
        List<AppointmentDTO> appointmentList = new ArrayList<>();
        try {
            appointmentList = patientServices.listAppointment(2);
            System.out.print("\n" + appointmentList.get(0).toString() + "\n");

        } catch (AppointmentNotFoundException | AppointmentsNotFoundException e) {
            System.out.print(e.toString());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void filterAppointmentsByState() {
        List<AppointmentDTO> appointmentList = new ArrayList<>();
        try {
            appointmentList = patientServices.filterAppointmentsByState(AppointmentState.PENDING);
            System.out.print("\n" + appointmentList.get(0).toString() + "\n");
        } catch (AppointmentsNotFoundException e) {
            System.out.print("\n " + "se genero esta excepcion" + e.toString() + "\n");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void showDetailsAppointment() {

        try {
            ItemAttentionDTO itemAttentionDTO = patientServices.showDetailsAppointment(2);
            System.out.print("\n" + itemAttentionDTO.toString() + "\n");

        } catch (AppointmentNotFoundException | AttentionNotFoundException e) {
            System.out.print("\n " + "se genero esta excepcion" + e.toString() + "\n");
        } catch (Exception e) {
            throw new RuntimeException(e.toString());
        }
    }

    @Test
    public void createPetition() {
        PetitionDTO petitionDTO = new PetitionDTO(
                2,
                "mala atencion",
                TypePetition.CLAIMS,
                2,
                "no estoy conforme con la atencion"
        );

        try {
            int response = patientServices.createPetition(petitionDTO);
            if (response > 0) {
                System.out.print("\n se genero correctamente la petcion \n");
            } else {
                System.out.print("\n no se  genero correctamente la petcion \n");
            }
        } catch (AppointmentNotFoundException |
                 AttentionNotAssociatedAppointmentException | AccountNotFoundException e) {
            System.out.print("\n " + "se genero esta excepcion" + e.toString() + "\n");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    public void listAttention() {
        List<ItemAttentionDTO> itemAttentionDTOList = new ArrayList<>();
        try {
            itemAttentionDTOList = patientServices.listAttention(2);
            System.out.print("\n" + itemAttentionDTOList.get(0).toString() + "\n");
        } catch (AppointmentsNotFoundException e) {
            System.out.print("\n " + "se genero esta excepcion" + e.toString() + "\n");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @Test
    public void listAppointmentByDoctor() {
        List<AppointmentDTO> appointmentList = new ArrayList<>();
        try {
            appointmentList = patientServices.listAppointmentByDoctor(2, 11223);
            if (appointmentList.isEmpty()) {
                throw new AppointmentsNotFoundException("no se encontraron citas");
            }
            System.out.print("\n" + appointmentList.get(0).toString() + "\n");

        } catch (AppointmentsNotFoundException e) {
            System.out.print(e.toString());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
