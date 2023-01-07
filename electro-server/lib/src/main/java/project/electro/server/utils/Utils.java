package project.electro.server.utils;

import java.time.LocalDateTime;

import project.electro.server.entities.Activity;
import project.electro.server.enums.ActivityTypeEnum;

public class Utils {

	public static Activity createActivity(ActivityTypeEnum type, String description) {
		
		Activity activity = new Activity();
		activity.setActivityType(type);
		activity.setCreatedDate(LocalDateTime.now());
		activity.setDescription(description);
		
		return activity;
		
	}
	
}
