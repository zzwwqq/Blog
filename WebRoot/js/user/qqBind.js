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
  
  /**
   * 失去焦点，校验
   */
  $(".inputClass").blur(function(){
	  var id = $(this).attr("id");
	  var functionName = "validate"+id.substring(0, 1).toUpperCase()+id.substring(1)+"()";
	  eval(functionName);//执行对应的校验函数 	  
  });
  
  
  /*
	 * 只有boolValue为true时，才能提交数据到后台
	 */
	$("#qqBindForm").submit(function(){
		var boolValue = true;  //表示校验通过		
		if (!validateLoginname()) {
			boolValue = false;
		}
		if (!validateLoginpass()) {
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
function validateLoginname() {
	var id = "loginname";
	var value = $("#" + id).val();
	/*
	 * 非空校验
	 */
	if(!value){
		$("#"+id+"Error").text("用户名不能为空！");
		showError($("#"+id+"Error"));
		return false;
	}	
	
	/*
	 * 长度校验
	 */
	if(value.length < 3 || value.length > 20){
		$("#"+id+"Error").text("用户名长度必须在3~20之间！");
		showError($("#"+id+"Error"));
		return false;
	}
	return true;
}

/**
 * 校验密码
 */
function validateLoginpass() {
	var id = "loginpass";
	var value = $("#" + id).val();
	/*
	 * 非空校验
	 */
	if(!value){
		$("#"+id+"Error").text("密码不能为空！");
		showError($("#"+id+"Error"));
		return false;
	}	
	
	/*
	 * 长度校验
	 */
	if(value.length < 6 || value.length > 16){
		$("#"+id+"Error").text("密码长度必须在6~16之间！");
		showError($("#"+id+"Error"));
		return false;
	}
	
	/*
	 * 密码格式限制
	 */
	if(!/^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z_]{6,16}$/.test(value)){
		$("#"+id+"Error").text("密码必须同时含有字母和数字！");
		showError($("#"+id+"Error"));
		return false; 
	}	
	return true;
}
	



