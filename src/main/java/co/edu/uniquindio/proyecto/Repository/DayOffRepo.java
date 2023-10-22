package co.edu.uniquindio.proyecto.Repository;

import co.edu.uniquindio.proyecto.Model.DayOff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DayOffRepo extends JpaRepository<DayOff, Integer>{
}
