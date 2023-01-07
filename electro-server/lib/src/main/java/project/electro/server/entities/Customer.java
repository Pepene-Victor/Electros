package project.electro.server.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import project.electro.server.enums.GenderEnum;
import project.electro.server.enums.StatusEnum;

@Entity
@Table(name = "customers")
public class Customer extends BaseUserEntity{
	
	@Column(name = "name")
	private String name;
	
	@Enumerated(value = EnumType.STRING)
	private StatusEnum status;
	
	@Column(name = "gender")
	@Enumerated(value = EnumType.STRING)
	private GenderEnum gender;
	
	@Column(name = "age")
	private int age;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
	
}

