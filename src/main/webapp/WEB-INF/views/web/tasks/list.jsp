<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="pagination" uri="/pagination"%>
<%@ taglib uri="/jsp_layout" prefix="layout"%>
<%@ taglib uri="/navigation" prefix="navigation"%>
<%@ taglib uri="/utils" prefix="utils"%>

<navigation:setNavigationActiveKey key="tasks"/>

<layout:override name="content">
<div class="col-xs-12">
			<div class="table-responsive">
			
			<c:choose>
			<c:when test="${tasks.total>0}">
				<form action="" class="isst-table-form">
					<table class="table table-striped table-bordered table-hover">
						<thead>
							<tr>
								<th>任务</th>
								<th>任务类型</th>
								<th>开始日期</th>
								<th>截止日期</th>
								<th></th>	
							</tr>
						</thead>
						
						<tbody>
							<c:forEach items="${tasks.items}" var="task">
							<tr>
								<td>
									<a href="<utils:url url="/tasks/${task.id}/survey.html" />">${task.name}</a>
									<c:if test="${task.finishId==0}">
										<span class="label label-sm label-warning">未完成</span>
									</c:if> 
									<c:if test="${task.finishId>0}">
										<span class="label label-sm label-success">已完成</span>
									</c:if> 
								</td>
								<td>
								<c:if test="${task.type == 0}">去向调查</c:if>
								</td>
								<td>
								<fmt:formatDate value="${task.startTime}" pattern="yyyy-MM-dd"/>								
								</td>
								<td>
								<fmt:formatDate value="${task.expireTime}" pattern="yyyy-MM-dd"/>								
								</td>
								<td>
									<a href="<utils:url url="/tasks/${task.id}/survey.html" />">完成任务>></a> 
								</td>								

							</tr>
							</c:forEach>
						</tbody>	
					</table>
					
					<div class="row">
						<div class="col-sm-8 col-xs-12">
							<!-- pager -->
							<div id="pager" class="pull-right">
								<pagination:paging page="${tasks.page}" total="${tasks.total}" size="${tasks.pageSize}" />
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

<%@ include file="../layouts/main.jsp"%>