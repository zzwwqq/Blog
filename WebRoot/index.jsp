<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
  <html style = "background: url('<%=basePath%>/images/background/001.gif')"></html>
    <head>
<base href="<%=basePath%>">
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

        <title> QQConnect JSDK - redirectURI </title>

        <script src="<%=basePath%>/js/jquery-3.2.1.min.js"></script>

        <script type="text/javascript" src="<%=basePath%>/js/qqLogin/qc_jssdk.js" 
        data-appid="101524188" data-redirecturi="http://zzwwqq.xyz:8080/blog/index.jsp&scope=all" charset="utf-8"></script>

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
                if(QC.Login.check()){//检查是否已经登录
                    //已登录，则发送请求获取用户信息
                    QC.api("get_user_info").success(function(s){//获取到数据，并成功回调到设置的回调地址
	                            /*
	                             * 回调函数 function(openId, accessToken) 
	                             * openId：用户身份的唯一标识。建议保存在本地，以便用户下次登录时
	                             * 可对应到其之前的身份信息，不需要重新授权。 
	                             * accessToken：表示当前用户在此网站/应用的登录状态与授权信息，
	                             * 建议保存在本地。 注意： getMe方法只能在用户登录授权后调用，建议总是在使
	                             * 用check方法并返回true的条件下，调用getMe方法
	                             *
	                             */
                            QC.Login.getMe(function(openId, accessToken){
                            //将获取到的数据传到UserServlet中的QQLogin方法
                                $.post('QQLogin.do',{name:s.data.nickname,openid:openId,otype:1,token:accessToken,gender:s.data.gender,figureurl_qq_2:s.data.figureurl_qq_2,figureurl_qq_1:s.data.figureurl_qq_1,ret:s.data.ret,figureurl:s.data.figureurl,figureurl_1:s.data.figureurl_1,figureurl_2:s.data.figureurl_2},function(data,status){
                                    if(status=="success") {
                                        //alert(s.data.nickname+"恭喜你,登录成功!");
                                        location.href = "AfterqqLoginSuccessforword.do";
                                    } else {
                                        alert("获取用户信息成功！登录失败！");
                                        location.href = "/blog/jsps/user/login.jsp";
                                    }

                                })

                            })

                        })
                        .error(function(f){//失败回调
                            alert("获取用户信息失败！登录失败！");
                            location.href = "/blog/jsps/user/login.jsp";
                        })
                        .complete(function(c){//完成请求回调
                        //alert("获取用户信息完成！");
                        });
                }else{
                    alert("请登录！");
                    location.href = "/blog/jsps/user/login.jsp";
                }
            }
</script>

