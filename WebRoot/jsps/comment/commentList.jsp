<%@ page contentType="text/html; charset=utf-8" language="java"
	import="java.util.List" errorPage=""%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page import="com.zwq.comment.domain.Comment"%>


<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>评论管理</title>
</head>

<body>
	<div align="center">
	  <p>评论管理</p>
		<table width="1129" height="48" border="1" cellpadding="0"
			cellspacing="0">
			<tr align="center">
				<td width="82" height="21">评论编号</td>
				<td width="152">评论人</td>
				<td width="566">内容</td>
				<td width="203">时间</td>
				<td width="114">操作</td>
		  </tr>
	
<%
List<Comment>commentList = (List<Comment>)request.getAttribute("commentList");
for(Comment comment:commentList) {


 %>
			<tr align="center">
				<td align="center" height="15"><%=comment.getId() %></td>
				<td align="center"><%=comment.getUsername() %></td>
				<td align="center" height="15"><%=comment.getContent() %></td>
				<td align="center"><%=comment.getCreatedtime() %></td>
				<td align="center"><a href="/blog/CommentServlet?method=preUpdateComment&id=<%=comment.getId()%>">修改</a>&nbsp;| <a href="/blog/CommentServlet?method=deleteComment&id=<%=comment.getId()%>">删除</a></td>
			</tr>
<%} %>


		</table>

		<p>&nbsp;</p>
		<p>&nbsp;</p>
		<p>&nbsp;</p>
		<p>&nbsp;</p>
	</div>
</body>
</html>