<%@ page contentType="text/html; charset=utf-8" language="java"
	import="java.util.List" errorPage=""%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page import="com.zwq.category.domain.Category"%>


<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>博客分类管理</title>
</head>

<body>
	<div align="center">
		<p>博客分类管理</p>
		<table width="600" height="139" border="1" cellpadding="0"
			cellspacing="0">
			<tr align="center">
				<td width="60" height="48">分类编号</td>
				<td width="355">分类名</td>
				<td width="100">显示顺序</td>
				<td width="85">操作</td>
			</tr>
			<%
       List<Category>categoryList = (List<Category>)request.getAttribute("categoryList"); 
       Category category = null;
       
       for(int i = 0; i < categoryList.size(); i++) {
       		category = categoryList.get(i);
     %>

			<tr align="center">
				<td align="center" height="43"><%=category.getCid() %></td>
				<td align="center"><%=category.getCname() %></td>
				<td align="center"><%=category.getClevel() %></td>				
				<td align="center"><a href="/blog/CategoryServlet?method=preUpdateCategory&cid=<%=category.getCid()%>">修改</a>&nbsp;| <a href="/blog/CategoryServlet?method=deleteCategory&cid=<%=category.getCid()%>">删除</a></td>
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