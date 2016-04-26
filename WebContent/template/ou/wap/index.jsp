<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/template/ou/module/common/taglibs.jsp" %>
<c:set value="${sdk:getLoginUser()}" var="loginUser"/> <%--获取当前用户--%>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <meta name="keywords" content="${sdk:getSysParamValue('index_keywords')}"/>
    <%--SEO keywords优化--%>
    <meta name="description" content="${sdk:getSysParamValue('index_description')}"/>
    <%--SEO description优化--%>
    <title>${webName}-首页</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <link href="${webRoot}/template/ou/wap/statics/css/header.css" type="text/css" rel="stylesheet" />
    <link href="${webRoot}/template/ou/wap/statics/css/index.css" type="text/css" rel="stylesheet" />
    <link href="${webRoot}/template/ou/wap/statics/css/swiper.min.css" type="text/css" rel="stylesheet" />
    <%--<link href="${webRoot}/template/ou/wap/statics/css/bootstrap.min.css" type="text/css" rel="stylesheet" />--%>
    <script src="${webRoot}/template/ou/wap/statics/js/base.js" type="text/javascript"> </script>
    <script src="${webRoot}/template/ou/wap/statics/js/jquery-1.10.2.min.js" type="text/javascript"></script>
    <%--<script src="${webRoot}/template/ou/wap/statics/js/bootstrap.min.js"></script>--%>
    <script type="text/javascript" src="${webRoot}/template/ou/wap/statics/js/jquery-1.7.1.min.js"></script>
    <script type="text/javascript" src="${webRoot}/template/ou/wap/statics/js/jquery.event.drag-1.5.min.js"></script>
    <%--<script type="text/javascript" src="${webRoot}/template/ou/wap/statics/js/jquery.touchSlider.js"></script>--%>
    <script type="text/javascript" src="${webRoot}/template/ou/wap/statics/js/flashTime.js"></script>
    <script type="text/javascript" src="${webRoot}/template/ou/wap/statics/js/index.js"></script>
    <script type="text/javascript" src="${webRoot}/template/ou/wap/statics/js/jquery.infinitescroll.min.js"></script>
    <script type="text/javascript" src="${webRoot}/template/ou/wap/statics/js/swiper-3.2.7.jquery.min.js"></script>
    <script type="text/javascript">
        var webPath = {
            webRoot:"${webRoot}",
            systemTime:"<fmt:formatDate value="${systemTime}" type="both" dateStyle="long" pattern="yyyy/MM/dd HH:mm:ss" />",
            from:"index"   //搜索可选
        };
        var readedpage = 1;//当前滚动到的页数
        var bannerSwiper;
        $(function(){
            $("#best-sale").infinitescroll({
                        navSelector: "#page-nav",     //页面分页元素--成功后自动隐藏
                        nextSelector: "#page-nav a",
                        itemSelector: "#mc-list" ,
                        animate: true,
                        loading: {
                            <%--msgText:"<div class='bs-loading'><img src='${webRoot}/template/ou/wap/statics/images/loading.png'></div>",--%>
                            msgText:'',      /*去掉插件的默认的提示文本和最后结束的提示文本*/
                            finishedMsg: '',
                            img:"${webRoot}/template/ou/wap/statics/images/loading.gif"
                        },
                        extraScrollPx: 100
                    }, function(newElements) {
                        readedpage++;
                        if(readedpage>${articleProxys.lastPageNumber}){//如果滚动到超过最后一页，置成不要再滚动。
                            $("#page-nav").remove();
                            $("#best-sale").infinitescroll({state:{isDone:true},extraScrollPx: 50});
                        }else{
                            $("#page-nav a").attr("href","${webRoot}/wap/indexContent.ac?page="+readedpage);
                        }
                    }
            );
            for(var i=1;i<=7;i++) {   //7个楼层
//                console.log("个数:" +$('#swiper-container'+i).find(".swiper-slide").length)
                if($('#swiper-container'+i).find(".swiper-slide").length > 3){  //3个以上才给滑动

               new Swiper('#swiper-container'+i, {
//                pagination: '.swiper-pagination',
                    slidesPerView: 'auto',         //自动根据slides的宽度来设定数量
//                    paginationClickable: true,
//                    spaceBetween: 30,
                    loop : true       //无限循环
                });
            }
            }
            $(".gotop").click(function() {  //回到顶部
                $(document).scrollTop(0);
            });
            bannerSwiper = new Swiper('#bannerSwiper', {
                pagination: '.sd-cont',
                paginationClickable: true,
                autoplay: 1000,      //自动切换时间 ms
                autoplayDisableOnInteraction: false,     //用户操作swiper之后自动切换不会停止
//                slidesPerView: 1,
                loop: true
            });

            new Swiper('#saleSwiper', {
                pagination: '.theme_nav',
                paginationClickable: true,
                //autoplay: 1000,      //自动切换时间 ms
                //autoplayDisableOnInteraction: false,     //用户操作swiper之后自动切换不会停止
                loop: true
            });
        });
    </script>
    <script type="text/javascript">
        if (window.parent.location.href.search(/admin.jsp/) > -1 && window.location.href.search(/\/wap\//) > -1) {  /*后台地址并且装修页面地址包含/wap/路径 */
            var iframe = window.parent.document.getElementsByTagName("iframe");
//          console.log(iframe[0].style.width)
//        console.log(iframe[0] == null);
            if (iframe[0] != null && iframe[0].style.width != '33%') {
                iframe[0].style.width = "33%";
                iframe[0].style.margin = "0 33.3%";
            }
        }
    </script>
</head>

<body>

<header class="header">
    <div class="logo frameEdit" frameInfo="logo_mobile|140X149"><%--<div class="logo"><img src="image/logo-120x50.png" alt="酷动数码"></div>--%>
        <c:forEach items="${sdk:findPageModuleProxy('logo_mobile').advt.advtProxy}" var="advtProxys" varStatus="s" end="1">
            <a id="${s.count}" href="${advtProxys.link}"><img src="${advtProxys.advUrl}"  alt="${advtProxys.hint}" ></a>
        </c:forEach>
    </div>
    <form id="searchForm" action="${webRoot}/wap/search.ac" method="get">
        <div class="search-input" id="search-input" style="margin:0 7px 0 7.8rem;">         <%--定义--%>
            <div class="sch-cont">
                  <span>输入商品关键字</span>
                <%--<input  id="searchFields" name="keyword" type="text" value="" placeholder="请输入搜索关键字">--%>
                <a href="javascript:void(0);" class="search-btn" style="left: 0;" ></a>
            </div>
        </div>
    </form>

   <%-- <a href="javascript:void(0);" class="more-icon"></a>
    <div class="more-bg" style="display:none;">
        <span class="arrows"></span>
        <ul class="more-mc">
            <li class="cur"><a href="${webRoot}/wap/index.ac" class="m-home">首页</a></li>
            <li><a href="${webRoot}/wap/category.ac" class="m-classify">分类</a></li>
            <li><a href="${webRoot}/wap/shoppingcart/cart.ac" class="m-cart">购物车</a></li>
            <li><a href="${webRoot}/wap/module/member/index.ac" class="m-user">会员中心</a></li>
        </ul>
    </div>--%>
</header>
<div class="main">
   <%--banner轮播start--%>
   <div  class="banner frameEdit" frameInfo="roteAdv_mobile|640X250">
    <div class="main_image swiper-container" id="bannerSwiper">
      <ul  class="slider-touch swiper-wrapper" <%--frameEdit" frameInfo="roteAdv_mobile|640X250"--%> <%--style="width: 300%;overflow: hidden;"--%>>
        <c:forEach items="${sdk:findPageModuleProxy('roteAdv_mobile').advt.advtProxy}" var="advtProxys" varStatus="s" end="5">
          <li <%--<c:if test="${s.count eq 1}">style='display:block'</c:if>--%> class="swiper-slide">
          <%-- <a href="#"><img src="case/banner.jpg"></a>--%>
          <a  id="${s.count}" href="${advtProxys.link}"><img src="${advtProxys.advUrl}" alt="${advtProxys.hint}"<%-- style="height:15rem;"--%>></a>
          </li><!--图片尺寸高300px;-->
        </c:forEach>
      </ul>
    </div>
       <div class="slider-nav flicking_con" id="pagenavi">
           <div class="sd-cont">
              <%-- <c:forEach items="${sdk:findPageModuleProxy('roteAdv_mobile').advt.advtProxy}" var="advtProxys" varStatus="s" end="5">
                   <a id="nav_${s.count}" href="javascript:" class="<c:if test="${s.count eq 1}">cur</c:if>"></a>
               </c:forEach>--%>
           </div>
       </div>
      <%-- <a href="javascript:" id="btn_prev"></a>
       <a href="javascript:" id="btn_next"></a>--%>
   </div>

   <%--banner轮播end--%>
    <div class="entry-nav">
        <div class="item frameEdit" frameInfo="adv1_mobile|120X140">
              <%--<div class="pic"><img src="${webRoot}/template/ou/wap/statics/images/store-pic01.png"></div>
              <a href="#" class="sp-name">特卖</a>--%>
            <c:forEach items="${sdk:findPageModuleProxy('adv1_mobile').advt.advtProxy}" var="advtProxys" varStatus="s" end="0">
                <div class="pic">
                    <a id="${s.count}"  href="${advtProxys.link}">
                        <img src="${advtProxys.advUrl}"  alt="${advtProxys.hint}"  >
                    </a>
                </div>
                <a href="javascript:" class="sp-name">${advtProxys.title}</a>
            </c:forEach>
        </div>
        <div class="item frameEdit" frameInfo="adv2_mobile|120X140">
           <%-- <div class="pic"><img src="${webRoot}/template/ou/wap/statics/images/store-pic02.png"></div>
            <a href="#" class="sp-name">新品预售</a>--%>
            <c:forEach items="${sdk:findPageModuleProxy('adv2_mobile').advt.advtProxy}" var="advtProxys" varStatus="s" end="0">
                <div class="pic">
                    <a id="${s.count}"  href="${advtProxys.link}">
                        <img src="${advtProxys.advUrl}"  alt="${advtProxys.hint}"  >
                    </a>
                </div>
                <a href="javascript:" class="sp-name">${advtProxys.title}</a>
            </c:forEach>
        </div>

        <div class="item frameEdit" frameInfo="adv3_mobile|120X140">
            <%--<div class="pic"><img src="${webRoot}/template/ou/wap/statics/images/store-pic03.png"></div>--%>
            <%--<a href="#" class="sp-name">试用商品</a>--%>
            <c:forEach items="${sdk:findPageModuleProxy('adv3_mobile').advt.advtProxy}" var="advtProxys" varStatus="s" end="0">
                <div class="pic">
                    <a id="${s.count}"  href="${advtProxys.link}">
                        <img src="${advtProxys.advUrl}"  ${advtProxys.hint} >
                    </a>
                </div>
                <a href="javascript:" class="sp-name">${advtProxys.title}</a>
            </c:forEach></div>
        <div class="item frameEdit" frameInfo="adv4_mobile|120X140">
          <%--  <div class="pic"><img src="${webRoot}/template/ou/wap/statics/images/store-pic04.png"></div>
            <a href="#" class="sp-name">积分商城</a>--%>
            <c:forEach items="${sdk:findPageModuleProxy('adv4_mobile').advt.advtProxy}" var="advtProxys" varStatus="s" end="0">
                <div class="pic">
                    <a id="${s.count}"  href="${advtProxys.link}">
                        <img src="${advtProxys.advUrl}" ${advtProxys.hint} >
                    </a>
                </div>
                <a href="javascript:" class="sp-name">${advtProxys.title}</a>
            </c:forEach>
        </div>
    </div>
    <%--限时抢购--%>
    <!--特卖推荐-->
    <c:if test="${not empty panicBuyProductProxys}">
    <div class="flash-sale">
        <div class="mt frameEdit" frameInfo="panicBuy_title1_mobile">
          <%--  <span>特卖推荐</span>
            <i>每天爆款</i>--%>
            <c:forEach items="${sdk:findPageModuleProxy('panicBuy_title1_mobile').links}" var="links" end="0">
                <span> ${links.title}</span>
                <i>${links.description}</i>
            </c:forEach>
        </div>
        <div class="mc theme_main_visual swiper-container" id="saleSwiper">
            <%--<div class="theme_main_image">--%>
                <ul class="slider-touch swiper-wrapper">
                    <%--                <li style="opacity: 1; z-index: 2;">
                                        <div class="pic"><a href="#" title="#"><img src="case/pic-250x250.jpg"></a></div>
                                        <div class="rtc">
                                            <div class="top">
                                                剩余<span>4天08:55:58.5</span>
                                            </div>
                                            <div class="title"><a href="#">亿觅移动电源S100小恶魔涂鸦版5200mAh</a></div>
                                            <div class="price">
                                                <i>￥</i>
                                                <span>2556</span>
                                                <em>6.3折</em>
                                            </div>
                                            <a href="#" class="buy-btn">立即抢购</a>
                                        </div>
                                    </li>--%>
                    <c:forEach items="${panicBuyProductProxys}" varStatus="s" var="proxys">
                        <li class="swiper-slide">
                            <div class="pic"><a
                                    href="${webRoot}/wap/product-${proxys.productId}.html?skuId=${proxys.skuId}" id="${s.count}"><img alt="${proxys.name}" src="${proxys.defaultImage["300X300"]}" width="250" height="250"/></a>
                            </div>
                            <div class="rtc">
                                <div class="top">
                                    <fmt:formatDate value="${proxys.price.endTime}" type="both"
                                                    pattern="yyyy/MM/dd HH:mm:ss"
                                                    var="endTimeStr"/> <%--格式化时间--%>
                                    <div class="fl themeIndexTime_${s.count}" ><%--剩余<span>4天08:55:58.5</span>  indexTheme--%>
                                        <script type="text/javascript">
                                            $(".themeIndexTime_${s.count}").imallCountdown("${endTimeStr}","span4",webPath.systemTime)
                                        </script>
                                    </div>
                                </div>
                                <div class="title"><a title="${proxys.name}"
                                                      href="${webRoot}/wap/product-${proxys.productId}.html">${proxys.name}</a>
                                </div>
                                <div class="price">
                                    <i>￥</i>
                                    <span><fmt:formatNumber value="${proxys.price.unitPrice}" type="number"
                                                            pattern="#0.00#"/></span>
                                    <%--<em>
                                        <del>￥<fmt:formatNumber value="${proxys.price.originalUnitPrice}" type="number"
                                                               pattern="#0.00#"/></del>
                                    </em>--%>
                                </div>
                                <a href="${webRoot}/wap/product-${proxys.productId}.html" title="立即抢购" class="buy-btn">立即抢购</a>
                            </div>
                        </li>
                    </c:forEach>
                </ul>
            <%--</div>--%>
            <div class="slider-nav theme_nav">
               <%-- <c:forEach items="${panicBuyProductProxys}" varStatus="s" var="proxys">
                        <span class="<c:if test="${s.count eq 1}">cur</c:if>" id="slider_nav_${s.count}" flagId="${s.count}"></span>
                </c:forEach>--%>
                    <%-- <span class="cur"></span>
                     <span></span>
                     <span></span>--%>
            </div>
            <%--<a href="javascript:" id="themebtn_prev"></a>--%>
            <%--<a href="javascript:" id="themebtn_next"></a>--%>
            </div>
    </div>
   </c:if>
    <!-- 楼层一 -->
       <div class="floor">
           <div class="mt">
               <div class="fl frameEdit" frameInfo="F1_title1_mobile">
                   <c:forEach items="${sdk:findPageModuleProxy('F1_title1_mobile').links}" var="pageLinks" begin="0"
                              end="0">
                   <span>
                           ${pageLinks.title}
                   </span>
                       <i>${pageLinks.description}</i>
                   </c:forEach>
               </div>
               <div class="fr frameEdit" frameInfo="F1_title2_mobile">
                   <c:forEach items="${sdk:findPageModuleProxy('F1_title2_mobile').links}" var="pageLinks" begin="0"
                              end="0">
                       <a href="${pageLinks.link}">更多</a>
                   </c:forEach>
               </div>
           </div>
           <div class="mc frameEdit" frameInfo="F1_adv1_mobile|640x220">
               <c:forEach items="${sdk:findPageModuleProxy('F1_adv1_mobile').advt.advtProxy}" var="advtProxys" varStatus="s" end="0">
                   <div class="pic">
                       <%--<a id="${s.count}"  href="${advtProxys.link}">--%>
                           <img src="${advtProxys.advUrl}" ${advtProxys.hint} >
                       <%--</a>--%>
                   </div>
               </c:forEach>
           </div>
           <div class="md">
               <span class="arrows"></span>
               <div class="md-cont recommend_image_1 swiper-container frameEdit" frameInfo="F1_prd_mobile" id="swiper-container1">
                   <c:set var="recommendProducts"  value="${sdk:findPageModuleProxy('F1_prd_mobile').recommendProducts}"/>
                   <ul style="width:${fn:length(recommendProducts)*10}rem" class="swiper-wrapper"><!-- 这里的宽度为动态，等于 li的个数乘以10rem; 有6个li宽度就为60rem; -->
                     <%--  <li><a href="#">
                           <div class="new"><img src="image/new-pic.png"></div>
                           <div class="pic"><img src="case/pic-180x180.jpg"></div>
                           <div class="title">先锋 SE-CL31 手机音乐耳机入耳式 </div>
                           <div class="new-pri">
                               商城价:<span>￥379.00</span>
                           </div>
                           <div class="old-pri">
                               原价:<del>￥599.00</del>
                           </div>
                       </a></li>--%>
                       <c:forEach items="${sdk:findPageModuleProxy('F1_prd_mobile').recommendProducts}" var="prd" varStatus="s">
                           <li class="swiper-slide"><a href="${webRoot}/wap/product-${prd.productId}.html">
                               <%--<div class="new"><img src="${webRoot}/template/ou/wap/statics/images/new-pic.png"></div>--%>
                               <c:if test="${!empty prd.tags}">
                                   <div class="lab lab-p">${fn:split(prd.tags," ")[0]}</div>
                               </c:if>
                               <div class="pic"><img src="${empty prd.images ? prd.defaultImage["180X180"] : prd.images[0]["180X180"]}"/></div>
                               <div class="title">${prd.name}</div>
                               <div class="new-pri">
                                   商城价:<span>￥<fmt:formatNumber value="${prd.price.unitPrice}" type="number"
                                                                                      pattern="#0.00#"/></span>
                               </div>
                               <div class="old-pri">
                                   原价:<del>￥<fmt:formatNumber value="${prd.marketPrice}" type="number"
                                                                                pattern="#0.00#"/></del>
                               </div>
                           </a></li>
                       </c:forEach>
                   </ul>
               </div>
           </div>
       </div>
    <!-- 楼层二 -->
    <div class="floor">
        <div class="mt">
            <div class="fl frameEdit" frameInfo="F2_title1_mobile">
                <c:forEach items="${sdk:findPageModuleProxy('F2_title1_mobile').links}" var="pageLinks" begin="0"
                           end="0">
                       <span>
                               ${pageLinks.title}
                       </span>
                    <i>${pageLinks.description}</i>
                </c:forEach>
            </div>
            <div class="fr frameEdit" frameInfo="F2_title2_mobile">
                <c:forEach items="${sdk:findPageModuleProxy('F2_title2_mobile').links}" var="pageLinks" begin="0"
                           end="0">
                    <a href="${pageLinks.link}">更多</a>
                </c:forEach>
            </div>
        </div>
        <div class="mc  frameEdit" frameInfo="F2_adv1_mobile|640x220">
            <c:forEach items="${sdk:findPageModuleProxy('F2_adv1_mobile').advt.advtProxy}" var="advtProxys" varStatus="s" end="0">
                <div class="pic">
                        <%--<a id="${s.count}"  href="${advtProxys.link}">--%>
                    <img src="${advtProxys.advUrl}" ${advtProxys.hint} >
                        <%--</a>--%>
                </div>
            </c:forEach>
        </div>
        <div class="md">
            <span class="arrows"></span>
            <div class="md-cont swiper-container frameEdit" frameInfo="F2_prd_mobile" id="swiper-container2">
                <c:set var="recommendProducts"  value="${sdk:findPageModuleProxy('F2_prd_mobile').recommendProducts}"/>
                <ul style="width:${fn:length(recommendProducts)*10}rem" class="swiper-wrapper"><!-- 这里的宽度为动态，等于 li的个数乘以10rem; 有6个li宽度就为60rem; -->
                    <c:forEach items="${sdk:findPageModuleProxy('F2_prd_mobile').recommendProducts}" var="prd" varStatus="s">
                        <li class="swiper-slide"><a href="${webRoot}/wap/product-${prd.productId}.html">
                            <%--<div class="new"><img src="${webRoot}/template/ou/wap/statics/images/hot-pic.png"></div>--%>
                            <c:if test="${!empty prd.tags}">
                                <div class="lab lab-red">${fn:split(prd.tags," ")[0]}</div>
                            </c:if>
                                <div class="pic"><img src="${empty prd.images ? prd.defaultImage["180X180"] : prd.images[0]["180X180"]}"/></div>
                                <div class="title">${prd.name}</div>
                            <div class="new-pri">
                                商城价:<span>￥<fmt:formatNumber value="${prd.price.unitPrice}" type="number"
                                                             pattern="#0.00#"/></span>
                            </div>
                            <div class="old-pri">
                                原价:<del>￥<fmt:formatNumber value="${prd.marketPrice}" type="number"
                                                           pattern="#0.00#"/></del>
                            </div>
                        </a></li>
                    </c:forEach>
                </ul>
            </div>
        </div>
    </div>
    <!-- 楼层三 -->
    <div class="floor">
        <div class="mt">
            <div class="fl frameEdit" frameInfo="F3_title1_mobile">
                <c:forEach items="${sdk:findPageModuleProxy('F3_title1_mobile').links}" var="pageLinks" begin="0"
                           end="0">
                   <span>
                           ${pageLinks.title}
                   </span>
                    <i>${pageLinks.description}</i>
                </c:forEach>
            </div>
            <div class="fr frameEdit" frameInfo="F3_title2_mobile">
                <c:forEach items="${sdk:findPageModuleProxy('F3_title2_mobile').links}" var="pageLinks" begin="0"
                           end="0">
                    <a href="${pageLinks.link}">更多</a>
                </c:forEach>
            </div>
        </div>
        <div class="mc  frameEdit" frameInfo="F3_adv1_mobile|640x220">
            <c:forEach items="${sdk:findPageModuleProxy('F3_adv1_mobile').advt.advtProxy}" var="advtProxys" varStatus="s" end="0">
                <div class="pic">
                        <%--<a id="${s.count}"  href="${advtProxys.link}">--%>
                    <img src="${advtProxys.advUrl}" ${advtProxys.hint} >
                        <%--</a>--%>
                </div>
            </c:forEach>
        </div>
        <div class="md">
            <span class="arrows"></span>
            <div class="md-cont swiper-container frameEdit" frameInfo="F3_prd_mobile" id="swiper-container3">
                <c:set var="recommendProducts"  value="${sdk:findPageModuleProxy('F3_prd_mobile').recommendProducts}"/>
                <ul style="width:${fn:length(recommendProducts)*10}rem" class="swiper-wrapper"><!-- 这里的宽度为动态，等于 li的个数乘以10rem; 有6个li宽度就为60rem; -->
                    <c:forEach items="${sdk:findPageModuleProxy('F3_prd_mobile').recommendProducts}" var="prd" varStatus="s">
                        <li class="swiper-slide"><a href="${webRoot}/wap/product-${prd.productId}.html">
                            <%--<div class="new"><img src="${webRoot}/template/ou/wap/statics/images/new-pic.png"></div>--%>
                            <c:if test="${!empty prd.tags}">
                                <div class="lab lab-p">${fn:split(prd.tags," ")[0]}</div>
                            </c:if>
                            <div class="pic"><img src="${empty prd.images ? prd.defaultImage["180X180"] : prd.images[0]["180X180"]}"/></div>
                            <div class="title">${prd.name}</div>
                            <div class="new-pri">
                                商城价:<span>￥<fmt:formatNumber value="${prd.price.unitPrice}" type="number"
                                                             pattern="#0.00#"/></span>
                            </div>
                            <div class="old-pri">
                                原价:<del>￥<fmt:formatNumber value="${prd.marketPrice}" type="number"
                                                           pattern="#0.00#"/></del>
                            </div>
                        </a></li>
                    </c:forEach>
                </ul>
            </div>
        </div>
    </div>
    <!-- 楼层四 -->
    <div class="floor">
        <div class="mt">
            <div class="fl frameEdit" frameInfo="F4_title1_mobile">
                <c:forEach items="${sdk:findPageModuleProxy('F4_title1_mobile').links}" var="pageLinks" begin="0"
                           end="0">
                       <span>
                               ${pageLinks.title}
                       </span>
                    <i>${pageLinks.description}</i>
                </c:forEach>
            </div>
            <div class="fr frameEdit" frameInfo="F4_title2_mobile">
                <c:forEach items="${sdk:findPageModuleProxy('F4_title2_mobile').links}" var="pageLinks" begin="0"
                           end="0">
                    <a href="${pageLinks.link}">更多</a>
                </c:forEach>
            </div>
        </div>
        <div class="mc  frameEdit" frameInfo="F4_adv1_mobile|640x220">
            <c:forEach items="${sdk:findPageModuleProxy('F4_adv1_mobile').advt.advtProxy}" var="advtProxys" varStatus="s" end="0">
                <div class="pic">
                        <%--<a id="${s.count}"  href="${advtProxys.link}">--%>
                    <img src="${advtProxys.advUrl}" ${advtProxys.hint} >
                        <%--</a>--%>
                </div>
            </c:forEach>
        </div>
        <div class="md">
            <span class="arrows"></span>
            <div class="md-cont swiper-container  frameEdit" frameInfo="F4_prd_mobile" id="swiper-container4">
                <c:set var="recommendProducts"  value="${sdk:findPageModuleProxy('F4_prd_mobile').recommendProducts}"/>
                <ul style="width:${fn:length(recommendProducts)*10}rem" class="swiper-wrapper"><!-- 这里的宽度为动态，等于 li的个数乘以10rem; 有6个li宽度就为60rem; -->
                    <c:forEach items="${sdk:findPageModuleProxy('F4_prd_mobile').recommendProducts}" var="prd" varStatus="s">
                        <li class="swiper-slide"><a href="${webRoot}/wap/product-${prd.productId}.html">
                            <%--<div class="new"><img src="${webRoot}/template/ou/wap/statics/images/hot-pic.png"></div>--%>
                            <c:if test="${!empty prd.tags}">
                                <div class="lab lab-red">${fn:split(prd.tags," ")[0]}</div>
                            </c:if>
                            <div class="pic"><img src="${empty prd.images ? prd.defaultImage["180X180"] : prd.images[0]["180X180"]}"/></div>
                            <div class="title">${prd.name}</div>
                            <div class="new-pri">
                                商城价:<span>￥<fmt:formatNumber value="${prd.price.unitPrice}" type="number"
                                                             pattern="#0.00#"/></span>
                            </div>
                            <div class="old-pri">
                                原价:<del>￥<fmt:formatNumber value="${prd.marketPrice}" type="number"
                                                           pattern="#0.00#"/></del>
                            </div>
                        </a></li>
                    </c:forEach>
                </ul>
            </div>
        </div>
    </div>
    <!-- 楼层五 -->
    <div class="floor">
        <div class="mt">
            <div class="fl frameEdit" frameInfo="F5_title1_mobile">
                <c:forEach items="${sdk:findPageModuleProxy('F5_title1_mobile').links}" var="pageLinks" begin="0"
                           end="0">
                      <span>
                              ${pageLinks.title}
                      </span>
                    <i>${pageLinks.description}</i>
                </c:forEach>
            </div>
            <div class="fr frameEdit" frameInfo="F5_title2_mobile">
                <c:forEach items="${sdk:findPageModuleProxy('F5_title2_mobile').links}" var="pageLinks" begin="0"
                           end="0">
                    <a href="${pageLinks.link}">更多</a>
                </c:forEach>
            </div>
        </div>
        <div class="mc  frameEdit" frameInfo="F5_adv1_mobile|640x220">
            <c:forEach items="${sdk:findPageModuleProxy('F5_adv1_mobile').advt.advtProxy}" var="advtProxys" varStatus="s" end="0">
                <div class="pic">
                        <%--<a id="${s.count}"  href="${advtProxys.link}">--%>
                    <img src="${advtProxys.advUrl}" ${advtProxys.hint} >
                        <%--</a>--%>
                </div>
            </c:forEach>
        </div>
        <div class="md">
            <span class="arrows"></span>
            <div class="md-cont swiper-container frameEdit" frameInfo="F5_prd_mobile" id="swiper-container5">
                <c:set var="recommendProducts"  value="${sdk:findPageModuleProxy('F5_prd_mobile').recommendProducts}"/>
                    <ul style="width:${fn:length(recommendProducts)*10}rem" class="swiper-wrapper"><!-- 这里的宽度为动态，等于 li的个数乘以10rem; 有6个li宽度就为60rem; -->
                        <c:forEach items="${sdk:findPageModuleProxy('F5_prd_mobile').recommendProducts}" var="prd"  varStatus="s">
                            <li class="swiper-slide"><a href="${webRoot}/wap/product-${prd.productId}.html">
                                <%--<div class="new"><img src="${webRoot}/template/ou/wap/statics/images/new-pic.png"></div>--%>
                                <c:if test="${!empty prd.tags}">
                                    <div class="lab lab-p">${fn:split(prd.tags," ")[0]}</div>
                                </c:if>
                                <div class="pic"><img src="${empty prd.images ? prd.defaultImage["180X180"] : prd.images[0]["180X180"]}"/></div>
                                <div class="title">${prd.name}</div>
                                <div class="new-pri">
                                    商城价:<span>￥<fmt:formatNumber value="${prd.price.unitPrice}" type="number"
                                                                 pattern="#0.00#"/></span>
                                </div>
                                <div class="old-pri">
                                    原价:<del>￥<fmt:formatNumber value="${prd.marketPrice}" type="number"
                                                               pattern="#0.00#"/></del>
                                </div>
                            </a></li>
                        </c:forEach>
                    </ul>
            </div>
        </div>
    </div>
       <!-- 楼层六 -->
       <div class="floor">
           <div class="mt">
               <div class="fl frameEdit" frameInfo="F6_title1_mobile">
                   <c:forEach items="${sdk:findPageModuleProxy('F6_title1_mobile').links}" var="pageLinks" begin="0"
                              end="0">
                        <span>
                                ${pageLinks.title}
                        </span>
                       <i>${pageLinks.description}</i>
                   </c:forEach>
               </div>
               <div class="fr frameEdit" frameInfo="F6_title2_mobile">
                   <c:forEach items="${sdk:findPageModuleProxy('F6_title2_mobile').links}" var="pageLinks" begin="0"
                              end="0">
                       <a href="${pageLinks.link}">更多</a>
                   </c:forEach>
               </div>
           </div>
           <div class="mc  frameEdit" frameInfo="F6_adv1_mobile|640x220">
               <c:forEach items="${sdk:findPageModuleProxy('F6_adv1_mobile').advt.advtProxy}" var="advtProxys" varStatus="s" end="0">
                   <div class="pic">
                           <%--<a id="${s.count}"  href="${advtProxys.link}">--%>
                       <img src="${advtProxys.advUrl}" ${advtProxys.hint} >
                           <%--</a>--%>
                   </div>
               </c:forEach>
           </div>
           <div class="md">
               <span class="arrows"></span>
               <div class="md-cont swiper-container frameEdit" frameInfo="F6_prd_mobile" id="swiper-container6">
                   <c:set var="recommendProducts"  value="${sdk:findPageModuleProxy('F6_prd_mobile').recommendProducts}"/>
                   <ul style="width:${fn:length(recommendProducts)*10}rem" class="swiper-wrapper"><!-- 这里的宽度为动态，等于 li的个数乘以10rem; 有6个li宽度就为60rem; -->
                       <c:forEach items="${sdk:findPageModuleProxy('F6_prd_mobile').recommendProducts}" var="prd"  varStatus="s">
                           <li class="swiper-slide"><a href="${webRoot}/wap/product-${prd.productId}.html">
                                   <%--<div class="new"><img src="${webRoot}/template/ou/wap/statics/images/new-pic.png"></div>--%>
                               <c:if test="${!empty prd.tags}">
                                   <div class="lab lab-p">${fn:split(prd.tags," ")[0]}</div>
                               </c:if>
                               <div class="pic"><img src="${empty prd.images ? prd.defaultImage["180X180"] : prd.images[0]["180X180"]}"/></div>
                               <div class="title">${prd.name}</div>
                               <div class="new-pri">
                                   商城价:<span>￥<fmt:formatNumber value="${prd.price.unitPrice}" type="number"
                                                                pattern="#0.00#"/></span>
                               </div>
                               <div class="old-pri">
                                   原价:<del>￥<fmt:formatNumber value="${prd.marketPrice}" type="number"
                                                              pattern="#0.00#"/></del>
                               </div>
                           </a></li>
                       </c:forEach>
                   </ul>
               </div>
           </div>
       </div>
       <!-- 楼层七 -->
       <div class="floor">
           <div class="mt">
               <div class="fl frameEdit" frameInfo="F7_title1_mobile">
                   <c:forEach items="${sdk:findPageModuleProxy('F7_title1_mobile').links}" var="pageLinks" begin="0"
                              end="0">
                      <span>
                              ${pageLinks.title}
                      </span>
                       <i>${pageLinks.description}</i>
                   </c:forEach>
               </div>
               <div class="fr frameEdit" frameInfo="F7_title2_mobile">
                   <c:forEach items="${sdk:findPageModuleProxy('F7_title2_mobile').links}" var="pageLinks" begin="0"
                              end="0">
                       <a href="${pageLinks.link}">更多</a>
                   </c:forEach>
               </div>
           </div>
           <div class="mc  frameEdit" frameInfo="F7_adv1_mobile|640x220">
               <c:forEach items="${sdk:findPageModuleProxy('F7_adv1_mobile').advt.advtProxy}" var="advtProxys" varStatus="s" end="0">
                   <div class="pic">
                           <%--<a id="${s.count}"  href="${advtProxys.link}">--%>
                       <img src="${advtProxys.advUrl}" ${advtProxys.hint} >
                           <%--</a>--%>
                   </div>
               </c:forEach>
           </div>
           <div class="md">
               <span class="arrows"></span>
               <div class="md-cont swiper-container frameEdit" frameInfo="F7_prd_mobile" id="swiper-container7">
                   <c:set var="recommendProducts"  value="${sdk:findPageModuleProxy('F7_prd_mobile').recommendProducts}"/>
                   <ul style="width:${fn:length(recommendProducts)*10}rem" class="swiper-wrapper"><!-- 这里的宽度为动态，等于 li的个数乘以10rem; 有6个li宽度就为60rem; -->
                       <c:forEach items="${sdk:findPageModuleProxy('F7_prd_mobile').recommendProducts}" var="prd"  varStatus="s">
                           <li class="swiper-slide"><a href="${webRoot}/wap/product-${prd.productId}.html">
                                   <%--<div class="new"><img src="${webRoot}/template/ou/wap/statics/images/new-pic.png"></div>--%>
                               <c:if test="${!empty prd.tags}">
                                   <div class="lab lab-p">${fn:split(prd.tags," ")[0]}</div>
                               </c:if>
                               <div class="pic"><img src="${empty prd.images ? prd.defaultImage["180X180"] : prd.images[0]["180X180"]}"/></div>
                               <div class="title">${prd.name}</div>
                               <div class="new-pri">
                                   商城价:<span>￥<fmt:formatNumber value="${prd.price.unitPrice}" type="number"
                                                                pattern="#0.00#"/></span>
                               </div>
                               <div class="old-pri">
                                   原价:<del>￥<fmt:formatNumber value="${prd.marketPrice}" type="number"
                                                              pattern="#0.00#"/></del>
                               </div>
                           </a></li>
                       </c:forEach>
                   </ul>
               </div>
           </div>
       </div>



    <!--骚品会-->
    <div class="best-sale" id="best-sale" >
        <div class="mt frameEdit" frameInfo="article_title1_mobile">
            <%-- <span>骚品会</span>
             <i>最值得买的酷动3C数码</i>--%>
            <c:forEach items="${sdk:findPageModuleProxy('article_title1_mobile').links}" var="links" end="0">
                <span> ${links.title}</span>
                <i>${links.description}</i>
            </c:forEach>
        </div>
        <div class="mc" id="mc-list">
            <%--  <div class="item">
                  <a href="#" class="pic" title="#"><img src="${webRoot}/template/ou/wap/statics/images/pic-620x260.jpg"></a>
                  <div class="md">
                      <h5 class="elli">Coofans携手KEF打造殿堂级音乐享受</h5>
                      <p class="elli">小有作为，分享五彩音乐世界 </p>
                  </div>
                  <div class="mb">
                      <span>7628人浏览</span>
                      <a href="#">分享给好友</a>
                  </div>
              </div>--%>
            <c:forEach items="${articleProxys.result}" var="articleProxy">
                <div class="item">
                   <%-- <a href="${webRoot}/wap/infoDetai.ac?articleId=${articleProxy.infArticleId}" class="pic"
                       title="${articleProxy.title}"><img src="${articleProxy.icon["620x260"]}"/></a>--%>
                    <c:choose>
                        <c:when test="${!empty articleProxy.externalLink}">
                            <a href="${articleProxy.externalLink}" class="pic" target="_Blank" title="${articleProxy.title}"><img src="${articleProxy.icon[""]}"  width="620" height="260"/></a>
                        </c:when>
                        <c:otherwise>
                            <a href="${webRoot}/wap/infoDetai.ac?articleId=${articleProxy.infArticleId}" class="pic" title="${articleProxy.title}"><img src="${articleProxy.icon[""]}"  width="620" height="260"/></a>
                        </c:otherwise>
                    </c:choose>

                    <div class="md">
                        <h5 class="elli">${fn:substring(articleProxy.title,0,60)}</h5>

                        <p class="elli">${sdk:cutString(sdk:cleanHTML(articleProxy.articleCont, ""), 300, "........")}</p>
                    </div>
                    <div class="mb">
                        <span>${articleProxy.browseTimes}人浏览</span>
                      <%--  <a href="javascript:void(0);">分享给好友</a>--%>
                    </div>
                </div>
            </c:forEach>
        </div>
        <%--<div class="bs-loading"><img src="${webRoot}/template/ou/wap/statics/images/loading.png"></div>--%>
    </div>
</div>
<nav id="page-nav">
    <a href="${webRoot}/wap/indexContent.ac?page=2"></a>
</nav>

<c:import url="/template/ou/wap/module/common/suspend.jsp"/>
<div id="loadSearch" style="height: 100%;width: 100%;display: none">
 <%--放在body下，显示#loadSearch时会隐藏同级的节点--%>
   <%-- <c:import url="/template/ou/wap/searchTop.jsp"/>--%>
</div>
<%--
<footer class="footer">
    <div class="footer-top">
        <ul>
            <li><a href="#">登录</a></li>
            <li><a href="#">退出</a></li>
        </ul>
    </div>
    <div class="footer-platforms">
        <ul>
            <li class="cur"><a href="#" title="触屏版"><img src="${webRoot}/template/ou/wap/statics/images/wap-icon.png"><span>触屏版</span></a></li>
            <li><a href="#" title="电脑版"><img src="${webRoot}/template/ou/wap/statics/images/pc-icon.png"><span>电脑版</span></a></li>
        </ul>
        <div class="footer-copyright">Copyright©1998-2014 CooFans. All Rights Reserved. <br/>深圳市合烁数码有限公司版权所有</div>
    </div>
    <div class="footer-nav">
        <ul>
            <li class="cur"><a href="#" class="m-home">首页</a></li>
            <li><a href="#" class="m-classify">分类</a></li>
            <li><a href="#" class="m-search">搜索</a></li>
            <li><a href="#" class="m-cart">购物车</a></li>
            <li><a href="#" class="m-center">会员中心</a></li>
        </ul>
    </div>
</footer>--%>


<c:import url="/template/ou/wap/module/common/bottom.jsp?p=index"/>

</body>
</html>
