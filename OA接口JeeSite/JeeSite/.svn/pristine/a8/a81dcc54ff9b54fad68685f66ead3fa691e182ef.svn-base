<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>加班申请报表</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#btnExport").click(function(){
				top.$.jBox.confirm("确认要导出加班申请报表吗？","系统提示",function(v,h,f){
					if(v=="ok"){
						$("#searchForm").attr("action","${ctx}/oa/officeRoomReport/export");
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
			$("#searchForm").attr("action","${ctx}/oa/officeRoomReport/list");
			$("#searchForm").submit();
	    	return false;
	    }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="#">加班申请报表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="user" action="${ctx}/oa/officeRoomReport/list" method="post" class="breadcrumb form-search ">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<sys:tableSort id="orderBy" name="orderBy" value="${page.orderBy}" callback="page();"/>
		<div style="margin-top:8px;">
		<label>所属部门：</label>
		<select id="dept" >
		  <option value="">====请选择所属部门</option>
		</select>
			<label>申请日期范围：&nbsp;</label><input id="startTime" name="startTime" type="text" 
			value="<fmt:formatDate value="${officeRoom.startTime}" pattern="yyyy-MM-dd"/>" readonly="readonly" maxlength="20" class="input-mini Wdate"
			 onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false,maxDate:'#F{$dp.$D(\'endTime\')||\'2120-10-01\'}'});"/>&nbsp;&nbsp;
			<label>&nbsp;--&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label><input id="endTime" name="endTime" 
			value="<fmt:formatDate value="${officeRoom.endTime}" pattern="yyyy-MM-dd"/>" type="text" readonly="readonly" maxlength="20" class="input-mini Wdate"
				 onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false,minDate:'#F{$dp.$D(\'startTime\')}',maxDate:'2120-10-01'});"/>&nbsp;&nbsp;
			&nbsp;<label for="exception">
			&nbsp;&nbsp;&nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>&nbsp;&nbsp;<input id="btnExport" class="btn btn-primary" type="button" value="导出"/> 
		</div>
			<li class="clearfix"></li>
	</form:form>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
			
				<th>申请时间</th>
				<th>申请人</th>
				<th>办公室名</th>
				<th>申请起止时间</th>
				<th>时长</th>
				<th>审批人</th>
				<th>审批状态</th>

				<!-- <shiro:hasPermission name="oa:oaNotifyMeeting:edit"><th>操作</th></shiro:hasPermission>-->
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="officeRoomReport">
		
			<tr>
			<!-- <td>${oaNotifyMeeting.id}</td> -->
			   <td></td>
				<td>
					${officeRoomReport.applyName}
				</td>
				<td>
				  ${officeRoomReport.officeRoomName}
				</td>
				<td>
					${officeRoomReport.startendtime}
				</td>
		       <td>
		       ${officeRoomReport.hours}
		       </td>
		       <td></td>
		       <td></td>
		       
				
				
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>
