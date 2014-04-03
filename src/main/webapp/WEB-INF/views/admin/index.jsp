<%@ page language="java" import="java.util.*, cn.edu.zju.isst.taglib.NavigationLink" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %> 
<%@ taglib uri="/jsp_layout" prefix="layout"%>
<%@ taglib uri="/navigation" prefix="navigation"%>
<%
Map<String, NavigationLink> menu = new HashMap<String, NavigationLink>();
menu.put("test1", new NavigationLink("测试1", "/admin/test1"));
menu.put("test2", new NavigationLink("测试2", "/admin/test2"));
menu.put("test3", new NavigationLink("测试3", "/admin/test3"));
menu.put("test4", new NavigationLink("测试4", "/admin/test4"));
menu.put("test5", new NavigationLink("测试5", "/admin/test5"));
menu.put("test6", new NavigationLink("测试6", "/admin/test6"));

request.setAttribute("navigationMenu", menu);
%>
<layout:override name="body">
<navigation:navigate active="test3" menu="${navigationMenu}" breadcrumbs="navigationBreadcrumbs">
<ul>
<navigation:item key="test1">
	<navigation:link key="test1"></navigation:link>
	<ul>
		<navigation:item key="test2">
			<navigation:link key="test2"></navigation:link>
		</navigation:item>
		<navigation:item key="test3">
			<navigation:link key="test3"></navigation:link>
		</navigation:item>
	</ul>
</navigation:item>

<navigation:item key="test4">
	<navigation:link key="test4"></navigation:link>
	<ul>
		<navigation:item key="test5">
			<navigation:link key="test5"></navigation:link>
			<ul>
				<navigation:item key="test6">
					<navigation:link key="test6"></navigation:link>
				</navigation:item>
			</ul>
		</navigation:item>
	</ul>
</navigation:item>
</ul>
</navigation:navigate>

<ul>
<c:forEach items="${navigationBreadcrumbs}" var="breadcrumb">
<li>
<a href="${breadcrumb.url}">
${breadcrumb.label}
</a>
</li>
</c:forEach>
</ul>
</layout:override>

<%@ include file="layouts/html.jsp" %>