package com.zwq.admin.service;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;

import com.zwq.admin.dao.AdminUserDao;
import com.zwq.admin.domain.Admin;
import com.zwq.admin.service.exception.AdminUserException;

public class AdminUserService {
	private AdminUserDao adminUserDao = new AdminUserDao();
	
	
	public boolean ajaxValidateAdminName(String adminbane) throws ServletException, IOException, SQLException {
		boolean b = adminUserDao.ajaxValidateAdminName(adminbane);
		return b;
	}
	
	public Admin adminLogin(String adminname,String adminpassword) {
		Admin admin = adminUserDao.findByAdminNameAndAdminPassword(adminname, adminpassword);
		return admin;
	}	
	
	/**
	 * 修改密码
	 * @throws AdminUserException 
	 */
	public void updatePassword(int adminId,String newAdminPass,String oldAdminPass) throws AdminUserException {
		
		/*
		 * 验证原密码
		 */
			try {
				boolean b = adminUserDao.findByAdminIdAndPassword(adminId, oldAdminPass);
			    if (!b) {
					throw new AdminUserException("原密码错误！");
				}
	     
			    /*
			     * 原密码正确时，
			     * 修改密码
			     */
			    adminUserDao.updatePassword(adminId, newAdminPass);   
			} catch (SQLException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		 
	}
	
	
	
	
}
