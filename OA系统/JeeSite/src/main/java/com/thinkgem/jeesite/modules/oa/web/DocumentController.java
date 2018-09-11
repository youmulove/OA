package com.thinkgem.jeesite.modules.oa.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "${adminPath}/oa/document")
public class DocumentController {
	@RequestMapping("returnPage")
	public String returnPage(){
		return "modules/oa/document/index";
	}
	
}
