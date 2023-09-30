package co.edu.uniquindio.proyecto.Repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Petition extends JpaRepository<Petition, Integer> {
}
