package net.java_school.blog;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;

public interface BlogService {

	public List<Blog> getAll();
	
	public Blog getOne(String slug);
	
	@PreAuthorize("hasRole('ADMIN')")
	public int changeSlug(Blog blog);
	
	@PreAuthorize("hasRole('ADMIN')")
	public int changeDescription(Blog blog);
	
	@PreAuthorize("hasRole('ADMIN')")
	public int addBlog(Blog blog);
	
	@PreAuthorize("hasRole('ADMIN')")
	public int removeBlog(int postNo);
}
