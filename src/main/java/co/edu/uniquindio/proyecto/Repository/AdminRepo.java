package co.edu.uniquindio.proyecto.Repository;

import co.edu.uniquindio.proyecto.Model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepo extends JpaRepository<Admin,Integer> {
}
