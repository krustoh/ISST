<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="pagination" uri="/pagination"%>
<%@ taglib uri="/jsp_layout" prefix="layout"%>

<c:set var="navigationActiveKey" value="便捷服务" scope="request"></c:set>

<layout:override name="page-header">
			<div class="pull-right" style="margin-right: 6%;">
				<a style="color:white" class="btn btn-sm btn-primary" href="${baseUrl}restaurants/add.html">
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
								<th>餐馆名称</th>
								<th>订餐电话</th>
								<th>地址</th>
								<th>营业时间</th>	
								<th></th>
							</tr>
						</thead>
						
						<tbody>
							<c:forEach items="${restaurants.items}" var="restaurant">
							<tr>
								<td class="center">
								<label>
								<input type="checkbox" class="ace" name="id[]" value="${restaurant.id}"/> <span class="lbl"></span> </label>
								</td>
								<td>${restaurant.id}</td>
								<td><a href="#">${restaurant.name}</a></td>
								<td>${archive.hotline}</td>
								<td>${archive.address}</td>								
								<td>
								<fmt:formatDate value="${archive.business_hours}" />
								
								</td>
								
								<td>
									<div
										class="visible-md visible-lg hidden-sm hidden-xs btn-group">
										<a class="btn btn-xs btn-info" href="${baseUrl}restaurants/${restaurant.id}.html">
											<i class="icon-edit bigger-120"></i>
										</a>

										<a class="btn btn-xs btn-danger" href="${baseUrl}restaurants/delete?id[]=${restaurant.id}">
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
												<li><a href="#" class="tooltip-info" data-rel="tooltip"
													title="View"> <span class="blue"> <i
															class="icon-zoom-in bigger-120"></i> </span> </a></li>

												<li><a href="#" class="tooltip-success"
													data-rel="tooltip" title="Edit"> <span class="green">
															<i class="icon-edit bigger-120"></i> </span> </a></li>

												<li><a href="#" class="tooltip-error"
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
								<li><a href="${baseUrl}restaurants/delete">批量删除</a></li>

								<li><a href="#">批量发布</a></li>

								<li><a href="#">批量隐藏</a></li>

							</ul>
						</div>				
						</div>
						<div class="col-sm-8">
							<!-- pager -->
							<div id="pager" class="pull-right" style="margin-right: 100px;">
								<pagination:paging page="${restaurants.page}" total="${restaurants.total}" size="${restaurants.pageSize}" />
							</div>
							<!-- end pager -->
						</div>
					</div>
					</form>
				</div>
			</div>
</layout:override>

<%@ include file="../layouts/main.jsp"%>