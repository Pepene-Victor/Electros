package project.electro.server.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import project.electro.server.dtos.ActivityDto;
import project.electro.server.enums.ActivityTypeEnum;
import project.electro.server.services.ActivityService;

@SpringBootTest
@ContextConfiguration
@TestMethodOrder(MethodOrderer.DisplayName.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ActivityServiceTest {
	
	@Autowired
	private ActivityService activityService;
	
	public static final String DESCRIPTION = "created product with id 2";
	
	
	private ActivityDto persistedActivity;
	
	@Test
	public void test001CreateActivity() throws Exception {
		
		persistedActivity = activityService.createActivity(ActivityTypeEnum.CREATE, DESCRIPTION);
		Assertions.assertNotNull(persistedActivity);		
	}
	@Test
	public void test002DeleteActivity() throws Exception {
		
		activityService.delete(this.persistedActivity.getId());
		Assertions.assertFalse(activityService.existsById(this.persistedActivity.getId()));
		
	}
	
}
