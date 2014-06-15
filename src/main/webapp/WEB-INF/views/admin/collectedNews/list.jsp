<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="pagination" uri="/pagination"%>
<%@ taglib uri="/jsp_layout" prefix="layout"%>
<%@ taglib uri="/navigation" prefix="navigation"%>
<%@ taglib uri="/utils" prefix="utils"%>

<navigation:setNavigationActiveKey key="${category.alias=='experience' ? 'job_' : 'archive_'}${category.alias}"/>
<navigation:setPageTitle label="采集"/>

<layout:override name="content">
	<div class="col-xs-12">

		<div class="tabbable">
			<ul class="nav nav-tabs" id="myTab">
				<li>
					<a href="<utils:url url="/collectedNews/categories/${category.alias}.html" />">未处理 </a>
				</li>
				<li class="${status=='published'?'active':''}">
					<a href="<utils:url url="/collectedNews/categories/${category.alias}/published.html" />">已发布</a>
				</li>
				<li class="${status=='ignored'?'active':''}">
					<a href="<utils:url url="/collectedNews/categories/${category.alias}/ignored.html" />">已忽略</a>
				</li>
			</ul>
			<div class="tab-content">
				<div id="newsContentList" class="tab-pane in active">
					<div class="table-responsive">
						<form action="" class="isst-table-form">

							<table class="table table-striped table-bordered table-hover">
								<thead>
									<tr>
										<th class="center"><label> <input type="checkbox" class="ace" /> <span class="lbl"></span> </label>
										</th>
										<th>ID</th>
										<th>标题</th>
										<th>发布日期</th>
										<th>采集日期</th>
									</tr>
								</thead>

								<tbody>
									<c:forEach items="${news.items}" var="item">
										<tr>
											<td class="center">
												<label> <input type="checkbox" class="ace" name="id[]" value="${item.id}" /> <span class="lbl"></span> </label>
											</td>
											<td>${item.id}</td>
											<td>
												<a href="<utils:url url="/collectedNews/${item.id}.html" returned="true"/>">
													${item.title}
												</a>
											</td>
											
											<td>
												<fmt:formatDate value="${item.postTime}" pattern="yyyy-MM-dd" />
											</td>
											
											<td>
												<fmt:formatDate value="${item.createdAt}" pattern="yyyy-MM-dd HH:mm:ss" />
											</td>
											

											<td>
												<div class="visible-md visible-lg hidden-sm hidden-xs btn-group">
													<a class="btn btn-xs btn-info" href="<utils:url url="/collectedNews/${item.id}.html" returned="true"/>" title="编辑"> 
														<i class="icon-edit bigger-120"></i> 
													</a> 
													<c:if test="${status=='ignored'}">
													<a class="btn btn-xs btn-success" href="<utils:url url="/collectedNews/categories/${category.alias}/unprocess?id[]=${item.id}" />" title="撤销忽略"> 
														<i class="icon-eye-open bigger-120"></i> 
													</a>
													</c:if>
														
													<a class="btn btn-xs btn-danger" href="<utils:url url="/collectedNews/categories/${category.alias}/delete?id[]=${item.id}" />" title="删除"> 
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
																<a href="<utils:url url="/collectedNews/${item.id}.html" returned="true"/>" class="tooltip-success" data-rel="tooltip" title="编辑"> 
																<span class="blue"> <i class="icon-edit bigger-120"></i></span> 
																</a>
															</li>
															<c:if test="${status=='ignored'}">
															<li>
																<a href="<utils:url url="/collectedNews/categories/${category.alias}/unprocessed?id[]=${item.id}" />" class="tooltip-error" data-rel="tooltip" title="撤销忽略"> 
																	<span class="green"><i class="icon-eye-open bigger-120"></i> </span> 
																</a>
															</li>
															</c:if>
															<li>
																<a href="<utils:url url="/collectedNews/categories/${category.alias}/delete?id[]=${item.id}" />" class="tooltip-error" data-rel="tooltip" title="删除"> 
																	<span class="red"> <i class="icon-trash bigger-120"></i> </span> 
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
								<div class="col-xs-12 col-sm-4 isst-table-form-actions">
									<div class="btn-group dropup">
										<button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown">
											批量操作 <i class="icon-angle-up icon-on-right"></i>
										</button>
										<ul class="dropdown-menu">
											<c:if test="${status=='ignored'}">
												<li><a href="<utils:url url="/collectedNews/categories/${category.alias}/unprocess" />">撤销忽略</a>
												</li>
											</c:if>
											<li><a href="<utils:url url="/collectedNews/categories/${category.alias}/delete" />">批量删除</a>
											</li>
										</ul>
									</div>
								</div>

								<div class="col-xs-12 col-sm-8">
									<!-- pager -->
									<div id="pager" class="pull-right">
										<pagination:paging page="${news.page}" total="${news.total}" size="${news.pageSize}" />
									</div>
									<!-- end pager -->
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