<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="pagination" uri="/pagination"%>
<%@ taglib uri="/jsp_layout" prefix="layout"%>
<%@ taglib uri="/navigation" prefix="navigation"%>
<%@ taglib uri="/utils" prefix="utils"%>

<navigation:setNavigationActiveKey key="activities"/>


<layout:override name="content">
<div class="col-xs-12">
	<div class="widget-box transparent center">
		<div class="widget-header">
			<h3 class="lighter">${campus.title}</h4>
			<div>
				发布者：${archives.user.name}
				&nbsp;&nbsp;
				发布时间：<fmt:formatDate value="${campus.updatedAt}" pattern="yyyy-MM-dd"/>
			</div>
		</div>

		<div class="widget-body">
			<div class="widget-main padding-6 no-padding-left no-padding-right">
				${campus.content}
			</div>
		</div>
	</div>

	<div class="clearfix form-actions">
		<div class="col-md-offset-5 col-md-9">
			<a class="btn" href="<utils:returnUrl url="/campus/activities.html" />">
				<i class="icon-undo bigger-110"></i> 返回
			</a>
		</div>
	</div>		
</div>
</layout:override>

<%@ include file="../../layouts/main.jsp"%>