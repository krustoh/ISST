<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="pagination" uri="/pagination"%>
<%@ taglib uri="/jsp_layout" prefix="layout"%>
<%@ taglib uri="/navigation" prefix="navigation"%>

<navigation:setNavigationActiveKey key="${category.alias=='experience' ? 'job_' : 'archive_'}${category.alias}"/>

<layout:override name="page-header">
			<div class="pull-right" style="margin-right: 6%;">
				<a style="color:white" class="btn btn-sm btn-primary" href="${baseUrl}collectedNews/categories/${category.alias}/collect.html">
					<i class="icon-plus align-top bigger-125"></i>
						开始采集
				</a>
			</div>
</layout:override>

<layout:override name="content">
	<div class="col-xs-12">
		<%@ include file="../blocks/message.jsp"%>

		<div class="tabbable">
			<ul class="nav nav-tabs" id="myTab">
				<li><a href="${baseUrl}collectedNews/categories/${category.alias}">未发布 </a></li>
				<li><a href="${baseUrl}collectedNews/categories/${category.alias}?published=1">已发布</a></li>
				<li class="active"><a href="#">采集中</a></li>
			</ul>
			<div class="tab-content">
				<div id="news-published" class="tab-pane in active">
					<div class="table-responsive">
						<form action="" class="isst-table-form">

							<table class="table table-striped table-bordered table-hover">
								<thead>
									<tr>
										<th class="center"><label> <input type="checkbox" class="ace" /> <span class="lbl"></span> </label></th>
										<th>ID</th>
										<th>标题</th>
										<th>发布日期</th>
										<th></th>
									</tr>
								</thead>

								<tbody>
									<c:forEach items="${news.items}" var="item">
										<tr>
											<td class="center"><label> <input type="checkbox" class="ace" name="id[]" value="${item.id}" /> <span class="lbl"></span> </label></td>
											<td>${item.id}</td>
											<td><a href="${baseUrl}collectedNews/${item.id}.html">${item.title}</a></td>
											<td><fmt:formatDate value="${item.createdAt}" pattern="yyyy-MM-dd HH:mm:ss" /></td>

											<td>
												<div class="visible-md visible-lg hidden-sm hidden-xs btn-group">
													<a class="btn btn-xs btn-info" href="${baseUrl}collectedNews/${item.id}.html"> <i class="icon-edit bigger-120"></i> </a> <a class="btn btn-xs btn-info"
														href="${baseUrl}collectedNews/{categories}/${category.alias}/publish?id[]=${item.id}"> <i class="icon-edit bigger-120"></i> </a> <a class="btn btn-xs btn-danger"
														href="${baseUrl}collectedNews/categories/${category.alias}/delete?id[]=${item.id}"> <i class="icon-trash bigger-120"></i> </a>
												</div>
												<div class="visible-xs visible-sm hidden-md hidden-lg">
													<div class="inline position-relative">
														<button class="btn btn-minier btn-primary dropdown-toggle" data-toggle="dropdown">
															<i class="icon-cog icon-only bigger-110"></i>
														</button>

														<ul class="dropdown-menu dropdown-only-icon dropdown-yellow pull-right dropdown-caret dropdown-close">

															<li><a href="${baseUrl}collectedNews/${item.id}.html" class="tooltip-success" data-rel="tooltip" title="Edit"> <span class="green"> <i class="icon-edit bigger-120"></i>
																</span> </a>
															</li>
															<li><a href="${baseUrl}collectedNews/{categories}/${category.alias}/publish?id[]=${item.id}" class="tooltip-error" data-rel="tooltip" title="Delete"> <span class="red">
																		<i class="icon-trash bigger-120"></i> </span> </a>
															</li>
															<li><a href="${baseUrl}collectedNews/categories/${category.alias}/delete?id[]=${item.id}" class="tooltip-error" data-rel="tooltip" title="Delete"> <span class="red"> <i
																		class="icon-trash bigger-120"></i> </span> </a>
															</li>
														</ul>
													</div>
												</div></td>

										</tr>
									</c:forEach>
								</tbody>
							</table>

							<div class="row">
								<div class="col-sm-4 isst-table-form-actions">
									<div class="btn-group dropup">
										<button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown">
											批量操作 <i class="icon-angle-up icon-on-right"></i>
										</button>
										<ul class="dropdown-menu">
											<li><a href="${baseUrl}collectedNews/categories/${category.alias}/delete">批量删除</a>
											</li>

											<li><a href="${baseUrl}collectedNews/categories/${category.alias}/publish">批量发布</a>
											</li>
										</ul>
									</div>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</layout:override>

<%@ include file="../layouts/main.jsp"%>