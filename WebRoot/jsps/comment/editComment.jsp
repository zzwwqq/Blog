<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8"%>
<%@ page import = "com.zwq.comment.domain.Comment" %>
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

<title>修改评论</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

	<!-- 导入css文件 -->
	<link rel="stylesheet" type="text/css" href="<c:url value = '/css/comment/comment.css'/>">
	<!--导入jQuery文件 -->
	<script type="text/javascript" src = "<c:url value = '/js/jquery-3.2.1.min.js'/>"></script>
	<!-- 导入js文件 -->
</head>
 
<body>
<%Comment comment = (Comment)request.getAttribute("comment");%>

	<div id = "divMain">
		<div id = "divTitle">
			<span style="text-align: center;display:block;" id = "spanTitle">修改评论</span>
		</div>
		<div id = "divBody">
		<form id = "editComment" action="CommentServlet" method = "post">
		<input type = "hidden" name = "method" value = "updateComment"/>
		<input type = "hidden" name = "id" value = "<%=comment.getId() %>" />
		<input type = "hidden" name = "blog_id" value = "<%=comment.getBlog_id() %>" />
			
			<table id = "tableForm">
				<tr>
					<td class = "tdText">评论人：</td>
					<td class = "tdInput"><input class = "inputClass" type = "text" name = "username" id = "username" value = "<%=comment.getUsername()%>"/></td>		            
				    <td class = "tdText"></td>
				</tr>
				<tr>
					<td class = "tdText">评论内容：</td>
					<td class = "tdInput"><input class = "inputClass" type = "text" name = "content" id = "content" value = "<%=comment.getContent()%>"/></td>
				<td class = "tdText"></td>
				</tr>
			    <tr> 
				<td class = "tdText"></td>
			     <td class = "tdInput"><div class = spanDiv>
			     <input type = "submit" name = "findPasswordClass" id = "findPassword" value= "修改">
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
