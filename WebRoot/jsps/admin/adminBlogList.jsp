<%@ page contentType="text/html; charset=utf-8" language="java"
	import="java.util.List" errorPage=""%>
<%@ page import="com.zwq.blog.domain.Blog"%>
<%@ include file="/jsps/header.jsp"%>
<h2>博文管理</h2>
<center>
<table id="tab">
<tr>
   <th>博文编号</th>
   <th>博文主题</th>
   <th>操作</th>
</tr>
<%
       List<Blog>blogList = (List<Blog>)request.getAttribute("blogList"); 
       Blog blog = null;
       
       for(int i = 0; i < blogList.size(); i++) {
       		blog = blogList.get(i);
%>

			<tr align="center">
				<td ><%=blog.getBid() %></td>
				<td ><%=blog.getTitle() %></td>
				<td align="center"><a href="AdminBlogServlet?method=preUpdateBlog&bid=<%=blog.getBid()%>" ><img src="/blog/images/manager/edit.gif" border="0" alt="修改"></a><a href="AdminBlogServlet?method=deleteBlog&bid=<%=blog.getBid()%>"><img src="/blog/images/manager/delete.gif" border="0" alt="删除"></a></td>    
			</tr>
			<%} %>			
		</table>	
		</center>
<%@include file="/jsps/footer.jsp" %>
		