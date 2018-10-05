package com.zwq.admin.web.servlet;


import static org.hamcrest.CoreMatchers.nullValue;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.zwq.admin.service.AdminBlogService;
import com.zwq.blog.domain.Blog;
import com.zwq.blog.service.exception.BlogException;
import com.zwq.category.domain.Category;

import cn.itcast.commons.CommonUtils;
import cn.itcast.servlet.BaseServlet;

public class AdminBlogServlet extends BaseServlet {
	private AdminBlogService adminBlogService = new AdminBlogService();
	
	public void getBlogList(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		try {
			List<Blog> blogList = adminBlogService.getBlogList();
			request.setAttribute("blogList", blogList);
			request.getRequestDispatcher("/jsps/admin/adminBlogList.jsp").forward(request, response);
			return;
		} catch (BlogException e) {
			request.setAttribute("msg", e.getMessage());
			request.setAttribute("code", "error");
			request.getRequestDispatcher("/jsps/admin/blogMsg.jsp").forward(request, response);
			return;
		}
	
	}
	
	
	public void deleteBlog(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		String tempbid = request.getParameter("bid");
		int bid = Integer.parseInt(tempbid);
		Blog formBlog = new Blog();
		formBlog.setBid(bid);
		try {
			adminBlogService.deleteBlog(bid);
			request.setAttribute("code", "success");
			request.setAttribute("msg", "删除博客内容成功！");
			request.setAttribute("formBlog", formBlog);
			getBlogList(request,response);
		} catch (BlogException e) {
			request.setAttribute("msg", e.getMessage());
			request.setAttribute("code", "error");
			request.setAttribute("formBlog", formBlog);
			request.getRequestDispatcher("/jsps/admin/blogMsg.jsp").forward(request, response);
			return;
		}	
	}	
	
	
	public void preUpdateBlog(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		String tempBid = request.getParameter("bid");
		int bid = Integer.parseInt(tempBid);
		Blog blog=null;
		Blog formBlog = new Blog();
		formBlog.setBid(bid);
		List<Category> categoryList =null;
		try {
			categoryList = adminBlogService.getCategoryList();
			blog = adminBlogService.getBlog(bid);
			request.setAttribute("blog", blog);
			request.setAttribute("categoryList", categoryList);
			request.getRequestDispatcher("/jsps/admin/editBlog.jsp").forward(request, response);
			return;
		} catch (BlogException e) {		
			request.setAttribute("msg", "修改失败！");
			request.setAttribute("code", "error");
			request.setAttribute("formBlog", formBlog);
			request.getRequestDispatcher("/jsps/admin/blogMsg.jsp").forward(request, response);
			return;
		}
	}

	public void updateBlog(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Blog formBlog = CommonUtils.toBean(request.getParameterMap(), Blog.class);
		try {
			boolean boolean1 = adminBlogService.updateBlog(formBlog);
			if (boolean1) {
				request.setAttribute("code", "success");
				request.setAttribute("msg", "修改博客内容成功！");
				request.setAttribute("formBlog", formBlog);
				request.getRequestDispatcher("/jsps/admin/blogMsg.jsp").forward(request, response);
				return;
			} else {
				request.setAttribute("msg", "修改博客内容失败！");
				request.setAttribute("code", "error");
				request.setAttribute("formBlog", formBlog);
				request.getRequestDispatcher("/jsps/admin/blogMsg.jsp").forward(request, response);
				return;
			}
			
		} catch (BlogException e) {
			request.setAttribute("msg", "修改博客内容失败！");
			request.setAttribute("code", "error");
			request.setAttribute("formBlog", formBlog);
			request.getRequestDispatcher("/jsps/admin/blogMsg.jsp").forward(request, response);
			return;
		}		
	}	
}
