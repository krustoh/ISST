<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="pagination" uri="/pagination"%>
<%@ taglib uri="/jsp_layout" prefix="layout"%>

<layout:override name="content">
	<div class="page-content">
		<div class="page-header">
			<div class="pull-right" style="margin-right: 5%;">
				<a style="color:white" class="btn btn-primary" href="${baseUrl}archives/categories/campus/add.html">
					<i class="icon-plus align-top bigger-125"></i>
						添加快讯
				</a>
			</div>
			<h1 style="margin-top: 5px;">
				软院生活 <small><i class="icon-double-angle-right"></i> 快讯</small>
			</h1>
		</div>
		<div class="row">
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
								<th>标题</th>
								<th>状态</th>
								<th>发布日期</th>
								<th>发布者</th>	
								<th></th>
							</tr>
						</thead>
						
						<tbody>
							<c:forEach items="${archives.items}" var="archive">
							<tr>
								<td class="center">
								<label>
								<input type="checkbox" class="ace" /> <span class="lbl"></span> </label>
								</td>
								
								<td><a href="#">${archive.title}</a></td>
								<td><a href="#">${archive.status}</a></td>
							
								<td class="hidden-480">
								<fmt:formatDate value="${archive.updatedAt}" pattern="yyyy-MM-dd"/>
								
								</td>
								<td>${archive.userId>0?archive.user.name:"管理员"}</td>
								
								<td>
									<div
										class="visible-md visible-lg hidden-sm hidden-xs btn-group">
										<button class="btn btn-xs btn-success">
											<i class="icon-ok bigger-120"></i>
										</button>

										<button class="btn btn-xs btn-info">
											<i class="icon-edit bigger-120"></i>
										</button>

										<button class="btn btn-xs btn-danger">
											<i class="icon-trash bigger-120"></i>
										</button>

										<button class="btn btn-xs btn-warning">
											<i class="icon-flag bigger-120"></i>
										</button>
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
						<div class="col-sm-4">
							<button data-toggle="dropdown"
								class="btn btn-primary dropdown-toggle">
								批量操作 <i class="icon-angle-down icon-on-right"></i>
							</button>

							<ul class="dropdown-menu">
								<li><a href="#">批量删除</a></li>

								<li><a href="#">批量发布</a></li>

								<li><a href="#">批量隐藏</a></li>

							</ul>
						</div>
						<div class="col-sm-8">
							<!-- pager -->
							<div id="pager" class="pull-right" style="margin-right: 100px;">
								<pagination:paging page="${archives.page}" total="${archives.total}" size="${archives.pageSize}" />
							</div>
							<!-- end pager -->
						</div>
					</div>
					</form>
				</div>
			</div>
					<!-- /.col -->
		</div>
		<!-- /.row -->
	</div>
	<!-- /.page-content -->
	<!-- /.main-content -->
</layout:override>

<%@ include file="../layouts/main.jsp"%>