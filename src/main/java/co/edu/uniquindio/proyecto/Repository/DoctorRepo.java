package co.edu.uniquindio.proyecto.Repository;
import co.edu.uniquindio.proyecto.Model.Doctor;
import co.edu.uniquindio.proyecto.Model.Enum.DoctorState;
import co.edu.uniquindio.proyecto.Model.Enum.Specialization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorRepo extends JpaRepository<Doctor, Integer> {

    Doctor findByIdentification(int id);
    Doctor findByEmail(String email);

    List<Doctor> findAllBySpecializationAndDoctorState(Specialization specialization, DoctorState doctorState);

    Doctor findByDoctorState(DoctorState doctorState);

}
