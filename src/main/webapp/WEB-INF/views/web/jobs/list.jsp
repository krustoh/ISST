<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="pagination" uri="/pagination"%>
<%@ taglib uri="/jsp_layout" prefix="layout"%>
<%@ taglib uri="/navigation" prefix="navigation"%>
<%@ taglib prefix="utils" uri="/utils"%>

<navigation:setNavigationActiveKey key="job_${category.alias}"/>

<layout:override name="content">
<div class="col-xs-12">
		<div class="table-responsive">	
			<form:form class="form-horizontal isst-form" modelAttribute="condition" method="GET">
					<fieldset>
						<div class="col-xs-12 col-sm-12">
							
							<div class="form-group col-xs-12 col-sm-6">
								<label class="col-xs-12 col-sm-3 col-md-3 control-label no-padding-right" for="keywords">关键字</label> 
								<div class="col-xs-12 col-sm-9">
									<form:input id="keywords" path="keywords" class="form-control"/> 
								</div>
							</div>
							
							<div class="form-group col-xs-12 col-sm-6">
								<button type="submit" class="btn btn-purple btn-sm">
									<i class="icon-search icon-on-right bigger-110"></i>
									查找
								</button>
							</div>	
						</div>
					</fieldset>
				</form:form>	
		
		
		<c:choose>
		<c:when test="${jobs.total>0}">
			<form action="" class="isst-table-form">		
				<table class="table table-striped table-bordered table-hover">
						<thead>
							<tr>
								<th>标题</th>
								<th>公司</th>	
								<th>城市</th>
								<th>发布者</th>
								<th>发布日期</th>
							</tr>
						</thead>
						
						<tbody>
							<c:forEach items="${jobs.items}" var="job">
							<tr>
								<td>
									<i class="icon-circle bigger-120"></i>
									<a href="<utils:url url="/jobs/${job.id}.html" />">${job.title}</a>  
								</td>
								
								<td>${job.company}</td>
								
								<td>${job.cityName}</td>
								
								<td>
								<c:choose>
									<c:when test="${job.userId>0}">
										<a href="<utils:url url="/alumni/${job.user.id}/view.html" returned="true"/>">${archive.user.name}</a>
									</c:when>
									<c:otherwise>
										管理员
									</c:otherwise>
								</c:choose>
								</td>
								
								<td>
								<fmt:formatDate value="${job.updatedAt}" pattern="yyyy-MM-dd"/>								
								</td>
							</tr>
							</c:forEach>
						</tbody>	
					</table>
					
					<div class="row">
						<div class="col-xs-12 col-sm-8">
							<!-- pager -->
							<div id="pager" class="pull-right">
								<pagination:paging page="${jobs.page}" total="${jobs.total}" size="${jobs.pageSize}" />
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
						<strong>查询结果不存在，请重新输入查询条件！</strong>
						<br />
						<br />
					</div>
				</c:otherwise>
				</c:choose>
				</div>
			</div>
</layout:override>



<%@ include file="../layouts/main.jsp"%>