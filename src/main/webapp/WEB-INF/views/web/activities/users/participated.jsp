<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="pagination" uri="/pagination"%>
<%@ taglib uri="/jsp_layout" prefix="layout"%>
<%@ taglib uri="/navigation" prefix="navigation"%>
<%@ taglib uri="/utils" prefix="utils"%>

<navigation:setNavigationActiveKey key="personal_activities_participant"/>

<layout:override name="content">
<div class="col-xs-12">		
			<div class="table-responsive">		
			<c:choose>
			<c:when test="${activities.total>0}">
				<form action="" class="isst-table-form">
					<table class="table table-striped table-bordered table-hover">
						<thead>
							<tr>
								<th>标题</th>
								<th>开始日期</th>
								<th>截止日期</th>
								<th>发布日期</th>
								<th>发布者</th>
								<th></th>
							</tr>
						</thead>
						
						<tbody>
							<c:forEach items="${activities.items}" var="activity">
							<tr>
								<td>
									<a href="<utils:url url="/activities/${activity.id}/view.html" />">${activity.title}</a>
  
								</td>
								<td>
								<fmt:formatDate value="${activity.startTime}" pattern="yyyy-MM-dd"/>
								
								</td>
								<td>
								<fmt:formatDate value="${activity.expireTime}" pattern="yyyy-MM-dd"/>
								
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
								
								<td>
								<c:if test="${activity.valid}">									
									<a style="color:white" class="btn btn-sm btn-danger" href="<utils:url url="/activities/${activity.id}/unparticipate?id[]=${activity.id}" />">
										取消报名
									</a>
								</c:if>									
								</td>
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
</layout:override>

<%@ include file="../../layouts/main.jsp"%>