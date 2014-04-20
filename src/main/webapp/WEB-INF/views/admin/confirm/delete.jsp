<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
<%@ taglib prefix="pagination" uri="/pagination"%>
<%@ taglib uri="/jsp_layout" prefix="layout"%>
<%@ taglib uri="/navigation" prefix="navigation"%>
<%@ taglib prefix="utils" uri="/utils"%>

<navigation:setNavigationActiveKey key="${navigationActiveKey}"/>
<navigation:setPageTitle label="删除"/>

<layout:override name="content">
			<div class="col-xs-12">
					<form method="POST">
						<input type="hidden" value="1" name="confirm" />
						<c:forEach items="${entities}" var="entity">
							<input type="hidden" value="${entity.id}" name="id[]" />
						</c:forEach>
						<div class="alert alert-warning">
							<button type="button" class="close" data-dismiss="alert">
								<i class="icon-remove"></i>
							</button>
							<strong>该操作不可逆，请再次确认！</strong>
							<br />
							<br />
							<ul>
							<c:forEach items="${entities}" var="entity">
								<li>${entity}</li>
							</c:forEach>
						</ul>
						</div>
						<div class="clearfix form-actions">
							<div class="col-md-offset-3 col-md-9">
								<button class="btn btn-info" type="submit">
									<i class="icon-ok bigger-110"></i> 确认
								</button>

								&nbsp; &nbsp; &nbsp;
								<a class="btn" href="${cancelUrl}">
									<i class="icon-undo bigger-110"></i> 取消
								</a>
							</div>
						</div>
					</form>
			</div>

</layout:override>


<%@ include file="../layouts/main.jsp"%>