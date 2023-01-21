package project.electro.server.controllers;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	void delete(@NotNull @PathVariable Long id) throws Exception {
		
		activityService.delete(id);
	}

	@Secured(RoleEnum.Code.ADMINISTRATOR)
	@Override
	public List<ActivityDto> getAll(@RequestParam Integer pageNumber, @RequestParam Integer pageSize) {
		
		return activityService.getAllActivities(pageNumber, pageSize);
	}




}
