<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="pagination" uri="/pagination"%>
<%@ taglib uri="/jsp_layout" prefix="layout"%>
<%@ taglib uri="/navigation" prefix="navigation"%>

<navigation:setNavigationActiveKey key="job_${category.alias}"/>
<navigation:setPageTitle label="评论"/>

<layout:override name="content">
<div class="col-xs-12">
		<div class="table-responsive">
			<form action="" class="isst-table-form">
				<div class="col-xs-12 col-sm-12">	
						<div class="widget-box transparent" id="recent-box">
							<div class="widget-header">
								<h4 class="lighter smaller">
									<i class="icon-comments-alt bigger-150"></i>
										${job.title}
								</h4>
							</div>
						
							<div class="widget-body col-sm-12">
								<div class="widget-main padding-4">
									<div class="tab-content padding-8 overflow-visible">
										<div id="comment-tab" class="tab-pane active">
											<div class="comments">
											<c:forEach items="${comments.items}" var="comment">
												<div class="itemdiv commentdiv">
													<div class="user">
														<label> <input type="checkbox" class="ace" name="id[]" value="${comment.id}" /> <span class="lbl"></span> </label>
													</div>

													<div class="body">
														<div class="name">
															<a href="#">${comment.user.name}</a>
														</div>

														<div class="time">
															<i class="icon-time"></i>
															<span class="blue">
																<fmt:formatDate value="${comment.createdAt}" pattern="yyyy-MM-dd HH:mm:ss"/>
															</span>
														</div>

														<div class="text">
															<i class="icon-quote-left"></i>
																${comment.content} &hellip;
															</div>
														</div>

														<div class="tools">
															<div class="action-buttons bigger-125">
																<a href="${baseUrl}jobs/${job.id}/comments/delete?id[]=${comment.id}">
																	<i class="icon-trash red"></i>
																</a>
															</div>
														</div>
													</div>
												</c:forEach>
											</div>
										</div>
										<div class="hr hr8"></div>
									</div>
								</div>
							</div>
							
						</div>
					</div>
				
					<div class="row">
						<div class="col-sm-4 isst-table-form-actions">
							<a href="${baseUrl}jobs/${job.id}/comments/delete" class="btn btn-primary">批量删除</a>
						</div>
						
						<div class="col-sm-8">
							<!-- pager -->
							<div id="pager" class="pull-right" style="margin-right: 100px;">
								<pagination:paging page="${comments.page}" total="${comments.total}" size="${comments.pageSize}" />
							</div>
							<!-- end pager -->
						</div>
					</div>
			</form>
		</div>
	</div>
</layout:override>

<%@ include file="../../layouts/main.jsp"%>