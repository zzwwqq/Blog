package com.zwq.blog.dao;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.zwq.blog.domain.Blog;
import com.zwq.category.domain.Category;
import com.zwq.page.domain.PageConstant;

import cn.itcast.jdbc.TxQueryRunner;

public class BlogDao {
	private QueryRunner qr = new TxQueryRunner();
	
	public void addBlog(String title,String content,int category_id,Timestamp created_time) throws SQLException {
		String sql = "insert into blog(title,content,category_id,created_time) values(?,?,?,?)";
		Object[]params = new Object[] {title,content,category_id,created_time};
		qr.update(sql,params);
	}
	
	public List<Category> findCategoryList() throws SQLException {
		String sql = "select * from category order by clevel desc,cid desc";
		 List<Category>categoryList = qr.query(sql, new BeanListHandler<Category>(Category.class));
		 return categoryList;
	}
	
	public Blog findByBidGetBlog(int bid) throws SQLException {
		String sql = "select * from blog where bid =?";
		return qr.query(sql, new BeanHandler<Blog>(Blog.class),bid);
	}
	
	public List<Blog> findBlogList() throws SQLException {
		String sql = "select * from blog  order by bid desc limit ?,?";
		 List<Blog>bloglist = qr.query(sql, new BeanListHandler<Blog>(Blog.class),0,5);
		 return bloglist;
	}
	
	public Blog findByTitleAndCategory_IdAndContent(String title, int category_id,String content) throws SQLException {
		String sql = "select * from blog where title=? And category_id=? And content=?";
		return qr.query(sql, new BeanHandler<Blog>(Blog.class),title,category_id,content);
	}	
	
	public List<Blog>getLimitRowNumTitleList() throws SQLException {
		String sql = "select * from blog order by bid desc limit ?,?";
		Object[]params = new Object[] {0,new PageConstant().LIMITTITLEROWNUM};
		return qr.query(sql, new BeanListHandler<Blog>(Blog.class),params);
	}
	
	
	
	
	
	
	
}
