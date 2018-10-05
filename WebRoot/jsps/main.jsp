<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'main.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script type="text/javascript"
src="//connect.qq.com/qc_jssdk.js" charset="utf-8" data-callback="true">
</script>





  </head>
  
  <body>
     <h1><%=request.getAttribute("code") %></h1>
     <h2><%=request.getAttribute("msg") %></h2>
<%
 Object c = (Object)request.getAttribute("code");

 %>
    <a href="jsps/user/updatePassword.jsp?user=<%=request.getSession().getAttribute("sessionUser")%>">修改密码</a>
    <a href = "jsps/user/regist.jsp">注册</a>
    <a href = "jsps/user/login.jsp">登录</a>
    <a href = "index.jsp?a=123&b='qq'&c=<%=request.getAttribute("code")%>">状态</a>
    
    
    
  </body>
</html>
