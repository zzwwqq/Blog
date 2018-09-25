package com.zwq.user.service;
import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import com.zwq.user.dao.UserDao;
import com.zwq.user.domain.User;

import cn.itcast.commons.CommonUtils;

public class UserService {
	private UserDao userDao = new UserDao();
	
	/**
	 * 注册功能
	 * @param user
	 * @throws SQLException 
	 */
	public void regist(User user) throws SQLException {
		/*
		 * 补齐数据
		 */
		user.setUid(CommonUtils.uuid());
		user.setActivationCode(CommonUtils.uuid()+CommonUtils.uuid());
		user.setStatus(0);
		/*
		 * 保存到数据库
		 */
		userDao.add(user);	
	}
	
	/**
	 * 验证用户名是否注册
	 * @param loginname
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 * @throws SQLException
	 */
	public boolean ajaxValidateLoginname(String loginname) throws ServletException, IOException, SQLException {
		boolean b = userDao.ajaxValidateLoginname(loginname);
		return b;
	}
	
	/**
	 * 验证邮箱是否注册
	 * @param email
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 * @throws SQLException
	 */
	public boolean ajaxValidateEmail(String email) throws ServletException, IOException, SQLException {
		boolean b = userDao.ajaxValidateEmail(email);
		return b;
	}	
}
