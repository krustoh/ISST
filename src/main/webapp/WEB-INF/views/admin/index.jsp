<%@ page language="java" import="java.util.*, cn.edu.zju.isst.taglib.NavigationLink" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %> 
<%@ taglib uri="/jsp_layout" prefix="layout"%>
<%@ taglib uri="/navigation" prefix="navigation"%>


<layout:override name="content">

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

<%@ include file="layouts/main.jsp" %>