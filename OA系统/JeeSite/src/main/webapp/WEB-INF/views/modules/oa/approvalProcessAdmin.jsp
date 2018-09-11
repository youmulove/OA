<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head lang="en">
  <meta charset="UTF-8">
  <title>审批流程管理端页面</title>
  <link rel="stylesheet" href="${ctxStatic}/approvalProcess/css/global.css"/>
  <link rel="stylesheet" href="${ctxStatic}/approvalProcess/css/approvalProcess2.css"/>
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
	<script src="${ctxStatic}/jquery-jbox/2.3/jquery.jBox-2.3.min.js" type="text/javascript"></script>
	<script src="${ctxStatic}/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
	<link href="${ctxStatic}/My97DatePicker/skin/WdatePicker.css" rel="stylesheet" type="text/css">
	<script src="${ctxStatic}/common/mustache.min.js" type="text/javascript"></script>
	<!-- <link href="/jeesite/static/common/jeesite.css" type="text/css" rel="stylesheet"> -->
	<script src="${ctxStatic}/common/jeesite.js" type="text/javascript"></script>
	<script src="${ctxStatic}/layer-v2.3/layer/layer.js" type="text/javascript"></script>
	<style type="text/css">
		.c-approver{
			position:relative;
		}
		.c-approver input["text"]{
		 	position:absolute;
		 	left:140px;
		}
		.c-approver input + label{
		 	position:absolute;
    		top: 8px;
		 	left:450px;
		 	line-height: 16px;
		}
		input.c-dataScope{
			position:static;
		}
		input.c-dataScope + label{
			position:static;
		}
		
		html,body{
			height:100%;
			width:100%;
			overflow-x:hidden;
		}
		body{
			background-color:#fff;
		}
		/* 设置审批导航 */
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
		/* 放拿到的审批人的盒子 */
		.approverName{
			float:left;
			max-width:500px;
			white-space: nowrap;
		    overflow: hidden;
		    text-overflow: ellipsis;
		}
		/* 按个人和职级审批input外的盒子 */
		.c-approver__box{
			margin-bottom:15px;
		}
		/* 点击添加审批人 按钮样式*/
		.j-addApprover{
			float:left;
			padding:3px 8px;
			margin-left:5px;
			margin-top:5px;
			border: 1px solid #38adff;
		    border-radius: 4px;
		    background-color: #38adff;
		    font-size: 14px;
		    color: #fff;
		    outline: none;
		    cursor: pointer;
		}
		/* 点击对应审批人，右侧滑动弹框的动画样式 */
		@-webkit-keyframes right-anim-show{
	      0% {
	        opacity: 0;
	        -webkit-transform: translateX(600px);
	        transform: translateX(600px)
	      }
	      100% {
	        opacity: 1;
	        -webkit-transform: translateX(0);
	        transform: translateX(0)
	      }
	    }
	
	    @keyframes right-anim-show{
	      0% {
	        opacity: 0;
	        -webkit-transform: translateX(600px);
	        -ms-transform: translateX(600px);
	        transform: translateX(600px)
	      }
	      100% {
	        opacity: 1;
	        -webkit-transform: translateX(0);
	        -ms-transform: translateX(0);
	        transform: translateX(0)
	      }
	    }
	    .layer-anim-07 {
	      -webkit-animation:right-anim-show .4s ease-out;
	      animation:right-anim-show .4s ease-out;
	    }
	</style>
	<script>
		$(function(){
			$(".j-validateForm1").validate();
			$(".j-validateForm2").validate();
			$(".j-validateForm3").validate();
			$(".j-validateForm4").validate();
			$(".j-validateForm5").validate();
			var inputArr = $(".c-approver .input-append").find("input");
			for(let i = 1;i <= inputArr.length;i++){
				if(i % 2 === 1){
					$(inputArr[i-1].outerHTML.replace(/"hidden"/g,"\"text\"")).insertBefore($(inputArr[i-1]));
					$(inputArr[i-1]).remove();
				}
			}
		});
	</script>
</head>
<body>
  <main class="main clearfix" style="height:100%; width:100%;">
   	<ul class="nav nav-tabs clearfix">
		<li class="active"><a href="/jeesite/a/oa/approvalProcessAdmin/returnPage/">设置审批</a></li>
		<li><a href="/jeesite/a/oa/approvalRule/alreadyProcess?type=leave">已设置审批</a></li>
  	</ul>
    <div style="display:flex; height:100%;">
      <aside class="m__aside j-aside">
        <ul>
          <li data-url="url_leave" class="hover"><img src="${ctxStatic}/approvalProcess/img/leave.png" alt=""/>请假</li>
          <li data-url="url_overtime"><img src="${ctxStatic}/approvalProcess/img/overtime.png" alt=""/>加班</li>
          <li data-url="url_home"><img src="${ctxStatic}/approvalProcess/img/home.png" alt=""/>会议室</li>
          <li data-url="url_projector"><img src="${ctxStatic}/approvalProcess/img/projector.png" alt=""/>投影仪</li>
          <li data-url="url_currency"><img src="${ctxStatic}/approvalProcess/img/common.png" alt=""/>用章</li>
        </ul>
      </aside>
      <article class="m__article">
        <!--请假部分-->
        <div class="url_leave">
          <div class="m__ar__list">
          	<ul class="j-approvalRule">
              	<li>
              		<label class="form--title form--star" for="">审批规则</label>
                	<input name="rule1" type="radio" value="0" checked="true" style="margin-right:10px;"/>固定审批
                	<input name="rule1" type="radio" value="1" style="margin-right:10px;"/>自由审批
              	</li>
            </ul>
            <form class="j-validateForm1" id="leaveForm" action="${ctx}/oa/approvalRule/addApprovalRule">
              <ul>
                <!-- <li><label class="form--star" for="">请假天数开始值</label>
                  <input class="layDate required" id="id1" placeholder="0" name="approvalStart" type="text" />
                  <span id="id2" style="display:none"></span>
                </li>
                <li><label class="form--star" for="">请假天数结束值</label>
                  <input class="layDate required" id="id1" placeholder="1" name="approvalEnd" type="text"/>
                  <span id="id2" style="display:none"></span>
                </li> -->
                <li class="j-shenPiClass"><label class="form--title form--star" for="">审批级别</label>
                  <input name="approvalProcess" value="1" type="radio" checked="true" style="margin-right:10px;"/>一级审批流
                  <input name="approvalProcess" value="2" type="radio" style="margin:10px;"/>二级审批流
                  <input name="approvalProcess" value="3" type="radio" style="margin:10px;"/>三级审批流
                  <input name="approvalProcess" value="4" type="radio" style="margin:10px;"/>四级审批流
                </li>
                <li class="c-approver">
                	<div class="clearfix">
             			<label class="form--title form--star" for="">一级审批人</label>
               			<span class="leave-1th">
               				<!-- 放审批人name的地方 -->
               				<span class="approverName" title=""></span>
               				<!-- 选择审批人的种类（按人设置还是按职级） -->
               				<input class="approverSort"  name="userType1" type="hidden" value=""/>
               				<!-- 放审批人id的地方 -->
               				<input class="approverId" name="oaNotifyRecordIds1" type="hidden" value=""/>
               				<!-- 该审批人的天数范围 -->
               				<input class="dayStart" name="start1" type="hidden" value=""/>
               				<input class="dayEnd" name="end1" type="hidden" value=""/>
               				<button data-type="leave" data-nth="1" type="button" class="j-addApprover">添加</button>
               			</span>
               		</div>
               		<div class="dayRange" style="display:none">
                		<label class="form--title form--star" for="">请假天数范围</label>
	              		<span class="dayStart" ></span>&nbsp;天&nbsp;~~&nbsp;
	              		<span class="dayEnd"></span>&nbsp;天
              		</div>
              	</li>
                  		<%-- <div class="j-approver">
	                  		<input name="approverSelect1" value="1" type="radio" checked="checked"/>按个人选择审批人
	                  		<input name="approverSelect1" value="2" type="radio"/>按职级选择审批人
                  		</div>
                  		<div class="c-approver__box">
	                  		<sys:treeselect id="oaNotifyRecord1" name="oaNotifyRecordIds1" cssplaceholder="按个人选择审批人" value="${oaNotify.oaNotifyRecordIds}" labelName="oaNotifyRecordNames" labelValue="${oaNotify.oaNotifyRecordNames}"
							title="用户" url="/sys/office/treeData?type=3" cssClass="input-xxlarge required" notAllowSelectParent="true" checked="true"/>
                  		</div>
                  		<div class="c-approver__box" style="display:none;">
	                  		<sys:treeselect id="sysRank1" name="sysRankIds1" cssplaceholder="按职级选择审批人" value="${user.sysrank.id}" labelName="sysrank.name" labelValue="${user.sysrank.name}"
							title="职级" url="/sys/sysRank/rankData?officeId=${fns:getUser().company.id}" cssClass="input-xxlarge required" notAllowSelectParent="true" checked="true"/>
                  		</div> --%>
                	
                	<!-- <label class="form--title form--star" for="">请假天数范围</label>
                	<div>
                		<input class="c-dataScope error required" type="text" id="start1" name="start1" style="width: 50px" />&nbsp;天&nbsp;~~&nbsp;
                		<input class="c-dataScope error required" type="text" id="end1" name="end1" style="width: 50px" />&nbsp;天
                	</div> -->
               <%--  <li class="c-approver" style="display:none;">
                  	<div class="clearfix">
                  		<label class="form--title form--star" for="">二级审批人</label>
                  		<div class="j-approver">
	                  		<input name="approverSelect2" value="1" type="radio" checked="checked"/>按个人选择审批人
	                  		<input name="approverSelect2" value="2" type="radio"/>按职级选择审批人
                		</div>
                		<div class="c-approver__box">
	                  		 <sys:treeselect id="oaNotifyRecord2" name="oaNotifyRecordIds2" cssplaceholder="按个人选择审批人" value="${oaNotify.oaNotifyRecordIds}" labelName="oaNotifyRecordNames" labelValue="${oaNotify.oaNotifyRecordNames}"
							title="用户" url="/sys/office/treeData?type=3" cssClass="input-xxlarge required" notAllowSelectParent="true" checked="true"/>
                  		</div>
                		<div class="c-approver__box" style="display:none;">
	                  		 <sys:treeselect id="oaNotifyRecordB" name="oaNotifyRecordIdsB" cssplaceholder="按职级选择审批人" value="${oaNotify.oaNotifyRecordIds}" labelName="oaNotifyRecordNames" labelValue="${oaNotify.oaNotifyRecordNames}"
							title="用户" url="/sys/office/treeData?type=3" cssClass="input-xxlarge required" notAllowSelectParent="true" checked="true"/>
                  		</div>
                  	</div>
                	<label class="form--title form--star" for="">请假天数范围</label>
                	<input class="c-dataScope error required" type="text" id="start2" name="start2" style="width: 50px" />&nbsp;天&nbsp;~~&nbsp;
                	<input class="c-dataScope error required" type="text" id="end2" name="end2" style="width: 50px" />&nbsp;天
                </li> --%>
                 <li class="c-approver" style="display:none;">
                	<div class="clearfix">
             			<label class="form--title form--star" for="">二级审批人</label>
               			<span class="leave-2th">
               				<!-- 放审批人name的地方 -->
               				<span class="approverName" title=""></span>
               				<!-- 选择审批人的种类（按人设置还是按职级） -->
               				<input class="approverSort"  name="userType2" type="hidden" value=""/>
               				<!-- 放审批人id的地方 -->
               				<input class="approverId" name="oaNotifyRecordIds2" type="hidden" value=""/>
               				<!-- 该审批人的天数范围 -->
               				<input class="dayStart" name="start2" type="hidden" value=""/>
               				<input class="dayEnd" name="end2" type="hidden" value=""/>
               				<button data-type="leave" data-nth="2" type="button" class="j-addApprover">添加</button>
               			</span>
               		</div>
               		<div class="dayRange" style="display:none">
                		<label class="form--title form--star" for="">请假天数范围</label>
	              		<span class="dayStart" ></span>&nbsp;天&nbsp;~~&nbsp;
	              		<span class="dayEnd"></span>&nbsp;天
              		</div>
              	</li>
             <%--    <li class="c-approver" style="display:none;">
                	<div class="clearfix">
                		<label class="form--title form--star" for="">三级审批人</label>
                  		<div class="j-approver">
	                  		<input name="approverSelect3" value="1" type="radio" checked="checked"/>按个人选择审批人
	                  		<input name="approverSelect3" value="2" type="radio"/>按职级选择审批人
                  		</div>
                  		<div class="c-approver__box">
                  			<sys:treeselect id="oaNotifyRecord3" name="oaNotifyRecordIds3" value="${oaNotify.oaNotifyRecordIds}" labelName="oaNotifyRecordNames" cssplaceholder="按个人选择审批人" labelValue="${oaNotify.oaNotifyRecordNames}" title="用户" url="/sys/office/treeData?type=3" cssClass="input-xxlarge required" notAllowSelectParent="true" checked="true"/>
                		</div>
                		<div class="c-approver__box" style="display:none;">
	                  		 <sys:treeselect id="oaNotifyRecordC" name="oaNotifyRecordIdsB" cssplaceholder="按职级选择审批人" value="${oaNotify.oaNotifyRecordIds}" labelName="oaNotifyRecordNames" labelValue="${oaNotify.oaNotifyRecordNames}"
							title="用户" url="/sys/office/treeData?type=3" cssClass="input-xxlarge required" notAllowSelectParent="true" checked="true"/>
                  		</div>
                	</div>
                	<label class="form--title form--star" for="">请假天数范围</label>
                	<input class="c-dataScope error required" onblur="check()" type="text" id="start3" name="start3" style="width: 50px" />&nbsp;天&nbsp;~~&nbsp;
                	<input class="c-dataScope error required" onblur="check()" type="text" id="end3" name="end3" style="width: 50px" />&nbsp;天
                </li> --%>
                <li class="c-approver" style="display:none;">
                	<div class="clearfix">
             			<label class="form--title form--star" for="">三级审批人</label>
               			<span class="leave-3th">
               				<!-- 放审批人name的地方 -->
               				<span class="approverName" title=""></span>
               				<!-- 选择审批人的种类（按人设置还是按职级） -->
               				<input class="approverSort"  name="userType3" type="hidden" value=""/>
               				<!-- 放审批人id的地方 -->
               				<input class="approverId" name="oaNotifyRecordIds3" type="hidden" value=""/>
               				<!-- 该审批人的天数范围 -->
               				<input class="dayStart" name="start3" type="hidden" value=""/>
               				<input class="dayEnd" name="end3" type="hidden" value=""/>
               				<button data-type="leave" data-nth="3" type="button" class="j-addApprover">添加</button>
               			</span>
               		</div>
               		<div class="dayRange" style="display:none">
                		<label class="form--title form--star" for="">请假天数范围</label>
	              		<span class="dayStart" ></span>&nbsp;天&nbsp;~~&nbsp;
	              		<span class="dayEnd"></span>&nbsp;天
              		</div>
              	</li>
                <%-- <li class="c-approver" style="display:none;">
                	<div class="clearfix">
                	<label class="form--title form--star" for="">四级审批人</label>
                  		<sys:treeselect id="oaNotifyRecord4" name="oaNotifyRecordIds4" value="${oaNotify.oaNotifyRecordIds}" labelName="oaNotifyRecordNames" labelValue="${oaNotify.oaNotifyRecordNames}" title="用户" url="/sys/office/treeData?type=3" cssClass="input-xxlarge required" notAllowSelectParent="true" checked="true"/>
                	</div>
                	<label class="form--title form--star" for="">请假天数范围</label>
                	<input class="c-dataScope error required" onblur="check()" type="text" id="start4" name="start4" style="width: 50px" />&nbsp;天&nbsp;~~&nbsp;
                	<input class="c-dataScope error required" onblur="check()" type="text" id="end4" name="end4" style="width: 50px" />&nbsp;天
                </li> --%>
                <li class="c-approver" style="display:none;">
                	<div class="clearfix">
             			<label class="form--title form--star" for="">四级审批人</label>
               			<span class="leave-4th">
               				<!-- 放审批人name的地方 -->
               				<span class="approverName" title=""></span>
               				<!-- 选择审批人的种类（按人设置还是按职级） -->
               				<input class="approverSort"  name="userType4" type="hidden" value=""/>
               				<!-- 放审批人id的地方 -->
               				<input class="approverId" name="oaNotifyRecordIds4" type="hidden" value=""/>
               				<!-- 该审批人的天数范围 -->
               				<input class="dayStart" name="start4" type="hidden" value=""/>
               				<input class="dayEnd" name="end4" type="hidden" value=""/>
               				<button data-type="leave" data-nth="4" type="button" class="j-addApprover">添加</button>
               			</span>
               		</div>
               		<div class="dayRange" style="display:none">
                		<label class="form--title form--star" for="">请假天数范围</label>
	              		<span class="dayStart" ></span>&nbsp;天&nbsp;~~&nbsp;
	              		<span class="dayEnd"></span>&nbsp;天
              		</div>
              	</li>
              </ul>
              	<input type="hidden" name="approvalType" value="leave">
          		<input id="btn" class="m--btn btn1" type="button" onclick="sub()" value="提交" />
          		<input style="display:none;" id="btn" class="m--btn free1" type="button" onclick="free()" value="设置自由审批" />
          		<!-- <button class="m--btn" type="submit">提交</button> -->
            </form>
          </div>
        </div>
        <!--加班部分-->
        <div class="url_overtime" style="display:none;">
          <div class="m__ar__list">
          	<ul class="j-approvalRule2">
              	<li>
              		<label class="form--title form--star" for="">审批规则</label>
                	<input name="rule2" type="radio" value="0" checked="true" style="margin-right:10px;"/>固定审批
                	<input name="rule2" type="radio" value="1" style="margin-right:10px;"/>自由审批
              	</li>
            </ul>
            <form class="j-validateForm2" id="overTimeForm" action="${ctx}/oa/approvalRule/addApprovalRule">
              <ul>
              	<li><label class="j-shenPiClass" for="">是否为节假日</label>
              		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                  <input name="approvalStart" type="radio" value="y" style="margin-right:10px;"/>是  &nbsp;&nbsp;&nbsp;&nbsp;
                  <input name="approvalStart" type="radio" value="n" checked="true" style="margin-right:10px;"/>否
                </li>
                <li class="j-shenPiClass"><label class="form--title form--star" for="">审批级别</label>
                  <input name="approvalProcess" value="1" type="radio" checked="true" style="margin-right:10px;"/>一级审批流
                  <input name="approvalProcess" value="2" type="radio" style="margin:10px;"/>二级审批流
                  <input name="approvalProcess" value="3" type="radio" style="margin:10px;"/>三级审批流
                  <input name="approvalProcess" value="4" type="radio" style="margin:10px;"/>四级审批流
                </li>
              	
               <%--  <li class="c-approver"><label class="form--title form--star" for="">一级审批人</label>
                  <sys:treeselect id="oaNotifyRecord5" name="oaNotifyRecordIds1" value="${oaNotify.oaNotifyRecordIds}" labelName="oaNotifyRecordNames" labelValue="${oaNotify.oaNotifyRecordNames}"
						title="用户" url="/sys/office/treeData?type=3" cssClass="input-xxlarge required" notAllowSelectParent="true" checked="true"/>
                </li> --%>
                <li class="c-approver">
                	<div class="clearfix">
             			<label class="form--title form--star" for="">一级审批人</label>
               			<span class="overTime-1th">
               				<!-- 放审批人name的地方 -->
               				<span class="approverName" title=""></span>
               				<!-- 选择审批人的种类（按人设置还是按职级） -->
               				<input class="approverSort"  name="userType1" type="hidden" value=""/>
               				<!-- 放审批人id的地方 -->
               				<input class="approverId" name="oaNotifyRecordIds1" type="hidden" value=""/>
               				<button data-type="overTime" data-nth="1" type="button" class="j-addApprover">添加</button>
               			</span>
               		</div>
              	</li>
              	 <li class="c-approver" style="display:none;">
                	<div class="clearfix">
             			<label class="form--title form--star" for="">二级审批人</label>
               			<span class="overTime-2th">
               				<!-- 放审批人name的地方 -->
               				<span class="approverName" title=""></span>
               				<!-- 选择审批人的种类（按人设置还是按职级） -->
               				<input class="approverSort"  name="userType2" type="hidden" value=""/>
               				<!-- 放审批人id的地方 -->
               				<input class="approverId" name="oaNotifyRecordIds2" type="hidden" value=""/>
               				<button data-type="overTime" data-nth="2" type="button" class="j-addApprover">添加</button>
               			</span>
               		</div>
              	</li>
              	 <li class="c-approver" style="display:none;">
                	<div class="clearfix">
             			<label class="form--title form--star" for="">三级审批人</label>
               			<span class="overTime-3th">
               				<!-- 放审批人name的地方 -->
               				<span class="approverName" title=""></span>
               				<!-- 选择审批人的种类（按人设置还是按职级） -->
               				<input class="approverSort"  name="userType3" type="hidden" value=""/>
               				<!-- 放审批人id的地方 -->
               				<input class="approverId" name="oaNotifyRecordIds3" type="hidden" value=""/>
               				<button data-type="overTime" data-nth="3" type="button" class="j-addApprover">添加</button>
               			</span>
               		</div>
              	</li>
              	 <li class="c-approver" style="display:none;">
                	<div class="clearfix">
             			<label class="form--title form--star" for="">四级审批人</label>
               			<span class="overTime-4th">
               				<!-- 放审批人name的地方 -->
               				<span class="approverName" title=""></span>
               				<!-- 选择审批人的种类（按人设置还是按职级） -->
               				<input class="approverSort"  name="userType4" type="hidden" value=""/>
               				<!-- 放审批人id的地方 -->
               				<input class="approverId" name="oaNotifyRecordIds4" type="hidden" value=""/>
               				<button data-type="overTime" data-nth="4" type="button" class="j-addApprover">添加</button>
               			</span>
               		</div>
              	</li>
               <%--  <li class="c-approver" style="display:none;"><label class="form--title form--star" for="">二级审批人</label>
                  <sys:treeselect id="oaNotifyRecord6" name="oaNotifyRecordIds2" value="${oaNotify.oaNotifyRecordIds}" labelName="oaNotifyRecordNames" labelValue="${oaNotify.oaNotifyRecordNames}"
						title="用户" url="/sys/office/treeData?type=3" cssClass="input-xxlarge required" notAllowSelectParent="true" checked="true"/>
                </li>
                <li class="c-approver" style="display:none;"><label class="form--title form--star" for="">三级审批人</label>
                  <sys:treeselect id="oaNotifyRecord7" name="oaNotifyRecordIds3" value="${oaNotify.oaNotifyRecordIds}" labelName="oaNotifyRecordNames" labelValue="${oaNotify.oaNotifyRecordNames}"
						title="用户" url="/sys/office/treeData?type=3" cssClass="input-xxlarge required" notAllowSelectParent="true" checked="true"/>
                </li>
                <li class="c-approver" style="display:none;"><label class="form--title form--star" for="">四级审批人</label>
                  <sys:treeselect id="oaNotifyRecord8" name="oaNotifyRecordIds4" value="${oaNotify.oaNotifyRecordIds}" labelName="oaNotifyRecordNames" labelValue="${oaNotify.oaNotifyRecordNames}"
						title="用户" url="/sys/office/treeData?type=3" cssClass="input-xxlarge required" notAllowSelectParent="true" checked="true"/>
                </li> --%>
              </ul>
              <input type="hidden" name="approvalType" value="overTime">
          	  <!-- <button class="m--btn" type="submit">提交</button> -->
          	  <input id="btn" type="button" onclick="sub2()" class="m--btn btn1" value="提交" />
          	  <input id="btn" style="display:none;" type="button" onclick="free2()" class="m--btn free1" value="设置自由审批" />
            </form>
          </div>
        </div>
        <!--申请会议室部分-->
        <div class="url_home" style="position:relative; display:none;">
          <div class="m__ar__list">
          	<ul class="j-approvalRule">
              	<li>
              		<label class="form--title form--star" for="">审批规则</label>
                	<input name="rule3" type="radio" value="0" checked="true" style="margin-right:10px;"/>固定审批
                	<input name="rule3" type="radio" value="1" style="margin-right:10px;"/>自由审批
              	</li>
            </ul>
            <form class="j-validateForm3" id="officeRoomForm" action="${ctx}/oa/approvalRule/addApprovalRule">
              <ul>
                <li class="j-shenPiClass"><label class="form--title form--star" for="">审批级别</label>
                  <input name="approvalProcess" value="1" type="radio" checked="true" style="margin-right:10px;"/>一级审批流
                  <input name="approvalProcess" value="2" type="radio" style="margin:10px;"/>二级审批流
                  <input name="approvalProcess" value="3" type="radio" style="margin:10px;"/>三级审批流
                  <input name="approvalProcess" value="4" type="radio" style="margin:10px;"/>四级审批流
                </li>
               <%--  <li class="c-approver"><label class="form--title form--star" for="">一级审批人</label>
                  <sys:treeselect id="oaNotifyRecord9" name="oaNotifyRecordIds1" value="${oaNotify.oaNotifyRecordIds}" labelName="oaNotifyRecordNames" labelValue="${oaNotify.oaNotifyRecordNames}"
						title="用户" url="/sys/office/treeData?type=3" cssClass="input-xxlarge required error" notAllowSelectParent="true" checked="true"/>
                </li>
                <li class="c-approver" style="display:none;"><label class="form--title form--star" for="">二级审批人</label>
                  <sys:treeselect id="oaNotifyRecord10" name="oaNotifyRecordIds2" value="${oaNotify.oaNotifyRecordIds}" labelName="oaNotifyRecordNames" labelValue="${oaNotify.oaNotifyRecordNames}"
						title="用户" url="/sys/office/treeData?type=3" cssClass="input-xxlarge required error" notAllowSelectParent="true" checked="true"/>
                </li>
                <li class="c-approver" style="display:none;"><label class="form--title form--star" for="">三级审批人</label>
                  <sys:treeselect id="oaNotifyRecord11" name="oaNotifyRecordIds3" value="${oaNotify.oaNotifyRecordIds}" labelName="oaNotifyRecordNames" labelValue="${oaNotify.oaNotifyRecordNames}"
						title="用户" url="/sys/office/treeData?type=3" cssClass="input-xxlarge required" notAllowSelectParent="true" checked="true"/>
                </li>
                <li class="c-approver" style="display:none;"><label class="form--title form--star" for="">四级审批人</label>
                  <sys:treeselect id="oaNotifyRecord12" name="oaNotifyRecordIds4" value="${oaNotify.oaNotifyRecordIds}" labelName="oaNotifyRecordNames" labelValue="${oaNotify.oaNotifyRecordNames}"
						title="用户" url="/sys/office/treeData?type=3" cssClass="input-xxlarge required" notAllowSelectParent="true" checked="true"/>
                </li> --%>
                 <li class="c-approver">
                	<div class="clearfix">
             			<label class="form--title form--star" for="">一级审批人</label>
               			<span class="officeRoom-1th">
               				<!-- 放审批人name的地方 -->
               				<span class="approverName" title=""></span>
               				<!-- 选择审批人的种类（按人设置还是按职级） -->
               				<input class="approverSort"  name="userType1" type="hidden" value=""/>
               				<!-- 放审批人id的地方 -->
               				<input class="approverId" name="oaNotifyRecordIds1" type="hidden" value=""/>
               				<button data-type="officeRoom" data-nth="1" type="button" class="j-addApprover">添加</button>
               			</span>
               		</div>
              	</li>
              	 <li class="c-approver" style="display:none;">
                	<div class="clearfix">
             			<label class="form--title form--star" for="">二级审批人</label>
               			<span class="officeRoom-2th">
               				<!-- 放审批人name的地方 -->
               				<span class="approverName" title=""></span>
               				<!-- 选择审批人的种类（按人设置还是按职级） -->
               				<input class="approverSort"  name="userType2" type="hidden" value=""/>
               				<!-- 放审批人id的地方 -->
               				<input class="approverId" name="oaNotifyRecordIds2" type="hidden" value=""/>
               				<button data-type="officeRoom" data-nth="2" type="button" class="j-addApprover">添加</button>
               			</span>
               		</div>
              	</li>
              	 <li class="c-approver" style="display:none;">
                	<div class="clearfix">
             			<label class="form--title form--star" for="">三级审批人</label>
               			<span class="officeRoom-3th">
               				<!-- 放审批人name的地方 -->
               				<span class="approverName" title=""></span>
               				<!-- 选择审批人的种类（按人设置还是按职级） -->
               				<input class="approverSort"  name="userType3" type="hidden" value=""/>
               				<!-- 放审批人id的地方 -->
               				<input class="approverId" name="oaNotifyRecordIds3" type="hidden" value=""/>
               				<button data-type="officeRoom" data-nth="3" type="button" class="j-addApprover">添加</button>
               			</span>
               		</div>
              	</li>
              	 <li class="c-approver" style="display:none;">
                	<div class="clearfix">
             			<label class="form--title form--star" for="">四级审批人</label>
               			<span class="officeRoom-4th">
               				<!-- 放审批人name的地方 -->
               				<span class="approverName" title=""></span>
               				<!-- 选择审批人的种类（按人设置还是按职级） -->
               				<input class="approverSort"  name="userType4" type="hidden" value=""/>
               				<!-- 放审批人id的地方 -->
               				<input class="approverId" name="oaNotifyRecordIds4" type="hidden" value=""/>
               				<button data-type="officeRoom" data-nth="4" type="button" class="j-addApprover">添加</button>
               			</span>
               		</div>
              	</li>
              </ul>
              <input type="hidden" name="approvalType" value="officeRoom">
          	  <!-- <button class="m--btn" type="submit">提交</button> -->
          	  <input id="btn" type="button" onclick="sub3()" class="m--btn btn1" value="提交" />
          	  <input id="btn" style="display:none;" type="button" onclick="free3()" class="m--btn free1" value="设置自由审批" />
            </form>
          </div>
        </div>
        <!--投影仪部分-->
        <div class="url_projector" style="display:none;">
          <div class="m__ar__list">
          	<ul class="j-approvalRule">
              	<li>
              		<label class="form--title form--star" for="">审批规则</label>
                	<input name="rule4" type="radio" value="0" checked="true" style="margin-right:10px;"/>固定审批
                	<input name="rule4" type="radio" value="1" style="margin-right:10px;"/>自由审批
              	</li>
            </ul>
            <form class="j-validateForm4" id="projectorForm" action="${ctx}/oa/approvalRule/addApprovalRule">
              <ul>
                <li class="j-shenPiClass"><label class="form--title form--star" for="">审批级别</label>
                  <input name="approvalProcess" value="1" type="radio" checked="true" style="margin-right:10px;"/>一级审批流
                  <input name="approvalProcess" value="2" type="radio" style="margin:10px;"/>二级审批流
                  <input name="approvalProcess" value="3" type="radio" style="margin:10px;"/>三级审批流
                  <input name="approvalProcess" value="4" type="radio" style="margin:10px;"/>四级审批流
                </li>
               <%--  <li class="c-approver"><label class="form--title form--star" for="">一级审批人</label>
                  <sys:treeselect id="oaNotifyRecord13" name="oaNotifyRecordIds1" value="${oaNotify.oaNotifyRecordIds}" labelName="oaNotifyRecordNames" labelValue="${oaNotify.oaNotifyRecordNames}"
						title="用户" url="/sys/office/treeData?type=3" cssClass="input-xxlarge required" notAllowSelectParent="true" checked="true"/>
                </li>
                <li class="c-approver" style="display:none;"><label class="form--title form--star" for="">二级审批人</label>
                  <sys:treeselect id="oaNotifyRecord14" name="oaNotifyRecordIds2" value="${oaNotify.oaNotifyRecordIds}" labelName="oaNotifyRecordNames" labelValue="${oaNotify.oaNotifyRecordNames}"
						title="用户" url="/sys/office/treeData?type=3" cssClass="input-xxlarge required" notAllowSelectParent="true" checked="true"/>
                </li>
                <li class="c-approver" style="display:none;"><label class="form--title form--star" for="">三级审批人</label>
                  <sys:treeselect id="oaNotifyRecord15" name="oaNotifyRecordIds3" value="${oaNotify.oaNotifyRecordIds}" labelName="oaNotifyRecordNames" labelValue="${oaNotify.oaNotifyRecordNames}"
						title="用户" url="/sys/office/treeData?type=3" cssClass="input-xxlarge required" notAllowSelectParent="true" checked="true"/>
                </li>
                <li class="c-approver" style="display:none;"><label class="form--title form--star" for="">四级审批人</label>
                  <sys:treeselect id="oaNotifyRecord16" name="oaNotifyRecordIds4" value="${oaNotify.oaNotifyRecordIds}" labelName="oaNotifyRecordNames" labelValue="${oaNotify.oaNotifyRecordNames}"
						title="用户" url="/sys/office/treeData?type=3" cssClass="input-xxlarge required" notAllowSelectParent="true" checked="true"/>
                </li>  --%>
                   <li class="c-approver">
                	<div class="clearfix">
             			<label class="form--title form--star" for="">一级审批人</label>
               			<span class="projector-1th">
               				<!-- 放审批人name的地方 -->
               				<span class="approverName" title=""></span>
               				<!-- 选择审批人的种类（按人设置还是按职级） -->
               				<input class="approverSort"  name="userType1" type="hidden" value=""/>
               				<!-- 放审批人id的地方 -->
               				<input class="approverId" name="oaNotifyRecordIds1" type="hidden" value=""/>
               				<button data-type="projector" data-nth="1" type="button" class="j-addApprover">添加</button>
               			</span>
               		</div>
              	</li>
              	 <li class="c-approver" style="display:none;">
                	<div class="clearfix">
             			<label class="form--title form--star" for="">二级审批人</label>
               			<span class="projector-2th">
               				<!-- 放审批人name的地方 -->
               				<span class="approverName" title=""></span>
               				<!-- 选择审批人的种类（按人设置还是按职级） -->
               				<input class="approverSort"  name="userType2" type="hidden" value=""/>
               				<!-- 放审批人id的地方 -->
               				<input class="approverId" name="oaNotifyRecordIds2" type="hidden" value=""/>
               				<button data-type="projector" data-nth="2" type="button" class="j-addApprover">添加</button>
               			</span>
               		</div>
              	</li>
              	 <li class="c-approver" style="display:none;">
                	<div class="clearfix">
             			<label class="form--title form--star" for="">三级审批人</label>
               			<span class="projector-3th">
               				<!-- 放审批人name的地方 -->
               				<span class="approverName" title=""></span>
               				<!-- 选择审批人的种类（按人设置还是按职级） -->
               				<input class="approverSort"  name="userType3" type="hidden" value=""/>
               				<!-- 放审批人id的地方 -->
               				<input class="approverId" name="oaNotifyRecordIds3" type="hidden" value=""/>
               				<button data-type="projector" data-nth="3" type="button" class="j-addApprover">添加</button>
               			</span>
               		</div>
              	</li>
              	 <li class="c-approver" style="display:none;">
                	<div class="clearfix">
             			<label class="form--title form--star" for="">四级审批人</label>
               			<span class="projector-4th">
               				<!-- 放审批人name的地方 -->
               				<span class="approverName" title=""></span>
               				<!-- 选择审批人的种类（按人设置还是按职级） -->
               				<input class="approverSort"  name="userType4" type="hidden" value=""/>
               				<!-- 放审批人id的地方 -->
               				<input class="approverId" name="oaNotifyRecordIds4" type="hidden" value=""/>
               				<button data-type="projector" data-nth="4" type="button" class="j-addApprover">添加</button>
               			</span>
               		</div>
              	</li>
              </ul>
              <input type="hidden" name="approvalType" value="projector">
          	  <!-- <button class="m--btn" type="submit">提交</button> -->
          	  <input id="btn" type="button" onclick="sub4()" class="m--btn btn1" value="提交" />
          	  <input id="btn" style="display:none;" type="button" onclick="free4()" class="m--btn free1" value="设置自由审批" />
            </form>
          </div>
        </div>
        <!--用章部分-->
        <div class="url_currency" style="display:none;">
          <div class="m__ar__list">
          	<ul class="j-approvalRule">
              	<li>
              		<label class="form--title form--star" for="">审批规则</label>
                	<input name="rule5" type="radio" value="0" checked="true" style="margin-right:10px;"/>固定审批
                	<input name="rule5" type="radio" value="1" style="margin-right:10px;"/>自由审批
              	</li>
            </ul>
            <form class="j-validateForm5" id="currencyForm" action="${ctx}/oa/approvalRule/addApprovalRule">
              <ul>
                <li class="j-shenPiClass"><label class="form--title form--star" for="">审批级别</label>
                  <input name="approvalProcess" value="1" type="radio" checked="true" style="margin-right:10px;"/>一级审批流
                  <input name="approvalProcess" value="2" type="radio" style="margin:10px;"/>二级审批流
                  <input name="approvalProcess" value="3" type="radio" style="margin:10px;"/>三级审批流
                  <input name="approvalProcess" value="4" type="radio" style="margin:10px;"/>四级审批流
                </li>
               <%--  <li class="c-approver"><label class="form--title form--star" for="">一级审批人</label>
                  <sys:treeselect id="oaNotifyRecord17" name="oaNotifyRecordIds1" value="${oaNotify.oaNotifyRecordIds}" labelName="oaNotifyRecordNames" labelValue="${oaNotify.oaNotifyRecordNames}"
						title="用户" url="/sys/office/treeData?type=3" cssClass="input-xxlarge required" notAllowSelectParent="true" checked="true"/>
                </li>
                <li class="c-approver" style="display:none;"><label class="form--title form--star" for="">二级审批人</label>
                  <sys:treeselect id="oaNotifyRecord18" name="oaNotifyRecordIds2" value="${oaNotify.oaNotifyRecordIds}" labelName="oaNotifyRecordNames" labelValue="${oaNotify.oaNotifyRecordNames}"
						title="用户" url="/sys/office/treeData?type=3" cssClass="input-xxlarge required" notAllowSelectParent="true" checked="true"/>
                </li>
                <li class="c-approver" style="display:none;"><label class="form--title form--star" for="">三级审批人</label>
                  <sys:treeselect id="oaNotifyRecord19" name="oaNotifyRecordIds3" value="${oaNotify.oaNotifyRecordIds}" labelName="oaNotifyRecordNames" labelValue="${oaNotify.oaNotifyRecordNames}"
						title="用户" url="/sys/office/treeData?type=3" cssClass="input-xxlarge required" notAllowSelectParent="true" checked="true"/>
                </li>
                <li class="c-approver" style="display:none;"><label class="form--title form--star" for="">四级审批人</label>
                  <sys:treeselect id="oaNotifyRecord20" name="oaNotifyRecordIds4" value="${oaNotify.oaNotifyRecordIds}" labelName="oaNotifyRecordNames" labelValue="${oaNotify.oaNotifyRecordNames}"
						title="用户" url="/sys/office/treeData?type=3" cssClass="input-xxlarge required" notAllowSelectParent="true" checked="true"/>
                </li> --%>
                 <li class="c-approver">
                	<div class="clearfix">
             			<label class="form--title form--star" for="">一级审批人</label>
               			<span class="currency-1th">
               				<!-- 放审批人name的地方 -->
               				<span class="approverName" title=""></span>
               				<!-- 选择审批人的种类（按人设置还是按职级） -->
               				<input class="approverSort"  name="userType1" type="hidden" value=""/>
               				<!-- 放审批人id的地方 -->
               				<input class="approverId" name="oaNotifyRecordIds1" type="hidden" value=""/>
               				<button data-type="currency" data-nth="1" type="button" class="j-addApprover">添加</button>
               			</span>
               		</div>
              	</li>
              	 <li class="c-approver" style="display:none;">
                	<div class="clearfix">
             			<label class="form--title form--star" for="">二级审批人</label>
               			<span class="currency-2th">
               				<!-- 放审批人name的地方 -->
               				<span class="approverName" title=""></span>
               				<!-- 选择审批人的种类（按人设置还是按职级） -->
               				<input class="approverSort"  name="userType2" type="hidden" value=""/>
               				<!-- 放审批人id的地方 -->
               				<input class="approverId" name="oaNotifyRecordIds2" type="hidden" value=""/>
               				<button data-type="currency" data-nth="2" type="button" class="j-addApprover">添加</button>
               			</span>
               		</div>
              	</li>
              	 <li class="c-approver" style="display:none;">
                	<div class="clearfix">
             			<label class="form--title form--star" for="">三级审批人</label>
               			<span class="currency-3th">
               				<!-- 放审批人name的地方 -->
               				<span class="approverName" title=""></span>
               				<!-- 选择审批人的种类（按人设置还是按职级） -->
               				<input class="approverSort"  name="userType3" type="hidden" value=""/>
               				<!-- 放审批人id的地方 -->
               				<input class="approverId" name="oaNotifyRecordIds3" type="hidden" value=""/>
               				<button data-type="currency" data-nth="3" type="button" class="j-addApprover">添加</button>
               			</span>
               		</div>
              	</li>
              	 <li class="c-approver" style="display:none;">
                	<div class="clearfix">
             			<label class="form--title form--star" for="">四级审批人</label>
               			<span class="currency-4th">
               				<!-- 放审批人name的地方 -->
               				<span class="approverName" title=""></span>
               				<!-- 选择审批人的种类（按人设置还是按职级） -->
               				<input class="approverSort"  name="userType4" type="hidden" value=""/>
               				<!-- 放审批人id的地方 -->
               				<input class="approverId" name="oaNotifyRecordIds4" type="hidden" value=""/>
               				<button data-type="currency" data-nth="4" type="button" class="j-addApprover">添加</button>
               			</span>
               		</div>
              	</li>
              </ul>
              <input type="hidden" name="approvalType" value="currency">
          	  <!-- <button class="m--btn" type="submit">提交</button> -->
          	  <input id="btn" type="button" onclick="sub5()" class="m--btn btn1" value="提交" />
          	  <input id="btn" type="button" style="display:none;" onclick="free5()" class="m--btn free1" value="设置自由审批" />
          	  
            </form>
          </div>
        </div>
      </article>
    </div>
  </main>

  <%-- <script src="${ctxStatic}/approvalProcess/js/jquery-3.2.1.js"></script> --%>
  
  <script type="text/javascript">
  	function sub5(){
  		var commonForm = $("#currencyForm").serializeArray();
	  	var forNum = parseInt(commonForm[0].value);
	  	for(var i = 1;i <= forNum*2;i++){
	  		if(commonForm[i].value == ""){
		  		switch(i){
					case 1,2:
						layer.msg("请将第一审批人内容填写完整！");
						return;
					case 3,4:
						layer.msg("请将第二审批人内容填写完整！");
						return;
					case 5,6:
						layer.msg("请将第三审批人内容填写完整！");
						return;
					case 7,8:
						layer.msg("请将第四审批人内容填写完整！");
						return;
					default:
					  	break;
				}
			}
		}
  		$.ajax({
           type: "POST",  
           url:"${ctx}/oa/approvalRule/addApprovalRule",  
           data:$("#currencyForm").serialize(),// 序列化表单值  
           dataType:"json",
           async: false,
           error: function(request) {
               alert("Connection error");  
           },  
           success: function(data) {
			alert("设置成功");
			window.location.href="${ctx}/oa/approvalProcessAdmin/returnPage";
           }
       });
  	}
  		//用章自由审批规则表单提交
  	function free5(){
	  	var commonForm = $("#currencyForm").serializeArray();
		$.ajax({
			type: "POST",  
			url:"${ctx}/oa/approvalRule/freeApprovalRule",  
			data:$("#currencyForm").serialize(),// 序列化表单值    
			dataType:"json",
			async: false,
			error: function(request) {
				alert("Connection error");  
			},  
			success: function(data) {
				alert("设置成功");
				window.location.href="${ctx}/oa/approvalProcessAdmin/returnPage";
			}
		});
	}
  	
  	//投影仪审批规则表单提交
  	function sub4(){
  		var commonForm = $("#projectorForm").serializeArray();
	  	var forNum = parseInt(commonForm[0].value);
	  	for(var i = 1;i <= forNum*2;i++){
	  		if(commonForm[i].value == ""){
		  		switch(i){
					case 1,2:
						layer.msg("请将第一审批人内容填写完整！");
						return;
					case 3,4:
						layer.msg("请将第二审批人内容填写完整！");
						return;
					case 5,6:
						layer.msg("请将第三审批人内容填写完整！");
						return;
					case 7,8:
						layer.msg("请将第四审批人内容填写完整！");
						return;
					default:
					  	break;
				}
			}
		}
  		$.ajax({
           type: "POST",  
           url:"${ctx}/oa/approvalRule/addApprovalRule",  
           data:$("#projectorForm").serialize(),// 序列化表单值  
           dataType:"json",
           async: false,
           error: function(request) {
               alert("Connection error");  
           },  
           success: function(data) {
			alert("设置成功");
			window.location.href="${ctx}/oa/approvalProcessAdmin/returnPage";
           }
       }); 
  	}
  		//投影仪自由审批规则表单提交
  	function free4(){
	  	var commonForm = $("#projectorForm").serializeArray();
		$.ajax({
			type: "POST",  
			url:"${ctx}/oa/approvalRule/freeApprovalRule",  
			data:$("#projectorForm").serialize(),// 序列化表单值    
			dataType:"json",
			async: false,
			error: function(request) {
				alert("Connection error");  
			},  
			success: function(data) {
				alert("设置成功");
				window.location.href="${ctx}/oa/approvalProcessAdmin/returnPage";
			}
		});
	}
  	
  	//会议室审批规则表单提交
  	function sub3(){
  		var commonForm = $("#officeRoomForm").serializeArray();
	  	var forNum = parseInt(commonForm[0].value);
	  	for(var i = 1;i <= forNum*2;i++){
	  		if(commonForm[i].value == ""){
		  		switch(i){
					case 1,2:
						layer.msg("请将第一审批人内容填写完整！");
						return;
					case 3,4:
						layer.msg("请将第二审批人内容填写完整！");
						return;
					case 5,6:
						layer.msg("请将第三审批人内容填写完整！");
						return;
					case 7,8:
						layer.msg("请将第四审批人内容填写完整！");
						return;
					default:
					  	break;
				}
			}
		}
  		$.ajax({
           type: "POST",  
           url:"${ctx}/oa/approvalRule/addApprovalRule",  
           data:$("#officeRoomForm").serialize(),// 序列化表单值  
           dataType:"json",
           async: false,
           error: function(request) {
               alert("Connection error");  
           },  
           success: function(data) {
			alert("设置成功");
			window.location.href="${ctx}/oa/approvalProcessAdmin/returnPage";
           }
       });
  	}
  		//会议室自由审批规则表单提交
  	function free3(){
	  	var commonForm = $("#officeRoomForm").serializeArray();
		$.ajax({
			type: "POST",  
			url:"${ctx}/oa/approvalRule/freeApprovalRule",  
			data:$("#officeRoomForm").serialize(),// 序列化表单值    
			dataType:"json",
			async: false,
			error: function(request) {
				alert("Connection error");  
			},  
			success: function(data) {
				alert("设置成功");
				window.location.href="${ctx}/oa/approvalProcessAdmin/returnPage";
			}
		});
	}
  	
  	//加班审批规则表单提交
  	function sub2(){
  		var overTimeForm = $("#overTimeForm").serializeArray();
	  	var forNum = parseInt(overTimeForm[1].value);
	  	for(var i = 2;i <= forNum*2+1;i++){
	  		if(overTimeForm[i].value == ""){
		  		switch(i){
					case 2,3:
						layer.msg("请将第一审批人内容填写完整！");
						return;
					case 4,5:
						layer.msg("请将第二审批人内容填写完整！");
						return;
					case 6,7:
						layer.msg("请将第三审批人内容填写完整！");
						return;
					case 8,9:
						layer.msg("请将第四审批人内容填写完整！");
						return;
					default:
					  	break;
				}
			}
		}
  		
  		$.ajax({
           type: "POST",  
           url:"${ctx}/oa/approvalRule/addApprovalRule",  
           data:$("#overTimeForm").serialize(),// 序列化表单值  
           dataType:"json",
           async: false,
           error: function(request) {
               alert("Connection error");  
           },  
           success: function(data) {
			alert("设置成功");
			window.location.href="${ctx}/oa/approvalProcessAdmin/returnPage";
           }
       }); 
  	}
  	//加班自由审批规则表单提交
  		function free2(){
	  	var overTimeForm = $("#overTimeForm").serializeArray();
		$.ajax({
			type: "POST",  
			url:"${ctx}/oa/approvalRule/freeApprovalRule",  
			data:$("#overTimeForm").serialize(),// 序列化表单值    
			dataType:"json",
			async: false,
			error: function(request) {
				alert("Connection error");  
			},  
			success: function(data) {
				alert("设置成功");
				window.location.href="${ctx}/oa/approvalProcessAdmin/returnPage";
			}
		});
	}
  	//请假审批规则表单提交
  	function sub(){
	  	var leaveForm = $("#leaveForm").serializeArray();
	  	var forNum = parseInt(leaveForm[0].value);
	  	for(var i = 1;i <= forNum*4;i++){
	  		if(leaveForm[i].value == ""){
		  		switch(i){
					case 1,2,3,4:
						layer.msg("请将第一审批人内容填写完整！");
						return;
					case 5,6,7,8:
						layer.msg("请将第二审批人内容填写完整！");
						return;
					case 9,10,11,12:
						layer.msg("请将第三审批人内容填写完整！");
						return;
					case 13,14,15,16:
						layer.msg("请将第四审批人内容填写完整！");
						return;
					default:
					  	break;
				}
			}
		}
		$.ajax({
			type: "POST",  
			url:"${ctx}/oa/approvalRule/addApprovalRule",  
			data:$("#leaveForm").serialize(),// 序列化表单值  
			dataType:"json",
			async: false,
			error: function(request) {
				alert("Connection error");  
			},  
			success: function(data) {
				alert("设置成功");
				window.location.href="${ctx}/oa/approvalProcessAdmin/returnPage";
			}
		});
	}
  	
  	//请假自由审批规则表单提交
  	function free(){
	  	var leaveForm = $("#leaveForm").serializeArray();
		$.ajax({
			type: "POST",  
			url:"${ctx}/oa/approvalRule/freeApprovalRule",  
			data:$("#leaveForm").serialize(),// 序列化表单值  
			dataType:"json",
			async: false,
			error: function(request) {
				alert("Connection error");  
			},  
			success: function(data) {
				alert("设置成功");
				window.location.href="${ctx}/oa/approvalProcessAdmin/returnPage";
			}
		});
	}
  	
  	/* function check1(){
  		var v1 = $("#id1").val();
  		if(v1 == null || v1 == ''){
  			$("#id2").html("<img src='${ctxStatic}/images/close_hover.png'><span style='color:red'>必填信息</span>");
  			$("#id2").show();
  			$("#btn").attr("disabled",true);
  		}else{
  			$("#id2").hide();
  			$("#btn").attr("disabled",false);
  		}
  	} */
  </script>
  
  
  <!--<script src="js/laydate/laydate.js"></script>-->
  <script src="${ctxStatic}/approvalProcess/js/layer/layer.js"></script>
  <script>
    $(".j-aside").on("click","li",function(){
      $(this).addClass("hover").siblings("li").removeClass("hover");
      $("."+$(this).attr("data-url")).show().siblings("div").hide();
    });
    $(".j-shenPiClass").on("change","input",function(){
//      console.log(this);
//      console.log($(this)[0].checked);
      var inputArr = $(this).parent("li").children("input");
      var liArr = $(this).parent("li").next("li");
      liArr.push($(liArr).next("li")[0]);
      liArr.push($(liArr).next("li").next("li")[0]);
      liArr.push($(liArr).next("li").next("li").next("li")[0]);
      /*console.log(liArr);*/
      for(let i = 0;i < inputArr.length;i++){
        if(inputArr[i].checked){
          switch(i)
          {
            case 0:
              $(liArr[0]).show().find("input[type='text']").val("").prop("disabled",false);
              $(liArr[1]).hide().find("input[type='text']").val("").prop("disabled",true);
              $(liArr[2]).hide().find("input[type='text']").val("").prop("disabled",true);
              $(liArr[3]).hide().find("input[type='text']").val("").prop("disabled",true);
              break;
            case 1:
              $(liArr[0]).show().find("input[type='text']").val("").prop("disabled",false);
              $(liArr[1]).show().find("input[type='text']").val("").prop("disabled",false);
              $(liArr[2]).hide().find("input[type='text']").val("").prop("disabled",true);
              $(liArr[3]).hide().find("input[type='text']").val("").prop("disabled",true);
              break;
            case 2:
              $(liArr[0]).show().find("input[type='text']").val("").prop("disabled",false);
              $(liArr[1]).show().find("input[type='text']").val("").prop("disabled",false);
              $(liArr[2]).show().find("input[type='text']").val("").prop("disabled",false);
              $(liArr[3]).hide().find("input[type='text']").val("").prop("disabled",true);
              break;
            case 3:
              $(liArr[0]).show().find("input[type='text']").val("").prop("disabled",false);
              $(liArr[1]).show().find("input[type='text']").val("").prop("disabled",false);
              $(liArr[2]).show().find("input[type='text']").val("").prop("disabled",false);
              $(liArr[3]).show().find("input[type='text']").val("").prop("disabled",false);
              break;
            default:
              break;
          }
        }
      }
    });
    /* 选择按个人还是按职级审批 */
    $(".j-approver").on("click","input",function(){
    //console.log($(this).parent().parent());
    	if($(this).val() == "1"){
    		var approverBox = $(this).parent().parent().children(".c-approver__box");
    		
    		approverBox.eq(0).show().find("input[type='text']").prop("disabled",false);
    		approverBox.eq(1).hide().find("input[type='text']").val("").prop("disabled",true);
    	}else if($(this).val() == "2"){
    		var approverBox = $(this).parent().parent().children(".c-approver__box");
    		approverBox.eq(1).show().find("input[type='text']").prop("disabled",false);
    		approverBox.eq(0).hide().find("input[type='text']").val("").prop("disabled",true);    		
    	}
    });
	/* 右侧滑动弹框的js代码 */
	$(".j-addApprover").click(function(){
		var type = $(this).attr("data-type");
		var nth = $(this).attr("data-nth");
		layer.open({
			type    : 2,
			offset  : 'r',
			area    : ['500px', '100%'],
			title   : $(this).parent().siblings('.form--title').text(),
			shade   : 0.1,
			anim   : -1,
			skin:'layer-anim-07',
			move    : false,
			content : '${ctx}/oa/approvalRule/addApprover?type='+type+'&nth='+nth,
			cancel  : function (index,layero) {
			    $(layero).animate({
			      left :$(layero).offset().left + $(layero).width()
			    }, 300, function () {
			      layer.close(index);
			    });
			    return false;
			}
		});
	});
	/* 提交时如果为空弹窗提示 */
	function errorAlert(othis){
		console.log(othis);
	}
	/* 设置审批规则 */
	$(".j-approvalRule").on("change","input[type='radio']",function(){
		var formEle = $(this).closest(".m__ar__list").children("form");
		if($(this).val() == "0"){
			formEle.children("ul").show();
			formEle.find(".btn1").show();
			formEle.find(".free1").hide();
		}else{
			formEle.children("ul").hide();
			formEle.find(".btn1").hide();
			formEle.find(".free1").show();
		}
	});
	/* 加班设置审批规则 */
	$(".j-approvalRule2").on("change","input[type='radio']",function(){
		var formEle = $(this).closest(".m__ar__list").children("form");
		var shenPiEle = formEle.find(".j-shenPiClass");
		var cApprEle = formEle.find(".c-approver");
		if($(this).val() == "0"){
			shenPiEle.eq(1).show();
			formEle.find("input[name='approvalProcess']").each(function(){
				if($(this).prop("checked") == true){
					for(var i = 0;i < $(this).val();i++){
						cApprEle.eq(i).show();
					}
				}
			});
			formEle.find(".btn1").show();
			formEle.find(".free1").hide();
		}else{
			shenPiEle.eq(1).hide();
			cApprEle.hide();
			formEle.find(".btn1").hide();
			formEle.find(".free1").show();
		}
	});
  </script>
</body>
</html>