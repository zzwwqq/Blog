<%@ page contentType="text/html; charset=utf-8" language="java" 
import="java.util.List" errorPage="" %>
<%@ page import = "com.zwq.blog.domain.Blog" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "
http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Edit Blog</title>

 <script type="text/javascript" src="/blog/js/jquery-3.2.1.min.js"></script>
 <script type="text/javascript" >
  <%
    Blog blog = (Blog)request.getAttribute("blog");
   %> 
   		$(function(){
        selected_option = <%=blog.getCategory_id() %>
        $("select#select").val(selected_option);	
   		})
</script>
<!-- fckeditor -->
<script type="text/javascript" src="/blog/fckeditor/fckeditor.js"></script>
<script type="text/javascript">
window.onload = function()
{
var oFCKeditor = new FCKeditor( 'content','100%','400','Default' ) ;
oFCKeditor.BasePath = "/blog/fckeditor/";
oFCKeditor.ReplaceTextarea() ;
}
</script>
<!-- end of fckeditor -->
</head>
<body>	
<p>请修改您的博文内容：</p>
<p><a href = "/blog/BlogServlet?method=getBlogList">查看博文内容</a></p>
<form id="form1" name="form1" method="post" action="<c:url value = '/AdminBlogServlet'/>">
  <input type = "hidden" name = "method" value = "updateBlog"/>
  <input type = "hidden" name = "bid" value = "<%=blog.getBid() %>" />
  <table width="742" border="0">
    <tr>
      <td width="85">主题：</td>
      <td width="647"><input name="title" type="text" id="title" size="60" value = "<%=blog.getTitle() %>" /></td>
    </tr>
    <tr>
      <td>类别：</td>
      <td><label for="select"></label>
        <select name="category_id" id="select">
          <option value = "1">心情故事</option>
          <option value = "2">旅游故事</option>
      </select></td>
    </tr>
    <tr>
      <td>内容：</td>
      <td><label for="textfield"></label>
      <textarea name="content" cols="60" rows="8" id="content"  ><%=blog.getContent() %></textarea></td>
    </tr>
    <tr>
      <td><input type="reset" name="button" id="button" value="重置" /></td>
      <td><input type="submit" name="button2" id="button2" value="提交" /></td>
    </tr>
    <tr>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
    </tr>
    <tr>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
    </tr>
  </table>
</form>
<p>&nbsp;</p>
</body>
</html>