package project.electro.server.dtos;

import javax.validation.constraints.NotNull;

import project.electro.server.enums.ActivityTypeEnum;

public class ActivityDto extends BaseEntityDto{

	private String description;
	
	@NotNull
	private ActivityTypeEnum activityType;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ActivityTypeEnum getActivityType() {
		return activityType;
	}

	public void setActivityType(ActivityTypeEnum activityType) {
		this.activityType = activityType;
	}
	
}
