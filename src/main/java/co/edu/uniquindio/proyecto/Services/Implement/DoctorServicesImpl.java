package co.edu.uniquindio.proyecto.Services.Implement;

import co.edu.uniquindio.proyecto.Dto.Admin.ScheduleDTO;
import co.edu.uniquindio.proyecto.Dto.AppointmentAttentionDTO;
import co.edu.uniquindio.proyecto.Dto.DayOffDTO;
import co.edu.uniquindio.proyecto.Dto.Doctor.AppointmentDocDTO;
import co.edu.uniquindio.proyecto.Exception.AppointmentException.AppointmentsNotFoundException;
import co.edu.uniquindio.proyecto.Exception.AppointmentHasAlreadyCreatedException;
import co.edu.uniquindio.proyecto.Exception.AttentionNotAssociatedAppointmentException;
import co.edu.uniquindio.proyecto.Exception.DoctorExceptions.AppointmentNotFoundException;
import co.edu.uniquindio.proyecto.Exception.DoctorExceptions.DoctorNotFoundException;
import co.edu.uniquindio.proyecto.Exception.DoctorHasAlreadyDayOffException;
import co.edu.uniquindio.proyecto.Exception.PatientException.PatientNotFoundException;
import co.edu.uniquindio.proyecto.Model.*;
import co.edu.uniquindio.proyecto.Model.Enum.AppointmentState;
import co.edu.uniquindio.proyecto.Model.Enum.DayOffState;
import co.edu.uniquindio.proyecto.Repository.*;
import co.edu.uniquindio.proyecto.Services.Interfaces.DoctorServices;
import co.edu.uniquindio.proyecto.Services.Interfaces.PatientServices;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DoctorServicesImpl implements DoctorServices {

    final DoctorRepo doctorRepo;
    final AppointmentRepo appointmentRepo;
    final ScheduleRepo scheduleRepo;

    final PatientRepo patientRepo;
    final AttentionRepo attentionRepo;
    final DayOffRepo dayOffRepo;

    final PatientServices patientServices;



    @Override
    public List<AppointmentDocDTO> listAppointment(int docCode) throws Exception {

        return getAppointmentsDoc(docCode);

    }

    @Override
    public List<AppointmentDocDTO> listPendingAppointments(int docCode, AppointmentState appointmentState) throws Exception {

        List<AppointmentDocDTO> answer = getAppointmentsDoc(docCode);

        answer.removeIf(item -> !item.appointmentState().equals(appointmentState));

        return answer;

    }

    @Override
    public List<ScheduleDTO> listSchedules(int docCode) throws Exception {

        List<Schedule> schedules = scheduleRepo.findAllByDoctorCode(docCode);

        if (schedules.isEmpty()) {
            throw new Exception("There are not schedules associated with the doctor code: " + docCode);
        }

        List<ScheduleDTO> answer = new ArrayList<>();
        for (Schedule item : schedules) {
            answer.add(new ScheduleDTO(
                            item.getDay(),
                            item.getInitialTime(),
                            item.getFinalTime()
                    )
            );
        }

        return answer;

    }

    @Override
    public AppointmentAttentionDTO showDetailsAttention(AppointmentDocDTO appointmentDocDTO) throws PatientNotFoundException, AppointmentNotFoundException, AttentionNotAssociatedAppointmentException {

        Optional<Appointment> optional = appointmentRepo.findById(appointmentDocDTO.codeAppointment());
        if (optional.isEmpty()){
            throw new AppointmentNotFoundException("There is no appointment associated with the appointment code: "+appointmentDocDTO.codeAppointment());
        }
        Appointment appointment = optional.get();

        Attention attention = attentionRepo.findByAppointmentCode(appointment.getCode());
        if (attention == null){
            throw new AttentionNotAssociatedAppointmentException("There is not attention associated with the appointment code: "+appointment.getCode());
        }

        return new AppointmentAttentionDTO(
                appointment.getCode(),
                attention.getMedicalNotes(),
                attention.getTreatment(),
                attention.getDiagnosis()
        );

    }

    @Override
    public List<AppointmentDocDTO> showAppointmentHistoryPatient(int patientCode) throws AppointmentsNotFoundException {

        List<Appointment>patientHistory= appointmentRepo.findAllByPatientCode(patientCode);

        if (patientHistory.isEmpty()){
            throw new AppointmentsNotFoundException("There is not appointments with the patient code: "+patientCode);
        }

        return convert(patientHistory);
    }

    @Override
    public List<AppointmentDocDTO> listFinishedAndCancelledAppointments(int docCode)
            throws AppointmentsNotFoundException {

        List<Appointment> appointmentsDoc = appointmentRepo.findAllByDoctorCode(docCode);
        if (appointmentsDoc.isEmpty()) {
            throw new AppointmentsNotFoundException("There are not appointments associated with the doctor code: " +
                    docCode);
        }

        List<AppointmentDocDTO> answer = new ArrayList<>();

        for (Appointment item : appointmentsDoc) {
            if (item.getAppointmentState().equals(AppointmentState.FINISHED) ||
                    item.getAppointmentState().equals(AppointmentState.CANCELLED)) {
                answer.add(convertDTO(item));
            }
        }

        return answer;

    }

    @Override
    public List<AppointmentDocDTO> listAppointmentsActualDay(int docCode) throws AppointmentsNotFoundException {

        List<Appointment> appointments = getAppointmentByDoctor(docCode);

        LocalTime time = LocalTime.of(23, 59);
        LocalTime time2 = LocalTime.of(0, 0);
        LocalDateTime yesterday = LocalDateTime.now().minusDays(1).with(time);
        LocalDateTime today = LocalDateTime.now().plusDays(1).with(time2);


        appointments.removeIf(item -> item.getAppointmentDate().isBefore(yesterday) || item.getAppointmentDate().isAfter(today));
        if (appointments.isEmpty()) {
            throw new AppointmentsNotFoundException("Not appointments with the doctor code: " + docCode);
        }
        return convertDTOS(appointments);

    }


    private List<Appointment> getAppointmentByDoctor(int code) throws AppointmentsNotFoundException {

        List<Appointment> appointments = appointmentRepo.findAllByDoctorCode(code);

        if (appointments.isEmpty()) {
            throw new AppointmentsNotFoundException("Not appointments with the doctor code: " + code);
        }

        return appointments;
    }


    @Override
    public List<AppointmentDocDTO> listFutureAppointments(int docCode) throws AppointmentsNotFoundException {

        List<Appointment> appointments = getAppointmentByDoctor(docCode);

        LocalTime time = LocalTime.of(23, 59);
        LocalDateTime localDateTime = LocalDateTime.now().with(time);

        appointments.removeIf(item -> item.getAppointmentDate().isBefore(localDateTime));

        return convertDTOS(appointments);

    }

    @Override
    public int attendAppointment(AppointmentAttentionDTO appointmentAttentionDTO) throws Exception {

        Optional<Appointment> optional = appointmentRepo.findById(appointmentAttentionDTO.appointmentCode());

        if (optional.isEmpty()) {
            throw new AppointmentNotFoundException("There is not appointment with the code: "
                    + appointmentAttentionDTO.appointmentCode());
        }

        Appointment appointment = optional.get();

        appointment.setAppointmentState(AppointmentState.FINISHED);
        Attention attention = attentionRepo.findByAppointmentCode(appointment.getCode());

        if (attention == null) {
            throw new Exception("There is not attention associated with the appointment code: "+ appointment.getCode());
        }

        attention.setAppointment(appointment);
        attention.setDiagnosis(appointmentAttentionDTO.diagnosis());
        attention.setTreatment(appointmentAttentionDTO.treatment());
        attention.setMedicalNotes(appointmentAttentionDTO.medicalNotes());

        attentionRepo.save(attention);

        return attention.getCode();

    }

    @Override
    public int assignDayOff(DayOffDTO dayOffDTO)
            throws AppointmentsNotFoundException, AppointmentHasAlreadyCreatedException,
            DoctorNotFoundException, DoctorHasAlreadyDayOffException {

        List<Appointment> appointments = appointmentRepo.findAllByDoctorCode(dayOffDTO.doctorCode());

        if (!appointments.isEmpty()) {
            for (Appointment item : appointments) {
                if (dayOffDTO.day().toLocalDate().equals(item.getAppointmentDate().toLocalDate())) {
                    throw new AppointmentHasAlreadyCreatedException("There is appointment with that day");
                }
            }
        }

        Optional<Doctor> optional = doctorRepo.findById(dayOffDTO.doctorCode());

        if(optional.isEmpty()){
            throw new DoctorNotFoundException("");
        }

        Doctor doctor = optional.get();

        if (doctor.getFreeDayList().isEmpty()){
            List<DayOff> dayOffs = new ArrayList<>();
            LocalDateTime now = LocalDateTime.now();

            String code = now.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
            dayOffs.add(new DayOff(
                    code,
                    dayOffDTO.day().toLocalDate(),
                    doctor,
                    DayOffState.AVAILABLE
            ));
        }else {
            for (DayOff dayOff:doctor.getFreeDayList()) {
                if(dayOff.getDayOffState().equals(DayOffState.AVAILABLE)){
                    throw new DoctorHasAlreadyDayOffException("");
                }
            }
        }

        return 1;

    }

    private List<AppointmentDocDTO> convertDTOS(List<Appointment> appointments) {

        List<AppointmentDocDTO> answer = new ArrayList<>();

        for (Appointment item : appointments) {
            answer.add(new AppointmentDocDTO(
                    item.getCode(),
                    item.getAppointmentState(),
                    item.getPatient().getName(),
                    item.getPatient().getIdentification(),
                    item.getAppointmentDate()

            ));
        }

        return answer;

    }

    private AppointmentDocDTO convertDTO(Appointment item) {
        return new AppointmentDocDTO(
                item.getCode(),
                item.getAppointmentState(),
                item.getPatient().getName(),
                item.getPatient().getIdentification(),
                item.getAppointmentDate()
        );
    }

    private List<AppointmentDocDTO> getAppointmentsDoc(int docCode) throws Exception {

        List<Appointment> appointments = appointmentRepo.findAllByDoctorCode(docCode);

        if (appointments.isEmpty()) {
            throw new Exception("There are not appointment associated with the doctor code: " + docCode);
        }
        return convert(appointments);
    }

    private List<AppointmentDocDTO> convert(List<Appointment> appointments) {
        List<AppointmentDocDTO> appointmentsDocDTO = new ArrayList<>();
        for (Appointment item : appointments) {
            AppointmentDocDTO appointmentDocDTO = new AppointmentDocDTO(
                    item.getCode(),
                    item.getAppointmentState(),
                    item.getPatient().getName(),
                    item.getPatient().getIdentification(),
                    item.getAppointmentDate());
            appointmentsDocDTO.add(appointmentDocDTO);
        }

        return appointmentsDocDTO;

    }


}
