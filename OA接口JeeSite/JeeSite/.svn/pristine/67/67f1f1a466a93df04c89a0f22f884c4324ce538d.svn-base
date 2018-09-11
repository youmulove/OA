<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>发文单管理</title>
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
		<shiro:hasPermission name="oa:oaDispatch:edit"><li><a href="${ctx}/oa/oaDispatch/form">发文单添加</a></li></shiro:hasPermission>
		<li class="active"><a href="${ctx}/oa/oaDispatch/myList">我的发文列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="oaDispatch" action="${ctx}/oa/oaDispatch/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>标题：</label>
				<form:input path="title" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>标题</th>
				<th>发文号</th>
				<th>文种</th>
				<th>发文份数</th>
				<th>发文日期</th>
				<th>当前状态</th>
				<shiro:hasPermission name="oa:oaDispatch:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="oaDispatch">
			<tr>
				<td><a href="${ctx}/oa/oaDispatch/form?id=${oaDispatch.id}">
					${oaDispatch.title}
				</a></td>
				<td>
					${oaDispatch.docNumber}
				</td>
				<td>
					${oaDispatch.docType}
				</td>
				<td>
					${oaDispatch.dispatchNum}
				</td>
				<td>
					<fmt:formatDate value="${oaDispatch.dispatchDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<c:if test="${oaDispatch.yuliu1=='0'}"><!-- <font style="color: red"> -->拟稿中<!-- </font> --></c:if>
					<c:if test="${oaDispatch.yuliu1=='2'}"><!-- <font style="color: red"> -->退回拟稿中<!-- </font> --></c:if>
					<c:if test="${oaDispatch.yuliu1=='1'}"><!-- <font style="color: red"> -->已经发起<!-- </font> --></c:if>
				</td>
				<shiro:hasPermission name="oa:oaDispatch:edit"><td>
    				<c:if test="${oaDispatch.yuliu1=='0'}">
	    				<a href="${ctx}/oa/oaDispatch/form?id=${oaDispatch.id}">修改</a>&nbsp;&nbsp;
						<a href="${ctx}/oa/oaDispatch/delete?id=${oaDispatch.id}" onclick="return confirmx('确认要删除该发文单吗？', this.href)">删除</a>&nbsp;&nbsp;
						<a href="${ctx}/oa/oaDispatch/docApply?dispatchId=${oaDispatch.id}">发起流程</a>
					</c:if>
					<c:if test="${oaDispatch.yuliu1=='1'}">
						<!-- <font style="color: red"> -->不能操作<!-- </font> -->
					</c:if>
					<c:if test="${oaDispatch.yuliu1=='2'}">
						<a href="${ctx}/oa/oaDispatch/form?id=${oaDispatch.id}">修改</a>&nbsp;&nbsp;
						<a href="${ctx}/oa/oaDispatch/docApplyAgain?dispatchId=${oaDispatch.id}&taskId=${taskId}">重新发起</a>
					</c:if>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>