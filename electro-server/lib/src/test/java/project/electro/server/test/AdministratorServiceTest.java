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

import project.electro.server.dtos.AdministratorDto;
import project.electro.server.dtos.UserDto;
import project.electro.server.enums.PasswordStatusEnum;
import project.electro.server.enums.RoleEnum;
import project.electro.server.enums.StatusEnum;
import project.electro.server.services.AdministratorService;
import project.electro.server.services.UserService;

@SpringBootTest
@ContextConfiguration
@TestMethodOrder(MethodOrderer.DisplayName.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AdministratorServiceTest {

	public static final String USERNAME = "user@test.com";
	public static final String FIRSTNAME = "firstname1";
	public static final String LASTNAME = "lastname1";
	public static final String COMPANY = "Test";


	public static final String FIRSTNAME_2 = "firstname2";
	public static final String LASTNAME_2 = "lastname2";
	public static final String COMPANY_2 = "Test2";

	
	@Autowired
	private AdministratorService adminService;
	@Autowired
	private UserService userService;
	
	
	private AdministratorDto persistedAdmin;
	private Long id;
	
	@Test
	public void test001Create() throws  Exception {
		
		UserDto user = new UserDto();
		user.setUsername(USERNAME);
		user.setRole(RoleEnum.CUSTOMER);
		user.setPassword("password");
		user.setPasswordStatus(PasswordStatusEnum.OK);
		userService.register(user);
		
		AdministratorDto customerDto = new AdministratorDto();
		customerDto.setLastName(LASTNAME);
		customerDto.setFirstName(FIRSTNAME);
		customerDto.setStatus(StatusEnum.FREE);
		customerDto.setCompany(COMPANY);
		
		persistedAdmin = adminService.create(customerDto, USERNAME);
		this.id = persistedAdmin.getId();
		assertNotNull(persistedAdmin);
		
	}

	
	@Test
	public void test002Update() throws Exception {

		persistedAdmin.setFirstName(FIRSTNAME_2);
		persistedAdmin.setLastName(LASTNAME_2);
		persistedAdmin.setStatus(StatusEnum.PENDING);
		persistedAdmin.setCompany(COMPANY_2);
		persistedAdmin = adminService.update(persistedAdmin);
		Assertions.assertEquals(FIRSTNAME_2, persistedAdmin.getFirstName());
		Assertions.assertEquals(LASTNAME_2, persistedAdmin.getLastName());
		Assertions.assertEquals(StatusEnum.PENDING, persistedAdmin.getStatus());
		Assertions.assertEquals(COMPANY_2, persistedAdmin.getCompany());

	}
	
	
	@Test
	public void test004Delete() throws Exception {
		adminService.delete(this.id);
		Assertions.assertNull(adminService.findByUser(USERNAME));
	}

	
}
