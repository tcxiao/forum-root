<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
	<head>
		<title>用户管理</title>
	</head>
	<body>
		<table width="100%" cellspacing="0" cellpadding="0" class="list_table">
			<tr>
				<th width="5%">序号</th>
				<th>登陆名</th>
				<th>用户姓名</th>
				<th>性别</th>
				<th width="15%">操作</th>
			</tr>
			<c:forEach var="person" items="${ persons }" varStatus="st">
				<tr>
					<td>${st.count}</td>
					<td>${ person.account }</td>
					<td>${ person.name }</td>
					<td>${ person.sex }</td>
					<td>
						<a href="view.ac?id=${person.id}">查看</a>
						<a href="addInit.ac?id=${person.id}">编辑</a>
						<a href="javascript:del(${person.id})">删除</a>
					</td>
				</tr>
			</c:forEach>
		</table>
		<div class="t3">
			<table width="100%" align="center" cellspacing="0" cellpadding="0">
				<tr>
					<td align="left" valign="middle">
						<div class="fl">
							<div class="pages">
								${ pagination }
							</div>
						</div>
					</td>
					<td style="text-align:right">
						<input type="button" value="新增" 
						class="btn" onclick="location.href='addInit.ac'"/>
					</td>
				</tr>
			</table>
		</div>
		<script type="text/javascript" src="${ctx}/js/jquery-1.7.2.js"></script>
		<script type="text/javascript" src="${ctx}/js/common.js"></script>
		<script type="text/javascript">
			dtree.s("2");//dtree选中
			function del(id){
				delAll(id,null,"delete.json");
			}
		</script>
	</body>
</html>

