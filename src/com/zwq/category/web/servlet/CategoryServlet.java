package com.zwq.category.web.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.zwq.blog.service.exception.BlogException;
import com.zwq.category.domain.Category;
import com.zwq.category.service.CategoryService;
import com.zwq.user.domain.User;

import cn.itcast.commons.CommonUtils;
import cn.itcast.servlet.BaseServlet;

public class CategoryServlet extends BaseServlet {
private CategoryService categoryService = new CategoryService();

/**
 * 点击主页，添加分类超链接时执行
 * @param request
 * @param response
 */
public void preAddCategory(HttpServletRequest request,HttpServletResponse response) {
	User user = (User)request.getSession().getAttribute("sessionUser");
	if(user == null) {
		try {
			request.getRequestDispatcher("/jsps/user/login.jsp").forward(request, response);
		    return;
		} catch (ServletException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		};
	}
	else {
		try {
			request.getRequestDispatcher("/jsps/category/addCategory.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		};
		
	}
	
	
}







public void addCategory(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
	User user = (User)request.getSession().getAttribute("sessionUser");
	if(user == null) {
		request.getRequestDispatcher("/jsps/user/login.jsp").forward(request, response);;
		return;
	}
	Category formCategory = new Category();
	try {
		request.setCharacterEncoding("UTF-8");
		Category Formcategory = CommonUtils.toBean(request.getParameterMap(), Category.class);		
		try {
			categoryService.addCategory(Formcategory);
			try {
				formCategory = categoryService.getCategory(Formcategory.getCname(), Formcategory.getClevel());
				request.setAttribute("formCategory", formCategory);
				request.setAttribute("msg", "添加分类成功！");
				request.setAttribute("code", "success");
				request.getRequestDispatcher("/jsps/category/categoryMsg.jsp").forward(request, response);
				return;			
			} catch (BlogException e) {
				request.setAttribute("formCategory", formCategory);
				request.setAttribute("msg", "操作失败！");
				request.setAttribute("code", "error");
				request.getRequestDispatcher("/jsps/category/categoryMsg.jsp").forward(request, response);
				return;
			}			
		} catch (SQLException e) {
			request.setAttribute("formCategory", formCategory);
			request.setAttribute("msg", "操作失败！");
			request.setAttribute("code", "error");
			request.getRequestDispatcher("/jsps/category/categoryMsg.jsp").forward(request, response);
			return;
		}
	} catch (Exception e) {
		request.setAttribute("formCategory", formCategory);
		request.setAttribute("msg", "操作失败！");
		request.setAttribute("code", "error");
		request.getRequestDispatcher("/jsps/category/categoryMsg.jsp").forward(request, response);
		return;
	}				
}
	
	public void getCategoryList(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		User user = (User)request.getSession().getAttribute("sessionUser");
		if(user == null) {
			request.getRequestDispatcher("/jsps/user/login.jsp").forward(request, response);;
		}
		try {
			List<Category> categoryList = categoryService.getCategoryList();
			request.setAttribute("categoryList", categoryList);
			request.getRequestDispatcher("/jsps/category/categoryList.jsp").forward(request, response);
			return;
		} catch (BlogException e) {
			request.setAttribute("msg", e.getMessage());
			request.setAttribute("code", "error");
			request.getRequestDispatcher("/jsps/category/categoryMsg.jsp").forward(request, response);
			return;
		}
	
	}
		
	public void deleteCategory(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		User user = (User)request.getSession().getAttribute("sessionUser");
		if(user == null) {
			request.getRequestDispatcher("/jsps/user/login.jsp").forward(request, response);;
		}
		String tempcid = request.getParameter("cid");
		int cid = Integer.parseInt(tempcid);
		Category formCategory = new Category();
		formCategory.setCid(cid);
		try {
			categoryService.deleteCategory(cid);
			request.setAttribute("code", "success");
			request.setAttribute("msg", "删除分类成功！");
			request.setAttribute("formCategory", formCategory);
			getCategoryList(request, response);
		} catch (BlogException e) {
			request.setAttribute("msg", e.getMessage());
			request.setAttribute("code", "error");
			request.setAttribute("formCategory", formCategory);
			request.getRequestDispatcher("/jsps/category/categoryMsg.jsp").forward(request, response);
			return;
		}	
	}	
	
	
	public void preUpdateCategory(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		User user = (User)request.getSession().getAttribute("sessionUser");
		if(user == null) {
			request.getRequestDispatcher("/jsps/user/login.jsp").forward(request, response);;
		}
		String tempCid = request.getParameter("cid");
		int cid = Integer.parseInt(tempCid);
		Category category=null;
		Category formCategory = new Category();
		formCategory.setCid(cid);
		try {
			category = categoryService.getCategory(cid);
			request.setAttribute("category", category);
			request.getRequestDispatcher("/jsps/category/editCategory.jsp").forward(request, response);
			return;
		} catch (BlogException e) {
			
			request.setAttribute("msg", "修改分类失败！");
			request.setAttribute("code", "error");
			request.setAttribute("formCategory", formCategory);
			request.getRequestDispatcher("/jsps/category/categoryMsg.jsp").forward(request, response);
			return;
		}
	}

	public void updateCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = (User)request.getSession().getAttribute("sessionUser");
		if(user == null) {
			request.getRequestDispatcher("/jsps/user/login.jsp").forward(request, response);;
		}
		Category formCategory = CommonUtils.toBean(request.getParameterMap(), Category.class);
		try {
			boolean boolean1 = categoryService.updateCategory(formCategory);
			if (boolean1) {
				request.setAttribute("code", "success");
				request.setAttribute("msg", "修改分类成功！");
				request.setAttribute("formCategory", formCategory);
				request.getRequestDispatcher("/jsps/category/categoryMsg.jsp").forward(request, response);
				return;
			} else {
				request.setAttribute("msg", "修改分类失败！");
				request.setAttribute("code", "error");
				request.setAttribute("formCategory", formCategory);
				request.getRequestDispatcher("/jsps/category/categoryMsg.jsp").forward(request, response);
				return;
			}
			
		} catch (BlogException e) {
			request.setAttribute("msg", "修改分类失败！");
			request.setAttribute("code", "error");
			request.setAttribute("formCategory", formCategory);
			request.getRequestDispatcher("/jsps/category/categoryMsg.jsp").forward(request, response);
			return;
		}		
	}	
}
