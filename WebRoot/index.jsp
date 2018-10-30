<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
    <head>

        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

        <title> QQConnect JSDK - redirectURI </title>

        <script src="/blog/js/jquery-3.2.1.min.js"></script>

        <script type="text/javascript" src="/blog/js/qqLogin/qc_jssdk.js" data-appid="101507411" data-redirecturi="http://localhost:8080/blog/index.jsp&scope=all" charset="utf-8"></script>

        <style type="text/css">

        html, body{font-size:14px; line-height:180%;}

        </style>

    </head>

    <body onload="getInfo()">

        <div>

            <h3>数据传输中，请稍后...</h3>

        </div>

    </body>

</html>

<script type="text/javascript">

            function getInfo() {
alert("进入getInfo()方法")
                if(QC.Login.check()){
alert("进入QC.Login.check方法");
                    QC.api("get_user_info").success(function(s){//成功回调
alert("成功回调");
                            QC.Login.getMe(function(openId, accessToken){
alert("c");
                                $.post('PageServlet?method=getPageBean',{name:s.data.nickname,openid:openId,otype:1,token:accessToken,gender:s.data.gender,figureurl_1:s.data.figureurl_1},function(data,status){
alert(data);
alert(status);
                                    if(status=="success"){
                                        alert(s.data.nickname+"恭喜你,登录成功!");
                                        location.href = "/blog/MyJsp.jsp";
                                    } else{
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
                        //alert("获取用户信息完成！");
                        });
                }else{
                    alert("请登录！");
                    location.href = "/blog/to/login";
                }
            }
</script>