package project.electro.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import project.electro.server.entities.Administrator;


public interface AdministratorRepository extends JpaRepository<Administrator, Long>{

}
