package co.edu.uniquindio.proyecto.Services.Interfaces;

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



    //Es mejor hacer los servicios por rol
}
