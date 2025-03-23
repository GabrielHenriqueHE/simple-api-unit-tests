package io.github.gabrielhenriquehe.simpleapi.business.repositories;

import io.github.gabrielhenriquehe.simpleapi.infrastructure.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
