/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.oa.web;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.activiti.engine.TaskService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.oa.entity.OaDispatch;
import com.thinkgem.jeesite.modules.oa.service.OaDispatchService;
import com.thinkgem.jeesite.modules.oa.utils.FTPUtils;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * 发文单Controller
 * 
 * @author lisp
 * @version 2017-11-03
 */
@Controller
@RequestMapping(value = "${adminPath}/oa/oaDispatch")
public class OaDispatchController extends BaseController {

	@Autowired
	private OaDispatchService oaDispatchService;
	@Autowired
	private TaskService taskService;

	@ModelAttribute
	public OaDispatch get(@RequestParam(required = false) String id) {
		OaDispatch entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = oaDispatchService.get(id);
		}
		if (entity == null) {
			entity = new OaDispatch();
		}
		return entity;
	}

	@RequiresPermissions("oa:oaDispatch:view")
	@RequestMapping(value = { "list", "" })
	public String list(OaDispatch oaDispatch, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		Page<OaDispatch> page = oaDispatchService.findPage(
				new Page<OaDispatch>(request, response), oaDispatch);
		model.addAttribute("page", page);
		return "modules/oa/document/oaDispatchList";
	}

	@RequiresPermissions("oa:oaDispatch:view")
	@RequestMapping(value = { "myList", "" })
	public String myList(OaDispatch oaDispatch, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		oaDispatch.setCreateBy(UserUtils.getUser());
		Page<OaDispatch> page = oaDispatchService.findPage(
				new Page<OaDispatch>(request, response), oaDispatch);
		model.addAttribute("page", page);
		return "modules/oa/document/oaDispatchList";
	}

	@RequiresPermissions("oa:oaDispatch:view")
	@RequestMapping(value = "form")
	public String form(OaDispatch oaDispatch, Model model) {
		model.addAttribute("oaDispatch", oaDispatch);
		return "modules/oa/document/oaDispatchForm";
	}

	@RequiresPermissions("oa:oaDispatch:edit")
	@RequestMapping(value = "save")
	public String save(HttpServletRequest request, OaDispatch oaDispatch,
			Model model, RedirectAttributes redirectAttributes,
			@RequestParam(value = "file", required = false) MultipartFile[] file) {
		if (!beanValidator(model, oaDispatch)) {
			return form(oaDispatch, model);
		}
		/*
		 * Calendar cal = Calendar.getInstance(); String year =
		 * String.valueOf(cal.get(Calendar.YEAR)); String month =
		 * String.valueOf(cal.get(Calendar.MONTH)+1); String day =
		 * String.valueOf(cal.get(Calendar.DATE)); StringBuffer sb = new
		 * StringBuffer();
		 * sb.append("D:/fileupload/userfiles/1/files/oa/document/"
		 * ).append(year).append("/").append(month).append("/")
		 * .append(day).append("/"); String path = sb.toString(); String fname =
		 * file.getOriginalFilename(); File f = new File(path,fname);
		 * if(!f.getParentFile().exists()){ f.getParentFile().mkdirs(); } try {
		 * file.transferTo(f); } catch (Exception e) { e.printStackTrace(); }
		 * oaDispatch.setFilePath(path+fname);
		 */

		/*
		 * String fname = file.getOriginalFilename(); String
		 * path=request.getSession
		 * ().getServletContext().getRealPath("/document"); File f = new
		 * File(path,fname); if(!f.getParentFile().exists()){
		 * f.getParentFile().mkdirs(); } try { file.transferTo(f); } catch
		 * (Exception e) { e.printStackTrace(); }
		 * oaDispatch.setFilePath(request.
		 * getContextPath()+"/boolean isDirectory = t.makeDirectory("
		 * upload/123");"+"/"+fname);
		 */

		// ftp文件上传
		StringBuffer sb = new StringBuffer();
		String basePath = "/document";// 设置服务器中文件保存的根目录
		Date now = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		String filePath = dateFormat.format(now); // 根据当前时间设置文件保存的子目录
		if (file != null && file.length > 0) {
			FTPUtils ftp = new FTPUtils();
			if (!ftp.uploadFile(file, basePath, filePath)) {
				return "error";
			}
			for (int i = 0; i < file.length; i++) {
				String fileName;
				fileName = new String(file[i].getOriginalFilename());
				if (i != 0) {
					sb.append(",");
				}
				sb.append(fileName);// 用逗号连接文件名存入数据库
			}
			// ------
		}
		System.out.println(basePath);
		System.out.println(filePath);
		System.out.println(sb.toString());
		oaDispatch.setFilePath("ftp://192.168.18.218" + basePath + "/"
				+ filePath + "/" + sb.toString());
		oaDispatch.setYuliu1("0");
		oaDispatchService.save(oaDispatch);
		addMessage(redirectAttributes, "保存发文单成功");
		return "redirect:" + Global.getAdminPath() + "/oa/oaDispatch/myList";
	}

	@RequiresPermissions("oa:oaDispatch:edit")
	@RequestMapping(value = "delete")
	public String delete(OaDispatch oaDispatch,
			RedirectAttributes redirectAttributes) {
		oaDispatchService.delete(oaDispatch);
		addMessage(redirectAttributes, "删除发文单成功");
		return "redirect:" + Global.getAdminPath() + "/oa/oaDispatch/myList";
	}

	/**
	 * 发起公文申请
	 */
	@RequestMapping("docApply")
	public String docApply(String dispatchId,
			RedirectAttributes redirectAttributes) {
		OaDispatch dispatch = oaDispatchService.get(dispatchId);
		oaDispatchService.docApply(dispatch);
		addMessage(redirectAttributes, "流程发起成功");
		return "redirect:" + Global.getAdminPath() + "/oa/oaDispatch/myList";
	}

	/**
	 * 再次发起申请
	 */
	@RequestMapping("docApplyAgain")
	public String docApplyAgain(HttpServletRequest request, String dispatchId,
			String taskId) {
		OaDispatch dispatch = oaDispatchService.get(dispatchId);
		dispatch.setYuliu1("1");
		oaDispatchService.save(dispatch);
		HashMap<String, Object> variables = new HashMap<String, Object>();
		taskId = request.getSession().getAttribute("taskId").toString();
		taskService.complete(taskId, variables);
		request.getSession().removeAttribute("taskId");
		return "redirect:" + Global.getAdminPath() + "/oa/oaDispatch/myList";
	}
}