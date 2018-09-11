<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%@ page import="java.sql.*,java.io.*"%>
<%@page import="com.jspsmart.upload.SmartUpload"%>
<%@page import="com.jspsmart.upload.File"%>
<%
	
	Connection conn = null;          
	Statement stmt = null;     
	ResultSet rs = null;
	PreparedStatement ps=null;
	try{       
		String MdbPath = new java.io.File(application.getRealPath("DBDemo.mdb")).getParent() + "\\DB\\DBDemo.mdb";
		Class.forName("sun.jdbc.odbc.JdbcOdbcDriver"); 
		String url= "jdbc:odbc:driver={Microsoft Access Driver (*.mdb)};DBQ=" + MdbPath ; 
		conn = DriverManager.getConnection(url);          
		stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);  
		        
	} catch(SQLException e){      
		out.print("SQL异常：数据库连接关闭异常---" + e.getMessage());
	} 
%>

<%
	
	try{
		//获取文件
		SmartUpload mysmartUp=new SmartUpload();
		mysmartUp.initialize(pageContext);
		mysmartUp.upload();
		
		String id=mysmartUp.getRequest().getParameter("id");
		
		//定义上传的文件
		com.jspsmart.upload.File myFile=null;
		myFile=mysmartUp.getFiles().getFile(0);
		String filePath="";
		filePath=myFile.getFileName();
		out.println("filePath="+filePath);
		if(!myFile.isMissing()){
		
			myFile.saveAs(filePath,mysmartUp.SAVE_PHYSICAL);	// 保存上传文件到内存
			//获取到值，并转换为二进制，然后修改
			java.io.File tfile = new java.io.File(filePath);
			java.io.InputStream inStream=new java.io.FileInputStream(tfile);
			byte[] bytes = new byte[(int)tfile.length()];
			inStream.read(bytes);
			inStream.close();
			
			String sql="update template_file set filebody=? where templateID="+id;
			ps=conn.prepareStatement(sql);
			ps.setBytes(1,bytes);			
			ps.executeUpdate();
			out.clear();
			out.write("succeed");//返回控件HttpPost()方法值。
			out.flush();
			
			
			if(tfile.exists()) {
				tfile.delete();
			}
		}		
		
	}catch(Exception e){
		out.clear();
		out.write("failed");//返回控件HttpPost()方法值。
		out.flush();
	}finally{
		conn.close();
		ps.close();
		rs.close();
	}
%>