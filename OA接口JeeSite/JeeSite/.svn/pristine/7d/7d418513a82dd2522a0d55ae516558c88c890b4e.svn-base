<%@ page contentType="text/html;charset=GB2312"%>
<%@ page import="java.io.*"%>
<%@page import="com.jspsmart.upload.SmartUpload"%>
<%
	try {
		// 初始化上传组件
		SmartUpload mySmartUpload=new SmartUpload();
		mySmartUpload.initialize(pageContext);
		mySmartUpload.upload();
		// 获取传到表单记录
		String name = mySmartUpload.getRequest().getParameter("fname");
		com.jspsmart.upload.File myFile = null;
		myFile = mySmartUpload.getFiles().getFile(0);
		
		//如果存在返回true
		if (!myFile.isMissing()) {
			java.io.File tfile = new java.io.File(name);
			if (tfile.exists()) {
				tfile.delete();
			}
			myFile.saveAs(name, mySmartUpload.SAVE_PHYSICAL); // 保存上传文件
			
			out.clear();
			out.write("succeed");//返回控件HttpPost()方法值。
			out.flush();
		} else
			throw new Exception("File is missing!!");
	} catch (Exception e) {
		out.clear();
		out.write("failed");//返回控件HttpPost()方法值。
		out.flush();
	}
%>