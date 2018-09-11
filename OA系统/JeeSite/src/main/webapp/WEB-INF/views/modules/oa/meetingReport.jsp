<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>预约会议报表</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#btnExport").click(function(){
				top.$.jBox.confirm("确认要导出会议数据吗？","系统提示",function(v,h,f){
					if(v=="ok"){
						$("#searchForm").attr("action","${ctx}/oa/meetingReport/export");
						$("#searchForm").submit();
					}
				},{buttonsFocus:1});
				top.$('.jbox-body .jbox-icon').css('top','55px');
			});
			$("#btnImport").click(function(){
				$.jBox($("#importBox").html(), {title:"导入数据", buttons:{"关闭":true}, 
					bottomText:"导入文件不能超过5M，仅允许导入“xls”或“xlsx”格式文件！"});
			});
		});
		function page(n,s){
			if(n) $("#pageNo").val(n);
			if(s) $("#pageSize").val(s);
			$("#searchForm").attr("action","${ctx}/oa/meetingReport/list");
			$("#searchForm").submit();
	    	return false;
	    }
	</script>
</head>
<body>
	<!--  <div id="importBox" class="hide">
		<form id="importForm" action="${ctx}/oa/meetingReport/import" method="post" enctype="multipart/form-data"
			class="form-search" style="padding-left:20px;text-align:center;" onsubmit="loading('正在导入，请稍等...');"><br/>
			<input id="uploadFile" name="file" type="file" style="width:330px"/><br/><br/>　　
			<input id="btnImportSubmit" class="btn btn-primary" type="submit" value="导  入  "/>
			<a href="${ctx}/oa/meetingReport/import/template">下载模板</a>
		</form>
	</div>-->
	<ul class="nav nav-tabs">
		<li class="active"><a href="#">预约会议报表</a></li>
		
	</ul>
	
	<form:form id="searchForm" modelAttribute="user" action="${ctx}/oa/meetingReport/list?" method="post" class="breadcrumb form-search ">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<sys:tableSort id="orderBy" name="orderBy" value="${page.orderBy}" callback="page();"/>
		<li><label>标&nbsp;&nbsp;&nbsp;题：</label><input type="text" value="${oaNotifyMeeting.title}" name="title"></li>
		<div style="margin-top:8px;">
			<label>日期范围：&nbsp;</label><input id="startTime" name="startTime" type="text" value="<fmt:formatDate value="${oaNotifyMeeting.startTime}" pattern="yyyy-MM-dd"/>" readonly="readonly" maxlength="20" class="input-mini Wdate"
			 onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false,maxDate:'#F{$dp.$D(\'endTime\')||\'2120-10-01\'}'});"/>&nbsp;&nbsp;
				
			<label>&nbsp;--&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label><input id="endTime" name="endTime" value="<fmt:formatDate value="${oaNotifyMeeting.endTime}" pattern="yyyy-MM-dd"/>" type="text" readonly="readonly" maxlength="20" class="input-mini Wdate"
				 onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false,minDate:'#F{$dp.$D(\'startTime\')}',maxDate:'2120-10-01'});"/>&nbsp;&nbsp;
			&nbsp;<label for="exception">
			&nbsp;&nbsp;&nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>&nbsp;&nbsp;<input id="btnExport" class="btn btn-primary" type="button" value="导出"/> 
		</div>
			<li class="clearfix"></li>
	</form:form>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
			
				<th>标题</th>
				<th>发起人</th>
				<th>查阅状态</th>
				<th>开始时间</th>
				<th>结束时间</th>
				<th>接收人</th>
				<th>内容</th>
				<!-- <shiro:hasPermission name="oa:oaNotifyMeeting:edit"><th>操作</th></shiro:hasPermission>-->
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="oaNotifyMeeting">
		
			<tr>
			<!-- <td>${oaNotifyMeeting.id}</td> -->
				<td>
					${oaNotifyMeeting.title}
				</td>
				<td>
				  ${oaNotifyMeeting.userName}
				</td>
				<td>
					<c:if test="${requestScope.oaNotifyMeeting.self}">
						${fns:getDictLabel(oaNotifyMeeting.readFlag, 'oa_notify_read', '')}
					</c:if>
					<c:if test="${!requestScope.oaNotifyMeeting.self}">
						${oaNotifyMeeting.readNum} / ${oaNotifyMeeting.readNum + oaNotifyMeeting.unReadNum}
					</c:if>
				</td>
				<td>
					<fmt:formatDate value="${oaNotifyMeeting.startTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${oaNotifyMeeting.endTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
		<td>${meetWithAccept[oaNotifyMeeting.id]}</td>
				<td><a href="${ctx}/oa/meetingReport/details?id=${oaNotifyMeeting.id}">
					${oaNotifyMeeting.content}</a>
				</td>
				
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>