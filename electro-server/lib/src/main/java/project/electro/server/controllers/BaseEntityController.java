package project.electro.server.controllers;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import project.electro.server.dtos.BaseEntityDto;

public interface BaseEntityController<T extends BaseEntityDto> {

	@PostMapping("/create")
	T create(@Valid @RequestBody T entity);
	
	@PutMapping("/create")
	T update(@Valid @RequestBody T entity);
	
	@DeleteMapping("/delete/{id}")
	void delete(@NotNull @PathVariable Long id);
	
	@GetMapping("/findById/{id}")
	T findById(@PathVariable Long id);
}
