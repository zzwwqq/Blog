package com.zwq.comment.service;

import java.sql.SQLException;
import java.util.List;

import com.zwq.comment.dao.CommentDao;
import com.zwq.comment.domain.Comment;
import com.zwq.comment.service.exception.CommentException;

public class CommentService {
	private CommentDao commentDao = new CommentDao();
	
	public boolean addComment(Comment comment) throws CommentException {
		try {
			int affectedRowNum = commentDao.addComment(comment);
		    if (affectedRowNum > 0) {
				return true;
			}
		    else {
				return false;
			}
		} catch (SQLException e) {
			throw new CommentException("发表评论失败！");
		}
	}
	
	
	public List<Comment> getCommentList(int blog_id) throws CommentException {
		try {
			List<Comment> commentList = commentDao.findCommentListByBlog_Id(blog_id);
			if (commentList == null) {
				throw new CommentException("查询评论失败！");
			}
			return commentList;
			
		} catch (SQLException e) {
			throw new CommentException("查询评论失败！");
		}
	}
	
	
	
	
}
