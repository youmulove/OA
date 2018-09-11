<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>在线用户管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
	</script>
</head>
<body>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead><tr>
			<th>SessionID</th>
			<th>姓名</th>
			<th>邮箱</th>
			<th>创建回话</th>
			<th>回话最后活动</th>
			<th>状态</th>
			<th>操作</th>
		</tr></thead>
		<tbody>
		<c:forEach items="${list}" var="it">
			<tr>
					<td>${it.sessionId}</td>
					<td>${it.name}</td>
					<td>${it.email}</td>
					<td>${it.startTime}</td>
					<td>${it.lastAccess}</td>
					<td>${(it.sessionStatus)}</td>
					<td>
						<a href="${ctx}/sys/user/onlineDetails/${it.sessionId}">详情</a>
						<a v="onlineDetails"href="javascript:void(0);" sessionId="${it.sessionId}" status="${(it.sessionStatus)}">${(it.sessionStatus)}</a>
					</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>