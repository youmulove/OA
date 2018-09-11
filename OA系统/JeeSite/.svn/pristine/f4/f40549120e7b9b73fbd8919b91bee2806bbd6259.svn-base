<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>主子表管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
		});
		function addRow(list, idx, tpl, row){
			$(list).append(Mustache.render(tpl, {
				idx: idx, delBtn: true, row: row
			}));
			$(list+idx).find("select").each(function(){
				$(this).val($(this).attr("data-value"));
			});
			$(list+idx).find("input[type='checkbox'], input[type='radio']").each(function(){
				var ss = $(this).attr("data-value").split(',');
				for (var i=0; i<ss.length; i++){
					if($(this).val() == ss[i]){
						$(this).attr("checked","checked");
					}
				}
			});
		}
		function delRow(obj, prefix){
			var id = $(prefix+"_id");
			var delFlag = $(prefix+"_delFlag");
			if (id.val() == ""){
				$(obj).parent().parent().remove();
			}else if(delFlag.val() == "0"){
				delFlag.val("1");
				$(obj).html("&divide;").attr("title", "撤销删除");
				$(obj).parent().parent().addClass("error");
			}else if(delFlag.val() == "1"){
				delFlag.val("0");
				$(obj).html("&times;").attr("title", "删除");
				$(obj).parent().parent().removeClass("error");
			}
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/oa/oaNotifyMeeting/">预约会议列表</a></li>
		<li class="active"><a href="${ctx}/oa/oaNotifyMeeting/form?id=${oaNotifyMeeting.id}">预约会议<shiro:hasPermission name="oa:oaNotifyMeeting:edit">${oaNotifyMeeting.status eq '1' ? '查看' :not empty oaNotifyMeeting.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="oa:oaNotifyMeeting:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="oaNotifyMeeting" action="${ctx}/oa/oaNotifyMeeting/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">标题：</label>
			<div class="controls">
				<form:input path="title" htmlEscape="false" maxlength="200" class="input-xlarge  required"/>
									<span class="help-inline"><font color="red">*</font> </span>
				
				
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">内容：</label>
			<div class="controls">
				<form:textarea path="content" htmlEscape="false" rows="4" maxlength="2000" class="input-xxlarge required"/>
								<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">开始时间：</label>
			<div class="controls">
				<input id="startTime" name="startTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate required "
					value="<fmt:formatDate value="${oaNotifyMeeting.startTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false,maxDate:'#F{$dp.$D(\'endTime\')||\'2120-10-01\'}'});"/>
								<span class="help-inline"><font color="red">*</font> </span>
			
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">结束时间：</label>
			<div class="controls">
				<input id="endTime" name="endTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate required "
					value="<fmt:formatDate value="${oaNotifyMeeting.endTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false,minDate:'#F{$dp.$D(\'startTime\')}',maxDate:'2120-10-01'});"/>
								<span class="help-inline"><font color="red">*</font> </span>
			
			</div>
		</div>
		<c:if test="${oaNotifyMeeting.status ne '1'}">
			<div class="control-group">
				<label class="control-label">附件：</label>
				<div class="controls">
					<form:hidden id="files" path="files" htmlEscape="false" maxlength="255" class="input-xlarge"/>
					<sys:ckfinder input="files" type="files" uploadPath="/oa/notify" selectMultiple="true"/>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">状态：</label>
				<div class="controls">
					<form:radiobuttons path="status" items="${fns:getDictList('oa_notify_status')}" itemLabel="label" itemValue="value" htmlEscape="false" class="required"/>
					<span class="help-inline"><font color="red">*</font> 发布后不能进行操作。</span>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">接受人：</label>
				<div class="controls">
	                <sys:treeselect id="oaNotifyMeetingAccept" name="oaNotifyMeetingAcceptIds" value="${oaNotifyMeeting.oaNotifyMeetingAcceptIds}" labelName="oaNotifyMeetingAcceptNames" labelValue="${oaNotifyMeeting.oaNotifyMeetingAcceptNames}"
						title="用户" url="/sys/office/treeData?type=3" cssClass="input-medium required" notAllowSelectParent="true" checked="true"/>
					<span class="help-inline"><font color="red">*</font> </span>
				</div>
			</div>
		</c:if>
		<c:if test="${oaNotifyMeeting.status eq '1'}">
		<div class="control-group">
				<label class="control-label">附件：</label>
				<div class="controls">
					<form:hidden id="files" path="files" htmlEscape="false" maxlength="255" class="input-xlarge"/>
					<sys:ckfinder input="files" type="files" uploadPath="/oa/notify" selectMultiple="true" readonly="true" />
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">接受人：</label>
				<div class="controls">
					<table id="contentTable" class="table table-striped table-bordered table-condensed">
						<thead>
							<tr>
								<th class="hide"></th>
								<th>接收人</th>
								<th>接收部门</th>
								<th>阅读状态</th>
								<th>阅读时间</th>
								<shiro:hasPermission name="oa:oaNotifyMeeting:edit"><th width="10">&nbsp;</th></shiro:hasPermission>
							</tr>
						</thead>
						<tbody id="oaNotifyMeettingAcceptList">
						 <c:forEach items="${oaNotifyMeeting.oaNotifyMeettingAcceptList}" var="oaNotifyMeettingAccept">
							<tr>
								<td>
									${oaNotifyMeettingAccept.user.name}
								</td>
								<td>
									${oaNotifyMeettingAccept.officeNames}
								</td>
								<td>
									${fns:getDictLabel(oaNotifyMeettingAccept.readFlag, 'oa_notify_meeting_read', '')}
								</td>	
								<td>
									<fmt:formatDate value="${oaNotifyMeettingAccept.readDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
								</td>
							</tr>
							</c:forEach>
						</tbody>
						
					</table>
					已查阅：${oaNotifyMeeting.readNum} &nbsp; 未查阅：${oaNotifyMeeting.unReadNum} &nbsp; 总共：${oaNotifyMeeting.readNum + oaNotifyMeeting.unReadNum}
				</div>
			</div>
			</c:if>
		<div class="form-actions">
			<c:if test="${oaNotifyMeeting.status ne '1'}">
				<shiro:hasPermission name="oa:oaNotifyMeeting:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			</c:if>
			 <a class="btn btn-primary"href="${ctx}/oa/oaNotifyMeeting/self">返回</a> 
		</div>
	</form:form>
</body>
</html>