<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>用户操作</title>
		<link rel="stylesheet" type="text/css" href="${ctx}/css/index.css"/>
		<link rel="stylesheet" type="text/css" href="${ctx}/js/validate/css/jquery.alert.css"/>
		<script type="text/javascript" src="${ctx}/js/jquery-1.7.2.js"></script>
		<script type="text/javascript" src="${ctx}/js/jquery.form.js"></script>
		<script type="text/javascript" src="${ctx}/js/validate/formValidate.js"></script>
		<script type="text/javascript" src="${ctx}/js/validate/jquery.alert.js"></script>
		<script type="text/javascript" src="${ctx}/js/validate/jquery.bgiframe.min.js"></script>
		<script type="text/javascript" src="${ctx}/js/validate/formValidate.js"></script>
		<script type="text/javascript" src="${ctx}/js/plugins/My97DatePicker/WdatePicker.js"></script>
	</head>
	<body>
		<form action="register.ac" method="post" id="saveForm">
			<div class="t t2">
				<table cellspacing="0" cellpadding="0" width="100%"
					class="form_table">
	
					<tr class="tr3">
						<th style="width: 120px; ">
							帐号:
						</th>
						<td>
							<input type="text" name="account" id="account" value="${person.account}" class="input"/>
						</td>
					</tr>
					<tr class="tr3">
						<th>
							姓名:
						</th>
						<td>
							<input type="text" name="name" id="name" value="${person.name}" class="input"/>
						</td>
					</tr>
					<tr class="tr3">
						<th>
							性别:
						</th>
						<td>
							<c:choose>
								<c:when test="${person.sex eq '女'}">
									<input type="radio" name="sex" value="男"/> 男
									<input type="radio" name="sex" checked="checked" value="女"/> 女
								</c:when>
								<c:otherwise>
									<input type="radio" name="sex" checked="checked" value="男"/> 男
									<input type="radio" name="sex" value="女"/> 女
								</c:otherwise>
							</c:choose>
						</td>
					</tr>
					<tr class="tr3">
						<th>
							电子邮件:
						</th>
						<td>
							<input type="text" name="email" id="email" value="${person.email}" class="input"/>
						</td>
					</tr>
					<tr class="tr3">
						<th>
							生日:
						</th>
						<td>
							<input type="text" name="birthday" id="birthday" value="${person.birthday}" onclick="WdatePicker({maxDate:'y%-M%-d%'})" class="Wdate"/>
						</td>
					</tr>
					<tr class="tr3">
						<td colspan="2">
							<input type="submit" value="提交" class="btn" id="submitBtn"/>
							<input type="button" value="返回" class="btn" onfocus="history.go(-1)"/>
							<input type="hidden" value="${person.id}" name="id" id="personId"/>
						</td>
					</tr>
				</table>
			</div>
		</form>
		<script type="text/javascript">
			dtree.s("2");//dtree选中
			$(function(){
				$("#saveForm").submit(function(){
					if(checkForm()){
						$(this).ajaxSubmit({
			                url : '${ctx}/user/addOrModify.json',
			                type : "post",
			                dataType : "json",
			                success : function(data) {
			                    if (data.success) {
			                        alert(data.msg);
			                    } else {
			                        alert(data.msg);
			                    }
			                },
			                error : function() {
			                    alert("系统响应失败");
			                }
			            });
					}
					return false;
				});
			});
			
			function checkForm(){
				var flag=true;
				if(!checkBlank($("#account"))||!checkRemote($("#account"),$("#personId"),"${ctx}/checkLoginName.json")){
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
	</body>
</html>
