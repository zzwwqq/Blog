package com.zwq.user.service;
import java.io.IOException;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.Properties;
import javax.mail.Session;
import javax.servlet.ServletException;
import com.zwq.user.dao.UserDao;
import com.zwq.user.domain.User;
import com.zwq.user.service.exception.UserException;
import cn.itcast.commons.CommonUtils;
import cn.itcast.mail.Mail;
import cn.itcast.mail.MailUtils;

public class UserService {
	private UserDao userDao = new UserDao();
	
	/**
	 * 注册功能
	 * @param user
	 * @throws SQLException 
	 * @throws IOException 
	 */
	public void regist(User user) throws SQLException{
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
		
		/*
		 * 发送邮件
		 */
		Properties prop = new Properties();
		try {
			prop.load(this.getClass().getClassLoader().getResourceAsStream("email_template.properties"));
		} catch (IOException e) {
			try {
				throw new UserException("加载邮箱配置文件失败！");
			} catch (UserException e1) {
				// TODO 自动生成的 catch 块
				e1.printStackTrace();
			}
		}
		/*
		 * 根据主机，登录名，密码创建session
		 */
		String host = prop.getProperty("host");
		String username = prop.getProperty("username");
		String password = prop.getProperty("password");
		Session session = MailUtils.createSession(host, username, password);
		
		/*
		 * 根据from to subject content创建Mail对象
		 */
		//MessageFormat.format方法会把第一个参数中的{0}，使用第二个参数来替换
    	//例如：MessageFormat.format(你好{0},我叫{1},"张三","李四")；返回：“你好张三，我叫李四”
    	//邮件内容
		String from = prop.getProperty("from");
		String to = user.getEmail();
		String subject = prop.getProperty("subject");
		String tempcontent = prop.getProperty("content");
		String content = MessageFormat.format(tempcontent, user.getActivationCode());
		Mail mail = new Mail(from, to, subject, content);
		/*
		 * 根据session和Mail对象发送邮件
		 */
		try {
			MailUtils.send(session, mail);
		} catch (Exception e) {
			try {
				throw new UserException("发送邮件失败，请检查邮箱是否正确！");
			} catch (UserException e1) {
				// TODO 自动生成的 catch 块
				e1.printStackTrace();
			}
		}
		
	}
	
	/**
	 * 激活功能
	 * @param activationCode
	 * @throws UserException 
	 */
	public void activation(String activationCode) throws UserException {
		User user = userDao.findByCode(activationCode);
		if(user == null) {
			throw new UserException("无效的激活码！");
		} 
		if (user.getStatus() == 1) {
			throw new UserException("您已经激活，不能二次激活！");
		}
		try {
			userDao.updateStatus(user.getUid(), 1);
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		
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
	
	public boolean ajaxValidateTelephone(String telephone) throws SQLException {
		return userDao.ajaxValidateTelephone(telephone);		
	}
	
	
	/**
	 * 登录功能
	 * @param loginname
	 * @param loginpass
	 */
	public User login(String loginname,String loginpass) {
		User user = userDao.findByLoginnameAndLoginPass(loginname, loginpass);
		return user;
	}	
	
	/**
	 * 修改密码
	 * @throws UserException 
	 */
	public void updatePassword(String uid,String newPass,String oldPass) throws UserException {
		
		/*
		 * 验证原密码
		 */
			try {
				boolean b = userDao.findByUidAndPassword(uid, oldPass);
			    if (!b) {
					throw new UserException("原密码错误！");
				}
	     
			    /*
			     * 原密码正确时，
			     * 修改密码
			     */
			    userDao.updatePassword(uid, newPass);   
			} catch (SQLException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		 
	}
	
	
	
	
	
}
