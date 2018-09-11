<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/modules/cms/front/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<!-- 组织机构页面 -->
	<title>${category.name}</title>
	<meta name="decorator" content="cms_default_${site.theme}"/>
	<meta name="description" content="${category.description}" />
	<meta name="keywords" content="${category.keywords}" />
	<style>
		h4{
			margin:5px 0;
		}
		.c-asideHeader{
			margin:5px 0;
			background-color:#265FAE;
			border:none;
			color:#fff;
			text-align:center;
			font-weight:500;
			font-size:16px;
		}
		.c-aside ul,.c-aside ol{
			margin:0;
		}
		.c-aside li{
			width:110px;
			padding:5px;
			margin:5px auto;
			list-style:none;
			background-color:#E5E5E5;
			text-align:center;
			color:#265FAE;
		}
		.c-span10{
			margin-left:30px;
		}
		.c-span10 .c-span5{
			margin:0;
		}
		.c-span5Title {
		    display: inline-block;
		    height: 36px;
		    padding: 0 20px;
		    line-height: 36px;
		    float: none;
		    margin: 0;
		    background-color: #265FAE;
		    color: #fff;
		    font-weight: 500;
		}
		.c-span5 h4{
			border: none;
		    padding: 0;
		    border-bottom: 2px solid #265FAE;
		    background-color: #fff;
		}
		a:hover, a:active{
			color:#282828;
		}
		.c-span5 li{
			margin:5px 0;
			list-style:none;
		}
	</style>
</head>
<body>
	<div class="row">
	   <div class="span2 c-aside">
	   	 <h4 class="c-asideHeader">栏目列表</h4>
		 <ol>
		 	<cms:frontCategoryList categoryList="${categoryList}"/>
		 </ol>
		 <h4 class="c-asideHeader">推荐阅读</h4>
		 <ol>
		 	<cms:frontArticleHitsTop category="${category}"/>
		 </ol>
	   </div>
	   <div class="span10 c-span10">
		 <ul class="breadcrumb" style="margin:5px 0; padding:0; background-color:#fff;">
		    <cms:frontCurrentPosition category="${category}"/>
		 </ul>
	   </div>
	   <div class="span10 c-span10">
	      <c:set var="index" value="1"/>
		  <c:forEach items="${categoryList}" var="tpl">
			<c:if test="${tpl.inList eq '1' && tpl.module ne ''}">
				<c:set var="index" value="${index+1}"/>
				<%-- ${index % 2 eq 0 ? '<div class="row">':''} --%>
		    	<div class="span5 c-span5" style="width:100%;">
		    		<h4><span class="c-span5Title">${tpl.name}</span><small class="pull-right" style="margin-top:10px;"><a href="${ctx}/list-${tpl.id}${urlSuffix}">更多&gt;&gt;</a></small></h4>
					<c:if test="${tpl.module eq 'article'}">
		    			<ul><c:forEach items="${fnc:getArticleList(site.id, tpl.id, 5, '')}" var="article">
							<li><span class="pull-right"><fmt:formatDate value="${article.updateDate}" pattern="yyyy.MM.dd"/></span><a href="${ctx}/view-${article.category.id}-${article.id}${urlSuffix}"<%--  style="color:${article.color} --%>">${fns:abbr(article.title,40)}</a></li>
						</c:forEach></ul>
					</c:if>
					<c:if test="${tpl.module eq 'link'}">
		    			<ul><c:forEach items="${fnc:getLinkList(site.id, tpl.id, 5, '')}" var="link">
							<li><span class="pull-right"><fmt:formatDate value="${link.updateDate}" pattern="yyyy.MM.dd"/></span><a target="_blank" href="${link.href}" <%-- style="color:${link.color}" --%>>${fns:abbr(link.title,40)}</a></li>
						</c:forEach></ul>
					</c:if>
		    	</div>
		    	<%-- ${index % 2 ne 0 ? '</div>':''} --%>
			</c:if>
		  </c:forEach>
	   </div>
	</div>
	<!-- 页面加载完成后页脚宽度不正确，觉的是因为标签不完整导致，添加一个 div 结尾标签，找不到是哪个地方少标签 -->
	</div>
</body>
</html>