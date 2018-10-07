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
   <tr valign="top">
    <td height="265">
    
    <table width="200" border="1" cellspacing="1">
      <tr>
        <td>评论人：郑文庆</td>
      </tr>
      <tr>
        <td>评论内容：我爱祖国</td>
      </tr>
      <tr>
        <td>评论时间：2018-09-09</td>
      </tr>
    </table>
     
    <p>&nbsp;</p></td>
  </tr>
  <tr valign="top">
    <td height="265" align="left"><p>欢迎发表评论</p>
      <form id="form1" name="form1" method="post" action="CommentServlet">
      <input type = "hidden" name = "method" value = "addComment"/>
      <input type = "hidden" name = "blog_id" value = "<%=blog.getBid() %>" />
        <table width="834" border="0">
          <tr>
            <td width="67" align="left">评论人：</td>
            <td width="757" align="left"><label for="name"></label>
            <input name="username" type="text" id="name" maxlength="20" /></td>
          </tr>
          <tr>
            <td align="left">内容：</td>
            <td align="left"><label for="textarea"></label>
            <textarea name="content" id="textarea" cols="80" rows="10"></textarea></td>
          </tr>
          <tr>
            <td align="left"><input type="submit" name="button" id="button" value="提交" /></td>
            <td align="left">&nbsp;</td>
          </tr>
        </table>
      </form>
    <p>&nbsp;</p></td>
  </tr>
</table>
</body>
</html>