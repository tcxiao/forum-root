<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>用户注册</title>
		<link rel="stylesheet" type="text/css" href="${ctx}/css/index.css"/>
		<link rel="stylesheet" type="text/css" href="${ctx}/js/validate/css/jquery.alert.css"/>
		<script type="text/javascript" src="${ctx}/js/jquery-1.7.2.js"></script>
		<script type="text/javascript" src="${ctx}/js/validate/jquery.alert.js"></script>
		<script type="text/javascript" src="${ctx}/js/validate/jquery.bgiframe.min.js"></script>
		<script type="text/javascript" src="${ctx}/js/validate/formValidate.js"></script>
		<script type="text/javascript" src="${ctx}/js/plugins/My97DatePicker/WdatePicker.js"></script>
		<script type="text/javascript">
			$(function(){
				$("#submitBtn").click(function(){
					if(checkForm()){
						$("#saveForm").submit();
					}
				});
			});
			
			function checkForm(){
				var flag=true;
				if(!checkBlank($("#account"))||!checkRemote($("#account"),$("#personId"),"checkLoginName.json")){
					flag=false;
				}else if(!checkBlank($("#password"))){
					flag=false;
				}else if(!checkBlank($("#confirmPass"))||!equalTo($("#confirmPass"),$("#password"))){
					flag=false;
				}else if(!checkBlank($("#name"))){
					flag=false;
				}else if(!checkBlank($("#email"))||!checkEmail($("#email"))){
					flag=false;
				}
				return flag;
			}
		</script>
	</head>
	<body>
		<%@ include file="../layouts/header.jsp" %>
		<div id="main">
		<div style="margin-top:7px;"></div>
		<div id="content">
			<!-- Thread Start -->
			<div class="t" style="margin-bottom:0px; border-bottom:0">
				<table cellspacing="0" cellpadding="0" width="100%">
					<tr>
						<th class="h">
							<strong class="fl w">用户注册</strong> &nbsp;
							<span style="color: red; font-weight: bold; ">${ message }</span>
						</th>
					</tr>
				</table>
			</div>
			<form action="register.ac" method="post" id="saveForm">
				<div class="t t2">
					<table cellspacing="0" cellpadding="0" width="100%"
						class="form_table">
		
						<tr class="tr3">
							<th style="width: 120px; ">
								帐号:
							</th>
							<td>
								<input type="text" name="account" id="account"  class="input"/>
							</td>
						</tr>
						<tr class="tr3">
							<th>
								密码:
							</th>
							<td>
								<input type="password" name="password" id="password"  class="input"/>
							</td>
						</tr>
						<tr class="tr3">
							<th>
								确认密码:
							</th>
							<td>
								<input type="password" name="confirmPass" id="confirmPass"  class="input"/>
							</td>
						</tr>
						<tr class="tr3">
							<th>
								姓名:
							</th>
							<td>
								<input type="text" name="name" id="name" class="input"/>
							</td>
						</tr>
						<tr class="tr3">
							<th>
								性别:
							</th>
							<td>
								<input type="radio" name="sex" checked="checked" value="男"/> 男
								<input type="radio" name="sex" value="女"/> 女
							</td>
						</tr>
						<tr class="tr3">
							<th>
								电子邮件:
							</th>
							<td>
								<input type="text" name="email" id="email" class="input"/>
							</td>
						</tr>
						<tr class="tr3">
							<th>
								生日:
							</th>
							<td>
								<input type="text" name="birthday" id="birthday" onclick="WdatePicker({maxDate:'y%-M%-d%'})" class="Wdate"/>
							</td>
						</tr>
						<tr class="tr3">
							<td colspan="2">
								<input type="button" value="提交" class="btn" id="submitBtn"/>
								<input type="hidden" value="" name="id" id="personId"/>
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
