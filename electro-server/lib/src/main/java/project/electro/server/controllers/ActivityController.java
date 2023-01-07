package project.electro.server.controllers;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import project.electro.server.dtos.ActivityDto;
import project.electro.server.enums.RoleEnum;
import project.electro.server.services.ActivityService;


@RestController
@RequestMapping("/activities")
@Secured(RoleEnum.Code.ADMINISTRATOR)
public class ActivityController extends BaseEntityController<ActivityDto>{

	@Autowired
	private ActivityService activityService;
	
	@Override
	void delete(@NotNull Long id) throws Exception {
		
		activityService.delete(id);
	}

	@Override
	public List<ActivityDto> getAll() {
		
		return activityService.getAllActivities();
	}


}
