<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!-- 审批人信息查看 -->
	 <c:set var="startIndex" value="${fn:length(list)-1 }"></c:set>  
   <c:forEach items="${list}" varStatus="i" var="item" >
   	<li class="person">
   		<label class="form--title form--star" for="">第${i.index+1}审批人</label>
   		<c:if test="${ not empty list[startIndex-i.index].approvalPerson}">
   			<input type="text" name="app" readonly="readonly" value="${list[startIndex-i.index].approvalPerson}" placeholder="4"/>
   		</c:if>
   		<c:if test="${ not empty list[startIndex-i.index].approvalRank}">
			<sys:treeselect id="app${i.index+1}" name="sysapp${i.index+1}" cssplaceholder="按职级选择审批人" value="${oaNotify.oaNotifyRecordIds}" labelName="approverPostName" labelValue="${oaNotify.oaNotifyRecordNames}"
			title="用户" url="/sys/user/useData?sysrankId=${list[startIndex-i.index].approvalRank}" cssClass="input-xxlarge required c-inputStyle" />
   		</c:if>
   	</li>	
   		
   </c:forEach>
   