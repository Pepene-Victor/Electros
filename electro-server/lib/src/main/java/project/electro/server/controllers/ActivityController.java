package project.electro.server.controllers;

import java.util.List;


import org.springframework.http.MediaType;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import project.electro.server.dtos.ActivityDto;
import project.electro.server.enums.RoleEnum;


@RestController
@RequestMapping("/activities")
@Secured(RoleEnum.Code.ADMINISTRATOR)
public class ActivityController {

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<ActivityDto> getAll(){
		return null;
	}

}
