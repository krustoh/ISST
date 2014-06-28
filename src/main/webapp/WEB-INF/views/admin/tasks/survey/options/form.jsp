<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="pagination" uri="/pagination"%>
<%@ taglib uri="/jsp_layout" prefix="layout"%>
<%@ taglib uri="/field" prefix="field"%>
<%@ taglib uri="/navigation" prefix="navigation"%>
<%@ taglib uri="/utils" prefix="utils"%>

<navigation:setNavigationActiveKey key="tasks"/>
<navigation:setPageTitle label="${taskSurveyOptionForm.id>0?'编辑':'添加'}选项"/>

<layout:override name="content">
			<div class="col-xs-12">
				<form:form class="form-horizontal isst-form" modelAttribute="taskSurveyOptionForm" method="POST">
					
					<fieldset>
						<field:wrapper class="form-group" path="label">
							<label for="label" class="col-xs-12 col-sm-3 col-md-3 control-label no-padding-right">
							选项
							</label>
							<div class="col-xs-12 col-sm-5">
								<form:input class="width-100" path="label" />
							</div>
							<form:errors cssClass="help-block col-xs-12 col-sm-reset inline" path="label">
							</form:errors>
						</field:wrapper>

						<div class="clearfix form-actions">
							<div class="col-md-offset-3 col-md-9">
								<button class="btn btn-info" type="submit">
									<i class="icon-ok bigger-110"></i> 提交
								</button>

								&nbsp; &nbsp; &nbsp;
								<a class="btn" href="<utils:returnUrl url="/tasks/${task.id}/surveys/options.html" />">
									<i class="icon-undo bigger-110"></i> 返回
								</a>
							</div>
						</div>
					</fieldset>
				</form:form>
			</div>
					<!-- /.col -->
	
</layout:override>




<%@ include file="../../../layouts/main.jsp"%>