<%@ page contentType="text/html; charset=utf-8" language="java" import="java.util.*" errorPage="" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import = "com.zwq.blog.domain.Blog" %>
<%@page import="com.zwq.user.domain.User"%>
<%@ page import = "com.zwq.category.domain.Category" %>
<%@ page import = "com.zwq.comment.domain.Comment" %>
<%@ page import = "com.zwq.page.domain.PageBean" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
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


<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>zwq的博客</title>
<base href="<%=basePath%>">
<link rel="stylesheet" type="text/css" href="css/display/style.css" />
</head>
<body>
<div id="container">	
		<div id="banner"><!-- 浮动元素不会超过它上面的块元素 ，不会超过它上面浮动的兄弟元素，顶多一般齐-->
	      <!-- 用户账号登录 -->
	     <%
	    	User  user = (User)request.getSession().getAttribute("sessionUser");
	        if(user != null) {//用户存在
	           if(user.getFigureurl_qq_2() != null ) {//图片不为空
	     %>
	    <div style = "float:right; margin-right: 20px; ">
	    欢迎<span style = "font-size:20px; font-weight:bold; color:yellow;"><%=user.getLoginname() %></span>用户!
	    <img style = "width:50px;height:50px;" alt = "图片" src = "${sessionUser.figureurl_qq_2 }"/>
	    <a style = "padding-left: 5px; font-size:20px; font-weight:bold;text-decoration:underline;" href="quit.do">退出</a>
	    </div>			
		<% 
		        } else {//用户存在，但图片为空，显示 默认图片
		%>   
		   <div style = "float:right; margin-right: 20px; ">
	    欢迎<span style = "font-size:20px; font-weight:bold; color:yellow;"><%=user.getLoginname() %></span>用户!
	    <img style = "width:50px;height:50px;" alt = "图片" src = "<%=basePath%>images/avtar.png"/>
	    <a style = "padding-left: 5px; font-size:20px; font-weight:bold;text-decoration:underline;" href="quit.do">退出</a>
	    </div>			      
		<%
		          } 
		   }
		%>   
		
		
		
		
		
		<%-- qq登录--%>		
		<%
	    	User  user2 = (User)request.getSession().getAttribute("sessionqqUser");
	        int bindStatus=-1;
	        if(user2 != null) {//用户存在
	        //账号绑定状态
	        String tempBindStatus = String.valueOf(request.getSession().getAttribute("bindStatus"));
	        if(tempBindStatus != null) {	        
	             bindStatus = Integer.parseInt(tempBindStatus);  
	             if(bindStatus == 1) {//已绑定	                     
	     %>
	    <div style = "float:right; margin-right: 20px; ">
	    欢迎<span style = "font-size:20px; font-weight:bold; color:yellow;"><%=user2.getLoginname() %></span>用户!
	    <img style = "width:50px;height:50px;" alt = "图片" src = "${sessionqqUser.figureurl_qq_2 }"/>
	    <a style = "padding-left: 5px; font-size:20px; font-weight:bold;text-decoration:underline;" href="quit.do">退出</a>
	    </div>			
		<% 
		          } 
		          if(bindStatus==0) {//用户存在，未绑定
		%>   
		   <div style = "float:right; margin-right: 20px; ">
	    欢迎<span style = "font-size:20px; font-weight:bold; color:yellow;"><%=user2.getLoginname() %></span>用户!
	    <img style = "width:50px;height:50px;" alt = "图片" src = "${sessionqqUser.figureurl_qq_2 }"/>
	    <a href= "<%=basePath%>/jsps/user/qqBind.jsp">去绑定已有账号</a>	   
	    <a style = "padding-left: 5px; font-size:20px; font-weight:bold;text-decoration:underline;" href="quit.do">退出</a>
	    </div>			      
		<%
		          } 
		   }
		  }
		%>        
		    
		<% if(user == null && user2 == null) {  %>

		<div style = "float:right; margin-right: 20px; "><a style = "text-decoration:underline;" href = "<c:url value = '/login.jsp'/>" >点击这里登录</a></div>						
		<%} %>
		<h1 style = "width :700px; "><a href="#">zwq的博客</a></h1>
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
<a href = "Home.jsp">Home</a>
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