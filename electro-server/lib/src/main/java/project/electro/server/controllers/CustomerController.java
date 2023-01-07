package project.electro.server.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import project.electro.server.dtos.CustomerDto;
import project.electro.server.services.CustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerController implements BaseUserEntityController<CustomerDto>{

	@Autowired
	private CustomerService customerService;
	
	@Override
	public CustomerDto create(@Valid CustomerDto entity, String username) throws Exception {
		// TODO Auto-generated method stub
		return customerService.create(entity, username);
	}

	@Override
	public CustomerDto update(@Valid CustomerDto entity) throws Exception {
		// TODO Auto-generated method stub
		return customerService.update(entity);
	}

	@Override
	public CustomerDto findByUser(String username) {
		// TODO Auto-generated method stub
		return customerService.findByUser(username);
	}

}
