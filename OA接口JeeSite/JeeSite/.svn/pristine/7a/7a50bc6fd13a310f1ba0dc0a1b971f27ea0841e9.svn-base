<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>公章使用记录管理</title>
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
		<li class="active"><a href="${ctx}/oa/oaSealRecord/form?id=${oaSealRecord.id}">公章使用记录<shiro:hasPermission name="oa:oaSealRecord:edit">${not empty oaSealRecord.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="oa:oaSealRecord:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="oaSealRecord" action="${ctx}/oa/oaSealRecord/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">公章名称：</label>
			<div class="controls">
				<input type="text" class="input-xlarge required" value="${oaSealRecord.oaSeal.sealName}">
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">公章所属人：</label>
			<div class="controls">
				<input type="text" class="input-xlarge required" value="${oaSealRecord.oaSeal.ownerName}">
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">使用公文id：</label>
			<div class="controls">
				<input type="text" class="input-xlarge required" value="${oaSealRecord.oaDispatch.id}">
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">使用公文标题：</label>
			<div class="controls">
				<input type="text" class="input-xlarge required" value="${oaSealRecord.oaDispatch.title}">
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">使用公文类型：</label>
			<div class="controls">
				<input type="text" class="input-xlarge required" value="${oaSealRecord.oaDispatch.docType}">
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">公文发文日期：</label>
			<div class="controls">
				<fmt:formatDate value="${oaSealRecord.oaDispatch.dispatchDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">使用人：</label>
			<div class="controls">
				<input type="text" class="input-xlarge required" value="${oaSealRecord.createBy.name}">
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">使用时间：</label>
			<div class="controls">
				<fmt:formatDate value="${oaSealRecord.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="oa:oaSealRecord:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>