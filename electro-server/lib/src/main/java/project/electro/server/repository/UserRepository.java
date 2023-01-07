package project.electro.server.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import project.electro.server.entities.User;

public interface UserRepository extends JpaRepository<User, Long>{

	Optional<User> findByUsername(String username);
	
	boolean existsByUsername(String username);
}
