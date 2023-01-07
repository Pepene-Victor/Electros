package project.electro.server.dtos;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import project.electro.server.enums.StatusEnum;

public class AdministratorDto extends BaseUserEntityDto{

	@Size(min = 3, max = 50)
	@NotNull
	private String firstName;
	
	@Size(min = 3, max = 50)
	@NotNull
	private String lastName;

	@NotNull
	private StatusEnum status;
	
	private String company;
	
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

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getPersonalPhoneNumber() {
		return personalPhoneNumber;
	}

	public void setPersonalPhoneNumber(String personalPhoneNumber) {
		this.personalPhoneNumber = personalPhoneNumber;
	}

	private String personalPhoneNumber;

	
}
