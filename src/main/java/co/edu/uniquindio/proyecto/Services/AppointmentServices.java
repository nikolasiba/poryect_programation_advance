package co.edu.uniquindio.proyecto.Services;

public interface AppointmentServices {
    void createAppointment();
    void cancelarAppointment();
    void listAppointmentPatient();
    void showDetailsAppointment();
    void filterAppointmentPerDoctor();
    void filterAppointmentPerDate();
    void listAppointmentPendientes();
    void atenderCita();
    void listAppointmentDoctor();
}
