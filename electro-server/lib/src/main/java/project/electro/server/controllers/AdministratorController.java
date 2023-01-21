package project.electro.server.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import project.electro.server.dtos.AdministratorDto;
import project.electro.server.enums.RoleEnum;
import project.electro.server.services.AdministratorService;

@RestController
@Secured(RoleEnum.Code.ADMINISTRATOR)
@RequestMapping("/admin")
public class AdministratorController implements BaseUserEntityController<AdministratorDto>{

	@Autowired
	private AdministratorService administratorService;
	
	@Override
	public AdministratorDto create(@Valid @RequestBody AdministratorDto entity, @RequestParam String username) throws Exception {
		
		return administratorService.create(entity, username);
	}

	@Override
	public AdministratorDto update(@Valid @RequestBody AdministratorDto entity) throws Exception {
		
		return  administratorService.update(entity);
	}

	@Override
	public AdministratorDto findByUser(String username) {
		
		return administratorService.findByUser(username);
	}



}
