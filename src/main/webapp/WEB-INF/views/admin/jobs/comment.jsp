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
				<%@ include file="../blocks/message.jsp"%>
				<div class="table-responsive">
					<form action="" class="isst-table-form">
						<div class="widget-box transparent" id="recent-box">
							<div class="widget-header">
								<h4 class="lighter smaller">
									<i class="icon-comment blue"></i>
										${jobs.title}
								</h4>
							</div>
							
							<div class="widget-body">
								<div class="widget-main padding-4">
									<div class="tab-content padding-8 overflow-visible">
										<div id="comment-tab" class="tab-pane">
											<div class="comments">
												<div class="itemdiv commentdiv">
													<div class="user">
														<img alt="Jennifer's Avatar" src="assets/avatars/avatar1.png" />
													</div>

													<div class="body">
														<div class="name">
															<a href="#">Jennifer</a>
														</div>

														<div class="time">
															<i class="icon-time"></i>
															<span class="blue">15 min</span>
														</div>

														<div class="text">
															<i class="icon-quote-left"></i>
																Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque commodo massa sed ipsum porttitor facilisis &hellip;
															</div>
														</div>

														<div class="tools">
															<div class="action-buttons bigger-125">
																<a href="#">
																	<i class="icon-pencil blue"></i>
																</a>

																<a href="#">
																	<i class="icon-trash red"></i>
																</a>
															</div>
														</div>
													</div>
											</div>
										</div>
									</div>
								</div>
							</div>
							
						</div>
				
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