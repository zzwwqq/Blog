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
<base href="<%=basePath%>">

<title>Welcome Login</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<!-- 引入 login.css文件 -->
<link rel="stylesheet" type="text/css"
	href="<c:url value = '/css/user/reset.css'/>">
<link rel="stylesheet" type="text/css"
	href="<c:url value = '/css/user/login.css'/>">
<!--导入jQuery文件 -->
<script type="text/javascript"
	src="<c:url value = '/js/jquery-3.2.1.min.js'/>"></script>
<!-- 导入js文件 -->
<script type="text/javascript"
	src="<c:url value = '/js/user/login.js'/>"></script>
<script type="text/javascript"
	src="<c:url value = '/js/qqLogin/qc_jssdk.js'/>"></script>	

<!-- 点击验证码，切换到下一张 -->
<script type="text/javascript">
		function refresh(obj){
			obj.src="/blog/VerifyCodeServlet?a="+new Date().getTime();
		}
	</script>
	
<!-- QQ互联登录 -->	
<script type="text/javascript"  charset="utf-8"
    src="/blog/js/qqLogin/qc_jssdk.js" 
    data-appid="101507411" 
    data-redirecturi="http://localhost:8080/blog/index.jsp&scope=all" 
    >
</script>
<script type="text/javascript">
    QC.Login({
       btnId:"qqLoginBtn"    //插入按钮的节点id
});
</script>	


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

</head>
<body>

	<h1>Welcome</h1>
	<div class="login-form">
		<div class="close"></div>
		<div class="clo"></div>

		<!-- 脱离文档流 -->
		<div class="head-info">
			<label class="lbl1"></label> <label class="lbl2"></label> <label
				class="lbl3"></label>
		</div>
		<div class="clear"></div>
		<!-- 为了清除浮动 -->
		<div class="avtar">
			<img src="images/avtar.png" />
		</div>
		<!-- action 属性规定当提交表单时，向何处发送表单数据。 
		登录和注册两个input语句不能换行，必须在同一行
		-->
		<div>
			<!-- 		<form name="login" method="post" action="UserServlet?method=login">
 -->
			<form id="loginForm" name="login" method="post" action="login.do">
				<label id="loginnameError" class="errorClass">${errors.loginnameError }</label>
				<input type="text" name="loginname" value="${userForm.loginname }"
					class="inputClass" id="loginname" <%-- 	
					onfocus="this.value = '';"
					onblur="if(this.value==''){this.value='Username';}" 
				--%> 
				/>
				<label id="loginpassError" class="errorClass">${errors.loginpassError }</label>
				<input type="password" name="loginpass"
					value="${userForm.loginpass }" class="inputClass" id="loginpass" <%--
					onfocus="this.value='';"
					onblur="if(this.value==''){this.value='1223334444'}"  
				--%>
				/>
				<label id="verifyCodeError" class="errorClass">${errors.verifyCodeError }</label>
				<label class="registerlabel">输入验证码：</label> <input type="text"
					name="verifyCode" value="${userForm.verifyCode }" id="verifyCode"
					class="verify" <%--	      
    	            value="" 
    	            onfocus="this.value='';"		
					onblur="if(this.value==''){this.value=''}"
			--%>	
			        />
				<img title="点击" onclick="javascript:refresh(this);"
					src="VerifyCodeServlet" /> <br />
				<div>
					<div>
						<span id="otherLoginSpan">其他账号登录：</span> <a
							href='https://graph.qq.com/oauth2.0/show?which=Login&display=pc&client_id=101507411&response_type=token&scope=all&redirect_uri=http://localhost:8080/blog/index.jsp'>
							<img id="qqLoginBtn" alt="QQ" class="qqClass"
							src="images/login/qq/bt_blue_24X24.png" onclick="toLogin()">
						</a> <img alt="微信" class="wechatClass"
							src="images/login/wechat/icon32_wx_logo.png"> <img alt="微博"
							class="weiboClass" src="images/login/weibo/weibo.png"> <a
							href="jsps/user/regist.jsp" class="hereregister1">注册</a><a
							href="jsps/user/findPassword.jsp" class="fondpassword">忘记密码？</a>

					</div>
				</div>
				<input type="submit" value="登录" class="signin" /><input
					type="reset" value="重置" class="reset" />
				<!-- 脱离文档流 -->
			</form>
		</div>

	</div>

	<div class="copy-rights">
		<p>
			Copyright &copy; 2018. zwq All rights reserved.please visit github
			for more information. <a href="https://github.com/" title="zwqgithub"
				target="_blank">GitHub</a>
		<p />
		<%@include file="/jsps/footer.jsp"%>