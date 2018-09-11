<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head lang="en">
  <meta charset="UTF-8">
  <title>修改审批人</title>
  <link rel="stylesheet" href="${ctxStatic}/approvalProcess/css/global.css"/>
  <link href="${ctxStatic}/jquery-validation/1.11.0/jquery.validate.min.css" type="text/css" rel="stylesheet">
  <script src="${ctxStatic}/jquery/jquery-1.8.3.min.js" type="text/javascript"></script>
  <script src="${ctxStatic}/jquery-validation/1.11.0/jquery.validate.min.js" type="text/javascript"></script>
  <script src="${ctxStatic}/approvalProcess/js/laydate/laydate.js"></script>
  
  
  <style type="text/css">
  	body, html{
  		background-color:transparent;
  	}
  	body{
  		min-width:0;
  	}
  	.c-inputStyle{
  		width:70%;
  		height: 34px;
	    padding: 6px 12px;
	    line-height: 1.42857143;
	    border: 1px solid #ccc;
	    border-radius: 4px;
	    font-size: 14px;
	    background-color: #fff;
	    color: #555;
	    outline: 0;
	    -webkit-box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.075);
	    box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.075);
	    -webkit-transition: border-color ease-in-out 0.15s, -webkit-box-shadow ease-in-out 0.15s;
	    -o-transition: border-color ease-in-out 0.15s, box-shadow ease-in-out 0.15s;
	    transition: border-color ease-in-out 0.15s, box-shadow ease-in-out 0.15s;
  	}
  </style>
</head>
<body>
  <div class="popup">
    <form class="j-validateForm5" id="modifyDocRule" action="${ctx}/oa/approvalRule/modifyApprovalRule" method="post">
      <input type="hidden" id="ruleType" name="type" value="${type}">
      <input type="hidden" name="approvalRole" value="${rule.approvalRole}">
      <div class="p__content">
        <header class="p__header">修改审批人</header>
        <div class="form-group" id="selectRoom">
          <div class="form-list">
          	<label class="form--title control-label">设置&nbsp;
          		<c:if test="${rule.approvalRole=='1'}">审稿人：</c:if>
          		<c:if test="${rule.approvalRole=='2'}">拟稿人：</c:if>
          		<c:if test="${rule.approvalRole=='3'}">会签人：</c:if>
          		<c:if test="${rule.approvalRole=='4'}">办公室审批人：</c:if>
          		<c:if test="${rule.approvalRole=='5'}">签发人：</c:if>
          		<c:if test="${rule.approvalRole=='6'}">校对人：</c:if>
          		<c:if test="${rule.approvalRole=='7'}">用印人：</c:if>
          	</label>
			<div class="form--input">
                <sys:treeselect id="oaNotifyRecord" name="oaNotifyRecordIds" value="${rule.approvalPerson}" labelName="oaNotifyRecordNames" labelValue="${oaNotify.oaNotifyRecordNames}"
					title="用户" url="/sys/office/treeData?type=3" cssClass="input-xxlarge c-inputStyle" notAllowSelectParent="true" checked="true"/>
				<span class="help-inline"> </span>
				<%-- <input id="approverId" type="hidden" name="id" value="${id}">
          		<input id="approverName" type="hidden" name="name" value="${name}"> --%>
			</div>
          </div>
        </div>
      </div>
      <div class="p__btn">
        <button type="button" onclick="leaveSub()">提交</button>
        <button type="button" class="j-cancel">取消</button>
      </div>
    </form>
   
  </div>
	<script src="${ctxStatic}/approvalProcess/js/layer/layer.js"></script>
	<script type="text/javascript">
		function leaveSub(){
			var type = $("#ruleType2").val();
			//console.log(type);
			$.ajax({
                type: "post",  
                url:"${ctx}/oa/oaDocApproval/modifyRule",  
                data:$("#modifyDocRule").serialize(),// 序列化表单值  
                dataType:"json",
                async: false,  
                error: function(request) {
                    alert("Connection error");  
                }, 
                success: function(data) { 
                	if(data=="success"){
	               		alert("修改成功");
                	}else{
                		alert("修改失败");
                	}
                	var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
					parent.layer.close(index); //再执行关闭 
                    window.location.href="${ctx}/oa/oaDocApproval/showApprovalRule"
                }  
            });  
		}
		
		$(function(){
			/* 将审批人放入对应input内 */
			var approverId = $.trim($("#approverId").val());
			var approverName = $.trim($("#approverName").val());
	  		if(approverId.charAt(approverId.length-1) == ","){
	  			approverId = approverId.substr(0,approverId.length-1);
	  		}
	  		if(approverName.charAt(approverName.length-1) == ","){
	  			approverName = approverName.substr(0,approverName.length-1);
	  		}
			$("#oaNotifyRecordId").val(approverId);
			$("#oaNotifyRecordName").val(approverName);
			/* 点击取消关闭窗口 */
			$(".j-cancel").on("click",function(){
				var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
				parent.layer.close(index); //再执行关闭 
			});
		});
	</script>
	
  <!-- <script>
    laydate.render({
      elem: '.layDate_apply',
      type: 'datetime',//时间类型
      range: '~',//分隔符号
      format:'yyyy-MM-dd HH:mm:ss',
      trigger: 'focus',//触发事件
      btns: ['clear', 'now', 'confirm'],//底部按钮
      theme: 'grid'//格子主题
    });
  </script> -->
</body>
</html>