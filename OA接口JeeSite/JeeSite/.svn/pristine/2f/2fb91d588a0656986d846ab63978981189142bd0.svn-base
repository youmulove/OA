<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>任务管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
	$(document).ready(function() {
		//select2插件默认选中
		var ddd = $("#status10").select2();
		ddd.val("${oaNotifyTask.status}").trigger("change")
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/oa/oaNotifyTask/${oaNotifyTask.self?'self':''}">任务列表</a></li>
		<c:if test="${!oaNotifyTask.self}"><shiro:hasPermission name="oa:oaNotifyTask:edit"><li><a href="${ctx}/oa/oaNotifyTask/form">任务添加</a></li></shiro:hasPermission></c:if>
	</ul>
	<form:form id="searchForm" modelAttribute="oaNotifyTask" action="${ctx}/oa/oaNotifyTask/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>标题：</label>
				<form:input path="title" htmlEscape="false" maxlength="200" class="input-medium"/>
			</li>
		<c:if test="${!requestScope.oaNotifyTask.self}"><li><label>状态：</label>
				<%-- <span><input id="status10" name="status" type="radio" value=""><label for="status1">全部</label></span> --%>
				<%-- <form:radiobuttons path="status" items="${fns:getDictList('oa_notify_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				<span><input id="status10" name="status" type="radio" value=""><label for="status1">全部</label></span> --%>
				<select id="status10" name="status">
					<option value="">全部</option>
					<option value="0">草稿</option>
					<option value="1">发布</option>
				</select>
			</li></c:if>
		 <li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
			    <th>发送人</th>
				<th>标题</th>
				<th>状态</th>
				<th>结束时间</th>
				<th>查阅状态</th>
				<th>备注信息</th>
				<shiro:hasPermission name="oa:oaNotifyTask:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="oaNotifyTask">
			<tr>
				<td>
					${oaNotifyTask.createBy.name}
				</td>
				<td><a href="${ctx}/oa/oaNotifyTask/form?id=${oaNotifyTask.id}">
					${oaNotifyTask.title}
				</a></td>
			<td>
					${fns:getDictLabel(oaNotifyTask.status, 'oa_notify_Task_status', '')}
				</td>	
				<td>
					<fmt:formatDate value="${oaNotifyTask.endTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<c:if test="${requestScope.oaNotifyTask.self}">
						${fns:getDictLabel(oaNotifyTask.readFlag, 'oa_notifyTask_read', '')}
					</c:if>
					<c:if test="${!requestScope.oaNotifyTask.self}">
						${oaNotifyTask.readNum} / ${oaNotifyTask.readNum + oaNotifyTask.unReadNum}
					</c:if>
				</td>
				<td>
					${oaNotifyTask.remarks}
				</td>
				<shiro:hasPermission name="oa:oaNotifyTask:edit"><td>
    				<%-- <a href="${ctx}/oa/oaNotifyTask/form?id=${oaNotifyTask.id}">修改</a> --%>
					<a href="${ctx}/oa/oaNotifyTask/delete?id=${oaNotifyTask.id}" onclick="return confirmx('确认要删除该任务吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>