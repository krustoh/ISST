<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="pagination" uri="/pagination"%>
<%@ taglib uri="/jsp_layout" prefix="layout"%>
<%@ taglib uri="/navigation" prefix="navigation"%>
<%@ taglib uri="/utils" prefix="utils"%>

<navigation:setNavigationActiveKey key="alumni" />


<layout:override name="content">
	<div class="col-xs-12">
		<div class="table-responsive">
			<form:form class="form-horizontal isst-form" modelAttribute="condition" method="GET">
				<fieldset>
					<div class="col-xs-12 col-sm-12">
						<div class="form-group col-xs-12 col-sm-3">
							<label class="col-xs-12 col-sm-3 col-md-3 control-label no-padding-right" for="name">姓名</label> 
							<div class="col-xs-12 col-sm-9">
								<form:input id="name" path="name" class="form-control"/> 
							</div>
						</div>
						
						<div class="form-group col-xs-12 col-sm-3">
							<label class="col-xs-12 col-sm-3 col-md-3 control-label no-padding-right" for="grade">年级</label> 
							<div class="col-xs-12 col-sm-9">
								<form:input id="grade" path="grade" class="form-control"/> 
							</div>
						</div>
						
						<div class="form-group col-xs-12 col-sm-3">
							<label class="col-xs-12 col-sm-3 col-md-3 control-label no-padding-right" for="company">公司</label> 
							<div class="col-xs-12 col-sm-9">
								<form:input id="company" path="company" class="form-control"/> 
							</div>
						</div>
						
						<div class="form-group col-xs-12 col-sm-3">
							<label class="col-xs-12 col-sm-3 col-md-3 control-label no-padding-right" for="position">职务</label> 
							<div class="col-xs-12 col-sm-9">
								<form:input id="position" path="position" class="form-control"/> 
							</div>
						</div>
					</div>
					
					<div class="col-xs-12 col-sm-12">
					
						<div class="form-group col-xs-12 col-sm-4">
							<label class="col-xs-12 col-sm-3 col-md-3 control-label no-padding-right" for="major">专业</label> 
							<div class="col-xs-12 col-sm-9">
								<form:select  id="major" path="major">
									<form:option value="" label="所有"/>
									<form:options items="${majors}" itemValue="name" itemLabel="name"/>
								</form:select> 
							</div>
						</div>
						
						<div class="form-group col-xs-12 col-sm-3">
							<label class="col-xs-12 col-sm-3 col-md-3 control-label no-padding-right" for="gender">性别</label> 
							<div class="col-xs-12 col-sm-9">
								<form:select  id="gender" path="gender">
									<form:option value="0" label="所有"/>
									<form:option value="1" label="男"/>
									<form:option value="2" label="女"/>
								</form:select> 
							</div>
						</div>
						
						<div class="form-group col-xs-12 col-sm-3">
							<label class="col-xs-12 col-sm-3 col-md-3 control-label no-padding-right" for="cityId">城市</label> 
							<div class="col-xs-12 col-sm-9">
								<form:select  id="cityId" path="cityId">
									<form:option value="0" label="所有"/>
									<form:options items="${cities}" itemValue="id" itemLabel="name"/>
								</form:select> 
							</div>
						</div>
						
						<div class="form-group col-xs-12 col-sm-1">
							<button type="submit" class="btn btn-purple btn-sm">
								<i class="icon-search icon-on-right bigger-110"></i>
								查找
							</button>
						</div>	
					</div>
				</fieldset>		
			</form:form>
			
			<c:choose>
			<c:when test="${users.total>0}">
			<form action="" class="isst-table-form">
				<table class="table table-striped table-bordered table-hover">
					<thead>
						<tr>
							<th>学号</th>
							<th>姓名</th>
							<th>性别</th>
							<th>年级</th>
							<th>专业</th>
							<th>QQ</th>
							<th>联系电话</th>
							<th>邮箱</th>
							
						</tr>
					</thead>

					<tbody>
						<c:forEach items="${users.items}" var="user">
							<tr>							
								<td>
									<a href="<utils:url url="/alumni/${user.id}.html" />">
										${user.username}
									</a>
								</td>
								<td>
									<a href="<utils:url url="/alumni/${user.id}.html" />">
										${user.name}
									</a>
								</td>
								<td>${user.gender==1?"男":"女"}</td>
								<td>${user.grade}</td>
								<td>${user.major}</td>
								<td><c:if test="${user.privateQQ == false}">${user.qq}</c:if></td>
								<td><c:if test="${user.privatePhone == false}">${user.phone}</c:if></td>
								<td><c:if test="${user.privateEmail ==false}">${user.email}</c:if></td>								
							</tr>
						</c:forEach>
					</tbody>
				</table>

				<div class="row">
					<div class="col-xs-12 col-sm-8">
						<!-- pager -->
						<div id="pager" class="pull-right">
							<pagination:paging page="${users.page}" total="${users.total}" size="${users.pageSize}" />
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