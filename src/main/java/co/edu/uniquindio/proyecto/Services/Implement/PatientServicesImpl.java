package co.edu.uniquindio.proyecto.Services.Implement;

import co.edu.uniquindio.proyecto.Dto.EmailDTO;
import co.edu.uniquindio.proyecto.Dto.PatientDTOS.*;
import co.edu.uniquindio.proyecto.Exception.*;
import co.edu.uniquindio.proyecto.Model.Appointment;
import co.edu.uniquindio.proyecto.Model.Doctor;
import co.edu.uniquindio.proyecto.Model.Enum.AppointmentState;
import co.edu.uniquindio.proyecto.Model.Enum.DoctorState;
import co.edu.uniquindio.proyecto.Model.Enum.PatientState;
import co.edu.uniquindio.proyecto.Model.Enum.Specialization;
import co.edu.uniquindio.proyecto.Model.Patient;
import co.edu.uniquindio.proyecto.Repository.AppointmentRepo;
import co.edu.uniquindio.proyecto.Repository.DoctorRepo;
import co.edu.uniquindio.proyecto.Repository.PatientRepo;
import co.edu.uniquindio.proyecto.Repository.PetitionRepo;
import co.edu.uniquindio.proyecto.Services.Interfaces.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class PatientServicesImpl implements PatientServices {

        final PatientRepo patientRepo;
        final DoctorRepo doctorRepo;

        final AppointmentRepo appointmentRepo;
        final PetitionRepo petitionRepo;

        final EmailServices emailServices;
        final ImageServices imageServices;

        final PetitionServices petitionServices;
        final AppointmentServices appointmentServices;


        @Override
        public int sigIn(PatientDTO patientDTO) throws Exception {

            if (validateIfExists(patientDTO.identification())){
                throw new RepeatedIdException("Id "+patientDTO.identification()+" is already used");
            }

            if (validateIfEmailExist(patientDTO.email())){
                throw new RepeatedEmailException("Email"+ patientDTO.email()+"is already used");
            }

            Patient patient = new Patient();

            patient.setEmail(patientDTO.email());
            patient.setPassword(patientDTO.password());

            patient.setName(patientDTO.name());
            patient.setIdentification(patientDTO.identification());
            patient.setPhone(patientDTO.phone());

            patient.setUrlPicture(patientDTO.urlPicture());

            patient.setCity(patientDTO.city());

            patient.setBirthday(patientDTO.birthday());

            patient.setAllergies(patientDTO.allergies());
            patient.setEps(patientDTO.eps());
            patient.setPatientState(PatientState.ASSET);

            patientRepo.save(patient);

            EmailDTO emailDTO = new EmailDTO(
                    patient.getEmail(),
                    "Appointment Center Team",
                    "Wellcome to appointment team, thanks to you with your support "
            );

            emailServices.sendEmail(emailDTO);

            return patient.getCode();

        }

        @Override
        public int editAccount(EditedPatientDTO editedPatientDTO) throws PatientNotExistException {

            Optional<Patient> optional = patientRepo.findById(editedPatientDTO.code());

            if (optional.isEmpty()){
                throw new PatientNotExistException("Patient with the code "+editedPatientDTO.code()+" doesn´t exist");
            }

            Patient wanted = optional.get();

            wanted.setIdentification(editedPatientDTO.identification());
            wanted.setName(editedPatientDTO.name());
            wanted.setPhone(editedPatientDTO.phone());
            wanted.setUrlPicture(editedPatientDTO.urlPicture());
            wanted.setCity(editedPatientDTO.city());
            wanted.setBirthday(editedPatientDTO.birthday());
            wanted.setAllergies(editedPatientDTO.allergies());
            wanted.setBloodType(editedPatientDTO.bloodType());
            wanted.setEps(editedPatientDTO.eps());

            patientRepo.save(wanted);

            return wanted.getCode();

        }

        @Override
        public void deleteAccount(int code) throws Exception {

            Optional<Patient> optional = patientRepo.findById(code);

            if (optional.isEmpty()){
                throw new PatientNotExistException("Patient with the code "+code+" doesn´t exist");
            }

            Patient wanted = optional.get();
            wanted.setPatientState(PatientState.INACTIVE);

            EmailDTO emailDTO = new EmailDTO(
                    wanted.getEmail(),
                    "Appointment Center Team",
                    "Hi!\nYour account was deleted successfully"
            );

            emailServices.sendEmail(emailDTO);

            patientRepo.save(wanted);

        }

        @Override
        public void sendLink(String email) throws Exception {

            if (validateIfEmailExist(email)){
                throw new PatientNotExistException("Patient with email "+email+" doesn´t exist");
            }

            EmailDTO emailDTO = new EmailDTO(
                    email,
                    "Appointment Center Team",
                    "Please follow the link bellow to change your password\n RUTA"
            );

            emailServices.sendEmail(emailDTO);

        }

        @Override
        public int changePassword(ChangePasswordPatientDTO changePasswordPatientDTO) throws Exception {

            if (validateIfEmailExist(changePasswordPatientDTO.email())){
                throw new PatientNotExistException("Patient with email "+changePasswordPatientDTO.email()+" doesn´t exist");
            }

            Patient patient = patientRepo.findByEmail(changePasswordPatientDTO.email());

            if (changePasswordPatientDTO.repeatPassword().equals(changePasswordPatientDTO.password())){
                throw new PssWrdNotMatchException("Passwords don´t match, please repeat");
            }

            patient.setPassword(changePasswordPatientDTO.password());

            patientRepo.save(patient);

            EmailDTO emailDTO = new EmailDTO(
                    changePasswordPatientDTO.email(),
                    "Appointment Center Team",
                    "Hi!\n Your password was changed successfully"
            );

            emailServices.sendEmail(emailDTO);

            return patient.getCode();
        }


        @Override
        public List<ItemDoctorDTO> checkAvailability(Specialization specialization, DoctorState doctorState) {

            List<Doctor> doctors = doctorRepo.findAllBySpecializationAndDoctorState(specialization, DoctorState.AVAILABE);

            List<ItemDoctorDTO> answer = new ArrayList<>();

            for (Doctor d: doctors) {
                answer.add( new ItemDoctorDTO(
                        d.getIdentification(),
                        d.getName(),
                        d.getScheduleList()) );
            }

            return answer;

        }

    @Override
    public int createAppointment(CreateAppointmentPatientDTO createAppointmentPatientDTO) throws Exception {
        return 0;
    }

    @Override
    public int cancelAppointement(int code) throws Exception {
        return 0;
    }

/*        @Override
        public int createAppointment(CreateAppointmentPatientDTO createAppointmentPatientDTO) throws Exception {
                return appointmentServices.createAppointment(createAppointmentPatientDTO);
            }*/

/*        @Override
        public int cancelAppointement(int code) throws Exception {

            return appointmentServices.cancelAppointment(code);

        }*/

    @Override
    public int calculateAge(LocalDate birthday) {

        LocalDate now = LocalDate.now();
        Period period = Period.between(birthday, now);

        return period.getYears();

    }

    @Override
    public List<Appointment> listCodePatientAppointments(int code) {
        return null;
    }

    @Override
        public List<AppointmentStateDTO> filterAppointmentsByState(AppointmentState appointmentState)
                throws NotAppointmentsCreatedException {

            return appointmentServices.filterAppointmentsByState(appointmentState);

        }

/*        @Override
        public int createPetition(ItemPetitionDTO itemPetitionDTO) throws AppointmentNotRelatedException {

            return petitionServices.createPetition(itemPetitionDTO);

        }

        @Override
        public int cancelPetition(int code) throws PetitionNotFoundException {
            return petitionServices.cancelPetition(code);

        }*/

        @Override
        public void filterPQRS() {

        }

        @Override
        public void responderPQRS() {

        }

        @Override
        public void filtrarCitasPorFecha() {

        }

        @Override
        public void filtrarCitasPorMedico() {

        }

        @Override
        public void verDetalleCita() {

        }

        private boolean validateIfEmailExist(String email) {
            return patientRepo.findByEmail(email)!= null;
        }
        private boolean validateIfExists(int id) {
            return patientRepo.findByIdentification(id) != null;
        }

        public Patient filterPatientByCode(int code) throws PatientNotExistException {

            Optional<Patient>optional= patientRepo.findById(code);

            if (optional.isEmpty()){
                throw new PatientNotExistException("Patient with the code "+code+" doesn´t exist");
            }

            return optional.get();

        }

    @Override
    public PatientDTO getPatientByName(String name) throws PatientNotExistException {

            Patient patient = patientRepo.findByName(name);
            if (patient==null){
                throw new PatientNotExistException("Patient with the name "+name+" does not exit");
            }

        return convertDTO(patient);
    }

    @Override
    public PatientDTO getPatientByIdentification(int identification) throws PatientNotExistException {
            Patient patient = patientRepo.findByIdentification(identification);
            validateIfExists(identification);

            return convertDTO(patient);
    }

    @Override
    public int createPetition(ItemPetitionDTO itemPetitionDTO) throws AppointmentNotRelatedException {
        return 0;
    }

    @Override
    public int cancelPetition(int code) throws PetitionNotFoundException {
        return 0;
    }

    private PatientDTO convertDTO(Patient patient) {

           return new PatientDTO(
                   patient.getName(),
                   patient.getIdentification(),
                   patient.getPhone(),
                   patient.getUrlPicture(),
                   patient.getCity(),
                   patient.getBirthday(),
                   patient.getAllergies(),
                   patient.getBloodType(),
                   patient.getEps(),
                   patient.getEmail(),
                   patient.getPassword()
           );
    }
}
