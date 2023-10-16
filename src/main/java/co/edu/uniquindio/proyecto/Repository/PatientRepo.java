package co.edu.uniquindio.proyecto.Repository;

import co.edu.uniquindio.proyecto.Model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientRepo extends JpaRepository<Patient, Integer> {


    List<Patient> findAllByCode(int code);

    Patient findByIdentification(int identification);

    Patient findByEmail(String email);

}
