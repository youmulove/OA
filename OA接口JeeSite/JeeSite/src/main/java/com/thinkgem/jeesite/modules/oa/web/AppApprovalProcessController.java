package com.thinkgem.jeesite.modules.oa.web;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.activiti.engine.TaskService;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.thinkgem.jeesite.common.utils.FileUtils;
import com.thinkgem.jeesite.common.utils.IdGen;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.oa.entity.ApprovalRule;
import com.thinkgem.jeesite.modules.oa.entity.OaApply;
import com.thinkgem.jeesite.modules.oa.entity.OaApplyOfficeroom;
import com.thinkgem.jeesite.modules.oa.entity.OaApplyProjector;
import com.thinkgem.jeesite.modules.oa.entity.OaLeaved;
import com.thinkgem.jeesite.modules.oa.entity.OaOvertime;
import com.thinkgem.jeesite.modules.oa.service.ApprovalRuleService;
import com.thinkgem.jeesite.modules.oa.service.CurrencyService;
import com.thinkgem.jeesite.modules.oa.service.OaApplyOfficeroomService;
import com.thinkgem.jeesite.modules.oa.service.OaApplyProjectorService;
import com.thinkgem.jeesite.modules.oa.service.OfficeRoomService;
import com.thinkgem.jeesite.modules.oa.service.OverTimeService;
import com.thinkgem.jeesite.modules.oa.service.ProjectorService;
import com.thinkgem.jeesite.modules.sys.dao.UserDao;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.service.OfficeService;
import com.thinkgem.jeesite.modules.sys.service.SystemService;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.thinkgem.jeesite.modules.sys.web.JsonResult;

@Controller
@RequestMapping(value = "mobile/${adminPath}/oa/approvalProcess")
public class AppApprovalProcessController extends BaseController {
	@Autowired
	private OverTimeService overTimeService;
	@Autowired
	private OfficeRoomService officeRoomService;
	@Autowired
	private ProjectorService projectorService;
	@Autowired
	private ApprovalRuleService approvalRuleService;
	@Autowired
	private CurrencyService currencyService;
	@Autowired
	private TaskService taskService;
	@Autowired
	private OaApplyOfficeroomService oaApplyOfficeroomService;
	@Autowired
	private OaApplyProjectorService oaApplyProjectorService;
	@Autowired
	private UserDao userDao;
	@Autowired
	private OfficeService officeService;
	@Autowired
	private SystemService systemService;

	/*
	 * @RequestMapping("returnPage") public String returnPage() { return
	 * "modules/oa/approvalProcess"; }
	 */

	/**
	 * 查询请假申请的审批人信息
	 */
	@RequestMapping("leaveApproval")
	@ResponseBody
	public JsonResult<Map<String, Object>> leaveApproval(String day) {
		User user = UserUtils.getUser();
		ApprovalRule appr = new ApprovalRule();
		appr.setApprovalCompany(user.getCompany().getId());
		appr.setApprovalType("leave");
		// appr.setApprovalProcess("1");
		// 获取审批规则列表
		// List<ApprovalRule> list = approvalRuleService
		// .findApprovalRuleSelective(appr);
		// appr.setApprovalProcess("2");
		// List<ApprovalRule> twoLevelList = approvalRuleService
		// .findApprovalRuleSelective(appr);
		// appr.setApprovalProcess("3");
		// List<ApprovalRule> threeLevelList = approvalRuleService
		// .findApprovalRuleSelective(appr);
		// appr.setApprovalProcess("4");
		// List<ApprovalRule> fourLevelList = approvalRuleService
		// .findApprovalRuleSelective(appr);
		// List<ApprovalRule> list = new ArrayList<ApprovalRule>();
		// if (oneLevelList.size() > 0) {
		// if ((Double.parseDouble(day)) > (Double.parseDouble(oneLevelList
		// .get(0).getApprovalStart()))
		// && (Double.parseDouble(day)) <= (Double
		// .parseDouble(oneLevelList.get(0).getApprovalEnd()))) {
		// list = oneLevelList;
		// }
		// }
		// if (twoLevelList.size() > 0) {
		// if ((Double.parseDouble(day)) > (Double.parseDouble(twoLevelList
		// .get(0).getApprovalStart()))
		// && (Double.parseDouble(day)) <= (Double
		// .parseDouble(twoLevelList.get(0).getApprovalEnd()))) {
		// list = twoLevelList;
		// }
		// }
		// if (threeLevelList.size() > 0) {
		// if ((Double.parseDouble(day)) > (Double.parseDouble(threeLevelList
		// .get(0).getApprovalStart()))
		// && (Double.parseDouble(day)) <= (Double
		// .parseDouble(threeLevelList.get(0).getApprovalEnd()))) {
		// list = threeLevelList;
		// }
		// }
		// if (fourLevelList.size() > 0) {
		// if ((Double.parseDouble(day)) > (Double.parseDouble(fourLevelList
		// .get(0).getApprovalStart()))
		// && (Double.parseDouble(day)) <= (Double
		// .parseDouble(fourLevelList.get(0).getApprovalEnd()))) {
		// list = fourLevelList;
		// }
		// }

		appr.setApprovalCompany(user.getCompany().getId());
		appr.setApprovalType("leave");
		List<ApprovalRule> appList = approvalRuleService
				.findApprovalRuleSelective(appr);
		String processDefinitionKey = "";
		OaLeaved leave = new OaLeaved();
		leave.setHours(day);

		for (ApprovalRule app : appList) {
			if ("".equals(processDefinitionKey)) {
				// 根据请假天数判断需要使用的请假流程图
				if ((Double.parseDouble(leave.getHours())) > (Double
						.parseDouble(app.getApprovalStart()))
						&& (Double.parseDouble(leave.getHours())) <= (Double
								.parseDouble(app.getApprovalEnd()))) {
					processDefinitionKey = app.getApprovalProcess();
					appr.setApprovalProcess(processDefinitionKey);
				}
			}
		}
		List<ApprovalRule> appList2 = approvalRuleService
				.findApprovalRuleSelective(appr);
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		// Double day1 = Double.parseDouble(day);
		for (ApprovalRule app : appList2) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("approvalId", app.getApprovaleId());
			map.put("approvalType", app.getApprovalType());
			map.put("approvalProcess", app.getApprovalProcess());
			map.put("approvalCompany", app.getApprovalCompany());
			map.put("approvalRank", app.getApprovalRank());
			if (app.getApprovalPerson() != null
					&& !app.getApprovalPerson().equals("")
					&& !app.getApprovalPerson().equals("free")) {
				app.setApprovalPerson(returnStr(app.getApprovalPerson().split(
						",")));
				map.put("approvalPerson", app.getApprovalPerson());
				map.put("approvalPersonName",
						app.getApprovalPerson() == null ? "" : UserIdToName(app
								.getApprovalPerson()));
			} else {
				map.put("approvalPerson", app.getApprovalPerson());
				map.put("approvalPersonName", app.getApprovalPerson());
			}
			map.put("approvalRole", app.getApprovalRole());
			map.put("approvalStart", app.getApprovalStart());
			map.put("approvalEnd", app.getApprovalEnd());
			listMap.add(map);
		}
		Map<String, Object> map1 = new HashMap<String, Object>();
		map1.put("listMap", listMap);
		return new JsonResult<Map<String, Object>>(JsonResult.SUCCESS, "查看成功",
				map1);
	}

	// 加班申请
	@RequestMapping("overTimeApply")
	@ResponseBody
	public JsonResult<Map<String, Object>> overTimeApply(OaOvertime overTime,
			String sysapp1, String sysapp2, String sysapp3, String sysapp4,
			String oaNotifyRecordIds) {
		try {
			overTime.setCopytoId(oaNotifyRecordIds);
			overTimeService.overTimeApply(overTime, oaNotifyRecordIds, sysapp1,
					sysapp2, sysapp3, sysapp4);//
			return new JsonResult<Map<String, Object>>(JsonResult.SUCCESS,
					"申请成功", null);
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult<Map<String, Object>>(JsonResult.ERROR,
					"查看失败", null);
		}
	}

	/**
	 * 查询加班流程的审批人信息
	 */
	@RequestMapping("overTimeApproval")
	@ResponseBody
	public JsonResult<Map<String, Object>> overTimeApproval(String isHoliday) {
		try {
			User user = UserUtils.getUser();
			ApprovalRule appr = new ApprovalRule();
			appr.setApprovalCompany(user.getCompany().getName());
			appr.setApprovalType("overTime");
			appr.setApprovalStart(isHoliday);// 是否是法定节假日
			// 获取审批规则信息列表
			List<ApprovalRule> appList = approvalRuleService
					.findApprovalRuleSelective(appr);
			List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
			for (ApprovalRule app : appList) {

				Map<String, Object> map = new HashMap<String, Object>();
				map.put("approvalId", app.getApprovaleId());
				map.put("approvalType", app.getApprovalType());
				map.put("approvalProcess", app.getApprovalProcess());
				map.put("approvalCompany", app.getApprovalCompany());
				map.put("approvalRank", app.getApprovalRank());
				if (app.getApprovalPerson() != null
						&& !app.getApprovalPerson().equals("")
						&& !app.getApprovalPerson().equals("free")) {
					app.setApprovalPerson(returnStr(app.getApprovalPerson()
							.split(",")));
					map.put("approvalPerson", app.getApprovalPerson());
					map.put("approvalPersonName",
							app.getApprovalPerson() == null ? ""
									: UserIdToName(app.getApprovalPerson()));
				} else {
					map.put("approvalPerson", app.getApprovalPerson());
					map.put("approvalPersonName", app.getApprovalPerson());
				}
				map.put("approvalRole", app.getApprovalRole());
				map.put("approvalStart", app.getApprovalStart());
				map.put("approvalEnd", app.getApprovalEnd());
				listMap.add(map);

			}
			Map<String, Object> map1 = new HashMap<String, Object>();
			map1.put("listMap", listMap);
			return new JsonResult<Map<String, Object>>(JsonResult.SUCCESS,
					"查询成功", map1);
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult<Map<String, Object>>(JsonResult.ERROR,
					"查询失败", null);
		}

	}

	/**
	 * 会议室申请
	 */
	@RequestMapping("officeRoomApply")
	@ResponseBody
	public JsonResult<Map<String, Object>> officeRoomApply(
			OaApplyOfficeroom officeRoom, String sysapp1, String sysapp2,
			String sysapp3, String sysapp4, String oaNotifyRecordIds) {
		OaApplyOfficeroom officeroom2 = new OaApplyOfficeroom();
		officeroom2.setCompanyId(UserUtils.getUser().getCompany().getId());
		Map<String, Object> map = new HashMap<String, Object>();
		officeroom2.setCompany(UserUtils.getUser().getCompany());
		officeroom2.setOfficeroomId(officeRoom.getOfficeroomId());
		List<OaApplyOfficeroom> list2 = oaApplyOfficeroomService
				.findList2(officeroom2);
		for (OaApplyOfficeroom oaroom : list2) {
			if (oaroom.getStartTime() != null && oaroom.getEndTime() != null) {
				boolean b1 = belongCalendar(officeRoom.getStartTime(),
						oaroom.getStartTime(), oaroom.getEndTime());
				boolean b2 = belongCalendar(officeRoom.getEndTime(),
						oaroom.getStartTime(), oaroom.getEndTime());
				boolean b3 = belongCalendar(oaroom.getStartTime(),
						officeRoom.getStartTime(), officeRoom.getEndTime());
				boolean b4 = belongCalendar(oaroom.getEndTime(),
						officeRoom.getStartTime(), officeRoom.getEndTime());
				if (b1 || b2 || b3 || b4) {
					return new JsonResult<Map<String, Object>>(
							JsonResult.ERROR, "会议室时间冲突", map);
				}
			}
		}
		officeRoom.setCopytoId(oaNotifyRecordIds);
		officeRoomService.officeRoomApply(officeRoom, oaNotifyRecordIds,
				sysapp1, sysapp2, sysapp3, sysapp4);
		return new JsonResult<Map<String, Object>>(JsonResult.SUCCESS, "申请成功",
				map);
	}

	/**
	 * 查询会议室申请流程的审批人信息
	 */
	@RequestMapping("officeRoomApproval")
	@ResponseBody
	public JsonResult<Map<String, Object>> officeRoomApproval(HttpServletRequest request,String isHoliday) {
		try {
			User user = UserUtils.getUser();
			ApprovalRule appr = new ApprovalRule();
			appr.setApprovalCompany(user.getCompany().getName());
			appr.setApprovalType("officeRoom");
			System.out.println(request.getSession().getId());
			// appr.setApprovalStart(isHoliday);//是否是法定节假日
			// 获取审批规则信息列表
			List<ApprovalRule> appList = approvalRuleService
					.findApprovalRuleSelective(appr);
			List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
			for (ApprovalRule app : appList) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("approvalId", app.getApprovaleId());
				map.put("approvalType", app.getApprovalType());
				map.put("approvalProcess", app.getApprovalProcess());
				map.put("approvalCompany", app.getApprovalCompany());
				map.put("approvalRank", app.getApprovalRank());
				if (app.getApprovalPerson() != null
						&& !app.getApprovalPerson().equals("")
						&& !app.getApprovalPerson().equals("free")) {
					app.setApprovalPerson(returnStr(app.getApprovalPerson()
							.split(",")));
					map.put("approvalPerson", app.getApprovalPerson());
					map.put("approvalPersonName",
							app.getApprovalPerson() == null ? ""
									: UserIdToName(app.getApprovalPerson()));
				} else {
					map.put("approvalPerson", app.getApprovalPerson());
					map.put("approvalPersonName", app.getApprovalPerson());
				}
				map.put("approvalRole", app.getApprovalRole());
				map.put("approvalStart", app.getApprovalStart());
				map.put("approvalEnd", app.getApprovalEnd());
				listMap.add(map);

			}
			Map<String, Object> map1 = new HashMap<String, Object>();
			map1.put("listMap", listMap);
			return new JsonResult<Map<String, Object>>(JsonResult.SUCCESS,
					"查询成功", map1);
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult<Map<String, Object>>(JsonResult.ERROR,
					"查询失败", null);
		}

	}

	/**
	 * 投影仪申请
	 */
	@RequestMapping("projectorApplyDetail")
	@ResponseBody
	public JsonResult<Map<String, Object>> projectorApplyDetail(
			OaApplyProjector projector, String oaNotifyRecordIds,
			String projectoroNo) {
		OaApplyProjector oap = new OaApplyProjector();
		oap.setCompany(UserUtils.getUser().getCompany());
		oap.setAdapterId(projectoroNo);
		Map<String, Object> map = new HashMap<String, Object>();
		List<OaApplyProjector> list = oaApplyProjectorService.findList(oap);
		map.put("list", list);
		return new JsonResult<Map<String, Object>>(JsonResult.SUCCESS,
				"投影仪申请详情查询成功成功", map);
	}

	/**
	 * 投影仪申请
	 */
	@RequestMapping("projectorApply")
	@ResponseBody
	public JsonResult<Map<String, Object>> projectorApply(
			OaApplyProjector projector, String oaNotifyRecordIds,
			String sysapp1, String sysapp2, String sysapp3, String sysapp4,
			String projectoroNo) {
		OaApplyProjector oap = new OaApplyProjector();
		oap.setCompany(UserUtils.getUser().getCompany());
		oap.setAdapterId(projectoroNo);
		Map<String, Object> map = new HashMap<String, Object>();
		List<OaApplyProjector> list = oaApplyProjectorService.findList(oap);
		for (OaApplyProjector pro : list) {
			if (pro.getStartTime() != null && pro.getEndTime() != null) {
				boolean b1 = belongCalendar(projector.getStartTime(),
						pro.getStartTime(), pro.getEndTime());
				boolean b2 = belongCalendar(projector.getEndTime(),
						pro.getStartTime(), pro.getEndTime());
				boolean b3 = belongCalendar(pro.getStartTime(),
						projector.getStartTime(), projector.getEndTime());
				boolean b4 = belongCalendar(pro.getEndTime(),
						projector.getStartTime(), projector.getEndTime());
				if (b1 || b2 || b3 || b4) {
					return new JsonResult<Map<String, Object>>(
							JsonResult.ERROR, "此时间段有人申请该投影仪", map);
				}
			}
		}
		projector.setCopytoId(oaNotifyRecordIds);
		projectorService.projectorApply(projector, oaNotifyRecordIds,
				projectoroNo, sysapp1, sysapp2, sysapp3, sysapp4);
		return new JsonResult<Map<String, Object>>(JsonResult.SUCCESS,
				"投影仪申请成功", map);
	}

	/***
	 * 查询投影仪申请的审批人信息
	 */
	@RequestMapping("projectorApproval")
	@ResponseBody
	public JsonResult<Map<String, Object>> projectorApproval() {
		User user = UserUtils.getUser();
		ApprovalRule appr = new ApprovalRule();
		appr.setApprovalCompany(user.getCompany().getName());
		appr.setApprovalType("projector");
		// 获取审批规则信息列表
		List<ApprovalRule> appList = approvalRuleService
				.findApprovalRuleSelective(appr);
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		for (ApprovalRule app : appList) {
			if (app.getApprovalPerson() != null) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("approvalId", app.getApprovaleId());
				map.put("approvalType", app.getApprovalType());
				map.put("approvalProcess", app.getApprovalProcess());
				map.put("approvalCompany", app.getApprovalCompany());
				map.put("approvalRank", app.getApprovalRank());
				if (app.getApprovalPerson() != null
						&& !app.getApprovalPerson().equals("")
						&& !app.getApprovalPerson().equals("free")) {
					app.setApprovalPerson(returnStr(app.getApprovalPerson()
							.split(",")));
					map.put("approvalPerson", app.getApprovalPerson());
					map.put("approvalPersonName",
							app.getApprovalPerson() == null ? ""
									: UserIdToName(app.getApprovalPerson()));
				} else {
					map.put("approvalPerson", app.getApprovalPerson());
					map.put("approvalPersonName", app.getApprovalPerson());
				}
				map.put("approvalRole", app.getApprovalRole());
				map.put("approvalStart", app.getApprovalStart());
				map.put("approvalEnd", app.getApprovalEnd());
				listMap.add(map);
			}
		}
		Map<String, Object> map1 = new HashMap<String, Object>();
		map1.put("listMap", listMap);
		return new JsonResult<Map<String, Object>>(JsonResult.SUCCESS, "查询成功",
				map1);
	}

	/**
	 * 通用申请
	 * @throws IOException 
	 */
	@RequestMapping("currencyApply")
	@ResponseBody
	public JsonResult<Map<String, Object>> currencyApply(OaApply currency,@RequestParam(value="photo",required=false)MultipartFile file[],
			String sysapp1, String sysapp2, String sysapp3, String sysapp4,
			String oaNotifyRecordIds) throws IOException {
		if(file!=null){
		currency.setStampFiles(UploadMyImage(file));
		}
		currency.setApplyName(UserUtils.getUser().getLoginName());
		currencyService.currencyApply(currency, oaNotifyRecordIds, sysapp1,
				sysapp2, sysapp3, sysapp4);
		return new JsonResult<Map<String, Object>>(JsonResult.SUCCESS, "申请成功",
				null);
	}

	/**
	 * 上传审核的图片
	 * 
	 * @param file
	 * @param req
	 * @param resp
	 * @throws IOException
	 */
	public static String UploadMyImage(MultipartFile files[]) throws IOException {
		User user = UserUtils.getUser();
		String Realpath="";
		String savepath = "";
		for(MultipartFile file:files){
		Calendar calendar = Calendar.getInstance();
		String path = "D:/fileupload/userfiles/"+user.getId()+"/images/photo/"
				+ calendar.get(Calendar.YEAR)
				+ "/"
				+ ((calendar.get(Calendar.MONTH) + 1) > 9 ? (calendar
						.get(Calendar.MONTH) + 1) + "" : "0"
						+ (calendar.get(Calendar.MONTH) + 1)) + "/";
		FileUtils.createDirectory(FileUtils.path(path));
		String fileName = file.getOriginalFilename();
		String fileAllName = null;

		//String fileType = file.getContentType();
		fileAllName = fileName /*+ fileType*/;

		// 获取文件的后缀
		String extensionName = fileAllName.substring(fileAllName
				.lastIndexOf(".") + 1);
		// 对扩展名进行小写转换
		String newExtensionName = extensionName.toLowerCase();
		// 获取文件的大小
		long fileSize = file.getSize();
		// 图片文件类型过滤
		if (!"jpg".equals(newExtensionName) && !"jpeg".equals(newExtensionName)
				&& !"png".equals(newExtensionName)
				&& !"bmp".equals(newExtensionName)
				&& !"gif".equals(extensionName)) {
			System.out.println("图片格式不正确");
		}
		if (fileSize <= 0) {
			System.out.println("请上传图片！");
		}
		if (fileSize > (5 * 1024 * 1024)) {
			System.out.println("图片不能大于5MB");
		} else {
			String newFileName = IdGen.uuid()
					+ user.getLoginName() + "." + newExtensionName;
			File targetFile = new File(path, newFileName); // 新建文件
			if (!targetFile.exists()) { // 判断文件的路径是否存在
				targetFile.createNewFile(); // 如果文件不存在 在目录中创建文件夹
											// 这里要注意mkdir()和mkdirs()的区别
			} else {
				targetFile.delete();
				targetFile.createNewFile();
			}
			try {
				file.transferTo(targetFile); // 传送 失败就抛异常
			} catch (Exception e) {
				e.printStackTrace();
				//删除图片处理代码
				if(!Realpath.equals("")){
				String[] Path=Realpath.split("|");
				for(int i=0;i<Path.length;i++){
					File newTargetFile=new File(Path[i]);
					if(newTargetFile.exists()){
						newTargetFile.delete();
					}
				}
				}
				
			}
			//Realpath用于异常的时候删除已保存的图片
			if(!Realpath.equals("")){
				Realpath=Realpath+path+newFileName;
			}else{
				Realpath=path+newFileName;
			}
			
			if(!savepath.equals("")){
			savepath =savepath+"|"+ "/jeesite" + path.substring("D:/fileupload".length())
					+ newFileName;
			}else{
			savepath="/jeesite" + path.substring("D:/fileupload".length())
					+ newFileName;
			}
		}
		}
		return savepath;
	}

	/**
	 * 通用申请的审批人查询
	 */
	@RequestMapping("currencyApproval")
	@ResponseBody
	public JsonResult<Map<String, Object>> currencyApproval() {
		User user = UserUtils.getUser();
		ApprovalRule appr = new ApprovalRule();
		appr.setApprovalCompany(user.getCompany().getName());
		appr.setApprovalType("currency");
		// 获取审批规则信息列表
		List<ApprovalRule> appList = approvalRuleService
				.findApprovalRuleSelective(appr);
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		for (ApprovalRule app : appList) {
			if (app.getApprovalPerson() != null) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("approvalId", app.getApprovaleId());
				map.put("approvalType", app.getApprovalType());
				map.put("approvalProcess", app.getApprovalProcess());
				map.put("approvalCompany", app.getApprovalCompany());
				map.put("approvalRank", app.getApprovalRank());
				if (app.getApprovalPerson() != null
						&& !app.getApprovalPerson().equals("")
						&& !app.getApprovalPerson().equals("free")) {
					app.setApprovalPerson(returnStr(app.getApprovalPerson()
							.split(",")));
					map.put("approvalPerson", app.getApprovalPerson());
					map.put("approvalPersonName",
							app.getApprovalPerson() == null ? ""
									: UserIdToName(app.getApprovalPerson()));
				} else {
					map.put("approvalPerson", app.getApprovalPerson());
					map.put("approvalPersonName", app.getApprovalPerson());
				}
				map.put("approvalRole", app.getApprovalRole());
				map.put("approvalStart", app.getApprovalStart());
				map.put("approvalEnd", app.getApprovalEnd());
				listMap.add(map);
			}
		}
		Map<String, Object> map1 = new HashMap<String, Object>();
		map1.put("listMap", listMap);
		return new JsonResult<Map<String, Object>>(JsonResult.SUCCESS,
				"查询申请人的审批人成功", map1);
	}

	@RequestMapping(value = "logOutle")
	@ResponseBody
	public JsonResult<Map<String, Object>> loginOut(HttpServletRequest request,
			HttpServletResponse response) {
		UserUtils.clearCache(UserUtils.getUser());
		UserUtils.getSubject().logout();
		logger.error("退出登录了");
		return new JsonResult<Map<String, Object>>("0", "退出成功", null);
	}

	public static boolean belongCalendar(Date time, Date from, Date to) {
		if (from == null || to == null) {
			return false;
		}
		Calendar date = Calendar.getInstance();
		date.setTime(time);

		Calendar after = Calendar.getInstance();
		after.setTime(from);

		Calendar before = Calendar.getInstance();
		before.setTime(to);

		if (date.after(after) && date.before(before)) {
			return true;
		} else {
			return false;
		}
	}

	@RequestMapping("/findUsersBySysrankId")
	@ResponseBody
	public JsonResult<Map<String, Object>> findUsersBySysrankId(String sysrankId) {
		List<User> list = userDao.findUserBysysrankId(sysrankId, UserUtils
				.getUser().getCompany().getId());
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userList", list);
		return new JsonResult<Map<String, Object>>("0", "请求成功了", map);
	}

	public String returnStr(String[] split) {
		User user = UserUtils.getUser();
		StringBuffer sb = new StringBuffer();
		sb.append("");
		for (int i = 0; i < split.length; i++) {
			// 根据申请人所在部门和公司展示审批人 查询申请人的部门父ids和审批人的部门id
			User u = systemService.getUser(split[i]);
			if (u == null) {
				continue;
			}
			if ((user.getOffice().getId()).equals(u.getOffice().getId())) {
				sb.append(split[i] + ",");
			} else {
				String parentIds = officeService.findAllDeptByUser(user)
						.getParentIds();
				if (ArrayUtils.contains(parentIds.split(","), u.getOffice()
						.getId())) {
					sb.append(split[i] + ",");
				}
			}
		}
		if ("".equals(sb.toString()) || (sb.toString()) == null) {
			for (int i = 0; i < split.length; i++) {
				sb.append(split[i] + ",");
			}
		}
		return sb.toString();
	}

	public static String UserIdToName(String Ids) {
		if (Ids.equals("")) {
			return "";
		}
		String[] list = Ids.split(",");
		String Names = "";
		for (int i = 0; i < list.length; i++) {
			User user = UserUtils.get(list[i]);
			if (i < list.length - 1) {
				Names = Names + user.getName() + ",";
			} else {
				Names = Names + user.getName();
			}
		}
		return Names;
	}
}
