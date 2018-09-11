<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!-- 审批人信息查看 -->
<c:choose>
	<c:when test="${fn:length(list)>0}">
		<c:set var="startIndex" value="${fn:length(list)-1 }"></c:set>
		<c:forEach items="${list}" varStatus="i" var="item">
			<li class="person"><label class="form--title form--star" for="">第${i.index+1}审批人</label>
				<c:if test="${ not empty list[startIndex-i.index].approvalPerson}">
					<input type="text" name="app" readonly="readonly"
						value="${list[startIndex-i.index].approvalPerson}" placeholder="4" />
					<input type="hidden" name="app1" readonly="readonly"
						value="${list[0].approvalYuliu5}" placeholder="4" />
					<input type="hidden" name="app2" readonly="readonly"
						value="${fns:getUser().id}" placeholder="4" />
				</c:if> <c:if test="${ not empty list[startIndex-i.index].approvalRank}">
					<sys:treeselect id="app${i.index+1}" name="sysapp${i.index+1}"
						cssplaceholder="按职级选择审批人" value="${oaNotify.oaNotifyRecordIds}"
						labelName="approverPostName"
						labelValue="${oaNotify.oaNotifyRecordNames}" title="用户"
						url="/sys/user/useData?sysrankId=${list[startIndex-i.index].approvalRank}"
						cssClass="input-xxlarge required c-inputStyle" />
					<input type="hidden" name="app2" readonly="readonly"
						value="${fns:getUser().id}" placeholder="4" />
				</c:if>
			</li>
		</c:forEach>
	</c:when>

	<c:when test="${fn:length(list)<=0 && fn:length(freeRule)>0}">
	<li class="person">
			<label class="form--title form--star" for="">第一审批人：</label>
				<sys:treeselect id="freePerson1" name="freePerson1"
					value="${freePerson1}" labelName="freePersonName1"
					labelValue="${freePersonName1}" title="用户"
					url="/sys/office/treeData?type=3" cssClass="input-medium required"
					notAllowSelectParent="true" allowClear="true" />
	</li>
	<li class="person">
			<label class="form--title form--star" for="">第二审批人：</label>
				<sys:treeselect id="freePerson2" name="freePerson2"
					value="${freePerson2}" labelName="freePersonName2"
					labelValue="${freePersonName2}" title="用户"
					url="/sys/office/treeData?type=3" cssClass="input-medium "
					notAllowSelectParent="true" allowClear="true" />
	</li>
	<li class="person">
			<label class="form--title form--star" for="">第三审批人：</label>
				<sys:treeselect id="freePerson3" name="freePerson3"
					value="${freePerson3}" labelName="freePersonName3"
					labelValue="${freePersonName3}" title="用户"
					url="/sys/office/treeData?type=3" cssClass="input-medium "
					notAllowSelectParent="true" allowClear="true" />
	</li>
	<li class="person">
			<label class="form--title form--star" for="">第四审批人：</label>
				<sys:treeselect id="freePerson4" name="freePerson4"
					value="${freePerson4}" labelName="freePersonName4"
					labelValue="${freePersonName4}" title="用户"
					url="/sys/office/treeData?type=3" cssClass="input-medium "
					notAllowSelectParent="true" allowClear="true" />
	</li>
	</c:when>
</c:choose>

