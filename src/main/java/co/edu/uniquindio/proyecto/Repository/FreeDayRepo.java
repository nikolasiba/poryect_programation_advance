package co.edu.uniquindio.proyecto.Repository;

import co.edu.uniquindio.proyecto.Model.FreeDay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FreeDayRepo extends JpaRepository<FreeDay, Integer>{
}
