<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<%@ page import="com.zwq.blog.domain.Blog" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>显示博文</title>
</head>

<body>
<%
	Blog blog = (Blog)request.getAttribute("blog");
 %>
<table width="945" height="524" border="0">
  <tr>
    <td align="center"><%=blog.getTitle() %></td>
  </tr>
  <tr>
    <td height="223"><%=blog.getContent() %></td>
  </tr>
  <tr>
    <td><%=blog.getCreated_time() %></td>
  </tr>
  <tr>
    <td>&nbsp;</td>
  </tr>
</table>
</body>
</html>