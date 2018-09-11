package com.thinkgem.jeesite.modules.sys.web;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.HandlerMethod;

@ControllerAdvice
public class GloablExceptionHandler {
	
	
	private Logger logger = LoggerFactory.getLogger(getClass());
  @ExceptionHandler(Exception.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  @ResponseBody
  public JsonResult<Map<String,Object>> execptionHandler(Exception e){
	 // e.printStackTrace();
	  //把异常的详细信息写到日志文件中
	  StringWriter sw=new StringWriter();
	  e.printStackTrace();
	  e.printStackTrace(new PrintWriter(sw,true));
	  System.out.println("wobuhuodaoleyichang");
	  logger.error(sw.toString());
	  System.out.println("wobuhuodaoleyichang");
	  logger.error(e.getMessage(), e);
	  
	  return new JsonResult<Map<String,Object>>("1","hallo,系统错误",null);
  }
}
