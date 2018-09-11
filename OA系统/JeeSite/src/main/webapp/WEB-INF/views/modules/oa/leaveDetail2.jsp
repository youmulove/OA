<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>待办任务页面中 请假一览</title>
<meta name="decorator" content="default" />
<link rel="stylesheet" href="${ctxStatic}/viewer/viewer.css">
<style>
.c-table {
	width: 500px;
	margin: 15px auto;
	border: 1px solid #bbb;
	border-bottom: none;
}

.c-table tr {
	border-bottom: 1px solid #bbb;
}

.c-table tr>td:nth-child(1) {
	width: 150px;
	padding-right: 8px;
	text-align: right;
	color: #555;
	font-weight: 600;
}

.c-btn {
	width: 500px;
	margin: 0 auto;
	text-align: center;
}

.c-btn input {
	padding: 4px 12px;
	margin: 0 10px;
	background-color: #2fa4e7;
	border: 1px solid #46aeea;
	border-radius: 4px;
	font-size: 16px;
	color: #fff;
}
</style>
</head>
<body>
	<form action="">
		<table class="c-table">
			<tbody>
				<c:if test="${type=='leave'}">
					<tr>
						<td>任务名称：</td>
						<td>${title}</td>
					</tr>
					<tr>
						<td>申请人：</td>
						<td>${leave.applyName}</td>
					</tr>
					<tr>
						<td>请假类型：</td>
						<td>${leave.leaveType}</td>
					</tr>
					<tr>
						<td>请假理由：</td>
						<td>${leave.reason}</td>
					</tr>
					<tr>
						<td>请假时间：</td>
						<td><fmt:formatDate value="${leave.startTime}" type="both" />
							~<fmt:formatDate value="${leave.endTime}" type="both" />
						</td>
					</tr>
					<tr>
						<td>请假天数：</td>
						<td>${leave.hours}天</td>
					</tr>
					<c:if test="${not empty leave.auditFirId}">
						<tr>
							<td>一级审批人：</td>
							<td>${oneLevel}</td>
						</tr>
						<tr>
							<td>一级审批人意见：</td>
							<td>${leave.auditFirInfo}</td>
						</tr>
					</c:if>
					<c:if test="${not empty leave.auditSecId}">
						<tr>
							<td>二级审批人：</td>
							<td>${twoLevel}</td>
						</tr>
						<tr>
							<td>二级审批人意见：</td>
							<td>${leave.auditSecInfo}</td>
						</tr>
					</c:if>
					<c:if test="${not empty leave.auditThrId}">
						<tr>
							<td>三级审批人：</td>
							<td>${threeLevel}</td>
						</tr>
						<tr>
							<td>三级审批人意见：</td>
							<td>${leave.auditThrInfo}</td>
						</tr>
					</c:if>
					<c:if test="${not empty leave.auditFouId}">
						<tr>
							<td>四级审批人：</td>
							<td>${fourLevel}</td>
						</tr>
						<tr>
							<td>四级审批人意见：</td>
							<td>${leave.auditFouInfo}</td>
						</tr>
					</c:if>
				</c:if>
				<c:if test="${type=='overTime'}">
					<tr>
						<td>任务名称：</td>
						<td>${title}</td>
					</tr>
					<tr>
						<td>申请人：</td>
						<td>${overTime.applyName}</td>
					</tr>
					<tr>
						<td>加班时间：</td>
						<td><fmt:formatDate value="${overTime.startTime}" type="both" />
							~<fmt:formatDate value="${overTime.endTime}" type="both" />
						</td>
					</tr>
					<tr>
						<td>加班时长：</td>
						<td>${overTime.hours}小时</td>
					</tr>
					<tr>
						<td>加班理由：</td>
						<td>${overTime.reason}</td>
					</tr>
					<tr>
						<td>是否是节假日：</td>
						<td><c:if test="${overTime.isHoliday =='y'}">是</c:if> <c:if
								test="${overTime.isHoliday =='n'}">否</c:if></td>
					</tr>
					<c:if test="${not empty overTime.auditFirId}">
						<tr>
							<td>一级审批人：</td>
							<td>${oneLevel}</td>
						</tr>
						<tr>
							<td>一级审批人意见：</td>
							<td>${overTime.auditFirInfo}</td>
						</tr>
					</c:if>
					<c:if test="${not empty overTime.auditSecId}">
						<tr>
							<td>二级审批人：</td>
							<td>${twoLevel}</td>
						</tr>
						<tr>
							<td>二级审批人意见：</td>
							<td>${overTime.auditSecInfo}</td>
						</tr>
					</c:if>
					<c:if test="${not empty overTime.auditThrId}">
						<tr>
							<td>三级审批人：</td>
							<td>${threeLevel}</td>
						</tr>
						<tr>
							<td>三级审批人意见：</td>
							<td>${overTime.auditThrInfo}</td>
						</tr>
					</c:if>
					<c:if test="${not empty overTime.auditFouId}">
						<tr>
							<td>四级审批人：</td>
							<td>${fourLevel}</td>
						</tr>
						<tr>
							<td>四级审批人意见：</td>
							<td>${overTime.auditFouInfo}</td>
						</tr>
					</c:if>
				</c:if>
				<c:if test="${type=='officeRoom'}">
					<tr>
						<td>任务名称：</td>
						<td>${title}</td>
					</tr>
					<tr>
						<td>申请人：</td>
						<td>${officeRoom.applyName}</td>
					</tr>
					<tr>
						<td>所在部门：</td>
						<td>${officeRoom.department}</td>
					</tr>
					<tr>
						<td>使用时间：</td>
						<td><fmt:formatDate value="${officeRoom.startTime}"
								type="both" /> ~<fmt:formatDate value="${officeRoom.endTime}"
								type="both" />
						</td>
					</tr>
					<tr>
						<td>使用时长：</td>
						<td>${officeRoom.hours}小时</td>
					</tr>
					<c:if test="${not empty officeRoom.auditFirId}">
						<tr>
							<td>一级审批人：</td>
							<td>${oneLevel}</td>
						</tr>
						<tr>
							<td>一级审批人意见：</td>
							<td>${officeRoom.auditFirInfo}</td>
						</tr>
					</c:if>
					<c:if test="${not empty officeRoom.auditSecId}">
						<tr>
							<td>二级审批人：</td>
							<td>${twoLevel}</td>
						</tr>
						<tr>
							<td>二级审批人意见：</td>
							<td>${officeRoom.auditSecInfo}</td>
						</tr>
					</c:if>
					<c:if test="${not empty officeRoom.auditThrId}">
						<tr>
							<td>三级审批人：</td>
							<td>${threeLevel}</td>
						</tr>
						<tr>
							<td>三级审批人意见：</td>
							<td>${officeRoom.auditThrInfo}</td>
						</tr>
					</c:if>
					<c:if test="${not empty officeRoom.auditFouId}">
						<tr>
							<td>四级审批人：</td>
							<td>${fourLevel}</td>
						</tr>
						<tr>
							<td>四级审批人意见：</td>
							<td>${officeRoom.auditFouInfo}</td>
						</tr>
					</c:if>
				</c:if>
				<c:if test="${type=='projector'}">
					<tr>
						<td>任务名称：</td>
						<td>${title}</td>
					</tr>
					<tr>
						<td>申请人：</td>
						<td>${projector.applyUserid}</td>
					</tr>
					<tr>
						<td>使用时间：</td>
						<td><fmt:formatDate value="${projector.startTime}"
								type="both" /> ~<fmt:formatDate value="${projector.endTime}"
								type="both" />
						</td>
					</tr>
					<tr>
						<td>使用时长：</td>
						<td>${projector.hours}小时</td>
					</tr>
					<tr>
						<td>申请理由：</td>
						<td>${projector.reason}</td>
					</tr>
					<tr>
						<td>是否需要转接头：</td>
						<td><c:if test="${projector.adapter =='y'}">是</c:if> <c:if
								test="${projector.adapter =='n'}">否</c:if></td>
					</tr>
					<c:if test="${not empty projector.auditFirId}">
						<tr>
							<td>一级审批人：</td>
							<td>${oneLevel}</td>
						</tr>
						<tr>
							<td>一级审批人意见：</td>
							<td>${projector.auditFirInfo}</td>
						</tr>
					</c:if>
					<c:if test="${not empty projector.auditSecId}">
						<tr>
							<td>二级审批人：</td>
							<td>${twoLevel}</td>
						</tr>
						<tr>
							<td>二级审批人意见：</td>
							<td>${projector.auditSecInfo}</td>
						</tr>
					</c:if>
					<c:if test="${not empty projector.auditThrId}">
						<tr>
							<td>三级审批人：</td>
							<td>${threeLevel}</td>
						</tr>
						<tr>
							<td>三级审批人意见：</td>
							<td>${projector.auditThrInfo}</td>
						</tr>
					</c:if>
					<c:if test="${not empty projector.auditFouId}">
						<tr>
							<td>四级审批人：</td>
							<td>${fourLevel}</td>
						</tr>
						<tr>
							<td>四级审批人意见：</td>
							<td>${projector.auditFouInfo}</td>
						</tr>
					</c:if>
				</c:if>
				<c:if test="${type=='currency'}">
					<tr>
						<td>任务名称：</td>
						<td>${title}</td>
					</tr>
					<tr>
						<td>申请人：</td>
						<td>${currency.applyName}</td>
					</tr>
					<tr>
						<td>起止时间：</td>
						<td><fmt:formatDate value="${currency.startTime}" type="both" />
							~<fmt:formatDate value="${currency.endTime}" type="both" />
						</td>
					</tr>
					<tr>
						<td>用章事宜：</td>
						<td>${currency.applyItem}</td>
					</tr>
					<tr>
						<td>用章类型：</td>
						<td>${currency.selectChapters}</td>
					</tr>
					<tr>
						<td>内容：</td>
						<td>${currency.reason}</td>
					</tr>
					<tr>
						<td>用章份数：</td>
						<td>${currency.chaptersNumber}</td>
					</tr>
					<tr>
						<td>盖章文件：</td>
						<td class="docs-pictures"><c:if
								test="${(currency.stampFiles==null) || (fn:length(fn:split(currency.stampFiles,\"|\")))==1}">
								<img onclick="bigImages()"
									style="max-width:100px;max-height:80px;border:0;padding:3px;"
									data-original="${currency.stampFiles}"
									src="${currency.stampFiles}" />
							</c:if> <c:if
								test="${(fn:length(fn:split(currency.stampFiles, \"|\")))>1}">

								<c:forEach items="${fn:split(currency.stampFiles, \"|\")}"
									varStatus="i" var="item">
									<img onclick="bigImages()"
										style="max-width:100px;max-height:80px;border:0;padding:3px;"
										data-original="${item}" src="${item}" />
								</c:forEach>
							</c:if></td>
					</tr>
					<c:if test="${not empty currency.auditFirId}">
						<tr>
							<td>一级审批人：</td>
							<td>${oneLevel}</td>
						</tr>
						<tr>
							<td>一级审批人意见：</td>
							<td>${currency.auditFirInfo}</td>
						</tr>
					</c:if>
					<c:if test="${not empty currency.auditSecId}">
						<tr>
							<td>二级审批人：</td>
							<td>${twoLevel}</td>
						</tr>
						<tr>
							<td>二级审批人意见：</td>
							<td>${currency.auditSecInfo}</td>
						</tr>
					</c:if>
					<c:if test="${not empty currency.auditThrId}">
						<tr>
							<td>三级审批人：</td>
							<td>${threeLevel}</td>
						</tr>
						<tr>
							<td>三级审批人意见：</td>
							<td>${currency.auditThrInfo}</td>
						</tr>
					</c:if>
					<c:if test="${not empty currency.auditFouId}">
						<tr>
							<td>四级审批人：</td>
							<td>${fourLevel}</td>
						</tr>
						<tr>
							<td>四级审批人意见：</td>
							<td>${currency.auditFouInfo}</td>
						</tr>
					</c:if>
				</c:if>
				<c:if test="${type=='doc'}">
					<tr>
						<td>文件下载：</td>
						<td><a href="${filePath}">${fileName}</a>
						</td>
					</tr>
					<tr>
						<td>任务名称：</td>
						<td>${title}</td>
					</tr>
					<tr>
						<td>拟稿人：</td>
						<td>${doc.ngName}</td>
					</tr>
					<tr>
						<td>审稿人：</td>
						<td>${doc.sgName}</td>
					</tr>
					<tr>
						<td>审稿人意见：</td>
						<td>${doc.sgOption}</td>
					</tr>
					<tr>
						<td>核稿人：</td>
						<td>${doc.hgName}</td>
					</tr>
					<tr>
						<td>核稿人意见：</td>
						<td>${doc.hgOption}</td>
					</tr>
					<tr>
						<td>会签人：</td>
						<td>${doc.signName}</td>
					</tr>
					<tr>
						<td>会签人意见：</td>
						<td>${doc.signOption}</td>
					</tr>
					<tr>
						<td>办公室审批人：</td>
						<td>${doc.officeApprovalName}</td>
					</tr>
					<tr>
						<td>办公室审批人意见：</td>
						<td>${doc.officeOption}</td>
					</tr>
					<tr>
						<td>签发人：</td>
						<td>${doc.qfName}</td>
					</tr>
					<tr>
						<td>签发人意见：</td>
						<td>${doc.qfOption}</td>
					</tr>
					<tr>
						<td>校对人：</td>
						<td>${doc.checkName}</td>
					</tr>
					<tr>
						<td>校对人意见：</td>
						<td>${doc.checkOption}</td>
					</tr>
					<tr>
						<td>用印人：</td>
						<td>${doc.useSealName}</td>
					</tr>
					<tr>
						<td>用印人意见：</td>
						<td>${doc.useSealOption}</td>
					</tr>

				</c:if>

				<c:if test="${detailFlag=='todo'}">
					<tr>
						<td>审批意见：</td>
						<td><textarea style="margin:0;" rows="2" cols="40"
								name="approvalResult" id="approvalResult"></textarea></td>
					</tr>
				</c:if>
			</tbody>
		</table>
		<div class="c-btn">
			<%-- <c:if test="${applyUserId != currentUser }"> --%>
			<input type="button" onclick="handleTask('y','${task.id}')"
				value="同意" /> <input type="button"
				onclick="handleTask('n','${task.id}')" value="驳回" />
			<%-- </c:if> --%>
		</div>
	</form>
	<script src="${ctxStatic}/jquery/jquery-2.1.1.min.js"></script>
	<script src="${ctxStatic}/viewer/viewer.js"></script>
	<script src="${ctxStatic}/viewer/main.js"></script>
	<script type="text/javascript">
		function handleTask(flag, taskId) {
			var approvalResult = $("#approvalResult").val();
			if (approvalResult == null || approvalResult == "") {
				if (flag == 'y') {
					approvalResult = "同意";
				} else {
					approvalResult = "拒绝";
				}

			}
			console.log(approvalResult);
			$.ajax({
				url : "${ctx}/act/task/handleTask",
				type : "post",
				data : {
					"flag" : flag,
					"taskId" : taskId,
					"approvalResult" : approvalResult
				},
				dataType : "json",
				success : function(ret) {
					console.log(ret + "-------");
					alert("办理成功");
					window.parent.location.href = "${ctx}/act/task/todo";
					var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
					parent.layer.close(index); //再执行关闭 
				}
			});
		}
	</script>
</body>
</html>
