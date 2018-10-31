package com.zwq.page.service;

import java.sql.SQLException;
import com.zwq.blog.domain.Blog;
import com.zwq.page.dao.PageDao;
import com.zwq.page.domain.PageBean;

public class PageService {
	private PageDao pageDao = new PageDao();
	
	public PageBean<Blog>findTotalRecordsGetPageBean(int currentPage) {
		try {
			return pageDao.findBlogTotalRecordsGetPageBean(currentPage);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}	
	}
	
	public PageBean<Blog>findTotalRecordsGetPageBeanByCid(int currentPage,int cid) {
		try {
			return pageDao.findBlogTotalRecordsGetPageBeanByCid(currentPage,cid);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}	
	}
	
	
	
}
