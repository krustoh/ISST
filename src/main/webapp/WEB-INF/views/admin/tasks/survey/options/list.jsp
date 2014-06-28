<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="pagination" uri="/pagination"%>
<%@ taglib uri="/jsp_layout" prefix="layout"%>
<%@ taglib uri="/navigation" prefix="navigation"%>
<%@ taglib uri="/utils" prefix="utils"%>

<navigation:setNavigationActiveKey key="tasks"/>
<navigation:setPageTitle label="添加选项"/>

<layout:override name="page-header">
		<div class="pull-right" style="margin-right: 6%;">
			<a style="color:white" class="btn btn-sm btn-primary" href="<utils:url url="/tasks/${task.id }/surveys/options/add.html" />">
				<i class="icon-plus align-top bigger-125"></i>
				添加
			</a>
		</div>
	
</layout:override>

<layout:override name="content">
<div class="col-xs-12">
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
								<th>选项</th>	
								<th></th>
							</tr>
						</thead>
						
						<tbody>
							<c:forEach items="${options}" var="option">
							<tr>
								<td class="center">
								<label>
								<input type="checkbox" class="ace" name="id[]" value="${option.id}"/> <span class="lbl"></span> </label>
								</td>
								<td>${option.id}</td>
								<td>${option.label}</td>
								
								<td>
									<div
										class="visible-md visible-lg hidden-sm hidden-xs btn-group">
										<a class="btn btn-xs btn-info" data-rel="tooltip" data-placement="bottom" title="编辑" href="<utils:url url="/tasks/${task.id }/surveys/options/${option.id}.html" />">
											<i class="icon-edit bigger-120"></i>
										</a>

										<a class="btn btn-xs btn-danger" data-rel="tooltip" data-placement="bottom" title="删除" href="<utils:url url="/tasks/${task.id }/surveys/options/delete?id[]=${option.id}" />">
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
												
												<li><a href="<utils:url url="/tasks/${task.id }/surveys/options/${option.id}.html" />" class="tooltip-success"
													data-rel="tooltip" title="编辑"> <span class="blue">
															<i class="icon-edit bigger-120"></i> </span> </a></li>

												<li><a href="<utils:url url="/tasks/${task.id }/surveys/options/delete?id[]=${option.id}" />" class="tooltip-error"
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
						<a href="<utils:url url="/tasks/${task.id }/surveys/options/delete" />" class="btn btn-primary">批量删除</a>
					</div>
					</div>
					</form>
				</div>
			</div>
</layout:override>

<%@ include file="../../../layouts/main.jsp"%>