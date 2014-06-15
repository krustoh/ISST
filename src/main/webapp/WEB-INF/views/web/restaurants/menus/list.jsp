<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="pagination" uri="/pagination"%>
<%@ taglib uri="/jsp_layout" prefix="layout"%>
<%@ taglib uri="/navigation" prefix="navigation"%>
<%@ taglib prefix="utils" uri="/utils"%>

<navigation:setNavigationActiveKey key="restaurants"/>
<navigation:setPageTitle label="菜单列表"/>

<layout:override name="content">
<div class="col-xs-12">
			<%@ include file="../../blocks/message.jsp"%>
			<div class="table-responsive">
				<form action="" class="isst-table-form">						
					<table class="table table-striped table-bordered table-hover">
						<thead>
							<tr>
								<th>菜名</th>
								<th>价格</th>
								<th>描述</th>
							</tr>
						</thead>
						
						<tbody>
							<c:forEach items="${restaurantMenus.items}" var="restaurantMenu">
							<tr>
								<td>
									<c:if test="${not empty restaurantMenu.picture}">
										<a href="${restaurantMenu.picture}" data-rel="colorbox"> 
											<img class="restaurants-picture" src="${restaurantMenu.picture}" />
										</a>
									</c:if>
									${restaurantMenu.name}	
								</td>
								<td>${restaurantMenu.price}</td>
								<td>${restaurantMenu.description}</td>
							</tr>
							</c:forEach>
						</tbody>	
					</table>
					
					<div class="row">
						
						<div class="col-xs-12 col-sm-8">
							<!-- pager -->
							<div id="pager" class="pull-right">
								<pagination:paging page="${restaurantMenus.page}" total="${restaurantMenus.total}" size="${restaurantMenus.pageSize}" />
							</div>
							<!-- end pager -->
						</div>
					</div>
					</form>
				</div>
			</div>
</layout:override>

<%@ include file="../../layouts/main.jsp"%>