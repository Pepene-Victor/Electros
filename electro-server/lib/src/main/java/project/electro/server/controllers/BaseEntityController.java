package project.electro.server.controllers;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import project.electro.server.dtos.BaseEntityDto;

public abstract class BaseEntityController<T extends BaseEntityDto> {

	@PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	T create(@Valid @RequestBody T entity) throws Exception {
		return null;
	}
	
	@PutMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	T update(@Valid @RequestBody T entity) throws Exception {
		return null;
	}
	
	@DeleteMapping(value = "/delete/{id}")
	void delete(@NotNull @PathVariable Long id) throws Exception {
	}
	
	@GetMapping("/find-by-id/{id}")
	T findById(@PathVariable Long id) {
		return null;
	}
	
	@GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<T> getAll() {
		return null;
	}
}
