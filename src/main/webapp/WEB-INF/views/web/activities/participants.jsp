<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="pagination" uri="/pagination"%>
<%@ taglib uri="/jsp_layout" prefix="layout"%>
<%@ taglib uri="/navigation" prefix="navigation"%>
<%@ taglib uri="/utils" prefix="utils"%>

<navigation:setNavigationActiveKey key="city_activity" />


<layout:override name="content">
	<div class="col-xs-12">
		<div class="table-responsive">			
			<c:choose>
			<c:when test="${participants.total>0}">
			<form action="" class="isst-table-form">
				<table class="table table-striped table-bordered table-hover">
					<thead>
						<tr>
							<th>学号</th>
							<th>姓名</th>
							<th>性别</th>
							<th>年级</th>
							<th>专业</th>
							<th>QQ</th>
							<th>联系电话</th>
							<th>邮箱</th>
							
						</tr>
					</thead>

					<tbody>
						<c:forEach items="${participants.items}" var="participant">
							<tr>							
								<td>
									<a href="<utils:url url="/alumni/${participant.id}.html" />">
										${participant.username}
									</a>
								</td>
								<td>
									<a href="<utils:url url="/alumni/${participant.id}.html" />">
										${participant.name}
									</a>
								</td>
								<td>${participant.gender==1?"男":"女"}</td>
								<td>${participant.grade}</td>
								<td>${participant.major}</td>
								<td><c:if test="${participant.privateQQ == false}">${participant.qq}</c:if></td>
								<td><c:if test="${participant.privatePhone == false}">${participant.phone}</c:if></td>
								<td><c:if test="${participant.privateEmail ==false}">${participant.email}</c:if></td>								
							</tr>
						</c:forEach>
					</tbody>
				</table>

				<div class="row">
					<div class="col-xs-12 col-sm-8">
						<!-- pager -->
						<div id="pager" class="pull-right">
							<pagination:paging page="${participants.page}" total="${participants.total}" size="${participants.pageSize}" />
						</div>
						<!-- end pager -->
					</div>
				</div>
			</form>
		</c:when>
		
		<c:otherwise>
			<div class="alert alert-warning">
				<button type="button" class="close" data-dismiss="alert">
					<i class="icon-remove"></i>
				</button>
				<strong>没有记录！</strong>
				<br />
				<br />
				</div>
		</c:otherwise>
		</c:choose>
		</div>
	</div>
</layout:override>

<%@ include file="../layouts/main.jsp"%>