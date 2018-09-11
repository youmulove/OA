<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>待办任务</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		/* $(document).ready(function() {
			
		}); */
		/**
		 * 签收任务
		 */
		function claim(taskId) {
			$.get('${ctx}/act/task/claim' ,{taskId: taskId}, function(data) {
				if (data == 'true'){
		        	top.$.jBox.tip('签收完成');
		            location = '${ctx}/act/task/todo/';
				}else{
		        	top.$.jBox.tip('签收失败');
				}
		    });
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/act/task/todo/">待办任务</a></li>
		<li><a href="${ctx}/act/task/historic/">已办任务</a></li>
		<li><a href="${ctx}/act/task/self">抄送我的</a></li>
		<%-- <li><a href="${ctx}/act/task/myApply/">我发起的</a></li> --%>
	</ul>
	<form:form id="searchForm" modelAttribute="act" action="${ctx}/act/task/todo/" method="get" class="breadcrumb form-search">
		<div>
			<label>流程类型：&nbsp;</label>
			<form:select path="procDefKey" class="input-medium">
				<form:option value="" label="全部流程"/>
				<form:options items="${fns:getDictList('act_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
			</form:select>
			<label>创建时间：</label>
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
				<th>当前环节</th><%--
				<th>任务内容</th> --%>
				<th>流程名称</th>
				<!-- <th>流程版本</th> -->
				<th>创建时间</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list}" var="act" varStatus="st">
				<c:set var="task" value="${act.task}" />
				<c:set var="vars" value="${act.vars}" />
				<c:set var="procDef" value="${act.procDef}" /><%--
				<c:set var="procExecUrl" value="${act.procExecUrl}" /> --%>
				<c:set var="status" value="${act.status}" />
				<tr>
					<td>
						<c:if test="${empty task.assignee}">
							<a href="javascript:claim('${task.id}');" title="签收任务">${fns:abbr(not empty act.vars.map.title ? act.vars.map.title : act.title, 60)}</a>
						</c:if>
						<c:if test="${not empty task.assignee}">
							<a class="j-bindLayer" href="javascript:;" data-href="${ctx}/act/task/detail?taskId=${task.id}&typeFlag=n&taskName=${fns:urlEncode(task.name)}&taskDefKey=${task.taskDefinitionKey}&procInsId=${task.processInstanceId}&procDefId=${task.processDefinitionId}&status=${status}">${fns:abbr(not empty vars.map.title ? vars.map.title : act.title, 60)}</a>
						</c:if>
					</td>
					<td>
						<a target="_blank" href="${pageContext.request.contextPath}/act/diagram-viewer?processDefinitionId=${task.processDefinitionId}&processInstanceId=${task.processInstanceId}">${task.name}</a>
					</td><%--
					<td>${task.description}</td> --%>
					<td>${procDef.name}</td>
					<%-- <td><b title='流程版本号'>V: ${procDef.version}</b></td> --%>
					<td><fmt:formatDate value="${task.createTime}" type="both"/></td>
					<td>
						<c:if test="${empty task.assignee}">
							<a href="javascript:claim('${task.id}');">签收任务</a>
						</c:if>
						<c:if test="${not empty task.assignee}"><%--
							<a href="${ctx}${procExecUrl}/exec/${task.taskDefinitionKey}?procInsId=${task.processInstanceId}&act.taskId=${task.id}">办理</a> --%>
							<%-- <a href="${ctx}/act/task/form?taskId=${task.id}&taskName=${fns:urlEncode(task.name)}&taskDefKey=${task.taskDefinitionKey}&procInsId=${task.processInstanceId}&procDefId=${task.processDefinitionId}&status=${status}">任务办理</a> --%>
							<c:if test="${act.comment == 'n'}">
								<c:if test="${task.assignee == list2[st.index]}">
									<c:if test="${procDef.name != '公文审批流程'}">
										<a href="${ctx}/act/task/modifyTask?taskId=${task.id}&flag='y'">重新发起</a>
										<a href="${ctx}/act/task/modifyTask?taskId=${task.id}&flag='n'">结束</a>
									</c:if>
									<c:if test="${procDef.name == '公文审批流程'}">
										<a href="${ctx}/act/task/modifyTask?taskId=${task.id}&flag='y'">重新修改</a>
									</c:if>
								</c:if>
							</c:if>
							<c:if test="${act.comment != 'n'}">
							<%-- <c:if test="${task.assignee != list2[st.index]}"> --%>
								<%-- <a href="${ctx}/act/task/detail?taskId=${task.id}">任务办理</a> --%>
								<a class="j-bindLayer" href="javascript:;" data-href="${ctx}/act/task/detail?taskId=${task.id}">任务办理</a>
							</c:if>
						</c:if>
						<a target="_blank" href="${pageContext.request.contextPath}/act/diagram-viewer?processDefinitionId=${task.processDefinitionId}&processInstanceId=${task.processInstanceId}">跟踪</a> 
						<c:if test="${fn:contains(list3[st.index].useSealId,currentLoginName)}">
							<c:if test="${procDef.name == '公文审批流程' && task.name=='用印'}">
								<a href="${ctx}/act/task/detail?taskId=${task.id}&typeFlag=y">在线查看</a>
							</c:if>
						</c:if>
						<%--<a target="_blank" href="${ctx}/act/task/trace/photo/${task.processDefinitionId}/${task.executionId}">跟踪2</a> 
						<a target="_blank" href="${ctx}/act/task/trace/info/${task.processInstanceId}">跟踪信息</a> --%>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	
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
		        area: ['600px', '80%'],//弹窗宽高
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
