<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="pagination" uri="/pagination"%>
<%@ taglib uri="/jsp_layout" prefix="layout"%>
<%@ taglib uri="/field" prefix="field"%>
<%@ taglib uri="/navigation" prefix="navigation"%>
<%@ taglib uri="/utils" prefix="utils"%>

<navigation:setNavigationActiveKey key="personal_info"/>

<layout:override name="content">
	<div class="col-xs-12">
		<%@ include file="../blocks/message.jsp"%>
		<div class="table-responsive">
			<form action="" class="isst-table-form">
				<fieldset>
					<table class="table table-bordered bootstrap-datatable datatable dataTable">
						<thead><tr style="font-weight:bold"><td colspan="6">基本信息</td></tr></thead>
						<tbody>
							<tr>
								<td class="center">学号</td><td>${alumni.username}</td>
							  	<td class="center">姓名</td><td>${alumni.name}</td>
							  	<td class="center">性别</td><td>${alumni.gender==1?'男':'女'}</td>
							</tr>	
							<tr>
							  	<td class="center">年级</td><td>${alumni.grade}</td>
							  	<td class="center">班级</td><td>${alumni.className}</td>
								<td class="center">专业</td><td>${alumni.major}</td>
							</tr>
							<tr>
								<td class="center">QQ</td><td><c:if test="${alumni.privateQQ ==false}">${alumni.qq}</c:if></td>
							  	<td class="center">邮箱</td><td><c:if test="${alumni.privateEmail == false}">${alumni.email}</c:if></td>
							  	<td class="center">联系电话</td><td><c:if test="${alumni.privatePhone ==false}">${alumni.phone}</c:if></td>
							</tr>
						</tbody>
						
						<thead><tr style="font-weight:bold" ><td colspan="6">工作信息</td></tr></thead>
						<tbody>
							<tr>
								<td class="center">工作单位</td><td colspan="5"><c:if test="${alumni.privateCompany == false}">${alumni.company}</c:if></td>
							</tr>	
							<tr>
							  	<td class="center">工作职务</td><td colspan="5"><c:if test="${alumni.privatePosition ==false}">${alumni.position}</c:if></td>
							</tr>
							<tr>
								<td class="center">工作城市</td><td colspan="5">${alumni.cityName}</td>
							</tr>
						</tbody>
					</table>

					<div class="clearfix form-actions">
						<div class="pull-right" style="margin-right: 0%;">
							<a style="color:white" class="btn btn-sm btn-primary" href="<utils:url url="/users/edit.html" returned="true"/>"> 
								<i class="icon-edit bigger-120 "></i>编辑 
							</a>
												
							<a style="color:white" class="btn btn-sm btn-grey" href="<utils:returnUrl url="/alumni.html" />"> 
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