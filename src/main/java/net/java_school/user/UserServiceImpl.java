package net.java_school.user;

import java.util.List;

import net.java_school.mapper.UserMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserMapper userMapper;

	@Override
	public int addUser(User user) {
		return userMapper.insert(user);
	}
	@Override
	public int addBasicAuthority(String username) {
		return userMapper.insertBasicAuthority(username);
	}
	@Override
	public int changePassword(String username, String password) {
		return userMapper.updatePassword(username, password);
	}
	@Override
	public String getPassword(String username) {
		return userMapper.selectPassword(username);
	}	
	@Override
	public List<User> getUsers(String search) {
		return userMapper.selectAll(search);
	}	
	@Override
	public int deleteAuthority(String username, String authority) {
		return userMapper.deleteAuthority(username, authority);
	}
	@Override
	public int addAuthority(String username, String authority) {
		return userMapper.insertAuthority(username, authority);
	}
	@Override
	public int deleteUser(String username) {
		return userMapper.updateEnabledToZero(username);
	}	
}