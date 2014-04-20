<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="pagination" uri="/pagination"%>
<%@ taglib uri="/jsp_layout" prefix="layout"%>
<%@ taglib uri="/navigation" prefix="navigation"%>

<navigation:setNavigationActiveKey key="alumni" />

<layout:override name="page-header">
	<div class="pull-right" style="margin-right: 5%;">
		<div class="btn-group">
			<button type="button" class="btn btn-sm btn-primary dropdown-toggle" data-toggle="dropdown">
				<i class="icon-plus align-top bigger-125"></i> 
				添加 
				<i class="icon-angle-down icon-on-right"></i>
			</button>
		
			<ul class="dropdown-menu">
				<li><a href="${baseUrl}archives/categories/${category.alias}/delete">单个添加</a></li>

				<li><a href="${baseUrl}archives/categories/${category.alias}/publish">批量添加</a></li>
			</ul>
		</div>
	</div>
	
	
</layout:override>

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
			
			<form action="" class="isst-table-form">
				<table class="table table-striped table-bordered table-hover">
					<thead>
						<tr>
							<th class="center"><label> <input type="checkbox" class="ace" /> <span class="lbl"></span> </label></th>
							<th>ID</th>
							<th>学号</th>
							<th>姓名</th>
							<th>性别</th>
							<th>年级</th>
							<th>专业</th>
							<th>联系电话</th>
							<th>邮箱</th>
							<th></th>
						</tr>
					</thead>

					<tbody>
						<c:forEach items="${users.items}" var="user">
							<tr>
								<td class="center"><label> <input type="checkbox" class="ace" name="id[]" value="${user.id}" /> <span class="lbl"></span> </label></td>
								<td>${user.id}</td>								
								<td>
									<a href="${baseUrl}alumni/${user.id}/view.html">
										${user.username}
									</a>
								</td>
								<td>
									<a href="${baseUrl}alumni/${user.id}/view.html">
										${user.name}
									</a>
								</td>
								<td>${user.gender==1?"男":"女"}</td>
								<td>${user.grade}</td>
								<td>${user.major}</td>
								<td>${user.phone}</td>
								<td>${user.email}</td>
								<td>
									<div class="visible-md visible-lg hidden-sm hidden-xs btn-group">
										<a class="btn btn-xs btn-success" href="${baseUrl}alumni/${user.id}/view.html"> <i class="icon-zoom-in bigger-120"></i></a> 
										<a class="btn btn-xs btn-info" href="${baseUrl}alumni/${user.id}.html"> <i class="icon-edit bigger-120"></i></a> 
										<a class="btn btn-xs btn-danger" href="${baseUrl}alumni/delete?id[]=${user.id}"> <i class="icon-trash bigger-120"></i> </a>
									</div>
									<div class="visible-xs visible-sm hidden-md hidden-lg">
										<div class="inline position-relative">
											<button class="btn btn-minier btn-primary dropdown-toggle" data-toggle="dropdown">
												<i class="icon-cog icon-only bigger-110"></i>
											</button>

											<ul class="dropdown-menu dropdown-only-icon dropdown-yellow pull-right dropdown-caret dropdown-close">
												<li><a href="${baseUrl}alumni/${user.id}/view.html" class="tooltip-info" data-rel="tooltip" title="View"> <span class="blue"> <i class="icon-zoom-in bigger-120"></i>
													</span> </a>
												</li>
												
												<li><a href="${baseUrl}alumni/${user.id}.html" class="tooltip-success" data-rel="tooltip" title="Edit"> <span class="green"> <i class="icon-edit bigger-120"></i>
													</span> </a>
												</li>

												<li><a href="${baseUrl}alumni/delete?id[]=${user.id}" class="tooltip-error" data-rel="tooltip" title="Delete"> <span class="red"> <i
															class="icon-trash bigger-120"></i> </span> </a>
												</li>
											</ul>
										</div>
									</div>
								</td>

							</tr>
						</c:forEach>
					</tbody>
				</table>

				<div class="row">
					<div class="col-sm-4 isst-table-form-actions">
						<a href="${baseUrl}alumni/delete" class="btn btn-primary">批量删除</a>
					</div>

					<div class="col-sm-8">
						<!-- pager -->
						<div id="pager" class="pull-right" style="margin-right: 100px;">
							<pagination:paging page="${users.page}" total="${users.total}" size="${users.pageSize}" />
						</div>
						<!-- end pager -->
					</div>
				</div>
			</form>
		</div>
	</div>
</layout:override>

<%@ include file="../layouts/main.jsp"%>