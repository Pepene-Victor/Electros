package project.electro.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import project.electro.server.entities.Activity;

public interface ActivityRepository extends JpaRepository<Activity, Long>{

}
