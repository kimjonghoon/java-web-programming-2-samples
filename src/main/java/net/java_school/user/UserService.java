package net.java_school.user;

import java.util.List;

import org.springframework.security.core.parameters.P;
import org.springframework.security.access.prepost.PreAuthorize;

public interface UserService {

	public int addUser(User user);

	public int addBasicAuthority(String username);

	@PreAuthorize("hasAnyRole('ADMIN','USER')")
	public int changePassword(String username, String password);

	@PreAuthorize("hasRole('ADMIN') or #username == principal.username")
	public String getPassword(@P("username") String username);

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public List<User> getUsers(String search);
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public int deleteAuthority(String username, String authority);

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public int addAuthority(String username, String authority);

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public int deleteUser(String username);	
}