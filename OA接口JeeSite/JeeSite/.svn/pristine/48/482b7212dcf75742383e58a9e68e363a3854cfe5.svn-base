<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>公章管理</title>
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
		<li class="active"><a href="${ctx}/oa/oaSeal/">公章列表</a></li>
		<shiro:hasPermission name="oa:oaSeal:edit"><li><a href="${ctx}/oa/oaSeal/form">公章添加</a></li></shiro:hasPermission>
		<li><a href="${ctx}/oa/oaSealRecord/list">公章使用记录</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="oaSeal" action="${ctx}/oa/oaSeal/" method="post" class="breadcrumb form-search">
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
				<th>所属人</th>
				<th>使用密码</th>
				<th>创建时间</th>
				<th>更新时间</th>
				<shiro:hasPermission name="oa:oaSeal:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="oaSeal">
			<tr>
				<td><a href="${ctx}/oa/oaSeal/form?id=${oaSeal.id}">
					${oaSeal.sealName}
				</a></td>
				<td>
					${oaSeal.ownerName}
				</td>
				<td>
					${oaSeal.usePassword}
				</td>
				<td>
					<fmt:formatDate value="${oaSeal.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${oaSeal.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="oa:oaSeal:edit"><td>
    				<a href="${ctx}/oa/oaSeal/form?id=${oaSeal.id}">修改</a>
					<a href="${ctx}/oa/oaSeal/delete?id=${oaSeal.id}" onclick="return confirmx('确认要删除该公章吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>