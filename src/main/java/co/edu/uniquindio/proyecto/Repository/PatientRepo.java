package co.edu.uniquindio.proyecto.Repository;

import co.edu.uniquindio.proyecto.Model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepo extends JpaRepository<Patient, Integer> {

    Patient findByIdentification(int identification);
    Patient findByEmail(String email);
    Patient findByName(String name);

}
