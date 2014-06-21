<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="pagination" uri="/pagination"%>
<%@ taglib uri="/jsp_layout" prefix="layout"%>
<%@ taglib uri="/navigation" prefix="navigation"%>
<%@ taglib uri="/utils" prefix="utils"%>

<navigation:setNavigationActiveKey key="job_${category.alias}"/>

<layout:override name="content">
<div class="col-xs-12">
	<div class="widget-box transparent center">
		<div class="widget-header">
			<h3 class="lighter">${job.title}</h4>
			<div>
				发布时间：<fmt:formatDate value="${job.updatedAt}" pattern="yyyy-MM-dd"/>
			</div>
		</div>

		<div class="widget-body">
			<div class="widget-main padding-6 no-padding-left no-padding-right">
				${job.content}
			</div>
		</div>
		<div class="hr hr8"></div>
	</div>
		
		<c:if test="${category.alias=='recommend'}">
		<div class="table-responsive">
			<form action="" class="isst-table-form">
			<c:choose>
				<c:when test="${comments.total>0}">
				<div class="col-xs-12 col-sm-12">	
						<div class="widget-box transparent">
							<div class="widget-header ">
								<h4 class="lighter smaller">
									<i class="icon-comments-alt bigger-150"></i>
										相关评论
								</h4>
								<div class="widget-toolbar no-border">
									<h5 class="lighter smaller">
										<a href="<utils:url url="/jobs/${job.id}/comments.html" returned="true"/>">
											更多>>
										</a>
									</h5>
								</div>
							</div>
						
							<div class="widget-body col-sm-12">
								<div class="widget-main padding-4">
									<div class="tab-content padding-8 overflow-visible">
										<div id="comment-tab" class="tab-pane active">
											<div class="comments">
											
											<c:forEach items="${comments.items}" var="comment" begin="0" end="3" >
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
										
								<div >
									<h5 class="lighter smaller">
										<a href="<utils:url url="/jobs/${job.id}/comments.html" returned="true"/>">
											参与评论>>
										</a>
									</h5>
								</div>
									</div>
								</div>
							</div>
							
						</div>
					</div>
				</c:when>
				
				<c:otherwise>
				<div class="col-xs-12 col-sm-12">	
					<div class="widget-box transparent" id="recent-box">
						<div class="widget-header">
							<h4 class="lighter smaller">
								<i class="icon-comments-alt bigger-150"></i>
									相关评论
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
					</div>
				</div>		
				</c:otherwise>
			</c:choose>
			</form>
		</div>
		</c:if>
	<div>
		<div class="col-md-offset-5 col-md-9">
			<a class="btn" href="<utils:returnUrl url="/jobs/categories/${category.alias}.html" />">
				<i class="icon-undo bigger-110"></i> 返回
			</a>
		</div>
	</div>		
</div>
</layout:override>

<%@ include file="../layouts/main.jsp"%>