$(function(){
	//选择所有显示错误的标签，然后遍历所有显示错误的标签
	$(".errorClass").each(function(){
		showError($(this));
	});
	
	 
  /**
   * 得到焦点
   */
  $(".inputClass").focus(function(){
	 var labelId = $(this).attr("id")+"Error";
	 $("#"+labelId).text("");//清空内容
	 showError($("#"+labelId));//隐藏标签
	 
  })
   
   $(".verify").focus(function(){
	 var labelId = $(this).attr("id")+"Error";
	 $("#"+labelId).text("");//清空内容
	 showError($("#"+labelId));//隐藏标签	 
  })
  
  /**
   * 失去焦点，校验
   */
  $(".inputClass").blur(function(){
	  var id = $(this).attr("id");
	  var functionName = "validate"+id.substring(0, 1).toUpperCase()+id.substring(1)+"()";
	  eval(functionName);//执行对应的校验函数 	  
  });
  
  $(".verify").blur(function(){
	  var id = $(this).attr("id");
	  var functionName = "validate"+id.substring(0, 1).toUpperCase()+id.substring(1)+"()";
	  eval(functionName);//执行对应的校验函数 	  
  });
  
  
  /*
	 * 只有boolValue为true时，才能提交数据到后台
	 */
	$("#adminLoginForm").submit(function() {
		var boolValue = true;  //表示校验通过		
		if (!validateAdminname()) {
			boolValue = false;
		}
		if (!validateAdminpassword()) {
			boolValue = false;
		}
		if (!validateVerifyCode()) {
			boolValue = false;
		}	
		return boolValue;
	});  
});

//显示错误信息
function showError(element) {
	var text = element.text();
	if(text){//有内容，显示
		element.css("display","");
	} else {//无内容，隐藏
		element.css("display","none");
	}
}

/**
 * 校验登录名
 */
function validateAdminname() {
	var id = "adminname";
	var value = $("#" + id).val();
	/*
	 * 非空校验
	 */
	if(!value) {
		$("#"+id+"Error").text("用户名不能为空！");
		showError($("#"+id+"Error"));
		return false;
	}	
	$.ajax({
		url : "AdminUserServlet",
		data : {
			method : "ajaxValidateAdminName",
			adminname : value
		},
		type : "POST",
		dataType : "json",
		async : false,
		cache : false,
		success : function(result) {
			if (result) {
				$("#" + id + "Error").text("用户不存在！");
				showError($("#" + id + "Error"));
				return false;
			} else{
			return true;
				
			}
		}
	});
	return true;
}

	
/**
 * 校验密码
 */
function validateAdminpassword() {
	var id = "adminpassword";
	var value = $("#" + id).val();
	/*
	 * 非空校验
	 */
	if(!value){
		$("#"+id+"Error").text("密码不能为空！");
		showError($("#"+id+"Error"));
		return false;
	}	
	return true;
}

/**
 * 校验验证码
 */
function validateVerifyCode() {
	var id = "verifyCode";
	var value = $("#" + id).val();
	/*
	 * 非空校验
	 */
	if(!value){
		$("#" + id+"Error").text("验证码不能为空！");
		showError($("#"+id+"Error"));
		return false;
	}
	
	if(value.length != 4) {
		$("#" + id+"Error").text("验证码长度不对！");
		showError($("#"+id+"Error"));
		return false;
	}
	
	$.ajax({
		url:"AdminUserServlet",
		data:{method:"ajaxValidateVerifyCode", verifyCode:value},
		type:"POST",
		dataType:"json",
		async:false,
		cache:false,
		success:function(result) {
			if(!result) {
				$("#" + id+"Error").text("验证码错误！");
				showError($("#"+id+"Error"));
				return false;
			}
			return true;
		}
	});
	return true;		
}
