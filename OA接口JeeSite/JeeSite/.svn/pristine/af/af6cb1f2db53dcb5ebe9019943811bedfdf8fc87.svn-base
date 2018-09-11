package com.thinkgem.jeesite.modules.oa.listener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

import org.activiti.engine.TaskService;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.ExecutionListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thinkgem.jeesite.modules.oa.entity.OaApply;
import com.thinkgem.jeesite.modules.oa.entity.OaApplyOfficeroom;
import com.thinkgem.jeesite.modules.oa.entity.OaApplyProjector;
import com.thinkgem.jeesite.modules.oa.entity.OaLeaved;
import com.thinkgem.jeesite.modules.oa.entity.OaNotify;
import com.thinkgem.jeesite.modules.oa.entity.OaNotifyRecord;
import com.thinkgem.jeesite.modules.oa.entity.OaOvertime;
import com.thinkgem.jeesite.modules.oa.service.OaNotifyService;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

@Service
public class SendTaskListener implements ExecutionListener {

	@Autowired
	private OaNotifyService oaNotifyService;
	@Autowired
	private TaskService taskService;

	@Override
	public void notify(DelegateExecution execution) throws Exception {
		execution.setVariable("nowposition", "successEnd");
		String copytoId = (String) execution.getVariable("copytoId");// 获取抄送人
		String Id = execution.getProcessInstanceId();
		if (!("".equals(copytoId)) && copytoId != null) {
			String type = (String) execution.getVariable("type");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			OaNotify oaNotify = new OaNotify();
			StringBuffer sb = new StringBuffer();
			StringBuffer sb1 = new StringBuffer();
			if ("leave".equals(type)) {
				OaLeaved leave = (OaLeaved) execution.getVariable("leave");
				oaNotify.setTitle(UserUtils.get(
						execution.getVariable("applyUserId").toString())
						.getName()
						+ "的请假申请");
				sb.setLength(0);
				sb1.setLength(0);
				sb.append("type:")
						.append(type)
						.append(",processInstanceId:")
						.append(Id)
						.append(",startTime:")
						.append(sdf.format(leave.getStartTime().getTime()))
						.append(",endTime:")
						.append(sdf.format(leave.getEndTime()))
						.append(",applyName:")
						.append(UserUtils
								.get(execution.getVariable("applyUserId")
										.toString()).getName());

				sb1.append("开始时间:")
						.append(sdf.format(leave.getStartTime().getTime()))
						.append(",结束时间:")
						.append(sdf.format(leave.getEndTime())).append(",时长:")
						.append(leave.getHours()).append("天").append(",请假事由:")
						.append(leave.getReason());

				oaNotify.setContent(sb.toString());
				oaNotify.setRemarks(sb1.toString());
			} else if ("overTime".equals(type)) {
				OaOvertime overTime = (OaOvertime) execution
						.getVariable("overTime");
				oaNotify.setTitle(UserUtils.get(
						execution.getVariable("applyUserId").toString())
						.getName()
						+ "的加班申请");
				sb.setLength(0);
				sb1.setLength(0);
				sb.append("type:")
						.append(type)
						.append(",processInstanceId:")
						.append(Id)
						.append(",startTime:")
						.append(sdf.format(overTime.getStartTime()))
						.append(",endTime:")
						.append(sdf.format(overTime.getEndTime()))
						.append(",applyName:")
						.append(UserUtils
								.get(execution.getVariable("applyUserId")
										.toString()).getName());

				sb1.append("开始时间:")
						.append(sdf.format(overTime.getStartTime().getTime()))
						.append(",结束时间:")
						.append(sdf.format(overTime.getEndTime()))
						.append(",时长:").append(overTime.getHours())
						.append("小时").append(",加班事由:")
						.append(overTime.getReason());

				oaNotify.setContent(sb.toString());
				oaNotify.setRemarks(sb1.toString());

			} else if ("officeRoom".equals(type)) {
				OaApplyOfficeroom officeRoom = (OaApplyOfficeroom) execution
						.getVariable("officeRoom");
				oaNotify.setTitle(UserUtils.get(
						execution.getVariable("applyUserId").toString())
						.getName()
						+ "的会议室申请");
				sb.setLength(0);
				sb1.setLength(0);

				sb.append("type:")
						.append(type)
						.append(",processInstanceId:")
						.append(Id)
						.append(",startTime:")
						.append(sdf.format(officeRoom.getStartTime()))
						.append(",endTime:")
						.append(sdf.format(officeRoom.getEndTime()))
						.append(",applyName:")
						.append(UserUtils
								.get(execution.getVariable("applyUserId")
										.toString()).getName());

				sb1.append("开始时间:")
						.append(sdf.format(officeRoom.getStartTime().getTime()))
						.append(",结束时间:")
						.append(sdf.format(officeRoom.getEndTime()))
						.append(",时长:").append(officeRoom.getHours())
						.append("小时").append(",申请事由:")
						.append(officeRoom.getReason()).append(",会议室:")
						.append(officeRoom.getYuliu1());

				oaNotify.setContent(sb.toString());
				oaNotify.setRemarks(sb1.toString());
			} else if ("projector".equals(type)) {
				OaApplyProjector projector = (OaApplyProjector) execution
						.getVariable("projector");
				oaNotify.setTitle(UserUtils.get(
						execution.getVariable("applyUserId").toString())
						.getName()
						+ "的投影仪申请");
				sb.setLength(0);
				sb1.setLength(0);

				sb.append("type:")
						.append(type)
						.append(",processInstanceId:")
						.append(Id)
						.append(",startTime:")
						.append(sdf.format(projector.getStartTime()))
						.append(",endTime:")
						.append(sdf.format(projector.getEndTime()))
						.append(",applyName:")
						.append(UserUtils
								.get(execution.getVariable("applyUserId")
										.toString()).getName());

				sb1.append("开始时间:")
						.append(sdf.format(projector.getStartTime().getTime()))
						.append(",结束时间:")
						.append(sdf.format(projector.getEndTime()))
						.append(",时长:").append(projector.getHours())
						.append("小时").append(",申请事由:")
						.append(projector.getReason()).append(",投影仪:")
						.append(projector.getYuliu1());

				oaNotify.setContent(sb.toString());
				oaNotify.setRemarks(sb1.toString());
			} else if ("currency".equals(type)) {
				OaApply currency = (OaApply) execution.getVariable("currency");
				oaNotify.setTitle(UserUtils.get(
						execution.getVariable("applyUserId").toString())
						.getName()
						+ "的通用申请");
				sb.setLength(0);
				sb1.setLength(0);

				sb.append("type:")
						.append(type)
						.append(",processInstanceId:")
						.append(Id)
						.append(",startTime:")
						.append(sdf.format(currency.getStartTime()))
						.append(",endTime:")
						.append(sdf.format(currency.getEndTime()))
						.append(",applyName:")
						.append(UserUtils
								.get(execution.getVariable("applyUserId")
										.toString()).getName());

				sb1.append("开始时间:")
						.append(sdf.format(currency.getStartTime().getTime()))
						.append(",结束时间:")
						.append(sdf.format(currency.getEndTime()))
						.append(",申请事项:").append(currency.getApplyItem())
						.append(",申请事由:").append(currency.getReason());

				oaNotify.setContent(sb.toString());
				oaNotify.setRemarks(sb1.toString());
			}
			oaNotify.setId(UUID.randomUUID().toString().replace("-", ""));
			oaNotify.setType("spcs");
			oaNotify.setStatus("1");
			oaNotify.setCreateDate(new Date());
			oaNotify.setUpdateDate(new Date());
			oaNotify.setOaNotifyRecordIds(copytoId);
			oaNotify.setCreateBy(UserUtils.get(execution.getVariable(
					"applyUserId").toString()));
			oaNotify.setUpdateBy(UserUtils.get(execution.getVariable(
					"applyUserId").toString()));
			ArrayList<OaNotifyRecord> oaNotifyRecordList = new ArrayList<OaNotifyRecord>();

			String[] split = copytoId.split(",");
			for (int i = 0; i < split.length; i++) {
				OaNotifyRecord record = new OaNotifyRecord();
				record.setId(UUID.randomUUID().toString().replace("-", ""));
				record.setUser(UserUtils.get(split[i]));
				record.setOaNotify(oaNotify);
				oaNotifyRecordList.add(record);
			}
			oaNotify.setOaNotifyRecordList(oaNotifyRecordList);
			// System.out.println(oaNotifyRecordList.get(0).getOaNotify());
			oaNotifyRecordList.get(0).getOaNotify().setIsNewRecord(true);
			oaNotifyService.save(oaNotifyRecordList.get(0).getOaNotify());// 发送通知
		}
	}

}
