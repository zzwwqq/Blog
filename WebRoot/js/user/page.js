$(function(){
	function _go() {
		var currentPage = $("#currentPageText").val();//获取文本框中输入的当前页码
		alert("af");
		//对当前页码进行整数校验
		//  *代表重复零次或更多次        \d	代表数字      ^代表字符串的开始    $代表字符串的结束
		if(!/^[1-9]\d*$/.test(currentPage)) {        
			alert('请输入正确的页码！');
			return;
		}
		if(currentPage > totalPageNum) {//判断当前页码是否大于最大页
			alert('请输入正确的页码！');
			return;
		}
		location = "http://localhost:8080/blog/PageServlet?method=getPageBean&currentPage="+currentPage;
	}

})
 
