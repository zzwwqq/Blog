<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html style = "background-image:url('/blog/images/background/001.gif')">
<head>

<!-- 和上面的配合使用 -->
<base href="<%=basePath%>">

<title>账号绑定</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

	<!-- 导入css文件 -->
	<link rel="stylesheet" type="text/css" href="<c:url value = '/css/user/qqBind.css'/>">
	<!--导入jQuery文件 -->
	<script type="text/javascript" src = "<c:url value = '/js/jquery-3.2.1.min.js'/>"></script>
	<!-- 导入js文件 -->
	<script type="text/javascript" src = "<c:url value = '/js/user/qqBind.js'/>"></script>	
</head> 
<body>
	<div id = "divMain">
		<div id = "divTitle">
			<span id = "spanTitle">账号绑定</span>
		</div>
		<div id = "divBody">
		<form id = "qqBindForm" action="qqBind.do" method = "post">
			<table id = "tableForm" >
				<tr>
					<td class = "tdText">用户名：</td>
					<td class = "tdInput"><input class = "inputClass" type = "text" name = "loginname" id = "loginname" value = "${userForm.loginname }"/></td>
		            <td class = "tdError"><label id = "loginnameError" class = "errorClass">${errors.loginnameError}</label></td> 	            
				</tr>
				<tr>
					<td class = "tdText">密  码：</td>
					<td class = "tdInput"><input class = "inputClass" type = "password" name = "loginpass" id = "loginpass" value = "${userForm.loginpass }"/></td>
		            <td class = "tdError"><label id = "loginpassError" class = "errorClass">${errors.loginpassError}</label></td> 	            
				</tr>
				<tr> 
					<td class = "tdText"></td>       
			     <td class = "tdInput"><div class = spanDiv><input type = "submit" = "qqBindClass" id = "qqBind" value= "确定"></div></td>				
					<td></td>
				</tr>
			</table>
			</form>
		</div>
		<%@include file="/jsps/footer.jsp"%>
