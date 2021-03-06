<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="pagination" uri="/pagination"%>
<%@ taglib uri="/jsp_layout" prefix="layout"%>
<%@ taglib uri="/field" prefix="field"%>
<%@ taglib uri="/navigation" prefix="navigation"%>
<%@ taglib uri="/utils" prefix="utils"%>

<navigation:setNavigationActiveKey key="tasks"/>
<navigation:setPageTitle label="完成任务"/>

<layout:override name="content">
			<div class="col-xs-12">
				<form:form class="form-horizontal isst-form" modelAttribute="taskSurveyForm" method="POST">
					
					<fieldset>
						
					<div class="form-group">
						<label class="col-sm-3 control-label no-padding-right" for="optionId">选项</label>
						<div class="col-sm-9">
							<form:select  id="optionId" path="optionId">
								<form:options items="${options}" itemValue="id" itemLabel="label"/>
								<form:option value="0" label="其他" />
							</form:select>
						</div>
					</div>
						
						<field:wrapper class="form-group optionOtherDiv" path="optionOther" style="display:none;">
							<label for="optionOther" class="col-xs-12 col-sm-3 col-md-3 control-label no-padding-right">
							其他
							</label>
							<div class="col-xs-12 col-sm-5 input-group">
								<div class="input-group">
								<form:input class="form-control"  path="optionOther" type="text" />
								</div>
							</div>
							<form:errors cssClass="help-block col-xs-12 col-sm-reset inline" path="optionOther">
							</form:errors>
						</field:wrapper>
						
						<field:wrapper class="form-group " path="remarks">
							<label for="remarks" class="col-xs-12 col-sm-3 col-md-3 control-label no-padding-right">
						    说明
							</label>
							<div class="col-xs-12 col-sm-5 input-group">
								<div class="input-group">
								<form:textarea path="remarks" id="remarks" class="form-control"></form:textarea>
								</div>
							</div>
							<form:errors cssClass="help-block col-xs-12 col-sm-reset inline" path="remarks">
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
	
	var $optionIdSelect = $("#optionId");
	var $optionOther = $("#optionOther");
	var $optionOtherDiv = $(".optionOtherDiv");
	if ($optionIdSelect.val() == 0) {
		$optionOtherDiv.show();
	}
	$optionIdSelect.change(function() {
		if ($(this).val() == 0) {
		    $optionOtherDiv.show();
	    } else {
	    	$optionOtherDiv.hide();
	    }
	});
});
</script>
</layout:override>


<%@ include file="../layouts/main.jsp"%>