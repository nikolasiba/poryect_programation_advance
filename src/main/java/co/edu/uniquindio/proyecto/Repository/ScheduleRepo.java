package co.edu.uniquindio.proyecto.Repository;

import co.edu.uniquindio.proyecto.Model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleRepo extends JpaRepository<Schedule, Integer> {
}
