package com.zwq.comment.web.servlet;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zwq.blog.domain.Blog;
import com.zwq.blog.service.exception.BlogException;
import com.zwq.comment.domain.Comment;
import com.zwq.comment.service.CommentService;
import com.zwq.comment.service.exception.CommentException;
import cn.itcast.commons.CommonUtils;
import cn.itcast.servlet.BaseServlet;

public class CommentServlet extends BaseServlet {
	private CommentService commentService = new CommentService();
	
	
	public void addComment(HttpServletRequest request,HttpServletResponse response) {
		Comment formComment = CommonUtils.toBean(request.getParameterMap(), Comment.class);	
		formComment.setCreatedtime(new Timestamp(new Date().getTime()));
		String username = formComment.getUsername();
		if(username == null || username.equals("") || username.trim().isEmpty()) {
			username = "匿名";
			formComment.setUsername(username);
		}
		
		try {
			boolean result = commentService.addComment(formComment);
			if (!result) {
				request.setAttribute("bid", formComment.getBlog_id());
				request.setAttribute("msg", "评论提交失败！");
				request.getRequestDispatcher("/jsps/comment/commentMsg.jsp").forward(request, response);
			} 
			response.sendRedirect("/blog/BlogServlet?method=getBlog&bid="+formComment.getBlog_id());
			
		} catch (CommentException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (ServletException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}		
	}
	
	public void getCommentList(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		try {
			List<Comment> commentList = commentService.getCommentList();
			request.setAttribute("commentList", commentList);
			request.getRequestDispatcher("/jsps/comment/commentList.jsp").forward(request, response);
			return;
		} catch (CommentException e) {
			request.setAttribute("msg", e.getMessage());
			request.setAttribute("code", "error");
			request.getRequestDispatcher("/jsps/comment/commentMsg.jsp").forward(request, response);
			return;
		}
	
	}
	
		public void deleteComment(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
			String tempid = request.getParameter("id");
			int id = Integer.parseInt(tempid);
			Comment formComment = new Comment();
			formComment.setId(id);
			try {
				commentService.deleteComment(id);
				request.setAttribute("code", "success");
				request.setAttribute("msg", "删除评论成功！");
				request.setAttribute("formComment", formComment);
				getCommentList(request, response);
			} catch (CommentException e) {
				request.setAttribute("msg", e.getMessage());
				request.setAttribute("code", "error");
				request.setAttribute("formComment", formComment);
				request.getRequestDispatcher("/jsps/comment/commentMsg.jsp").forward(request, response);
				return;
			}
		}	
	
	
	
	/**
	 * 在评论管理界面点击修改时执行
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void preUpdateComment(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		String tempId = request.getParameter("id");
		int id = Integer.parseInt(tempId);
		try {
			Comment comment = commentService.getComment(id);
			request.setAttribute("comment", comment);
			request.getRequestDispatcher("/jsps/comment/editComment.jsp").forward(request, response);
		} catch (CommentException e) {
			request.setAttribute("msg",e.getMessage() );
			request.setAttribute("code", "error");			
			request.getRequestDispatcher("/jsps/comment/commentMsg.jsp").forward(request, response);
			return;
		}	
	}

	/**
	 * 编辑评论页面，点击提交时，执行此方法
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void updateComment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Comment formComment = CommonUtils.toBean(request.getParameterMap(), Comment.class);
		formComment.setCreatedtime(new Timestamp(new Date().getTime()));
		
		try {
			boolean boolean1 = commentService.updateComment(formComment);
			if (boolean1) {
				request.setAttribute("code", "success");
				request.setAttribute("msg", "修改评论成功！");
				request.setAttribute("formComment", formComment);
				request.getRequestDispatcher("/jsps/comment/commentMsg.jsp").forward(request, response);
				return;
			} else {
				request.setAttribute("msg", "修改评论失败！");
				request.setAttribute("code", "error");
				request.setAttribute("formComment", formComment);
				request.getRequestDispatcher("/jsps/comment/commentMsg.jsp").forward(request, response);
				return;
			}
			
		} catch (CommentException e) {
			request.setAttribute("msg",e.getMessage());
			request.setAttribute("code", "error");
			request.setAttribute("formComment", formComment);
			request.getRequestDispatcher("/jsps/comment/commentMsg.jsp").forward(request, response);
			return;
		}		
	}		
}
