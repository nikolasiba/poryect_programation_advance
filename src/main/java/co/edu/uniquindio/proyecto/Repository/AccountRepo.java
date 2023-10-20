package co.edu.uniquindio.proyecto.Repository;

import co.edu.uniquindio.proyecto.Model.Account;
import co.edu.uniquindio.proyecto.Model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepo extends JpaRepository<Account,Integer> {
    Account findByCode(int code);

}
