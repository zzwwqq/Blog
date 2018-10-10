package com.zwq.blog.web.servlet;


import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zwq.admin.domain.Admin;
import com.zwq.blog.domain.Blog;
import com.zwq.blog.service.BlogService;
import com.zwq.blog.service.exception.BlogException;
import com.zwq.category.domain.Category;
import com.zwq.comment.domain.Comment;
import com.zwq.comment.service.CommentService;
import com.zwq.comment.service.exception.CommentException;
import com.zwq.user.domain.User;

import cn.itcast.commons.CommonUtils;
import cn.itcast.servlet.BaseServlet;

public class BlogServlet extends BaseServlet {
	private BlogService blogService = new BlogService();
	
	public void preAddBlog(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		Admin admin = (Admin)request.getSession().getAttribute("sessionUser");
		if(admin == null) {
			request.getRequestDispatcher("/jsps/admin/adminLogin.jsp").forward(request, response);;
		}
		try {
			List<Category> categoryList = blogService.getCategoryList();
			request.setAttribute("categoryList", categoryList);
			request.getRequestDispatcher("/jsps/blog/addBlog.jsp").forward(request, response);
			return;
		
		} catch (BlogException e) {
			request.setAttribute("msg", e.getMessage());
			request.setAttribute("code", "error");
			request.getRequestDispatcher("/jsps/blog/blogMsg.jsp").forward(request, response);
			return;
		}
	}
	
	
	
	public void addBlog(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sessionUser");
		if(admin == null) {
			request.getRequestDispatcher("/jsps/admin/adminLogin.jsp").forward(request, response);;
		}
		Blog formBlog = new Blog();
		try {
			request.setCharacterEncoding("UTF-8");
			Blog Formblog = CommonUtils.toBean(request.getParameterMap(), Blog.class);		
			try {
				blogService.addBlog(Formblog);
				try {
					formBlog = blogService.getBlog(Formblog.getTitle(), Formblog.getCategory_id(), Formblog.getContent());
					request.setAttribute("formBlog", formBlog);
					request.setAttribute("msg", "添加博客信息成功！");
					request.setAttribute("code", "success");
					request.getRequestDispatcher("/jsps/admin/blogMsg.jsp").forward(request, response);
					return;			
				} catch (BlogException e) {
					request.setAttribute("formBlog", formBlog);
					request.setAttribute("msg", "操作失败！");
					request.setAttribute("code", "error");
					request.getRequestDispatcher("/jsps/admin/blogMsg.jsp").forward(request, response);
					return;
				}			
			} catch (SQLException e) {
				request.setAttribute("formBlog", formBlog);
				request.setAttribute("msg", "操作失败！");
				request.setAttribute("code", "error");
				request.getRequestDispatcher("/jsps/admin/blogMsg.jsp").forward(request, response);
				return;
			}
		} catch (Exception e) {
			request.setAttribute("formBlog", formBlog);
			request.setAttribute("msg", "操作失败！");
			request.setAttribute("code", "error");
			request.getRequestDispatcher("/jsps/admin/blogMsg.jsp").forward(request, response);
			return;
		}				
	}
	
	public void getBlog(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		Blog Formblog = CommonUtils.toBean(request.getParameterMap(), Blog.class);
		int bid = Formblog.getBid();
		try {
			Blog blog = blogService.getBlog(bid);
			List<Comment> commentList = new CommentService().getCommentList(Formblog.getBid());
			request.setAttribute("blog", blog);
			request.setAttribute("commentList", commentList);
			request.getRequestDispatcher("/jsps/blog/displayBlog.jsp").forward(request, response);
			return;
		} catch (BlogException e) {
			request.setAttribute("msg", e.getMessage());
			request.setAttribute("code", "error");
			request.getRequestDispatcher("/jsps/msg.jsp").forward(request, response);
			return;
		} catch (CommentException e) {
			//request.setAttribute("msg", e.getMessage());
			//request.setAttribute("code", "error");
			request.setAttribute("bid",bid);
			request.getRequestDispatcher("/jsps/comment/commentMsg.jsp").forward(request, response);
			return;
		}
		
	}	
	
	/**
	 * 管理员获取博客列表
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void getBlogList(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		try {
			List<Blog> blogList = blogService.getBlogList();
			request.setAttribute("blogList", blogList);
			request.getRequestDispatcher("/jsps/admin/adminBlogList.jsp").forward(request, response);
			return;
		} catch (BlogException e) {
			request.setAttribute("msg", e.getMessage());
			request.setAttribute("code", "error");
			request.getRequestDispatcher("/jsps/msg.jsp").forward(request, response);
			return;
		}
	
	}
	
	/**
	 * 一般用户获取博客内容
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void getBlogListForHome(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		try {
			List<Blog> blogList = blogService.getBlogList();
			request.setAttribute("blogList", blogList);
			request.getRequestDispatcher("/jsps/Home.jsp").forward(request, response);
			return;
		} catch (BlogException e) {
			request.setAttribute("msg", e.getMessage());
			request.setAttribute("code", "error");
			request.getRequestDispatcher("/jsps/msg.jsp").forward(request, response);
			return;
		}
	
	}
	
	
	
	
	
	
}
