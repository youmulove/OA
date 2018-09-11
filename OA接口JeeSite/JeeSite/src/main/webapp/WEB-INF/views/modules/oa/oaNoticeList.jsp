<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>公告管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//select2插件默认选中
		var ddd = $("#status10").select2();
		ddd.val("${oaNotice.status}").trigger("change")
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
		<li class="active"><a href="${ctx}/oa/oaNotice/${oaNotice.self?'self':''}">公告列表</a></li>
		<c:if test="${!oaNotice.self}"><shiro:hasPermission name="oa:oaNotice:edit"><li><a href="${ctx}/oa/oaNotice/form">公告添加</a></li></shiro:hasPermission></c:if>
	</ul>
	<form:form id="searchForm" modelAttribute="oaNotice" action="${ctx}/oa/oaNotice/${oaNotice.self?'self':''}" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>标题：</label>
				<form:input path="title" htmlEscape="false" maxlength="200" class="input-medium"/>
			</li>
			<c:if test="${!requestScope.oaNotice.self}"><li><label>状态：</label>
				<%-- <form:radiobuttons path="status" items="${fns:getDictList('oa_notify_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
		 --%>	
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
				<th>标题</th>
				<th>状态</th>
				<th>查阅状态</th>
				<th>更新时间</th>
				<c:if test="${!oaNotice.self}"><shiro:hasPermission name="oa:oaNotice:edit"><th>操作</th></shiro:hasPermission></c:if>
			</tr>
		</thead>
		<tbody>
	
		<c:forEach items="${page.list}" var="oaNotice">
			<tr>
				<td><a href="${ctx}/oa/oaNotice/${requestScope.oaNotice.self?'view':'form'}?id=${oaNotice.id}&isSelf=${isSelf}">
					${fns:abbr(oaNotice.title,50)}
				</a></td>
				<td>
					${fns:getDictLabel(oaNotice.status, 'oa_notify_status', '')}
				</td>
				<td>
					<c:if test="${requestScope.oaNotice.self}">
						${fns:getDictLabel(oaNotice.readFlag, 'oa_notify_read', '')}
					</c:if>
					<c:if test="${!requestScope.oaNotice.self}">
						${oaNotice.readNum} / ${oaNotice.readNum + oaNotice.unReadNum}
					</c:if>
				</td>
				<td>
					<fmt:formatDate value="${oaNotice.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<c:if test="${!requestScope.oaNotice.self}"><shiro:hasPermission name="oa:oaNotice:edit"><td>
    				<a href="${ctx}/oa/oaNotice/form?id=${oaNotice.id}&isSelf=${isSelf}">修改</a>
					<a href="${ctx}/oa/oaNotice/delete?id=${oaNotice.id}" onclick="return confirmx('确认要删除该公告吗？', this.href)">删除</a>
				</td></shiro:hasPermission></c:if>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>