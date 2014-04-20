<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="pagination" uri="/pagination"%>
<%@ taglib uri="/jsp_layout" prefix="layout"%>
<%@ taglib uri="/field" prefix="field"%>
<%@ taglib uri="/navigation" prefix="navigation"%>

<navigation:setNavigationActiveKey key="alumni"/>
<navigation:setPageTitle label="查看"/>

<layout:override name="content">
	<div class="col-xs-12">
		<%@ include file="../blocks/message.jsp"%>
		<div class="table-responsive">
			<form action="" class="isst-table-form">
				<fieldset>
					<table class="table table-bordered bootstrap-datatable datatable dataTable">
						<thead><tr style="font-weight:bold"><td colspan="6">基本信息&nbsp;&nbsp;(&nbsp;&nbsp;<i class="icon-check bigger-100 "></i>表示不公开&nbsp;&nbsp;)</td></tr></thead>
						<tbody>
							<tr>
								<td class="center">学号</td><td>${alumnus.username}</td>
							  	<td class="center">姓名</td><td>${alumnus.name}</td>
							  	<td class="center">性别</td><td>${alumnus.gender==1?'男':'女'}</td>
							</tr>	
							<tr>
							  	<td class="center">年级</td><td>${alumnus.grade}</td>
							  	<td class="center">班级</td><td>${alumnus.className}</td>
								<td class="center">专业</td><td>${alumnus.major}</td>
							</tr>
							<tr>
								<td class="center">QQ</td><td>${alumnus.qq}<c:if test="${alumnus.privateQQ}">&nbsp;&nbsp;<i class="icon-check bigger-100 "></i></c:if></td>
							  	<td class="center">邮箱</td><td>${alumnus.email}<c:if test="${alumnus.privateEmail}">&nbsp;&nbsp;<i class="icon-check bigger-100 "></i></c:if></td>
							  	<td class="center">联系电话</td><td>${alumnus.phone}<c:if test="${alumnus.privatePhone}">&nbsp;&nbsp;<i class="icon-check bigger-100 "></i></c:if></td>
							</tr>
						</tbody>
						
						<thead><tr style="font-weight:bold" ><td colspan="6">工作信息&nbsp;&nbsp;(&nbsp;&nbsp;<i class="icon-check bigger-100 "></i>表示不公开&nbsp;&nbsp;)</td></tr></thead>
						<tbody>
							<tr>
								<td class="center">工作单位</td><td colspan="5">${alumnus.company}<c:if test="${alumnus.privateCompany}">&nbsp;&nbsp;<i class="icon-check bigger-100 "></i></c:if></td>
							</tr>	
							<tr>
							  	<td class="center">工作职务</td><td colspan="5">${alumnus.position}<c:if test="${alumnus.privatePosition}">&nbsp;&nbsp;<i class="icon-check bigger-100 "></i></c:if></td>
							</tr>
							<tr>
								<td class="center">工作城市</td><td colspan="5">${alumnus.cityName}</td>
							</tr>
						</tbody>
					</table>

					<div class="clearfix form-actions">
						<div class="pull-right" style="margin-right: 0%;">
							<a style="color:white" class="btn btn-sm btn-primary" href="${baseUrl}alumni/${alumnus.id}.html"> 
								<i class="icon-edit bigger-120 "></i>编辑 
							</a>
							
							<a style="color:white" class="btn btn-sm btn-purple" href="${baseUrl}alumni.html"> 
								<i class="icon-download-alt bigger-120"></i>导出 
							</a>
							
							<a style="color:white" class="btn btn-sm btn-grey" href="${baseUrl}alumni.html"> 
								<i class="icon-undo bigger-120 "></i>返回 
							</a>
						</div>
					</div>
				</fieldset>
			</form>
		</div>
	</div>
</layout:override>

<%@ include file="../layouts/main.jsp"%>