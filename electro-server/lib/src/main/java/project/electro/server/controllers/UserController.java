package project.electro.server.controllers;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import project.electro.server.dtos.UserDto;

@RestController
@RequestMapping("/user")
public class UserController implements BaseEntityController<UserDto>{

	@Override
	public UserDto create(@Valid UserDto entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserDto update(@Valid UserDto entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(@NotNull Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public UserDto findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
