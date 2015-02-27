<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <title>论坛：</title>
	<link rel="stylesheet" type="text/css" href="${ctx}/css/index.css"/>
  </head>
  
  <body>
  	<%@ include file="header.jsp" %>
	<div id="main">
		<div style="margin-top:7px;"></div>
		<div id="content">
	  	</div>
	</div>
  	<%@ include file="footer.jsp" %>
  </body>
</html>
