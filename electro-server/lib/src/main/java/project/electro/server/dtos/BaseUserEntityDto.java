package project.electro.server.dtos;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import project.electro.server.entities.User;

public abstract class BaseUserEntityDto extends BaseEntityDto{

	
	private User user;
	
	@Email
	private String email;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}
