package project.electro.server.entities;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import project.electro.server.enums.StatusEnum;

@Entity
@Table(name = "administrators")
public class Administrator extends BaseUserEntity{
	
	@Column(name = "first_name")
	private String firstName;
	
	@Column(name = "last_name")
	private String lastName;
	
	@Enumerated(value = EnumType.STRING)
	private StatusEnum status;
	
	private String company;
	
	@Column(name = "personal_phone_number")
	private String personalPhoneNumber;


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

	
}
