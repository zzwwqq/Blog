package com.zwq.comment.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.zwq.comment.domain.Comment;

import cn.itcast.jdbc.TxQueryRunner;

public class CommentDao {
	private QueryRunner qr = new TxQueryRunner();
	
	public List<Comment>findCommentListByBlog_Id(int blog_id) throws SQLException {
		String sql = "select * from comment where blog_id =?";
		return qr.query(sql, new BeanListHandler<Comment>(Comment.class),blog_id);		
	}
	
	public int addComment(Comment comment) throws SQLException {
		String sql = "insert into comment(blog_id,username,content,createdtime) values(?,?,?,?)";
		Object[]params = new Object[] {comment.getBlog_id(),comment.getUsername(),comment.getContent(),comment.getCreatedtime()};
		return qr.update(sql,params);
	}
	
	
	
	
	
	
	
	
	
	
}
