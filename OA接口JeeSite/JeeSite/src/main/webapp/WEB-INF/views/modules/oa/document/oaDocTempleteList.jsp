<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>公文模板管理</title>
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
		<li class="active"><a href="${ctx}/oa/oaDocTemplete/">公文模板列表</a></li>
		<shiro:hasPermission name="oa:oaDocTemplete:edit"><li><a href="${ctx}/oa/oaDocTemplete/form">公文模板添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="oaDocTemplete" action="${ctx}/oa/oaDocTemplete/" method="post" class="breadcrumb form-search">
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
				<th>模板名称</th>
				<th>创建人</th>
				<th>创建时间</th>
				<th>更新时间</th>
				<shiro:hasPermission name="oa:oaDocTemplete:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="oaDocTemplete">
			<tr>
				<td><a href="${ctx}/oa/oaDocTemplete/form?id=${oaDocTemplete.id}">
					${oaDocTemplete.templeteName}
					<%-- <sys:ckfinder input="filePath" type="files" uploadPath="/oa/oaDocTemplete" selectMultiple="true"/> --%>
				</a></td>
				<td>
					${oaDocTemplete.createBy.name}
				</td>
				<td>
					<fmt:formatDate value="${oaDocTemplete.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${oaDocTemplete.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="oa:oaDocTemplete:edit"><td>
    				<a href="${ctx}/oa/oaDocTemplete/form?id=${oaDocTemplete.id}">修改</a>
					<a href="${ctx}/oa/oaDocTemplete/delete?id=${oaDocTemplete.id}" onclick="return confirmx('确认要删除该公文模板吗？', this.href)">删除</a>
					<a href="${oaDocTemplete.filePath}">下载模板</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>