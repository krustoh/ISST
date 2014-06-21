<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="pagination" uri="/pagination"%>
<%@ taglib uri="/jsp_layout" prefix="layout"%>
<%@ taglib uri="/navigation" prefix="navigation"%>
<%@ taglib uri="/utils" prefix="utils"%>

<navigation:setNavigationActiveKey key="city_activity"/>
<navigation:setPageTitle label="${city.name}活动"/>

<layout:override name="page-header">
		<div class="pull-right" style="margin-right: 6%;">
			<a style="color:white" class="btn btn-sm btn-primary" href="<utils:url url="/cities/activities/add.html" />">
				<i class="icon-plus align-top bigger-125"></i>
				添加
			</a>
		</div>
</layout:override>

<layout:override name="content">
<div class="col-xs-12">
				<form:form class="form-horizontal isst-form" modelAttribute="condition" method="GET">
					<fieldset>
						<div class="col-xs-12 col-sm-12">							
							<div class="form-group col-xs-12 col-sm-4">
								<label class="col-xs-12 col-sm-3 col-md-3 control-label no-padding-right" for="poster">发起人</label> 
								<div class="col-xs-12 col-sm-9">
									<form:input id="poster" path="poster" class="form-control" placeholder="学号或姓名"/>  
								</div>
							</div>
							
							<div class="form-group col-xs-12 col-sm-4">
								<label class="col-xs-12 col-sm-3 col-md-3 control-label no-padding-right" for="keywords">关键字</label> 
								<div class="col-xs-12 col-sm-9">
									<form:input id="keywords" path="keywords" class="form-control"/> 
								</div>
							</div>
							
							<div class="form-group col-xs-12 col-sm-2">
								<button type="submit" class="btn btn-purple btn-sm">
									<i class="icon-search icon-on-right bigger-110"></i>
									查找
								</button>
							</div>	
						</div>
					</fieldset>
				</form:form>
		

		<div class="tabbable">
			<ul class="nav nav-tabs" id="myTab">
				<li>
					<a href="<utils:url url="/cities/${user.classId}/activities.html" />">所有活动</a>
				</li>
				<li>
					<a href="<utils:url url="/users/activities.html" />">发起的活动</a>
				</li>
				<li>
					<a href="<utils:url url="/collectedNews/categories/${category.alias}/ignored.html" />">参加的活动</a>
				</li>
			</ul>
			
		<div class="tab-content">
			<div class="tab-pane in active">
			
			<div class="table-responsive">		
			<c:choose>
			<c:when test="${activities.total>0}">
				<form action="" class="isst-table-form">
					<table class="table table-striped table-bordered table-hover">
						<thead>
							<tr>
								<th>标题</th>
								<th>发布日期</th>
								<th>发布者</th>
								<th></th>
							</tr>
						</thead>
						
						<tbody>
							<c:forEach items="${activities.items}" var="activity">
							<tr>
								<td>
									<a href="<utils:url url="/cities/${user.cityId}/activities/${activity.id}.html" />">${activity.title}</a>
									<c:if test="${activity.participated==true}">
										<span class="label label-sm label-warning">已报名</span>
									</c:if>  
								</td>
								<td>
								<fmt:formatDate value="${activity.updatedAt}" pattern="yyyy-MM-dd"/>
								
								</td>
								<td>
								<c:choose>
									<c:when test="${activity.userId>0}">
										<a href="<utils:url url="/alumni/${activity.user.id}/view.html" returned="true"/>">${activity.user.name}</a>
									</c:when>
									<c:otherwise>
										管理员
									</c:otherwise>
								</c:choose>
								</td>
								
								<td>我要报名</td>
							</tr>
							</c:forEach>
						</tbody>	
					</table>
					
					<div class="row">
						<div class="col-sm-8 col-xs-12">
							<!-- pager -->
							<div id="pager" class="pull-right">
								<pagination:paging page="${activities.page}" total="${activities.total}" size="${activities.pageSize}" />
							</div>
							<!-- end pager -->
						</div>
					</div>
					</form>
				</c:when>
				
				
				<c:otherwise>
				<div class="alert alert-warning">
					<button type="button" class="close" data-dismiss="alert">
						<i class="icon-remove"></i>
					</button>
					<strong>没有记录！</strong>
				<br />
				<br />
				</div>
				</c:otherwise>
				</c:choose>
				</div>
				</div>
				</div>
			</div>
		</div>
</layout:override>

<%@ include file="../layouts/main.jsp"%>