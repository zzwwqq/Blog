package com.zwq.user.dao;

import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.zwq.comment.domain.Comment;

import cn.itcast.jdbc.TxQueryRunner;

public class HomeDao {
	private QueryRunner qr = new TxQueryRunner();
	
	/**
	 * 获取指定数目的评论
	 * @return
	 */
	public List<Comment>getLimitRowsNumComments(int limitRows) {
		String sql = "select * from comment limit ?,?";
		//qr.query(sql, new BeanListHandler<Comment>(Comment.class),)
		
		
		return null;
		
	}
	
	
	/**
	 * 获取指定数目的博客
	 */
	
	
	
	
	
	
	
}
