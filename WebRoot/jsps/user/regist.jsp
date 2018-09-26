<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<!-- 和上面的配合使用 -->
<base href="<%=basePath%>">

<title>注册页面</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

	<!-- 导入css文件 -->
	<link rel="stylesheet" type="text/css" href="<c:url value = 'http://localhost:8080/blog/css/user/regist.css'/>">
	<!--导入jQuery文件 -->
	<script type="text/javascript" src = "<c:url value = '/js/jquery-3.2.1.min.js'/>"></script>
	<!-- 导入js文件 -->
	<script type="text/javascript" src = "<c:url value = '/js/user/regist.js'/>"></script>	


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
	<div id = "divMain">
		<div id = "divTitle">
			<span id = "spanTitle">新用户注册</span>
		</div>
		<div id = "divBody">
		<form id = "registForm" action="regist.do" method = "post">
			<table id = "tableForm" >
				<tr>
					<td class = "tdText">用户名：</td>
					<td class = "tdInput"><input class = "inputClass" type = "text" name = "loginname" id = "loginname" value = "${userForm.loginname }"/></td>

		            <td class = "tdError"><label id = "loginnameError" class = "errorClass">${errors.loginnameError}</label></td> 
		            
				</tr>
				<tr>
					<td class = "tdText">登录密码：</td>
					<td class = "tdInput"><input class = "inputClass" type = "password" name = "loginpass" id = "loginpass" value = "${userForm.loginpass }"/></td>
					<td><label id = "loginpassError" class = "errorClass">${errors.loginpassError}</label></td>
				</tr>
				<tr>
					<td class = "tdText">确认密码：</td>
					<td class = "tdInput"><input class = "inputClass" type = "password" name = "reloginpass" id = "reloginpass" value = "${userForm.reloginpass }"/></td>
					<td><label id = "reloginpassError" class = "errorClass">${errors.reloginpassError}</label></td>
				</tr>
				<tr>
					<td class = "tdText">Email：</td>
					<td class = "tdInput"><input class = "inputClass" type = "text" name = "email" id = "email" value = "${userForm.email }"/></td>
					<td><label id = "emailError" class = "errorClass">${errors.emailError}</label></td>
				</tr>
				<tr>
					<td class = "tdText">图形验证码：</td>
					<td ><input class = "inputClass" type = "text" name = "verifyCode" id = "verifyCode" value = "${userForm.verifyCode }"></td>
					<td><label id = "verifyCodeError" class = "errorClass">${errors.verifyCodeError}</label></td>
				</tr>
				<tr>
					<td class = "tdText"></td>
					<td><div id = "divVerifyCode"><img  id = "imgVerifyCode" alt = "无图片!"  src="<c:url value = '/VerifyCodeServlet' />"></div></td>
					<td><label><a href = "javascript:_change()">换一张</a></label></td>
				</tr>
				<tr> 
					<td class = "tdText"></td>
             
			<td class = "tdInput"><input  id = "submitBtn" type = "image" src = "<c:url value = '/images/regist1.jpg'/>" /></td>
					
					<td></td>
				</tr>
			</table>
			</form>
		</div>
	</div>
</body>
</html>