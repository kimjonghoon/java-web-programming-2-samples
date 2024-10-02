package net.java_school.mapper;

import net.java_school.user.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
	public int insert(User user);
	public int insertAuthority(@Param("username") String username, @Param("authority") String authority);
}