<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>办公室申请报表</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#btnExport").click(function(){
				top.$.jBox.confirm("确认要导出办公室申请报表吗？","系统提示",function(v,h,f){
					if(v=="ok"){
						$("#searchForm").attr("action","${ctx}/oa/oaApplyOfficeroom/export");
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
			$("#searchForm").attr("action","${ctx}/oa/oaApplyOfficeroom/reportList");
			$("#searchForm").submit();
	    	return false;
	    }
	</script>
	<style>
		.c-search li{
			list-style:none;
			line-height:30px;
			height:40px;
		}
		.c-search li label{
			width:100px;
		}
	/* 	.c-search input[type="text"]{
			width:120px;
		} */
	</style>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="#">办公室申请报表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="user" action="${ctx}/oa/oaApplyOfficeroom/reportList" method="post" class="breadcrumb form-search ">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<sys:tableSort id="orderBy" name="orderBy" value="${page.orderBy}" callback="page();"/>
		<div style="margin-top:8px;">
			<ul class="c-search" style="list-style:none;">
				<li class="clearfix">
					<div style="float:left;">
						<label>归属公司：</label>
						<sys:treeselect id="company" name="company.id" value="${user.company.id}" labelName="company.name" labelValue="${oaApplyOfficeroom.company.name}" title="公司" url="/sys/office/treeData?type=1" cssClass="input-medium" allowClear="true"/>
					</div>
					<div style="float:left;">
						<label>归属部门：</label>
						<sys:treeselect id="office" name="office.id" value="${user.office.id}" labelName="office.name" labelValue="${oaApplyOfficeroom.office.name}" title="部门" url="/sys/office/treeData?type=2" cssClass="input-medium" allowClear="true" notAllowSelectParent="true"/>
					</div>
				</li>
				<!-- <label>当前状态：</label>
				<select class="input-medium">
				 <option value="">====请选择状态</option> 
				  <option value="">全部</option>
				  <option value="">待审批</option>  
				  <option value="">正常结束</option>
				  <option value="">被驳回</option>      
				</select><br><br> -->
				<li class="clearfix">
					<div style="float:left;">
						<label>申请日期范围：</label>
						<input id="startTime" name="startTime" type="text" value="<fmt:formatDate value="${officeRoom.startTime}" pattern="yyyy-MM-dd"/>" readonly="readonly" maxlength="20" class="input-medium Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false,maxDate:'#F{$dp.$D(\'endTime\')||\'2120-10-01\'}'});"/>
						<label style="width:50px; margin:0; text-align:center;">--</label>
						<input id="endTime" name="endTime" value="<fmt:formatDate value="${officeRoom.endTime}" pattern="yyyy-MM-dd"/>" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false,minDate:'#F{$dp.$D(\'startTime\')}',maxDate:'2120-10-01',maxDate:'#F{$dp.$D(\'endTime\')||\'2120-10-01\'}'});"/>
					</div>
					<div style="float:left;">
						<input style="margin:0 15px;" id="btnSubmit" class="btn btn-primary" type="submit" value="查询" onclick="return page();"/>
						<input id="btnExport" class="btn btn-primary" type="button" value="导出"/> 
					</div>
				</li>
			</ul>
		</div>
	</form:form>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
			
				<th>申请时间</th>
				<th>申请人</th>
				<th>所属公司</th>
				<th>所属部门</th>
				<th>办公室名</th>
				<th>申请原因</th>
				<th>申请开始时间</th>
				<th>申请结束时间</th>
				<th>时长</th>
				<th>第一审批人</th>
				<th>审批状态</th>
				<th>审批意见</th>
				<th>第二审批人</th>
				<th>审批状态</th>
				<th>审批意见</th>
				<th>第三审批人</th>
				<th>审批状态</th>
				<th>审批意见</th>
				<th>第四审批人</th>
				<th>审批状态</th>
				<th>审批意见</th>
				<th>抄送人</th>
				<th>最终审核状态</th>
				<!-- <shiro:hasPermission name="oa:oaNotifyMeeting:edit"><th>操作</th></shiro:hasPermission>-->
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="officeRoomReport">
		
			<tr>
			<!-- <td>${oaNotifyMeeting.id}</td> -->
			   <td><fmt:formatDate value="${officeRoomReport.applyTime}" type="both"/></td>
				<td>${officeRoomReport.applyName}</td> 
				<td>${officeRoomReport.company.name}</td> 
				<td>${officeRoomReport.officeNames}</td> 
				<td>${officeRoomReport.yuliu1}</td>
				<td>
				<c:choose>
		        <c:when test="${officeRoomReport.reason.length()>4}">
		       ${officeRoomReport.reason.substring(0,4)}...
		        </c:when>
		        <c:otherwise> 
		        ${officeRoomReport.reason}
		        </c:otherwise>
		       </c:choose>
				</td>
				<td><fmt:formatDate value="${officeRoomReport.startTime}" type="both" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td><fmt:formatDate value="${officeRoomReport.endTime}" type="both" pattern="yyyy-MM-dd HH:mm:ss"/></td> 
				
		       <td>${officeRoomReport.hours}</td>
		        <td>
		        <c:choose>
		        <c:when test="${officeRoomReport.auditFirId.length()>4}">
		       ${officeRoomReport.auditFirId.substring(0,4)}...
		        </c:when>
		        <c:otherwise> 
		        ${officeRoomReport.auditFirId}
		        </c:otherwise>
		       </c:choose>
		        </td>
			    <td>
			    ${officeRoomReport.auditFstate}
			    </td>
			    <td>${officeRoomReport.auditFirInfo}</td>
			    <td>
			    <c:choose>
		        <c:when test="${officeRoomReport.auditSecId.length()>4}">
		       ${officeRoomReport.auditSecId.substring(0,4)}...
		        </c:when>
		        <c:otherwise> 
		        ${officeRoomReport.auditSecId}
		        </c:otherwise>
		       </c:choose>
			    </td>
			    <td>
			    ${officeRoomReport.auditSstate}
			    </td>
			    <td>${officeRoomReport.auditSecInfo}</td>
			    <td>
			    <c:choose>
		        <c:when test="${officeRoomReport.auditThrId.length()>4}">
		       ${officeRoomReport.auditThrId.substring(0,4)}...
		        </c:when>
		        <c:otherwise> 
		        ${officeRoomReport.auditThrId}
		        </c:otherwise>
		       </c:choose>
			    </td>
			    <td>
			    ${officeRoomReport.auditTstate}
			    </td>
			    <td>${officeRoomReport.auditThrInfo}</td>
			    <td>
			    <c:choose>
		        <c:when test="${officeRoomReport.auditFouId.length()>4}">
		       ${officeRoomReport.auditFouId.substring(0,4)}...
		        </c:when>
		        <c:otherwise> 
		        ${officeRoomReport.auditFouId}
		        </c:otherwise>
		       </c:choose>
			    </td>
			    <td>
			    ${officeRoomReport.auditFouState}
			    </td>
			    <td>${officeRoomReport.auditFouInfo}</td>
		       <td>
		       <c:choose>
		        <c:when test="${officeRoomReport.copytoId.length()>4}">
		       ${officeRoomReport.copytoId.substring(0,4)}...
		        </c:when>
		        <c:otherwise> 
		        ${officeRoomReport.copytoId}
		        </c:otherwise>
		       </c:choose>
		       </td>
		       <td>${officeRoomReport.endAuditState}</td>
				
				
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>
