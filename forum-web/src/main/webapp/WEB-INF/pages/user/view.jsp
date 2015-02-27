<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
	<title>查看用户资料</title>
</head>
<body>
	<!-- Thread Start -->
	<div class="t t2">
		<table cellspacing="0" cellpadding="0" width="100%"
			class="form_table">
			<tr>
				<th colspan="2">用户信息</th>
			</tr>
			<tr>
				<th width="20%">
					帐号
				</th>
				<td>
					${ person.account }
				</td>
			</tr>
			<tr>
				<th>
					姓名
				</th>
				<td>
					${ person.name } &nbsp;
				</td>
			</tr>
			<tr>
				<th>
					性别
				</th>
				<td>
					${ person.sex } &nbsp;
				</td>
			</tr>
			<tr>
				<th>
					电子邮件
				</th>
				<td>
					${ person.email } &nbsp;
				</td>
			</tr>
			<tr>
				<th>
					生日
				</th>
				<td>
					${ person.birthday } &nbsp;
				</td>
			</tr>
			<tr>
				<th>
					注册时间
				</th>
				<td>
					${ person.dateCreated } &nbsp;
				</td>
			</tr>
			<tr>
				<th>
					上次登录时间
				</th>
				<td>
					${ person.dateLastActived } &nbsp;
				</td>
			</tr>
			<tr>
				<th>
					负责的版面
				</th>
				<td>
				</td>
			</tr>
			<tr>
				<td colspan="2" style="text-align:center">
					<input type="button" class="btn" value="返回" onclick="history.go(-1)"/>
				</td>
			</tr>
		</table>
	</div>
	<script type="text/javascript">
		dtree.s("2");//dtree选中
	</script>
</body>
</html>