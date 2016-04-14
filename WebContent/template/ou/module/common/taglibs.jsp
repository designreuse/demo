<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="http://www.ozh.com/sdk" prefix="sdk"%>
<%@ page errorPage="/commons/error.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%
    response.setHeader("cache-control","max-age=5,public,must-revalidate"); //one day
    response.setDateHeader("expires", -1);
%>
<c:set value="${pageContext.request.contextPath}" var="webRoot" />
<%--<c:set value="${sdk:getSysParamValue('webName')}" var="webName" />--%>


<%
    String userAgent = request.getHeader("User-Agent");
    String microMessenger = "MicroMessenger/";
    request.setAttribute("isWeixin",userAgent.indexOf(microMessenger) > 0?"Y":"N");
%>





