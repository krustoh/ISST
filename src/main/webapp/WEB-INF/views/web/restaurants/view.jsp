<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="pagination" uri="/pagination"%>
<%@ taglib uri="/jsp_layout" prefix="layout"%>
<%@ taglib uri="/field" prefix="field"%>
<%@ taglib uri="/navigation" prefix="navigation"%>
<%@ taglib prefix="utils" uri="/utils"%>

<navigation:setNavigationActiveKey key="restaurants"/>

<layout:override name="content">
<div class="col-xs-12">
	<div class="widget-box transparent center">
		<div class="widget-header">
			<h3 class="lighter">${restaurant.name}</h4>
		</div>
		
		<div class="widget-body">
		<div class="col-sm-12" >
			<div class="col-xs-12 col-sm-3 col-md-3 control-label no-padding-right"> 订餐电话 </div>
			<div class="col-xs-12 col-sm-5">
				${restaurant.hotline}
			</div>
		</div>
		
		<div class="col-sm-12" >
			<div class="col-xs-12 col-sm-3 col-md-3 control-label no-padding-right"> 营业时间 </div>
			<div class="col-xs-12 col-sm-5">
				${restaurant.businessHours}
			</div>
		</div>
		
		<div class="col-sm-12" >
			<div class="col-xs-12 col-sm-3 col-md-3 control-label no-padding-right">地址</div>
			<div class="col-xs-12 col-sm-5">
				${restaurant.address}
			</div>
		</div>
		
		<div class="col-sm-12" >
			<div class="col-xs-12 col-sm-3 col-md-3 control-label no-padding-right">餐馆描述</div>
			<div class="col-xs-12 col-sm-5">
				${restaurant.description}
			</div>
		</div>
		</div>
		
	</div>

	<div class="clearfix form-actions">
		<div class="col-md-offset-5 col-md-9">
			<a class="btn" href="<utils:returnUrl url="/restaurants.html" />">
				<i class="icon-undo bigger-110"></i> 返回
			</a>
		</div>
	</div>		
</div>
</layout:override>

<%@ include file="../layouts/main.jsp"%>