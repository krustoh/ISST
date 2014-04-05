<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="pagination" uri="/pagination"%>
<%@ taglib uri="/jsp_layout" prefix="layout"%>

<layout:override name="content">
	<div class="page-content">
		<div class="page-header">
			<div class="pull-right" style="margin-right: 10%;"></div>

			<h1 style="margin-top: 5px;">
				软院生活 <small><i class="icon-double-angle-right"></i> 快讯</small>
			</h1>
		</div>
		<div class="row">
			<div class="col-xs-12">
				<%@ include file="../blocks/message.jsp"%>
				<div class="table-responsive">
					<table class="table table-striped table-hover">
						<tbody>
							<tr>
								<th class="center">标题</th>
								<th class="center">时间</th>
							</tr>
							<c:forEach items="${archives.items }" var="archive">
							<tr>
								<td><a href="web/archives/content.html">${archive.title}</a></td>
								<td>${archive.updatedAt}</td>
							</tr>
							</c:forEach>
						</tbody>
					</table>
					<!-- pager -->
							<div id="pager" class="pull-right" style="margin-right: 100px;">
								<pagination:paging page="${archives.page}" total="${archives.total}" size="${archives.pageSize}"/>
							</div>
							<!-- end pager -->
				</div>
			</div>
			<!-- /.col -->
		</div>
		<!-- /.row -->
	</div>
	<!-- /.page-content -->
	</div>
	<!-- /.main-content -->
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