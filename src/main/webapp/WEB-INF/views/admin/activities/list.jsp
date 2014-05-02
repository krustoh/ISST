<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="pagination" uri="/pagination"%>
<%@ taglib uri="/jsp_layout" prefix="layout"%>
<%@ taglib uri="/navigation" prefix="navigation"%>
<%@ taglib uri="/utils" prefix="utils"%>

<navigation:setNavigationActiveKey key="cities"/>
<navigation:setPageTitle label="同城活动"/>

<layout:override name="page-header">
		<div class="pull-right" style="margin-right: 6%;">
			<a style="color:white" class="btn btn-sm btn-primary" href="<utils:url url="/cities/${city.id}/activities/add.html" />">
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
							
							<div class="form-group col-xs-12 col-sm-2">
								<label class="col-xs-12 col-sm-4 col-md-4 control-label no-padding-right" for="status">状态</label> 
								<div class="col-xs-12 col-sm-8">
									<form:select  id="status" path="status">
										<form:option value="-1" label="所有"/>
										<form:option value="0" label="隐藏"/>
										<form:option value="1" label="发布"/>
									</form:select> 
								</div>
							</div>
							
							<div class="form-group col-xs-12 col-sm-2">
								<label class="col-xs-12 col-sm-4 col-md-4 control-label no-padding-right" for="cityId">城市</label> 
								<div class="col-xs-12 col-sm-8">
									<form:select  id="cityId" path="cityId">
										<form:option value="0" label="所有"/>
										<form:options items="${cities}" itemValue="id" itemLabel="name"/>
									</form:select> 
								</div>
							</div>
							
							<div class="form-group col-xs-12 col-sm-3">
								<label class="col-xs-12 col-sm-3 col-md-3 control-label no-padding-right" for="poster">发起人</label> 
								<div class="col-xs-12 col-sm-9">
									<form:input id="poster" path="poster" class="form-control" placeholder="学号或姓名"/>  
								</div>
							</div>
							
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
								<th>发布日期</th>
								<th>发布者</th>	
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
									<a href="<utils:url url="/cities/${city.id}/activities/${activity.id}.html" />">${activity.title}</a>
									<c:if test="${activity.status==0}">
										<span class="label label-sm label-warning">隐藏</span>
									</c:if>  
								</td>
								<td>
								<fmt:formatDate value="${activity.updatedAt}" pattern="yyyy-MM-dd HH:mm:ss"/>
								
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
									<div
										class="visible-md visible-lg hidden-sm hidden-xs btn-group">
										<a class="btn btn-xs btn-info" data-rel="tooltip" data-placement="bottom" title="编辑" href="<utils:url url="/cities/${city.id}/activities/${activity.id}.html" />">
											<i class="icon-edit bigger-120"></i>
										</a>

										<a class="btn btn-xs btn-danger" data-rel="tooltip" data-placement="bottom" title="删除" href="<utils:url url="/cities/${city.id}/activities/delete?id[]=${activity.id}" />">
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
												
												<li><a href="<utils:url url="/cities/${city.id}/activities/${activity.id}.html" />" class="tooltip-success"
													data-rel="tooltip" title="编辑"> <span class="green">
															<i class="icon-edit bigger-120"></i> </span> </a></li>

												<li><a href="<utils:url url="/cities/${city.id}/activities/delete?id[]=${activity.id}" />" class="tooltip-error"
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
						<div class="col-sm-4 col-xs-12 isst-table-form-actions" >
						<div class="btn-group dropup">
							<button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown">
								批量操作
								<i class="icon-angle-up icon-on-right"></i>
							</button>
							<ul class="dropdown-menu">
								<li><a href="<utils:url url="/cities/${city.id}/activities/delete" />">批量删除</a></li>

								<li><a href="<utils:url url="/cities/${city.id}/activities/publish" />">批量发布</a></li>

								<li><a href="<utils:url url="/cities/${city.id}/activities/hide" />">批量隐藏</a></li>

							</ul>
						</div>				
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
				</div>
			</div>
</layout:override>

<%@ include file="../layouts/main.jsp"%>