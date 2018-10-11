package com.zwq.comment.dao;

import java.sql.SQLException;
import java.util.List;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.zwq.blog.domain.Blog;
import com.zwq.comment.domain.Comment;
import com.zwq.page.domain.PageConstant;

import cn.itcast.jdbc.TxQueryRunner;

public class CommentDao {
	private QueryRunner qr = new TxQueryRunner();
	
	public List<Comment>findCommentListByBlog_Id(int blog_id) throws SQLException {
		String sql = "select * from comment where blog_id =? order by id desc";
		return qr.query(sql, new BeanListHandler<Comment>(Comment.class),blog_id);		
	}
	
	public Comment findCommentById(int id) throws SQLException {
		String sql = "select * from comment where id =?";
		return qr.query(sql, new BeanHandler<Comment>(Comment.class),id);		
	}
	
	
	
	
	
	public List<Comment> findCommentList() throws SQLException {
		String sql = "select * from comment order by blog_id desc,id desc";
		 List<Comment>commentlist = qr.query(sql, new BeanListHandler<Comment>(Comment.class));
		 return commentlist;
	}
	
	
	
	
	
	public int addComment(Comment comment) throws SQLException {
		String sql = "insert into comment(blog_id,username,content,createdtime) values(?,?,?,?)";
		Object[]params = new Object[] {comment.getBlog_id(),comment.getUsername(),comment.getContent(),comment.getCreatedtime()};
		return qr.update(sql,params);
	}
	
	public int updateComment(Comment comment) throws SQLException {
		String sql = "update comment set username=?,content=? where id=?";
		Object[]params=new Object[] {comment.getUsername(),comment.getContent(),comment.getId()};		
		return qr.update(sql,params);
	}
	
	
	public int deleteCommentById(int id) throws SQLException {
		String sql = "delete from comment where id =?";
		return qr.update(sql,id);
	}
	
	public List<Comment> getLimitRowNumComment() throws SQLException{
		String sql = "select * from comment order by id desc limit ?,?";
		Object[]params = new Object[] {0,new PageConstant().LIMITCOMMENTROWNUM};
		return qr.query(sql, new BeanListHandler<Comment>(Comment.class),params);	
	}
	
	
	
	
	
	
	
	
}
