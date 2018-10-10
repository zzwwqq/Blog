<%@ page contentType="text/html; charset=utf-8" language="java" import="java.util.List" errorPage="" %>
<%@ include file = "/jsps/header.jsp" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import = "com.zwq.blog.domain.Blog" %>
<link rel="stylesheet" type="text/css" href="/blog/css/display/style.css" />

 
 <div id="center">
<div class="content">
    <!-- list blog begin  -->
  <%
	Blog blog = null;
	List<Blog> blogList = (List<Blog>)request.getAttribute("blogList");
    String content = null;
    String newContent = null;
    int length = 300;
    for(int i = 0; i < blogList.size(); i++) {
    	blog = blogList.get(i);
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
    	String date = sdf.format(blog.getCreated_time());   	
    	SimpleDateFormat sdf2 = new SimpleDateFormat("HH-mm-ss");
    	String time = sdf2.format(blog.getCreated_time());   	
 %>  
    
   	<h2><%=date %></h2>
    <div class="entry">
	    <a id="6"></a>	
		<h3><a href=""><%=blog.getTitle() %></a></h3>
<%    
    content = blog.getContent();
    if(content.length() < 300) {
    	length = content.length();
    } 
    	newContent = content.substring(0,length);
   	    out.println(newContent + "..."); 
%>
			
			
		<p class="posted"><%=time %><a href=""><%=blog.getCategory_id() %></a> | <a href="tm?method=e&id=6#comments">评论</a></p>
    </div>

	<!-- 产生分页的连接-->
   	&nbsp; 1/2 &nbsp;<a href="tm?method=h&p=2">&gt;&gt;</a>
 <!-- end list -->	

<br clear="all" />
</div><!-- end content -->
</div><!-- end center -->

<div id="right">
<div class="sidebar">
        <ul>
    	<li>专业的Java视频下载网站</li>
      </ul>
  	     <h2>分类</h2>
   <ul>		
	<li><a href="">全部</a></li>
	    <li><a href="">Struts 2.x</a></li>
        <li><a href="">Java SE</a></li>
        <li><a href="">Java Web</a></li>
        <li><a href="">Hibernate 3.X</a></li>
       </ul>

  	    <h2>最近的主题</h2>
  <ul>		
	    <li><a href="">北京与三大运营商签协议 提高宽带接入能力</a></li>
        <li><a href="">美澳测试超级飞机 速度可超5倍音速</a></li>
      </ul>
  	    <h2>最近的评论</h2>
	  <ul>		
	    <li><a href="">他的: 他的对的</a></li>
        <li><a href="">长的: 你的</a></li>
      </ul>
  	   	
</div><!-- end sidebar -->	
</div><!-- end right -->
 
 
 
 
 
 
 
<center>
<table width="632" height="205" border="0">
  <tr>
    <td width="774" height="41"><%=blog.getCreated_time() %></td>
  </tr>
  <tr>
    <td><a href = "/blog/BlogServlet?method=getBlog&bid=<%=blog.getBid() %>"><%=blog.getTitle() %></a></td>
  </tr>
  <tr>
    <td height="42">
    <%    
    content = blog.getContent();
    if(content.length() < 300) {
    	length = content.length();
    } 
    	newContent = content.substring(0,length);
   	    out.println(newContent + "..."); 
    %>
    </td>
  </tr>
  <tr>
    <td height="36">&nbsp;</td>
  </tr>
</table>
</center>
<p>&nbsp;</p>
<%}%>
<%@ include file = "/jsps/footer.jsp"%>
