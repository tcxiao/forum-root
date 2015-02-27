<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="sitemesh" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <title>论坛：<sitemesh:title/></title>
	<link rel="stylesheet" type="text/css" href="${ctx}/css/index.css"/>
	<link href="${ctx}/js/plugins/dtree/dtree.css" type="text/css" rel="stylesheet" />
	<script type="text/javascript" src="${ctx}/js/plugins/dtree/dtree.js"></script>
	<script type="text/javascript">
		//显示所有的权限树的节点
		function showAllTreeNode(){
		   	 dtree.add(1,0,'板块管理','${ctx}/yjjc/modAnal/entTransSenType.json','板块管理','moduleItem');
			 dtree.add(2,0,'用户管理','${ctx}/user/query.ac','用户管理','moduleItem');
			 dtree.add(3,0,'类别管理','${ctx}/yjjc/modAnal/entTransSenType.json','类别管理','moduleItem');
			 dtree.add(4,0,'帖子管理','${ctx}/yjjc/modAnal/entTransSenType.json','帖子管理','moduleItem');
			 document.write(dtree);
		}
	</script>
	<sitemesh:head />
  </head>
  
  <body>
  	<%@ include file="header.jsp" %>
	<div id="main">
		<div style="margin-top:7px;"></div>
		<div id="content">
			<div class="t" style="margin-bottom:0px; border-bottom:0">
				<table cellspacing="0" cellpadding="0" width="100%">
					<tr>
						<th class="h">
							<strong class="fl w"><sitemesh:title/></strong>
						</th>
					</tr>
				</table>
			</div>
			<div class="t t2">
				<div class="con_left">
		  			<script type="text/javascript">
						var dtree = new dTree('d','${ctx}');//创建一个树对象 
						dtree.add(0,-1,'论坛菜单','','论坛菜单','moduleItem');                           
						showAllTreeNode();
						dtree.s("1");
					</script>
		  		</div>
		  		<div class="con_right">
	  				<sitemesh:body/>
	  			</div>
	  			<div class="clear_both"></div>
			</div>
	  	</div>
	</div>
  	<%@ include file="footer.jsp" %>
  </body>
</html>
