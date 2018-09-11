<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>发文拟稿</title>
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
		<li class="active"><a href="${ctx}/oa/oaDispatch/form?id=${oaDispatch.id}">发文单<shiro:hasPermission name="oa:oaDispatch:edit">${not empty oaDispatch.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="oa:oaDispatch:edit">查看</shiro:lacksPermission></a></li>
		<li><a href="${ctx}/oa/oaDispatch/myList">我的发文列表</a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="oaDispatch" action="${ctx}/oa/oaDispatch/save" method="post" class="form-horizontal" enctype="multipart/form-data">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">标题：</label>
			<div class="controls">
				<form:input path="title" htmlEscape="false" maxlength="255" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">发文号：</label>
			<div class="controls">
				<form:input path="docNumber" htmlEscape="false" maxlength="255" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">文种：</label>
			<div class="controls">
														<!-- 无奈之举把position的权值加到最大，使其在原位上，可以显示验证信息。。。 -->
				<select class="input-xlarge required" style="position:relative !important;" name="docType">
					<option value="">请选择文种</option>
					<c:choose>
						<c:when test="${not empty oaDispatch.docType}">
							<c:if test="${oaDispatch.docType eq '会议纪要'}">
								<option value="会议纪要" selected="selected">会议纪要</option>
								<option value="通报">通报</option>
							</c:if>
							<c:if test="${oaDispatch.docType eq '通报'}">
								<option value="会议纪要">会议纪要</option>
								<option value="通报" selected="selected">通报</option>
							</c:if>
						</c:when>
						<c:otherwise>
							<option value="会议纪要">会议纪要</option>
							<option value="通报">通报</option>
						</c:otherwise>
					</c:choose>
				</select>
				<span class="help-inline"><font color="red">*</font> </span>
				<%-- <form:select path="docType" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select> --%>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">发文份数：</label>
			<div class="controls">
				<form:input path="dispatchNum" htmlEscape="false" maxlength="255" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">发文日期：</label>
			<div class="controls">
				<input name="dispatchDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate required"
					value="<fmt:formatDate value="${oaDispatch.dispatchDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">密级：</label>
			<div class="controls required">
				<%-- <form:radiobuttons path="secretLevel" items="${fns:getDictList('')}" itemLabel="label" itemValue="value" htmlEscape="false" class=""/> --%>
				<c:choose>
					<c:when test="${not empty oaDispatch.secretLevel}">
						<c:if test="${oaDispatch.secretLevel=='1'}">
							<input type="radio" checked="checked" name="secretLevel" value="1">一般&nbsp;&nbsp;
							<input type="radio" name="secretLevel" value="2">秘密&nbsp;&nbsp;
							<input type="radio" name="secretLevel" value="3">机密&nbsp;&nbsp;
							<input type="radio" name="secretLevel" value="4">绝密&nbsp;&nbsp;
						</c:if>
						<c:if test="${oaDispatch.secretLevel=='2'}">
							<input type="radio" name="secretLevel" value="1">一般&nbsp;&nbsp;
							<input type="radio" checked="checked" name="secretLevel" value="2">秘密&nbsp;&nbsp;
							<input type="radio" name="secretLevel" value="3">机密&nbsp;&nbsp;
							<input type="radio" name="secretLevel" value="4">绝密&nbsp;&nbsp;
						</c:if>
						<c:if test="${oaDispatch.secretLevel=='3'}">
							<input type="radio" name="secretLevel" value="1">一般&nbsp;&nbsp;
							<input type="radio" name="secretLevel" value="2">秘密&nbsp;&nbsp;
							<input type="radio" checked="checked" name="secretLevel" value="3">机密&nbsp;&nbsp;
							<input type="radio" name="secretLevel" value="4">绝密&nbsp;&nbsp;
						</c:if>
						<c:if test="${oaDispatch.secretLevel=='4'}">
							<input type="radio" name="secretLevel" value="1">一般&nbsp;&nbsp;
							<input type="radio" name="secretLevel" value="2">秘密&nbsp;&nbsp;
							<input type="radio" name="secretLevel" value="3">机密&nbsp;&nbsp;
							<input type="radio" checked="checked" name="secretLevel" value="4">绝密&nbsp;&nbsp;
						</c:if>
					</c:when>
					<c:otherwise>
						<input type="radio" checked="checked" name="secretLevel" value="1">一般&nbsp;&nbsp;
						<input type="radio" name="secretLevel" value="2">秘密&nbsp;&nbsp;
						<input type="radio" name="secretLevel" value="3">机密&nbsp;&nbsp;
						<input type="radio" name="secretLevel" value="4">绝密&nbsp;&nbsp;
					</c:otherwise>
				</c:choose>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">紧急程度：</label>
			<div class="controls">
				<%-- <form:radiobuttons path="urgency" items="${fns:getDictList('')}" itemLabel="label" itemValue="value" htmlEscape="false" class=""/> --%>
				<c:choose>
					<c:when test="${not empty oaDispatch.urgency}">
						<c:if test="${oaDispatch.urgency=='1'}">
							<input type="radio" checked="checked" name="urgency" value="1">普通&nbsp;&nbsp;
							<input type="radio" name="urgency" value="2">重要&nbsp;&nbsp;
							<input type="radio" name="urgency" value="3">紧急&nbsp;&nbsp;
						</c:if>
						<c:if test="${oaDispatch.urgency=='2'}">
							<input type="radio" name="urgency" value="1">普通&nbsp;&nbsp;
							<input type="radio" checked="checked" name="urgency" value="2">重要&nbsp;&nbsp;
							<input type="radio" name="urgency" value="3">紧急&nbsp;&nbsp;
						</c:if>
						<c:if test="${oaDispatch.urgency=='3'}">
							<input type="radio" name="urgency" value="1">普通&nbsp;&nbsp;
							<input type="radio" name="urgency" value="2">重要&nbsp;&nbsp;
							<input type="radio" checked="checked" name="urgency" value="3">紧急&nbsp;&nbsp;
						</c:if>
					</c:when>
					<c:otherwise>
						<input type="radio" checked="checked" name="urgency" value="1">普通&nbsp;&nbsp;
						<input type="radio" name="urgency" value="2">重要&nbsp;&nbsp;
						<input type="radio" name="urgency" value="3">紧急&nbsp;&nbsp;
					</c:otherwise>
				</c:choose>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">主题词：</label>
			<div class="controls">
				<form:input path="subjectHeading" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">抄送人：</label>
			<div class="controls">
                <sys:treeselect id="oaNotifyRecord" name="dispatchCopy" value="${oaNotify.oaNotifyRecordIds}" labelName="oaNotifyRecordNames" labelValue="${oaNotify.oaNotifyRecordNames}"
					title="用户" url="/sys/office/treeData?type=3" cssClass="input-medium" notAllowSelectParent="true" checked="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">附件公文：</label>
			<div class="controls">
				<sys:ckfinder input="filePath" type="files" uploadPath="/oa/oaDispatch" selectMultiple="true"/>
				<span class="help-inline"><font color="red">*</font> </span>
				<form:input id="filePath" path="filePath" htmlEscape="false" maxlength="255" class="input-xlarge required" style="width:0;padding:0;border:0;"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="oa:oaDispatch:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>