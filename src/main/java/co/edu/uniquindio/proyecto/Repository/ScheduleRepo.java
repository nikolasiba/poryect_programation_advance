package co.edu.uniquindio.proyecto.Repository;

import co.edu.uniquindio.proyecto.Model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleRepo extends JpaRepository<Schedule, Integer> {
    List<Schedule> findAllByDoctorCode(int code);

}
