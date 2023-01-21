package project.electro.server.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import project.electro.server.dtos.ActivityDto;
import project.electro.server.entities.Activity;
import project.electro.server.enums.ActivityTypeEnum;
import project.electro.server.repository.ActivityRepository;

@Service
@Transactional
public class ActivityService {
	
	@Autowired
	private ActivityRepository activityRepository;
	
	private static final Logger LOGGER = Logger.getLogger(ActivityService.class.getName());

	public List<ActivityDto> getAllActivities(Integer pageNumber, Integer pageSize){
		
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		
		Page<Activity> pageActivities = activityRepository.findAll(pageable);
		List<Activity> activities = pageActivities.getContent();
		List<ActivityDto> activitiesDto = new ArrayList<ActivityDto>();
		if(activities.size() != 0) {
			activities.forEach(activity -> {
				activitiesDto.add(convertToActivityDto(activity));
			});
			
		}
		return activitiesDto;
	}
	
	public void delete(Long id) {
		
		activityRepository.deleteById(id);
		Optional<Activity> activity = activityRepository.findById(id);
		if (activityRepository.existsById(activity.get().getId()) == false) {
			
			LOGGER.info("Delete was successful");
		}
		else
			LOGGER.warning("Delete has failed for activity with id: " + activity.get().getId());
		
	}
	public void createActivity(ActivityTypeEnum type, String description) throws Exception {
		
		Activity activity = new Activity();
		activity.setActivityType(type);
		activity.setCreatedDate(LocalDateTime.now());
		activity.setDescription(description);
		activityRepository.save(activity);
		
	}

	private ActivityDto convertToActivityDto(Activity activity) {
		ActivityDto activityDto = new ActivityDto();
		activityDto.setActivityType(activity.getActivityType());
		activityDto.setCreatedDate(activity.getCreatedDate());
		activityDto.setDescription(activity.getDescription());
		activityDto.setId(activity.getId());
		return activityDto;
	}
}
