<%@ page contentType="text/html;charset=GB2312"%>
<%@ page import="java.io.*"%>
<%@page import="com.jspsmart.upload.SmartUpload"%>
<%
	try {
		// ��ʼ���ϴ����
		SmartUpload mySmartUpload=new SmartUpload();
		mySmartUpload.initialize(pageContext);
		mySmartUpload.upload();
		// ��ȡ��������¼
		String name = mySmartUpload.getRequest().getParameter("fname");
		com.jspsmart.upload.File myFile = null;
		myFile = mySmartUpload.getFiles().getFile(0);
		
		//������ڷ���true
		if (!myFile.isMissing()) {
			java.io.File tfile = new java.io.File(name);
			if (tfile.exists()) {
				tfile.delete();
			}
			myFile.saveAs(name, mySmartUpload.SAVE_PHYSICAL); // �����ϴ��ļ�
			
			out.clear();
			out.write("succeed");//���ؿؼ�HttpPost()����ֵ��
			out.flush();
		} else
			throw new Exception("File is missing!!");
	} catch (Exception e) {
		out.clear();
		out.write("failed");//���ؿؼ�HttpPost()����ֵ��
		out.flush();
	}
%>