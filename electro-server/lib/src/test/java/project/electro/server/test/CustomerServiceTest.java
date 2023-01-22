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

import project.electro.server.dtos.CustomerDto;
import project.electro.server.dtos.UserDto;
import project.electro.server.enums.GenderEnum;
import project.electro.server.enums.PasswordStatusEnum;
import project.electro.server.enums.RoleEnum;
import project.electro.server.enums.StatusEnum;
import project.electro.server.services.CustomerService;
import project.electro.server.services.UserService;

@SpringBootTest
@ContextConfiguration
@TestMethodOrder(MethodOrderer.DisplayName.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CustomerServiceTest {
	
	public static final String USERNAME = "user@test.com";
	public static final String NAME = "customer1";
	public static final int AGE = 20;

	public static final String NAME_2 = "customer2";
	public static final int AGE_2 = 23;

	
	@Autowired
	private CustomerService customerService;
	@Autowired
	private UserService userService;
	
	
	private CustomerDto persistedCustomer;
	private Long id;
	
	@Test
	public void test001Create() throws  Exception {
		
		UserDto user = new UserDto();
		user.setUsername(USERNAME);
		user.setRole(RoleEnum.CUSTOMER);
		user.setPassword("password");
		user.setPasswordStatus(PasswordStatusEnum.OK);
		userService.register(user);
		
		CustomerDto customerDto = new CustomerDto();
		customerDto.setName(NAME);
		customerDto.setAge(AGE);
		customerDto.setStatus(StatusEnum.FREE);
		customerDto.setGender(GenderEnum.MASCULINE);
		
		persistedCustomer = customerService.create(customerDto, USERNAME);
		this.id = persistedCustomer.getId();
		assertNotNull(persistedCustomer);
		
	}

	
	@Test
	public void test002Update() throws Exception {

		persistedCustomer.setName(NAME_2);
		persistedCustomer.setAge(AGE_2);
		persistedCustomer.setStatus(StatusEnum.PENDING);
		persistedCustomer = customerService.update(persistedCustomer);
		Assertions.assertEquals(NAME_2, persistedCustomer.getName());
		Assertions.assertEquals(AGE_2, persistedCustomer.getAge());
		Assertions.assertEquals(StatusEnum.PENDING, persistedCustomer.getStatus());

	}
	
	
	@Test
	public void test004Delete() throws Exception {
		customerService.delete(this.id);
		Assertions.assertNull(customerService.findByUser(USERNAME));
	}

}
