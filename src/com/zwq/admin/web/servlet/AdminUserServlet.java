package com.zwq.admin.web.servlet;

import java.io.IOException;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.zwq.admin.domain.Admin;
import com.zwq.admin.service.AdminUserService;
import com.zwq.admin.service.exception.AdminUserException;
import com.zwq.user.domain.User;
import com.zwq.user.service.exception.UserException;

import cn.itcast.commons.CommonUtils;
import cn.itcast.servlet.BaseServlet;

public class AdminUserServlet extends BaseServlet {
	private AdminUserService adminUserService = new AdminUserService();
	
	public void ajaxValidateAdminName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {			 
		String adminname = request.getParameter("adminname");
		boolean b = adminUserService.ajaxValidateAdminName(adminname);	
		/*
		 * 将信息返回客户端
		 */
		response.getWriter().print(b);
	}
	
	public void ajaxValidateVerifyCode(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

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
	
	public void adminLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//System.out.println("login");
		/*
		 * 封装表单数据
		 */
		Admin adminForm = CommonUtils.toBean(request.getParameterMap(), Admin.class);
		
		/*
		 * 校验
		 * 如果校验失败，返回登录界面，并回显表单数据
		 * 特别提示：如果转发语句的后面还有语句，那么一定要在转发语句后面添加return;否则会抛异常
		 */
		Map<String, String> errors = validateAdminLogin(adminForm, request.getSession());
		if (errors.size() > 0) {
			request.setAttribute("adminForm", adminForm);
			request.setAttribute("errors", errors);
			/*
			 * 特别提示：如果转发语句的后面还有语句，那么一定要在转发语句后面添加return;否则会抛异常
			 * 转发时，路径中第一个"/"代表" http://localhost:8080/blog/"
			 * 重定向，时路径中第一个"/"代表" http://localhost:8080/"
			 */
			request.getRequestDispatcher("/jsps/admin/adminLogin.jsp").forward(request, response);
			return;
		}
		/*
		 * 校验成功，保存用户数据到session和cookie中，并跳转到成功界面
		 */
		Admin admin = adminUserService.adminLogin(adminForm.getAdminname(), adminForm.getAdminpassword());
		request.getSession().setAttribute("sessionUser", admin);
		URLEncoder.encode(admin.getAdminname(), "UTF-8");
		Cookie cookie = new Cookie("adminname", admin.getAdminname());
		cookie.setMaxAge(60*60*24*10);//cookie保存10天
		response.addCookie(cookie);
		request.setAttribute("code", "success");
		request.setAttribute("msg", "恭喜您登录成功！");
		//重定向后，request被清空，不能再获取request中的数据
		//response.sendRedirect("/blog/index.jsp");
		//转发
		request.getRequestDispatcher("/AdminBlogServlet?method=displayBlogList").forward(request, response);
	}
	
	/**
	 * 登录校验
	 * @return
	 */
	private Map<String, String> validateAdminLogin(Admin adminForm,HttpSession session) {
		Map<String, String>errors=new HashMap<String, String>();
		String adminname = adminForm.getAdminname();
		String adminpassword = adminForm.getAdminpassword();
		String verifyCode = adminForm.getVerifyCode();
		Admin admin = new Admin();
		/*
		 * 验证用户名
		 */
		if (adminname == null|| adminname.trim().isEmpty()) {
			errors.put("adminnameError", "用户名不能为空！");
		}
			
		/*
		 * 验证密码
		 */
		if(adminpassword == null || adminpassword.trim().isEmpty()) {
			errors.put("adminpasswordError", "密码不能为空！");
		}
		admin = adminUserService.adminLogin(adminname, adminpassword);
		if(admin == null) {
			errors.put("adminpasswordError", "用户名或密码错误!");
		} 		
		/*
		 * 验证验证码
		 */
	    String vCode = (String) session.getAttribute("vCode");
		if (verifyCode == null || verifyCode.trim().isEmpty()) {
			errors.put("verifyCodeError", "验证码不能为空！");
		}
		if (verifyCode.length() != 4) {
			errors.put("verifyCodeError","验证码长度不对!");
		}
		if(!verifyCode.equalsIgnoreCase(vCode)) {
			errors.put("verifyCodeError", "验证码错误!");
		}
		return errors;		
	}
	
	public void quit(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException {
		//手动销毁session
		request.getSession().invalidate();
		request.getRequestDispatcher("/jsps/admin/adminLogin.jsp").forward(request, response);
	}
	
	/**
	 * 修改密码
	 * @throws IOException 
	 * @throws ServletException 
	 */
	public void updatePassword(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		/*
		 * 1.封装表单数据到user中
		 * 2.校验
		 * 3.从session中获取uid
		 * 4.使用uid和表单中的oldPass和newPass来调用service方法
		 * >如果出现异常，保存异常信息到request，转发到pwd.jsp
		 * 5.保存成功信息到request中
		 * 6.转发到msg.jsp
		 */

		Admin adminForm = CommonUtils.toBean(request.getParameterMap(), Admin.class);

		Map<String, String> errors = validateUpdatePassword(adminForm, request.getSession());
		if (errors.size() > 0) {
			request.setAttribute("adminForm", adminForm);
			request.setAttribute("errors", errors);
			request.getRequestDispatcher("/jsps/admin/updatePassword.jsp").forward(request, response);
			return;
		}	
		Admin admin = (Admin) request.getSession().getAttribute("sessionUser");
		if(admin == null) {
			request.setAttribute("msg", "您还未登录!");
			request.setAttribute("code", "error");
			request.getRequestDispatcher("/jsps/admin/msg.jsp").forward(request, response);;
			return;
		}
		/*
		 * 用户已经登录
		 */
		try {
			adminUserService.updatePassword(admin.getAdminId(), adminForm.getNewadminpassword(), adminForm.getAdminpassword());	
			request.setAttribute("msg", "修改密码成功！");
			request.setAttribute("code", "success");
			request.getRequestDispatcher("/jsps/admin/msg.jsp").forward(request, response);;
			return;
		} catch (AdminUserException e) {
			request.setAttribute("msg", e.getMessage());
			request.setAttribute("adminForm", adminForm);
			request.getRequestDispatcher("/jsps/admin/updatePassword.jsp").forward(request, response);;
			return;
		}
	
	}
	
	private Map<String,String> validateUpdatePassword(Admin  adminForm,HttpSession session) {
		Map<String, String>errors=new HashMap<String, String>();
		String adminpassword = adminForm.getAdminpassword();
		String newadminpassword = adminForm.getNewadminpassword();
		String readminpassword = adminForm.getReadminpassword();
		String verifyCode = adminForm.getVerifyCode();
		String vCode = (String) session.getAttribute("vCode");

		/*
		 * 非空校验 ，格式校验
		 */
		if (adminpassword == null || adminpassword.trim().isEmpty()) {
			errors.put("adminpasswordError", "原密码不能为空！");
		} 
		if (newadminpassword == null || newadminpassword.trim().isEmpty()) {
			errors.put("newadminpasswordError", "新密码不能为空！");
		} 
		if (readminpassword == null || readminpassword.trim().isEmpty()) {
			errors.put("readminpasswordError", "确认密码不能为空！");
		} else if(!newadminpassword.equals(readminpassword)) {
			errors.put("readminpasswordError", "两次密码不一致！");
		}
		
		if (verifyCode == null || verifyCode.trim().isEmpty()) {
			errors.put("verifyCodeError", "验证码不能为空！");
		}  else if(verifyCode.length() != 4) {
			errors.put("verifyCodeError", "验证码长度不对!");
		} else if (!verifyCode.equalsIgnoreCase(vCode)) {
			errors.put("verifyCodeError", "验证码错误!");
		}	
		return errors;
		
	}
	
	
	
}
