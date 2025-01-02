package net.java_school.mapper;

import java.util.List;

import net.java_school.user.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
	public int insert(User user);
	public int insertAuthority(@Param("username") String username, @Param("authority") String authority);
	public int updatePassword(@Param("newPassword") String newPassword, @Param("username") String username);
	public String selectPassword(@Param("username") String username);
	public List<User> selectAll(@Param("search") String search);
	
}
