<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>officeRoom管理</title>
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
		<li><a href="${ctx}/oa/officeRoom/showAll">会议室列表</a></li>
		<c:if test="${type=='add'}">
			<li class="active"><a href="${ctx}/oa/officeRoom/form?type=add">会议室添加</a></li>
		</c:if>
		<c:if test="${type!='add'}">
			<li class="active">
				<a href="${ctx}/oa/officeRoom/form?id=${officeRoom.id}">会议室信息</a>
			</li>
		</c:if>
		
	</ul><br/>
	<form:form id="inputForm" modelAttribute="officeRoom" action="${ctx}/oa/officeRoom/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">会议室名称：</label>
			<div class="controls">
				<input class="input-xlarge required" name="officeRoomName" value="${officeRoom.officeRoomName}">
				<%-- <form:input path="officeRoomName" htmlEscape="false" maxlength="50" class="input-xlarge required"/> --%>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">会议室位置：</label>
			<div class="controls">
				<input class="input-xlarge required" name="officeRoomPosition" value="${officeRoom.officeRoomPosition}">
				<span class="help-inline"><font color="red">*</font> </span>
				<%-- <form:input path="officeRoomPosition" htmlEscape="false" maxlength="50" class="input-xlarge "/> --%>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">描述：</label>
			<div class="controls">
				<input class="input-xlarge" name="describe" value="${officeRoom.describe}">
				<%-- <form:input path="describe" htmlEscape="false" maxlength="150" class="input-xlarge "/> --%>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注信息：</label>
			<div class="controls">
				<textarea rows="4" cols="255" class="input-xxlarge" name="remarks">${officeRoom.remarks}</textarea>
				<%-- <form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/> --%>
			</div>
		</div>
		<%-- <div class="control-group">
			<label class="control-label">申请人：</label>
			<div class="controls">
				<form:input path="applyUserid" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">申请起止时间：</label>
			<div class="controls">
				<form:input path="startendtime" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">时长：</label>
			<div class="controls">
				<form:input path="hours" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div> --%>
		<div class="form-actions">
			<c:if test="${type!='add'}">
				<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;
			</c:if>
			<c:if test="${type=='add'}">
				<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;
			</c:if>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>