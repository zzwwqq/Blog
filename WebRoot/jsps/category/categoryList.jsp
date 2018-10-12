<%@ page contentType="text/html; charset=utf-8" language="java"
	import="java.util.List" errorPage=""%>
<%@ include file="/jsps/header.jsp"%>
<%@ page import="com.zwq.category.domain.Category"%>
<h2>分类管理</h2>
<center>
<table id="tab">
<tr>
   <th>分类编号</th>
   <th>分类名</th>
   <th>显示顺序</th>
   <th>操作</th>
</tr>
<%
       List<Category>categoryList = (List<Category>)request.getAttribute("categoryList"); 
       Category category = null;
       
       for(int i = 0; i < categoryList.size(); i++) {
       		category = categoryList.get(i);
%>

			<tr align="center">
				<td align="center" height="23"><%=category.getCid() %></td>
				<td align="center"><%=category.getCname() %></td>
				<td align="center"><%=category.getClevel() %></td>				
				<td align="center"><a href="CategoryServlet?method=preUpdateCategory&cid=<%=category.getCid()%>" ><img src="/blog/images/manager/edit.gif" border="0" alt="修改"></a><a href="CategoryServlet?method=deleteCategory&cid=<%=category.getCid()%>"><img src="/blog/images/manager/delete.gif" border="0" alt="删除"></a></td>    			
			</tr>
			<%} %>
		</table>
				</center>
<%@include file="/jsps/footer.jsp" %>
