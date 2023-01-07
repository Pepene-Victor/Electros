package project.electro.server.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import project.electro.server.enums.PasswordStatusEnum;
import project.electro.server.enums.RoleEnum;

@Entity
@Table(name = "users")
public class User extends BaseEntity{
	
	@Column(name = "username", unique = true)
	private String username;
	
	@Column(name = "password")
	private String password;
	
	@Enumerated(value = EnumType.STRING)
	private RoleEnum role;
	
	@Enumerated(value = EnumType.STRING)
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
