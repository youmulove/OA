package com.thinkgem.jeesite.modules.oa.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

public class FTPUtils {

	@Autowired  
    private FTPClient client = new FTPClient(); 
      
    public boolean uploadFile(MultipartFile[] uploadFile, String basePath, String filePath){  
        try {  
        	client.connect("192.168.18.218",21);
        	boolean b = client.login("anonymous", "123@163.com");
        	System.out.println(b);
            //FTPClient client = fTPClientPool.borrowObject();  
            //切换到上传目录  ,basepath需已存在  
            if (!client.changeWorkingDirectory(basePath+filePath)) {    
                //如果目录不存在创建目录    
                String[] dirs = filePath.split("/");    
                String tempPath = basePath;    
                for (String dir : dirs) {    
                    if (null == dir || "".equals(dir)) continue;    
                    tempPath += "/" + dir;    
                    if (!client.changeWorkingDirectory(tempPath)) {    
                        if (!client.makeDirectory(tempPath)) {    
                            return false;    
                        } else {    
                            client.changeWorkingDirectory(tempPath);    
                        }    
                    }    
                }    
            }     
              
            if(uploadFile != null && uploadFile.length > 0){  
                for(int i=0;i<uploadFile.length;i++){  
                    MultipartFile file = uploadFile[i];  
                    saveFile(file,client);  
                }  
            }  
            //fTPClientPool.returnObject(client);
            client.logout();
            client.disconnect();
        } catch (Exception e) {  
            e.printStackTrace();  
        }   
        return true;  
    }  
      
    private boolean saveFile(MultipartFile file,FTPClient client){  
        boolean success = false;  
        InputStream inStream = null;  
        try {  
            String fileName = new String(file.getOriginalFilename());  
            inStream = file.getInputStream();  
            client.enterLocalPassiveMode();//设置为被动模式
            client.setFileType(FTP.BINARY_FILE_TYPE);
            success = client.storeFile(new String(fileName.getBytes("GBK"), "iso-8859-1"), inStream);  
             if (success == true) {  
                return success;  
            }  
        }  catch (Exception e) {  
        }finally {  
            if (inStream != null) {  
                try {  
                    inStream.close();  
                } catch (IOException e) {  
                }  
            }  
        }  
        return success;  
    }  
      
    public boolean downloadFile(HttpServletResponse response, String fileName, String path){  
        response.setCharacterEncoding("UTF-8");  
        response.setContentType("multipart/form-data;charset=UTF-8");  
        try {  
            //FTPClient client = fTPClientPool.borrowObject();  
            client.changeWorkingDirectory(path);  
            FTPFile[] fs = client.listFiles();  
            for(FTPFile ff: fs){  
                if(ff.getName().equals(fileName)){  
                    response.setHeader("Content-Disposition", "attachment;fileName="+ new String( ff.getName().getBytes("gb2312"), "ISO8859-1" ) );  
                    OutputStream os = response.getOutputStream();  
                    client.retrieveFile(ff.getName(), os);  
                    os.flush();  
                    os.close();  
                    break;  
                }  
            }  
            //fTPClientPool.returnObject(client);
            client.logout();
            client.disconnect();
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return true;  
    }  
}
