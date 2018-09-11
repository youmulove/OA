<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head lang="en">
  <meta charset="UTF-8">
  <title>审批流程</title>
  <link rel="stylesheet" href="${ctxStatic}/approvalProcess/css/global.css"/>
  <link rel="stylesheet" href="${ctxStatic}/approvalProcess/css/approvalProcess.css"/>

	<meta http-equiv="Content-Type" content="text/html;charset=utf-8"><meta name="author" content="http://jeesite.com/">
	<meta name="renderer" content="webkit"><meta http-equiv="X-UA-Compatible" content="IE=8,IE=9,IE=10">
	<meta http-equiv="Expires" content="0"><meta http-equiv="Cache-Control" content="no-cache"><meta http-equiv="Cache-Control" content="no-store">
	<script src="//hm.baidu.com/hm.js?82116c626a8d504a5c0675073362ef6f"></script>
	<script src="${ctxStatic}/jquery/jquery-1.8.3.min.js" type="text/javascript"></script>
	<script src="${ctxStatic}/bootstrap/2.3.1/js/bootstrap.min.js" type="text/javascript"></script>
	<link href="${ctxStatic}/jquery-select2/3.4/select2.min.css" rel="stylesheet">
	<script src="${ctxStatic}/jquery-select2/3.4/select2.min.js" type="text/javascript"></script>
	<link href="${ctxStatic}/jquery-jbox/2.3/Skins/Bootstrap/jbox.min.css" rel="stylesheet">
	<script src="${ctxStatic}/jquery-jbox/2.3/jquery.jBox-2.3.min.js" type="text/javascript"></script>
	<script src="${ctxStatic}/My97DatePicker/WdatePicker.js" type="text/javascript"></script><link href="${ctxStatic}/My97DatePicker/skin/WdatePicker.css" rel="stylesheet" type="text/css">
	<script src="${ctxStatic}/common/mustache.min.js" type="text/javascript"></script>
	<!-- <link href="/jeesite/static/common/jeesite.css" type="text/css" rel="stylesheet"> -->
	<script src="${ctxStatic}/common/jeesite.js" type="text/javascript"></script>
	<link href="${ctxStatic}/jquery-validation/1.11.0/jquery.validate.min.css" type="text/css" rel="stylesheet">
	<script src="${ctxStatic}/jquery-validation/1.11.0/jquery.validate.min.js" type="text/javascript"></script>
	<script>
		$(function(){
			$(".j-validateForm1").validate({//请假
				submitHandler: function(form){
					leaveSub();
				}
			});
			$(".j-validateForm2").validate({//加班
				submitHandler: function(form){
					overTimeSub();
				}
			});
			$(".j-validateForm3").validate({//投影仪
				submitHandler: function(form){
					sub();
				}
			});
			$(".j-validateForm4").validate({//通用
				submitHandler: function(form){
					currencySub();
				}
			});
		});
	</script>
	<style>
		html,body{
			min-height:100%;
			width:100%;
			overflow-x:hidden;
		}
		body{
			background-color:#fff;
		}
		.nav-tabs {
		    border-bottom: 1px solid #ddd;
		}
		.nav {
		    margin-left: 0;
		    list-style: none;
		}
		.nav-tabs>li {
		    margin-bottom: -1px;
		}
		.nav-tabs>li, .nav-pills>li {
		    float: left;
		}
		.nav-tabs>.active>a, .nav-tabs>.active>a:hover, .nav-tabs>.active>a:focus {
		    color: #555;
		    cursor: default;
		    background-color: #fff;
		    border: 1px solid #ddd;
		    border-bottom-color: transparent;
		}
		.nav-tabs>li>a {
		    padding-top: 8px;
		    padding-bottom: 8px;
		    line-height: 20px;
		    border: 1px solid transparent;
		    -webkit-border-radius: 4px 4px 0 0;
		    -moz-border-radius: 4px 4px 0 0;
		    border-radius: 4px 4px 0 0;
		}
		.nav-tabs>li>a, .nav-pills>li>a {
		    padding-right: 12px;
		    padding-left: 12px;
		    margin-right: 2px;
		    line-height: 20px;
		}
		.nav>li>a {
		    display: block;
		    color: #2fa4e7;
		}
		/* 弹窗iframe不出现滑动条 */
		@media screen and (min-width:0){
			div.layui-layer-iframe{
				overflow-y:hidden;
			}
		}
	</style>
</head>
<body>
  <main class="main clearfix" style="width:100%; height:100%;">
    <!--<header class="m__header">
      审批流程控制
    </header>-->
    <ul class="nav nav-tabs clearfix">
		<li class="active"><a href="/jeesite/a/oa/approvalProcess/returnPage">发起申请</a></li>
		<li><a href="/jeesite/a/act/task/myProcess?type=leave">我发起的</a></li>
  	</ul>
    <div style="display:flex">
      <aside class="m__aside j-aside">
        <ul>
          <li data-url="url_leave" class="hover"><img src="${ctxStatic}/approvalProcess/img/leave.png" alt=""/>请假</li>
          <li data-url="url_overtime""><img src="${ctxStatic}/approvalProcess/img/overtime.png" alt=""/>加班</li>
          <li data-url="url_home" onclick="officeRoomList2()"><img src="${ctxStatic}/approvalProcess/img/home.png" alt=""/>会议室</li>
          <li data-url="url_projector" onclick="projectorApproval()"><img src="${ctxStatic}/approvalProcess/img/projector.png" alt=""/>投影仪</li>
          <li data-url="url_currency" onclick="currencyApproval()"><img src="${ctxStatic}/approvalProcess/img/common.png" alt=""/>通用</li>
        </ul>
      </aside>
      <article class="m__article">
        <!--请假部分-->
        <div class="url_leave">
          <div class="m__ar__list">
            <form class="j-validateForm1" action="${ctx}/oa/leave/leaveApply" method="post" id="leaveForm">
              <ul>
                <li><label class="form--title form--star" for="">请假类型</label>
                  <select name="leaveType" style="width: 100px" id="">
                    <option value="事假">事假</option>
                    <option value="年假">年假</option>
                    <option value="病假">病假</option>
                    <option value="调休">调休</option>
                    <option value="婚假">婚假</option>
                    <option value="产假">产假</option>
                    <option value="陪产假">陪产假</option>
                    <option value="例假">例假</option>
                    <option value="丧假">丧假</option>
                  </select>
                </li>
                <!-- <li><label class="form--title form--star" for="">起、止时间</label>
                  <input readonly class="layDate required error" type="text" name="startendTime"/>
                </li> -->
                <li>
                	<label class="form--title form--star" for="">开始时间</label>
                  	<input id="startTime" name="startTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate required "
							value="<fmt:formatDate value="${oaNotifyMeeting.startTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
							onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false,maxDate:'#F{$dp.$D(\'endTime\')||\'2120-10-01\'}'});"/>
                </li>
                <li>
                	<label class="form--title form--star" for="">结束时间</label>
                  	<input id="endTime" name="endTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate required "
							value="<fmt:formatDate value="${oaNotifyMeeting.endTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
							onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false,minDate:'#F{$dp.$D(\'startTime\')}',maxDate:'2120-10-01'});"/>					
                </li>
                <li><label class="form--title form--star" for="">天数（天）</label>
                  <input id="leaveDay" class="required error" type="text" name="hours" onblur="leaveApproval()" style="width: 50px"/>&nbsp;&nbsp;&nbsp;天
                </li>
                <li id="leaveApproval"><label class="form--title form--star" for="">请假事由</label>
                  <textarea class="required error" id="leaveReason" name="reason" cols="30" rows="10"></textarea>
                </li>
                <li><label class="form--title control-label">添加抄送人：</label>
					<div class="controls">
		                <sys:treeselect id="oaNotifyRecord" name="oaNotifyRecordIds" value="${oaNotify.oaNotifyRecordIds}" labelName="oaNotifyRecordNames" labelValue="${oaNotify.oaNotifyRecordNames}"
							title="用户" url="/sys/office/treeData?type=3" cssClass="input-xxlarge" notAllowSelectParent="true" checked="true"/>
						<span class="help-inline"> </span>
					</div>
				</li>
				<%-- <li><label class="form--title control-label">附件：</label>
					<div class="controls">
						<form:hidden id="yuliu2" path="yuliu2" htmlEscape="false" maxlength="255" class="input-xlarge"/>
						<sys:ckfinder input="yuliu2" type="files" uploadPath="/oa/oaLeaved" selectMultiple="true"/>
					</div>
				</li> --%>
                <!-- <li><label for="">图片</label>
                  <input class="form--file" type="file" name="leaveImg"/>
                </li> -->
              </ul>
          	<!-- <input type="submit" class="m--btn" value="提交"> -->
          	<input type="submit" <%--onclick="leaveSub()"--%> class="m--btn" value="提交">
            </form>
          </div>
          <!-- <button class="m--btn" type="submit">提交</button> -->
        </div>
        <!--加班部分-->
        <div class="url_overtime" style="display:none;">
          <div class="m__ar__list">
            <form class="j-validateForm2" id="overTimeApplyForm" action="${ctx}/oa/approvalProcess/overTimeApply" method="post">
              <ul>
                <!--<li>默认审批人
                  <span class="m__icon&#45;&#45;add"></span>
                </li>
                <li>审批人去重
                  <select name="" id="">
                    <option value="">不启用自动去重</option>
                    <option value="">统一审批人在流程中出现多次时，自动去重</option>
                    <option value="">统一审批人仅在连续出现时，自动去重</option>
                  </select>
                </li>-->
                <!-- <li><label class="form--title form--star" for="">起、止时间</label>
                  <input readonly class="layDate required error" type="text" name="startendTime"/>
                </li> -->
                 <li>
                	<label class="form--title form--star" for="">开始时间</label>
                  	<input id="startTime2" name="startTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate required "
							value="<fmt:formatDate value="${oaNotifyMeeting.startTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
							onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false,maxDate:'#F{$dp.$D(\'endTime2\')||\'2120-10-01\'}'});"/>
                </li>
                <li>
                	<label class="form--title form--star" for="">结束时间</label>
                  	<input id="endTime2" name="endTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate required "
							value="<fmt:formatDate value="${oaNotifyMeeting.endTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
							onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false,minDate:'#F{$dp.$D(\'startTime2\')}',maxDate:'2120-10-01'});"/>					
                </li>
                <li><label class="form--title form--star" for="">时长（小时）</label>
                  <input type="text" id="hours" name="hours" class="required error" placeholder="4"/>
                </li>
                <li id="isholiday"><label class="form--title form--star" for="">法定节假日</label>
                  <input type="radio" id="isHoliday" name="isHoliday" value="y"/>是&nbsp;&nbsp;&nbsp;&nbsp;
                  <input type="radio" id="isHoliday" name="isHoliday" value="n"/>否
                </li>
                <li id="approvalPerson"><label class="form--title form--star" for="">加班事由</label>
                  <textarea id="overTimeReason" name="reason" class="required error" cols="30" rows="10"></textarea>
                </li>
                <li><label class="form--title control-label">添加抄送人：</label>
					<div class="controls">
		                <sys:treeselect id="fuckOverTime" name="oaNotifyRecordIds" value="${oaNotify.oaNotifyRecordIds}" labelName="oaNotifyRecordNames" labelValue="${oaNotify.oaNotifyRecordNames}"
							title="用户" url="/sys/office/treeData?type=3" cssClass="input-xxlarge" notAllowSelectParent="true" checked="true"/>
						<span class="help-inline"> </span>
					</div>
				</li>
               <!--  <li><label class="form--star" for="">审批人</label>
                  <textarea style="" name="reason" id="" cols="30" rows="3"></textarea>
                </li> -->
                <!-- <li><label for="">图片</label>
                  <input class="form--file" type="file"/>
                </li> -->
              </ul>
              <!-- <input type="submit"  name="overTimeBtn" class="m--btn" value="提交"> -->
              <input type="submit" <%--onclick="overTimeSub()"--%> name="overTimeBtn" class="m--btn" value="提交">
            </form>
          </div>
          <!-- <button class="m--btn" type="button">提交</button> -->
        </div>
        <!--申请会议室部分-->
        <div class="url_home" style="position:relative; display:none;">
          <div class="m__ar__list home__list">
            <!--会议室详情-->
            <ul id="ul">
              <li class="clearfix" style="line-height: normal; margin-bottom:20px;" id="li">
                <p class="home--title" style="height:100%;">
                  <span style="display:inline-block;width:50px;">会议室</span>
                </p>
                <div class="home--content" style="height:100%;">
                  <p>
                    <!-- <span>申请日期</span> -->
                    <span style="width: 300px;text-align: center">使用时间</span>
                    <span style="width: 150px;text-align: center">部门</span>
                    <span style="width: 80px;text-align: center">申请人</span>
                  </p>
                </div>
              </li>
              <%-- <li class="clearfix" >
                <p class="home--title" id="hometitle">
                  <img src="${ctxStatic}/approvalProcess/img/home1.png" alt=""/>
                  <!-- A403 -->
                </p>
                <div class="home--content" id="homecontent">
                  <p>
                    <span style="width: 300px">2017-09-22 08:00:00 ~ 2017-09-22 10:00:00</span>
                    <!-- <span style="width: 200px">8:00-9:00</span> -->
                    <span style="width: 150px">科技公司综合业务部</span>
                    <span style="width: 80px">何旭</span>
                  </p>
                  <!-- <p>
                    <span>2017-09-21</span>
                    <span>8:00-9:00</span>
                    <span>科技公司综合业务部</span>
                    <span>何旭</span>
                  </p> -->
                </div>
              </li> --%>
              
            </ul>
          </div>
          <!--申请会议室-->
          <button class="j-applyHome m--btn" style="position:absolute;top:-25px;right:-75px;" class="m--btn" type="button">申请会议室</button>
        </div>
        <!--投影仪部分-->
        <div class="url_projector" style="display:none;">
          <div class="m__ar__list">
            <form class="j-validateForm3" id="projectorApply" action="${ctx}/oa/approvalProcess/projectorApply" method="post">
              <ul>
               <!--  <li><label class="form--star" for="">申请人</label>
                  <input type="text"/>
                </li> -->
                <!-- <li><label class="form--title form--star" for="">开始、结束时间</label>
                  <input readonly class="required error layDate" type="text" name="startendtime"/>
                </li> -->
                <li>
                	<label class="form--title form--star" for="">开始时间</label>
                  	<input id="startTime4" name="startTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate required "
							value="<fmt:formatDate value="${oaNotifyMeeting.startTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
							onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false,maxDate:'#F{$dp.$D(\'endTime4\')||\'2120-10-01\'}'});"/>
                </li>
                <li>
                	<label class="form--title form--star" for="">结束时间</label>
                  	<input id="endTime4" name="endTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate required "
							value="<fmt:formatDate value="${oaNotifyMeeting.endTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
							onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false,minDate:'#F{$dp.$D(\'startTime4\')}',maxDate:'2120-10-01'});"/>					
                </li>
                <li><label class="form--title form--star" for="">时长（小时）</label>
                  <input class="required error" id="proHours" type="text" name="hours"/>
                </li>
                <li><label class="form--title form--star" for="">投影仪选择</label>
                    <select class="required error" style="width:50%;" name="projectoroNo" id="projectorList">
		              <option value="">请选择投影仪</option>
		            </select>
                </li>
                <li><label class="form--title form--star" for="">是否需要转换头</label>
                  <input type="radio" name="adapter" onclick="projectorApproval()" checked="checked" value="y"/>是&nbsp;&nbsp;&nbsp;&nbsp;
                  <input type="radio" name="adapter" onclick="projectorApproval()" value="n"/>否
                </li>
                <li id="projectorApproval"><label class="form--title form--star" for="">使用事由</label>
                  <textarea class="required error" id="proReason" name="reason" cols="30" rows="10"></textarea>
                </li>
                <li><label class="form--title control-label">添加抄送人：</label>
					<div class="controls">
		                <sys:treeselect id="fuckProjector" name="oaNotifyRecordIds" value="${oaNotify.oaNotifyRecordIds}" labelName="oaNotifyRecordNames" labelValue="${oaNotify.oaNotifyRecordNames}"
							title="用户" url="/sys/office/treeData?type=3" cssClass="input-xxlarge" notAllowSelectParent="true" checked="true"/>
						<span class="help-inline"> </span>
					</div>
				</li>
                <!-- <li><label for="">图片</label>
                  <input class="form--file" type="file"/>
                </li> -->
              </ul>
              <input id="overTimeBth" type="submit" <%--onclick="sub()" --%> name="overTimeBtn" class="m--btn" value="提交">
            </form>
          </div>
          <!-- <button class="m--btn" type="button">提交</button> -->
        </div>
		<!--通用部分-->
        <div class="url_currency" style="display:none;">
          <div class="m__ar__list">
            <form class="j-validateForm4" id="currencyApply" action="${ctx}/oa/approvalProcess/currencyApply" method="post">
            <%-- <form:form modelAttribute="111" action="${ctx}/oa/approvalProcess/currencyApply" method="post" class="j-validateForm4">  --%> 
              <ul>
               <!--  <li><label class="form--star" for="">申请人</label>
                  <input type="text"/>
                </li> -->
                <!-- <li><label class="form--title form--star" for="">起、止时间</label>
                  <input readonly class="layDate required error" type="text" name="startendTime"/>
                </li> -->
                <li>
                	<label class="form--title form--star" for="">开始时间</label>
                  	<input id="startTime5" name="startTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate required "
							value="<fmt:formatDate value="${oaNotifyMeeting.startTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
							onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false,maxDate:'#F{$dp.$D(\'endTime5\')||\'2120-10-01\'}'});"/>
                </li>
                <li>
                	<label class="form--title form--star" for="">结束时间</label>
                  	<input id="endTime5" name="endTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate required "
							value="<fmt:formatDate value="${oaNotifyMeeting.endTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
							onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false,minDate:'#F{$dp.$D(\'startTime5\')}',maxDate:'2120-10-01'});"/>					
                </li>
                <li><label class="form--title form--star" for="">申请事项</label>
                  <textarea class="required error" id="applyItem" name="applyItem" cols="30" rows="3"></textarea>
                </li>
                <li id="currencyApproval"><label class="form--title form--star" for="">申请理由</label>
                  <textarea class="required error" id="currencyReason" name="reason" cols="30" rows="10"></textarea>
                </li>
                <li><label class="form--title control-label">添加抄送人：</label>
					<div class="controls">
		                <sys:treeselect id="fuckCurrency" name="oaNotifyRecordIds" value="${oaNotify.oaNotifyRecordIds}" labelName="oaNotifyRecordNames" labelValue="${oaNotify.oaNotifyRecordNames}"
							title="用户" url="/sys/office/treeData?type=3" cssClass="input-xxlarge" notAllowSelectParent="true" checked="true"/>
						<span class="help-inline"> </span>
					</div>
				</li>
				<%-- <li><label class="form--title control-label">附件：</label>
					<div class="controls">
						<form:hidden id="yuliu2" path="yuliu2" htmlEscape="false" maxlength="255" class="input-xlarge"/>
						<sys:ckfinder input="yuliu2" type="files" uploadPath="/oa/oaLeaved" selectMultiple="true"/>
					</div>
				</li> --%>
                <!-- <li><label for="">图片</label>
                  <input class="form--file" type="file"/>
                </li> -->
              </ul>
              <!-- <input type="submit"  name="overTimeBtn" class="m--btn" value="提交"> -->
              <input type="submit" <%--onclick="currencySub()"--%> name="overTimeBtn" class="m--btn" value="提交">
            </form>
            <%-- </form:form> --%>
          </div>
          <!-- <button class="m--btn" type="button">提交</button> -->
        </div>
      </article>
    </div>
  </main>
  <%-- <script src="${ctxStatic}/approvalProcess/js/jquery-3.2.1.js"></script> --%>
  <script src="${ctxStatic}/approvalProcess/js/layer/layer.js"></script>
  <script src="${ctxStatic}/approvalProcess/js/laydate/laydate.js"></script>
  <script type="text/javascript">
  	function CompareDate(d1,d2){
	  return ((new Date(d1.replace(/-/g,"\/"))) > (new Date(d2.replace(/-/g,"\/"))));
	}
	//通用申请表单提交
  	function currencySub(){
  		var date1 = $("#startTime5").val();
  		var date2 = $("#endTime5").val();
  		var applyItem = $("#applyItem").val();
  		var currencyReason = $("#currencyReason").val();
  		if(date1==null || date1=="" || date2==null || date2==""){
			alert("请输入申请时间");
		}else{
	  		console.log(CompareDate(date1,date2)+"***********");
	  		if(CompareDate(date1,date2)){
	  			alert("输入时间有误，请重新输入");
	  		}else{
	  			if(applyItem==null || applyItem=="" || currencyReason=="" || currencyReason==null){
	  				alert("输入完整信息");
	  			}else{
			  		$.ajax({
			           type: "POST",  
			           url:"${ctx}/oa/approvalProcess/currencyApply",  
			           data:$("#currencyApply").serialize(),// 序列化表单值  
			           dataType:"json",
			           async: false,
			           error: function(request) {
			               alert("Connection error");  
			           },  
			           success: function(data) {
						alert("申请成功");
						window.location.href="${ctx}/oa/approvalProcess/returnPage";
			           }
			       }); 
	  			}
	  		}
		}
  	}
	//加班申请表单提交
  	function overTimeSub(){
  		var date1 = $("#startTime2").val();
  		var date2 = $("#endTime2").val();
  		var isHoliday = $('#isholiday input[name="isHoliday"]:checked ').val();//是否是法定节假日
  		console.log(isHoliday+"*****");
  		if(date1==null || date1=="" || date2==null || date2==""){
			alert("请输入申请时间");
		}else{
	  		console.log(CompareDate(date1,date2)+"***********");
	  		if(CompareDate(date1,date2)){
	  			alert("输入时间有误，请重新输入");
	  		}else{
	  			if(isHoliday==null || isHoliday==""){
	  				alert("请输入完整信息");
	  			}else{
			  		$.ajax({
			           type: "POST",  
			           url:"${ctx}/oa/approvalProcess/overTimeApply",  
			           data:$("#overTimeApplyForm").serialize(),// 序列化表单值  
			           dataType:"json",
			           async: false,
			           error: function(request) {
			               alert("Connection error");  
			           },  
			           success: function(data) {
						alert("申请成功");
						window.location.href="${ctx}/oa/approvalProcess/returnPage";
			           }
			       }); 
	  			}
	  		}
		}
  	}
  	//请假申请表单提交
  	function leaveSub(){
  		var date1 = $("#startTime").val();
  		var date2 = $("#endTime").val();
  		var reason = $("#leaveReason").val();
  		var leaveDay = $("#leaveDay").val();
  		if(date1==null || date1=="" || date2==null || date2==""){
			alert("请输入申请时间");
		}else{
			if(leaveDay=="" || leaveDay==null || reason==null || reason==""){
				alert("请输入完整信息");
			}else{
		  		console.log(CompareDate(date1,date2)+"***********");
		  		if(CompareDate(date1,date2)){
		  			alert("输入时间有误，请重新输入");
		  		}else{
			  		$.ajax({
			           type: "POST",  
			           url:"${ctx}/oa/leave/leaveApply",  
			           data:$("#leaveForm").serialize(),// 序列化表单值  
			           dataType:"json",
			           async: false,
			           error: function(request) {
			               alert("Connection error");  
			           },  
			           success: function(data) {
						alert("申请成功");
						window.location.href="${ctx}/oa/approvalProcess/returnPage";
			           }
			       }); 
		  		}
			}
		}
  	}
  	
  	$(function(){
  		var htmlStr='';
  		$.ajax({
			url:"${ctx}/oa/oaProjector/showAll",
  			type:"post",
  			data:"",
  			dataType:"json",
  			success:function(ret){
  				$.each(ret,function(i,obj){
  				//console.log("***"+obj.projectoroNo);
  				htmlStr+='<option value="'+obj.id+'">'+obj.projectoroNo+'</option>';
  				});
  				$("#projectorList").append(htmlStr);
  			}
		});
  	});
  
  	//投影仪表单提交
  	function sub(){
  		var date1 = $("#startTime4").val();
  		var date2 = $("#endTime4").val();
  		var proHours = $("#proHours").val();
  		var proReason = $("#proReason").val();
  		if(date1==null || date1=="" || date2==null || date2==""){
			alert("请输入申请时间");
		}else{
	  		console.log(CompareDate(date1,date2)+"***********");
	  		if(CompareDate(date1,date2)){
	  			alert("输入时间有误，请重新输入");
	  		}else{
	  			if(proHours=="" || proHours==null || proReason=="" || proReason==null){
	  				alert("请输入完整信息");
	  			}else{
					$.ajax({  
		               type: "POST",  
		               url:"${ctx}/oa/approvalProcess/projectorApply",  
		               data:$("#projectorApply").serialize(),// 序列化表单值  
		               dataType:"json",
		               async: false,  
		               error: function(request) {  
		                   alert("Connection error");  
		               },  
		               success: function(data) { 
		               	console.log(data+"------data")
		               	if(data == "error"){
		               		alert("该投影仪此时间段已经有人申请，请调整申请");
		                    //window.location.href="${ctx}/oa/approvalProcess/returnPage"
		               	}else{
		               		alert("申请成功");
		                    window.location.href="${ctx}/oa/approvalProcess/returnPage"  
		               	}
		               }  
		           });  
	  			}
	  		}
		}
	}
  	
  //通用申请的审批人查询
    function currencyApproval(){
      $(".person").empty();
      $.ajax({
        url:"${ctx}/oa/approvalProcess/currencyApproval",
        data:"",
        type:"get",
          dataType:"text",
        success:function(ret){
         /*  var htmlStr = '';
          $.each(ret,function(i,obj){
            htmlStr += '<li class="person"><label class="form--title form--star" for="">第'+ obj.approvalRole +'审批人</label>'
                      +'<input class="required error" type="text" name="app" readonly="readonly" value="'+ obj.approvalPerson +'" placeholder="4"/>'
                      +'</li>';
          });*/
          $("#currencyApproval").append(ret); 
        }
        
      });
    }
  	
  	//查询请假申请的审批人信息
  	function leaveApproval(){
  		$(".person").empty();
  		var day = $("#leaveDay").val();
  		if(day!=null && day!=""){
	  		$.ajax({
	  			url:"${ctx}/oa/approvalProcess/leaveApproval?day="+day,
	  			data:null,
	  			type:"get",
	  			dataType:"text",
	  			success:function(ret){
	  				console.log(ret);
	  				/* var htmlStr = '';
	  				$.each(ret,function(i,obj){
	  					htmlStr += '<li class="person"><label class="form--title form--star" for="">第'+ obj.approvalRole +'审批人</label>'
					              +'<input class="required error" type="text" name="app" readonly="readonly" value="'+ ret[i].approvalPerson +'" placeholder="4"/>'
					              +'</li>';
	  				}); */
	  				$("#leaveApproval").append(ret);	
	  			},
	  			/* error:function(){
	  				alert("879897897");
	  			} */
	  		});
  		}
  	}
  
  //查询投影仪申请的审批人信息
    function projectorApproval(){
      $(".person").empty();
      $.ajax({
        url:"${ctx}/oa/approvalProcess/projectorApproval",
        type:"get",
        dataType:"text",
        success:function(ret){
          /*var htmlStr = '';
          $.each(ret,function(i,obj){
            htmlStr += '<li class="person"><label class="form--title form--star" for="">第'+ obj.approvalRole +'审批人</label>'
                      +'<input class="required error" type="text" name="app" readonly="readonly" value="'+ ret[i].approvalPerson +'" placeholder="4"/>'
                      +'</li>';
          });*/
          $("#projectorApproval").append(ret);  
        }
      });
    }
  
   	//查询加班申请的审批人信息
  	$(function(){
  		$(":radio").click(function(){
  		var isHoliday = $('#isholiday input[name="isHoliday"]:checked ').val();//是否是法定节假日
  		console.log("==============="+isHoliday);
  			$(".person").empty();
  			$.ajax({
  			url:"${ctx}/oa/approvalProcess/overTimeApproval",
  			type:"post",
  			data:{isHoliday},
  			dataType:"text",
  			success:function(ret){
	  			/* var htmlStr = '';
  				console.log(ret);
  				$.each(ret,function(i,obj){
  					htmlStr += '<li class="person"><label class="form--title form--star" for="">第'+ obj.approvalRole +'审批人</label>'
				              +'<input type="text" name="app" readonly="readonly" value="'+ ret[i].approvalPerson +'" placeholder="4"/>'
				              +'</li>'
  				});*/
  				$("#approvalPerson").append(ret);
  			}
  			});
  		});
  	});
  	
  	
  	function officeRoomList2(){
  		$("#ul").empty();
  		var htmlStr1 = '';
  		var htmlStr2 = '';
  		var htmlStr3 = '';
  		$.ajax({
  			url:"${ctx}/oa/officeRoom/list",
  			type:"post",
  			data:"",
  			dataType:"json",
  			success:function(ret){
  				var list2 = ret.list2;
  				var a;
  				$.each(list2,function(i,obj){
  					//console.log("***");
  					//console.log("---"+(obj.yuliu1 == a));
  					if(obj.officeroomId != a){
  					a = obj.officeroomId;
  					htmlStr1+="<li class='clearfix' id='li2'>"
            				+"<p class='home--title' id='hometitle'>"
            				+"<img src='${ctxStatic}/approvalProcess/img/home1.png' alt=''/>"
            				+obj.yuliu1
                			+"</p>"
	                		+"<div class='home--content' id='homecontent'>";
				    htmlStr3="</div></li>";
  					}
  					if(obj.officeroomId == a){
	                	htmlStr1+="<p>"
	                    +"<span style='width: 300px;text-align: center'>"+format(obj.startTime)+"~"+format(obj.endTime)+"</span>"
	                    +"<span style='width: 150px;text-align: center'>"+obj.department+"</span>"
	                    +"<span style='width: 80px;text-align: center'>"+obj.applyName+"</span>"
	                  	+"</p>"
					}
  				});
  			htmlStr1+=htmlStr3;
  				
 			htmlStr2+='<li class="clearfix" style="line-height: normal; margin-bottom:20px;" id="li">'
               		+'<p class="home--title" style="height:100%;">'
                 	+'<span style="display:inline-block;width:50px;">会议室</span>'
               		+'</p>'
               		+'<div class="home--content" style="height:100%;">'
                 	+'<p>'
                   	+'<span style="width: 300px;text-align: center">使用时间</span>'
                   	+'<span style="width: 150px;text-align: center">部门</span>'
                   	+'<span style="width: 80px;text-align: center">申请人</span>'
                 	+'</p>'
               		+'</div>'
             		+'</li>';
            $("#ul").append(htmlStr2);
  			$("#ul").append(htmlStr1);
  			}
  		});
  	}
  	
  	//格式化日期
	function format(date){
		var date = new Date(date);
  		var dateStr = date.getFullYear()+'-'+date.getMonth()+'-'+date.getDate()+' '+date.getHours()+':'+date.getMinutes()+':'+date.getSeconds();
		return dateStr;
	}
  	
  
  </script>
  	
  <script type="text/javascript">
    $(".j-aside").on("click","li",function(){
      $(this).addClass("hover").siblings("li").removeClass("hover");
      $("."+$(this).attr("data-url")).show().siblings("div").hide();
    });
    //日期范围
    /* lay('.layDate').each(function(){
      laydate.render({
        elem: this,
        type: 'datetime',//时间类型
        range: '~',//分隔符号
        format:'yyyy-MM-dd HH:mm:ss',
        trigger: 'focus',//触发事件
        btns: ['clear', 'now', 'confirm'],//底部按钮
        theme: 'grid'//格子主题
      });
    }); */
    
    
    
    /*申请会议室*/
    $(".j-applyHome").on("click",function() {
    	layer.open({
	        type: 2,//2是iframe层
	        title: '申请会议室',//标题
	        shadeClose: true,//点击遮罩层也关闭
	        id: '1',//同时只能有一个弹窗
	        scrollbar: false,//弹窗时浏览器不能滚动
	        area: ['800px', '95%'],//弹窗宽高
	        //弹窗点击×的回调函数
	        /* cancel: function(index, layero){
	         layer.close(index);
	         },*/
	        content: '${ctx}/oa/approvalProcess/officeRoomApplyPage'//这个引号里写要访问页面的路径
		});
    });
    
    
    
   
  </script>
</body>
</html>