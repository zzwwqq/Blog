package com.zwq.page.web.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zwq.blog.domain.Blog;
import com.zwq.category.dao.CategoryDao;
import com.zwq.category.domain.Category;
import com.zwq.comment.dao.CommentDao;
import com.zwq.comment.domain.Comment;
import com.zwq.page.domain.PageBean;
import com.zwq.page.service.PageService;

import cn.itcast.servlet.BaseServlet;

public class PageServlet extends BaseServlet {
	private PageService pageService = new PageService();
	/**
	 * 获取当前页
	 * @param request
	 * @return
	 */
		public int getCurrentPage(HttpServletRequest request) {
			int currentPage = 1;
			String tempCurrentPage = request.getParameter("currentPage");
			if (tempCurrentPage != null && !tempCurrentPage.trim().isEmpty()) {			
				try {
					currentPage = Integer.parseInt(tempCurrentPage);
				} catch (RuntimeException e) {
					e.getMessage();
				}			
			}
			return currentPage;
		}


		

	/**
	 * 获取PageBean，返回给jsp页面
	 * @throws IOException 
	 * @throws ServletException 
	 * @throws SQLException 
	 */
	public void getPageBean(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException, SQLException {
		List<Comment>commentList = null;
		List<Category>categoryList = null;
		String param = request.getParameter("param");//根据该参数值决定查询所有分类还是查询部分分类
		if (param == null) {
			categoryList = new CategoryDao().getLimitRowNumCategory();						
		} else if(param.equals("all")){
			categoryList = new CategoryDao().findCategoryList();		
		}
		int currentPage = new PageServlet().getCurrentPage(request);
		PageBean<Blog> pageBean = pageService.findTotalRecordsGetPageBean(currentPage);
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
}
