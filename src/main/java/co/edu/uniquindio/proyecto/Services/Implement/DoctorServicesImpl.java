package co.edu.uniquindio.proyecto.Services.Implement;

import co.edu.uniquindio.proyecto.Dto.Admin.ScheduleDTO;
import co.edu.uniquindio.proyecto.Dto.Doctor.AppointmentDocDTO;
import co.edu.uniquindio.proyecto.Exception.AppointmentException.AppointmentsNotFoundException;
import co.edu.uniquindio.proyecto.Model.Appointment;
import co.edu.uniquindio.proyecto.Model.Enum.AppointmentState;
import co.edu.uniquindio.proyecto.Model.Schedule;
import co.edu.uniquindio.proyecto.Repository.*;
import co.edu.uniquindio.proyecto.Services.Interfaces.DoctorServices;
import co.edu.uniquindio.proyecto.Services.Interfaces.PatientServices;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DoctorServicesImpl implements DoctorServices {

    final DoctorRepo doctorRepo;
    final AppointmentRepo appointmentRepo;
    final ScheduleRepo scheduleRepo;

    final PatientRepo patientRepo;
    final AttentionRepo attentionRepo;

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
        List<Appointment> appointments = appointmentRepo.findAllByDoctorCode(docCode);

        LocalTime time = LocalTime.of(23, 59);
        LocalDateTime localDateTime = LocalDateTime.now().minusDays(1).with(time);

        LocalDateTime localDateTime2 = LocalDateTime.now().plusDays(1).with(time);

        if (appointments.isEmpty()) {
            throw new AppointmentsNotFoundException("Not appointments with the doctor code: " + docCode);
        }
        appointments.removeIf(item -> !item.getAppointmentDate().isAfter(localDateTime) && !item.getAppointmentDate().isBefore(localDateTime2));

        return convertDTOS(appointments);

    }

    private List<AppointmentDocDTO> convertDTOS(List<Appointment> appointments) {
        List<AppointmentDocDTO>answer=new ArrayList<>();
        for (Appointment item: appointments) {
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
