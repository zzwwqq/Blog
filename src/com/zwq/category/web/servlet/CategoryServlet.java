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

import cn.itcast.commons.CommonUtils;
import cn.itcast.servlet.BaseServlet;

public class CategoryServlet extends BaseServlet {
private CategoryService categoryService = new CategoryService();

public void addCategory(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
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
