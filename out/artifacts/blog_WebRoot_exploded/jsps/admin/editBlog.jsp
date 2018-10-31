<%@ page contentType="text/html; charset=utf-8" language="java" 
import="java.util.List" errorPage="" %>
<%@ include file="/jsps/header.jsp"%>
<%@ page import = "com.zwq.blog.domain.Blog" %>
<%@ page import = "com.zwq.category.domain.Category" %>

 <script type="text/javascript" >
  <%
    Blog blog = (Blog)request.getAttribute("blog");
    List<Category> categoryList = (List<Category>)request.getAttribute("categoryList");
    
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
<p><a href = "AdminBlogServlet?method=displayBlogList">查看博文内容</a></p>
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
         <%
        	for(Category category:categoryList) {       	   
         %>
          <option value = "<%=category.getCid()%>"><%=category.getCname() %></option>
          <%} %>
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