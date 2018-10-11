package com.zwq.page.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.zwq.blog.domain.Blog;
import com.zwq.page.domain.PageBean;
import com.zwq.page.domain.PageConstant;

import cn.itcast.jdbc.TxQueryRunner;

public class PageDao {
	private QueryRunner qr = new TxQueryRunner();

	/**
	 * 查询所有博客记录，并提取出指定数目的记录数
	 * 最后封装数据到PageBean
	 * @return
	 * @throws SQLException
	 */
	public PageBean findBlogTotalRecordsGetPageBean(int currentPage) throws SQLException {


		String sql = "select count(*) from blog";
		Number number = (Number) qr.query(sql, new ScalarHandler());
		/*
		 * 1.总记录数
		 */
		int totalRecordsNum = number.intValue();
		/*
		 * 2.每页记录数
		 */
		int perPageRecordsNum = new PageConstant().PERPAGERECORDS;

		/*
		 * 3.获取每页记录的内容即beanList limit ?,? 第一个是起始下标，第二个是总行数（不够的话有多少是多少）
		 */
		String sql2 = "select b.bid,b.title,b.content,b.created_time,b.category_id,c.cname from blog b,category c where b.category_id = c.cid order by b.bid desc limit ?,?";
		int startRowIndex = (currentPage - 1) * perPageRecordsNum;
		int perPageRecordsNum2 = perPageRecordsNum;
		Object[] params = new Object[] { startRowIndex, perPageRecordsNum2 };
		List<Blog> blogList= qr.query(sql2, new BeanListHandler<Blog>(Blog.class), params);		

		/*
		 * 创建PageBean
		 * 4.计算总页数
		 * 5.封装数据到PageBean
		 */
		PageBean<Blog> pageBean = new PageBean<Blog>();	
		pageBean.setTotalRecordsNum(totalRecordsNum);//总记录数
		pageBean.setPerPageRecordsNum(perPageRecordsNum2);//每页记录数
		pageBean.setTotalPageNum(pageBean.getTotalPageNum());//4.计算总页数
		pageBean.setBeanList(blogList);//每页数据内容
		pageBean.setCurrentPage(currentPage);//当前页		
		return pageBean;
	}
	
	public PageBean findBlogTotalRecordsGetPageBeanByCid(int currentPage,int cid) throws SQLException {


		String sql = "select count(*) from blog";
		Number number = (Number) qr.query(sql, new ScalarHandler());
		/*
		 * 1.总记录数
		 */
		int totalRecordsNum = number.intValue();
		/*
		 * 2.每页记录数
		 */
		int perPageRecordsNum = new PageConstant().PERPAGERECORDS;

		/*
		 * 3.获取每页记录的内容即beanList limit ?,? 第一个是起始下标，第二个是总行数（不够的话有多少是多少）
		 */
		String sql2 = "select b.bid,b.title,b.content,b.created_time,b.category_id,c.cname from blog b,category c where b.category_id = c.cid and cid = ? order by b.bid desc limit ?,?";
		int startRowIndex = (currentPage - 1) * perPageRecordsNum;
		int perPageRecordsNum2 = perPageRecordsNum;
		Object[] params = new Object[] {cid ,startRowIndex, perPageRecordsNum2 };
		List<Blog> blogList= qr.query(sql2, new BeanListHandler<Blog>(Blog.class), params);		

		/*
		 * 创建PageBean
		 * 4.计算总页数
		 * 5.封装数据到PageBean
		 */
		PageBean<Blog> pageBean = new PageBean<Blog>();	
		pageBean.setTotalRecordsNum(totalRecordsNum);//总记录数
		pageBean.setPerPageRecordsNum(perPageRecordsNum2);//每页记录数
		pageBean.setTotalPageNum(pageBean.getTotalPageNum());//4.计算总页数
		pageBean.setBeanList(blogList);//每页数据内容
		pageBean.setCurrentPage(currentPage);//当前页		
		return pageBean;
	}
	
	
	

	
	
	
	
	
	
	
	

}
