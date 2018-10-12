<%@ page contentType="text/html; charset=utf-8" language="java" import="java.util.*" errorPage="" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import = "com.zwq.user.domain.User" %>
<%@ page import = "com.zwq.admin.domain.Admin" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<title></title>
<base href="<%=basePath%>">
<meta http-equiv="content-type" content="text/html;charset=UTF-8">
<link href="css/header/style.css" type="text/css" rel="stylesheet"> 
<script type="text/javascript" src = "<c:url value = '/js/jquery-3.2.1.min.js'/>"></script>


</head>
<body>
<div id="container">	
<div id="banner">
		<h1>zwq的博客</h1>
</div>
<div id="menu">
 <%
 Admin admin = (Admin)request.getSession().getAttribute("sessionUser");
  if(admin != null){
  	 out.print("欢迎"+admin.getAdminname()+"用户!");
  } else{
  
  %>
  	<a href="/blog/jsps/admin/adminLogin.jsp">您还未登录，点击这里登录</a>
 <%
  }
  %>
  	 | <a id = "postBlog" href="AdminBlogServlet?method=displayBlogList">查看所有博客</a>
	 | <a id = "postBlog" href="BlogServlet?method=preAddBlog">发布博客</a>
	 | <a id = "managerBlog" href="AdminBlogServlet?method=getBlogList">博客管理</a>
	 | <a id = "addCategory" href="CategoryServlet?method=preAddCategory">添加分类</a>
	 | <a id = "managerCategory" href="CategoryServlet?method=getCategoryList">分类管理</a>
 	 | <a id = "managerComment" href="CommentServlet?method=getCommentList">评论管理</a>
	 | <a id = "updatePassword" href="jsps/admin/updatePassword.jsp?admin=<%=request.getSession().getAttribute("sessionUser")%>">修改密码</a>
	 | <a id = "quit" href="AdminUserServlet?method=quit">退出</a>
</div>
<br/>
<div id="main">

