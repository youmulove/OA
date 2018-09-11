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
		<li><a href="${ctx}/oa/oaDocApproval/returnPage">设置公文审批规则</a></li>
		<li class="active"><a href="${ctx}/oa/oaDocApproval/showApprovalRule">公文审批规则列表</a></li>
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
				<th>审批级别</th>
				<th>审批人</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:set var="startIndex" value="${fn:length(list)-1 }"></c:set> 
		<c:forEach items="${list}" var="approvalRule" varStatus="status">
			<tr>
				<td>
					<c:if test="${list[startIndex - status.index].approvalRole eq '1'}">审稿人</c:if>
					<c:if test="${list[startIndex - status.index].approvalRole eq '2'}">核稿人</c:if>
					<c:if test="${list[startIndex - status.index].approvalRole eq '3'}">会签人</c:if>
					<c:if test="${list[startIndex - status.index].approvalRole eq '4'}">办公室审批人</c:if>
					<c:if test="${list[startIndex - status.index].approvalRole eq '5'}">签发人</c:if>
					<c:if test="${list[startIndex - status.index].approvalRole eq '6'}">校对人</c:if>
					<c:if test="${list[startIndex - status.index].approvalRole eq '7'}">用印人</c:if>
				</td>
				<td>
					${list[startIndex - status.index].approvalPerson}
				</td>
				<td>
    				<%-- <a href="${ctx}/oa/oaDocTemplete/form?id=">修改</a> --%>
    				<a onclick="showLayer('${list[startIndex - status.index].approvalRole}')" class="m--btn">修改</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
<script src="${ctxStatic}/approvalProcess/js/layer/layer.js"></script>
<script src="${ctxStatic}/approvalProcess/js/laydate/laydate.js"></script>
<script type="text/javascript">
	function showLayer(approvalRole){
  		layer.open({
	        type: 2,//2是iframe层
	        title: '修改审批人',//标题
	        shadeClose: true,//点击遮罩层也关闭
	        id: '1',//同时只能有一个弹窗
	        scrollbar: false,//弹窗时浏览器不能滚动
	        area: ['800px', '95%'],//弹窗宽高
	        //弹窗点击×的回调函数
	        /* cancel: function(index, layero){
	         layer.close(index);
	         },*/
	        content: '${ctx}/oa/oaDocApproval/modifyPage?&approvalRole='+approvalRole //这个引号里写要访问页面的路径
		});
  	}
</script>

</body>
</html>