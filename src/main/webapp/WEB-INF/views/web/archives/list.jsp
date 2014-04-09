<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="pagination" uri="/pagination"%>
<%@ taglib uri="/jsp_layout" prefix="layout"%>
<%@ taglib uri="/navigation" prefix="navigation"%>

<navigation:setNavigationActiveKey key="archive_${category.alias}"/>

<layout:override name="page-header">
			
</layout:override>

<layout:override name="content">
	<div class="col-xs-12">
				<%@ include file="../blocks/message.jsp"%>
				<div class="table-responsive">
					<form action="" class="isst-table-form">
					
					<table class="table table-striped table-bordered table-hover">
						<thead>
							<tr>
								<th>ID</th>
								<th>标题</th>
								<th>发布日期</th>
								<th>发布者</th>	
							</tr>
						</thead>
						
						<tbody>
							<c:forEach items="${archives.items}" var="archive">
							<tr>
								<td>${archive.id}</td>
								<td>
									<a href="${baseUrl}web/archives/${archive.id}.html">${archive.title}</a>
									<c:if test="${archive.status==0}">
										<span class="label label-sm label-warning">隐藏</span>
									</c:if>  
								</td>
								<td>
								<fmt:formatDate value="${archive.updatedAt}" pattern="yyyy-MM-dd HH:mm:ss"/>
								
								</td>
								<td>${archive.userId>0?archive.user.name:"管理员"}</td>					
							</tr>
							</c:forEach>
						</tbody>	
					</table>
					
					<div class="row">
						<div class="col-sm-8 pull-right">
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
</layout:override>

<layout:override name="javascript">
		<script src="resources/admin/js/ace-elements.min.js"></script>
	<script src="resources/admin/js/ace.min.js"></script>
		<script>
			$(document).ready(function () {
				var options = {
					currentPage: 1,
					totalPages: 1,
					bootstrapMajorVersion: 3
				}
				$("#paginator").bootstrapPaginator(options);
			});
		</script>	
</layout:override>

<%@ include file="../layouts/main.jsp"%>