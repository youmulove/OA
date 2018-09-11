<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/modules/cms/front/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>${article.title} - ${category.name}</title>
	<meta name="decorator" content="cms_default_${site.theme}"/>
	<meta name="description" content="${article.description} ${category.description}" />
	<meta name="keywords" content="${article.keywords} ${category.keywords}" />
	<script type="text/javascript">
		$(document).ready(function() {
			if ("${category.allowComment}"=="1" && "${article.articleData.allowComment}"=="1"){
				$("#comment").show();
				page(1);
			}
		});
		function page(n,s){
			$.get("${ctx}/comment",{theme: '${site.theme}', 'category.id': '${category.id}',
				contentId: '${article.id}', title: '${article.title}', pageNo: n, pageSize: s, date: new Date().getTime()
			},function(data){
				$("#comment").html(data);
			});
		}
	</script>
	<style>
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
		.c-span10Title{
			display:block;
			width:100%;
			border: none;
		    padding: 0;
		    margin:0;
		    margin-bottom:5px;
		    border-bottom: 2px solid #265FAE;
		    background-color: #fff;
		}
		.c-span10Title>span {
		    display: inline-block;
		    height: 30px;
		    padding: 0 15px;
		    line-height: 30px;
		    float: none;
		    margin: 0;
		    background-color: #265FAE;
		    color: #fff;
		    font-weight: 500;
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
	   <div class="span10">
	     <div class="row">
	       <div class="span10">
			<h3 style="color:#555555;font-size:20px;text-align:center;border-bottom:1px solid #ddd;padding-bottom:15px;margin:25px 0;">${article.title}</h3>
			<c:if test="${not empty article.description}"><div>摘要：${article.description}</div></c:if>
			<div>${article.articleData.content}</div>
			<div style="border-top:1px solid #ddd;padding:10px;margin:25px 0;">发布者：${article.user.name} &nbsp; 点击数：${article.hits} &nbsp; 发布时间：<fmt:formatDate value="${article.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/> &nbsp; 更新时间：<fmt:formatDate value="${article.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/></div>
  	       </div>
  	     </div>
	     <div class="row">
			<div id="comment" class="hide span10">
				正在加载评论...
			</div>
	     </div>
	     <div class="row">
	       <div class="span10">
			<h5 class="c-span10Title"><span>相关文章</span></h5>
			<ol><c:forEach items="${relationList}" var="relation">
				<li style="float:left;width:230px;"><a href="${ctx}/view-${relation[0]}-${relation[1]}${urlSuffix}">${fns:abbr(relation[2],30)}</a></li>
			</c:forEach></ol>
	  	  </div>
  	    </div>
  	  </div>
   </div>
</body>
</html>