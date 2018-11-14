<%@ page contentType="text/html; charset=utf-8" language="java" import="java.util.List" errorPage="" %>
<%@ include file = "/jsps/header.jsp" %>
<%@ page import = "com.zwq.blog.domain.Blog" %>
<%
	Blog blog = null;
	List<Blog> blogList = (List<Blog>)request.getAttribute("blogList");
    String content = null;
    String newContent = null;
    int length = 300;
    for(int i = 0; i < blogList.size(); i++) {
    	blog = blogList.get(i);
 %>
<center>
<table width="632" height="205" border="0">
  <tr>
    <td width="774" height="41"><%=blog.getCreated_time() %></td>
  </tr>
  <tr>
    <td><a href = "AdminBlogServlet?method=displayBlog&bid=<%=blog.getBid() %>"><%=blog.getTitle() %></a></td>
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
</center>
<p>&nbsp;</p>
<%}%>
<%@ include file = "/jsps/footer.jsp"%>
