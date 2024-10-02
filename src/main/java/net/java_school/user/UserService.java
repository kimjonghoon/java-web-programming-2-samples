package net.java_school.user;

public interface UserService {
	public int addUser(User user);
	public int addAuthority(String username, String authority);
}