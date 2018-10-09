<%@ page contentType="text/html; charset=utf-8" language="java"
	import="java.util.List" errorPage=""%>
<%@ include file="/jsps/header.jsp"%>
<%@ page import="com.zwq.comment.domain.Comment"%>

<table id="tab">
<tr>
   <th>评论编号</th>
   <th>评论人</th>
   <th>内容</th>
   <th>时间</th>
   <th>操作</th>
</tr>
<%
List<Comment>commentList = (List<Comment>)request.getAttribute("commentList");
for(Comment comment:commentList) {
 %>
			<tr align="center">
				<td align="center" height="15"><%=comment.getId() %></td>
				<td align="center"><%=comment.getUsername() %></td>
				<td align="center" height="15"><%=comment.getContent() %></td>
				<td align="center"><%=comment.getCreatedtime() %></td>
			    <td align="center"><a href="/blog/CommentServlet?method=preUpdateComment&id=<%=comment.getId()%>" ><img src="/blog/images/manager/edit.gif" border="0" alt="修改"></a><a href="/blog/CommentServlet?method=deleteComment&id=<%=comment.getId()%>"><img src="/blog/images/manager/delete.gif" border="0" alt="删除"></a></td>    				
			</tr>
<%} %>
		</table>
<%@ include file = "/jsps/footer.jsp" %>