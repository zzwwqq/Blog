package com.zwq.comment.web.servlet;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
	
	
	
	
	
	
}
