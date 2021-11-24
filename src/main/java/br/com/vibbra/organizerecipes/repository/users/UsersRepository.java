package br.com.vibbra.organizerecipes.repository.users;

import br.com.vibbra.organizerecipes.model.entity.users.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {

    @Transactional(readOnly = true)
    Optional<Users> findByEmail(String email);

    @Transactional(readOnly = true)
    Boolean existsByEmail(String email);
}