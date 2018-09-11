<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>通讯录</title>
	<meta name="decorator" content="default"/>   
	<script type="text/javascript" src="${ctxStatic}/jquery/jq.resizableColumns.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#btnExport").click(function(){
				top.$.jBox.confirm("确认要导出用户数据吗？","系统提示",function(v,h,f){
					if(v=="ok"){
						$("#searchForm").attr("action","${ctx}/sys/user/export");
						$("#searchForm").submit();
					}
				},{buttonsFocus:1});
				top.$('.jbox-body .jbox-icon').css('top','55px');
			});
		});
		function page(n,s){
			if(n) $("#pageNo").val(n);
			if(s) $("#pageSize").val(s);
			$("#searchForm").attr("action","${ctx}/sys/user/list1");
			$("#searchForm").submit();
	    	return false;
	    }
	</script>
	<style type="text/css">
.rc-handle-container{position:relative;}
.rc-handle{position:absolute;width:7px;cursor:ew-resize;*cursor:pointer;margin-left:-3px;}
</style>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/sys/user/list1">员工通讯录</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="user" action="${ctx}/sys/user/list1" method="post" class="breadcrumb form-search ">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<sys:tableSort id="orderBy" name="orderBy" value="${page.orderBy}" callback="page();"/>
		<ul class="ul-form">   
			<li><label>归属公司：</label><sys:treeselect id="company" name="company.id" value="${user.company.id}" labelName="company.name" labelValue="${user.company.name}" 
				title="公司" url="/sys/office/treeData1?type=1" cssClass="input-medium" allowClear="true"/></li>
			<li><label>手机：</label><form:input path="loginName" htmlEscape="false" maxlength="50" class="input-medium"/></li>
			<li class="clearfix"></li>
			<li><label>归属部门：</label><sys:treeselect id="office" name="office.id" value="${user.office.id}" labelName="office.name" labelValue="${user.office.name}" 
				title="部门" url="/sys/office/treeData1?type=2" cssClass="input-medium" allowClear="true" notAllowSelectParent="false"/></li>
			<%-- <li><label>职级：</label><sys:treeselect id="sysrank" name="sysrank.id" value="${user.sysrank.id}" labelName="sysrank.name" labelValue="${user.sysrank.name}" 
				title="职级" url="/sys/sysRank/rankData" cssClass="input-small" allowClear="true" notAllowSelectParent="true"/></li>
			 --%><li><label>姓&nbsp;&nbsp;&nbsp;名：</label><form:input path="name" htmlEscape="false" maxlength="50" class="input-medium"/></li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询" onclick="return page();"/>
				<c:if test="${fns:getUser().sysrank.name eq'部长' || fns:getUser().sysrank.name eq'总助' ||fns:getUser().sysrank.name eq'总经理' || fns:getUser().sysrank.name eq'副总工'}">
				<input id="btnExport" class="btn btn-primary" type="button" value="导出"/>
				</c:if> 
			</li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<!-- table id="contentTable" class="table table-striped table-bordered table-condensed"> -->
	<table id="contentTable" class="table table-striped table-bordered table-condensed listext" data-resizable-columns-id="demo-table">
		<thead><tr><th>归属公司</th><th class="sort-column name">归属部门</th><th class="sort-column login_name">手机</th><th class="sort-column name">姓名</th><th class="sort-column no">工号</th><th class="sort-column email">邮箱</th><!-- <th class="sort-column name">职级</th> --><!-- <th class="sort-column name">角色</th> --><%-- <shiro:hasPermission name="sys:user:edit"><th>操作</th></shiro:hasPermission> --%></tr></thead>
		<tbody>
		<c:forEach items="${page.list}" var="user">
			<tr>
				<td>${user.company.name}</td>
				<td>${user.officeNames}</td>
				<%-- <c:choose>
				<c:when test="${user.office.parent.type=='1'|| user.office.parent.id=='0'}">
					<td>${user.office.name}</td>
				</c:when>
				<c:otherwise>
					<td>${user.office.parent.name}--${user.office.name}</td>
				</c:otherwise>
				</c:choose> --%>
				<td>${user.loginName}</td>
				<td>${user.name}</td>
				<td>${user.no}</td>
				<td>${user.email}</td>
				<%-- <td>${user.sysrank.name}</td> --%>
				<%--  <td>${RoleLists[user.id]}</td> --%>
				<%--
				<td>${user.roleNames}</td> --%>
			<%-- 	<shiro:hasPermission name="sys:user:edit"><td>
    				<a href="${ctx}/sys/user/form?id=${user.id}">修改</a>
					<a href="${ctx}/sys/user/delete?id=${user.id}" onclick="return confirmx('确认要删除该用户吗？', this.href)">删除</a>
				</td></shiro:hasPermission> --%>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
	<script type="text/javascript">
$(function(){
	$("table").resizableColumns({});
});
</script>
</body>
</html>