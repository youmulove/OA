<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="org.apache.shiro.web.filter.authc.FormAuthenticationFilter"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>${fns:getConfig('productName')} 登录</title>
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
    	.main .m__box .loginBtn{
    		height:40px;
    		width:300px;
    		background-color:#2fa4e7;
    		border:1px solid #3BA7F5;
    		border-radius:3px;
			font-size:18px;
			color:#fff;
    	}
    	.main .m__box .loginBtn:hover{
    		background-color:#4ca8F6;
    	}
    </style>
    <script src="${ctxStatic}/layer/layer.js" ></script>
	<script src="${ctxStatic}/layer/extend/layer.ext.js" ></script>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#loginForm").validate({
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
			var logout = "${modifyPhone}L";
			if(logout == "L"){
				alert('未登录或登录超时。请重新登录，谢谢！');
			}else{
				alert('手机号已修改，请重新登录，谢谢！');
			}
			top.location = "${ctx}";
		}
	</script>
</head>
<body>
	<!--[if lte IE 6]><br/><div class='alert alert-block' style="text-align:left;padding-bottom:10px;"><a class="close" data-dismiss="alert">x</a><h4>温馨提示：</h4><p>你使用的浏览器版本过低。为了获得更好的浏览体验，我们强烈建议您 <a href="http://browsehappy.com" target="_blank">升级</a> 到最新版本的IE浏览器，或者使用较新版本的 Chrome、Firefox、Safari 等。</p></div><![endif]-->
	<%-- <div class="header">
		<div class="header__newHeader clearfix">
			<div class="header__wrap clearfix">
				<div class="header__logo">
			      <img src="${ctxStatic}/newImage/logo.png" alt="">
			      <span>中浩oa系统</span>
			    </div>
			    
			</div>
		</div>
		<div id="messageBox" style="margin-top:10px;" class="alert alert-error ${empty message ? 'hide' : ''}">
			<button data-dismiss="alert" class="close">×</button>
			<label id="loginError" class="error">${message}</label>
		</div>
	</div> --%>
	<div class="main clearfix">
		<div id="messageBox" style="margin-top:10px;" class="alert alert-error ${empty message ? 'hide' : ''}"><button data-dismiss="alert" class="close">×</button>
			<label id="loginError" class="error">${message}</label>
		</div>
	<div style="position:absolute; top:50%; left:50%; margin-left:-200px; margin-top:-270px;">
		<div class="m__box">
			<h1 class="form-signin-heading">${fns:getConfig('productName')}</h1>
			<form id="loginForm" class="form-signin" action="${ctx}/login" method="post">
				<label class="input-label" for="username">手机号</label>
				<input type="text" id="username" name="username" class="input-block-level required" value="${username}">
				<label class="input-label" for="password">密码</label>
				<input type="password" id="password" name="password" class="input-block-level required">
				<c:if test="${isValidateCodeLogin}"><div class="validateCode">
					<label class="input-label mid" for="validateCode">验证码</label>
					<sys:validateCode name="validateCode" inputCssStyle="margin-bottom:0;"/>
				</div></c:if><%--
				<label for="mobile" title="手机登录"><input type="checkbox" id="mobileLogin" name="mobileLogin" ${mobileLogin ? 'checked' : ''}/></label> --%>
				<label style="display:block; margin-bottom:15px;" for="rememberMe" title="下次不需要再登录"><input type="checkbox" id="rememberMe" name="rememberMe" ${rememberMe ? 'checked' : ''}/> 记住我（公共场所慎用）
				&nbsp;&nbsp;&nbsp;&nbsp;<a href="${ctx}/login?forgot=forgot "  method="post" ">忘记密码</a></label>
				<input class="loginBtn" type="submit" value="登 录"/>&nbsp;&nbsp;
				<%-- <div id="themeSwitch" class="dropdown">
					<a class="dropdown-toggle" data-toggle="dropdown" href="#">${fns:getDictLabel(cookie.theme.value,'theme','默认主题')}<b class="caret"></b></a>
					<ul class="dropdown-menu">
					  <c:forEach items="${fns:getDictList('theme')}" var="dict"><li><a href="#" onclick="location='${pageContext.request.contextPath}/theme/${dict.value}?url='+location.href">${dict.label}</a></li></c:forEach>
					</ul>
					<!--[if lte IE 6]><script type="text/javascript">$('#themeSwitch').hide();</script><![endif]-->
				</div> --%>
			</form>
		</div>
		<%-- <div class="footer">
			Copyright &copy;${fns:getConfig('copyrightYear')} <a href="${pageContext.request.contextPath}${fns:getFrontPath()}">${fns:getConfig('productName')}</a> - Powered By <a href="" target="_blank">中浩科技</a> ${fns:getConfig('version')} 
		</div> --%>
	</div>	
	</div>
	<script src="${ctxStatic}/flash/zoom.min.js" type="text/javascript"></script>
</body>
</html>