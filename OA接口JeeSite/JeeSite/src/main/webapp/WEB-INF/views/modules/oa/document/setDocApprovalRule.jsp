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
		<li class="active"><a href="${ctx}/oa/oaDocApproval/returnPage">设置公文审批规则</a></li>
		<li><a href="${ctx}/oa/oaDocApproval/showApprovalRule">修改公文审批规则</a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="oaSeal" action="${ctx}/oa/oaDocApproval/setDocApprovalRule" method="post" class="form-horizontal">
		<%-- <form:hidden path="id"/> --%>
		<sys:message content="${message}"/>
		<div class="control-group">
			<font color="blue">拟稿-->审稿-->核稿-->会签-->办公室审批-->签发-->校对-->用印</font>
		</div>		
		<div class="control-group">
			<label class="control-label">审稿人：</label>
			<div class="controls">
                <sys:treeselect id="oaNotifyRecordsg" name="sgId" value="${oaNotify.oaNotifyRecordIds}" labelName="oaNotifyRecordNames" labelValue="${oaNotify.oaNotifyRecordNames}"
					title="用户" url="/sys/office/treeData?type=3" cssClass="input-medium required" notAllowSelectParent="true" checked="true"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">核稿人：</label>
			<div class="controls"><!-- value="${oaNotify.oaNotifyRecordIds}" -->
                <sys:treeselect id="oaNotifyRecordhg" name="hgId" value="" labelName="oaNotifyRecordNames" labelValue="${oaNotify.oaNotifyRecordNames}"
					title="用户" url="/sys/office/treeData?type=3" cssClass="input-medium required" notAllowSelectParent="true" checked="true"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">会签人：</label>
			<div class="controls"><!-- value="${oaNotify.oaNotifyRecordIds}" -->
                <sys:treeselect id="oaNotifyRecordhq" name="signId" value="" labelName="oaNotifyRecordNames" labelValue="${oaNotify.oaNotifyRecordNames}"
					title="用户" url="/sys/office/treeData?type=3" cssClass="input-medium required" notAllowSelectParent="true" checked="true"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">办公室审批人：</label>
			<div class="controls"><!-- value="${oaNotify.oaNotifyRecordIds}" -->
                <sys:treeselect id="oaNotifyRecordbgs" name="officeApprovalId" value="" labelName="oaNotifyRecordNames" labelValue="${oaNotify.oaNotifyRecordNames}"
					title="用户" url="/sys/office/treeData?type=3" cssClass="input-medium required" notAllowSelectParent="true" checked="true"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">签发人：</label>
			<div class="controls"><!-- value="${oaNotify.oaNotifyRecordIds}" -->
                <sys:treeselect id="oaNotifyRecordqf" name="qfId" value="" labelName="oaNotifyRecordNames" labelValue="${oaNotify.oaNotifyRecordNames}"
					title="用户" url="/sys/office/treeData?type=3" cssClass="input-medium required" notAllowSelectParent="true" checked="true"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">校对人：</label>
			<div class="controls"><!-- value="${oaNotify.oaNotifyRecordIds}" -->
                <sys:treeselect id="oaNotifyRecordjd" name="checkId" value="" labelName="oaNotifyRecordNames" labelValue="${oaNotify.oaNotifyRecordNames}"
					title="用户" url="/sys/office/treeData?type=3" cssClass="input-medium required" notAllowSelectParent="true" checked="true"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">用印人：</label>
			<div class="controls"><!-- value="${oaNotify.oaNotifyRecordIds}" -->
                <sys:treeselect id="oaNotifyRecordyy" name="useSealId" value="" labelName="oaNotifyRecordNames" labelValue="${oaNotify.oaNotifyRecordNames}"
					title="用户" url="/sys/office/treeData?type=3" cssClass="input-medium required" notAllowSelectParent="true" checked="true"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="oa:oaSeal:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>