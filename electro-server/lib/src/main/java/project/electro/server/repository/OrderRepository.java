package project.electro.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import project.electro.server.entities.Order;


public interface OrderRepository extends JpaRepository<Order, Long>{

}
