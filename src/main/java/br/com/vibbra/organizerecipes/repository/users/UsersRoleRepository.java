package br.com.vibbra.organizerecipes.repository.users;

import br.com.vibbra.organizerecipes.model.entity.users.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRoleRepository extends JpaRepository<UserRole, Integer> {

}