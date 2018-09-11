<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>职级管理</title>
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
		<li class="active"><a href="${ctx}/sys/sysRank/">职级列表</a></li>
		<shiro:hasPermission name="sys:sysRank:edit"><li><a href="${ctx}/sys/sysRank/form">职级添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="sysRank" action="${ctx}/sys/sysRank/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
			<sys:tableSort id="orderBy" name="orderBy" value="${page.orderBy}" callback="page();"/>
		<ul class="ul-form">
			<li><label>归属公司：</label><sys:treeselect id="company" name="company.id" value="${sysRank.company.id}" labelName="company.name" labelValue="${sysRank.company.name}" 
				title="公司" url="/sys/office/treeData?type=1" cssClass="input-medium" allowClear="true"/></li>
			<li class="clearfix"></li>
			<%-- <li><label>归属部门：</label><sys:treeselect id="office" name="office.id" value="${sysRank.office.id}" labelName="office.name" labelValue="${sysRank.office.name}" 
				title="部门" url="/sys/office/treeData?type=2" cssClass="input-small" allowClear="true" notAllowSelectParent="true"/></li>
			 --%><li><label>职&nbsp;&nbsp;&nbsp;级：</label><form:input path="name" htmlEscape="false" maxlength="50" class="input-large"/></li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询" onclick="return page();"/>
			<li class="clearfix"></li>
		</ul>
		<!-- <ul class="ul-form">
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul> -->
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
	<thead><tr><th>归属公司</th><!-- <th >归属部门</th> --><th  class="sort-column name">职级</th><shiro:hasPermission name="sys:sysRank:edit"><th>操作</th></shiro:hasPermission></tr></thead>
		<tbody>
		<c:forEach items="${page.list}" var="sysRank">
			<tr>
				<td>${sysRank.company.name}</td>
		<%-- 		<td>${sysRank.office.name}</td> --%>
				<td><a href="${ctx}/sys/sysRank/form?id=${sysRank.id}">${sysRank.name}</a></td>
				<shiro:hasPermission name="sys:sysRank:edit"><td>
    				<a href="${ctx}/sys/sysRank/form?id=${sysRank.id}">修改</a>
					<a href="${ctx}/sys/sysRank/delete?id=${sysRank.id}" onclick="return confirmx('确认要删除该用户吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>