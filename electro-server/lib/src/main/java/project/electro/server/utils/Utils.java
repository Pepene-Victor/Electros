package project.electro.server.utils;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import project.electro.server.dtos.UserDto;
import project.electro.server.entities.Activity;
import project.electro.server.enums.ActivityTypeEnum;
import project.electro.server.repository.ActivityRepository;
import project.electro.server.services.UserService;

public class Utils {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private static ActivityRepository activityRepository;

	public static void createActivity(ActivityTypeEnum type, String description) throws Exception {
		
		Activity activity = new Activity();
		activity.setActivityType(type);
		activity.setCreatedDate(LocalDateTime.now());
		activity.setDescription(description);
	    try {
		 activityRepository.save(activity);
		} catch (Exception e) {
			throw new Exception("Couldn't save activity");
		}
		
	}
	
	public UserDto getLoggedUser() throws Exception {
		
		final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		if(auth == null) {
			
			throw new Exception("No user logged in");
		}
		final String username = auth.getName();
		
		return getUserByUsername(username);
	}

	private UserDto getUserByUsername(String username) {
		
		UserDto userDto = userService.findByUsername(username);
		
		return userDto;
	}
	
}
