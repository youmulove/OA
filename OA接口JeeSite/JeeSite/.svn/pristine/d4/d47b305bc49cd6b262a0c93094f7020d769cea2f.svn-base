<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script src="${ctxStatic}/jquery/jquery-1.8.3.min.js" type="text/javascript"></script>
</head>
<body>
	<form id="sealForm" action="" method="post">
		<table>
			<tr><td></td></tr>
			<tr><td></td></tr>
			<tr><td></td></tr>
			<tr>
				<td>请输入公章密码</td>
				<td><input type="password" id="pwd" name="password"></td>
			</tr>
			<!-- <tr><td></td>
			<td><input type="button" onclick="sub()" value="提交"></td>
			</tr> -->
			<input type="hidden" id="sealId" value="${sealId}">
		</table>
	</form>
</body>
<script type="text/javascript">
	 var formData = function () {
         var sealId = $("#sealId").val();
         var password = $("#pwd").val();
         var data="";
         $.ajax({  
             type: "post",  
             url:"${ctx}/oa/oaSeal/checkPassword",  
             data:{"sealId":sealId,"password":password},// 序列化表单值  
             dataType:"json",
             async: false,  
             error: function(request) {
                 alert("Connection error");  
             }, 
             success: function(ret) { 
             	data = ret;
             	var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
				parent.layer.close(index); //再执行关闭
             }  
         });
         return data;
     };
</script>
</html>