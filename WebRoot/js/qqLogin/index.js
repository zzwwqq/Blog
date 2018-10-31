$(function(){
	getInfo();
})
  function getInfo() {
	alert("执行getInfo");
                if(QC.Login.check()) {
                	alert("进入！");
                    QC.api("get_user_info")
                        .success(function(s){//成功回调
                            alert("b");
                            QC.Login.getMe(function(openId, accessToken){
                               alert("c");                        
                                $.post('PageServlet?method=getPageBean',{name:s.data.nickname,openid:openId,otype:1,token:accessToken},function(data,status) {
                                  alert(data);
                                  alert(status);
                                    if(status=="success"){
                                        alert("d");
                                        alert(s.data.nickname+"恭喜你,登录成功!");
                                        location.href = "/MyJsp.jsp";

                                    }else{
                                        alert("d");
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
                        alert("获取用户信息完成！");
                        });
                }else{
                    alert("请登录！");
                    location.href = "/blog/jsps/user/login.jsp";
                }
            }