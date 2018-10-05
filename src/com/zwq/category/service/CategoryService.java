package com.zwq.category.service;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import com.zwq.blog.domain.Blog;
import com.zwq.blog.service.exception.BlogException;
import com.zwq.category.dao.CategoryDao;
import com.zwq.category.domain.Category;

public class CategoryService {
	private CategoryDao categoryDao = new CategoryDao();
	
	

	public void addCategory(Category category) throws SQLException {
		categoryDao.addCategory(category.getCname(), category.getClevel());;
	}
	
	public List<Category> getCategoryList() throws BlogException {
			
			try {
				List<Category>categoryList = categoryDao.findCategoryList();
				if (categoryList == null || categoryList.size() < 0) {
					throw new BlogException("查询所有分类失败！");
				} else {
					return categoryList;
				}
			} catch (SQLException e) {
				throw new BlogException("查询所有分类失败！");
			}
		}
		

		public boolean deleteCategory(int cid) throws BlogException {
			try {
				int affectedRowNum = categoryDao.deleteCategoryByCid(cid);
				if (affectedRowNum > 0) {
					return true;
				}
				else {
					throw new BlogException("删除分类失败！");
				}
			} catch (SQLException e) {
				throw new BlogException("删除分类失败！");
			}
		}


		public boolean updateCategory(Category category) throws BlogException {
			try {
				int affectedRowNum = categoryDao.updateCategoryByCid(category);
				if (affectedRowNum > 0) {
					return true;
				} else {
					return false;
				}
						
			} catch (SQLException e) {
				throw new BlogException("修改分类失败！");
			}		
		}
		
		public Category getCategory(int cid) throws BlogException {
			Category category=null;
			try {
				category = categoryDao.findByCidGetCategory(cid);
				if (category == null) {
					throw new BlogException("查询分类失败！");
				}
				else {
					return category;
				}			
			} catch (SQLException e) {
				throw new BlogException("查询分类失败！");
			}
		}
		
		
		public Category getCategory(String cname,int clevel) throws BlogException {
			Category category=null;
			try {
				category = categoryDao.findByCnameAndClevel(cname, clevel);
				if (category == null) {
					throw new BlogException("查询分类失败！");
				}
				else {
					return category;
				}			
			} catch (SQLException e) {
				throw new BlogException("查询分类失败！");
			}

		}
		
		
		
}
