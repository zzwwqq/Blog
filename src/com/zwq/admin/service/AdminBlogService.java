package com.zwq.admin.service;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import com.zwq.admin.dao.AdminBlogDao;
import com.zwq.blog.domain.Blog;
import com.zwq.blog.service.exception.BlogException;
import com.zwq.category.domain.Category;

public class AdminBlogService {
	private AdminBlogDao adminBlogDao = new AdminBlogDao();
	
	
public List<Blog> getBlogList() throws BlogException {
		
		try {
			List<Blog>bloglist = adminBlogDao.findBlogList();
			if (bloglist == null || bloglist.size() < 0) {
				throw new BlogException("查询所有博客内容失败！");
			} else {
				return bloglist;
			}
		} catch (SQLException e) {
			throw new BlogException("查询所有博客内容失败！");
		}
	}
	
public List<Category> getCategoryList() throws BlogException {
	
	try {
		List<Category>categoryList = adminBlogDao.findCategoryList();
		if (categoryList == null || categoryList.size() < 0) {
			throw new BlogException("查询所有分类失败！");
		} else {
			return categoryList;
		}
	} catch (SQLException e) {
		throw new BlogException("查询所有分类失败！");
	}
}

	public boolean deleteBlog(int bid) throws BlogException {
		try {
			int affectedRowNum = adminBlogDao.deleteBlogByBid(bid);
			if (affectedRowNum > 0) {
				return true;
			}
			else {
				throw new BlogException("删除博客内容失败！");
			}
		} catch (SQLException e) {
			throw new BlogException("删除博客内容失败！");
		}
	}


	public boolean updateBlog(Blog blog) throws BlogException {
		blog.setCreated_time(new Timestamp(new Date().getTime()));
		try {
			int affectedRowNum = adminBlogDao.updateBlogByBid(blog);
			if (affectedRowNum > 0) {
				return true;
			} else {
				return false;
			}
					
		} catch (SQLException e) {
			throw new BlogException("修改博客内容失败！");
		}		
	}
	
	public Blog getBlog(int bid) throws BlogException {
		Blog blog=null;
		try {
			blog = adminBlogDao.findByBidGetBlog(bid);
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
	
}
