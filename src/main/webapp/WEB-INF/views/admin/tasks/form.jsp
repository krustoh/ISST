<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="pagination" uri="/pagination"%>
<%@ taglib uri="/jsp_layout" prefix="layout"%>
<%@ taglib uri="/field" prefix="field"%>
<%@ taglib uri="/navigation" prefix="navigation"%>
<%@ taglib uri="/utils" prefix="utils"%>

<navigation:setNavigationActiveKey key="tasks"/>
<navigation:setPageTitle label="${taskForm.id>0?'编辑':'添加'}"/>

<layout:override name="content">
			<div class="col-xs-12">
				<form:form class="form-horizontal isst-form" modelAttribute="taskForm" method="POST">
					
					<fieldset>
						<field:wrapper class="form-group" path="name">
							<label for="name" class="col-xs-12 col-sm-3 col-md-3 control-label no-padding-right">
							任务名称
							</label>
							<div class="col-xs-12 col-sm-5">
								<form:input class="width-100" path="name" />
							</div>
							<form:errors cssClass="help-block col-xs-12 col-sm-reset inline" path="name">
							</form:errors>
						</field:wrapper>
						
						<field:wrapper class="form-group " path="startTime">
							<label for="startTime" class="col-xs-12 col-sm-3 col-md-3 control-label no-padding-right">
							开始时间
							</label>
							<div class="col-xs-12 col-sm-5 input-group">
								<div class="input-group">
								<form:input class="form-control date-picker"  path="startTime" type="text" data-date-format="yyyy-mm-dd" />
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
						
					<div class="form-group">
						<label class="col-sm-3 control-label no-padding-right" for="type">任务类型</label>
						<div class="col-sm-9">
							<form:select  id="type" path="type">
								<form:option value="0" label="去向调查"/>
								<form:option value="-1" label="其他"/>
							</form:select>
						</div>
					</div>
						
					<div class="form-group">
						<label class="col-sm-3 control-label no-padding-right" for="status">状态</label>
						<div class="col-sm-9">
							<form:select  id="status" path="status">
								<form:option value="0" label="隐藏"/>
								<form:option value="1" label="发布"/>
							</form:select>
						</div>
					</div>

				<field:wrapper class="form-group" path="description">
					<label for="description" class="col-xs-12 col-sm-3 col-md-3 control-label no-padding-right"> 任务描述 </label>
					<div class="col-xs-12 col-sm-5">
						<form:textarea path="description" id="description" class="form-control"></form:textarea>
					</div>
					<form:errors cssClass="help-block col-xs-12 col-sm-reset inline" path="description">
					</form:errors>
				</field:wrapper>

						<div class="clearfix form-actions">
							<div class="col-md-offset-3 col-md-9">
								<button class="btn btn-info" type="submit">
									<i class="icon-ok bigger-110"></i> 提交
								</button>

								&nbsp; &nbsp; &nbsp;
								<a class="btn" href="<utils:returnUrl url="/tasks.html" />">
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