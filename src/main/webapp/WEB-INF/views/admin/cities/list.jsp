<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="pagination" uri="/pagination"%>
<%@ taglib uri="/jsp_layout" prefix="layout"%>
<%@ taglib uri="/navigation" prefix="navigation"%>
<%@ taglib prefix="utils" uri="/utils"%>

<navigation:setNavigationActiveKey key="cities"/>

<layout:override name="page-header">
			<div class="pull-right" style="margin-right: 6%;">
				<a style="color:white" class="btn btn-sm btn-primary" href="<utils:url url="/cities/add.html" />">
					<i class="icon-plus align-top bigger-125"></i>
						添加
				</a>
			</div>
</layout:override>

<layout:override name="content">
<div class="col-xs-12">
		<div class="table-responsive">	
			<form:form class="form-horizontal isst-form" modelAttribute="condition" method="GET">
					<fieldset>
						<div class="col-xs-12 col-sm-12">
							<div class="form-group col-xs-12 col-sm-4">
								<label class="col-xs-12 col-sm-3 col-md-3 control-label no-padding-right" for="status">状态</label> 
								<div class="col-xs-12 col-sm-9">
									<form:select  id="status" path="status" class="form-control">
										<form:option value="-1" label="所有"/>
										<form:option value="0" label="隐藏"/>
										<form:option value="1" label="发布"/>
									</form:select> 
								</div>
							</div>
							
							<div class="form-group col-xs-12 col-sm-4">
								<label class="col-xs-12 col-sm-3 col-md-3 control-label no-padding-right" for="keywords">关键字</label> 
								<div class="col-xs-12 col-sm-9">
									<form:input id="keywords" path="keywords" class="form-control"/> 
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
								<th>城市</th>
								<th>城主</th>
								<th>活动</th>
								<th>同城校友</th>
								<th></th>
							</tr>
						</thead>
						
						<tbody>
							<c:forEach items="${cities.items}" var="city">
							<tr>
								<td class="center">
								<label>
								<input type="checkbox" class="ace" name="id[]" value="${city.id}"/> <span class="lbl"></span> </label>
								</td>
								<td>${city.id}</td>
								<td>
									<a href="<utils:url url="/cities/${city.id}.html" />">${city.name}</a>
									<c:if test="${city.status==0}">
										<span class="label label-sm label-warning">隐藏</span>
									</c:if>  
								</td>
								<td>
									<a href="<utils:url url="/alumni/${city.userId}/view.html" returned="true"/>">${city.userId}</a> 
								</td>
								<td>
									<a href="<utils:url url="/cities/${city.id}/activities.html" returned="true"/>">活动详情>></a>
								</td>
								
								<td>
									<a href="<utils:url url="#" returned="true"/>">同城校友详情>></a>
								</td>
								
								<td>
									<div class="visible-md visible-lg hidden-sm hidden-xs btn-group">
										<a class="btn btn-xs btn-info tooltip-danger" data-rel="tooltip" data-placement="bottom" title="编辑" href="<utils:url url="/cities/${city.id}.html" />">
											<i class="icon-edit bigger-120"></i>
										</a>

										<a class="btn btn-xs btn-danger tooltip-danger" data-rel="tooltip" data-placement="bottom" title="删除" href="<utils:url url="/cities/delete?id[]=${city.id}" />">
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
												<li><a href="<utils:url url="/cities/${city.id}.html" />" class="tooltip-success"
													data-rel="tooltip" title="编辑"> <span class="green">
												<i class="icon-edit bigger-120"></i> </span> </a></li>

												<li><a href="<utils:url url="/cities/delete?id[]=${city.id}" />" class="tooltip-error"
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
						<div class="col-xs-12 col-sm-4 isst-table-form-actions" >
						<div class="btn-group dropup">
							<button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown">
								批量操作
								<i class="icon-angle-up icon-on-right"></i>
							</button>
							<ul class="dropdown-menu">
								<li><a href="<utils:url url="/cities/delete" />">批量删除</a></li>

								<li><a href="<utils:url url="/cities/publish" />">批量发布</a></li>

								<li><a href="<utils:url url="/cities/hide" />">批量隐藏</a></li>

							</ul>
						</div>				
						</div>
						<div class="col-xs-12 col-sm-8">
							<!-- pager -->
							<div id="pager" class="pull-right">
								<pagination:paging page="${cities.page}" total="${cities.total}" size="${cities.pageSize}" />
							</div>
							<!-- end pager -->
						</div>
					</div>
					</form>
				</div>
			</div>
</layout:override>



<%@ include file="../layouts/main.jsp"%>