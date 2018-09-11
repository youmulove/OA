<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>已办任务</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		/* $(document).ready(function() {
			
		}); */
		function page(n,s){
        	location = '${ctx}/act/task/historic/?pageNo='+n+'&pageSize='+s;
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/act/task/todo/">待办任务</a></li>
		<li class="active"><a href="${ctx}/act/task/historic/">已办任务</a></li>
		<li><a href="${ctx}/act/task/self">抄送我的</a></li>
		<%-- <li><a href="${ctx}/act/task/process/">新建任务</a></li> --%>
	</ul>
	<form:form id="searchForm" modelAttribute="act" action="${ctx}/act/task/historic/" method="get" class="breadcrumb form-search">
		<div>
			<label>流程类型：&nbsp;</label>
			<form:select path="procDefKey" class="input-medium">
				<form:option value="" label="全部流程"/>
				<form:options items="${fns:getDictList('act_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
			</form:select>
			<label>完成时间：</label>
			<input id="beginDate"  name="beginDate"  type="text" readonly="readonly" maxlength="20" class="input-medium Wdate" style="width:163px;"
				value="<fmt:formatDate value="${act.beginDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"/>
				　--　
			<input id="endDate" name="endDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate" style="width:163px;"
				value="<fmt:formatDate value="${act.endDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"/>
			&nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
		</div>
		
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>标题</th>
				<th>审批结果</th><%--
				<th>任务内容</th> --%>
				<th>流程名称</th>
				<!-- <th>流程版本</th> -->
				<th>创建时间</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="act" varStatus="sta">
				<c:set var="task" value="${act.histTask}" />
				<c:set var="vars" value="${act.vars}" />
				<c:set var="procDef" value="${act.procDef}" />
				<%-- <c:set var="procExecUrl" value="${act.procExecUrl}" /> --%>
				<%-- <c:set var="status" value="${act.status}" /> --%>
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
							<%-- <a target="_blank" href="${pageContext.request.contextPath}/act/diagram-viewer?processDefinitionId=${task.processDefinitionId}&processInstanceId=${task.processInstanceId}">${task.name}</a> --%>
						</td>
						<%-- <td>${task.description}</td> --%>
						<td>${procDef.name}</td>
						<%-- <td><b title='流程版本号'>V: ${procDef.version}</b></td> --%>
						<td><fmt:formatDate value="${task.createTime}" type="both"/></td>
						<td>
							<a class="j-bindLayer" href="javascript:;" data-href="${ctx}/act/task/alreadyDetail?taskId=${task.id}&taskName=${fns:urlEncode(task.name)}&taskDefKey=${task.taskDefinitionKey}&procInsId=${task.processInstanceId}&procDefId=${task.processDefinitionId}&status=${status}">详情</a>
						<%-- <c:if test="${empty task.executionId}"> --%>
							<a href="${ctx}/act/task/deleteTask?taskId=${task.id}&reason=" onclick="return promptx('删除任务','删除原因',this.href);">删除任务</a>
						<%-- </c:if> --%>
						</td>
					</tr>
		  			</c:otherwise>
		  		</c:choose>
			  	
			</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
	
	<script src="${ctxStatic}/approvalProcess/js/layer/layer.js"></script>
	<script>
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
