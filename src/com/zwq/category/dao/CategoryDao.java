package com.zwq.category.dao;

import java.sql.SQLException;
import java.util.List;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import com.zwq.category.domain.Category;
import cn.itcast.jdbc.TxQueryRunner;

public class CategoryDao {
private QueryRunner qr = new TxQueryRunner();
	

	public void addCategory(String cname,int clevel) throws SQLException {
		String sql = "insert into category(cname,clevel) values(?,?)";
		Object[]params = new Object[] {cname,clevel};
		qr.update(sql,params);
	}
	
	
	public Category findByCidGetCategory(int cid) throws SQLException {
		String sql = "select * from category where cid =?";
		return qr.query(sql, new BeanHandler<Category>(Category.class),cid);
	}
	
	public Category findByCnameAndClevel(String cname, int clevel) throws SQLException {
		String sql = "select * from category where cname=? And clevel=?";
		return qr.query(sql, new BeanHandler<Category>(Category.class),cname,clevel);
	}
	
	
	public List<Category> findCategoryList() throws SQLException {
		String sql = "select * from category order by clevel desc,cid desc";
		 List<Category>categoryList = qr.query(sql, new BeanListHandler<Category>(Category.class));
		 return categoryList;
	}
	
	
	public int deleteCategoryByCid(int cid) throws SQLException {
		String sql = "delete from category where cid =?";
		return qr.update(sql,cid);
	}
	
	public int updateCategoryByCid(Category category) throws SQLException {
		String sql = "update category set cname =?,clevel=? where cid=?";
		Object[]params = new Object[] {category.getCname(),category.getClevel(),category.getCid()};
		return qr.update(sql,params);
	}	
}
