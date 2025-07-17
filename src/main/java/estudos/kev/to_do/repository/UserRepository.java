package estudos.kev.to_do.repository;

import estudos.kev.to_do.model.entitie.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository <User, Long > {
    UserDetails findByLogin(String login);
}
