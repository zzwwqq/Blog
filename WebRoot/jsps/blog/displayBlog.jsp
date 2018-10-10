<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page import="com.zwq.blog.domain.Blog" %>
<%@ page import="com.zwq.comment.domain.Comment" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>郑文庆的博客</title>
<link rel="stylesheet" type="text/css" href="/blog/css/display/style.css" />
</head>
<body>
<%
	Blog blog = (Blog)request.getAttribute("blog");
%>
<div id="container">	
	<div id="banner">
		<h1><a href="/blog"></a>郑文庆的博客</h1>
	</div>

<div id="center">
<div class="content">
	<!-- begin entry -->
	    <h2><%=blog.getCreated_time() %></h2>
        <div class="entry">
        <a id="6"></a>
        <h3><%=blog.getTitle() %></h3>
        <p><%=blog.getContent() %></p>
           <p class="posted">09:33 上午 <a href="tm?method=c&id=2">Java Web</a> | <a href="#comments">评论</a></p>
        </div>
	<!-- end entry -->
	<!-- begin comments -->
		<h2><a id="comments"></a>评论</h2>
    	    	<a id="2"></a>
        <p>我的</p>
        <p class=posted>Posted by: liuwei at 2009/05/24 09:38 上午</p>		
    	    	<a id="1"></a>
        <p>想到的</p>
		<p class=posted>Posted by: liuwei at 2009/05/24 09:38 上午</p>		
    	    	<a id="1"></a>
		<h2>发表评论</h2>
 <form name=comments_form method="post" action="">
	     <div id="name_email">
        <p><label for="author">名字:</label><br><input id="author" tabindex=1 name="author"></p>
        </div>
        <p><label for=text>内容:</label><br>
        <textarea id=text tabindex=4 name="content" rows=6 cols=50></textarea></p>
        <div align="center">
		<input style="font-weight: bold" tabindex=6 type=submit value=&nbsp;发表&nbsp; name=post></div>
    </form>
		<!-- end comments -->

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
<%@ include file = "/jsps/footer.jsp"%>