package co.edu.uniquindio.proyecto.Repository;

import co.edu.uniquindio.proyecto.Model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepo extends JpaRepository<Account,Integer> {
    Account findByCode(int code);
    Optional<Account> findByEmail(String email);

}
