package com.zwq.comment.service;

import java.sql.SQLException;
import java.util.List;

import com.sun.org.apache.regexp.internal.recompile;
import com.zwq.blog.domain.Blog;
import com.zwq.blog.service.exception.BlogException;
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
	
	/**
	 * 修改评论时调用
	 * @param id
	 * @return
	 * @throws CommentException
	 */
	public Comment getComment(int id) throws CommentException {
		try {
			Comment comment = commentDao.findCommentById(id);
			if (comment == null) {
				throw new CommentException("操作失败！");
			}
		    return comment;
		} catch (SQLException e) {
			throw new CommentException("操作失败！");
		}
	}
	
	
	
/**
 * 查询所有用户的所有评论	
 * @return
 * @throws CommentException
 */
public List<Comment> getCommentList() throws CommentException {
		try {
			List<Comment>commentlist = commentDao.findCommentList();
			if (commentlist == null || commentlist.size() < 0) {
				throw new CommentException("查询所有评论失败！");
			} else {
				return commentlist;
			}
		} catch (SQLException e) {
			throw new CommentException("查询所有评论失败！");
		}
	}
	
	
/**
 * 查询某一个博客下的所有评论
 * @param blog_id
 * @return
 * @throws CommentException
 */
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
	
	
	
	
	
	public boolean updateComment(Comment comment) throws CommentException {
		try {
			int affectedRowNum = commentDao.updateComment(comment);
			if (affectedRowNum > 0) {
				return true;
			}
		    return false;
		     
		} catch (SQLException e) {
			throw new CommentException("修改评论失败！");
		}	
	}
	
	
	public boolean deleteComment(int id) throws  CommentException {
		try {
			int affectedRowNum = commentDao.deleteCommentById(id);
			if (affectedRowNum > 0) {
				return true;
			}
			else {
				throw new CommentException("删除评论失败！");
			}
		} catch (SQLException e) {
			throw new CommentException("删除评论失败！");
		}
	}
	
	
	
	
}
