package net.java_school.mapper;

import java.util.List;

import net.java_school.user.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
	public int insert(User user);
	public int insertBasicAuthority(@Param("username") String username);
	public int updatePassword(@Param("username") String username, @Param("password") String password);
	public String selectPassword(@Param("username") String username);
	public List<User> selectAll(@Param("search") String search);
	public int deleteAuthority(@Param("username") String username, @Param("authority") String authority);
	public int insertAuthority(@Param("username") String username, @Param("authority") String authority);
	public int delete(@Param("username") String username);
}
