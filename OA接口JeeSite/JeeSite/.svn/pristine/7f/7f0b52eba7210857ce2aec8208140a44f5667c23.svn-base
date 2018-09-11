<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>我的行程管理</title>
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
		<li><a href="${ctx}/oa/oaTravelRecord/">行程列表</a></li>
		<li class="active"><a href="${ctx}/oa/oaTravelRecord/form?id=${oaTravelRecord.id}">我的行程<shiro:hasPermission name="oa:oaTravelRecord:edit">${not empty oaTravelRecord.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="oa:oaTravelRecord:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="oaTravelRecord" action="${ctx}/oa/oaTravelRecord/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">员工ID：</label>
			<div class="controls">
				    <!--<sys:treeselect id="user" name="user.id" value="${oaTravelRecord.user.id}" labelName="user.name" labelValue="${oaTravelRecord.user.name}" title="用户" url="/sys/office/treeData?type=3" cssClass="" allowClear="true" notAllowSelectParent="true"/>-->
					<input  type="text" id="user" readonly="true" value="${userNo}" name="user.id">
					
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">员工姓名：</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="30" class="input-xlarge " value="${fns:getUser().name}" readonly="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">行程开始时间：</label>
			<div class="controls">
				<input id="startTime" name="startTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate required "
					value="<fmt:formatDate value="${oaTravelRecord.startTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false,maxDate:'#F{$dp.$D(\'endTime\')||\'2120-10-01\'}'});"/>
								<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">行程结束时间：</label>
			<div class="controls">
				<input id="endTime" name="endTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate required "
					value="<fmt:formatDate value="${oaTravelRecord.endTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false,minDate:'#F{$dp.$D(\'startTime\')}',maxDate:'2120-10-01'});"/>
								<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">行程描述：</label>
			<div class="controls">
			   <!--<form:input path="describe" htmlEscape="false" maxlength="300" class="input-xlarge "/>-->
			   <form:textarea path="describe" htmlEscape="false" rows="3" maxlength="200" class="input-xxlarge required"/>
			   <span class="help-inline"><font color="red">*</font> </span>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="oa:oaTravelRecord:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn btn-primary" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>