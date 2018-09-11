<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/modules/cms/front/include/taglib.jsp"%>
<%@ taglib prefix="sitemesh" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<!DOCTYPE html>
<html>
<head>
	<title><sitemesh:title default="欢迎光临"/> - ${site.title} - Powered By JeeSite</title>
	<%@include file="/WEB-INF/views/modules/cms/front/include/head.jsp" %>
	<!-- Baidu tongji analytics --><script>var _hmt=_hmt||[];(function(){var hm=document.createElement("script");hm.src="//hm.baidu.com/hm.js?82116c626a8d504a5c0675073362ef6f";var s=document.getElementsByTagName("script")[0];s.parentNode.insertBefore(hm,s);})();</script>
	<sitemesh:head/>
	<style>
		html,body{
			margin:0;
			padding:0;
		}
		.width--1080{
			width:1080px;
			margin:0 auto;
		}
		.newNav{
			height:50px;
			padding-bottom: 8px;
			line-height:50px;
			width:100%;
			background-color:#fff;
			/* overflow:hidden; */
			border-bottom:2px solid #265FAE;
		}
		.newNav .newNav--logo{
			float:left;
			margin-left:30px;
		}
		.newNav .newNav__navbar{
			float:left;
			width:950px;
			margin-left:30px;
		}
		.newNav .newNav__navbar li{
			float:left;
			width:80px;
			line-height:inherit;
			list-style:none;
			text-align:center;
		}
		.newNav .newNav__navbar li a{
			font-size:15px;
		}
		.newNav .newNav__navbar li{
			/* -webkit-transition: all ease-in-out 0.4s;
		    -o-transition: all ease-in-out 0.4s;
		    transition: all ease-in-out 0.4s; */
		    cursor:pointer;
		}
		.newNav .newNav__navbar li:hover{
			border-bottom:2px solid #73BCED;
		}
		.newNav .newNav__navbar li:hover a{
			text-decoration:none;
			color:#73BCED;
		}
		.newNav .newNav__navbar li.newActive{
			border-bottom:2px solid #73BCED;
		}
		.newNav .newNav__navbar li.newActive a{
			color:#73BCED;
		}
		/* 页脚 */
		.footer{
			/* width:100%; */
			padding:30px;
			background-color:#303036;
			text-align:center;
			color:#fff;
			line-height:30px;
		}
		.footer a{
			color:#fff;
		}

	</style>
</head>
<body>
	<div class="newNav" style="position:static;margin-bottom:10px;">
      <div class="width--1080" style="width:1200px;">
        <!-- <div class=""> -->
          <c:choose>
   			<c:when test="${not empty site.logo}">
   				<img alt="${site.title}" src="${site.logo}" class="container" onclick="location='${ctx}/index-${site.id}${fns:getUrlSuffix()}'">
   			</c:when>
   			<c:otherwise><a class="newNav--logo" href="${ctx}/index-${site.id}${fns:getUrlSuffix()}"><img style="width:110px; height:40px;" src="${ctxStatic}/newImage/logo.png"/></a></c:otherwise>
   		  </c:choose>
          <div class="nav-collapse newNav__navbar">
            <ul style="margin:0;" id="main_nav" class="">
             	<li class="${not empty isIndex && isIndex ? 'newActive' : ''}"><a href="${ctx}/index-1${fns:getUrlSuffix()}"><span>${site.id eq '1'?'首　 页':'返回主站'}</span></a></li>
				<c:forEach items="${fnc:getMainNavList(site.id)}" var="category" varStatus="status"><c:if test="${status.index lt 8}">
                    <c:set var="menuCategoryId" value=",${category.id},"/>
		    		<li class="${requestScope.category.id eq category.id||fn:indexOf(requestScope.category.parentIds,menuCategoryId) ge 1?'active':''} "><a href="${category.url}" target="${category.target}"><span>${category.name}</span></a></li>
		    	</c:if></c:forEach>
		    	<li class=""><a href="/jeesite${fns:getAdminPath()}/login" target="view_window"><span>OA网页版</span></a></li>
			    <%-- <li id="siteSwitch" class="dropdown">
			       	<a class="dropdown-toggle" data-toggle="dropdown" href="#" title="站点"><i class="icon-retweet"></i></a>
					<ul class="dropdown-menu">
					  <c:forEach items="${fnc:getSiteList()}" var="site"><li><a href="#" onclick="location='${ctx}/index-${site.id}${urlSuffix}'">${site.title}</a></li></c:forEach>
					</ul>
				</li>
		    	<li id="themeSwitch" class="dropdown">
			       	<a class="dropdown-toggle" data-toggle="dropdown" href="#" title="主题切换"><i class="icon-th-large"></i></a>
				    <ul class="dropdown-menu">
				      <c:forEach items="${fns:getDictList('theme')}" var="dict"><li><a href="#" onclick="location='${pageContext.request.contextPath}/theme/${dict.value}?url='+location.href">${dict.label}</a></li></c:forEach>
				    </ul>
				    <!--[if lte IE 6]><script type="text/javascript">$('#themeSwitch').hide();</script><![endif]-->
			    </li> --%>
            </ul>
            <%-- <form style="float:right; margin:0;" action="${ctx}/search" method="get">
              	<input type="text" name="q" maxlength="20" style="width:160px; height:16px; margin:0;" placeholder="全站搜索..." value="${q}">
            </form> --%>
          </div><!--/.nav-collapse -->
        <!-- </div> -->
      </div>
    </div>
	<div class="width--1080">
		<!-- <div>
			<img src="/jeesite/static/newImage/banner.jpg"/>
		</div> -->
		<div class="content">
			<sitemesh:body/>
		</div>
		
		<hr style="margin:20px 0 10px;"/>
    </div> <!-- /container -->
    <footer class="footer">
		<div class="footer__blogroll"><a href="" target="_blank">河南物资集团</a> | <a href="" target="_blank">郑州国际陆港</a> | <a href="" target="_blank">中浩科技</a> | <a href="">中陆贸易</a> | <a href="" target="_blank">班列购</a></div>
		<%-- <div class="footer__bottom">${fns:getDate('yyyy年MM月dd日 E')}</div> --%>
		<div class="footer__copyright">${site.copyright}</div>
   	</footer>
    
</body>
</html>