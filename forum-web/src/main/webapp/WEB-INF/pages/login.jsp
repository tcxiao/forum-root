<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <title>论坛：登陆</title>
	<link rel="stylesheet" type="text/css" href="${ctx}/css/index.css"/>
  </head>
  
  <body>
  	<%@ include file="../layouts/header.jsp" %>
	<div id="main">
		<div style="margin-top:7px;"></div>
		<div id="content">
			<c:if test="${sessionScope.regiInfo ne null}">
		  		<p>
		  			<span style="color:red">${sessionScope.regiInfo}</span>
		  		</p>
		  	</c:if>
			<!-- Thread Start -->
			<div class="t" style="margin-bottom:0px; border-bottom:0">
				<table cellspacing="0" cellpadding="0" width="100%">
					<tr>
						<th class="h">
							<strong class="fl w">需要登录</strong> &nbsp;
							<span style="color: red; font-weight: bold; "></span>
						</th>
					</tr>
				</table>
			</div>
			<form action="login.ac" method="post">
				<div class="t t2">
					<table cellspacing="0" cellpadding="0" width="100%"
						style="table-layout:fixed;border-top:0">
						<tr class="tr3">
							<td style="width: 120px; ">
								帐号:
							</td>
							<td>
								<input type="text" name="username" id="username"  class="input"/>
							</td>
						</tr>
		
						<tr class="tr3">
							<td>
								密码:
							</td>
							<td>
								<input type="password" name="password" id="password"  class="input"/>
							</td>
						</tr>
		
						<tr class="tr3">
							<td colspan="2">
								<input type="submit" class="btn" value="登录" />
							</td>
						</tr>
					</table>
				</div>
			</form>
	  	</div>
	</div>
  	<%@ include file="../layouts/footer.jsp" %>
  </body>
</html>