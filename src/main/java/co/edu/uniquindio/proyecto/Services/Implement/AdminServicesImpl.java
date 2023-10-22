package co.edu.uniquindio.proyecto.Services.Implement;

import co.edu.uniquindio.proyecto.Dto.Admin.*;
import co.edu.uniquindio.proyecto.Dto.Petition.AnswerDTO;
import co.edu.uniquindio.proyecto.Dto.Petition.ItemPetitionDTO;
import co.edu.uniquindio.proyecto.Dto.Petition.PetitionDetailDTO;
import co.edu.uniquindio.proyecto.Dto.Petition.RespLogDTO;
import co.edu.uniquindio.proyecto.Exception.AppointmentException.AccountNotFoundException;
import co.edu.uniquindio.proyecto.Exception.AppointmentException.AppointmentsNotFoundException;
import co.edu.uniquindio.proyecto.Exception.DoctorExceptions.DoctorNotFoundException;
import co.edu.uniquindio.proyecto.Exception.DoctorExceptions.DoctorWithEmailRepeatedException;
import co.edu.uniquindio.proyecto.Exception.DoctorExceptions.DoctorWithIdRepeatedException;
import co.edu.uniquindio.proyecto.Exception.DoctorExceptions.DoctorsNotFoundException;
import co.edu.uniquindio.proyecto.Exception.PetitionExceptions.PetitionNotFoundException;
import co.edu.uniquindio.proyecto.Exception.PetitionExceptions.PetitionsNotFoundException;
import co.edu.uniquindio.proyecto.Model.*;
import co.edu.uniquindio.proyecto.Model.Enum.DoctorState;
import co.edu.uniquindio.proyecto.Model.Enum.PetitionState;
import co.edu.uniquindio.proyecto.Model.Enum.ScheduleState;
import co.edu.uniquindio.proyecto.Repository.*;
import co.edu.uniquindio.proyecto.Services.Interfaces.AdminServices;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdminServicesImpl implements AdminServices {

    private final DoctorRepo doctorRepo;
    private final PetitionRepo petitionRepo;
    private final AccountRepo accountRepo;
    private final MessageRepo messageRepo;
    private final AppointmentRepo appointmentRepo;
    private final ScheduleRepo scheduleRepo;

    @Override
    public int createDoctor(DoctorRecordDTO doctorRecordDTO) throws DoctorWithIdRepeatedException, DoctorWithEmailRepeatedException {

        if( validateRepeatedId(doctorRecordDTO.identification()) ){
            throw new DoctorWithIdRepeatedException("The id "+doctorRecordDTO.identification()+" has been already used");
        }

        if( validateRepeatedEmail(doctorRecordDTO.email()) ){
            throw new DoctorWithEmailRepeatedException("The email "+doctorRecordDTO.email()+" has been already used");
        }

        Doctor d = new Doctor();

        d.setIdentification(doctorRecordDTO.identification());
        d.setPhone(doctorRecordDTO.phone());
        d.setName(doctorRecordDTO.name());
        d.setSpecialization( doctorRecordDTO.specialization());
        d.setCity(doctorRecordDTO.city());
        d.setEmail(doctorRecordDTO.email());
        d.setPassword(doctorRecordDTO.password());
        d.setUrlPicture(doctorRecordDTO.urlPicture());
        d.setDoctorState(DoctorState.AVAILABLE);

        Doctor newDoctor = doctorRepo.save(d);

        assignSchedules( newDoctor, doctorRecordDTO.schedules() );

        return newDoctor.getCode();

    }

    private void assignSchedules(Doctor newDoctor, List<ScheduleDTO> schedules) {

        for( ScheduleDTO s : schedules ){

            Schedule sd = new Schedule();
            sd.setDay(s.day());
            sd.setInitialTime( s.initialTime());
            sd.setFinalTime( s.finalTime() );
            sd.setDoctor( newDoctor );
            sd.setScheduleState(ScheduleState.AVAILABLE);

            scheduleRepo.save(sd);

        }

    }

    private boolean validateRepeatedEmail(String email) {
        return doctorRepo.findByEmail(email) != null;
    }
    private boolean validateRepeatedId(int identification) {
        return doctorRepo.findByIdentification(identification) != null;
    }

    @Override
    public int updateDoctor(DoctorDetailDTO doctorDTO) throws DoctorNotFoundException {

        Optional<Doctor> optional = doctorRepo.findById(doctorDTO.code());

        if( optional.isEmpty() ){
            throw new DoctorNotFoundException("There is not doctor with the code "+doctorDTO.code());
        }

        Doctor wanted = optional.get();

        wanted.setIdentification(doctorDTO.identification());
        wanted.setPhone(doctorDTO.phone());
        wanted.setName(doctorDTO.name());
        wanted.setSpecialization(doctorDTO.specialization());
        wanted.setCity(doctorDTO.city());
        wanted.setEmail(doctorDTO.email());
        wanted.setUrlPicture(doctorDTO.urlPicture());

        doctorRepo.save( wanted );

        return wanted.getCode();

    }

    @Override
    public void deleteDoctor(int code) throws DoctorNotFoundException {

        Optional<Doctor> optional =doctorRepo.findById(code);

        if( optional.isEmpty() ){
            throw new DoctorNotFoundException("There is not doctor with the code "+code);
        }

        Doctor wanted = optional.get();
        wanted.setDoctorState(DoctorState.INACTIVE);

        doctorRepo.save( wanted );

        //medicoRepo.delete(buscado);

    }

    @Override
    public List<ItemDoctorDTO> ListDoctors() throws DoctorsNotFoundException {

        List<Doctor> doctors = doctorRepo.findAll();

        if(doctors.isEmpty()){
            throw new DoctorsNotFoundException("There are not doctors registered");
        }

        List<ItemDoctorDTO> answer = new ArrayList<>();

        for(Doctor d: doctors){
            answer.add( new ItemDoctorDTO(
                    d.getIdentification(),
                    d.getName(),
                    d.getUrlPicture(),
                    d.getSpecialization()) );
        }

        /*List<ItemMedicoDTO> respuesta = medicos.stream().map( m -> new ItemMedicoDTO(
                m.getCodigo(),
                m.getCedula(),
                m.getNombre(),
                m.getUrlFoto(),
                m.getEspecialidad()
        ) ).toList();*/

        return answer;

    }

    @Override
    public DoctorDetailDTO getDoctor(int code) throws DoctorNotFoundException {

        Optional<Doctor> optional = doctorRepo.findById(code);

        if( optional.isEmpty() ){
            throw new DoctorNotFoundException("There is not doctor with the code "+code);
        }

        Doctor wanted = optional.get();

        List<Schedule> schedules = scheduleRepo.findAllByDoctorCode(code);
        List<ScheduleDTO> schedulesDTO = new ArrayList<>();

        for( Schedule s : schedules ){
            schedulesDTO.add( new ScheduleDTO(
                    s.getDay(),
                    s.getInitialTime(),
                    s.getFinalTime()
            ) );
        }

        return new DoctorDetailDTO(
                wanted.getCode(),
                wanted.getName(),
                wanted.getIdentification(),
                wanted.getPhone(),
                wanted.getUrlPicture(),
                wanted.getCity(),
                wanted.getSpecialization(),
                schedulesDTO,
                wanted.getEmail()
        );

    }

    @Override
    public List<ItemPetitionDTO> listPetition() throws PetitionsNotFoundException {

        List<Petition> petitionList = petitionRepo.findAll();

        if (petitionList.isEmpty()){
            throw new PetitionsNotFoundException("There are not petition registered");
        }

        List<ItemPetitionDTO> answer = new ArrayList<>();

        for( Petition p: petitionList ){

            answer.add( new ItemPetitionDTO(

                    p.getPetitionState(),
                    p.getReason(),
                    p.getCreatedDate(),
                    p.getAppointment().getPatient().getName()));

        }

        return answer;

    }

    @Override
    public PetitionDetailDTO showDetailPetition(int code) throws PetitionNotFoundException {

        Optional<Petition> optional = petitionRepo.findById(code);

        if(optional.isEmpty()){
            throw new PetitionNotFoundException("There is not petition with the code: "+code);
        }

        Petition wanted = optional.get();
        List<Message> messages = messageRepo.findAllByPetitionCode(code);

        return new PetitionDetailDTO(
                wanted.getCode(),
                wanted.getPetitionState(),
                wanted.getReason(),
                wanted.getAppointment().getPatient().getName(),
                wanted.getAppointment().getDoctor().getName(),
                wanted.getAppointment().getDoctor().getSpecialization(),
                wanted.getCreatedDate(),

                convertAnswerDTO(messages)

        );

    }

    private List<AnswerDTO> convertAnswerDTO(List<Message> messages) {
        return messages.stream().map(m -> new AnswerDTO(
                m.getCode(),
                m.getMessage(),
                m.getAccount().getEmail(),
                m.getCreatedDate()
        )).toList();
    }

    @Override
    public int respondPetition(RespLogDTO respLogDTO) throws PetitionNotFoundException, AccountNotFoundException {

        Optional<Petition> optionalPetition = petitionRepo.findById(respLogDTO.petitionCode());

        if(optionalPetition.isEmpty()){
            throw new PetitionNotFoundException("There is not petition with the code: "+respLogDTO.petitionCode());
        }

        Optional<Account> optionalAccount = accountRepo.findById(respLogDTO.accountCode());

        if(optionalAccount.isEmpty()){
            throw new AccountNotFoundException("There is not account with the code  "+respLogDTO.accountCode());
        }

        Message newMessage = new Message();

        newMessage.setPetition(optionalPetition.get());
        newMessage.setCreatedDate( LocalDateTime.now() );
        newMessage.setAccount(optionalAccount.get());
        newMessage.setMessage(respLogDTO.message());

        Message answer = messageRepo.save(newMessage);

        return answer.getCode();

    }

    @Override
    public void changePetitionState(int petitionCode, PetitionState petitionState) throws PetitionNotFoundException {

        Optional<Petition> optional = petitionRepo.findById(petitionCode);

        if( optional.isEmpty() ){
            throw new PetitionNotFoundException("There is not petition with the code: "+petitionCode);
        }

        Petition petition = optional.get();
        petition.setPetitionState( petitionState );

        petitionRepo.save( petition );

    }

    @Override
    public List<ItemAppointmentAdminDTO> listAppointment() throws AppointmentsNotFoundException {

        List<Appointment> appointments = appointmentRepo.findAll();
        List<ItemAppointmentAdminDTO> answer = new ArrayList<>();

        if(appointments.isEmpty()){
            throw new AppointmentsNotFoundException("There are not appointments created");
        }

        for( Appointment a : appointments ){
            answer.add( new ItemAppointmentAdminDTO(

                    a.getCode(),
                    a.getPatient().getIdentification(),
                    a.getPatient().getName(),
                    a.getDoctor().getName(),
                    a.getDoctor().getSpecialization(),
                    a.getAppointmentState(),
                    a.getAppointmentDate()

            ) );
        }

        return answer;
    }




}
