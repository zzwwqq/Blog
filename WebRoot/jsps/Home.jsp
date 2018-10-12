<%@ page contentType="text/html; charset=utf-8" language="java" import="java.util.*" errorPage="" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import = "com.zwq.blog.domain.Blog" %>
<%@ page import = "com.zwq.category.domain.Category" %>
<%@ page import = "com.zwq.comment.domain.Comment" %>
<%@ page import = "com.zwq.page.domain.PageBean" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>zwq的博客</title>
<link rel="stylesheet" type="text/css" href="<c:url value = '/css/display/style.css' />"/>
<script type="text/javascript" src="js/page.js"></script>
<script type="text/javascript" src="/blog/js/qqLogin/qc_jssdk.js"></script>
<%--
<script type="text/javascript">
//下面这段if判断代码的作用是延迟加载，不写也可以，但为了好的体验建议写上，当然这个800是毫秒数，可以自定义 
if(document.location.search.indexOf('signout_qq')>=0) { 
setTimeout(function(){ 
QC.Login.signOut(); 
alert('QQ登录，退出成功'); }, 800) 
} 
//从页面收集OpenAPI必要的参数。get_user_info不需要输入参数，因此paras中没有参数 
var paras = {}; 
//用JS SDK调用OpenAPI 
QC.api("get_user_info", paras) 
//指定接口访问成功的接收函数，s为成功返回Response对象 
.success(function(s) { 
//成功回调，通过s.data获取OpenAPI的返回数据 
QC.Login.getMe(function(openId, accessToken) { 
qq_Login(s,openId,accessToken);
//对应JS的qq_Login()的方法，继续往下看 
}) 
}) //指定接口访问失败的接收函数，f为失败返回Response对象 
.error(function(f) { 
//失败回调 
qq_error(f); 
//这个地方可以自动以逻辑处理，也可以不处理，就是请求失败的提示
}) //指定接口完成请求后的接收函数，c为完成请求返回Response对象 
.complete(function(c) { 
//完成请求回调 // 
qq_complete(c); 
});
</script>
 --%>

<script type="text/javascript">
var paras = {};

QC.api("get_user_info", paras)
	.success(function(s){//成功回调
		alert("获取用户信息成功！当前用户昵称为："+s.data.nickname);
	})
	.error(function(f){//失败回调
		alert("获取用户信息失败！");
	})
	.complete(function(c){//完成请求回调
		alert("获取用户信息完成！");
	});
</script>

</head>
<body>
<div id="container">	
	<div id="banner">
		<h1><a href="#">zwq的博客</a></h1>
		<div></div>
	</div>
<div id="center">
<div class="content">
    <!-- list blog begin  -->
    <%
	Blog blog = null;
	PageBean pageBean = (PageBean)request.getAttribute("pageBean");
	//List<Blog> blogList = (List<Blog>)request.getAttribute("");
    List<Blog> blogList = (List<Blog>)pageBean.getBeanList();
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
	 <span>上一页</span>当前第${pageBean.currentPage}/${pageBean.totalPageNum }页
	 <%} else { %>	 
   <a href="PageServlet?method=getPageBean&currentPage=${pageBean.currentPage - 1 }">上一页</a>当前第${pageBean.currentPage}/${pageBean.totalPageNum }页
   <%} %>
   
   <%
   if(pageBean.getCurrentPage() == pageBean.getTotalPageNum()) {
    %>
    <span>下一页</span>
    <%} else {%>    
   <a href="PageServlet?method=getPageBean&currentPage=${pageBean.currentPage + 1 }">下一页</a>
   <%} %>
   <a href="PageServlet?method=getPageBean&currentPage=${pageBean.totalPageNum }">尾页</a>
       跳至 &nbsp;<input id="currentPageText" size = 1 value="${pageBean.currentPage }" class="" type="text" >&nbsp;页
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
		    List<Category> categoryList= (List<Category>)request.getAttribute("categoryList");
		    for(Category category:categoryList) {
		        //System.out.print(category.getCname()+"===============");	    
		 %>		
	    <li><a href="BlogServlet?method=getBlogBycidForHome&cid=<%=category.getCid()%>"><%=category.getCname() %></a></li>
 <%} %>
       </ul>

  	    <h2>最近的主题</h2>
  <ul>		  		
   		<%
   		  PageBean pageBean2 = (PageBean)request.getAttribute("pageBean");
         List<Blog> blogList2 = (List<Blog>)pageBean.getBeanList();   		  
    	 for(Blog blog2:blogList2) { 		
   		 %> 
	    <li><a href="BlogServlet?method=getBlog&bid=<%=blog2.getBid()%>"><%=blog2.getTitle() %></a></li>
	    <%} %>
      </ul>
  	    <h2>最近的评论</h2>
	  <ul>	
	  	<%
   		    List<Comment>commentList = (List<Comment>)request.getAttribute("commentList");
   			for(Comment comment:commentList) {  
		       // System.out.print(comment.getContent()+"===============");
   		 %> 	
	    <li><a href="BlogServlet?method=getBlog&bid=<%=comment.getBlog_id()%>"><%=comment.getContent() %></a></li>
	<%} %>
      </ul>
  	   	
</div><!-- end sidebar -->	
</div><!-- end right -->

<%@ include file = "/jsps/footer.jsp"%>