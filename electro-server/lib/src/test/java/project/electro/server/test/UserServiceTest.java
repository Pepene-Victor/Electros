package project.electro.server.test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import project.electro.server.dtos.UserDto;
import project.electro.server.enums.PasswordStatusEnum;
import project.electro.server.enums.RoleEnum;
import project.electro.server.services.UserService;

@SpringBootTest
@ContextConfiguration
@TestMethodOrder(MethodOrderer.DisplayName.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserServiceTest {

	public static final String EMAIL = "user45@gmail.com";
	public static final String PASSWORD = "victorA@2";
	
	public static final String EMAIL_2 = "2000@gmail.com";
	public static final String PASSWORD_2 = "victorA@2fffX";
	
	@Autowired
	private UserService userService;
	
	private UserDto persistedUser;
	private Long id;
	
	@Test
	public void test001CreateUser() throws  Exception {
		UserDto userDto = new UserDto();
		userDto.setUsername(EMAIL);
		userDto.setPassword(PASSWORD);
		userDto.setRole(RoleEnum.ADMINISTRATOR);
		persistedUser = userService.register(userDto);
		updateUser();
		assertNotNull(persistedUser);
		
	}
	public void updateUser() {
		
		id = persistedUser.getId();
		
	}
	
	@Test
	public void test002UpdateUserPasswordTest() throws Exception {
		persistedUser.setPassword(PASSWORD_2);
		persistedUser = userService.updateUserPassword(persistedUser);
		Assertions.assertEquals(PasswordStatusEnum.NEW, persistedUser.getPasswordStatus());
		updateUser();
	}
	
	@Test
	public void test003UpdateUserEmailTest() throws Exception {
		persistedUser.setUsername(EMAIL_2);
		persistedUser = userService.updateUserName(persistedUser);
		Assertions.assertEquals(EMAIL_2, persistedUser.getUsername());
		updateUser();
	}
	
	@Test
	public void test004DeleteUserByIdTest() throws Exception {
		
		userService.deleteUser(id);
		Assertions.assertNull(userService.findByUsername(EMAIL_2));
		
	}
	
	

}
