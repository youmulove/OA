<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head lang="en">
  <meta charset="UTF-8">
  <title>修改审批人</title>
  <link rel="stylesheet" href="${ctxStatic}/approvalProcess/css/global.css"/>
  <script src="${ctxStatic}/approvalProcess/js/jquery-3.2.1.js" type="text/javascript"></script>
  <link href="${ctxStatic}/jquery-validation/1.11.0/jquery.validate.min.css" type="text/css" rel="stylesheet">
  <script src="${ctxStatic}/jquery-validation/1.11.0/jquery.validate.min.js" type="text/javascript"></script>
  
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
  	/* 表单验证,，错误后提示文字上下重叠，只显示一个 */
  	.j-storeErrorMsg{
  		position:absolute;
  		top:8px;
  		left:170px;
  		width:100px;
  		height:22px;
  	}
  	.j-storeErrorMsg>.error{
  		position:absolute;
  		background-position:0px 3px;
  	}
  	.form--input>.input-append{
  		float:left;
  	}
  </style>
</head>
<body>
  <div class="popup">
  	<form class="j-approverForm">
		<div class="p__content">
			<div class="form-group">
				<div class="j-approver form-list">
					<div style="padding:0 30px 0 40px;">
						<input name="approverSort" value="1" type="radio" checked="checked"/>按个人选择审批人
					</div>
					<div>	
						<input name="approverSort" value="2" type="radio"/>按职级选择审批人
					</div>
				</div>
				<div class="c-approver__box form-list">
					<label class="form--title control-label" style="width:40%;">设置第${nth}审批人：</label>
					<div class="form--input" style="width:200px;">
						<sys:treeselect id="approverPeople" name="approverPeopleId" cssplaceholder="按个人选择审批人" value="${oaNotify.oaNotifyRecordIds}" labelName="approverPeopleName" labelValue="${oaNotify.oaNotifyRecordNames}"
						title="用户" url="/sys/office/treeData?type=3" cssClass="input-xxlarge required c-inputStyle" notAllowSelectParent="true" checked="true"/>
						<span class="j-storeErrorMsg"></span>
					</div>
				</div>
				<div class="c-approver__box form-list" style="display:none;">
					<label class="form--title control-label" style="width:40%;">设置第${nth}审批人：</label>
					<div class="form--input" style="width:200px;">
						<sys:treeselect id="approverPost" name="approverPostId" cssplaceholder="按职级选择审批人" value="${oaNotify.oaNotifyRecordIds}" labelName="approverPostName" labelValue="${oaNotify.oaNotifyRecordNames}"
						title="职级" url="/sys/sysRank/rankData?officeId=${fns:getUser().company.id}" cssClass="input-xxlarge required c-inputStyle" />
						<span class="j-storeErrorMsg"></span>
					</div>
				</div>
			    <c:if test="${type=='leave'}">
				    <div class="form-list">
						<label class="form--title form--star" style="width:40%;">请假天数范围：</label>
						<div class="form--input" style="width:200px;">
							<input class="c-dataScope error required" type="text" id="dayStart" name="dayStart" style="width: 50px"/>&nbsp;天&nbsp;~~&nbsp;
							<input class="c-dataScope error required" type="text" id="dayEnd" name="dayEnd" style="width: 50px"/>&nbsp;天
							<span class="j-storeErrorMsg"></span>
						</div>
					</div>	
			    </c:if>
		    </div>
	    </div>
    
		<div class="p__btn">
		  <button type="submit" class="j-save">保存</button>
		  <button type="button" class="j-cancel">取消</button>
		</div>
	</form>
  </div>
	<script src="/approvalProcess/js/layer/layer.js"></script>
	<script type="text/javascript">
		
		
		$(function(){
			/* /* 将审批人放入对应input内 
			var approverId = $.trim($("#approverId").val());
			var approverName = $.trim($("#approverName").val());
	  		if(approverId.charAt(approverId.length-1) == ","){
	  			approverId = approverId.substr(0,approverId.length-1);
	  		}
	  		if(approverName.charAt(approverName.length-1) == ","){
	  			approverName = approverName.substr(0,approverName.length-1);
	  		}
			$("#oaNotifyRecordId").val(approverId);
			$("#oaNotifyRecordName").val(approverName); */
			/* 点击保存页面数据到父页面，并关闭窗口 */
			function saveData(){
			    //console.log($(".j-approverForm").serialize());
				var formData = "{\""+$(".j-approverForm").serialize().replace(/\&/g,"\",\"").replace(/\=/g,"\":\"").replace(/(%2C)|(%2c)/g,",")+"\"}";
				/* console.log(formData); */
				formData = JSON.parse(formData);
				console.log(formData);
				//console.log(ormData.approverSelect);
				var $dataBox = $(".${type}-${nth}th",parent.document);
				/* 是按个人还是按职级 */
				$dataBox.children(".approverSort").val(formData.approverSort);
				/* 放入审批人（或职级）的id和name */
				if(formData.approverSort == "1"){
					$dataBox.children(".approverName").text($("#approverPeopleName").val()).attr("title",$("#approverPeopleName").val());
					$dataBox.children(".approverId").val(formData.approverPeopleId);
				}else if(formData.approverSort == "2"){
					$dataBox.children(".approverName").text($("#approverPostName").val()).attr("title",$("#approverPostName").val());
					$dataBox.children(".approverId").val(formData.approverPostId);
				}
				/* 放入天数范围 (用来提交的)*/
				$dataBox.children(".dayStart").val(formData.dayStart);
				$dataBox.children(".dayEnd").val(formData.dayEnd);
				/* 放入天数范围 (用来显示的)*/
				var $dayRange = $dataBox.closest(".c-approver").find(".dayRange").show();
				$dayRange.find(".dayStart").text(formData.dayStart);
				$dayRange.find(".dayEnd").text(formData.dayEnd);
				
				/* 保存完成关闭弹窗 */
				var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
				var layero = $("#layui-layer"+index,parent.document);
				layero.animate({
			      left :layero.offset().left + layero.width()
			    }, 300, function () {
			      parent.layer.close(index);
			    });
			}
			/* 点击取消关闭窗口 */
			$(".j-cancel").on("click",function(){
				var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
				var layero = $("#layui-layer"+index,parent.document);
				layero.animate({
			      left :layero.offset().left + layero.width()
			    }, 300, function () {
			      parent.layer.close(index);
			    });
				//parent.layer.close(index); //再执行关闭 
			});
			/* 选择按个人还是按职级审批 */
			$(".j-approver").on("click","input",function(){
			console.log($(this).parent().parent().parent());
				if($(this).val() == "1"){
					var approverBox = $(this).parent().parent().parent().children(".c-approver__box");
					approverBox.eq(0).show().find("input[type='text']").prop("disabled",false);
					approverBox.eq(1).hide().find("input[type='text']").val("").prop("disabled",true);
				}else if($(this).val() == "2"){
					var approverBox = $(this).parent().parent().parent().children(".c-approver__box");
					approverBox.eq(1).show().find("input[type='text']").prop("disabled",false);
					approverBox.eq(0).hide().find("input[type='text']").val("").prop("disabled",true);    		
				}
			});
			/* 点击保存验证是否为空 */
			$(".j-approverForm").validate({
				submitHandler: function(form){
					saveData();
				},
				errorPlacement:function(error, element) { //错误信息位置设置方法
					console.log(element.parent());
					error.appendTo(element.closest(".form--input").children(".j-storeErrorMsg"));
				}
			});
		});
	</script>
	
 
</body>
</html>