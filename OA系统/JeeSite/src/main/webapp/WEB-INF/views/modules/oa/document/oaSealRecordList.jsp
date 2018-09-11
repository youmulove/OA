<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>公章使用记录管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
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
		<li><a href="${ctx}/oa/oaSeal/">公章列表</a></li>
		<shiro:hasPermission name="oa:oaSeal:edit"><li><a href="${ctx}/oa/oaSeal/form">公章添加</a></li></shiro:hasPermission>
		<li class="active"><a href="${ctx}/oa/oaSealRecord/list">公章使用记录</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="oaSealRecord" action="${ctx}/oa/oaSealRecord/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<!-- <ul class="ul-form">
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul> -->
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>公章名称</th>
				<th>公章所属人</th>
				<th>使用事项</th>
				<th>使用人</th>
				<th>使用时间</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="oaSealRecord">
			<tr>
				<td><a href="${ctx}/oa/oaSealRecord/form?id=${oaSealRecord.id}">
					${oaSealRecord.oaSeal.sealName}
				</a></td>
				<td>
					${oaSealRecord.oaSeal.ownerName}
				</td>
				<td>
					${oaSealRecord.oaDispatch.title}
				</td>
				<td>
					${oaSealRecord.createBy.name}
				</td>
				<td>
					<fmt:formatDate value="${oaSealRecord.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
    				<a href="${ctx}/oa/oaSealRecord/form?id=${oaSealRecord.id}">详情</a>
					<a href="${ctx}/oa/oaSealRecord/delete?id=${oaSealRecord.id}" onclick="return confirmx('确认要删除该公章使用记录吗？', this.href)">删除</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>