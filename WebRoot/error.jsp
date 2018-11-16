<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<script type="text/javascript" src="../js/jquery-1.8.3.js"></script>
<style type="text/css">
    .error{background:url(/blog/images/404.png) no-repeat; width:490px; margin-top:75px;padding-top:65px;}
    .error h2{font-size:22px; padding-left:154px;}
    .error p{padding-left:154px; line-height:35px;color:#717678;}
    .reindex{padding-left:154px;}
    .reindex a{width:115px; height:35px; font-size:14px; font-weight:bold; color:#fff; background:#3c95c8; display:block; line-height:35px; text-align:center;border-radius: 3px; behavior:url(js/pie.htc);margin-top:20px;}
</style>

<script language="javascript">
	$(function(){
    $('.error').css({'position':'absolute','left':($(window).width()-490)/2});
	$(window).resize(function(){  
    $('.error').css({'position':'absolute','left':($(window).width()-490)/2});
    })  
});  
</script> 


</head>


<body style="background:#edf6fa;">    
    <div class="error">    
    <h2>非常遗憾，您访问的页面不存在！</h2>
    <p>看到这个提示，就自认倒霉吧!</p>
    <div class="reindex"><a href="../index.jsp" target="_parent">返回首页</a></div>
    
    </div>
</body>
</html>
