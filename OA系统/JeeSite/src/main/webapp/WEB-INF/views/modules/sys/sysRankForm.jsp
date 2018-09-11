<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>职级管理</title>
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
		<li><a href="${ctx}/sys/sysRank/">职级列表</a></li>
		<li class="active"><a href="${ctx}/sys/sysRank/form?id=${sysRank.id}">职级<shiro:hasPermission name="sys:sysRank:edit">${not empty sysRank.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="sys:sysRank:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="sysRank" action="${ctx}/sys/sysRank/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">归属公司:</label>
			<div class="controls">
                <sys:treeselect id="company" name="company.id" value="${sysRank.company.id}" labelName="company.name" labelValue="${sysRank.company.name}"
					title="公司" url="/sys/office/treeData?type=1" cssClass="required error input-medium"/>
			</div>
		</div>
		<%-- <div class="control-group">
			<label class="control-label">归属部门:</label>
			<div class="controls">
				<sys:treeselect id="office" name="office.id" value="${sysRank.office.id}" labelName="office.name" labelValue="${sysRank.office.name}"
					title="部门" url="/sys/office/treeData?type=2" cssClass="" allowClear="true" notAllowSelectParent="true"/>
			</div>
		</div> --%>
		<div class="control-group">
			<label class="control-label">职级:</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="100" class=" required input-large "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="sys:sysRank:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>