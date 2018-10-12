<%@ page contentType="text/html; charset=utf-8" language="java" import="java.util.*" errorPage="" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import = "com.zwq.blog.domain.Blog" %>
<%@ page import = "com.zwq.category.domain.Category" %>
<%@ page import = "com.zwq.comment.domain.Comment" %>
<%@ page import = "com.zwq.page.domain.PageBean" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>zwq的博客</title>
<base href="<%=basePath%>">
<link rel="stylesheet" type="text/css" href="css/display/style.css" />
</head>
<body>
<div id="container">	
	<div id="banner">
		<h1><a href="#">zwq的博客</a></h1>
	</div>

<div id="center">
<div class="content">
	<!-- begin entry -->
	   <%
	   	List<Category>limitRowNumcategoryList = (List<Category>)request.getAttribute("limitRowNumcategoryList");
	    List<Comment>limitRowNumcommentList = (List<Comment>)request.getAttribute("limitRowNumcommentList");
	   	List<Blog>limitRowNumTitleList = (List<Blog>)request.getAttribute("limitRowNumTitleList");
	   
	    Blog blog = (Blog)request.getAttribute("blog");
	    Category category = (Category)request.getAttribute("category");
	    List<Comment>commentList = ( List<Comment>)request.getAttribute("commentList"); 
	    SimpleDateFormat sdf =  new SimpleDateFormat("yyyy年MM月dd日");
	    String date = sdf.format(blog.getCreated_time());
	    SimpleDateFormat sdf2 =  new SimpleDateFormat("HH:mm:ss");
	    String time = sdf.format(blog.getCreated_time());    
	    %>
	    <h2><%=date %></h2>
        <div class="entry">
        <a id="6"></a>
        <h3><%=blog.getTitle() %></h3>
        <p><%=blog.getContent() %></p></p>
		<p class="posted"><%=time %> <a href=""><%=category.getCname() %></a> | <a href="#comments">评论</a></p>
        </div>
	<!-- end entry -->
	
	<!-- begin comments -->
		<h2><a id="comments"></a>评论</h2><a id="2"></a>  
        <p>所有评论：</p>
        <hr />
        <%
        for(Comment comment :commentList) {
         %>
		<p ><%=comment.getUsername() %>的评论</p>	
    	<p >内容：&nbsp;<%=comment.getContent() %></p>		
    	<p >评论时间：&nbsp;<%=comment.getCreatedtime() %></p>		
    	<hr/>
    	 
    	 <%} %>   	
		<h2>发表评论</h2>
			 <form name=comments_form method="post" action="CommentServlet">
 			 <input type = "hidden" name = "method" value = "addComment"/>
			 <input type = "hidden" name = "blog_id" value = "<%=blog.getBid()%>"/>	    
	     <div id="name_email">
        <p><label for="author">评论人:</label><br><input id="author" tabindex=1 name="username"></p>
        </div>
        <p><label for=text>内容:</label><br>
        <textarea id=text tabindex=4 name="content" rows=6 cols=50></textarea></p>
        <div align="center">
		<input style="font-weight: bold" tabindex=6 type=submit value=&nbsp;发表&nbsp; name=""></div>
    </form>
		<!-- end comments -->

<br clear="all" />
</div><!-- end content -->
</div><!-- end center -->
<div id="right">
<div class="sidebar">
<a href = "PageServlet?method=getPageBean">Home</a>
        <ul>
    	<li>欢迎访问zwq的博客</li>
      </ul>
  	     <h2>分类</h2>
   <ul>		
	<li><a href="BlogServlet?method=getBlog&bid=<%=blog.getBid() %>&param=all">全部</a></li>
	<%
	for(Category category2 :limitRowNumcategoryList) {
	 %>
        <li><a href="BlogServlet?method=getBlogBycidForHome&cid=<%=category2.getCid()%>"><%=category2.getCname() %></a></li>
        <%} %>
       </ul>

  	    <h2>最近的主题</h2>
  <ul>		
        
  		<%
  			for(Blog blog3:limitRowNumTitleList) {
  			
  		 %>
        <li><a href="BlogServlet?method=getBlog&bid=<%=blog3.getBid()%>"><%=blog3.getTitle() %></a></li>
        <%} %>
      </ul>
  	    <h2>最近的评论</h2>
	  <ul>	
	  <%
	    for(Comment comment4:limitRowNumcommentList){
	   %>	
        <li><a href="BlogServlet?method=getBlog&bid=<%=comment4.getBlog_id() %>"><%=comment4.getContent() %></a></li>
        <%} %>
      </ul>
  	   	
</div><!-- end sidebar -->	
</div><!-- end right -->
<%@ include file = "/jsps/footer.jsp"%>