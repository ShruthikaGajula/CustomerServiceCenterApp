package com.customerService.app.Entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Login {
	enum UserType {
		Admin, Operator, Customer
	}
	@Id
	private String username;
	private String String;
	private UserType type;
	private Boolean isActive;
	public Login() {
		super();
	}
	public Login(java.lang.String username, java.lang.String string, UserType type, boolean isActive) {
		super();
		this.username = username;
		String = string;
		this.type = type;
		this.isActive = isActive;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getString() {
		return String;
	}
	public void setString(String string) {
		String = string;
	}
	public UserType getType() {
		return type;
	}
	public void setType(UserType type) {
		this.type = type;
	}
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	
}
