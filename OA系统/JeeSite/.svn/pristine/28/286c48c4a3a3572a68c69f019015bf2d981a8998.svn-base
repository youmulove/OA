<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>officeRoom管理</title>
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
		<li class="active"><a href="${ctx}/oa/officeRoom/showAll">会议室列表</a></li>
		<li><a href="${ctx}/oa/officeRoom/form?type=add">会议室添加</a></li>
	</ul>
	<%-- <form:form id="searchForm" modelAttribute="officeRoom" action="${ctx}/oa/officeRoom/showAll" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		会议室名字：<input type="text" name="officeRoomName">
		<input type="submit" class="btn btn-primary" id="btnSubmit" value="查询">
		<!-- <ul class="ul-form">
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul> -->
	</form:form> --%>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<!-- <th>id</th> -->
				<th>会议室名称</th>
				<th>会议室地点</th>
				<th>更新时间</th>
				<th>备注信息</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="officeRoom">
			<tr>
				<%-- <td><a href="${ctx}/oa/officeRoom/form?id=${officeRoom.id}&type=view">
					${officeRoom.id}
					</a>
				</td> --%>
				<td><a href="${ctx}/oa/officeRoom/form?id=${officeRoom.id}&type=view">
					${officeRoom.officeRoomName}
					</a>
				</td>
				<td>
					${officeRoom.officeRoomPosition}
				</td>
				<td>
					<fmt:formatDate value="${officeRoom.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${officeRoom.remarks}
				</td>
				<td>
    				<a href="${ctx}/oa/officeRoom/form?id=${officeRoom.id}&type=modify">修改</a>
					<a href="${ctx}/oa/officeRoom/delete?id=${officeRoom.id}" onclick="return confirmx('确认要删除该officeRoom吗？', this.href)">删除</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>