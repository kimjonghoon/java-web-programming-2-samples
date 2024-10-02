package net.java_school.user;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import net.java_school.mapper.UserMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

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
}