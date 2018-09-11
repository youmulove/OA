<%@ page language="java" contentType="text/html; charset=GB2312"
    pageEncoding="GB2312"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<HTML><HEAD><TITLE></TITLE>
<META http-equiv=Content-Type content="text/html; charset=GB2312">
<LINK href="${ctxStatic}/weboffice/style.css" type=text/css rel=stylesheet>
<SCRIPT src="${ctxStatic}/weboffice/main.js" type=text/javascript></SCRIPT>
<!-- --------------------=== 调用Weboffice初始化方法 ===--------------------- -->
<SCRIPT language=javascript event=NotifyCtrlReady for=WebOffice1>
/****************************************************
*
*	在装载完Weboffice(执行<object>...</object>)
*	控件后执行 "WebOffice1_NotifyCtrlReady"方法
*
****************************************************/
    //WebOffice_Event_Flash("NotifyCtrlReady");
	//WebOffice1_NotifyCtrlReady()
</SCRIPT>

<SCRIPT language=javascript event=NotifyWordEvent(eventname) for=WebOffice1>
	//WebOffice_Event_Flash("NotifyWordEvent");
	//WebOffice1_NotifyWordEvent(eventname);
 
</SCRIPT>

<SCRIPT language=javascript event=NotifyToolBarClick(iIndex) for=WebOffice1>
	//WebOffice_Event_Flash("NotifyToolBarClick");
	//WebOffice1_NotifyToolBarClick(iIndex);
</SCRIPT>

<SCRIPT language=javascript>
/****************************************************
*
*		控件初始化WebOffice方法
*
****************************************************/
function WebOffice1_NotifyCtrlReady() {
	document.all.WebOffice1.SetWindowText("授权XX(可通过接口自定义)", 0);
	document.all.WebOffice1.OptionFlag |= 128;
	
	// 新建文档
	//document.all.WebOffice1.LoadOriginalFile("", "doc");
	//spnWebOfficeInfo.innerText="----   您电脑上安装的WebOffice版本为:V" + document.all.WebOffice1.GetOcxVersion() +"\t\t\t本实例是根据版本V6044编写";
}
var flag=false;
function menuOnClick(id){
	var id=document.getElementById(id);
	var dis=id.style.display;
	if(dis!="none"){
		id.style.display="none";
		
	}else{
		id.style.display="block";
	}
}
/****************************************************
*
*		接收office事件处理方法
*
****************************************************/
var vNoCopy = 0;
var vNoPrint = 0;
var vNoSave = 0;
var vClose=0;
function no_copy(){
	vNoCopy = 1;
}
function yes_copy(){
	vNoCopy = 0;
}


function no_print(){
	vNoPrint = 1;
}
function yes_print(){
	vNoPrint = 0;
}


function no_save(){
	vNoSave = 1;
}
function yes_save(){
	vNoSave = 0;
}
function EnableClose(flag){
 vClose=flag;
}
function CloseWord(){
	
  document.all.WebOffice1.CloseDoc(0); 
}

function WebOffice1_NotifyWordEvent(eventname) {
	if(eventname=="DocumentBeforeSave"){
		if(vNoSave){
			document.all.WebOffice1.lContinue = 0;
			alert("此文档已经禁止保存");
		}else{
			document.all.WebOffice1.lContinue = 1;
		}
	}else if(eventname=="DocumentBeforePrint"){
		if(vNoPrint){
			document.all.WebOffice1.lContinue = 0;
			alert("此文档已经禁止打印");
		}else{
			document.all.WebOffice1.lContinue = 1;
		}
	}else if(eventname=="WindowSelectionChange"){
		if(vNoCopy){
			document.all.WebOffice1.lContinue = 0;
			//alert("此文档已经禁止复制");
		}else{
			document.all.WebOffice1.lContinue = 1;
		}
	}else   if(eventname =="DocumentBeforeClose"){
	    if(vClose==0){
	    	document.all.WebOffice1.lContinue=0;
	    } else{
	    	//alert("word");
		    document.all.WebOffice1.lContinue = 1;
		  }
 }
	//alert(eventname); 
}
function dd(){

	document.all.WebOffice1.FullScreen=0;

}</SCRIPT>
<LINK href="${ctxStatic}/weboffice/style.css" type=text/css rel=stylesheet>
<META content="MSHTML 6.00.2900.5921" name=GENERATOR></HEAD>
<BODY style="BACKGROUND: #ccc" onunload="return window_onunload()">
<CENTER>
<DIV 
style="PADDING-RIGHT: 0px; PADDING-LEFT: 0px; BACKGROUND: #fff; PADDING-BOTTOM: 0px; MARGIN: -10px 0px 0px; WIDTH: 1024px; PADDING-TOP: 10px; HEIGHT: 750px" 
align=center>
<FORM name=myform>
<TABLE class=TableBlock width="90%">
  <TBODY>
  <TR class=TableHeader>
    <TD colSpan=2 valign=middle>
    	Tips:请在鼠标光标处盖章
    	<INPUT class=btn onclick="return SaveFileDoc('${filePath}','doc')" type=button value=提交> 
    </TD>
	</tr>
</TBODY></TABLE><BR>
<TABLE class=TableBlock width="90%">
  <TBODY>
  <TR>
    <TD class=leftTableData vAlign=top width="15%">
      <!-- <DIV class=menuItem onclick="menuOnClick('chc')">文档操作 </DIV>
      <DIV id=chc style="DISPLAY: none">---------------=== 该处文件格式的value不可以自定义 ===-------------------------<SELECT 
      id=doctype size=1 name=doctype> <OPTION value=doc selected>Office</OPTION> 
        <OPTION value=wps>WPS</OPTION></SELECT> <INPUT class=btn id=CreateFile onclick=newDoc() type=button value=新建文档 name=CreateFile> 
		<INPUT class=btn onclick="return docOpen()" type=button value=打开本地文件 name=button> 
		<INPUT class=btn id=showPrint onclick=showPrintDialog() type=button value=打印 name=CreateFile4> 
		<INPUT class=btn id=zhiPrints onclick=zhiPrint() type=button value=直接打印 name=zhiPrints> 
		<INPUT class=btn id=CreateFile2 onclick=newSave() type=button value=保存 name=CreateFile2> 
		<INPUT class=btn id=CreateFile3 onclick=SaveAsTo() type=button value=另存为 name=CreateFile3> 
 
      </DIV>
      <DIV class=menuItem onclick="menuOnClick('docSafe')">文档安全设置 </DIV>
      <DIV id=docSafe style="DISPLAY: none">保护密码： 
      	<INPUT style="WIDTH: 74px" value=Password name=docPwd> 
       	<INPUT class=btn onclick="return ProtectFull()" type=button value=保护文档> 
       	<INPUT class=btn onclick="return UnProtect()" type=button value=解除保护 name=button3> 
		<INPUT class=btn onclick="return notPrint()" type=button value=禁用打印> 
		<INPUT class=btn onclick="return okPrint()" type=button value=启用打印 name=button3> 
		<INPUT class=btn onclick="return notSave()" type=button value=禁止保存 name=button10> 
		<INPUT class=btn onclick="return okSave()" type=button value=允许保存 name=button32> 
		<INPUT class=btn onclick="return notCopy()" type=button value=禁止复制 name=button11> 
		<INPUT class=btn onclick="return okCopy()" type=button value=允许复制 name=button33> 
		<INPUT class=btn onclick="return notDrag()" type=button value=禁止拖动 name=but11> 
		<INPUT class=btn onclick="return okDrag()" type=button value=允许拖动 name=but33> 
      </DIV>
      <DIV class=menuItem onclick="menuOnClick('recension')">修订操作 </DIV>
      <DIV id=recension style="DISPLAY: none"><INPUT style="WIDTH: 74px" 
      maxLength=10 value=Test name=UserName> <INPUT class=btn onclick="return SetUserName()" type=button value=设置用户 name=button2> 
		<INPUT class=btn onclick="return ProtectRevision()" type=button value=修订文档 name=button4> 
		<INPUT class=btn onclick="return ExitRevisions()" type=button value=退出修订 name=button42> 
		<INPUT class=btn onclick="return ShowRevisions()" type=button value=显示修订 name=button5> 
		<INPUT class=btn onclick="return UnShowRevisions()" type=button value=隐藏修订 name=button6> 
		<INPUT class=btn onclick="return AcceptAllRevisions()" type=button value=接受所有修订 name=button7> 
		<INPUT class=btn onclick="return unAcceptAllRevisions()" type=button value=拒绝所有修订 name=button72> 
		<INPUT class=btn onclick="return GetRevAllInfo()" type=button value=获取修订信息 name=button922> 
      </DIV>
      <DIV class=menuItem onclick="menuOnClick('markset')">书签操作 </DIV>
      <DIV id=markset style="DISPLAY: none"><INPUT class=btn onclick="return addBookmark()" type=button value=设置书签 name=button8> 
		<INPUT class=btn onclick="return gaizhang()" type=button value=盖章 name=button8> 
      </DIV>
      <DIV class=menuItem onclick="menuOnClick('off07menu')">菜单设置 </DIV>
      <DIV id=off07menu style="DISPLAY: none">
	      <INPUT class=btn onclick="return hideAll('hideall','','','')" type=button value=隐藏全部 name=button12222>
	      <INPUT class=btn onclick="return hideAll('showmenu','','','')" type=button value=显示全部 name=button12222>
	      <INPUT class=btn onclick="return hideAll('','','','')" type=button value=隐藏功能区 name=button12222>
	      <INPUT class=btn onclick="return hideAll('show','','','')" type=button value=显示功能区 name=button12222>
	      <INPUT class=btn onclick="return beginMenu_onclick()" type=button value=隐藏开始 name=button12222> 
		<INPUT class=btn onclick="return insertMenu_onclick()" type=button value=隐藏插入 name=button12322> 
		<INPUT class=btn onclick="return pageMenu_onclick()" type=button value=隐藏页面布局 name=button12422> 
		<INPUT class=btn onclick="return adducMenu_onclick()" type=button value=隐藏引用 name=button12522> 
		<INPUT class=btn onclick="return emailMenu_onclick()" type=button value=隐藏邮件 name=button12422> 
		<INPUT class=btn onclick="return checkMenu_onclick()" type=button value=隐藏审阅 name=button12522> 
		<INPUT class=btn onclick="return viewMenu_onclick()" type=button value=隐藏视图 name=button125222> 
		<INPUT class=btn onclick="return empolderMenu_onclick()" type=button value=隐藏开发工具 name=button124222> 
		<INPUT class=btn onclick="return loadMenu_onclick()" type=button value=隐藏加载项 name=button125222> 
		<INPUT class=btn onclick="return allHideMenu_onclick()" type=button value=隐藏全部 name=button1242222> 
		<INPUT class=btn onclick="return nullityCopy_onclick()" type=button value=复制无效 name=button12422222> 
		<INPUT class=btn onclick="return nullityAffix_onclick()" type=button value=粘贴无效 name=button12422223> 
		<INPUT class=btn onclick="return affixCopy_onclick()" type=button value=恢复 name=button1252222> 
      </DIV>
      <DIV class=menuItem onclick="menuOnClick('webofficeTool')">weboffice工具栏 
      </DIV>
      <DIV id=webofficeTool style="DISPLAY: none"><INPUT language=javascript class=btn onclick="return bToolBar_onclick()" type=button value=工具栏(隐/显) name=bToolBar> 
		<INPUT language=javascript class=btn onclick="return bToolBar_New_onclick()" type=button value=新建文档(隐/显) name=bToolBar_New> 
		<INPUT language=javascript class=btn onclick="return bToolBar_Open_onclick()" type=button value=打开文档(隐/显) name=bToolBar_Open> 
		<INPUT language=javascript class=btn onclick="return bToolBar_Save_onclick()" type=button value=保存文档(隐/显) name=bToolBar_Save> 
      </DIV>
      <DIV class=menuItem onclick="menuOnClick('face')">换肤</DIV>
      <DIV id=face style="DISPLAY: none"><INPUT class=btn onclick="return ChangeFace(1)" type=button value=温馨浪漫 name=EnableClose1> 
      <INPUT class=btn onclick="return ChangeFace(2)" type=button value=深沉儒雅 name=EnableClose1>
      <INPUT class=btn onclick="return ChangeFace(3)" type=button value=office风格 name=EnableClose1>
      </DIV>
      
      <DIV class=menuItem onclick="menuOnClick('base64')">base64</DIV>
      <DIV id=base64 style="DISPLAY: none"><INPUT class=btn id=CreateFile4 onclick=GetFileBase64() type=button value=获取文档的base64值 name=CreateFile4> 
			<INPUT class=btn id=CreateFile5 onclick=SaveBinaryFileFromBase64() type=button value=将base64值存回文件 name=CreateFile5>
			<textarea name="FileBase64" rows="10" cols="16"></textarea>
      </DIV>
      
			<DIV class=menuItem onclick="menuOnClick('other')">其它 </DIV>
      <DIV id=other style="DISPLAY: none"><INPUT language=javascript class=btn onclick="return bToolBar_FullScreen_onclick()" type=button value="全  屏" name=bToolBar_FullScreen> 
		<INPUT class=btn id=CreateFile32 onclick=TestVBA() type=button value=VBA调用 name=CreateFile32> 
		<INPUT class=btn onclick="return UnActiveExcel()" type=button value=退出Excel编辑状态 name=EnableClose1>
      </DIV> -->
      <div class=menuItem onclick="menuOnClick('sealList')">公章列表</div>
      <div id="sealList" style="DISPLAY: none">
      	
      </div>
      </TD>
    <INPUT type="hidden" id="docPath" name="filePath" value="${filePath}">
    <INPUT type="hidden" id="docName" name="fileName" value="${fileName}">
    <INPUT type="hidden" id="dispatchId" name="dispatchId" value="${dispatchId}">
    <TD class=TableData vAlign=top width="85%">
    	<!-- -----------------------------== 装载weboffice控件 ==--------------------------------- -->
      <SCRIPT src="${ctxStatic}/weboffice/LoadWebOffice.js"></SCRIPT>
			<!-- --------------------------------== 结束装载控件 ==----------------------------------- -->
			</TD></TR></TBODY></TABLE></FORM></DIV></CENTER>
<script src="${ctxStatic}/jquery/jquery-1.8.3.min.js" type="text/javascript"></script>
<script src="${ctxStatic}/approvalProcess/js/layer/layer.js"></script>
<script type="text/javascript">
	//加载文档
	$(function(){
		var serverUrl = '<%=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+"/"%>';
		console.log("---------------"+serverUrl);
		var docPath = '<%=request.getAttribute("filePath")%>';
		WebOffice1_NotifyCtrlReady();
		document.all.WebOffice1.SetWindowText("授权XX(可通过接口自定义)", 0);
		document.all.WebOffice1.OptionFlag |= 128;
		var path = serverUrl+docPath;
		console.log("-------"+docPath);
		document.all.WebOffice1.LoadOriginalFile(path, "doc");
		
		$.ajax({
			type:"post",
			dataType:"json",
			url:"${ctx}/oa/oaSeal/showAllSeal",
			data:{},
			success:function(ret){
				console.log(ret);
				var htmlStr = '';
				$.each(ret,function(i,obj){
					htmlStr += "<INPUT class=btn onclick=\"return gaizhang('"+obj.id+"','"+obj.filePath+"')\" type=button name='EnableClose1' value='"+obj.sealName+"'>";	
				});
				$("#sealList").append(htmlStr);
			}
		});
	});
	//盖章
	function gaizhang(sealId,filePath){
		var serverUrl = '<%=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+"/"%>';
		var dispatchId = $("#dispatchId").val();
		layer.open({
	        type: 2,//2是iframe层
	        title: '校验公章密码',//标题
	        shadeClose: true,//点击遮罩层也关闭
	        id: '1',//同时只能有一个弹窗
	        scrollbar: false,//弹窗时浏览器不能滚动
	        area: ['480px', '80%'],//弹窗宽高
	        offset:'l',
	        //弹窗点击×的回调函数
	        /* cancel: function(index, layero){
	         layer.close(index);
	         },*/
	         btn: ['确定', '关闭'],
	        content: '${ctx}/oa/oaDocApproval/returnCheckSeal?sealId='+sealId, //这个引号里写要访问页面的路径
	        yes: function(index){
                //当点击‘确定’按钮的时候，获取弹出层返回的值
                var res = window["layui-layer-iframe" + index].formData();
                //最后关闭弹出层
                layer.close(index);
                if(res == 'success'){
                	$.ajax({
			             type: "post",  
			             url:"${ctx}/oa/oaSeal/returnSealPath",  
			             data:{"sealId":sealId},// 序列化表单值  
			             dataType:"json",
			             async: false,  
			             error: function(request) {
			                 alert("Connection error");  
			             }, 
			             success: function(ret) { 
			             	//console.log("-------"+ret);
		                	//document.all.WebOffice1.ProtectDoc(2, 0, "12345");
		                	document.all.WebOffice1.SetFieldValue("mark", "Q", "::ADDMARK::");
							document.all.WebOffice1.SetFieldValue("mark", serverUrl+ret, "::JPG::");	
							document.all.WebOffice1.SetFieldValue("mark", "Q", "::DELMARK::");
			             }  
			         });
                	$.ajax({ 
			             type: "post",  
			             url:"${ctx}/oa/oaSeal/modifySealRecord",  
			             data:{"dispatchId":dispatchId,"sealId":sealId},// 序列化表单值  
			             dataType:"json",
			             async: false,  
			             error: function(request) {
			                 alert("Connection error");  
			             }, 
			             success: function(ret){
			             	console.log("--"+ret);
			             }
			         });
                }else{
                	alert("密码输入错误");
                	document.all.WebOffice1.CloseDoc (0);
                	window.location.href="${ctx}/act/task/todo";
                }
            },
		});
	}
	//上传文档
	function SaveFileDoc(fname,docType) {
		var serverUrl = '<%=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+"/"%>';
		var dispatchId = $("#dispatchId").val();
		try{
			var webObj=document.getElementById("WebOffice1");
			webObj.HttpInit();			//初始化Http引擎
			// 添加相应的Post元素 
			webObj.HttpAddPostString("fname", fname);
			webObj.HttpAddPostCurrFile("DocContent","");// 上传文件 
			var returnValue = webObj.HttpPost(serverUrl+"jeesite/a/oa/oaDocApproval/saveFileDoc?dispatchId="+dispatchId);
			//console.log("*******"+returnValue);
			if("succeed" == returnValue){
				alert("文件上传成功");	
			}else if("failed" == returnValue)
				alert("文件上传失败");
			webObj.Close();
			window.location.href  = "${ctx}/act/task/todo";
		}catch(e){
			alert("异常\r\nError:"+e+"\r\nError Code:"+e.number+"\r\nError Des:"+e.description);
		}
	}
	
</script>

</BODY></HTML>
