<%@ page contentType="text/html; charset=utf-8" language="java" import="java.util.*" errorPage="" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import = "com.zwq.blog.domain.Blog" %>
<%@ page import = "com.zwq.category.domain.Category" %>
<%@ page import = "com.zwq.comment.domain.Comment" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>zwq的博客</title>
<link rel="stylesheet" type="text/css" href="/blog/css/display/style.css" />
</head>
<body>
<div id="container">	
	<div id="banner">
		<h1><a href="/blog">zwq的博客</a></h1>
	</div>

<div id="center">
<div class="content">
    <!-- list blog begin  -->
    <%
	Blog blog = null;
	List<Blog> blogList = (List<Blog>)request.getAttribute("blogList");
    String content = null;
    String newContent = null;
    int length = 200;
    for(int i = 0; i < blogList.size(); i++) {
    	blog = blogList.get(i);
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
    	String date = sdf.format(blog.getCreated_time());   	
    	SimpleDateFormat sdf2 = new SimpleDateFormat("HH:mm:ss");
    	String time = sdf2.format(blog.getCreated_time());   	
 %>  
   	<h2><%=date %></h2>
    <div class="entry">
	    <a id="6"></a>	
		<h3><a href=""><%=blog.getTitle() %></a></h3>
<%    
    content = blog.getContent();
    if(content.length() < length) {
    	length = content.length();
    	newContent = content.substring(0,length);
    	 out.println(newContent); 
    } else {
    	newContent = content.substring(0,length);
   	    out.println(newContent + "......"); 
   	}
%>
	
		<p class="posted"><%=time %>&nbsp;<a href="">Java Web</a> | <a href="#">评论</a></p>
    </div>
   <%} %>	
   

	<!-- 产生分页的连接-->
   	&nbsp; 1/2 &nbsp;<a href="tm?method=h&p=2">&gt;&gt;</a>
 <!-- end list -->	

<br clear="all" />
</div><!-- end content -->
</div><!-- end center -->

<div id="right">
<div class="sidebar">
        <ul>
    	<li>欢迎访问zwq的博客</li>
      </ul>
  	     <h2>分类</h2>
   <ul>		
	<li><a href="">全部</a></li>
		<%
		    List<Category> categoryList= (List<Category>)request.getAttribute("categoryList");
		    for(Category category:categoryList) {
		 %>
		
	    <li><a href=""><%=category.getCname() %></a></li>
 <%} %>
       </ul>

  	    <h2>最近的主题</h2>
  <ul>		  		
   		<%
   		   List<Blog> blogList2 = (List<Blog>)request.getAttribute("blogList");
   		   for(Blog blog2:blogList2) { 		
   		 %> 
	    <li><a href="/"><%=blog2.getTitle() %></a></li>
	    <%} %>
      </ul>
  	    <h2>最近的评论</h2>
	  <ul>	
	  	<%
   		    List<Comment>commentList = (List<Comment>)request.getAttribute("commentList");
   			for(Comment comment:commentList) {  		 		
   		 %> 	
	    <li><a href="#"><%=comment.getContent() %>></a></li>
	    <%} %>
      </ul>
  	   	
</div><!-- end sidebar -->	
</div><!-- end right -->
<%@ include file = "/jsps/footer.jsp"%>