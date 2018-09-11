<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
	<title>修改手机号</title>
	<meta name="decorator" content="default"/>
	<script src="${ctxStatic}/layer/layer.js" ></script>
	<script src="${ctxStatic}/layer/extend/layer.ext.js" ></script>
	<script type="text/javascript">
		var wait=60;
        function time(o) {
            if (wait == 0) {
                o.removeAttribute("disabled");
                o.value="获取验证码";
                wait = 60;
            } else {
                o.setAttribute("disabled", true);
                o.value="重新发送(" + wait + ")";
                wait--;
                setTimeout(function() {
                            time(o);
                        },
                        1000);
            }
        }
		function ajaxReq(o){
			var newPhone=document.getElementById("newPhone").value;
			if(newPhone.length==0){
				alert('手机号码不能为空！');
				return false; 
			}
			var myreg = /^(((13[0-9])|(14[0-9])|(15[^4,\D])|(17[0-9])|(18[0,0-9]))\d{8})$/; 
			if(!myreg.test(newPhone)) 
			{ 
   				 alert('请输入有效的手机号码！');
   				 return false; 
			}
			
			 $.ajax({
                url: "${ctx}/sys/user/sendSms.action",
                data:{
                    newPhone : newPhone
                },
                type: "post",
                dataType: "json",
                success:function(data){
                	layer.msg(data.message);
                	if(data.state=='0'){
                		time(o);
                	}
                }
            });
			/* window.location.href='${ctx}/sys/user/sendSms?newPhone='+newPhone; */
		}
			/* 2017-10-11 新加 验证码比对正确才能发送短信验证码 */
			
			function phoneSave(othis) {
				var newPhone =$('#newPhone').val();
				var verifycode =$('#verifycode').val();
				$.ajax({
					url:"${ctx}/sys/user/modifyPhoneSave",
					data:{"newPhone":newPhone,"verifycode":verifycode},
					dataType:"json",
					type:"post",
					success:function(data){
						layer.msg(data.message);
						if(data.state=='0'){
							window.location.href = "${ctx}/sys/user/userLoginOut";
						}
					}
				});
			}
			
		$(function(){
			$(".j-disabled2").prop("disabled",true)
			$(".j-validatePwd").on("keyup",function(){
				//console.log($(this).val());
				$.ajax({
					url:"${ctx}/sys/user/checkPwd.action",
					data:{pwd:$(this).val()},
					dataType:"json",
					type:"post",
					success:function(data){
					/* alert("66666");
					console.log(data);	 */
						if(data){
							$(".j-disabled2").prop("disabled",false);
						}else{
							$(".j-disabled2").prop("disabled",true);
						}				
					},
					error:function(){
						
					}
				});
			});
			$(".j-validateCode").on("keyup","input",function(){
				//console.log($(this).val());
				$.ajax({
					url:"${ctx}/sys/user/checkCode.action",
					data:{validateCode:$(this).val()},
					dataType:"json",
					type:"post",
					success:function(data){
					/* alert("66666");
					console.log(data);	 */
						if(data){
							$(".j-disabled").prop("disabled",false).css("backgroundColor","#006dcc");
						}else{
							$(".j-disabled").prop("disabled",true);
						}				
					},
					error:function(){
						
					}
				});
			});
			/* 2017-10-11 新加 表单验证 */
			$("#inputForm").validate();
		});
</script>
<style>
	/* #inputForm .j-disabled{
		background-color:#999;
	} */
</style>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/sys/user/info">个人信息</a></li>
		<li><a href="${ctx}/sys/user/modifyPwd">修改密码</a></li>
		<li class="active"><a href="${ctx}/sys/user/modifyPhone">修改手机号</a></li>
		<li ><a href="${ctx}/sys/user/modifyEmail">修改邮箱</a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="user" action="${ctx}/sys/user/modifyPhone" method="post"  class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>
		<div class="control-group">
			<label class="control-label">原密码:</label>
			<div class="j-pwd controls">
				<input id="pwd" class="j-validatePwd" name="pwd" type="password" value="${pwd}" maxlength="50" minlength="3" class="required"/>
				<input type="hidden" name="modifyPhone" value="modifyPhone" />
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">新手机号:</label>
			<div class="controls">
				<input id="newPhone" class="j-disabled2" name="newPhone" type="text" value="${newPhone}" maxlength="50" minlength="3" class="required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label input-label mid" for="validateCode">图片验证码:</label>
			<div class="j-validateCode validateCode controls">
				<sys:validateCode buttonClass="j-disabled2" name="validateCode" inputCssStyle="margin-bottom:0;"/>
		 	<span class="help-inline"><font color="red">*</font> </span>
		 	</div>	
		</div>
		<div class="control-group">
			<label class="control-label">短信验证码:</label>
			<div class="controls">
				<input id="verifycode" class="j-disabled2" name="verifycode" type="text" value="${verifycode}" placeholder="获取短信验证码" maxlength="50" minlength="3" class="required" />
				      <input id="btn" class="j-disabled btn btn-primary" type="button" onclick="ajaxReq(this)" disabled value="获取验证码"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="form-actions">
			<input id="btnSubmit" class="j-disabled btn btn-primary" type="button" onclick="phoneSave(this)" disabled value="保 存"/>
		</div>
	</form:form>
</body>
</html>