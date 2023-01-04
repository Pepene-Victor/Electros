package project.electro.server.dtos;


import javax.validation.constraints.NotNull;

import project.electro.server.enums.PasswordStatusEnum;
import project.electro.server.enums.RoleEnum;

public class UserDto extends BaseEntityDto{

	@NotNull
	private String username;
	
	@NotNull
	private String password;

	@NotNull
	private RoleEnum role;

	@NotNull
	private PasswordStatusEnum passwordStatus;
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	public RoleEnum getRole() {
		return role;
	}

	public void setRole(RoleEnum role) {
		this.role = role;
	}

	public PasswordStatusEnum getPasswordStatus() {
		return passwordStatus;
	}

	public void setPasswordStatus(PasswordStatusEnum passwordStatus) {
		this.passwordStatus = passwordStatus;
	}

}

