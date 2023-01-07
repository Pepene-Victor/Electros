package project.electro.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import project.electro.server.entities.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long>{

	Customer findByUserId(Long id);

}
