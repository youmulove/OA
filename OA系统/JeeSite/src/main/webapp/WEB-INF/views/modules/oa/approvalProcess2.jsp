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
	<link rel="stylesheet" href="${ctxStatic}/viewer/viewer.css">
	<script src="${ctxStatic}/viewer/viewer.js"></script>
    <script src="${ctxStatic}/viewer/main.js"></script>
	<script>
		/* $(function(){
			$(".j-validateForm1").validate();
			$(".j-validateForm2").validate();
			$(".j-validateForm3").validate();
			$(".j-validateForm4").validate();
			$(".j-validateForm5").validate();
		}); */
	</script>

	<style>
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
			/* height:-moz-calc(100% - 38px);
			height:-webkit-calc(100% - 38px);
			height:calc(100% - 38px); */
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
		.c-table tbody>tr:nth-child(even){
		    background-color: #F5F5F5;
		}
		/* 弹窗样式 */
		.c-details{
			width:560px;
			margin:15px; auto;
		}
		.c-details li{
			line-height:25px;
			border:1px solid #bbb;
			border-bottom:none;
		}
		.c-details li:last-child{
			border-bottom:1px solid #bbb;
		}
		.c-details li:after{
			content:"";
			display:table;
			clear:both;
		}
		.c-details li>b{
			/* display:inline-block; */
			float:left;
			width:120px;
			text-align:right;
			margin-right:15px;
			color:#555;
		}
		.c-details li>span{
			/* display:inline-block; */
			float:left;
			width:-moz-calc(100% - 135px);
			width:-webkit-calc(100% - 135px);
			width:calc(100% - 135px);
		}
	</style>
</head>
<body>
  <main class="main clearfix" style="width:100%; height:100%;">
    <!--<header class="m__header">
      审批流程控制
    </header>-->
    <ul class="nav nav-tabs clearfix">
		<li><a href="/jeesite/a/oa/approvalProcess/returnPage">发起申请</a></li>
		<li class="active"><a href="/jeesite/a/act/task/myProcess?type=leave">我发起的</a></li>
  	</ul>
    <div class="c-mainWrap" style="display:flex">
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
        </ul>
      </aside>
      <article class="m__article">
        <!--请假部分-->
        <div class="url_leave">
          <div class="m__ar__list">
            <table class="c-table">
	        	<thead>
        		<tr>
        			<th>标题</th>
        			<th>审批结果</th>
        			<th>流程名称</th>
        			<th>创建时间</th>
        			<th>操作</th>
        		</tr>
	        	</thead>   
	        	<tbody>
	        		<c:forEach items="${list}" var="var">
	        			<%-- <c:if test="${type=='leave' || type=='overTime'}"> --%>
	        				<tr>
		        				<td><a onclick="myDetail('${var.id}','${type}')">${var.yuliu5}</a></td>
		        				<td>
		        					<c:if test="${var.yuliu4=='y'}">审批通过</c:if>
		        					<c:if test="${var.yuliu4=='n'}">审批未通过</c:if>
		        					<c:if test="${var.yuliu4=='z'}">审批中</c:if>
		        				</td>
		        				<td>
		        					<c:if test="${not empty var.auditFouId}">四级审批流</c:if>
		        					<c:if test="${empty var.auditFouId}">
			        					<c:if test="${not empty var.auditThrId}">三级审批流</c:if>
		        					</c:if>
		        					<c:if test="${empty var.auditFouId && empty var.auditThrId}">
			        					<c:if test="${not empty var.auditSecId}">二级审批流</c:if>
		        					</c:if>
		        					<c:if test="${empty var.auditFouId && empty var.auditThrId && empty var.auditSecId}">
			        					<c:if test="${not empty var.auditFirId}">一级审批流</c:if>
		        					</c:if>
		        				</td>
		        				<td><fmt:formatDate value="${var.applyTime}" type="both"/></td>
		        				<td><a onclick="myDetail('${var.id}','${type}')">详情</a>
		        				<%-- <c:if test="${type=='officeRoom' && var.yuliu4=='y'}"> --%>
		        				<c:if test="${type=='officeRoom'}">
		        					<c:if test="${var.delFlag=='0'}">
		        						<a href="" data-href="${var.id}" onclick="deleteOffice(event)">取消</a>
		        					</c:if>
		        					<c:if test="${var.delFlag=='1'}">
		        						<span style="color:red;" data-href="${var.id}">已取消</span>
		        					</c:if>
		        						<%--<a href="${ctx}/act/task/deleteOffice?taskId=${var.id}&reason=" onclick="return promptx('取消','取消原因',this.href);">取消</a> --%>
		        				</c:if>
		        				</td>
	        				</tr>
	        			<%-- </c:if> --%>
	        		</c:forEach>
	        	
	        	
	        	<%-- <c:forEach items="${list}" var="act" varStatus="st">
	        	<c:choose>
		  			<c:when test="${act.histTask.processInstanceId eq list[st.index-1].histTask.processInstanceId}">
		  			</c:when>
		  			<c:otherwise>
			        	<tr>
			        		<td><a onclick="myDetail('${act.histTask.id}','${type}')">${act.title}</a></td>
			        		<td><a target="_blank" href="${pageContext.request.contextPath}/act/diagram-viewer?processDefinitionId=${act.histTask.processDefinitionId}&processInstanceId=${act.histTask.processInstanceId}">
			        			<c:if test="${act.histTask.name == '退回'}">审批未通过</c:if>
			        			<c:if test="${act.histTask.name == '发起申请'}">审批中</c:if>  
			        			</a></td>
			        		<td>
			        		<c:if test="${act.assigneeName=='run'}">
								<a target="_blank" href="${pageContext.request.contextPath}/act/diagram-viewer?processDefinitionId=${task.processDefinitionId}&processInstanceId=${task.processInstanceId}">审批中</a>
							</c:if>
							<c:if test="${act.assigneeName=='successEnd'}">
								<a target="_blank" href="${pageContext.request.contextPath}/act/diagram-viewer?processDefinitionId=${task.processDefinitionId}&processInstanceId=${task.processInstanceId}">审批通过</a>
							</c:if>
							<c:if test="${act.assigneeName=='rejectEnd'}">
								<a target="_blank" href="${pageContext.request.contextPath}/act/diagram-viewer?processDefinitionId=${task.processDefinitionId}&processInstanceId=${task.processInstanceId}">审批未通过</a>
							</c:if>
							</td>
			        		<td>${act.procDef.name}</td>
			        		<td><fmt:formatDate value="${act.histTask.createTime}" type="both"/></td>
			        		<!-- <td><a class="j-details" href="#">详情</a></td> -->
			        		<td><a onclick="myDetail('${act.histTask.id}','${type}')">详情</a></td>
			        	</tr>
		  			</c:otherwise>
		  		</c:choose>	
	        	</c:forEach> --%>
	        	
	        	</tbody> 
	        </table>
          </div>
        </div>
        <!--加班部分-->
        <div class="url_overtime" style="display:none;">
          <div class="m__ar__list">
            
          </div>
        </div>
        <!--申请会议室部分-->
        <div class="url_home" style="position:relative; display:none;">
          <div class="m__ar__list">
          </div>
        </div>
        <!--投影仪部分-->
        <div class="url_projector" style="display:none;">
          <div class="m__ar__list">
            
          </div>
        </div>
		<!--用章部分-->
        <div class="url_currency" style="display:none;">
          <div class="m__ar__list">
            
          </div>
        </div>
      </article>
    </div>
  </main>
  <!-- <div class="c-details">
  	<ul>
  		<li>
  			<b>任务名称：</b>
  			<span>何旭的请假申请</span>
  		</li>
  		<li>
  			<b>申请人：</b>
  			<span>何旭</span>
  		</li>
  		<li>
  			<b>请假理由：</b>
  			<span>有事有事有事有事有事有事有事有事有事有事有事有事有事有事有事有事有事有事有事有事有事有事有事有事有事有事有事有事有事有事有事</span>
  		</li>
  		<li>
  			<b>请假天数：</b>
  			<span>2</span>
  		</li>
  		<li>
  			<b>一级审批人意见：</b>
  			<span>同意</span>
  		</li>
  		<li>
  			<b>二级审批人意见：</b>
  			<span>同意</span>
  		</li>
  		<li>
  			<b>三级审批人意见：</b>
  			<span>同意</span>
  		</li>
  		<li>
  			<b>四级审批人意见：</b>
  			<span>不同意</span>
  		</li>
  	</ul>
  </div> -->
  <script src="${ctxStatic}/approvalProcess/js/layer/layer.js"></script>
  <script type="text/javascript">
  	function myProcess(type){
  		console.log("----"+type);
  		window.location.href="/jeesite/a/act/task/myProcess?type="+type;
  	}
  	
	function myDetail(taskId,type){
    	$.ajax({
    		url:"${ctx}/act/task/myProcessDetail",
    		type:"post",
    		dataType:"json",
    		data:{taskId,type},
    		success:function(ret){
    			console.log(ret)
    			var htmlStr = "";
		    	htmlStr += '<div class="c-details">';
		    	htmlStr += '<ul><li>';
		    	htmlStr += '<b>任务名称：</b>';
		    	htmlStr += '<span>'+ret.yuliu5+'</span>';
		    	htmlStr += '</li><li><b>申请人：</b>';
		    	if(ret.applyName==null){
		    	htmlStr += '<span>'+ret.applyUserid+'</span></li>';
		    	}else{
		    	htmlStr += '<span>'+ret.applyName+'</span></li>';
		    	}
		    	if(type=="leave"){
			    	htmlStr += '<li><b>请假理由：</b>';
			    	htmlStr += '<span>'+ret.reason+'</span>';
			    	htmlStr += '</li><li><b>请假时间：</b>';
			    	htmlStr += '<span>'+format(ret.startTime)+'~~'+format(ret.endTime)+'</span>';
			    	htmlStr += '</li><li><b>请假天数：</b>';
			    	htmlStr += '<span>'+ret.hours+'</span></li>';
		    	}
		    	if(type=="overTime"){
			    	htmlStr += '<li><b>加班事由：</b>';
			    	htmlStr += '<span>'+ret.reason+'</span>';
			    	htmlStr += '</li><li><b>加班时间：</b>';
			    	//htmlStr += '<span>'+ret.startendTime+'</span>';
			    	htmlStr += '<span>'+format(ret.startTime)+'~~'+format(ret.endTime)+'</span>';
			    	htmlStr += '</li><li><b>加班时长：</b>';
			    	htmlStr += '<span>'+ret.hours+'小时</span></li>';
		    	}
		    	if(type=="officeRoom"){
			    	htmlStr += '<li><b>申请事由：</b>';
			    	htmlStr += '<span>'+ret.reason+'</span>';
			    	htmlStr += '</li><li><b>使用时间：</b>';
			    	//htmlStr += '<span>'+ret.startendTime+'</span>';
			    	htmlStr += '<span>'+format(ret.startTime)+'~~'+format(ret.endTime)+'</span>';
			    	htmlStr += '</li><li><b>使用时长：</b>';
			    	htmlStr += '<span>'+ret.hours+'小时</span></li>';
		    	}
		    	if(type=="projector"){
			    	htmlStr += '<li><b>申请事由：</b>';
			    	htmlStr += '<span>'+ret.reason+'</span>';
			    	htmlStr += '</li><li><b>使用时间：</b>';
			    	//htmlStr += '<span>'+ret.startendTime+'</span>';
			    	htmlStr += '<span>'+format(ret.startTime)+'~~'+format(ret.endTime)+'</span>';
			    	htmlStr += '</li><li><b>使用时长：</b>';
			    	htmlStr += '<span>'+ret.hours+'小时</span></li>';
		    	}
		    	if(type=="currency"){
		    		htmlStr += '<li><b>用章事宜：</b>';
			    	htmlStr += '<span>'+ret.applyItem+'</span>';
			    	htmlStr += '<li><b>内容：</b>';
			    	htmlStr += '<span>'+ret.reason+'</span>';
			    	htmlStr += '<li><b>用章类型：</b>';
			    	htmlStr += '<span>'+ret.selectChapters+'</span>';
			    	htmlStr += '<li><b>用章份数：</b>';
			    	htmlStr += '<span>'+ret.chaptersNumber+'</span>';
			    	htmlStr += '<li class="docs-pictures"><b>盖章文件：</b>';
			    	if(ret.stampFiles == undefined || ret.stampFiles.split("|").length == 1){
			    		htmlStr += '<img onclick="bigImages()" style="max-width:100px;max-height:100px;border:0;padding:3px;" data-original=\"'+ret.stampFiles+'\" src=\"'+ret.stampFiles+'\">';
			    	}else{
						var aaa = ret.stampFiles.split("|");
			    		for(var i = 1;i < aaa.length;i++){
			    			htmlStr += '<img onclick="bigImages()" style="max-width:100px;max-height:100px;border:0;padding:3px;" data-original=\"'+aaa[i]+'\" src=\"'+aaa[i]+'\">';
			    		}
			    	}
			    	htmlStr += '</li><li><b>起止时间：</b>';
			    	//htmlStr += '<span>'+ret.startendTime+'</span></li>';
			    	htmlStr += '<span>'+format(ret.startTime)+'~~'+format(ret.endTime)+'</span>';
		    	}
		    	if(ret.auditFirId != null){
			    	htmlStr += '<li><b>一级审批人意见：</b>';
			    	if(ret.auditFirInfo == null){
			    		htmlStr += '<span></span>';
			    	}else{
			    		htmlStr += '<span>'+ret.auditFirInfo+'</span>';
			    	}
		    	}
		    	if(ret.auditSecId != null){
		    		htmlStr += '</li><li><b>二级审批人意见：</b>';
		    		if(ret.auditSecInfo == null){
		    			htmlStr += '<span></span></li>';
		    		}else{
				    	htmlStr += '<span>'+ret.auditSecInfo+'</span></li>';
		    		}
		    	}
		    	if(ret.auditThrId != null){
		    		htmlStr += '<li><b>三级审批人意见：</b>';
		    		if(ret.auditThrInfo == null){
		    			htmlStr += '<span></span></li>';
		    		}else{
					    htmlStr += '<span>'+ret.auditThrInfo+'</span></li>';
		    		}
		    	}
		    	if(ret.auditFouId){
		    		htmlStr += '<li style="border-bottom:1px solid #bbb;"><b>四级审批人意见：</b>';
		    		if(ret.auditFouInfo == null){
		    			htmlStr += '<span></span></li>';
		    		}else{
					    htmlStr += '<span>'+ret.auditFouInfo+'</span></li>';
		    		}
		    	}
			    htmlStr += '</ul></div>';
		    	
		    	layer.open({
			        type: 1,
			        title: '审批详情',//标题
			        shadeClose: true,//点击遮罩层也关闭
			        id: '1',//同时只能有一个弹窗
			        scrollbar: false,//弹窗时浏览器不能滚动
			        area: ['600px', '95%'],//弹窗宽高
			         btn: ['确认'],
			        //弹窗点击×的回调函数
			        /* cancel: function(index, layero){
			         layer.close(index);
			         },*/
			        content: htmlStr//这个引号里写要访问页面的路径
				});
    		}
    	});
    }
    //格式化日期
	function format(date){
		var date = new Date(date);
  		var dateStr = date.getFullYear()+'-'+(date.getMonth()+1)+'-'+date.getDate()+' '+date.getHours()+':'+date.getMinutes()+':'+date.getSeconds();
		return dateStr;
	}
	</script>
  <script type="text/javascript">
    $(".j-aside").on("click","li",function(){
      $(this).addClass("hover").siblings("li").removeClass("hover");
      $("."+$(this).attr("data-url")).show().siblings("div").hide();
    });
    /*详情*/
    //$(".m__ar__list").on("click",".j-details",function() {
    function deleteOffice(ev){
    	var otaskId = $(ev.target).attr("data-href")	
    	ev.preventDefault();
	    layer.confirm('确认取消吗？', {
		  btn: ['确认','取消'] //按钮
		}, function(){
		  $.ajax({
		  	url:"${ctx}/act/task/deleteOffice",
		  	data:{"taskId":otaskId},
		  	type:"post",
		  	dataType:"json",
		  	success:function(result){
		  		location.href = "${ctx}/act/task/myProcess?type=officeRoom";
		  	}
		  });
		}/* , function(){
		  layer.msg('也可以这样', {
		    time: 20000, //20s后自动关闭
		    btn: ['明白了', '知道了']
		  });
		} */);
    }
    
    
    
    
   
  </script>
</body>
</html>