package com.thinkgem.jeesite.modules.oa.listener;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

import org.activiti.engine.TaskService;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.ExecutionListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thinkgem.jeesite.modules.oa.entity.OaDispatch;
import com.thinkgem.jeesite.modules.oa.entity.OaDocApproval;
import com.thinkgem.jeesite.modules.oa.entity.OaNotify;
import com.thinkgem.jeesite.modules.oa.entity.OaNotifyRecord;
import com.thinkgem.jeesite.modules.oa.service.OaDispatchService;
import com.thinkgem.jeesite.modules.oa.service.OaDocApprovalService;
import com.thinkgem.jeesite.modules.oa.service.OaNotifyService;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

@Service
public class DocSendTaskListener implements ExecutionListener {

	@Autowired
	private OaNotifyService oaNotifyService;
	@Autowired
	private TaskService taskService;
	@Autowired
	private OaDispatchService oaDispatchService;
	@Autowired
	private OaDocApprovalService oaDocApprovalService;

	@Override
	public void notify(DelegateExecution execution) throws Exception {
		execution.setVariable("nowposition", "successEnd");
		// 获取抄送人
		OaDocApproval doc = (OaDocApproval) execution
				.getVariable("oaDocApproval");
		OaDispatch dispatch = oaDispatchService.getByDocApprovalId(doc.getId());
		String copytoId = dispatch.getDispatchCopy();

		if (!("".equals(copytoId)) && copytoId != null) {
			String type = (String) execution.getVariable("type");
			OaNotify oaNotify = new OaNotify();
			if ("doc".equals(type)) {
				oaNotify.setTitle(dispatch.getTitle());
				oaNotify.setContent(dispatch.getTitle());
				oaNotify.setFiles(dispatch.getFilePath());
			}
			oaNotify.setId(UUID.randomUUID().toString().replace("-", ""));
			oaNotify.setType("spcs");
			oaNotify.setStatus("1");
			oaNotify.setCreateDate(new Date());
			oaNotify.setUpdateDate(new Date());
			oaNotify.setOaNotifyRecordIds(copytoId);
			oaNotify.setCreateBy(UserUtils.get(execution.getVariable(
					"applyUser").toString()));
			oaNotify.setUpdateBy(UserUtils.get(execution.getVariable(
					"applyUser").toString()));
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
			OaDocApproval approval = oaDocApprovalService.get(doc.getId());
			approval.setEndFlag("1");// 修改流程结束标识
			oaDocApprovalService.save(approval);
		}
	}

}
