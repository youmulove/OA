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
		<li class="active"><a href="${ctx}/oa/oaDocApproval/alreadyDoc">已办发文列表</a></li>
	</ul>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>标题</th>
				<th>审批结果</th>
				<th>创建时间</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="act" varStatus="sta">
				<c:set var="task" value="${act.histTask}" />
				<c:set var="vars" value="${act.vars}" />
				<c:set var="procDef" value="${act.procDef}" />
		  		<c:choose>
		  			<c:when test="${task.processInstanceId eq page.list[sta.index-1].histTask.processInstanceId}">
		  				
		  			</c:when>
		  			<c:otherwise>
			  			<tr>
						<td>
							<a class="j-bindLayer" href="javascript:;" data-href="${ctx}/act/task/alreadyDetail?taskId=${task.id}&taskName=${fns:urlEncode(task.name)}&taskDefKey=${task.taskDefinitionKey}&procInsId=${task.processInstanceId}&procDefId=${task.processDefinitionId}&status=${status}">${fns:abbr(not empty vars.map.title ? vars.map.title : act.title, 60)}</a>
						</td>
						<td>
							<c:if test="${act.comment=='run'}">
								<a target="_blank" href="${pageContext.request.contextPath}/act/diagram-viewer?processDefinitionId=${task.processDefinitionId}&processInstanceId=${task.processInstanceId}">审批中</a>
							</c:if>
							<c:if test="${act.comment=='successEnd'}">
								<a target="_blank" href="${pageContext.request.contextPath}/act/diagram-viewer?processDefinitionId=${task.processDefinitionId}&processInstanceId=${task.processInstanceId}">审批通过</a>
							</c:if>
							<c:if test="${act.comment=='rejectEnd'}">
								<a target="_blank" href="${pageContext.request.contextPath}/act/diagram-viewer?processDefinitionId=${task.processDefinitionId}&processInstanceId=${task.processInstanceId}">审批未通过</a>
							</c:if>
						</td>
						<td><fmt:formatDate value="${task.createTime}" type="both"/></td>
						<td>
							<a class="j-bindLayer" href="javascript:;" data-href="${ctx}/act/task/alreadyDetail?taskId=${task.id}&taskName=${fns:urlEncode(task.name)}&taskDefKey=${task.taskDefinitionKey}&procInsId=${task.processInstanceId}&procDefId=${task.processDefinitionId}&status=${status}">详情</a>
							<a href="${ctx}/oa/oaDocApproval/deleteAlreadyTask?taskId=${task.id}">删除任务</a>
						</td>
					</tr>
		  			</c:otherwise>
		  		</c:choose>
			  	
			</c:forEach>
		<%-- <c:forEach items="${list}" var="doc">
			<tr>
				<td><a href="${ctx}/oa/oaDispatch/form?id=${doc.oaDispatch.id}">
					${doc.oaDispatch.title}
				</a></td>
				<td>
					${doc.oaDispatch.docNumber}
				</td>
				<td>
					${doc.oaDispatch.docType}
				</td>
				<td>
					${doc.oaDispatch.dispatchNum}
				</td>
				<td>
					<fmt:formatDate value="${doc.oaDispatch.dispatchDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<c:if test="${oaDispatch.yuliu1=='0'}"><font style="color: red">拟稿中</font></c:if>
					<c:if test="${oaDispatch.yuliu1=='1'}"><font style="color: red">已经发起</font></c:if>
				</td>
				<td>
    				<c:if test="${oaDispatch.yuliu1=='0'}">
	    				<a href="${ctx}/oa/oaDispatch/form?id=${oaDispatch.id}">修改</a>&nbsp;&nbsp;
						<a href="${ctx}/oa/oaDispatch/delete?id=${oaDispatch.id}" onclick="return confirmx('确认要删除该发文单吗？', this.href)">删除</a>&nbsp;&nbsp;
						<a href="${ctx}/oa/oaDispatch/docApply?dispatchId=${oaDispatch.id}">发起流程</a>
					</c:if>
					<c:if test="${oaDispatch.yuliu1=='1'}">
						<font style="color: red">不能操作</font>
					</c:if>
				</td>
			</tr>
		</c:forEach> --%>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
	<script src="${ctxStatic}/approvalProcess/js/layer/layer.js"></script>
	<script type="text/javascript">
		/*点击详情*/
	    $(".j-bindLayer").on("click",function() {
	    	var dataHref = $(this).attr("data-href");
	    	layer.open({
		        type: 2,//2是iframe层
		        title: '详情',//标题
		        shadeClose: true,//点击遮罩层也关闭
		        id: '1',//同时只能有一个弹窗
		        scrollbar: false,//弹窗时浏览器不能滚动
		        area: ['600px', '65%'],//弹窗宽高
		        //弹窗点击×的回调函数
		        /* cancel: function(index, layero){
		         layer.close(index);
		         },*/
		        content: dataHref//这个引号里写要访问页面的路径
			});
	    });
	</script>
</body>
</html>