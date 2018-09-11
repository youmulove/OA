/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.test.web;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.DeploymentBuilder;
import org.junit.Test;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.thinkgem.jeesite.common.web.BaseController;

/**
 * 测试Controller
 * 
 * @author ThinkGem
 * @version 2013-10-17
 */
@Controller
@RequestMapping(value = "${adminPath}/test/test")
public class TestController1 extends BaseController {

	ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

	@Test
	public void test2() {
		DeploymentBuilder builder = processEngine.getRepositoryService()
				.createDeployment();
		builder.addClasspathResource("act/designs/oa/test_audit/test.bpmn");
		builder.addClasspathResource("act/designs/oa/test_audit/test.png");
		Deployment deploy = builder.deploy();
		System.out.println(deploy.getId());
	}

}
