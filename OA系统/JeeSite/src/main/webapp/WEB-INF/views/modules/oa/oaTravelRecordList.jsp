<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
	<title>行程管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").attr("action","${ctx}/oa/oaTravelRecord");
			$("#searchForm").submit();
        	return false;
        }
	</script>
	<style>
		.c-search{
			list-style:none;
			margin:0;
		}
		.c-search li{
			float:left;
			padding:5px 15px;
			background-color:#2fa4e7;
			color:#fff;
			margin:0 5px;
		}
		.c-search li:hover{
			background-color:#4fc6f9;
		}
		.c-search li>a{
			color:#fff;
			text-decoration:none;
		}
		#contentTable{
			border:none;
		}
		#contentTable td{
			padding:0;
			background-color:#fff;
			border:none;
		}
		.c-list .c-listContent{
			margin:10px 0;
			padding:5px 10px;
			background-color:#f7f7f7;
		}
	</style>
</head>

<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/oa/oaTravelRecord">行程列表</a></li>
		<shiro:hasPermission name="oa:oaTravelRecord:edit"><li><a href="${ctx}/oa/oaTravelRecord/form">我的行程添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="oaTravelRecord" action="${ctx}/oa/oaTravelRecord}" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>员工姓名：</label>
				<form:input path="name" htmlEscape="false" maxlength="30" class="input-medium" value="${oaTravelRecord.name}"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询" onclick="return page();"/></li>
			<!-- 
			<c:if test="${requestScope.oaTravelRecord.self}">
			<shiro:hasPermission name="oa:oaNotify:edit">
			<li class="btn"><a href="#">修改</a></li>
			<li class="btn"><a href="#">编辑</a></li>
			</shiro:hasPermission>
			</c:if>
			 -->
			<!-- <li class="clearfix"></li> -->
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<div>
	<ul class="c-search" class="clearfix">
	 <li><a href="${ctx}/oa/oaTravelRecord/" style="">全部行程</a></li>
	 <li><a href="${ctx}/oa/oaTravelRecord/self" style="">我的行程</a></li>
	 <li><a href="${ctx}/oa/oaTravelRecord/others" style="">他/她人的行程</a></li>
	</ul>
	
	</div>
	<table id="contentTable" class="c-list table table-striped table-bordered table-condensed" >
		
		
		<c:forEach items="${page.list}" var="oaTravelRecord">
			<tr>
				<td text-align="center">
					<div class="c-listContent">
						<h5><span style="margin-right:10px;">姓名：</span>${oaTravelRecord.name}</h5><br>
						<p><span style="margin-right:10px;">行程：</span>${oaTravelRecord.describe}</p>
	                    <span><span style="margin-right:10px;">时间：</span><fmt:formatDate value="${oaTravelRecord.startTime}" type="date" pattern="yyyy-MM-dd HH:mm:ss"/>&nbsp;
	                                                          至&nbsp;<fmt:formatDate value="${oaTravelRecord.endTime}" type="date" pattern="yyyy-MM-dd HH:mm:ss"/></span>
					</div>
				</td>
				<td>
				    <c:if test="${requestScope.oaTravelRecord.self}">
				    <shiro:hasPermission name="oa:oaNotify:edit">
				  <%--  <a href="${ctx}/oa/oaTravelRecord/form?id=${oaTravelRecord.id}">修改</a>&nbsp;&nbsp;&nbsp;&nbsp; --%>
				    <a href="${ctx}/oa/oaTravelRecord/delete?id=${oaTravelRecord.id}" onclick="return confirmx('确认要删除该我的行程吗？', this.href)">删除</a>
				    </shiro:hasPermission>
				    </c:if></td>		
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>