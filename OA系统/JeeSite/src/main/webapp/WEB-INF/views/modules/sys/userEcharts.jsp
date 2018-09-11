1<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>部门树形展示</title>
	<meta name="decorator" content="default"/>
</head>
<body>
<shiro:hasPermission name="sys:office:userEcharts">
	<div style="padding:20px;width:100%;height:100%;">   
        <!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->  
               <div id="main" style="width: 1104px;height:464px;">  
               
               </div>  
             
      <div id='main_1' style="position: relative;height:15px;width: 100%;color:#A52A2A"></div>  
            
 </div> 
</shiro:hasPermission> 
<%-- <shiro:lacksRole name="sys:office:userEcharts">
<div>
	对不起您没有权限访问！
</div>
</shiro:lacksRole> --%>
 <script type="text/javascript">
    	$(document).ready(function() { 
    			$.ajax({ 
type: "GET", 
url: "${ctx}/sys/office/treeJson", 
success: function(data) {
	var zNodes=data; 
	data=getData(zNodes);
    var myChart = echarts.init(document.getElementById('main'), 'macarons');  
    createTreeV(myChart,"组织架构图",zNodes);
} 
}); 
}); 

 
    </script> 
</body>
</html>