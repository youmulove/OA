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
    <c:if test="${type=='leave'}">
    <form class="j-validateForm5" id="modifyLeaveRule" action="${ctx}/oa/approvalRule/modifyApprovalRule" method="post">
      <input type="hidden" id="ruleType" name="type" value="${type}">
      <input type="hidden" name="approvalRole" value="${approvalRole}">
      <div class="p__content">
        <header class="p__header">修改审批人</header>
        <div class="form-group" id="selectRoom">
          <div class="form-list">
          	<label class="form--title control-label">请假天数范围：</label>
			<div class="form--input">
                <input class="c-dataScope error required" type="text" name="start" value="${rule.approvalStart}" style="width: 50px" />&nbsp;天&nbsp;~~&nbsp;
                <input class="c-dataScope required" type="text" name="end" value="${rule.approvalEnd}" style="width: 50px" />&nbsp;天
			</div>
          </div>
          <div class="form-list">
          	<label class="form--title control-label">设置第&nbsp;${approvalRole}&nbsp;审批人：</label>
			<div class="form--input">
			<c:if test="${empty rule.approvalRank}">
				  <sys:treeselect id="oaNotifyRecord" name="oaNotifyRecordIds" value="${oaNotify.oaNotifyRecordIds}" labelName="oaNotifyRecordNames" labelValue="${oaNotify.oaNotifyRecordNames}"
					title="用户" url="/sys/office/treeData?type=3" cssClass="input-xxlarge c-inputStyle" notAllowSelectParent="true" checked="true"/>
				<input id="approverId" type="hidden" name="id" value="${id}">
          		<input id="approverName" type="hidden" name="name" value="${name}">
			</c:if>
            <c:if test="${not empty rule.approvalRank}">
				  <sys:treeselect id="oaNotifyRecord" name="approvalRankIds" value="${oaNotify.oaNotifyRecordIds}" labelName="oaNotifyRecordNames" labelValue="${oaNotify.oaNotifyRecordNames}"
					title="职级" url="/sys/sysRank/rankData?officeId=${fns:getUser().company.id}" cssClass="input-xxlarge c-inputStyle" notAllowSelectParent="true"/>
				<input id="approverId" type="hidden" name="sid" value="${sid}">
          		<input id="approverName" type="hidden" name="sname" value="${sname}">
			</c:if>	
				<span class="help-inline"> </span>
			</div>
          </div>
        </div>
      </div>
      <div class="p__btn">
        <button type="button" onclick="leaveSub()">提交</button>
        <button type="button" class="j-cancel">取消</button>
      </div>
    </form>
    </c:if>
    <c:if test="${type=='overTime'}">
    <form class="j-validateForm5" id="modifyLeaveRule" action="${ctx}/oa/approvalRule/modifyApprovalRule" method="post">
      <input type="hidden" name="type" id="ruleType2" value="${type}">
      <input type="hidden" name="approvalRole" value="${approvalRole}">
      <input type="hidden" name="isOver" value="${isOver}">
      <div class="p__content">
        <header class="p__header">修改审批人</header>
        <div class="form-group" id="selectRoom">
          <div class="form-list">
          	<label class="form--title control-label">设置第&nbsp;${approvalRole}&nbsp;审批人：</label>
			<div class="form--input">
               <c:if test="${empty rule.approvalRank}">
				  <sys:treeselect id="oaNotifyRecord" name="oaNotifyRecordIds" value="${oaNotify.oaNotifyRecordIds}" labelName="oaNotifyRecordNames" labelValue="${oaNotify.oaNotifyRecordNames}"
					title="用户" url="/sys/office/treeData?type=3" cssClass="input-xxlarge c-inputStyle" notAllowSelectParent="true" checked="true"/>
				<input id="approverId" type="hidden" name="id" value="${id}">
          		<input id="approverName" type="hidden" name="name" value="${name}">
			</c:if>
            <c:if test="${not empty rule.approvalRank}">
				  <sys:treeselect id="oaNotifyRecord" name="approvalRankIds" value="${oaNotify.oaNotifyRecordIds}" labelName="oaNotifyRecordNames" labelValue="${oaNotify.oaNotifyRecordNames}"
					title="职级" url="/sys/sysRank/rankData?officeId=${fns:getUser().company.id}" cssClass="input-xxlarge c-inputStyle" notAllowSelectParent="true"/>
				<input id="approverId" type="hidden" name="sid" value="${sid}">
          		<input id="approverName" type="hidden" name="sname" value="${sname}">
			</c:if>	
			</div>
          </div>
        </div>
      </div>
      <div class="p__btn">
        <button type="button" onclick="overTimeSub()">提交</button>
        <button type="button" class="j-cancel">取消</button>
      </div>
    </form>
    </c:if>
    <c:if test="${type=='officeRoom'}">
    <form class="j-validateForm5" id="modifyLeaveRule" action="${ctx}/oa/approvalRule/modifyApprovalRule" method="post">
      <input type="hidden" name="type" id="ruleType3" value="${type}">
      <input type="hidden" name="approvalRole" value="${approvalRole}">
      <div class="p__content">
        <header class="p__header">修改审批人</header>
        <div class="form-group" id="selectRoom">
          <div class="form-list">
          	<label class="form--title control-label">设置第&nbsp;${approvalRole}&nbsp;审批人：</label>
			<div class="form--input">
               <c:if test="${empty rule.approvalRank}">
				  <sys:treeselect id="oaNotifyRecord" name="oaNotifyRecordIds" value="${oaNotify.oaNotifyRecordIds}" labelName="oaNotifyRecordNames" labelValue="${oaNotify.oaNotifyRecordNames}"
					title="用户" url="/sys/office/treeData?type=3" cssClass="input-xxlarge c-inputStyle" notAllowSelectParent="true" checked="true"/>
				<input id="approverId" type="hidden" name="id" value="${id}">
          		<input id="approverName" type="hidden" name="name" value="${name}">
			</c:if>
            <c:if test="${not empty rule.approvalRank}">
				  <sys:treeselect id="oaNotifyRecord" name="approvalRankIds" value="${oaNotify.oaNotifyRecordIds}" labelName="oaNotifyRecordNames" labelValue="${oaNotify.oaNotifyRecordNames}"
					title="职级" url="/sys/sysRank/rankData?officeId=${fns:getUser().company.id}" cssClass="input-xxlarge c-inputStyle" notAllowSelectParent="true"/>
				<input id="approverId" type="hidden" name="sid" value="${sid}">
          		<input id="approverName" type="hidden" name="sname" value="${sname}">
			</c:if>	
			</div>
          </div>
        </div>
      </div>
      <div class="p__btn">
        <button type="button" onclick="officeRoomSub()">提交</button>
        <button type="button" class="j-cancel">取消</button>
      </div>
    </form>
    </c:if>
    <c:if test="${type=='projector'}">
    <form class="j-validateForm5" id="modifyLeaveRule" action="${ctx}/oa/approvalRule/modifyApprovalRule" method="post">
      <input type="hidden" name="type" id="ruleType4" value="${type}">
      <input type="hidden" name="approvalRole" value="${approvalRole}">
      <div class="p__content">
        <header class="p__header">修改审批人</header>
        <div class="form-group" id="selectRoom">
          <div class="form-list">
          	<label class="form--title control-label">设置第&nbsp;${approvalRole}&nbsp;审批人：</label>
			<div class="form--input">
               <c:if test="${empty rule.approvalRank}">
				  <sys:treeselect id="oaNotifyRecord" name="oaNotifyRecordIds" value="${oaNotify.oaNotifyRecordIds}" labelName="oaNotifyRecordNames" labelValue="${oaNotify.oaNotifyRecordNames}"
					title="用户" url="/sys/office/treeData?type=3" cssClass="input-xxlarge c-inputStyle" notAllowSelectParent="true" checked="true"/>
				<input id="approverId" type="hidden" name="id" value="${id}">
          		<input id="approverName" type="hidden" name="name" value="${name}">
			</c:if>
            <c:if test="${not empty rule.approvalRank}">
				  <sys:treeselect id="oaNotifyRecord" name="approvalRankIds" value="${oaNotify.oaNotifyRecordIds}" labelName="oaNotifyRecordNames" labelValue="${oaNotify.oaNotifyRecordNames}"
					title="职级" url="/sys/sysRank/rankData?officeId=${fns:getUser().company.id}" cssClass="input-xxlarge c-inputStyle" notAllowSelectParent="true"/>
				<input id="approverId" type="hidden" name="sid" value="${sid}">
          		<input id="approverName" type="hidden" name="sname" value="${sname}">
			</c:if>	
			</div>
          </div>
        </div>
      </div>
      <div class="p__btn">
        <button type="button" onclick="projectorSub()">提交</button>
        <button type="button" class="j-cancel">取消</button>
      </div>
    </form>
    </c:if>
    <c:if test="${type=='currency'}">
    <form class="j-validateForm5" id="modifyLeaveRule" action="${ctx}/oa/approvalRule/modifyApprovalRule" method="post">
     <input type="hidden" name="type" id="ruleType5" value="${type}">
      <input type="hidden" name="approvalRole" value="${approvalRole}">
      <div class="p__content">
        <header class="p__header">修改审批人</header>
        <div class="form-group" id="selectRoom">
          <div class="form-list">
          	<label class="form--title control-label">设置第&nbsp;${approvalRole}&nbsp;审批人：</label>
			<div class="form--input">
               <c:if test="${empty rule.approvalRank}">
				  <sys:treeselect id="oaNotifyRecord" name="oaNotifyRecordIds" value="${oaNotify.oaNotifyRecordIds}" labelName="oaNotifyRecordNames" labelValue="${oaNotify.oaNotifyRecordNames}"
					title="用户" url="/sys/office/treeData?type=3" cssClass="input-xxlarge c-inputStyle" notAllowSelectParent="true" checked="true"/>
				<input id="approverId" type="hidden" name="id" value="${id}">
          		<input id="approverName" type="hidden" name="name" value="${name}">
			</c:if>
            <c:if test="${not empty rule.approvalRank}">
				  <sys:treeselect id="oaNotifyRecord" name="approvalRankIds" value="${oaNotify.oaNotifyRecordIds}" labelName="oaNotifyRecordNames" labelValue="${oaNotify.oaNotifyRecordNames}"
					title="职级" url="/sys/sysRank/rankData?officeId=${fns:getUser().company.id}" cssClass="input-xxlarge c-inputStyle" notAllowSelectParent="true"/>
				<input id="approverId" type="hidden" name="sid" value="${sid}">
          		<input id="approverName" type="hidden" name="sname" value="${sname}">
			</c:if>	
			</div>
          </div>
        </div>
      </div>
      <div class="p__btn">
        <button type="button" onclick="currencySub()">提交</button>
        <button type="button" class="j-cancel">取消</button>
      </div>
    </form>
    </c:if>
  </div>
	<script src="${ctxStatic}/approvalProcess/js/layer/layer.js"></script>
	<script type="text/javascript">
		function leaveSub(){
			var type = $("#ruleType").val();
			//console.log(type);
			$.ajax({  
                type: "post",  
                url:"${ctx}/oa/approvalRule/modifyApprovalRule",  
                data:$("#modifyLeaveRule").serialize(),// 序列化表单值  
                dataType:"json",
                async: false,  
                error: function(request) {
                    alert("Connection error");  
                }, 
                success: function(data) { 
                	console.log(data+"------data");
               		alert("修改成功");
                    window.parent.location.href="${ctx}/oa/approvalRule/alreadyProcess?type="+type
                	var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
					parent.layer.close(index); //再执行关闭 
                }  
            });  
		}
		function overTimeSub(){
			var type = $("#ruleType2").val();
			console.log(type);
			$.ajax({  
                type: "post",  
                url:"${ctx}/oa/approvalRule/modifyApprovalRule",  
                data:$("#modifyLeaveRule").serialize(),// 序列化表单值  
                dataType:"json",
                async: false,  
                error: function(request) {
                    alert("Connection error");  
                }, 
                success: function(data) { 
                	console.log(data+"------data");
               		alert("修改成功");
                    window.parent.location.href="${ctx}/oa/approvalRule/alreadyProcess?type="+type
                	var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
					parent.layer.close(index); //再执行关闭 
                }  
            });  
		}
		function officeRoomSub(){
			var type = $("#ruleType3").val();
			console.log(type);
			$.ajax({  
                type: "post",  
                url:"${ctx}/oa/approvalRule/modifyApprovalRule",  
                data:$("#modifyLeaveRule").serialize(),// 序列化表单值  
                dataType:"json",
                async: false,  
                error: function(request) {
                    alert("Connection error");  
                }, 
                success: function(data) { 
                	console.log(data+"------data");
               		alert("修改成功");
                    window.parent.location.href="${ctx}/oa/approvalRule/alreadyProcess?type="+type
                	var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
					parent.layer.close(index); //再执行关闭 
                }  
            });  
		}
		function projectorSub(){
			var type = $("#ruleType4").val();
			console.log(type);
			$.ajax({  
                type: "post",  
                url:"${ctx}/oa/approvalRule/modifyApprovalRule",  
                data:$("#modifyLeaveRule").serialize(),// 序列化表单值  
                dataType:"json",
                async: false,  
                error: function(request) {
                    alert("Connection error");  
                }, 
                success: function(data) { 
                	console.log(data+"------data");
               		alert("修改成功");
                    window.parent.location.href="${ctx}/oa/approvalRule/alreadyProcess?type="+type
                	var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
					parent.layer.close(index); //再执行关闭 
                }  
            });  
		}
		function currencySub(){
			var type = $("#ruleType5").val();
			console.log(type);
			$.ajax({  
                type: "post",  
                url:"${ctx}/oa/approvalRule/modifyApprovalRule",  
                data:$("#modifyLeaveRule").serialize(),// 序列化表单值  
                dataType:"json",
                async: false,  
                error: function(request) {
                    alert("Connection error");  
                }, 
                success: function(data) { 
                	console.log(data+"------data");
               		alert("修改成功");
                    window.parent.location.href="/jeesite/a/oa/approvalRule/alreadyProcess?type="+type;
                	var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
					parent.layer.close(index); //再执行关闭 
                    //window.location.href="${ctx}/oa/approvalRule/alreadyProcess?type="+type
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