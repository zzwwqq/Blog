<%@ page contentType="text/html; charset=utf-8" language="java"
	import="java.util.List" errorPage=""%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page import="com.zwq.blog.domain.Blog"%>


<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>博客文章管理</title>
</head>

<body>
	<div align="center">
		<p>博客文章管理</p>
		<table width="700" height="139" border="1" cellpadding="0"
			cellspacing="0">
			<tr align="center">
				<td width="60" height="48">文章号</td>
				<td width="500">文章主题</td>
				<td width="85">操作</td>
			</tr>
			<%
       List<Blog>blogList = (List<Blog>)request.getAttribute("blogList"); 
       Blog blog = null;
       
       for(int i = 0; i < blogList.size(); i++) {
       		blog = blogList.get(i);
     %>

			<tr align="center">
				<td align="center" height="43"><%=blog.getBid() %></td>
				<td align="center"><%=blog.getTitle() %></td>
				<td align="center"><a href="/blog/AdminBlogServlet?method=preUpdateBlog&bid=<%=blog.getBid()%>">修改</a>&nbsp;| <a href="/blog/AdminBlogServlet?method=deleteBlog&bid=<%=blog.getBid()%>">删除</a></td>
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