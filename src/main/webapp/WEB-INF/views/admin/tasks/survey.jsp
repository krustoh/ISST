<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="pagination" uri="/pagination"%>
<%@ taglib uri="/jsp_layout" prefix="layout"%>
<%@ taglib uri="/navigation" prefix="navigation"%>
<%@ taglib uri="/utils" prefix="utils"%>

<navigation:setNavigationActiveKey key="tasks"/>
<navigation:setPageTitle label="调查结果"/>


<layout:override name="content">
<div class="col-xs-12">
			<div class="table-responsive">
				<form:form class="form-horizontal isst-form" modelAttribute="taskSurveySearchCondition" method="GET">
					<fieldset>
						<div class="col-xs-12 col-sm-12">
							
							<div class="form-group col-xs-12 col-sm-4">
								<label class="col-xs-12 col-sm-3 col-md-3 control-label no-padding-right" for="optionId">选项</label> 
								<div class="col-xs-12 col-sm-9">
									<form:select  id="optionId" path="optionId" class="form-control">
										<form:option value="1" label="留校"/>
										<form:option value="2" label="回家"/>
									</form:select> 
								</div>
							</div>
							
							<div class="form-group col-xs-12 col-sm-4">
								<button type="submit" class="btn btn-purple btn-sm">
									<i class="icon-search icon-on-right bigger-110"></i>
									查找
								</button>
							</div>	
						</div>
					</fieldset>
				</form:form>			
			<c:choose>
			<c:when test="${surveys.total>0}">
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
								<th>姓名</th>
								<th>离校时间</th>
								<th>返校时间</th>	
								<th>创建时间</th>	
							</tr>
						</thead>
						
						<tbody>
							<c:forEach items="${surveys.items}" var="survey">
							<tr>
								<td class="center">
								<label>
								<input type="checkbox" class="ace" name="id[]" value="${survey.id}"/> <span class="lbl"></span> </label>
								</td>
								<td>${survey.id}</td>
								<td>
									<a href="<utils:url url="/alumni/${survey.userId}/view.html" />">${survey.userId.name}</a> 
								</td>
								<td>
								<fmt:formatDate value="${survey.departTime}" pattern="yyyy-MM-dd"/>								
								</td>
								<td>
								<td>
								<fmt:formatDate value="${survey.returnTime}" pattern="yyyy-MM-dd"/>								
								</td>
								<td>
								<td>
								<fmt:formatDate value="${survey.createdAt}" pattern="yyyy-MM-dd HH:mm:ss"/>								
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