<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
</head>

<body>
<table width="191" border="1" align="center" cellpadding="0" cellspacing="0">
   <tr>
    <td align="center"><a href="/blog/BlogServlet?method=getBlogList">查看博客</a></td>
  </tr>
  <tr>
    <td align="center"><a href="/blog/BlogServlet?method=preAddBlog">发布博客</a></td>
  </tr>
  <tr>
    <td align="center"><a href="/blog/AdminBlogServlet?method=getBlogList">管理博客</a></td>
  </tr>
     <tr>
    <td align="center"><a href="/blog/jsps/category/addCategory.jsp">添加分类</a></td>
  </tr>
  <tr>
    <td align="center"><a href="/blog/CategoryServlet?method=getCategoryList">管理分类</a></td>
  </tr>
 <tr>
    <td align="center"><a href="/blog/CommentServlet?method=getCommentList">管理评论</a></td>
  </tr>
 
</table>
</body>
</html>