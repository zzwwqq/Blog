package com.zwq.user.web.servlet;

import java.io.IOException;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.zwq.user.domain.User;
import com.zwq.user.service.UserService;

import cn.itcast.commons.CommonUtils;


public class UserServlet extends HttpServlet{

	private UserService userService = new UserService();
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

/**
 * 此方式会在浏览器地址栏暴露method=xxx,不私密，有安全隐患	
 * @param request
 * @param response
 * @throws ServletException
 * @throws IOException
 */
//	@Override
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		String method = request.getParameter("method");
//		switch (method) {
//		case "login":
//			login(request, response);
//			break;
//		default:
//			break;
//		}
//	}
	

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.获取ServletPath: /login.do
		String servletPath = request.getServletPath();
		
		//2. 去除/ 和 .do,得到类似于edit或addCustomer 这样的字符串
		String methodName = servletPath.substring(1);
		methodName = methodName.substring(0, methodName.length()-3);
		
		try {
			//3. 利用反射获取methodName对应的方法
            Method method = getClass().getDeclaredMethod(methodName, HttpServletRequest.class,HttpServletResponse.class);
			
			//4.利用反射调用相应的方法
			method.invoke(this, request,response);
		} catch (Exception e) {
			e.printStackTrace();
		} 	
	}
		
	private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("login");
	}

	/**
	 * 注册
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 * @throws SQLException 
	 */
	private void regist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException{
	//System.out.println("regist");	
		/*
		 * 封装表单数据到User对象
		 */
		User userForm = CommonUtils.toBean(request.getParameterMap(), User.class);
		
		/*
		 * 校验
		 * 校验失败则保存错误信息，同时保存表单数据用于回显，并返回regist.jsp
		 */
		Map<String, String> errors = validateRegist(userForm, request.getSession());
		/*
		 * 验证失败
		 */
		if (errors.size() > 0) {
			//保存错误信息，保存表单数据用于回显
			request.setAttribute("userForm",userForm);
			request.setAttribute("errors", errors);
			//转发
			request.getRequestDispatcher("/jsps/user/regist.jsp").forward(request, response);
			/*
			 * 小提示：
             * forward(request, response);跳转页面，执行这个语句之后，如果此语句后面还有代码，既然跳转了页面后面代码执行不了，原页面的代码没有终止一定会出错。
             * 解决方法，在forward(request, response);跳转后面不要写其他代码，且加上 return；
			 */
			return;
		}
		/*
		 * 校验成功，则调用service层方法保存用户注册信息到数据库
		 */
		userService.regist(userForm);
		/*
		 * 保存成功信息，并转发到msg.jsp
		 */	
		request.setAttribute("code", "success");
		request.setAttribute("msg", "注册成功，请马上去邮箱激活！");
		request.getRequestDispatcher("index.jsp").forward(request, response);
		return;
	}
	
	/**
	 * 验证所有注册信息
	 * 这是后台校验，较前台校验更安全
	 * 将错误信息保存在Map中
	 * 要点：向HashMap中添加元素时，会调用key所在类的equals()方法，判
	 *     断两个key是否相同，若相同 ,则只能添加进后添加的那个元素。
	 * @param userForm
	 * @param session
	 * @return
	 * @throws IOException 
	 * @throws ServletException 
	 * @throws SQLException 
	 */
	private Map<String,String> validateRegist(User userForm,HttpSession session) throws ServletException, IOException, SQLException {
		Map<String, String> errors = new HashMap<String, String>();
		/*
		 * 验证用户名
		 * 注意两种情形：第一种用户名为空null，
		 *           第二种用户名为空格（注意null != 空格）
		 *   trim函数去掉空格      
		 */
		if(userForm.getLoginname() == null || userForm.getLoginname().trim().isEmpty()) {
			errors.put("loginnameError", "用户名不能为空");
		} else if (userForm.getLoginname().length() < 3 || userForm.getLoginname().length() > 20) {
			errors.put("loginnameError", "用户名长度必须在3~20之间！");
		} else if (!userService.ajaxValidateLoginname(userForm.getLoginname())) {
			errors.put("loginnameError", "用户名已被注册！");
		}
				
		/*
		 * 验证密码
		 */
		String loginpass = userForm.getLoginpass();
		if(loginpass == null || loginpass.trim().isEmpty()) {
			errors.put("loginnameError", "密码不能为空");
		} else if (loginpass.length() < 6 || loginpass.length() > 16) {
			errors.put("loginnameError", "密码长度必须在6~16之间！");
		}
			
		/*
		 * 验证确认密码
		 */
		String reloginpass = userForm.getReloginpass();
		if(!loginpass.equals(reloginpass)) {
			errors.put("reloginpassError", "两次输入的密码不一致！");
		} else if(reloginpass == null || reloginpass.trim().isEmpty()) {
			errors.put("reloginpassError", "确认密码不能为空！");
		}

		/*
		 * 验证邮箱
		 */
		String email = userForm.getEmail();
		if(email == null || email.trim().isEmpty()) {
			errors.put("emailError", "Email不能为空！");
			//java和js有区别：/^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\\.[a-zA-Z0-9_-]{2,3}){1,2})$/中的/要去掉，\要改为\\
		} else if(!email.matches("^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\\.[a-zA-Z0-9_-]{2,3}){1,2})$")){
			errors.put("emailError", "Email格式错误！");
		} else if (!userService.ajaxValidateEmail(email)) {
			errors.put("emailError", "Email已被注册！");
		}
		
		/*
		 * 验证验证码
		 */
		String verifyCode = userForm.getVerifyCode();
		String vCode = (String) session.getAttribute("vCode");
		if(verifyCode==null || verifyCode.trim().isEmpty()) {
			errors.put("verifyCodeError", "验证码不能为空!");
		} else if(verifyCode.length() != 4) {
			errors.put("verifyCodeError", "验证码长度不对!");
		} else if (!verifyCode.equalsIgnoreCase(vCode)) {
			errors.put("verifyCodeError", "验证码错误!");
		}		
		return errors;	
	}
	

	
	
	
	
	
	
	
	
	
	/**
	 * 前台js文件中ajax请求该函数
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 * @throws SQLException
	 */
	private void ajaxValidateLoginname(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		
		/*
		 * 测试使用
		 * System.out.println("ajaxValidateLoginname");
		 * response.getWriter().print(true);
		 */
		String loginname = request.getParameter("loginname");
		boolean b = userService.ajaxValidateLoginname(loginname);	
		/*
		 * 将信息返回客户端
		 */
		response.getWriter().print(b);
	}
	
	private void ajaxValidateEmail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		//System.out.println("ajaxValidateEmail");
		//response.getWriter().print(true);
		String email = request.getParameter("email");
		boolean b = userService.ajaxValidateEmail(email);
		response.getWriter().print(b);
	}
	
	/**
	 * 验证码保存在session中，而不是数据库中，所以此处直接比较session中的验证码和用户输入的验证码
	 */
	private void ajaxValidateVerifyCode(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//System.out.println("ajaxValidateVerifyCode");
		//response.getWriter().print(true);
		/*
		 * 从表单获取验证码
		 */
		String verifyCode = request.getParameter("verifyCode");
		/*
		 * 从session中获取验证码
		 */
		String vCode = (String) request.getSession().getAttribute("vCode");
		
		/*
		 * 比较验证码，注意：忽略大小写
		 */
		boolean b = verifyCode.equalsIgnoreCase(vCode);
		/*
		 * 返回信息到客户端
		 */
		response.getWriter().print(b);
	}
}
