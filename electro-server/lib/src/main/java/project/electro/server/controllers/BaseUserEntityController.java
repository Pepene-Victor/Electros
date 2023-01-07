package project.electro.server.controllers;

import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import project.electro.server.dtos.BaseUserEntityDto;

public interface BaseUserEntityController<T extends BaseUserEntityDto> {

	@PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	T create(@Valid @RequestBody T entity, @RequestParam String username) throws Exception;
	
	@PutMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	T update(@Valid @RequestBody T entity) throws Exception;
	
	@GetMapping(value = "/find-by-username", produces = MediaType.APPLICATION_JSON_VALUE)
	T findByUser(@RequestParam String username);
	
}
