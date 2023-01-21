package project.electro.server.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import project.electro.server.dtos.CustomerDto;
import project.electro.server.services.CustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerController implements BaseUserEntityController<CustomerDto>{

	@Autowired
	private CustomerService customerService;
	
	@Override
	public CustomerDto create(@Valid @RequestBody CustomerDto entity, @RequestParam String username) throws Exception {
		// TODO Auto-generated method stub
		return customerService.create(entity, username);
	}

	@Override
	public CustomerDto update(@Valid @RequestBody CustomerDto entity) throws Exception {
		// TODO Auto-generated method stub
		return customerService.update(entity);
	}

	@Override
	public CustomerDto findByUser(@RequestParam String username) {
		// TODO Auto-generated method stub
		return customerService.findByUser(username);
	}

}
