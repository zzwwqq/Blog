<%@ page contentType="text/html; charset=utf-8" language="java" import="java.util.*" errorPage=""%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.zwq.admin.domain.Admin"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<title></title>
<base href="<%=basePath%>">
<meta http-equiv="content-type" content="text/html;charset=UTF-8">
<link href="/blog/css/admin/style.css" type="text/css" rel="stylesheet">
<script type="text/javascript" src="<c:url value = '/js/jquery-3.2.1.min.js'/>"></script>
<script type="text/javascript" src="<c:url value = '/js/admin/adminLogin.js'/>"></script>


<script type="text/javascript">
	function _change() {
		/*
		 * 1. 获取<img>元素
		 * 2. 重新设置它的src
		 * 3. 使用毫秒来添加参数,为了干掉浏览器缓存
		 */
		$("#imgVerifyCode").attr("src", "VerifyCodeServlet?a=" + new Date().getTime());
	}
</script>

</head>
<body>
	<div id="container">
		<div id="banner">
			<h1>zwq的博客</h1>
		</div>
		<center>
			<form id="adminLoginForm" name="adminLoginForm" method="post"
				action="AdminUserServlet">
				<input type="hidden" name="method" value="adminLogin" />
				<table border="0px">
					<tr>
						<td  align="right">用户名：</td>
						<td >
						<input type="text" name="adminname" id="adminname" class = "inputClass" value = "${adminForm.getAdminname() }"/></td>
						<td ><label class="errorClass" id = "adminnameError">${errors.adminnameError }</label></td>
					</tr>
					<tr>
						<td  align="right">密码：</td>
						<td >
						<input type="text" name="adminpassword" id="adminpassword" class = "inputClass"/></td>
						<td ><label class="errorClass" id = "adminpasswordError">${errors.adminpasswordError }</label></td>
					</tr>
						<tr>
						<td  align="right">验证码：</td>
						<td >
						<input type="text" name="verifyCode" id="verifyCode" class = "verify"/></td>
						<td ><label class="errorClass" id = "verifyCodeError">${errors.verifyCodeError }</label></td>
					</tr>
					<tr>
						<td align="right"></td>
						<td>
						    <div >
								<img style="display:block;" text-align="right" id="imgVerifyCode" class = "inputClass" alt="验证码"
									src="<c:url value = '/VerifyCodeServlet' />">
							</div>
						</td>
						<td align="left"></td>
					</tr>

					<tr>
						<td></td>
						<td>
						     <div>
								<a style="font-size: 16px" href="javascript:_change()">看不清?换一张</a>
							</div>
					    </td>
						<td></td>
					</tr>

					<tr>
						<td>&nbsp;</td>
						<td align="left">
						<input type="submit" name="button" id="button" value="登录" />
						&nbsp;&nbsp;<input type="reset" name="button2" id="button2" value="重置" /></td>
						<td></td>
					</tr>
				</table>
			</form>
		</center>
		<%@ include file="/jsps/footer.jsp"%>