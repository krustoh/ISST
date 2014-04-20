<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="pagination" uri="/pagination"%>
<%@ taglib uri="/jsp_layout" prefix="layout"%>
<%@ taglib uri="/field" prefix="field"%>
<%@ taglib uri="/navigation" prefix="navigation"%>
<%@ taglib prefix="utils" uri="/utils"%>

<layout:override name="content">
	<div class="col-xs-12">
    	<form:form class="form-horizontal isst-form" modelAttribute="administratorPasswordForm" method="POST">
			<fieldset>
				<field:wrapper class="form-group" path="oldPassword">
					<label for="oldPassword" class="col-xs-12 col-sm-3 col-md-3 control-label no-padding-right">原密码 </label>
					
					<div class="col-xs-12 col-sm-5">
						<form:input id="oldPassword" class="form-control" path="oldPassword" />
					</div>
					<form:errors cssClass="help-block col-xs-12 col-sm-reset inline" path="oldPassword">
					</form:errors>
				</field:wrapper>

				<field:wrapper class="form-group" path="newPassword">
					<label for="newPassword" class="col-xs-12 col-sm-3 col-md-3 control-label no-padding-right">新密码 </label>
					
					<div class="col-xs-12 col-sm-5">
						<form:input id="newPassword" class="form-control" path="newPassword" />
					</div>
					
					<form:errors cssClass="help-block col-xs-12 col-sm-reset inline" path="newPassword">
					</form:errors>
				</field:wrapper>
				
				<field:wrapper class="form-group" path="confirmPassword">
					<label for="confirmPassword" class="col-xs-12 col-sm-3 col-md-3 control-label no-padding-right">确认密码</label>
					<div class="col-xs-12 col-sm-5">
						<form:input id="confirmPassword" class="form-control" path="confirmPassword" />
					</div>
					<form:errors cssClass="help-block col-xs-12 col-sm-reset inline" path="confirmPassword">
					</form:errors>
				</field:wrapper>
                
				<div class="clearfix form-actions">
					<div class="col-md-offset-3 col-md-9">
						<button class="btn btn-info" type="submit">
							<i class="icon-ok bigger-110"></i> 提交
						</button>
						&nbsp; &nbsp; &nbsp; 
						<a class="btn" href="<utils:returnUrl url="/login.html" />"> 
							<i class="icon-undo bigger-110"></i> 返回 
						</a>
					</div>
				</div>
			</fieldset>
		</form:form>
	</div>
</layout:override>


<%@ include file="../layouts/main.jsp"%>