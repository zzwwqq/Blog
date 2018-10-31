package com.zwq.user.service;
import java.io.IOException;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import javax.mail.Session;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.zwq.user.dao.UserDao;
import com.zwq.user.domain.User;
import com.zwq.user.service.exception.UserException;
import com.zwq.user.service.shortmessage.SendShortMessage;
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
	
	
	/**
	 * 找回密码
	 */
	public void findPassword(User userForm) {
		/*
		 * 发送携带了用户密码的短信到用户手机	
		 */
		
		User user = null;
		try {
			//此处user一定不为空，因为Servlet层验证通过后才执行此方法
			user = userDao.findByloginnameAndTelephoneAndAnswer(userForm.getLoginname(), userForm.getTelephone(), userForm.getAnswer());
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		String telephone = userForm.getTelephone();
		SendShortMessage.getCode(telephone,user.getLoginpass());	
	}
	
	/**
	 * 找回密码验证
	 * @return
	 */
	public Map<String,String> validatefindPassword(User userForm,HttpSession session) {
		Map<String, String> errors = new HashMap<String, String>();
		String loginname = userForm.getLoginname();
		String telephone = userForm.getTelephone();
		String answer = userForm.getAnswer();
		String verifyCode = userForm.getVerifyCode();
		String vCode = (String) session.getAttribute("vCode");
		
		/*
		 * 非空校验
		 */
		if (loginname == null || loginname.trim().isEmpty()) {
			errors.put("loginnameError", "用户名不能为空！");
		}
		if (telephone == null || telephone.trim().isEmpty()) {
			errors.put("telephoneError", "手机号不能为空！");
		}
		if (answer == null || answer.trim().isEmpty()) {
			errors.put("answerError", "密保答案不能为空！");
		}
		if (verifyCode == null || verifyCode.trim().isEmpty()) {
			errors.put("verifyCodeError", "验证码不能为空！");
		} 
		if (!verifyCode.equalsIgnoreCase(vCode)) {
			errors.put("verifyCodeError", "验证码错误!");
		}		
		try {
			User user = userDao.findByloginnameAndTelephoneAndAnswer(loginname, telephone, answer);
			if (user == null) {
				errors.put("loginnameError", "用户信息错误!");
			} 
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return errors;	
	}

	public boolean qqLogin(HttpServletRequest request,HttpServletResponse response ,User user) {
		try {
			
			User user2 = userDao.findByOpenId(user.getOpenid());
			/**
			 * openID存在，将uid和openID保存到cookie
			 */
			if (user2 != null) {
				Cookie cookie = new Cookie("uid",user2.getUid());
				Cookie cookie2 = new Cookie("openid", user2.getOpenid());
				cookie.setMaxAge(60*60*24*10);//cookie保存10天
				request.getSession().setAttribute("sessionUser", user2);
				response.addCookie(cookie);
				response.addCookie(cookie2);
				return true;
			} else {//openID不存在
				/**
				 * 判断QQ用户名是否存在
				 */
				boolean  b = userDao.findByQQLoginName(user.getLoginname());
			    if (!b) {
			    	/*
			    	 * QQ用户名不存在，保存用户信息
			    	 */
			    	User user3 = new User();
			    	user3.setLoginname(user.getLoginname());
			    	user3.setUid(CommonUtils.uuid());
			    	user3.setOpenid(user.getOpenid());
			    	user3.setFigureurl_2(user.getFigureurl_2());
			    	user3.setFigureurl_qq_2(user.getFigureurl_qq_2());
			    	user3.setStatus(1);
			    	user3.setGender(user.getGender());
			    	userDao.add(user3);
					request.getSession().setAttribute("sessionUser", user3);
					return true;
			    }
			}			
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return false;
	}	
}
