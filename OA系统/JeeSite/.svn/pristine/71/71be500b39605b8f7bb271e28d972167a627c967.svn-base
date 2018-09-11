<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>公告管理</title>
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
		<%-- <li><a href="${ctx}/oa/oaNotice/">公告列表</a></li> --%>
		<c:choose>
			<c:when test="${isSelf == 'self'}">
				<li><a href="${ctx}/oa/oaNotice/self">公告列表</a></li>
			</c:when>
			<c:otherwise>
				<li><a href="${ctx}/oa/oaNotice/">公告列表</a></li>
			</c:otherwise>
		</c:choose>
		<li class="active"><a href="${ctx}/oa/oaNotice/form?id=${oaNotice.id}">公告<shiro:hasPermission name="oa:oaNotice:edit">${oaNotice.status eq '1' ? '查看' : not empty oaNotice.id ? '修改' : '添加'}</shiro:hasPermission><shiro:lacksPermission name="oa:oaNotice:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="oaNotice" action="${ctx}/oa/oaNotice/save" method="post" class="form-horizontal">
		<input type="hidden" value="${isSelf}" />
		<form:hidden path="id"/>
		<sys:message content="${message}"/>	
		<div class="control-group">
			<label class="control-label">标题：</label>
			<div class="controls">
				<form:input path="title" htmlEscape="false" maxlength="200" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">内容：</label>
			<div class="controls">
				<form:textarea path="content" htmlEscape="false" rows="6" maxlength="2000" class="input-xxlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>	
			</div>
		</div>
		<c:if test="${oaNotice.status ne '1'}">
			<div class="control-group">
				<label class="control-label">附件：</label>
				<div class="controls">
					<form:hidden id="filePath" path="filePath" htmlEscape="false" maxlength="255" class="input-xlarge"/>
				<sys:ckfinder input="filePath" type="files" uploadPath="/oa/oaNotice" selectMultiple="true"/>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">状态：</label>
				<div class="controls">
					<form:radiobuttons path="status" items="${fns:getDictList('oa_notice_status')}" itemLabel="label" itemValue="value" htmlEscape="false" class="required"/>
					<span class="help-inline"><font color="red">*</font> 发布后不能进行操作。</span>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">接受人：</label>
				<div class="controls">
	                <sys:treeselect id="oaNoticeRecord" name="oaNoticeRecordIds" value="${oaNotice.oaNoticeRecordIds}" labelName="oaNoticeRecordNames" labelValue="${oaNotice.oaNoticeRecordNames}"
						title="用户" url="/sys/office/treeData?type=3" cssClass="input-medium required" notAllowSelectParent="true" checked="true"/>
					<span class="help-inline"><font color="red">*</font> </span>
				</div>
			</div>
		</c:if>
		<c:if test="${oaNotice.status eq '1'}">
			<div class="control-group">
				<label class="control-label">附件：</label>
				<div class="controls">
					<form:hidden id="filePath" path="filePath" htmlEscape="false" maxlength="255" class="input-xlarge"/>
					<sys:ckfinder input="filePath" type="files" uploadPath="/oa/notice" selectMultiple="true" readonly="true" />
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">接受人：</label>
				<div class="controls">
					<table id="contentTable" class="table table-striped table-bordered table-condensed">
						<thead>
							<tr>
								<th>接受人</th>
								<th>接受部门</th>
								<th>阅读状态</th>
								<th>阅读时间</th>
							</tr>
						</thead>
						<tbody>
						<c:forEach items="${oaNotice.oaNoticeRecordList}" var="oaNoticeRecord">
							<tr>
								<td>
									${oaNoticeRecord.user.name}
								</td>
								<td>
									${oaNoticeRecord.officeNames}
								</td>
								<td>
									${fns:getDictLabel(oaNoticeRecord.readFlag, 'oa_notice_read', '')}
								</td>
								<td>
									<fmt:formatDate value="${oaNoticeRecord.readDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
								</td>
							</tr>
						</c:forEach>
						</tbody>
					</table>
					已查阅：${oaNotice.readNum} &nbsp; 未查阅：${oaNotice.unReadNum} &nbsp; 总共：${oaNotice.readNum + oaNotice.unReadNum}
				</div>
			</div>
		</c:if>
		<div class="form-actions">
			<c:if test="${oaNotice.status ne '1'}">
				<shiro:hasPermission name="oa:oaNotice:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			</c:if>
			<!-- <input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/> -->
			<%-- <a class="btn btn-primary" href="${ctx}/oa/oaNotice/self">返回</a> --%>
		<c:choose>
			<c:when test="${isSelf == 'self'}">
				<a class="btn btn-primary" href="${ctx}/oa/oaNotice/self">返回</a>
			</c:when>
			<c:otherwise>
				<a class="btn btn-primary" href="${ctx}/oa/oaNotice/">返回</a>
			</c:otherwise>
		</c:choose>
		</div>
	</form:form>
</body>
</html>