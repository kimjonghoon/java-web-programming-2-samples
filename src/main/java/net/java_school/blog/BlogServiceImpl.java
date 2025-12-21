package net.java_school.blog;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.java_school.mapper.BlogMapper;

@Service
public class BlogServiceImpl implements BlogService {
	
	@Autowired
	private BlogMapper blogMapper;

	@Override
	public List<Blog> getAll() {
		return blogMapper.selectAll();
	}

	@Override
	public Blog getOne(String slug) {
		return blogMapper.selectOne(slug);
	}

	@Override
	public int changeBlog(Blog blog) {
		return blogMapper.updateBlog(blog);
	}

	@Override
	public int addBlog(Blog blog) {
		return blogMapper.insertBlog(blog);
	}

	@Override
	public int removeBlog(int postNo) {
		return blogMapper.deleteBlog(postNo);
	}

}