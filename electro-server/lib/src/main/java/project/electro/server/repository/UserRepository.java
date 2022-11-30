package project.electro.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import project.electro.server.entities.User;

public interface UserRepository extends JpaRepository<User, Long>{

}
