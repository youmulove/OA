<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head lang="en">
  <meta charset="UTF-8">
  <title>申请会议室</title>
  <link rel="stylesheet" href="${ctxStatic}/approvalProcess/css/global.css"/>
  <link href="${ctxStatic}/jquery-validation/1.11.0/jquery.validate.min.css" type="text/css" rel="stylesheet">
  <script src="${ctxStatic}/jquery/jquery-1.8.3.min.js" type="text/javascript"></script>
  <script src="${ctxStatic}/jquery-validation/1.11.0/jquery.validate.min.js" type="text/javascript"></script>
  <script src="${ctxStatic}/approvalProcess/js/laydate/laydate.js"></script>
  
  <%-- <link rel="stylesheet" href="${ctxStatic}/approvalProcess/css/global.css"/>
  <link rel="stylesheet" href="${ctxStatic}/approvalProcess/css/approvalProcess.css"/>

	<meta http-equiv="Content-Type" content="text/html;charset=utf-8"><meta name="author" content="http://jeesite.com/">
	<meta name="renderer" content="webkit"><meta http-equiv="X-UA-Compatible" content="IE=8,IE=9,IE=10">
	<meta http-equiv="Expires" content="0"><meta http-equiv="Cache-Control" content="no-cache"><meta http-equiv="Cache-Control" content="no-store">
	<script src="//hm.baidu.com/hm.js?82116c626a8d504a5c0675073362ef6f"></script>
	<script src="${ctxStatic}/jquery/jquery-1.8.3.min.js" type="text/javascript"></script>
	<script src="${ctxStatic}/bootstrap/2.3.1/js/bootstrap.min.js" type="text/javascript"></script>
	<link href="${ctxStatic}/jquery-select2/3.4/select2.min.css" rel="stylesheet">
	<script src="${ctxStatic}/jquery-select2/3.4/select2.min.js" type="text/javascript"></script>
	<link href="${ctxStatic}/jquery-validation/1.11.0/jquery.validate.min.css" type="text/css" rel="stylesheet">
	<script src="${ctxStatic}/jquery-validation/1.11.0/jquery.validate.min.js" type="text/javascript"></script>
	<link href="${ctxStatic}/jquery-jbox/2.3/Skins/Bootstrap/jbox.min.css" rel="stylesheet">
	<script src="${ctxStatic}/jquery-jbox/2.3/jquery.jBox-2.3.min.js" type="text/javascript"></script> --%>
	<script src="${ctxStatic}/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
	<link href="v/static/My97DatePicker/skin/WdatePicker.css" rel="stylesheet" type="text/css">
	<!-- <script src="v/static/common/mustache.min.js" type="text/javascript"></script>
	<link href="${ctxStatic}/common/jeesite.css" type="text/css" rel="stylesheet">
	<script src="${ctxStatic}/common/jeesite.js" type="text/javascript"></script> -->
  
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
  <script>
	$(function(){
	  	$(".j-validateForm5").validate({//会议室
			submitHandler: function(form){
				sub();
			}
		});
	});
  </script>
</head>
<body>
  <div class="popup">
    <form class="j-validateForm5" id="officeRoomApply" action="${ctx}/oa/approvalProcess/officeRoomApply" method="post">
      <div class="p__content">
        <header class="p__header">申请会议室</header>
        <div class="form-group" id="selectRoom">
          <div class="form-list"></div>
          <div class="form-list"><label for=""><span>*</span>开始时间：</label>
            <div class="form--input">
            	<!-- <input class="required error layDate_apply" style="width:70%;" readonly="" name="startendTime" type="text" value=""> -->
            	<input id="startTime3" name="startTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate required "
							value="<fmt:formatDate value="${oaNotifyMeeting.startTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
							onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false,maxDate:'#F{$dp.$D(\'endTime3\')||\'2120-10-01\'}'});"/>
            </div>
          </div>
          <div class="form-list"><label for=""><span>*</span>结束时间：</label>
            <div class="form--input">
            	<!-- <input class="required error layDate_apply" style="width:70%;" readonly="" name="startendTime" type="text" value=""> -->
            	<input id="endTime3" name="endTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate required "
							value="<fmt:formatDate value="${oaNotifyMeeting.endTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
							onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false,minDate:'#F{$dp.$D(\'startTime3\')}',maxDate:'2120-10-01'});"/>
            </div>
          </div> 
          <div class="form-list"><label for=""><span>*</span>使用时长(小时)：</label>

            <div class="form--input"><input class="required error" style="width:70%;" name="hours" placeholder="1" type="text" value=""></div>
          </div>
          <div class="form-list"><label for=""><span>*</span>申请事由：</label>

            <div class="form--input"><textarea class="required error"  style="width:70%;"  name="reason" cols="40" rows="3"></textarea></div>
          </div>
          <div class="form-list"><label for=""><span>*</span>会议室：</label>

            <div class="form--input">
	            <select class="required error" style="width:70%;" name="officeroomId" id="officeRoomList">
	              <option value="">请选择会议室</option>
	            </select>
            </div>
          </div>
          <div class="form-list " id="selectRoom1"></div>
          <div class="form-list">
          	<label class="form--title control-label">添加抄送人：</label>
			<div class="form--input">
                <sys:treeselect id="oaNotifyRecord" name="oaNotifyRecordIds" value="${oaNotify.oaNotifyRecordIds}" labelName="oaNotifyRecordNames" labelValue="${oaNotify.oaNotifyRecordNames}"
					title="用户" url="/sys/office/treeData?type=3" cssClass="input-xxlarge c-inputStyle" notAllowSelectParent="true" checked="true"  cssStyle="width:100%;"/>
				<span class="help-inline"> </span>
			</div>
          </div>
          
          <!-- <div class="form-list"><label for=""><span>*</span>第2审批人</label>

            <div class="form--input"><input style="width:85%;" type="text" name="" readonly="readonly" value="321">
            </div>
          </div>
          <div class="form-list"><label for=""><span>*</span>第1审批人</label>

            <div class="form--input"><input style="width:85%;" type="text" name="" readonly="readonly" value="1234">
            </div>
          </div> -->
        </div>
      </div>
      <div class="p__btn">
        <button type="submit" <%--onclick="sub()"--%>>提交</button>
        <button type="button" class="j-cancel">取消</button>
      </div>
    </form>
  </div>
	<script src="${ctxStatic}/approvalProcess/js/layer/layer.js"></script>
	<script type="text/javascript">
	function CompareDate(d1,d2){
	  return ((new Date(d1.replace(/-/g,"\/"))) > (new Date(d2.replace(/-/g,"\/"))));
	}
		function sub(){
			var date1 = $("#startTime3").val();
	  		var date2 = $("#endTime3").val();
			if(date1==null || date1=="" || date2==null || date2==""){
				alert("请输入申请时间");
			}else{
		  		console.log(CompareDate(date1,date2)+"***********");
		  		if(CompareDate(date1,date2)){
		  			alert("输入时间有误，请重新输入");
		  		}else{
					$.ajax({  
		                type: "POST",  
		                url:"${ctx}/oa/approvalProcess/officeRoomApply",  
		                data:$("#officeRoomApply").serialize(),// 序列化表单值  
		                dataType:"json",
		                async: false,  
		                error: function(request) {
		                    alert("Connection error");  
		                },  
		                success: function(data) { 
		                	console.log(data+"------data")
		                	if(data == "error"){
		                		alert("该会议室此时间段已经有人申请，请调整申请");
		                		/* var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
		                		parent.layer.close(index); //再执行关闭 
			                    window.location.href="${ctx}/oa/approvalProcess/returnPage"; */
		                	}else{
		                		alert("申请成功");
			                	var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
								parent.layer.close(index); //再执行关闭 
			                    //window.location.href="${ctx}/oa/approvalProcess/returnPage";
		                	}
		                }  
		            });  
	            }
			}
		}
	
		$(function(){
			//提交申请会议室表单加表单验证，点击取消时关闭窗口  start
			/* $(".j-validateForm5").validate({
				submitHandler:function(){
					$.ajax({
						url:"${ctx}/oa/approvalProcess/officeRoomApply",
						data:$("#officeRoomApply").serialize(),
						type:"post",
						dataType:"json",
						success:function(data){
							var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
							parent.layer.close(index); //再执行关闭 
						},
						error:function(){
							alert("提交异常");
						}
					});
				}
			}); */
			$(".j-cancel").click(function(){
				var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
				parent.layer.close(index); //再执行关闭 
			});
			//end
			 var htmlStr = '';
			var htmlStr2 = '';
			$.ajax({
				url:"${ctx}/oa/officeRoom/list",
	  			type:"post",
	  			data:"",
	  			dataType:"json",
	  			success:function(ret){
	  				var list1 = ret.list1;
	  				$.each(list1,function(i,obj){
	  				console.log("***"+obj);
	  				htmlStr+='<option value="'+obj.id+'">'+obj.officeRoomName+'</option>';
	  				});
	  				$("#officeRoomList").append(htmlStr);
	  			
	  				/* var appList = ret.appList; 
	  				$.each(appList,function(i,obj){
		   				htmlStr2+='<div class="form-list"><label for=""><span>*</span>'
		                     	+'第'+ obj.approvalRole +'审批人'
		                   		+'</label>'
		                   		+'<div class="form--input">'
		                     	+'<input style="width:70%;" type="text" name="" readonly="readonly" value="'+obj.approvalPerson+'">'
		                   		+'</div>'
		                 		+'</div>';
		  			});  */
		 			//$("#selectRoom").append(htmlStr2); 
	  			}
			});
			$.ajax({
		        url:"${ctx}/oa/approvalProcess/offficeroomApproval",
		        type:"get",
		        dataType:"text",
		        success:function(ret){
		          /*var htmlStr = '';
		          $.each(ret,function(i,obj){
		            htmlStr += '<li class="person"><label class="form--title form--star" for="">第'+ obj.approvalRole +'审批人</label>'
		                      +'<input class="required error" type="text" name="app" readonly="readonly" value="'+ ret[i].approvalPerson +'" placeholder="4"/>'
		                      +'</li>';
		          });*/
		          $("#selectRoom").append(ret);  
		        }
		      });
		});
		
	</script>
	
  <script>
    laydate.render({
      elem: '.layDate_apply',
      type: 'datetime',//时间类型
      range: '~',//分隔符号
      format:'yyyy-MM-dd HH:mm:ss',
      trigger: 'focus',//触发事件
      btns: ['clear', 'now', 'confirm'],//底部按钮
      theme: 'grid'//格子主题
    });
  </script>
</body>
</html>