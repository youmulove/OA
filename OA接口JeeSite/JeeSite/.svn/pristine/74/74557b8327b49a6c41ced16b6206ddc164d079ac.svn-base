<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>公章管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/oa/oaSeal/">公章列表</a></li>
		<li class="active"><a href="${ctx}/oa/oaSeal/form?id=${oaSeal.id}">公章<shiro:hasPermission name="oa:oaSeal:edit">${not empty oaSeal.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="oa:oaSeal:edit">查看</shiro:lacksPermission></a></li>
		<li><a href="${ctx}/oa/oaSealRecord/list">公章使用记录</a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="oaSeal" action="${ctx}/oa/oaSeal/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">公章名称：</label>
			<div class="controls">
				<form:input path="sealName" htmlEscape="false" maxlength="255" class="input-large required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">所属人：</label>
			<div class="controls"><!-- value="${oaNotify.oaNotifyRecordIds}" -->
                <sys:treeselect id="oaNotifyRecord" name="ownerId" value="${oaSeal.ownerId}" labelName="ownerName" labelValue="${oaSeal.ownerName}"
					title="用户" url="/sys/office/treeData?type=3" cssClass="input-medium required" notAllowSelectParent="true" checked="true"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">使用密码：</label>
			<div class="controls">
				<form:input path="usePassword" htmlEscape="false" maxlength="255" class="input-large required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">上传公章：</label>
			<div class="controls">
				<form:hidden id="filePath" path="filePath" htmlEscape="false" maxlength="255" class="input-xlarge required"/>
				<sys:ckfinder input="filePath" type="files" uploadPath="/oa/oaSeal" selectMultiple="true"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="oa:oaSeal:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>