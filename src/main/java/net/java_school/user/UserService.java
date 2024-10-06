package net.java_school.user;

import org.springframework.security.access.prepost.PreAuthorize;

public interface UserService {
	public int addUser(User user);
	public int addAuthority(String username, String authority);
	@PreAuthorize("hasAnyRole('ADMIN','USER')")
	public int changePassword(String currentPassword, String newPassword, String username);
	@PreAuthorize("hasAnyRole('ADMIN','USER')")
	public String getPassword(String username);
}