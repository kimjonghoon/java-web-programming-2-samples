package net.java_school.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import net.java_school.blog.Blog;

public interface BlogMapper {

	public List<Blog> selectAll();
	
	public Blog selectOne(@Param("slug") String slug);
	
	public int updateSlug(Blog blog);
	
	public int updateDescription(Blog blog);
	
	public int insertBlog(Blog blog);
	
	public int deleteBlog(int postNo);
}
