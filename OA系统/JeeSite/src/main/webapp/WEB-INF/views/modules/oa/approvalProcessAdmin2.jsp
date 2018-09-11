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
	<style type="text/css">
		.c-approver{
			position:relative;
		}
		.c-approver input{
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
		.c-mainWrap{
			height:-moz-calc(100% - 38px);
			height:-webkit-calc(100% - 38px);
			height:calc(100% - 38px);
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
		/* 页面中展示的列表样式 */
		.main .m__article{
			width:-moz-calc(100% - 180px);
			width:-webkit-calc(100% - 180px);
			width:calc(100% - 180px);
			padding:5px;
		}
		.c-table{
			width:100%;
			border: 1px solid #ddd;
		    border-collapse: separate;
		    border-left: 0;
		    -webkit-border-radius: 4px;
		    -moz-border-radius: 4px;
		    border-radius: 4px;
		    margin-bottom: 8px;
    		background-color: #fdfdfd;
    		border-collapse: collapse;
    		border-spacing: 0;
		}
		.c-table th, .c-table td {
		    border-left: 1px solid #ddd;
		}
		.c-table th, .c-table td {
		    padding: 4px 5px;
		}
		.c-table tbody tr{
			border-top:1px solid #ddd;
		}
		.c-table thead tr{
			text-align:left;
			white-space: nowrap;
		    background-color: #f5f5f5;
		    background-image: -moz-linear-gradient(top,#ffffff,#f5f5f5);
		    background-image: -ms-linear-gradient(top,#ffffff,#f5f5f5);
		    background-image: -webkit-gradient(linear, 0 0, 0 100%, from(#FAFAF9), to(#f5f5f5));
		    background-image: -webkit-linear-gradient(top,#ffffff,#f5f5f5);
		    background-image: -o-linear-gradient(top,#ffffff,#f5f5f5);
		    background-image: linear-gradient(top,#ffffff,#f5f5f5);
		    background-repeat: repeat-x;
		}
		.c-table a{
    		color: #2fa4e7;
		}
		/* 审批人装不下...显示 */
		.c-person{
			max-width:400px;
			white-space:nowrap;
			overflow:hidden;
			text-overflow:ellipsis;
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
		<li><a href="/jeesite/a/oa/approvalProcessAdmin/returnPage/">设置审批</a></li>
		<li class="active"><a href="/jeesite/a/oa/approvalRule/alreadyProcess?type=leave">已设置审批</a></li>
  	</ul>
    <div class="c-mainWrap" style="display:flex;">
      <aside class="m__aside j-aside">
        <ul>
          <c:if test="${type=='leave'}">
	          <li data-url="url_leave" class="hover" onclick="myProcess('leave')"><img src="${ctxStatic}/approvalProcess/img/leave.png" alt=""/>请假</li>
	          <li data-url="url_overtime" onclick="myProcess('overTime')"><img src="${ctxStatic}/approvalProcess/img/overtime.png" alt=""/>加班</li>
	          <li data-url="url_home" onclick="myProcess('officeRoom')"><img src="${ctxStatic}/approvalProcess/img/home.png" alt=""/>会议室</li>
	          <li data-url="url_projector" onclick="myProcess('projector')"><img src="${ctxStatic}/approvalProcess/img/projector.png" alt=""/>投影仪</li>
	          <li data-url="url_currency" onclick="myProcess('currency')"><img src="${ctxStatic}/approvalProcess/img/common.png" alt=""/>用章</li>
	        </c:if>
	        <c:if test="${type=='overTime'}">
	          <li data-url="url_leave" onclick="myProcess('leave')"><img src="${ctxStatic}/approvalProcess/img/leave.png" alt=""/>请假</li>
	          <li data-url="url_overtime" class="hover" onclick="myProcess('overTime')"><img src="${ctxStatic}/approvalProcess/img/overtime.png" alt=""/>加班</li>
	          <li data-url="url_home" onclick="myProcess('officeRoom')"><img src="${ctxStatic}/approvalProcess/img/home.png" alt=""/>会议室</li>
	          <li data-url="url_projector" onclick="myProcess('projector')"><img src="${ctxStatic}/approvalProcess/img/projector.png" alt=""/>投影仪</li>
	          <li data-url="url_currency" onclick="myProcess('currency')"><img src="${ctxStatic}/approvalProcess/img/common.png" alt=""/>用章</li>
	        </c:if>
	        <c:if test="${type=='officeRoom'}">
	          <li data-url="url_leave" onclick="myProcess('leave')"><img src="${ctxStatic}/approvalProcess/img/leave.png" alt=""/>请假</li>
	          <li data-url="url_overtime" onclick="myProcess('overTime')"><img src="${ctxStatic}/approvalProcess/img/overtime.png" alt=""/>加班</li>
	          <li data-url="url_home" class="hover" onclick="myProcess('officeRoom')"><img src="${ctxStatic}/approvalProcess/img/home.png" alt=""/>会议室</li>
	          <li data-url="url_projector" onclick="myProcess('projector')"><img src="${ctxStatic}/approvalProcess/img/projector.png" alt=""/>投影仪</li>
	          <li data-url="url_currency" onclick="myProcess('currency')"><img src="${ctxStatic}/approvalProcess/img/common.png" alt=""/>用章</li>
	        </c:if>
	        <c:if test="${type=='projector'}">
	          <li data-url="url_leave" onclick="myProcess('leave')"><img src="${ctxStatic}/approvalProcess/img/leave.png" alt=""/>请假</li>
	          <li data-url="url_overtime" onclick="myProcess('overTime')"><img src="${ctxStatic}/approvalProcess/img/overtime.png" alt=""/>加班</li>
	          <li data-url="url_home" onclick="myProcess('officeRoom')"><img src="${ctxStatic}/approvalProcess/img/home.png" alt=""/>会议室</li>
	          <li data-url="url_projector" class="hover" onclick="myProcess('projector')"><img src="${ctxStatic}/approvalProcess/img/projector.png" alt=""/>投影仪</li>
	          <li data-url="url_currency" onclick="myProcess('currency')"><img src="${ctxStatic}/approvalProcess/img/common.png" alt=""/>用章</li>
	        </c:if>
	        <c:if test="${type=='currency'}">
	          <li data-url="url_leave" onclick="myProcess('leave')"><img src="${ctxStatic}/approvalProcess/img/leave.png" alt=""/>请假</li>
	          <li data-url="url_overtime" onclick="myProcess('overTime')"><img src="${ctxStatic}/approvalProcess/img/overtime.png" alt=""/>加班</li>
	          <li data-url="url_home" onclick="myProcess('officeRoom')"><img src="${ctxStatic}/approvalProcess/img/home.png" alt=""/>会议室</li>
	          <li data-url="url_projector" onclick="myProcess('projector')"><img src="${ctxStatic}/approvalProcess/img/projector.png" alt=""/>投影仪</li>
	          <li data-url="url_currency" class="hover" onclick="myProcess('currency')"><img src="${ctxStatic}/approvalProcess/img/common.png" alt=""/>用章</li>
	        </c:if>
      </aside>
      <article class="m__article">
        <!--请假部分-->
        <div class="url_leave">
          <div class="m__ar__list">
            <c:if test="${type=='leave'}">
            <table class="c-table">
	        	<thead>
        		<tr>
        			<th>请假天数范围</th>
        			<th>审批级数</th>
        			<th>对应审批人</th>
        			<th>审批人级别</th>
        			<th>操作</th>
        		</tr>
	        	</thead>   
	        	<tbody>
	        	<c:forEach items="${list}" var="rule" varStatus="st">
		        	<tr>
		        		<c:if test="${rule.approvalRole=='1'}">
			        		<td>${rule.approvalStart}~${rule.approvalEnd}天</td>
			        		<td>${rule.approvalProcess}级审批流程</td>
			        		<c:if test="${empty rule.approvalPerson and not empty rule.approvalRank}">
			        			<td><div class="c-person">${rule.approvalRank}</div></td>		
			        		</c:if>
			        		<c:if test="${not empty rule.approvalPerson and empty rule.approvalRank}">
			        			<td><div class="c-person">${rule.approvalPerson}</div></td>
			        		</c:if>
			        		<td>${rule.approvalRole}级审批人</td>
		        		</c:if>
		        		<c:if test="${rule.approvalRole=='2'}">
			        		<td>${rule.approvalStart}~${rule.approvalEnd}天</td>
			        		<td>${rule.approvalProcess}级审批流程</td>
			        		<c:if test="${empty rule.approvalPerson and not empty rule.approvalRank}">
			        			<td><div class="c-person">${rule.approvalRank}</div></td>		
			        		</c:if>
			        		<c:if test="${not empty rule.approvalPerson and empty rule.approvalRank}">
			        			<td><div class="c-person">${rule.approvalPerson}</div></td>
			        		</c:if>
			        		<td>${rule.approvalRole}级审批人</td>
		        		</c:if>
		        		<c:if test="${rule.approvalRole=='3'}">
			        		<td>${rule.approvalStart}~${rule.approvalEnd}天</td>
			        		<td>${rule.approvalProcess}级审批流程</td>
			        	<c:if test="${empty rule.approvalPerson and not empty rule.approvalRank}">
			        			<td><div class="c-person">${rule.approvalRank}</div></td>		
			        		</c:if>
			        		<c:if test="${not empty rule.approvalPerson and empty rule.approvalRank}">
			        			<td><div class="c-person">${rule.approvalPerson}</div></td>
			        		</c:if>
			        		<td>${rule.approvalRole}级审批人</td>
		        		</c:if>
		        		<c:if test="${rule.approvalRole=='4'}">
			        		<td>${rule.approvalStart}~${rule.approvalEnd}天</td>
			        		<td>${rule.approvalProcess}级审批流程</td>
			        		<c:if test="${empty rule.approvalPerson and not empty rule.approvalRank}">
			        			<td><div class="c-person">${rule.approvalRank}</div></td>		
			        		</c:if>
			        		<c:if test="${not empty rule.approvalPerson and empty rule.approvalRank}">
			        			<td><div class="c-person">${rule.approvalPerson}</div></td>
			        		</c:if>
			        		<td>${rule.approvalRole}级审批人</td>
		        		</c:if>
		        		<c:if test="${rule.approvalRole=='0'}">
			        		<td>自由审批</td>
			        		<td>自由审批</td>
			        		<td>自由审批</td>	
			        		<td>自由审批</td>	
		        		</c:if>
		        		
		        		<!-- <a class="j-applyHome m--btn">修改</a> -->
		        		<c:if test="${rule.approvalRole=='0'}">
		        		<td>无</td>
		        		</c:if>
		        		<c:if test="${rule.approvalRole!='0'}">
		        		<td><a onclick="showLayer('${type}','${rule.approvalRole}','${rule.approvalRank}')" class="m--btn">修改</a>
		        		</td>
		        		</c:if>
		        	</tr>
	        	</c:forEach>
	        	</tbody> 
	        </table>
	        </c:if>
	        <c:if test="${type=='overTime'}">
	        <table class="c-table">
	        	<thead>
        		<tr>
        			<th>是否是节假日</th>
        			<th>审批级数</th>
        			<th>对应审批人</th>
        			<th>审批人级别</th>
        			<th>操作</th>
        		</tr>
	        	</thead>   
	        	<tbody>
	        	<c:forEach items="${list}" var="rule" varStatus="st">
		        	<tr>
		        		<c:if test="${rule.approvalRole=='1'}">
			        		<td>
			        			<c:if test="${rule.approvalStart=='y'}">是</c:if>
			        			<c:if test="${rule.approvalStart=='n'}">否</c:if>
			        		</td>
			        		<td>${rule.approvalProcess}级审批流程</td>
			        		<c:if test="${empty rule.approvalPerson and not empty rule.approvalRank}">
			        			<td><div class="c-person">${rule.approvalRank}</div></td>		
			        		</c:if>
			        		<c:if test="${not empty rule.approvalPerson and empty rule.approvalRank}">
			        			<td><div class="c-person">${rule.approvalPerson}</div></td>
			        		</c:if>
			        		<td>${rule.approvalRole}级审批人</td>
		        		</c:if>
		        		<c:if test="${rule.approvalRole=='2'}">
			        		<td>
			        			<c:if test="${rule.approvalStart=='y'}">是</c:if>
			        			<c:if test="${rule.approvalStart=='n'}">否</c:if>
			        		</td>
			        		<td>${rule.approvalProcess}级审批流程</td>
			        		<c:if test="${empty rule.approvalPerson and not empty rule.approvalRank}">
			        			<td><div class="c-person">${rule.approvalRank}</div></td>		
			        		</c:if>
			        		<c:if test="${not empty rule.approvalPerson and empty rule.approvalRank}">
			        			<td><div class="c-person">${rule.approvalPerson}</div></td>
			        		</c:if>
			        		<td>${rule.approvalRole}级审批人</td>
		        		</c:if>
		        		<c:if test="${rule.approvalRole=='3'}">
			        		<td>
			        			<c:if test="${rule.approvalStart=='y'}">是</c:if>
			        			<c:if test="${rule.approvalStart=='n'}">否</c:if>
			        		</td>
			        		<td>${rule.approvalProcess}级审批流程</td>
			        		<c:if test="${empty rule.approvalPerson and not empty rule.approvalRank}">
			        			<td><div class="c-person">${rule.approvalRank}</div></td>		
			        		</c:if>
			        		<c:if test="${not empty rule.approvalPerson and empty rule.approvalRank}">
			        			<td><div class="c-person">${rule.approvalPerson}</div></td>
			        		</c:if>
			        		<td>${rule.approvalRole}级审批人</td>
		        		</c:if>
		        		<c:if test="${rule.approvalRole=='4'}">
			        		<td>
			        			<c:if test="${rule.approvalStart=='y'}">是</c:if>
			        			<c:if test="${rule.approvalStart=='n'}">否</c:if>
			        		</td>
			        		<td>${rule.approvalProcess}级审批流程</td>
			        		<c:if test="${empty rule.approvalPerson and not empty rule.approvalRank}">
			        			<td><div class="c-person">${rule.approvalRank}</div></td>		
			        		</c:if>
			        		<c:if test="${not empty rule.approvalPerson and empty rule.approvalRank}">
			        			<td><div class="c-person">${rule.approvalPerson}</div></td>
			        		</c:if>
			        		<td>${rule.approvalRole}级审批人</td>
		        		</c:if>
		        		<c:if test="${rule.approvalRole=='0'}">
		        			<td>
		        				<c:if test="${rule.approvalStart=='y'}">是</c:if>
			        			<c:if test="${rule.approvalStart=='n'}">否</c:if>
		        			</td>
			        		<td>自由审批</td>
			        		<td>自由审批</td>	
			        		<td>自由审批</td>	
		        		</c:if>
		        		<c:if test="${rule.approvalRole=='0'}">
		        		<td>无</td>
		        		</c:if>
		        		<c:if test="${rule.approvalRole!='0'}">
		        		<td>
		        		<a onclick="showLayer2('${type}','${rule.approvalRole}','${rule.approvalStart}','${rule.approvalRank}')" class="m--btn">修改</a>
		        		</td>
		        		</c:if>
		        	</tr>
	        	</c:forEach>
	        	</tbody> 
	        </table>
	        </c:if>
	        <c:if test="${type=='officeRoom'}">
	        <table class="c-table">
	        	<thead>
        		<tr>
        			<th>审批级数</th>
        			<th>对应审批人</th>
        			<th>审批人级别</th>
        			<th>操作</th>
        		</tr>
	        	</thead>   
	        	<tbody>
	        	<c:forEach items="${list}" var="rule" varStatus="st">
		        	<tr>
		        		<c:if test="${rule.approvalRole=='1'}">
			        		<td>${rule.approvalProcess}级审批流程</td>
			        		<c:if test="${empty rule.approvalPerson and not empty rule.approvalRank}">
			        			<td><div class="c-person">${rule.approvalRank}</div></td>		
			        		</c:if>
			        		<c:if test="${not empty rule.approvalPerson and empty rule.approvalRank}">
			        			<td><div class="c-person">${rule.approvalPerson}</div></td>
			        		</c:if>
			        		<td>${rule.approvalRole}级审批人</td>
		        		</c:if>
		        		<c:if test="${rule.approvalRole=='2'}">
			        		<td>${rule.approvalProcess}级审批流程</td>
			        		<c:if test="${empty rule.approvalPerson and not empty rule.approvalRank}">
			        			<td><div class="c-person">${rule.approvalRank}</div></td>		
			        		</c:if>
			        		<c:if test="${not empty rule.approvalPerson and empty rule.approvalRank}">
			        			<td><div class="c-person">${rule.approvalPerson}</div></td>
			        		</c:if>
			        		<td>${rule.approvalRole}级审批人</td>
		        		</c:if>
		        		<c:if test="${rule.approvalRole=='3'}">
			        		<td>${rule.approvalProcess}级审批流程</td>
			        		<c:if test="${empty rule.approvalPerson and not empty rule.approvalRank}">
			        			<td><div class="c-person">${rule.approvalRank}</div></td>		
			        		</c:if>
			        		<c:if test="${not empty rule.approvalPerson and empty rule.approvalRank}">
			        			<td><div class="c-person">${rule.approvalPerson}</div></td>
			        		</c:if>
			        		<td>${rule.approvalRole}级审批人</td>
		        		</c:if>
		        		<c:if test="${rule.approvalRole=='4'}">
			        		<td>${rule.approvalProcess}级审批流程</td>
			        		<c:if test="${empty rule.approvalPerson and not empty rule.approvalRank}">
			        			<td><div class="c-person">${rule.approvalRank}</div></td>		
			        		</c:if>
			        		<c:if test="${not empty rule.approvalPerson and empty rule.approvalRank}">
			        			<td><div class="c-person">${rule.approvalPerson}</div></td>
			        		</c:if>
			        		<td>${rule.approvalRole}级审批人</td>
		        		</c:if>
		        		<c:if test="${rule.approvalRole=='0'}">
			        		<td>自由审批</td>
			        		<td>自由审批</td>
			        		<td>自由审批</td>	
		        		</c:if>
		        		<c:if test="${rule.approvalRole=='0'}">
		        		<td>无</td>
		        		</c:if>
		        		<c:if test="${rule.approvalRole!='0'}">
		        		<td>
		        		<a onclick="showLayer('${type}','${rule.approvalRole}','${rule.approvalRank}')" class="m--btn">修改</a>
		        		</td>
		        		</c:if>
		        	</tr>
	        	</c:forEach>
	        	</tbody> 
	        </table>
	        </c:if>
	        <c:if test="${type=='projector'}">
	        <table class="c-table">
	        	<thead>
        		<tr>
        			<th>审批级数</th>
        			<th>对应审批人</th>
        			<th>审批人级别</th>
        			<th>操作</th>
        		</tr>
	        	</thead>   
	        	<tbody>
	        	<c:forEach items="${list}" var="rule" varStatus="st">
		        	<tr>
		        		<c:if test="${rule.approvalRole=='1'}">
			        		<td>${rule.approvalProcess}级审批流程</td>
			        		<c:if test="${empty rule.approvalPerson and not empty rule.approvalRank}">
			        			<td><div class="c-person">${rule.approvalRank}</div></td>		
			        		</c:if>
			        		<c:if test="${not empty rule.approvalPerson and empty rule.approvalRank}">
			        			<td><div class="c-person">${rule.approvalPerson}</div></td>
			        		</c:if>
			        		<td>${rule.approvalRole}级审批人</td>
		        		</c:if>
		        		<c:if test="${rule.approvalRole=='2'}">
			        		<td>${rule.approvalProcess}级审批流程</td>
			        		<c:if test="${empty rule.approvalPerson and not empty rule.approvalRank}">
			        			<td><div class="c-person">${rule.approvalRank}</div></td>		
			        		</c:if>
			        		<c:if test="${not empty rule.approvalPerson and empty rule.approvalRank}">
			        			<td><div class="c-person">${rule.approvalPerson}</div></td>
			        		</c:if>
			        		<td>${rule.approvalRole}级审批人</td>
		        		</c:if>
		        		<c:if test="${rule.approvalRole=='3'}">
			        		<td>${rule.approvalProcess}级审批流程</td>
			        		<c:if test="${empty rule.approvalPerson and not empty rule.approvalRank}">
			        			<td><div class="c-person">${rule.approvalRank}</div></td>		
			        		</c:if>
			        		<c:if test="${not empty rule.approvalPerson and empty rule.approvalRank}">
			        			<td><div class="c-person">${rule.approvalPerson}</div></td>
			        		</c:if>
			        		<td>${rule.approvalRole}级审批人</td>
		        		</c:if>
		        		<c:if test="${rule.approvalRole=='4'}">
			        		<td>${rule.approvalProcess}级审批流程</td>
			        		<c:if test="${empty rule.approvalPerson and not empty rule.approvalRank}">
			        			<td><div class="c-person">${rule.approvalRank}</div></td>		
			        		</c:if>
			        		<c:if test="${not empty rule.approvalPerson and empty rule.approvalRank}">
			        			<td><div class="c-person">${rule.approvalPerson}</div></td>
			        		</c:if>
			        		<td>${rule.approvalRole}级审批人</td>
		        		</c:if>
		        		<c:if test="${rule.approvalRole=='0'}">
			        		<td>自由审批</td>
			        		<td>自由审批</td>
			        		<td>自由审批</td>	
		        		</c:if>
		        		<c:if test="${rule.approvalRole=='0'}">
		        		<td>无</td>
		        		</c:if>
		        		<c:if test="${rule.approvalRole!='0'}">
		        		<td>
		        		<a onclick="showLayer('${type}','${rule.approvalRole}','${rule.approvalRank}')" class="m--btn">修改</a>
		        		</td>
		        		</c:if>
		        	</tr>
	        	</c:forEach>
	        	</tbody> 
	        </table>
	        </c:if>
	        <c:if test="${type=='currency'}">
	        <table class="c-table">
	        	<thead>
        		<tr>
        			<th>审批级数</th>
        			<th>对应审批人</th>
        			<th>审批人级别</th>
        			<th>操作</th>
        		</tr>
	        	</thead>   
	        	<tbody>
	        	<c:forEach items="${list}" var="rule" varStatus="st">
		        	<tr>
		        		<c:if test="${rule.approvalRole=='1'}">
			        		<td>${rule.approvalProcess}级审批流程</td>
			        		<c:if test="${empty rule.approvalPerson and not empty rule.approvalRank}">
			        			<td><div class="c-person">${rule.approvalRank}</div></td>		
			        		</c:if>
			        		<c:if test="${not empty rule.approvalPerson and empty rule.approvalRank}">
			        			<td><div class="c-person">${rule.approvalPerson}</div></td>
			        		</c:if>
			        		<td>${rule.approvalRole}级审批人</td>
		        		</c:if>
		        		<c:if test="${rule.approvalRole=='2'}">
			        		<td>${rule.approvalProcess}级审批流程</td>
			        		<c:if test="${empty rule.approvalPerson and not empty rule.approvalRank}">
			        			<td><div class="c-person">${rule.approvalRank}</div></td>		
			        		</c:if>
			        		<c:if test="${not empty rule.approvalPerson and empty rule.approvalRank}">
			        			<td><div class="c-person">${rule.approvalPerson}</div></td>
			        		</c:if>
			        		<td>${rule.approvalRole}级审批人</td>
		        		</c:if>
		        		<c:if test="${rule.approvalRole=='3'}">
			        		<td>${rule.approvalProcess}级审批流程</td>
			        		<c:if test="${empty rule.approvalPerson and not empty rule.approvalRank}">
			        			<td><div class="c-person">${rule.approvalRank}</div></td>		
			        		</c:if>
			        		<c:if test="${not empty rule.approvalPerson and empty rule.approvalRank}">
			        			<td><div class="c-person">${rule.approvalPerson}</div></td>
			        		</c:if>
			        		<td>${rule.approvalRole}级审批人</td>
		        		</c:if>
		        		<c:if test="${rule.approvalRole=='4'}">
			        		<td>${rule.approvalProcess}级审批流程</td>
			        		<c:if test="${empty rule.approvalPerson and not empty rule.approvalRank}">
			        			<td><div class="c-person">${rule.approvalRank}</div></td>		
			        		</c:if>
			        		<c:if test="${not empty rule.approvalPerson and empty rule.approvalRank}">
			        			<td><div class="c-person">${rule.approvalPerson}</div></td>
			        		</c:if>
			        		<td>${rule.approvalRole}级审批人</td>
		        		</c:if>
		        		<c:if test="${rule.approvalRole=='0'}">
			        		<td>自由审批</td>
			        		<td>自由审批</td>
			        		<td>自由审批</td>	
		        		</c:if>
		        		<c:if test="${rule.approvalRole=='0'}">
		        		<td>无</td>
		        		</c:if>
		        		<c:if test="${rule.approvalRole!='0'}">
		        		<td>
		        		<a onclick="showLayer('${type}','${rule.approvalRole}','${rule.approvalRank}')" class="m--btn">修改</a>
		        		</td>
		        		</c:if>
		        	</tr>
	        	</c:forEach>
	        	</tbody> 
	        </table>
	        </c:if>
          </div>
        </div>
        <!--加班部分-->
        <!-- <div class="url_overtime" style="display:none;">
          <div class="m__ar__list">
            
          </div>
        </div>
        申请会议室部分
        <div class="url_home" style="position:relative; display:none;">
          <div class="m__ar__list">
           
          </div>
        </div>
        投影仪部分
        <div class="url_projector" style="display:none;">
          <div class="m__ar__list">
            
          </div>
        </div>
        用章部分
        <div class="url_currency" style="display:none;">
          <div class="m__ar__list">
            
          </div>
        </div> -->
      </article>
    </div>
  </main>
   <script src="${ctxStatic}/approvalProcess/js/layer/layer.js"></script>
  <script src="${ctxStatic}/approvalProcess/js/laydate/laydate.js"></script>
  <script type="text/javascript">
  	function myProcess(type){
  		console.log("----"+type);
  		window.location.href="${ctx}/oa/approvalRule/alreadyProcess?type="+type;
  	}
  </script>
  <script>
  	function showLayer(aa,approvalRole,approvalRank){
  		layer.open({
	        type: 2,//2是iframe层
	        title: '修改审批人',//标题
	        shadeClose: true,//点击遮罩层也关闭
	        id: '1',//同时只能有一个弹窗
	        scrollbar: false,//弹窗时浏览器不能滚动
	        area: ['800px', '95%'],//弹窗宽高
	        //弹窗点击×的回调函数
	        /* cancel: function(index, layero){
	         layer.close(index);
	         },*/
	        content: '${ctx}/oa/approvalRule/modifyPage?aa='+aa+'&approvalRole='+approvalRole+'&approvalRank='+approvalRank //这个引号里写要访问页面的路径
		});
  	}
  	function showLayer2(aa,approvalRole,isOver,approvalRank){
  		layer.open({
	        type: 2,//2是iframe层
	        title: '修改审批人',//标题
	        shadeClose: true,//点击遮罩层也关闭
	        id: '1',//同时只能有一个弹窗
	        scrollbar: false,//弹窗时浏览器不能滚动
	        area: ['800px', '95%'],//弹窗宽高
	        //弹窗点击×的回调函数
	        /* cancel: function(index, layero){
	         layer.close(index);
	         },*/
	        content: '${ctx}/oa/approvalRule/modifyPage?aa='+aa+'&approvalRole='+approvalRole+'&isOver='+isOver+'&approvalRank='+approvalRank //这个引号里写要访问页面的路径
		});
  	}
  	
  	/* 左侧侧边栏点击效果 */
    /* $(".j-aside").on("click","li",function(){
      $(this).addClass("hover").siblings("li").removeClass("hover");
      $("."+$(this).attr("data-url")).show().siblings("div").hide();
    }); */
    $(function(){	
    	$(".c-person").each(function(){
    		$(this).attr("title",$(this).text());
    	});
    });
    /*修改审批人*/
    /* $(".j-applyHome").on("click",function() {
    	layer.open({
	        type: 2,//2是iframe层
	        title: '申请会议室',//标题
	        shadeClose: true,//点击遮罩层也关闭
	        id: '1',//同时只能有一个弹窗
	        scrollbar: false,//弹窗时浏览器不能滚动
	        area: ['800px', '95%'],//弹窗宽高
	        content: '${ctx}/oa/approvalRule/modifyPage'//这个引号里写要访问页面的路径
		});
    }); */
  </script>
</body>
</html>