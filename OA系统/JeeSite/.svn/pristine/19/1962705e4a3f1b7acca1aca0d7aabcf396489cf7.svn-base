<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="org.apache.shiro.web.filter.authc.FormAuthenticationFilter"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>${fns:getConfig('productName')} 忘记密码</title>
	<meta name="decorator" content="blank"/>
	<style type="text/css">
      html,body,table{background-color:#f5f5f5;width:100%;text-align:center;}.form-signin-heading{font-family:Helvetica, Georgia, Arial, sans-serif, 黑体;font-size:36px;margin-bottom:20px;color:#0663a2;}
      .form-signin{
      	/* position:relative;text-align:left;width:300px;padding:25px 29px 29px;margin:0 auto 20px;background-color:#fff;border:1px solid #e5e5e5;
        	-webkit-border-radius:5px;-moz-border-radius:5px;border-radius:5px;-webkit-box-shadow:0 1px 2px rgba(0,0,0,.05);-moz-box-shadow:0 1px 2px rgba(0,0,0,.05);box-shadow:0 1px 2px rgba(0,0,0,.05); */
      	text-align: left;
	    width: 300px;
	    margin: 0 auto;
      }
      .form-signin .checkbox{margin-bottom:10px;color:#0663a2;} .form-signin .input-label{font-size:16px;line-height:23px;color:#999;}
      .form-signin .input-block-level{font-size:16px;height:auto;margin-bottom:15px;padding:7px;*width:283px;*padding-bottom:0;_padding:7px 7px 9px 7px;}
      .form-signin .btn.btn-large{font-size:16px;} .form-signin #themeSwitch{position:absolute;right:15px;bottom:10px;}
      .form-signin div.validateCode {padding-bottom:15px;} .mid{vertical-align:middle;}
      /*.header{height:80px; padding-top:20px;} */ .alert{position:relative;width:300px;margin:0 auto;*padding-bottom:0px;}
      label.error{background:none;width:270px;font-weight:normal;color:inherit;margin:0;}
    	/* 新加的头部样式 */
    	html,body{
    		height:100%;
    		font-size:0;
    	}
    	.footer{
    		font-size:14px;
    		color:#fff;
    	}
    	/* .header__newHeader{
    		height: 62px;
		    font-size: 16px;
		    background-color: #fdfdfd;
    	}
    	.header__newHeader .header__wrap{
    		width:1080px;
    		margin:0 auto;
    	}
    	.header__newHeader .header__logo{
    		float: left;
		    height: 62px;
		    line-height: 62px;
		    text-align: left;
		    margin-right: 40px;
		    cursor: pointer;
    	}
    	.header__newHeader .header__logo span{
    		margin-left:5px;
    		font-size:17px;
    	}
    	.header__newHeader .header__logo img{
    		width:32px;
    		height:32px;
    	} */
    	.main{
    		/* height: -moz-calc(100% - 62px);
			height: -webkit-calc(100% - 62px);
			height: calc(100% - 62px); */
			position:relative;
			height:100%;
    		background:url("${ctxStatic}/newImage/loginBackground.jpg") no-repeat;
    		background-size:cover;
    	}
    	.main .m__box{
    		width:300px;
    		margin:50px auto;
    		background-color:#EDEDEF;
    		padding:50px;
    		border-radius:5px;
    	}
    	.main .m__box .subBtn{
    		height:40px;
    		width:300px;
    		background-color:#2fa4e7;
    		border:1px solid #3BA7F5;
    		border-radius:3px;
			font-size:18px;
			color:#fff;
    	}
    	.main .m__box .subBtn:hover{
    		background-color:#4ca8F6;
    	}
    	/* 修正验证码位置 */
    	.validateCode{
    		position:relative;
    	}
    	.validateCode>input{
    		width:300px;
    	}
    	.validateCode>img{
    		position:absolute;
    		top:5px;
    		right:2px;
    	}
    	/* 发送手机验证码的位置 */
    	.c-sendPhoneCode{
    		position:relative;
    	}
    	.c-sendPhoneCode .j-disabled{
    		position:absolute;
    		top:3px;
    		right:2px;
    	}
    </style>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#nextForm").validate({
				rules: {
					validateCode: {remote: "${pageContext.request.contextPath}/servlet/validateCodeServlet"}
				},
				messages: {
					username: {required: "请填写用户名."},password: {required: "请填写密码."},
					validateCode: {remote: "验证码不正确.", required: "请填写验证码."}
				},
				errorLabelContainer: "#messageBox",
				errorPlacement: function(error, element) {
					error.appendTo($("#loginError").parent());
				}
			});
		});
		// 如果在框架或在对话框中，则弹出提示并跳转到首页
		if(self.frameElement && self.frameElement.tagName == "IFRAME" || $('#left').length > 0 || $('.jbox').length > 0){
			alert('未登录或登录超时。请重新登录，谢谢！');
			top.location = "${ctx}";
		}
	</script>
</head>
<body>
	<div class="main clearfix">
		<div id="messageBox" style="margin-top:10px;" class="alert alert-error ${empty message ? 'hide' : ''}"><button data-dismiss="alert" class="close">×</button>
			<label id="loginError" class="error">${message}</label>
		</div>
	<div style="position:absolute; top:50%; left:50%; margin-left:-200px; margin-top:-295px;">
		<div class="m__box nextBox">
			<h4 class="form-signin-heading" style="font-size:25px;">手机找回密码</h4>
			<form id="nextForm" class="form-signin" action="${ctx}/forget" method="get">
				<label class="input-label" for="username">手机号</label>
				<input type="text" id="username" name="username" class="input-block-level required" value="${username}">
				<label class="input-label mid" for="validateCode">验证码</label>
				<div class="j-validateCode validateCode" style="padding:0;">
					<sys:validateCode name="validateCode"  inputCssStyle="width:286px; margin-bottom:15px; padding:7px;" buttonCssStyle="display:none;"/>
				</div>
				<label class="input-label" for="userPhoneCode">手机验证码</label>
				<div class="c-sendPhoneCode">
					<input type="text" id="userPhoneCode" name="userPhoneCode" class="input-block-level required" value="">
					<input id="btn" class="j-disabled btn btn-primary" type="button" disabled="disabled" onclick="ajaxCode(this)" value="获取验证码">
				</div>
				<input class="j-nextBtn subBtn" type="button" value="下一步"/>
				
			</form>
		</div>
		<div class="m__box pwdBox" style="display:none;">
			<h4 class="form-signin-heading" style="font-size:25px;">手机找回密码</h4>
			<form id="loginForm" class="form-signin" action="${ctx}/forget" method="post" >
				<label class="input-label" for="userNewPwd">新的密码</label>
				<input type="password" id="userNewPwd" name="userNewPwd" class="input-block-level required" value="">
				<label class="input-label"  for="userNewPwdRepeat">确认新密码</label>
				<input type="password" id="userNewPwdRepeat" name="userNewPwdRepeat" class="input-block-level required" value="">
				<input class="nextBtn subBtn" id=submit type="button" value="提交" onclick="submit1(this)"/>
			</form>
		</div>
	</div>	
	</div>
	<script src="${ctxStatic}/flash/zoom.min.js" type="text/javascript"></script>
	<script src="${ctxStatic}/layer/layer.js" ></script>
	<script src="${ctxStatic}/layer/extend/layer.ext.js" ></script>
	<script>
		$("#validateCode").on("keyup",function(){
				var validateCode = $(this).val();
				if(validateCode == ""){
					validateCode = "false";
				}
				$.ajax({
					url:"${pageContext.request.contextPath}/servlet/validateCodeServlet?validateCode="+validateCode,
					data:null,
					dataType:"json",
					type:"get",
					success:function(data){
						if(data){
							$(".j-disabled").prop("disabled",false);
						}else{
							$(".j-disabled").prop("disabled",true);
						}				
					},
					error:function(){
						
					}
				});
			});
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
	                },1000);
	            }
	        }
	        /* 点击发送手机验证码的地方 */
	        function ajaxCode(othis){
	        	var username=$('#username').val();
	        	$.ajax({
					url:"${ctx}/forget",
					data:{"username":username,"code":"request"},
					dataType:"json",
					type:"post",
					success: function(result){
						layer.msg(result.message);
					}
				});
				/* var username=$('#username').val();
				window.location.href="${ctx}/forget?username="+username+"&code="+"request", */
				
				time(othis);
			}
			/* 点击下一步，跳转到输入密码页 */
			$(".j-nextBtn").click(function(){
				/* 此处为点击下一步的提交第一页表单的ajax */
				var kvData = $(this).closest("form").serialize();
				$.ajax({
					url:"${ctx}/forget",
					data:kvData,
					dataType:"json",
					type:"post",
					success:function(data){
						/* 点击下一步返回的数据中status为true时说明成功，跳到下一页面 */
						if(data.state == "0"){
							$(".nextBox").hide();
							$(".pwdBox").show();
						}
						if(data.state == "1"){
							layer.msg(data.message);
						}
					},
				});
			});
			
			
			 function submit1(othis){
	        	var userNewPwd=$('#userNewPwd').val();
	        	var userNewPwdRepeat=$('#userNewPwdRepeat').val();
	        	var submit="submit";
	        	$.ajax({
					url:"${ctx}/forget",
					data:{"userNewPwd":userNewPwd,"submit":"submit","userNewPwdRepeat":userNewPwdRepeat},
					dataType:"json",
					type:"post",
					success: function(result){
						layer.msg(result.message);
						if(result.state == "0"){
							window.location.href = "${ctx}/login";
						}
					}
				});
			}
			
			
			
			
	</script>
</body>
</html>