<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src="<c:url value='/js/qqLogin/qc_jssdk.js'/>"></script>
	
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  
  	<body  onload="getInfo()">
       <div>
            <h3>数据传输中，请稍后...</h3>
        </div>

  	</body>
</html>
<script type="text/javascript">
            function getInfo() {
                if(QC.Login.check()){
                    QC.api("get_user_info")
                        .success(function(s){//成功回调
                            QC.Login.getMe(function(openId, accessToken){
                                $.post('PageServlet?method=getPageBean',{name:s.data.nickname,openid:openId,otype:1,token:accessToken},function(data,status){
                                    if(status=="success") {
                                        alert(s.data.nickname+"恭喜你,登录成功!");
                                        location.href = "/?page=0";
                                    }else{
                                        alert("获取用户信息成功！登录失败！");
                                        location.href = "/blog/to/login";
                                    }
                                })
                            })
                        })
                        .error(function(f){//失败回调
                            alert("获取用户信息失败！登录失败！");
                            location.href = "/blog/to/login";
                        })
                        .complete(function(c){//完成请求回调
                        	alert("获取用户信息完成！");
                        });
                }else{
                    alert("请登录！");
                    location.href = "/blog/to/login";
                }
            }
</script>

