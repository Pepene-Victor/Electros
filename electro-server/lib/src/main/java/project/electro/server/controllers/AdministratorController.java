package project.electro.server.controllers;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import project.electro.server.dtos.AdministratorDto;
import project.electro.server.enums.RoleEnum;

@RestController
@Secured(RoleEnum.Code.ADMINISTRATOR)
@RequestMapping("/admin")
public class AdministratorController implements BaseEntityController<AdministratorDto>{

	@Override
	public AdministratorDto create(@Valid AdministratorDto entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AdministratorDto update(@Valid AdministratorDto entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AdministratorDto findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(@NotNull Long id) {
		// TODO Auto-generated method stub
		
	}

}
