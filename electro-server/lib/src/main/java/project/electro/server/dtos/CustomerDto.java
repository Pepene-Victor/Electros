package project.electro.server.dtos;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import project.electro.server.enums.GenderEnum;
import project.electro.server.enums.StatusEnum;

public class CustomerDto extends BaseUserEntityDto{

	@NotNull
	@Size(min = 3, max = 50)
	private String firstName;
	
	@NotNull
	@Size(min = 3, max = 50)
	private String lastName;
	
	@NotNull
	private StatusEnum status;
	
	private GenderEnum gender;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public StatusEnum getStatus() {
		return status;
	}

	public void setStatus(StatusEnum status) {
		this.status = status;
	}

	public GenderEnum getGender() {
		return gender;
	}

	public void setGender(GenderEnum gender) {
		this.gender = gender;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	private int age;
	
}
