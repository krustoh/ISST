<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="pagination" uri="/pagination"%>
<%@ taglib uri="/jsp_layout" prefix="layout"%>
<%@ taglib uri="/navigation" prefix="navigation"%>

<navigation:setNavigationActiveKey key="restaurants"/>
<navigation:setPageTitle label="菜单列表"/>

<layout:override name="page-header">
			<div class="pull-right" style="margin-right: 6%;">
				<a style="color:white" class="btn btn-sm btn-primary" href="${baseUrl}restaurants/${restaurant.id}/menus/add.html">
					<i class="icon-plus align-top bigger-125"></i>
						添加
				</a>
			</div>
</layout:override>

<layout:override name="content">
<div class="col-xs-12">
				<%@ include file="../../blocks/message.jsp"%>
				<div class="table-responsive">
					<form action="" class="isst-table-form">
						<fieldset>
							<div class="col-sm-12">
							
							<div class="col-sm-2">
							<label class="control-label no-padding-right" for="status">状态</label> 
							<select name="status" id="status">
								<option value="" >--请选择--</option>
								<option value="0">隐藏</option>
								<option value="1">发布</option>
							</select>
							</div>
							
							
							<div class="col-sm-2">
							<label class="control-label no-padding-right" for="city">城市</label> 
							<select name="status" id="status">
								<option value="" >--请选择--</option>
								<option value="0">宁波</option>
								<option value="1">杭州</option>
								<option value="2">上海</option>
								<option value="3">北京</option>
								<option value="4">深圳</option>
							</select>
							</div>
							
							
							<div class="col-sm-3">
							<label class=" control-label no-padding-right" for="status">单位</label> 
							<input type="text" placeholder="不限" /> 
							</div>
							
							
							<div class="col-sm-3">
							<label class="control-label no-padding-right" for="status">其他</label> 
							<input type="text" placeholder="其他" /> 
							</div>
							<button type="button" class="btn btn-purple btn-sm pull-right" style="margin-right: 5%;">
								查找
								<i class="icon-search icon-on-right bigger-110"></i>
							</button>
							</div>
						
						</fieldset>
							
				<div class="space-4"></div> 					
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
								<th>菜名</th>
								<th>价格</th>
								<th>描述</th>	
								<th></th>
							</tr>
						</thead>
						
						<tbody>
							<c:forEach items="${restaurantMenus.items}" var="restaurantMenu">
							<tr>
								<td class="center">
								<label>
								<input type="checkbox" class="ace" name="id[]" value="${restaurantMenu.id}"/> <span class="lbl"></span> </label>
								</td>
								<td>${restaurantMenu.id}</td>
								<td>
									<c:if test="${not empty restaurantMenu.picture}">
										<a href="${restaurantMenu.picture}" data-rel="colorbox"> 
											<img class="restaurants-picture" src="${restaurantMenu.picture}" />
										</a>
									</c:if>
									${restaurantMenu.name}	
								</td>
								<td>${restaurantMenu.price}</td>
								<td>${restaurantMenu.description}</td>
								
								<td>
									<div
										class="visible-md visible-lg hidden-sm hidden-xs btn-group">
										<a class="btn btn-xs btn-info" href="${baseUrl}/restaurants/menus/${restaurantMenu.id}.html">
											<i class="icon-edit bigger-120"></i>
										</a>

										<a class="btn btn-xs btn-danger" href="${baseUrl}restaurants/${restaurant.id}/menus/delete?id[]=${restaurantMenu.id}">
											<i class="icon-trash bigger-120"></i>
										</a>
									</div>
									<div class="visible-xs visible-sm hidden-md hidden-lg">
										<div class="inline position-relative">
											<button class="btn btn-minier btn-primary dropdown-toggle" data-toggle="dropdown">
												<i class="icon-cog icon-only bigger-110"></i>
											</button>
											
											<ul class="dropdown-menu dropdown-only-icon dropdown-yellow pull-right dropdown-caret dropdown-close">												
												<li>
													<a href="${baseUrl}/restaurants/menus/${restaurantMenu.id}.html" class="tooltip-success" data-rel="tooltip" title="Edit">
														<span class="green">
															<i class="icon-edit bigger-120"></i> 
														</span>
													</a>
												</li>

												<li>
													<a href="${baseUrl}restaurants/${restaurant.id}/menus/delete?id[]=${restaurantMenu.id}" class="tooltip-error" data-rel="tooltip" title="Delete"> 
														<span class="red">
															<i class="icon-trash bigger-120"></i>
														</span> 
													</a> 
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
							<a href="${baseUrl}restaurants/${restaurant.id}/menus/delete" class="btn btn-primary">批量删除</a>
						</div>
						
						<div class="col-sm-8">
							<!-- pager -->
							<div id="pager" class="pull-right" style="margin-right: 100px;">
								<pagination:paging page="${restaurantMenus.page}" total="${restaurantMenus.total}" size="${restaurantMenus.pageSize}" />
							</div>
							<!-- end pager -->
						</div>
					</div>
					</form>
				</div>
			</div>
</layout:override>

<%@ include file="../../layouts/main.jsp"%>