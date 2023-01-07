package project.electro.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import project.electro.server.entities.Product;


public interface ProductRepository extends JpaRepository<Product, Long>{

}
