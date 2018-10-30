<%@ page contentType="text/html; charset=utf-8" language="java"
	import="java.util.*" errorPage=""%>
<%@ include file="/jsps/header.jsp"%>
<%@page import="com.zwq.category.domain.Category"%>

<!-- fckeditor -->
<script type="text/javascript" src="<c:url value='/fckeditor/fckeditor.js'/>"></script>
<script type="text/javascript">
window.onload = function()
{
var oFCKeditor = new FCKeditor( 'content','100%','400','Default' ) ;
oFCKeditor.BasePath = "/blog/fckeditor/";
oFCKeditor.ReplaceTextarea() ;
}
</script>
<!-- end of fckeditor -->
<%
  List<Category>categoryList = (List<Category>)request.getAttribute("categoryList");

 %>
<h3 style="text-align:center;">发布博客</h3>
<p style="text-align:left; margin-left: 7px">
	<a style="display:block;text-align: center" href="AdminBlogServlet?method=displayBlogList">查看往期博客内容</a>
</p>
<center>
	<form id="form1" name="form1" method="post"
		action="<c:url value = '/BlogServlet'/>">
		<input type="hidden" name="method" value="addBlog" />
		<table id="tab">
			<tr>
				<td style="font-size: 16px;">主题:</td>
				<td><input type="text" name="title" value="" size="50"
					maxlength="100" style="width: 750px"/></td>
			</tr>

			<tr>
				<td style="font-size: 16px;">类别：</td>
				<td>
				<label for="select"></label> 
				<select style="font-size: 16px;" name="category_id"
					id="select">
		<%
        	for(Category category:categoryList) {       	   
         %>
						<option value="<%=category.getCid()%>"><%=category.getCname() %></option>
						<%} %>
				</select>
				</td>
			</tr>
			<tr>
				<td colspan="2" style=" font-size: 16px;">内容: <br /> 
				<textarea name="content" rows="18"
						cols="100"></textarea>
				</td>
			</tr>

			<tr>
				<td ><input style=" font-size: 16px;" type="reset" name="button" id="button" value="重置" /></td>
				<td ><input style=" font-size: 16px;" type="submit" name="button2" id="button2" value="发布" /></td>
			</tr>

		</table>
	</form>
	</center>
<%@ include file="/jsps/footer.jsp"%>