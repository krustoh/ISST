<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="pagination" uri="/pagination"%>
<%@ taglib uri="/jsp_layout" prefix="layout"%>
<%@ taglib uri="/navigation" prefix="navigation"%>
<%@ taglib prefix="utils" uri="/utils"%>

<navigation:setNavigationActiveKey key="job_${category.alias}"/>
<navigation:setPageTitle label="评论"/>

<layout:override name="content">
<div class="col-xs-12">
		<div class="table-responsive">
			<form action="" class="isst-table-form">
			<c:choose>
				<c:when test="${comments.total>0}">
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
													<div class="body">
														<div class="name">
															<a href="<utils:url url="/alumni/${comment.user.id}/view.html" returned="true"/>">${comment.user.name}</a>
														</div>

														<div class="time">
															<i class="icon-time"></i>
															<span class="blue">
																<fmt:formatDate value="${comment.createdAt}" pattern="yyyy-MM-dd"/>
															</span>
														</div>

														<div class="text">
															<i class="icon-quote-left"></i>
																${comment.content} &hellip;
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
						<div class="col-xs-12 col-sm-11">
							<!-- pager -->
							<div id="pager" class="pull-right">
								<pagination:paging page="${comments.page}" total="${comments.total}" size="${comments.pageSize}" />
							</div>
							<!-- end pager -->
						</div>
					</div>
						<div class="clearfix form-actions">
							<div class="col-md-offset-5 col-md-9">
								<a class="btn btn-info" href="<utils:url url="/jobs/categories/${category.alias}.html" />">
									<i class="icon-ok bigger-110"></i> 返回
								</a>
							</div>
						</div>
					
				</c:when>
				
				<c:otherwise>
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
											<div class="alert alert-warning">
												<button type="button" class="close" data-dismiss="alert">
												<i class="icon-remove"></i>
												</button>
												<strong>暂时没有评论！</strong>
												<br />
												<br />
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="clearfix form-actions">
							<div class="col-md-offset-3 col-md-9">
								<a class="btn btn-info" href="<utils:url url="/jobs/categories/${category.alias}.html" />">
									<i class="icon-ok bigger-110"></i> 返回
								</a>
							</div>
						</div>
					</div>
				</div>		
				</c:otherwise>
			</c:choose>
			
			</form>
		</div>
	</div>
</layout:override>


<%@ include file="../../layouts/main.jsp"%>