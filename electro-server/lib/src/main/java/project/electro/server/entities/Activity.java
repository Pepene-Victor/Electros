package project.electro.server.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import project.electro.server.enums.ActivityTypeEnum;

@Entity
@Table(name = "activities")
public class Activity extends BaseEntity{

	@Column(name = "description")
	private String description;
	
	@Column(name = "activity_type")
	@Enumerated(value = EnumType.STRING)
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
