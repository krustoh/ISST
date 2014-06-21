<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="pagination" uri="/pagination"%>
<%@ taglib uri="/jsp_layout" prefix="layout"%>
<%@ taglib uri="/field" prefix="field"%>
<%@ taglib uri="/navigation" prefix="navigation"%>
<%@ taglib uri="/utils" prefix="utils"%>

<navigation:setNavigationActiveKey key="city_activity"/>
<navigation:setPageTitle label="${cityUserActivityForm.id>0?'编辑':'添加'}"/>

<layout:override name="content">
			<div class="col-xs-12">
				<form:form class="form-horizontal isst-form" modelAttribute="cityUserActivityForm" method="POST">
					<fieldset>
						<field:wrapper class="form-group" path="title">
							<label for="title" class="col-xs-12 col-sm-3 col-md-3 control-label no-padding-right">
							标题
							</label>
							<div class="col-xs-12 col-sm-5">
								<form:input class="width-100" path="title" />
							</div>
							<form:errors cssClass="help-block col-xs-12 col-sm-reset inline" path="title">
							</form:errors>
						</field:wrapper>
						
						<field:wrapper class="form-group" path="poster">
							<label for="poster" class="col-xs-12 col-sm-3 col-md-3 control-label no-padding-right">
							发布者
							</label>
							<div class="col-xs-12 col-sm-5">
								<form:input class="width-100" path="poster" placeholder="学号" />
							</div>
							<form:errors cssClass="help-block col-xs-12 col-sm-reset inline" path="poster">
							</form:errors>
						</field:wrapper>
						
						<field:wrapper class="form-group" path="cityId">
							<label for="cityId" class="col-xs-12 col-sm-3 col-md-3 control-label no-padding-right">
							城市
							</label>
							<div class="col-xs-12 col-sm-5">
								<form:select  path="cityId">
									<form:options items="${cities}" itemValue="id" itemLabel="name"/>
								</form:select>
							</div>
							<form:errors cssClass="help-block col-xs-12 col-sm-reset inline" path="cityId">
							</form:errors>
						</field:wrapper>
						
						<field:wrapper class="form-group" path="location">
							<label for="location" class="col-xs-12 col-sm-3 col-md-3 control-label no-padding-right">
							地点
							</label>
							<div class="col-xs-12 col-sm-5">
								<form:input class="width-100" path="location" />
							</div>
							<form:errors cssClass="help-block col-xs-12 col-sm-reset inline" path="location">
							</form:errors>
						</field:wrapper>
						
						<field:wrapper class="form-group " path="startTime">
							<label for="startTime" class="col-xs-12 col-sm-3 col-md-3 control-label no-padding-right">
							开始时间
							</label>
							<div class="col-xs-12 col-sm-5 input-group">
								<div class="input-group">
								<form:input class="form-control date-picker" path="startTime" type="text" data-date-format="yyyy-mm-dd" />
								<span class="input-group-addon">
									<i class="icon-calendar bigger-110"></i>
								</span>
								</div>
							</div>
							<form:errors cssClass="help-block col-xs-12 col-sm-reset inline" path="startTime">
							</form:errors>
						</field:wrapper>
						
						<field:wrapper class="form-group " path="expireTime">
							<label for="expireTime" class="col-xs-12 col-sm-3 col-md-3 control-label no-padding-right">
							截止时间
							</label>
							<div class="col-xs-12 col-sm-5 input-group">
								<div class="input-group">
								<form:input class="form-control date-picker" path="expireTime" type="text" data-date-format="yyyy-mm-dd" />
								<span class="input-group-addon">
									<i class="icon-calendar bigger-110"></i>
								</span>
								</div>
							</div>
							<form:errors cssClass="help-block col-xs-12 col-sm-reset inline" path="expireTime">
							</form:errors>
						</field:wrapper>

					<div class="space-4"></div>
					
					<div class="row">
						<div class="col-sm-12">
							<h4 class="header green clearfix">
								内容
							</h4>
							<form:textarea path="content" id="content"></form:textarea>
						</div>
					</div>

						<div class="clearfix form-actions">
							<div class="col-md-offset-3 col-md-9">
								<button class="btn btn-info" type="submit">
									<i class="icon-ok bigger-110"></i> 提交
								</button>

								&nbsp; &nbsp; &nbsp;
								<a class="btn" href="<utils:returnUrl url="/users/activities" />">
									<i class="icon-undo bigger-110"></i> 返回
								</a>
							</div>
						</div>
					</fieldset>
				</form:form>
			</div>
					<!-- /.col -->
	
</layout:override>

<layout:override name="javascripts">
<script type="text/javascript">
jQuery(function($){
	$("#content").isst_wysiwyg();
	
	$('.date-picker').datepicker({autoclose:true}).next().on(ace.click_event, function(){
		$(this).prev().focus();
	});
	
});
</script>
</layout:override>



<%@ include file="../layouts/main.jsp"%>