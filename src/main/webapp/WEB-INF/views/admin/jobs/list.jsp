<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="pagination" uri="/pagination"%>
<%@ taglib uri="/jsp_layout" prefix="layout"%>
<%@ taglib uri="/navigation" prefix="navigation"%>

<navigation:setNavigationActiveKey key="job_${category.alias}"/>

<layout:override name="page-header">
			<div class="pull-right" style="margin-right: 6%;">
				<a style="color:white" class="btn btn-sm btn-primary" href="${baseUrl}jobs/categories/${category.alias}/add.html">
					<i class="icon-plus align-top bigger-125"></i>
						添加
				</a>
			</div>
</layout:override>

<layout:override name="content">
<div class="col-xs-12">
				<%@ include file="../blocks/message.jsp"%>
				<div class="table-responsive">
					<form action="" class="isst-table-form">
					<!--  	<div class="row">
							<div class="col-sm-2 isst-table-form-actions">状态
								<input type="text" class="form-control" placeholder="Search">
							</div>
							
							<div class="col-sm-2 isst-table-form-actions">城市
								
								<input type="text" class="form-control" placeholder="Search">
							</div>
							
							<div class="col-sm-3 isst-table-form-actions">工作单位
								<input type="text" class="form-control" placeholder="Search">
							</div>
							
							<div class="col-sm-3 isst-table-form-actions">
								<input type="text" class="form-control" placeholder="Search">
							</div>
							
							<div class="col-sm-2 isst-table-form-actions">
								<button type="submit" class="btn btn-default">Submit</button>
							</div>
							
						</div>
						
						<div class="space-4"></div>  -->
						
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
								<th>单位</th>	
								<th>城市</th>	
								<th></th>
							</tr>
						</thead>
						
						<tbody>
							<c:forEach items="${jobs.items}" var="job">
							<tr>
								<td class="center">
								<label>
								<input type="checkbox" class="ace" name="id[]" value="${job.id}"/> <span class="lbl"></span> </label>
								</td>
								<td>${job.id}</td>
								<td>
									<a href="${baseUrl}jobs/${job.id}.html">${job.title}</a>
									<c:if test="${job.status==0}">
										<span class="label label-sm label-warning">隐藏</span>
									</c:if>  
								</td>
								<td>
								<fmt:formatDate value="${job.updatedAt}" pattern="yyyy-MM-dd HH:mm:ss"/>
								
								</td>
								<td>${job.userId>0?job.user.name:"管理员"}</td>
								
								<td>${job.company}</td>
								
								<td>${job.cityName}</td>
								
								<td>
									<div
										class="visible-md visible-lg hidden-sm hidden-xs btn-group">
										<a class="btn btn-xs btn-info" href="${baseUrl}jobs/${job.id}.html">
											<i class="icon-edit bigger-120"></i>
										</a>

										<a class="btn btn-xs btn-danger" href="${baseUrl}jobs/categories/${category.alias}/delete?id[]=${job.id}">
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
												
												<li><a href="${baseUrl}jobs/${job.id}.html" class="tooltip-success"
													data-rel="tooltip" title="Edit"> <span class="green">
															<i class="icon-edit bigger-120"></i> </span> </a></li>

												<li><a href="${baseUrl}jobs/categories/${category.alias}/delete?id[]=${job.id}" class="tooltip-error"
													data-rel="tooltip" title="Delete"> <span class="red">
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
						<div class="col-sm-4 isst-table-form-actions" >
						<div class="btn-group dropup">
							<button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown">
								批量操作
								<i class="icon-angle-up icon-on-right"></i>
							</button>
							<ul class="dropdown-menu">
								<li><a href="${baseUrl}jobs/categories/${category.alias}/delete">批量删除</a></li>

								<li><a href="${baseUrl}jobs/categories/${category.alias}/publish">批量发布</a></li>

								<li><a href="${baseUrl}jobs/categories/${category.alias}/hide">批量隐藏</a></li>

							</ul>
						</div>				
						</div>
						<div class="col-sm-8">
							<!-- pager -->
							<div id="pager" class="pull-right" style="margin-right: 100px;">
								<pagination:paging page="${jobs.page}" total="${jobs.total}" size="${jobs.pageSize}" />
							</div>
							<!-- end pager -->
						</div>
					</div>
					</form>
				</div>
			</div>
</layout:override>

<%@ include file="../layouts/main.jsp"%>