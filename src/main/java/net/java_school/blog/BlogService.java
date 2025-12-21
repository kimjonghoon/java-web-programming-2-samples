package net.java_school.blog;

import java.util.List;

public interface BlogService {

	public List<Blog> getAll();
	
	public Blog getOne(String slug);
	
	public int changeBlog(Blog blog);
	
	public int addBlog(Blog blog);
	
	public int removeBlog(int postNo);
}
