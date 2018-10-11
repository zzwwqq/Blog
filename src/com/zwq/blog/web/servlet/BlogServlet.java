package com.zwq.blog.web.servlet;


import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zwq.admin.domain.Admin;
import com.zwq.blog.dao.BlogDao;
import com.zwq.blog.domain.Blog;
import com.zwq.blog.service.BlogService;
import com.zwq.blog.service.exception.BlogException;
import com.zwq.category.dao.CategoryDao;
import com.zwq.category.domain.Category;
import com.zwq.comment.dao.CommentDao;
import com.zwq.comment.domain.Comment;
import com.zwq.comment.service.CommentService;
import com.zwq.comment.service.exception.CommentException;
import com.zwq.page.domain.PageBean;
import com.zwq.page.service.PageService;
import com.zwq.page.web.servlet.PageServlet;

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
	
	
	public void getBlog(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException, SQLException {
		Blog Formblog = CommonUtils.toBean(request.getParameterMap(), Blog.class);
		int bid = Formblog.getBid();
		try {
			Blog blog = blogService.getBlog(bid);
			Category category = new CategoryDao().findByCidGetCategory(blog.getCategory_id());
			//对应的博客下显示
			List<Comment> commentList = new CommentService().getCommentList(Formblog.getBid());
			List<Category>limitRowNumcategoryList = null;
			//右侧菜单栏显示
			List<Comment> limitRowNumcommentList = new CommentDao().getLimitRowNumComment();
			String param = request.getParameter("param");//根据该参数值决定查询所有分类还是查询部分分类
			if (param == null) {
				limitRowNumcategoryList = new CategoryDao().getLimitRowNumCategory();						
			} else if(param.equals("all")){
				limitRowNumcategoryList = new CategoryDao().findCategoryList();		
			}			
			List<Blog>limitRowNumTitleList = new BlogDao().getLimitRowNumTitleList();
			request.setAttribute("limitRowNumcategoryList",limitRowNumcategoryList);
			request.setAttribute("limitRowNumcommentList", limitRowNumcommentList);			
			request.setAttribute("blog", blog);
			request.setAttribute("limitRowNumTitleList", limitRowNumTitleList);
			request.setAttribute("category", category);
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
	
	
	public void getBlogBycidForHome(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException, SQLException {
			List<Comment>commentList = null;
			List<Category>categoryList = null;
			String param = request.getParameter("param");//根据该参数值决定查询所有分类还是查询部分分类
			if (param == null) {
				categoryList = new CategoryDao().getLimitRowNumCategory();						
			} else if(param.equals("all")){
				categoryList = new CategoryDao().findCategoryList();		
			}
			int currentPage = new PageServlet().getCurrentPage(request);
			String tempcid = request.getParameter("cid");
		    int cid = Integer.parseInt(tempcid);
			PageBean<Blog> pageBean = new PageService().findTotalRecordsGetPageBeanByCid(currentPage,cid);
			try {
				commentList = new CommentDao().getLimitRowNumComment();
				request.setAttribute("categoryList",categoryList);
				request.setAttribute("commentList", commentList);			
				request.setAttribute("pageBean", pageBean);			
				request.getRequestDispatcher("/jsps/Home.jsp").forward(request, response);
			} catch (SQLException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}	
		}	

//	/**
//	 * 一般用户获取博客内容
//	 * @param request
//	 * @param response
//	 * @throws ServletException
//	 * @throws IOException
//	 */
//	public void getBlogListForHome(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
//		try {
//			/*
//			 * 获取所有的博文
//			 */
//			List<Blog> blogList = blogService.getBlogList();
//			request.setAttribute("blogList", blogList);
//			
//			/*
//			 * 获取所有的分类
//			 */
//			List<Category> categoryList = new CategoryService().getCategoryList();
//			request.setAttribute("categoryList", categoryList);			
//			
//			/*
//			 * 获取所有的评论
//			 */
//			List<Comment> commentList = new CommentService().getCommentList();
//			request.setAttribute("commentList", commentList);
//			
//			
//			request.getRequestDispatcher("/jsps/Home.jsp").forward(request, response);
//			return;
//		} catch (BlogException e) {
//			request.setAttribute("msg", e.getMessage());
//			request.setAttribute("code", "error");
//			request.getRequestDispatcher("/jsps/msg.jsp").forward(request, response);
//			return;
//		} catch (CommentException e) {
//			request.setAttribute("msg", e.getMessage());
//			request.setAttribute("code", "error");
//			request.getRequestDispatcher("/jsps/msg.jsp").forward(request, response);
//			return;
//		}
//	
//	}
	
	
	
	
	
	
	
}
