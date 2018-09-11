<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>请假一览</title>
	<meta name="decorator" content="default"/>
</head>
<body>
	<form action="">
		<table>		
			<tbody>
				${task}
				<%-- <c:set var="task" value="${leave.task}" />
				<c:set var="pi" value="${leave.processInstance}" />
				<tr id="${leave.id }" tid="${task.id}">
					<td>${leave.leaveTypeDictLabel}</td>
					<td>${leave.user.name}</td>
					<td><fmt:formatDate value="${leave.createDate}" type="both"/></td>
					<td><fmt:formatDate value="${leave.startTime}" type="both"/></td>
					<td><fmt:formatDate value="${leave.endTime}" type="both"/></td>
					<td>${task.name}</td>
					<td><fmt:formatDate value="${task.createTime}" type="both"/></td>
					<td>${pi.suspended ? "已挂起" : "正常" }；<b title='流程版本号'>V: ${leave.processDefinition.version}</b></td>
					<td>
						<a target="_blank" href="${ctx}/act/task/trace/photo/${task.processDefinitionId}/${task.executionId}">跟踪</a>
						<c:if test="${empty task.assignee}">
							<a class="claim" href="#" onclick="javescript:claim('${task.id}');">签收</a>
						</c:if>
						<c:if test="${not empty task.assignee}">
							此处用tkey记录当前节点的名称
							<a class="handle" href="#" data-tkey="${task.taskDefinitionKey}" data-tname="${task.name}"  data-id="${leave.id}"  data-tid="${task.id}">办理</a>
						</c:if>
					</td>
				</tr> --%>
			</tbody>
		</table>
	</form>
</body>

</html>
