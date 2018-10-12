<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8"%>
<%@ page import = "com.zwq.category.domain.Category" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<!-- 和上面的配合使用 -->
<base href="<%=basePath%>">

<title>修改博客分类</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

	<!-- 导入css文件 -->
	<link rel="stylesheet" type="text/css" href="<c:url value = '/css/category/addCategory.css'/>">
	<!--导入jQuery文件 -->
	<script type="text/javascript" src = "<c:url value = '/js/jquery-3.2.1.min.js'/>"></script>
	<!-- 导入js文件 -->
</head>
 
<body>
<%
    Category category = (Category)request.getAttribute("category");
   %> 

	<div id = "divMain">
		<div id = "divTitle">
			<span style="text-align: center;display:block;" id = "spanTitle">修改博客分类</span>
		</div>
		<div id = "divBody">
		<form id = "editCategory" action="CategoryServlet" method = "post">
		<input type = "hidden" name = "method" value = "updateCategory"/>
		<input type = "hidden" name = "cid" value = "<%=category.getCid() %>" />
			<table id = "tableForm">
				<tr>
					<td class = "tdText">分类的名称：</td>
					<td class = "tdInput"><input class = "inputClass" type = "text" name = "cname" id = "cname" value = "<%=category.getCname()%>"/></td>		            
				    <td class = "tdText"></td>
				</tr>
				<tr>
					<td class = "tdText">分类的显示顺序：</td>
					<td class = "tdInput"><input class = "inputClass" type = "text" name = "clevel" id = "clevel" value = "<%=category.getClevel()%>"/></td>
				<td class = "tdText"></td>
				</tr>
			    <tr> 
				<td class = "tdText"></td>
			     <td class = "tdInput"><div class = spanDiv>
			     <input type = "submit" name = "findPasswordClass" id = "findPassword" value= "提交">
			     <input type = "reset" name = "reset" id = "reset" value= "重置">
			     </div>
			     </td>
				<td class = "tdText"></td>
				</tr>
			</table>
			</form>
		</div>
	</div>
</body>
</html>
