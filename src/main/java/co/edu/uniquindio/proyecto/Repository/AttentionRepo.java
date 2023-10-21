package co.edu.uniquindio.proyecto.Repository;

import co.edu.uniquindio.proyecto.Model.Attention;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttentionRepo extends JpaRepository<Attention,Integer> {
    Attention findByAppointmentCode(int code);

}
