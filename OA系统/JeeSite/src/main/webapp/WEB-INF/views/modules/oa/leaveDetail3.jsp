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
			<input type="button" onclick="handleTaskAll('y','${taskIds}')"
				value="同意" /> <input type="button"
				onclick="handleTaskAll('n','${taskIds}')" value="驳回" />
			<%-- </c:if> --%>
		</div>
	</form>
	<script src="${ctxStatic}/jquery/jquery-2.1.1.min.js"></script>
	<script src="${ctxStatic}/viewer/viewer.js"></script>
	<script src="${ctxStatic}/viewer/main.js"></script>
	<script type="text/javascript">
		function handleTaskAll(flag, taskIds) {
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
				url : "${ctx}/act/task/handleTaskAll",
				type : "post",
				data : {
					"flag" : flag,
					"taskIds" : taskIds,
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
