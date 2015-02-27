<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />	
	
		<div id="wrapA">
			<div id="header">
				<div class="toptool tar">
					<span><a href="">Home首页</a> | <a
						href="">论坛首页</a> </span>
				</div>
				<table cellspacing="0" cellpadding="0" align="center" width="100%">
					<tr>
						<td class="banner">
							<a href="./"><img src="${ctx}/images/yellow/logo.png" /> </a>
						</td>
						<td class="banner" id="banner" align="right">
							<img src="${ctx}/images/yellow/banner.gif">
						</td>
					</tr>
					<tr>
						<td align="center" height="1" colspan="2"></td>
					</tr>
				</table>
				<div id="h_guide" class="guide" colspan="2">
					<span class="s3">&raquo;</span>

					<c:choose>
						<c:when test="${ personInfo == null }">
							您尚未&nbsp;
							<a href="loginInit.json">登录</a> |
							<a href="registerInit.json">注册</a>
						</c:when>
						<c:otherwise>
							欢迎您, <a
								href="#">${
								personInfo.account }</a> | <a
								href="logout.ac">注销</a>
						</c:otherwise>
					</c:choose>

				</div>
			</div>
		</div>