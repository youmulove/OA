<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!-- 审批人信息查看 -->
	 <c:set var="startIndex" value="${fn:length(list)-1 }"></c:set>  
   <c:forEach items="${list}" varStatus="i" var="item" >
   	<div class="form-list">
   		<label class="form--title form--star" for=""><span>*</span>第${i.index+1}审批人：</label>
   		 <div class="form--input">
   		<c:if test="${ not empty list[startIndex-i.index].approvalPerson}">
   			<input class="required error" style="width:220px;" type="text" name="app" readonly="readonly" value="${list[startIndex-i.index].approvalPerson}" placeholder="4"/>
   		</c:if>
   		<c:if test="${ not empty list[startIndex-i.index].approvalRank}">
			<sys:treeselect id="app${i.index+1}" name="sysapp${i.index+1}" cssplaceholder="按职级选择审批人" value="${oaNotify.oaNotifyRecordIds}" labelName="approverPostName" labelValue="${oaNotify.oaNotifyRecordNames}"
			title="用户" url="/sys/user/useData?sysrankId=${list[startIndex-i.index].approvalRank}" cssClass="input-xxlarge required c-inputStyle" cssStyle="width:100%;"/>
   		</c:if>
   		</div>
   	</div>	
   	<!-- <div class="form-list"><label for=""><span>*</span>使用时长(小时)：</label>

            <div class="form--input"><input class="required error" style="width:70%;" name="hours" placeholder="1" type="text" value=""></div>
          </div> -->
   		
   </c:forEach>
   