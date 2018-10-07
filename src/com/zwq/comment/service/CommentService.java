package com.zwq.comment.service;

import java.sql.SQLException;

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
}
