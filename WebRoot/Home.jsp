<%@ page contentType="text/html; charset=utf-8" language="java" import="java.util.*" errorPage="" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import = "com.zwq.blog.domain.Blog" %>
<%@ page import = "com.zwq.category.domain.Category" %>
<%@ page import = "com.zwq.comment.domain.Comment" %>
<%@ page import = "com.zwq.page.domain.PageBean" %>
<%@ page import = "com.zwq.page.service.PageService" %>
<%@ page import = "com.zwq.category.dao.CategoryDao" %>
<%@ page import = "com.zwq.comment.dao.CommentDao" %>
<%@page import="com.zwq.user.domain.User"%>

<%-- QQ用户登录 --%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>zwq的博客</title>
<link rel="stylesheet" type="text/css" href="<c:url value = '/css/display/style.css' />"/>
<script type="text/javascript" src="/blog/js/qqLogin/qc_jssdk.js"></script>
<script type="text/javascript" src="/blog/js/jquery-3.2.1.min.js"></script>

<script type="text/javascript">
function _go() {
		var currentPage = $("#currentPageText").val();//获取文本框中输入的当前页码
		if(!/^[1-9]\d*$/.test(currentPage)) {//对当前页码进行整数校验
			alert('请输入正确的页码！');
			return;
		}
		if(currentPage > $("#param").val()) {//判断当前页码是否大于最大页
			alert('请输入正确的页码！');
			return;
		}
		   location = "PageServlet?method=getPageBean&currentPage="+currentPage;
	}
</script>
</head>
<body>
<div id="container">	
	<div id="banner"><!-- 浮动元素不会超过它上面的块元素 ，不会超过它上面浮动的兄弟元素，顶多一般齐-->
<%!  
PageBean<Blog> pageBean;
%>

<%
    Blog blog = null;
	PageBean<Blog> pageBean =new PageService().findTotalRecordsGetPageBean(1);
    List<Blog> blogList = (List<Blog>)pageBean.getBeanList();
    String content = null;
    String newContent = null;
%>
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
    <!-- list blog begin  -->
    <%
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
		<h3><a href="BlogServlet?method=getBlog&bid=<%=blog.getBid()%>"><%=blog.getTitle() %></a></h3>
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
	
		<p class="posted"><%=time %>&nbsp;<a href="BlogServlet?method=getBlog&bid=<%=blog.getBid()%>"><%=blog.getCname() %></a> | <a href="BlogServlet?method=getBlog&bid=<%=blog.getBid()%>">评论</a></p>
    </div>
   <%} %>	
   

	<!-- 产生分页的连接-->
   <a href="PageServlet?method=getPageBean&currentPage=1">首页</a>
	<%
	   if(pageBean.getCurrentPage() == 1) {	   
	 %>
	 <span>上一页</span>当前第1/<%=pageBean.getTotalPageNum()%>页
	 <%} else { %>	 
   <a href="PageServlet?method=getPageBean&currentPage=<%=pageBean.getCurrentPage()-1%>">上一页</a>当前第<%=pageBean.getCurrentPage() %>/<%=pageBean.getTotalPageNum() %>页
   <%} %>
   
   <%
   if(pageBean.getCurrentPage() == pageBean.getTotalPageNum()) {
    %>
    <span>下一页</span>
    <%} else {%>    
   <a href="PageServlet?method=getPageBean&currentPage=<%=pageBean.getCurrentPage()+1%>">下一页</a>
   <%} %>
  <a href="PageServlet?method=getPageBean&currentPage=<%=pageBean.getTotalPageNum()%>">尾页</a>
       跳至 &nbsp;
       <input type = "hidden" id = "param" value = "<%=pageBean.getTotalPageNum()%>"/>
       <input id="currentPageText" size = 1 value="<%=pageBean.getCurrentPage()%>" class="" type="text" >&nbsp;页
&nbsp; <a href="javascript:_go();" class="go">GO</a>
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
	<li><a href="PageServlet?method=getPageBean&param=all">全部</a></li>
		 <%
		 	List<Category>categoryList = new CategoryDao().getLimitRowNumCategory();		
		    for(Category category:categoryList) {
		 %>		
	    <li><a href="BlogServlet?method=getBlogBycidForHome&cid=<%=category.getCid()%>"><%=category.getCname() %></a></li>
 <%} %>
       </ul>

  	    <h2>最近的主题</h2>
  <ul>		  		
   		<%
         List<Blog> blogList2 = (List<Blog>)pageBean.getBeanList();   		  
    	 for(Blog blog2:blogList2) { 		
   		 %> 
	    <li><a href="BlogServlet?method=getBlog&bid=<%=blog2.getBid()%>"><%=blog2.getTitle() %></a></li>
	    <%} %>
      </ul>
  	    <h2>最近的评论</h2>
	  <ul>	
	  
	  	<%
	  		List<Comment>commentList =  new CommentDao().getLimitRowNumComment();	  		
   			for(Comment comment:commentList) {  
   		 %> 	
	    <li><a href="BlogServlet?method=getBlog&bid=<%=comment.getBlog_id()%>"><%=comment.getContent() %></a></li>
	<%} %>
      </ul>
  	   	
</div><!-- end sidebar -->	
</div><!-- end right -->

<%@ include file = "/jsps/footer.jsp"%>