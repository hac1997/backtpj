package ifsc.edu.tpj.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import ifsc.edu.tpj.model.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
