<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="pagination" uri="/pagination"%>
<%@ taglib uri="/jsp_layout" prefix="layout"%>
<%@ taglib uri="/navigation" prefix="navigation"%>
<%@ taglib uri="/utils" prefix="utils"%>

<navigation:setNavigationActiveKey key="users_activities"/>


<layout:override name="page-header">
			<div class="pull-right" style="margin-right: 6%;">
				<a style="color:white" class="btn btn-sm btn-primary" href="<utils:url url="/activities/add.html" />">
					<i class="icon-plus align-top bigger-125"></i>
						发布
				</a>
			</div>
</layout:override>

<layout:override name="content">
<div class="col-xs-12">
				<form:form class="form-horizontal isst-form" modelAttribute="condition" method="GET">
					<fieldset>
						<div class="col-xs-12 col-sm-12">
							
							<div class="form-group col-xs-12 col-sm-3">
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
			
			<div class="table-responsive">	
			<c:choose>
			<c:when test="${activities.total>0}">
				<form action="" class="isst-table-form">
					<table class="table table-striped table-bordered table-hover">
						<thead>
							<tr>
								<th class="center">
								<label> 
								<input type="checkbox" class="ace" /> 
								<span class="lbl"></span> 
								</label>
								</th>
								<th>ID</th>
								<th>标题</th>
								<th>开始日期</th>
								<th>截止日期</th>
								<th>发布日期</th>	
								<th></th>
							</tr>
						</thead>
						
						<tbody>
							<c:forEach items="${activities.items}" var="activity">
							<tr>
								<td class="center">
								<label>
								<input type="checkbox" class="ace" name="id[]" value="${activity.id}"/> <span class="lbl"></span> </label>
								</td>
								<td>${activity.id}</td>
								<td>
									<a href="<utils:url url="/activities/${activity.id}.html" />">${activity.title}</a> 
									<c:if test="${activity.status==0}">
										<span class="label label-sm label-warning">未审核</span>
									</c:if>
								</td>
								<td>
								<fmt:formatDate value="${activity.startTime}" pattern="yyyy-MM-dd"/>
								
								</td>
								<td>
								<fmt:formatDate value="${activity.expireTime}" pattern="yyyy-MM-dd"/>
								
								</td>
								<td>
								<fmt:formatDate value="${activity.updatedAt}" pattern="yyyy-MM-dd HH:mm:ss"/>
								
								</td>
								
								<td>
									<div
										class="visible-md visible-lg hidden-sm hidden-xs btn-group">
										<a class="btn btn-xs btn-info" data-rel="tooltip" data-placement="bottom" title="编辑" href="<utils:url url="/activities/${activity.id}.html" />">
											<i class="icon-edit bigger-120"></i>
										</a>

										<a class="btn btn-xs btn-danger" data-rel="tooltip" data-placement="bottom" title="删除" href="<utils:url url="/users/activities/delete?id[]=${activity.id}" />">
											<i class="icon-trash bigger-120"></i>
										</a>
									</div>
									<div class="visible-xs visible-sm hidden-md hidden-lg">
										<div class="inline position-relative">
											<button class="btn btn-minier btn-primary dropdown-toggle"
												data-toggle="dropdown">
												<i class="icon-cog icon-only bigger-110"></i>
											</button>

											<ul
												class="dropdown-menu dropdown-only-icon dropdown-yellow pull-right dropdown-caret dropdown-close">
												
												<li><a href="<utils:url url="/activities/${activity.id}.html" />" class="tooltip-success"
													data-rel="tooltip" title="编辑"> <span class="blue">
															<i class="icon-edit bigger-120"></i> </span> </a></li>

												<li><a href="<utils:url url="/users/activities/delete?id[]=${activity.id}" />" class="tooltip-error"
													data-rel="tooltip" title="删除"> <span class="red">
															<i class="icon-trash bigger-120"></i> </span> </a></li>
											</ul>
										</div>
									</div>
								</td>

							</tr>
							</c:forEach>
						</tbody>	
					</table>
					
					<div class="row">
					
					<div class="col-xs-12 col-sm-4 isst-table-form-actions">
						<a href="<utils:url url="/users/activities/delete" />" class="btn btn-primary">批量删除</a>
					</div>
					
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