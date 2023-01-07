package project.electro.server.controllers;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import project.electro.server.dtos.CustomerDto;
import project.electro.server.enums.RoleEnum;

@RestController
@RequestMapping("/customer")
public class CustomerController implements BaseEntityController<CustomerDto>{

	@Override
	public CustomerDto create(@Valid CustomerDto entity) {
		
		return null;
	}

	@Override
	public CustomerDto update(@Valid CustomerDto entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(@NotNull Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	@Secured(RoleEnum.Code.ADMINISTRATOR)
	public CustomerDto findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
