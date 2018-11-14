<%@ page contentType="text/html; charset=utf-8" language="java" import="java.util.List" errorPage="" %>
<%@ include file = "/jsps/header.jsp" %>
<%@ page import = "com.zwq.blog.domain.Blog" %>
<%
	Blog blog = null;
	blog = (Blog)request.getAttribute("blog");	
%>
<center>
<table width="632" height="205" border="0">
  <tr>
    <td width="774" height="41"><%=blog.getCreated_time() %></td>
  </tr>
  <tr>
    <td><%=blog.getTitle() %></td>
  </tr>
  <tr>
 <td><%=blog.getContent() %><td>
  </tr>
  <tr>
     <td>所属分类：<%=blog.getCname() %></td>
  </tr>
</table>
</center>
<p>&nbsp;</p>
<%@ include file = "/jsps/footer.jsp"%>
