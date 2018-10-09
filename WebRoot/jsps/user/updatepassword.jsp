<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/jsps/header.jsp"%>

<link rel="stylesheet" type="text/css" href="<c:url value = '/css/user/updatePassword.css'/>">
<!-- 导入JQuery -->
<script type="text/javascript" src = "<c:url value = '/js/jquery-3.2.1.min.js'/>"></script>
<!-- 导入JS文件 -->
<script type="text/javascript" src = "<c:url value = '/js/user/updatePassword.js'/>"></script>

  <script type="text/javascript" charset="UTF-8">
  function _change(){
  	$("#verifyCodeImg").attr("src","VerifyCodeServlet?a="+new Date().getTime());
  
  }
  </script>
<h2>修改密码</h2>
<center>
<div id = "mainDiv">
 <form id = "updatePasswordForm" action="updatePassword.do" method = "POST">
    <table style="margin:0 auto 0 auto">
    <tr>
    <td class = "tdClass">原密码：</td>
     <td><input type = "password" name = "loginpass" id = "loginpass" class = "inputClass" value = "${userForm.loginpass }"/></td>
      <td><label class = "errorClass" id = "loginpassError">${msg }</label></td>
    </tr>
   <tr>
     <td class = "tdClass">新密码：</td>
     <td><input type = "password" name = "newpass" id = "newpass" class = "inputClass" value = "${userForm.newpass }"/></td>
      <td><label class = "errorClass" id = "newpassError">${errors.newpassError }</label></td>
    </tr>
    
    <tr>
     <td class = "tdClass">确认密码：</td>
     <td><input type = "password" name = "reloginpass" id = "reloginpass" class = "inputClass" value = "${userForm.reloginpass }"/></td>
      <td><label class = "errorClass" id = "reloginpassError">${errors.reloginpassError }</label></td>
    </tr>
    
     <tr>
    <td class = "tdClass">验证码：</td>
     <td><input type = "text" name = "verifyCode" id = "verifyCode" class = "inputClass" value = "${userForm.verifyCode }"/></td>
      <td><label class = "errorClass" id = "verifyCodeError">${errors.verifyCodeError }</label></td>
    </tr>
    
     <tr>
    <td></td>
     <td><div class = "verifyCodeDiv"><img id = "verifyCodeImg" alt="这是一个验证码" src = "<c:url value = '/VerifyCodeServlet'/>"></div></td>
      <td><a href = "javascript:_change()">看不清，换一张</a></td>
    </tr>
    
     <tr>
     <td></td>
    <td><input type = "submit" id = "submitBtn" value = "提交"><input type = "reset" value = "重置"/></td>
      <td></td>
    </tr>  
    </table>
 </form>
     </center>
<%@include file="/jsps/footer.jsp" %>
