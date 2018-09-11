<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/modules/cms/front/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>首页</title>
	<meta name="decorator" content="cms_default_${site.theme}"/>
	<meta name="description" content="JeeSite ${site.description}" />
	<meta name="keywords" content="JeeSite ${site.keywords}" />
	<style>
		/* 	三个列表 */
	.content .row{
		margin:0;
	}
	.content .span4{
		margin:10px 0;
	}
	.content .span4>h4{
		border:none;
		padding:0;
		border-bottom: 2px solid #265FAE;
	    background-color: #fff;
	}
	.content .span4 ul{
		margin:10px 0 0 0;
	}
	.content .span4 li{
		list-style:none;
	}
	.content .span4 li a{
		color:#666;
	}
	/* .content .span4:nth-child(1){
		width:100%;
	} */
	/* .content .span4:nth-child(1)>h4{
		color:#61C4FA;
	} */
	.content .span4:nth-child(1) li:before{
		content:"";
		display:inline-block;
		width:8px;
		height:8px;
		margin-right:8px;
		margin-bottom:1px;
		border-radius:50%;
		background-color:#61C4FA;
	}
	.content .span4:nth-child(2) li:before{
		content:"";
		display:inline-block;
		width:8px;
		height:8px;
		margin-right:8px;
		margin-bottom:1px;
		border-radius:50%;
		background-color:#FCD37D;
	}
	.content .span4:nth-child(3) li:before{
		content:"";
		display:inline-block;
		width:8px;
		height:8px;
		margin-right:8px;
		margin-bottom:1px;
		border-radius:50%;
		background-color:#BF93ED;
	}
	/* .content .span4:nth-child(2)
	,.content .span4:nth-child(3){
		margin:1%;
		width:49%;
	} */
	.content .span4{
		margin:1%;
		width:49%;
	}
	/* .content .span4 ul{
		height: 190px;
    	overflow: hidden;
    } */
	.content .span4:nth-child(odd){
		margin-left:0;
	}
	/* .content .span4:nth-child(2)>h4{
		color:#FCD37D;
	} */
	.content .span4:nth-child(even){
		margin-right:0;
	}
	/* .content .span4:nth-child(3)>h4{
		color:#BF93ED;
	} */
	.content .span4 .c-span4Title{
		display:inline-block;
		height:36px;
		padding:0 20px;
		line-height:36px;
		float:none;
		margin:0;
		background-color:#265FAE;
		color:#fff;
		font-weight:500;
	}
	.ziGongSi{
		width:100%;
	}
	.ziGongSi .zi__title{
		width:100%;
		border:none;
	    border-bottom: 1px solid #bababa;
	    background-color: #fff;
	    padding: 5px;
	    margin: 10px 0 5px;
	    color:#51E3BE;
	}
	.ziGongSi .zi__content{
		width:100%;
	}
	.ziGongSi .zi__content ul{
		margin:0;
	}
	.ziGongSi .zi__content li{
		float:left;
		width:11.5%;
		margin:1%;
		list-style:none;
		text-align:center;
	}
	 .c-heroUnit{
	 	position:relative;
	 	width:1080px;
	 	height:450px;
	 	background:url("${ctxStatic}/newImage/banner.jpg") no-repeat;
	 	background-size:cover;
	 	padding:0;
	 	border-radius:4px;
	 	border:1px solid #eee;
	 }
	 .c-heroUnit h1{ 
	 	position:absolute;
	 	top:60px;
	 	left:600px;
	 	width:460px;
	 	height:80px;
	 	overflow:hidden;
	 	font-size:26px;
	 	font-weight:500;
	 }
	 .c-heroUnit>p{ 
	 	position:absolute;
	 	top:165px;
	 	left:600px;
	 }
	 .c-heroUnit .c-button{
	 	width:100px;
	 	height:60px;
	 	padding:10px 30px;
	 	border:2px solid #63C3F7;
	 	border-radius:4px;
	 	color:#63C3F7;
	 	background-color:rgba(255,255,255,.5);
	 	font-size:18px;
	 	text-decoration:none
	 }
	 .c-aisle{
	 	margin:10px 0;
	 }
	 .c-aisle ul{
	 	margin:0;
	 }
	 .c-aisle li{
	 	float:left;
	  	width:300px;
	  	height:240px;
	  	border-radius:4px;
	  	background-color:#00CED7;
	  	list-style:none;
	 }
	 .c-aisle li>a{
	 	display:block;
	 	width:100%;
	 	height:100%;
	 	text-align:center;
	 	font-size:21px;
	 	color:#fff;
	 }
	 .c-aisle li:nth-child(2){
	 	margin:0 90px;
	 	background-color:#67BBE7;
	 	/* background: url(${ctxStatic}/newImage/indexDoor5.png) no-repeat;
	 	background-size:cover; */
	 }
	 .c-aisle li:nth-child(3){
	 	background-color:#95ADED;
	 }
	 .c-aisle li>a>.c-moduleBg{
	 	display:block;
	 	width:102px;
	 	height:102px;
	 	margin:40px auto 30px;
	 }
	 .c-aisle li>a>.c-moduleTxt{
	 	display:block;
	 }
	 .c-aisle li:nth-child(1) .c-moduleBg{
	 	background: url(${ctxStatic}/newImage/indexDoor1.png) no-repeat;
	 	background-size:cover;
	 }
	 .c-aisle li:nth-child(2) .c-moduleBg{
	 	background: url(${ctxStatic}/newImage/indexDoor2.png) no-repeat;
	 	background-size:cover;
	 }
	 .c-aisle li:nth-child(3) .c-moduleBg{
	 	background: url(${ctxStatic}/newImage/indexDoor3.png) no-repeat;
	 	background-size:cover;
	 }
	 .c-aisle .c-center{
	 	width:102px;
	 	height:102px;
	 	margin:30px auto;
	 }
	 .c-aisle a{
	 	text-decoration:none
	 }
	 .c-aisle p{
	 	width:100px;
	 	margin:20px auto;
	 	color:#fff;
	 	font-size:18px;
	 	font-weight:600px;
	 	text-align:center;
	 }
	 .span4 li{
	 	margin:3px 0;
	 }
	</style>
</head>
<body>
    <div class="c-heroUnit" style="/* padding-bottom:35px; */margin:10px 0;">
     <%--  <c:set var="article" value="${fnc:getArticle('2')}"/>
      <h1>${fns:abbr(article.title,28)}</h1><!-- <p></p> -->
      <p>${fns:abbr(fns:replaceHtml(article.articleData.content),260)}</p>
      <p><a href="${article.url}" class="c-button">查看详情 &raquo;</a></p> --%>
    </div> 
    <div class="c-aisle clearfix">
    	<ul>
    		<c:forEach items="${fnc:getMainNavList(site.id)}" var="category" varStatus="status">
    			<c:if test="${status.index gt 3 and status.index lt 7}">
                <c:set var="menuCategoryId" value=",${category.id},"/>
		    		<li class="${requestScope.category.id eq category.id||fn:indexOf(requestScope.category.parentIds,menuCategoryId) ge 1?'active':''}">
		    			<a class="clearfix" href="${category.url}" target="${category.target}">
		    				<b class="c-moduleBg"></b>
		    				<span class="c-moduleTxt">${category.name}</span>
		    			</a>
		    		</li>
		    	</c:if>
		    </c:forEach>
    		<%-- <li>
    			<a href="${ctx}/list-2${urlSuffix}">
	    			<div class="c-center"><img src="${ctxStatic}/newImage/indexDoor2.png"/></div>
	    			<p>组织机构</p>
    			</a>
    		</li>
    		<li>
    			<a href="${ctx}/list-6${urlSuffix}">
	    			<div class="c-center"><img src="${ctxStatic}/newImage/indexDoor1.png"/></div>
	    			<p>质量监督</p>
    			</a>
    		</li>
    		<li>
    			<a href="${ctx}/list-10${urlSuffix}">
	    			<div class="c-center"><img src="${ctxStatic}/newImage/indexDoor3.png"/></div>
	    			<p>政策法规</p>
    			</a>
    		</li> --%>
    	</ul>
    </div>
    <div class="row j-main">
    	
    		<c:forEach items="${fnc:getMainNavList(site.id)}" var="category" varStatus="status">
    			<c:if test="${status.index lt 4}">
    				<div class="span4 ">
	                    <c:set var="menuCategoryId" value=",${category.id},"/>
			    		<%-- <li class="${requestScope.category.id eq category.id||fn:indexOf(requestScope.category.parentIds,menuCategoryId) ge 1?'active':''}"> --%>
			    			<h4<%--  href="${category.url}" target="${category.target}" --%>><span class="c-span4Title">${category.name}</span></h4>
			    			<div style="height:190px; overflow:hidden;">
				    			<ul>
					    			<c:forEach items="${fnc:getArticleList(site.id, category.id, 15, '')}" var="article">
										<li>
											<span class="pull-right"><fmt:formatDate value="${article.updateDate}" pattern="yyyy.MM.dd"/></span>
											<a href="${article.url}"<%--  style="color:${article.color}" --%>>${fns:abbr(article.title,28)}</a>
										</li>
									</c:forEach>
								</ul>
							</div>
			    		<!-- </li> -->
		    		</div>
		    	</c:if>
		    	
		    </c:forEach>
		  
      <%-- <div class="span4">
        <h4><span class="c-span4Title">组织机构</span><small><a href="${ctx}/list-2${urlSuffix}" class="pull-right">更多&gt;&gt;</a></small></h4>
		<ul><c:forEach items="${fnc:getArticleList(site.id, 0, 8, '')}" var="article">
			<li><span class="pull-right"><fmt:formatDate value="${article.updateDate}" pattern="yyyy.MM.dd"/></span><a href="${article.url}" style="color:${article.color}">${fns:abbr(article.title,28)}</a></li>
		</c:forEach></ul>
      </div>
      <div class="span4 j-listGd">
        <h4><span class="c-span4Title">组织机构</span><small><a href="${ctx}/list-2${urlSuffix}" class="pull-right">更多&gt;&gt;</a></small></h4>
		<div style="height:190px; overflow:hidden;">
			<ul><c:forEach items="${fnc:getArticleList(site.id, 2, 16, '')}" var="article">
				<li><span class="pull-right"><fmt:formatDate value="${article.updateDate}" pattern="yyyy.MM.dd"/></span><a href="${article.url}" style="color:${article.color}">${fns:abbr(article.title,28)}</a></li>
			</c:forEach></ul>
		</div>
      </div>
      <div class="span4">
        <h4><span class="c-span4Title">质量监督</span> <small><a href="${ctx}/list-6${urlSuffix}" class="pull-right">更多&gt;&gt;</a></small></h4>
		<ul><c:forEach items="${fnc:getArticleList(site.id, 6, 8, '')}" var="article">
			<li><span class="pull-right"><fmt:formatDate value="${article.updateDate}" pattern="yyyy.MM.dd"/></span><a href="${article.url}" style="color:${article.color}">${fns:abbr(article.title,28)}</a></li>
		</c:forEach></ul>
      </div>
      <div class="span4">
        <h4><span class="c-span4Title">政策法规</span><small><a href="${ctx}/list-10${urlSuffix}" class="pull-right">更多&gt;&gt;</a></small></h4>
		<ul><c:forEach items="${fnc:getArticleList(site.id, 10, 8, '')}" var="article">
			<li><span class="pull-right"><fmt:formatDate value="${article.updateDate}" pattern="yyyy.MM.dd"/></span><a href="${article.url}" style="color:${article.color}">${fns:abbr(article.title,28)}</a></li>
		</c:forEach></ul>
      </div> --%>
    </div>
    <!-- <div class="ziGongSi clearfix">
			<h4 class="zi__title">
				子公司
			</h4>
			<div class="zi__content">
				<ul>
					<li>
						<a href="javascript:;">
							<div class="zi--logo"><img src="${ctxStatic}/newImage/zigongsi.jpg"/></div>
							<span>中浩科技</span>
						</a>
					</li>
					<li>
						<a href="javascript:;">
							<div class="zi--logo"><img src="${ctxStatic}/newImage/zigongsi.jpg"/></div>
						</a>
					</li>
					<li>
						<a href="javascript:;">
							<div class="zi--logo"><img src="${ctxStatic}/newImage/zigongsi.jpg"/></div>
						</a>
					</li>
					<li>
						<a href="javascript:;">
							<div class="zi--logo"><img src="${ctxStatic}/newImage/zigongsi.jpg"/></div>
						</a>
					</li>
					<li>
						<a href="javascript:;">
							<div class="zi--logo"><img src="${ctxStatic}/newImage/zigongsi.jpg"/></div>
						</a>
					</li>
					<li>
						<a href="javascript:;">
							<div class="zi--logo"><img src="${ctxStatic}/newImage/zigongsi.jpg"/></div>
						</a>
					</li>
					<li>
						<a href="javascript:;">
							<div class="zi--logo"><img src="${ctxStatic}/newImage/zigongsi.jpg"/></div>
						</a>
					</li>
					<li>
						<a href="javascript:;">
							<div class="zi--logo"><img src="${ctxStatic}/newImage/zigongsi.jpg"/></div>
						</a>
					</li>
					<li>
						<a href="javascript:;">
							<div class="zi--logo"><img src="${ctxStatic}/newImage/zigongsi.jpg"/></div>
						</a>
					</li>
				</ul>
			</div>
		</div> -->
<script>
    (function($){
      $.fn.myScroll = function(options){//创建一个jquery类扩展函数（原型函数）
        //默认配置
        var defaults = {
          speed:100,  //滚动速度,值越大速度越慢
          rowHeight:24 //每行的高度
        };
        //将用户设置的参数options与默认配置defaults合并到前面的空对象{}中，
        var opts = $.extend({}, defaults, options),intId = [];

        function marquee(obj, step){
          obj.find("ul").animate(
          {//规定产生动画效果的 CSS 样式和值
            marginTop: '-=1'
          },
          0,//规定动画运行的速度（可为毫秒数）
          function(){//动画运行完成后调用函数
            var s = Math.abs(parseInt($(this).css("margin-top")));
            if(s >= step){
              $(this).find("li").slice(0, 1).appendTo($(this));
              $(this).css("margin-top", 0);
            }
          });
        }

        this.each(function(i){
          //每行的高度                //滚动的速度           //即绑调用函数的div
          var sh = opts["rowHeight"],speed = opts["speed"],_this = $(this);
          intId[i] = setInterval(function(){
            if(_this.find("ul").height() <= _this.height()){
              //如果当前ul的高度小于ul父元素div的高度，则清除当前定时器
              clearInterval(intId[i]);
            }else{
              marquee(_this, sh);
            }
          }, speed);
          _this.hover(function(){
            clearInterval(intId[i]);
          },function(){
            intId[i] = setInterval(function(){
              if(_this.find("ul").height()<=_this.height()){
                clearInterval(intId[i]);
              }else{
                marquee(_this, sh);
              }
            }, speed);
          });

        });
      }
    })(jQuery);
    $(document).ready(function(){
      $(".j-main").children(".span4").eq(1).addClass("j-listGd");
      $("div.j-listGd").myScroll({
        speed:40, //数值越大，速度越慢
        rowHeight:23 //li的高度
      });
    })
  </script>		
</body>
</html>