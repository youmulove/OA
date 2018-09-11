<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>预约会议管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
	$(document).ready(function() {
		//select2插件默认选中
		var ddd = $("#status10").select2();
		ddd.val("${oaNotifyMeeting.status}").trigger("change")
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
		<li class="active"><a href="${ctx}/oa/oaNotifyMeeting/${oaNotifyMeeting.self?'self':''}">会议列表</a></li>
		<c:if test="${!oaNotifyMeeting.self}"><shiro:hasPermission name="oa:oaNotifyMeeting:edit"><li><a href="${ctx}/oa/oaNotifyMeeting/form">预约会议添加</a></li></shiro:hasPermission></c:if>
	</ul>
	<form:form id="searchForm" modelAttribute="oaNotifyMeeting" action="${ctx}/oa/oaNotifyMeeting/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>标题：</label>
				<form:input path="title" htmlEscape="false" maxlength="200" class="input-medium"/>
			</li>
		 <c:if test="${!requestScope.oaNotifyMeeting.self}"><li><label>状态：</label>
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
				<th>开始时间</th>
				<th>结束时间</th>
				<th>查阅状态</th>
				<shiro:hasPermission name="oa:oaNotifyMeeting:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="oaNotifyMeeting">
			<tr>
				<td>
					${oaNotifyMeeting.createBy.name}
				</td>
				<td><a href="${ctx}/oa/oaNotifyMeeting/form?id=${oaNotifyMeeting.id}">
					${oaNotifyMeeting.title}
				</a></td>
			 <td>
					${fns:getDictLabel(oaNotifyMeeting.status, 'oa_notify_meeting_status', '')}
				</td>
				<td>
					<fmt:formatDate value="${oaNotifyMeeting.startTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${oaNotifyMeeting.endTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<c:if test="${requestScope.oaNotifyMeeting.self}">
						${fns:getDictLabel(oaNotifyMeeting.readFlag, 'oa_notify_read', '')}
					</c:if>
					<c:if test="${!requestScope.oaNotifyMeeting.self}">
						${oaNotifyMeeting.readNum} / ${oaNotifyMeeting.readNum + oaNotifyMeeting.unReadNum}
					</c:if>
				</td>
				<shiro:hasPermission name="oa:oaNotifyMeeting:edit"><td>
    				<%-- <%-- <a href="${ctx}/oa/oaNotifyMeeting/form?id=${oaNotifyMeeting.id}">修改</a> --%> 
					<a href="${ctx}/oa/oaNotifyMeeting/delete?id=${oaNotifyMeeting.id}" onclick="return confirmx('确认要删除会议吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>