package project.electro.server.controllers;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import project.electro.server.dtos.UserDto;
import project.electro.server.enums.RoleEnum;
import project.electro.server.services.UserService;

@RestController
@RequestMapping("/user")
public class UserController extends BaseEntityController<UserDto>{

	@Autowired
	private UserService service;
	
	@PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	UserDto register(@Valid UserDto entity) throws Exception {
		
		return service.register(entity);
	}

	@Override
	void delete(@NotNull Long id) throws Exception {
		
		service.deleteUser(id);
	}

	@Override
	@Secured(RoleEnum.Code.ADMINISTRATOR)
	public List<UserDto> getAll() {
		
		return service.getAllUsers();
	}
	@PutMapping(value = "/update/username", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public UserDto updateUsername(UserDto userDto) throws Exception {
		
		return service.updateUserName(userDto);
	}
	@PutMapping(value = "/update/password", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public UserDto updatePassword(UserDto userDto) throws Exception {
		
		return service.updateUserPassword(userDto);
	}


}
