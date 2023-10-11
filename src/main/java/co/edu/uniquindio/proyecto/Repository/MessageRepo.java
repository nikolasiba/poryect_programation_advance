package co.edu.uniquindio.proyecto.Repository;

import co.edu.uniquindio.proyecto.Model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepo extends JpaRepository<Message, Integer> {
}
