package co.edu.uniquindio.proyecto.Repository;

import co.edu.uniquindio.proyecto.Model.Petition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PetitionRepo extends JpaRepository<Petition, Integer> {
}
