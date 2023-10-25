package co.edu.uniquindio.proyecto.Controllers;

import co.edu.uniquindio.proyecto.Dto.AnswerPetitionDTO;
import co.edu.uniquindio.proyecto.Dto.AppointmentDTO;
import co.edu.uniquindio.proyecto.Dto.ItemAttentionDTO;
import co.edu.uniquindio.proyecto.Dto.Patient.EditedPatientDTO;
import co.edu.uniquindio.proyecto.Dto.Patient.ItemAppointmentPatientDTO;
import co.edu.uniquindio.proyecto.Dto.Patient.ItemPatientPwdDTO;
import co.edu.uniquindio.proyecto.Dto.Patient.PatientDTO;
import co.edu.uniquindio.proyecto.Dto.Petition.ItemDoctorPatientDTO;
import co.edu.uniquindio.proyecto.Dto.Petition.PetitionDTO;
import co.edu.uniquindio.proyecto.Dto.PetitionMessagedDTO;
import co.edu.uniquindio.proyecto.Exception.AppointmentException.AppointmentsNotFoundException;
import co.edu.uniquindio.proyecto.Exception.AttentionNotFoundException;
import co.edu.uniquindio.proyecto.Exception.DoctorExceptions.AppointmentNotFoundException;
import co.edu.uniquindio.proyecto.Exception.DoctorExceptions.DoctorsNotFoundException;
import co.edu.uniquindio.proyecto.Exception.PatientException.PatientNotFoundException;
import co.edu.uniquindio.proyecto.Model.Enum.AppointmentState;
import co.edu.uniquindio.proyecto.Model.Enum.DoctorState;
import co.edu.uniquindio.proyecto.Model.Enum.Specialization;
import co.edu.uniquindio.proyecto.Services.Interfaces.PatientServices;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/patient")
public class PatientController {
    private final PatientServices patientServices;

    @PostMapping("/sign-in")
    public int sigIn(@Valid @RequestBody PatientDTO patientDTO) throws Exception {
        return patientServices.sigIn(patientDTO);
    }

    @PutMapping("/edit-account")
    public int editAccount(@Valid @RequestBody EditedPatientDTO editedPatientDTO) throws PatientNotFoundException {
        return patientServices.editAccount(editedPatientDTO);
    }

    @GetMapping("/get-patient-id/{identification}")
    public PatientDTO getPatientByIdentification(@PathVariable int identification) throws PatientNotFoundException {
        return patientServices.getPatientByIdentification(identification);
    }

    @PutMapping("/delete-account/{code}")
    public void deleteAccount(@PathVariable int code) throws Exception {
        patientServices.deleteAccount(code);
    }

    @PutMapping("/step1-change-pwd/{email}")
    public void sendLink(@PathVariable String email) throws Exception {
        patientServices.sendLink(email);
    }

    @PutMapping("/step2-change-pwd")
    public int changePassword(@Valid @RequestBody ItemPatientPwdDTO itemPatientPwdDTO) throws Exception {
        return patientServices.changePassword(itemPatientPwdDTO);
    }

    @GetMapping("/api/check-availability")
    public List<ItemDoctorPatientDTO> checkAvailability(@Valid @RequestBody Specialization specialization,
                                                        @Valid @RequestBody DoctorState doctorState)
            throws DoctorsNotFoundException {
        return patientServices.checkAvailability(specialization,doctorState);
    }

    @PostMapping("/create-appointment")
    public AppointmentDTO createAppointment(@Valid @RequestBody ItemAppointmentPatientDTO itemAppointmentPatientDTO) throws Exception {
        return patientServices.createAppointment(itemAppointmentPatientDTO);
    }


    @PutMapping("/cancel-appointment/{code}")
    public int cancelAppointment(@PathVariable int code) throws Exception {
        return patientServices.cancelAppointment(code);
    }

    @GetMapping("/get-appointments/{code}")
    public List<AppointmentDTO> listAppointment(@PathVariable int code) throws AppointmentNotFoundException, AppointmentsNotFoundException {
        return patientServices.listAppointment(code);
    }

    @GetMapping("/get-appointments-by-state")
    public List<AppointmentDTO> filterAppointmentsByState(@Valid @RequestBody AppointmentState appointmentState) throws AppointmentsNotFoundException {
        return patientServices.filterAppointmentsByState(appointmentState);
    }

    @GetMapping("/get-choosed-appointment/{code}")
    public ItemAttentionDTO showDetailsAppointment(@PathVariable int code)
            throws AppointmentNotFoundException, AttentionNotFoundException {
        return patientServices.showDetailsAppointment(code);
    }

    @PostMapping("/create-petition")
    public int createPetition(@Valid @RequestBody PetitionDTO petitionDTO) throws Exception {
        return patientServices.createPetition(petitionDTO);
    }

    @GetMapping("/get-attentions/{code}")
    public List<ItemAttentionDTO> listAttention(@PathVariable int code) throws AppointmentsNotFoundException {
        return patientServices.listAttention(code);
    }

    @GetMapping("/get-appointments-by-doctor/{patientCode}/{doctorId}")
    public List<AppointmentDTO> listAppointmentByDoctor(@PathVariable int patientCode,@PathVariable int doctorId) throws AppointmentsNotFoundException {
        return patientServices.listAppointmentByDoctor(patientCode,doctorId);
    }

    @GetMapping("/get-appointments-by-date/{patientCode}")
    public List<AppointmentDTO> listAppointmentByDate(@PathVariable int patientCode,@Valid @RequestBody LocalDate date) throws Exception {
        return patientServices.listAppointmentByDate(patientCode, date);
    }

    @GetMapping("/get-petition-by-patient/{patientCode}")
    public List<PetitionMessagedDTO> listPetitionByPatient(@PathVariable int patientCode) throws Exception {
        return patientServices.listPetitionByPatient(patientCode);
    }

    @PostMapping("/ansewer-petition-patient")
    public int answerPetitionPatient(@Valid @RequestBody AnswerPetitionDTO answerPetitionDTO) throws Exception {
        return patientServices.answerPetitionPatient(answerPetitionDTO);
    }

}
