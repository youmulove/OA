<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>修改邮箱</title>
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
		   var email=document.getElementById("email").value;
			if(email.length==0){
				alert('邮箱不能为空！');
				return false; 
			}
			var reg = new RegExp("^[a-z0-9]+([._\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$");  
			if(!reg.test(email)) 
			{ 
   				 alert('请输入有效的邮箱地址！');
   				 return false; 
			}

			 $.ajax({
                url: "${ctx}/sys/user/sendMail.action",
                data:{
                    email : email
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
            //window.location.href='${ctx}/sys/user/sendMail?email='+email;
	
		}
		
		//修改email保存
		function emailSave(othis) {
				var email =$('#email').val();
				var verifycode =$('#verifycode').val();
				$.ajax({
					url:"${ctx}/sys/user/modifyEmailSave",
					data:{"email":email,"verifycode":verifycode},
					dataType:"json",
					type:"post",
					success:function(data){
						layer.msg(data.message);
						if(data.state=='0'){
							window.location.href = "${ctx}/login";
						}
					}
				});
			}
		/* 2017-10-11 新加 验证码比对正确才能发送短信验证码  和 原密码对比才能填写*/
		$(function(){
			$(".j-disabled2").prop("disabled",true);
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
				console.log($(this).val());
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
		});
	</script>
	<!-- <style>
		#inputForm .j-disabled{
			background-color:#999;
		}
	</style> -->
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/sys/user/info">个人信息</a></li>
		<li><a href="${ctx}/sys/user/modifyPwd">修改密码</a></li>
		<li><a href="${ctx}/sys/user/modifyPhone">修改手机号</a></li>
		<li class="active"><a href="${ctx}/sys/user/modifyEmail">修改邮箱</a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="user" action="${ctx}/sys/user/modifyEmail" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>
		<div class="control-group">
			<label class="control-label">原密码:</label>
			<div class="j-pwd controls">
				<input id="pwd" class="j-validatePwd" name="pwd" type="password" value="${pwd}" maxlength="50" minlength="3" class="required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">新邮箱:</label>
			<div class="controls">
				<input id="email" class="j-disabled2" name="email" type="text" value="${email}" maxlength="50" minlength="3" class="required"/>
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
			<label class="control-label">邮箱验证码:</label>
			<div class="controls">
				<input id="verifycode" class="j-disabled2" name="verifycode" type="text" value="${verifycode}" placeholder="获取邮箱验证码" maxlength="50" minlength="3" class="required" />
							<input id="btn" class="j-disabled btn btn-primary" disabled type="button" onclick="ajaxReq(this)" value="获取邮箱验证码"/>
				
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="form-actions">
			<input id="btnSubmit" class="j-disabled btn btn-primary" disabled type="button" onclick="emailSave(this)"  value="保 存"/>
		</div>
	</form:form>
</body>
</html>