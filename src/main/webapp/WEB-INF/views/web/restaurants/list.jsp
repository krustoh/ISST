<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="pagination" uri="/pagination"%>
<%@ taglib uri="/jsp_layout" prefix="layout"%>
<%@ taglib uri="/navigation" prefix="navigation"%>
<%@ taglib prefix="utils" uri="/utils"%>

<navigation:setNavigationActiveKey key="restaurants" />

<layout:override name="content">
	<div class="col-xs-12">
		<%@ include file="../blocks/message.jsp"%>
		<div class="table-responsive">
			<form action="" class="isst-table-form">
				<table class="table table-striped table-bordered table-hover">
					<thead>
						<tr>
							<th>餐馆名称</th>
							<th>订餐电话</th>
							<th>营业时间</th>
							<th>地址</th>
							<th>点菜</th>
						</tr>
					</thead>

					<tbody>
						<c:forEach items="${restaurants.items}" var="restaurant">
							<tr>							
								<td>
									<c:if test="${not empty restaurant.picture }">
										<a href="${restaurant.picture}" data-rel="colorbox"> 
											<img class="restaurants-picture" src="${restaurant.picture}" />
										</a>
									</c:if>
									<a href="<utils:url url="/restaurants/${restaurant.id}.html" />">
										${restaurant.name}
									</a>
								</td>
								<td>${restaurant.hotline}</td>
								<td>${restaurant.businessHours}</td>
								<td>${restaurant.address}</td>
								<td>
									<a data-rel="tooltip" data-placement="bottom" title="点菜" href="<utils:url url="/restaurants/${restaurant.id}/menus.html" />">
									<span class="red">
										<i class=" icon-book bigger-120"></i>
									</span>
									</a>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>

				<div class="row">
					<div class="col-xs-12 col-sm-8">
						<!-- pager -->
						<div id="pager" class="pull-right">
							<pagination:paging page="${restaurants.page}" total="${restaurants.total}" size="${restaurants.pageSize}" />
						</div>
						<!-- end pager -->
					</div>
				</div>
			</form>
		</div>
	</div>
</layout:override>

<%@ include file="../layouts/main.jsp"%>