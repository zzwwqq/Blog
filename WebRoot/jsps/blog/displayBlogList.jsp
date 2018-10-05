<%@ page contentType="text/html; charset=utf-8" language="java" import="java.util.List" errorPage="" %>
<%@ page import = "com.zwq.blog.domain.Blog" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>显示博客列表</title>
</head>

<body>

<%
	Blog blog = null;
	List<Blog> blogList =  (List<Blog>)request.getAttribute("blogList");
    String content = null;
    String newContent = null;
    int length = 300;
    for(int i = 0; i < blogList.size(); i++) {
    	blog = blogList.get(i);
 %>
<table width="632" height="205" border="0">
  <tr>
    <td width="774" height="41"><%=blog.getCreated_time() %></td>
  </tr>
  <tr>
    <td><a href = "/blog/BlogServlet?method=getBlog&bid=<%=blog.getBid() %>"><%=blog.getTitle() %></a></td>
  </tr>
  <tr>
    <td height="42">
    <%    
    content = blog.getContent();
    if(content.length() < 300) {
    	length = content.length();
    } 
    	newContent = content.substring(0,length);
   	    out.println(newContent + "..."); 
    %>
    </td>
  </tr>
  <tr>
    <td height="36">&nbsp;</td>
  </tr>
</table>
<p>&nbsp;</p>
<% } %>
</body>
</html>