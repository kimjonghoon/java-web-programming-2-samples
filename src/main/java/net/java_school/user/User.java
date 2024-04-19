package net.java_school.user;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class User {

	@NotNull
	@Size(min=2, message="Please enter an account name that is at least 2 characters long.")
	private String username;

	@NotNull
	@Size(min=4, message="Please enter a password that is at least 4 characters long.")
	private String password;
	
	@NotNull
	@Pattern(regexp="(Y|N)", message="Y or N.")
	private char enabled;

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

	public char getEnabled() {
		return enabled;
	}

	public void setEnabled(char enabled) {
		this.enabled = enabled;
	}
	
}
