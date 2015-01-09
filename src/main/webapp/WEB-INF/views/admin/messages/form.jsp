<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="pagination" uri="/pagination"%>
<%@ taglib uri="/jsp_layout" prefix="layout"%>
<%@ taglib uri="/field" prefix="field"%>
<%@ taglib uri="/navigation" prefix="navigation"%>
<%@ taglib uri="/utils" prefix="utils"%>

<navigation:setNavigationActiveKey key="messages" />
<navigation:setPageTitle label="${messageForm.id>0?'编辑':'添加'}" />

<layout:override name="content">
	<div class="col-xs-12">
		<form:form class="form-horizontal isst-form"
			modelAttribute="messageForm" method="POST">

			<fieldset>
				<field:wrapper class="form-group" path="title">
					<label for="title"
						class="col-xs-12 col-sm-3 col-md-3 control-label no-padding-right">
						标题 </label>
					<div class="col-xs-12 col-sm-5">
						<form:input class="width-100" path="title" />
					</div>
					<form:errors cssClass="help-block col-xs-12 col-sm-reset inline"
						path="title">
					</form:errors>
				</field:wrapper>

				<div class="icheckbox form-group">
					<label
						class="col-xs-12 col-sm-3 col-md-3 control-label no-padding-right">设备范围</label>
					<label class="checkbox-inline" style="padding-left: 40px">
						<form:checkbox path="" value="" label="Android"></form:checkbox>
					</label> <label class="checkbox-inline"> <form:checkbox path=""
							value="" label="IOS"></form:checkbox>
					</label>
				</div>

				<div class="form-group">
					<label
						class="col-xs-12 col-sm-3 col-md-3 control-label no-padding-right">用户范围</label>
					<div class="col-xs-12 col-sm-5">
						<form:select id="userSelect" path="">
							<form:option value="0" label="所有用户" />
							<form:option value="1" label="广播组" />
							<form:option value="2" label="特定用户" />
						</form:select>
						<div id="groupUser" style="display: none">
							<div class="space-4"></div>
							<button onclick="return false">选择tag</button>
						</div>
						<div id="specialUser" style="display: none; padding-left: 0">
							<div class="space-4"></div>
							<form:select id="specialUserSelect" path="">
								<form:option value="3" label="少量用户" />
								<form:option value="4" label="批量用户" />
							</form:select>
							<div id="lessUser" style="display: none">
								<div class="space-4"></div>
								<form:input class="width-100" path="" />
							</div>
							<div id="moreUser" style="display: none">
								<div class="space-4"></div>
								<form:input type="file" path="" />
							</div>
						</div>
					</div>
				</div>

				<div class="row">
					<div class="col-xs-12 col-sm-12 col-sm-12">
						<h4 class="header green clearfix">内容</h4>
						<form:textarea path="content" id="content" class="form-control"></form:textarea>
					</div>
				</div>

				<div class="space-4"></div>

				<div class="clearfix form-actions">
					<div class="col-md-offset-3 col-md-9">
						<button class="btn btn-info" type="submit">
							<i class="icon-ok bigger-110"></i> 提交
						</button>

						&nbsp; &nbsp; &nbsp; <a class="btn"
							href="<utils:returnUrl url="/messages.html" />"> <i
							class="icon-undo bigger-110"></i> 返回
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
		jQuery(function($) {

			$('.date-picker').datepicker({
				autoclose : true
			}).next().on(ace.click_event, function() {
				$(this).prev().focus();
			});
		});

		$(document).ready(function() {
			$("#userSelect").change(function(e) {
				$("#groupUser").hide();
				$("#specialUser").hide();
				switch (e.target.value) {
				case '1':
					$("#groupUser").show();
					break;
				case '2':
					$("#specialUser").show();
					$("#lessUser").show();
					break;
				}
			});
			$("#specialUserSelect").change(function(e) {
				$("#lessUser").hide();
				$("#moreUser").hide();
				if(e.target.value=='3')
					$("#lessUser").show();
				else
					$("#moreUser").show();
			});
		});
	</script>
</layout:override>



<%@ include file="../layouts/main.jsp"%>