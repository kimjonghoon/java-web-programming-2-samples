package net.java_school.user;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import net.java_school.mapper.UserMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.access.AccessDeniedException;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserMapper userMapper;

	@Autowired
	private BCryptPasswordEncoder bcryptPasswordEncoder;

	@Override
	public int addUser(User user) {
		user.setPassword(this.bcryptPasswordEncoder.encode(user.getPassword()));
		return userMapper.insert(user);
	}
	@Override
	public int addAuthority(String username, String authority) {
		return userMapper.insertAuthority(username, authority);
	}
	@Override
	public int changePassword(String currentPassword, String newPassword, String username) {
		String dbPassword = this.getPassword(username);
		boolean check = this.bcryptPasswordEncoder.matches(currentPassword, dbPassword);
		if (check == false) throw new AccessDeniedException("The password is incorrect!");
		newPassword = this.bcryptPasswordEncoder.encode(newPassword);
		return userMapper.updatePassword(newPassword, username);
	}
	@Override
	public String getPassword(String username) {
		return userMapper.selectPassword(username);
	}	
}