<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/jsps/header.jsp"%>

<link rel="stylesheet" type="text/css" href="<c:url value = '/css/user/updatePassword.css'/>">
<!-- 导入JQuery -->
<script type="text/javascript" src = "<c:url value = '/js/jquery-3.2.1.min.js'/>"></script>
<!-- 导入JS文件 -->
<script type="text/javascript" src = "<c:url value = '/js/admin/updatePassword.js'/>"></script>

  <script type="text/javascript" charset="UTF-8">
  function _change(){
  	$("#verifyCodeImg").attr("src","VerifyCodeServlet?a="+new Date().getTime());
  
  }
  </script>
  <html style = "background: url('/blog/images/background/001.gif')"></html>
<h2>修改密码</h2>
<center>
<div id = "mainDiv">
 <form  id = "updatePasswordForm" action="AdminUserServlet" method = "POST">
    <input type="hidden" name = "method" value = "updatePassword"/>
    <table style="padding-bottom: 10px">
    <tr>
    <td class = "tdClass">原密码：</td>
     <td><input type = "password" name = "adminpassword" id = "adminpassword" class = "inputClass" value = "${adminForm.adminpassword }"/></td>
      <td><label class = "errorClass" id = "adminpasswordError">${msg }</label></td>
    </tr>
   <tr>
     <td class = "tdClass">新密码：</td>
     <td><input type = "password" name = "newadminpassword" id = "newadminpassword" class = "inputClass" value = "${adminForm.newadminpassword }"/></td>
      <td><label class = "errorClass" id = "newadminpasswordError">${errors.newadminpasswordError }</label></td>
    </tr>
    
    <tr>
     <td class = "tdClass">确认密码：</td>
     <td><input type = "password" name = "readminpassword" id = "readminpassword" class = "inputClass" value = "${adminForm.readminpassword }"/></td>
      <td><label class = "errorClass" id = "readminpasswordError">${errors.readminpasswordError }</label></td>
    </tr>
    
     <tr>
    <td class = "tdClass">验证码：</td>
     <td><input type = "text" name = "verifyCode" id = "verifyCode" class = "inputClass" value = "${userForm.verifyCode }"/></td>
      <td><label class = "errorClass" id = "verifyCodeError">${errors.verifyCodeError }</label></td>
    </tr>
    
  <tr>
    <td class = "tdClass"></td>
     <td><div class = "verifyCodeDiv"><img id = "verifyCodeImg" alt="这是一个验证码" src = "<c:url value = '/VerifyCodeServlet'/>"></div></td>
      <td></td>
    </tr>
    
     <tr>
    <td></td>
      <td style="font-size: 18px;"><a href = "javascript:_change()">看不清，换一张</a></td>
     <td></td>
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
