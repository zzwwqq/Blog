package com.zwq.blog.service;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import com.zwq.blog.dao.BlogDao;
import com.zwq.blog.domain.Blog;
import com.zwq.blog.service.exception.BlogException;

public class BlogService {
	private BlogDao blogDao = new BlogDao();
	
	public void addBlog(Blog blog) throws SQLException {
		blog.setCreated_time(new Timestamp(new Date().getTime()) );
		blogDao.addBlog(blog.getTitle(), blog.getContent(), blog.getCategory_id(), blog.getCreated_time());
	}
	
	public Blog getBlog(int bid) throws BlogException {
		Blog blog=null;
		try {
			blog = blogDao.findByBidGetBlog(bid);
			if (blog == null) {
				throw new BlogException("查询博客内容失败！");
			}
			else {
				return blog;
			}			
		} catch (SQLException e) {
			throw new BlogException("查询博客内容失败！");
		}
	}
	
	
	public Blog getBlog(String title, int category_id,String content ) throws BlogException {
		Blog blog=null;
		try {
			blog = blogDao.findByTitleAndCategory_IdAndContent(title, category_id, content);
			if (blog == null) {
				throw new BlogException("查询博客内容失败！");
			}
			else {
				return blog;
			}			
		} catch (SQLException e) {
			throw new BlogException("查询博客内容失败！");
		}

	}
	
	
	
	
	
	public List<Blog> getBlogList() throws BlogException {
		
		try {
			List<Blog>bloglist = blogDao.findBlogList();
			if (bloglist == null || bloglist.size() < 0) {
				throw new BlogException("查询所有博客内容失败！");
			} else {
				return bloglist;
			}
		} catch (SQLException e) {
			throw new BlogException("查询所有博客内容失败！");
		}
	}
	
	
	
	
	
}
