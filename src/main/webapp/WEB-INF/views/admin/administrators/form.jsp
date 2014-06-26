<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="pagination" uri="/pagination"%>
<%@ taglib uri="/jsp_layout" prefix="layout"%>
<%@ taglib uri="/field" prefix="field"%>
<%@ taglib uri="/navigation" prefix="navigation"%>
<%@ taglib prefix="utils" uri="/utils"%>

<navigation:setNavigationActiveKey key="administrators"/>
<navigation:setPageTitle label="${administratorForm.id>0?'编辑':'添加'}"/>

<layout:override name="content">
	<div class="col-xs-12">
		<form:form class="form-horizontal isst-form" modelAttribute="administratorForm">
			<fieldset>
				<field:wrapper class="form-group" path="username">
					<label for="username" class="col-xs-12 col-sm-3 col-md-3 control-label no-padding-right"> 用户名 </label>
					<div class="col-xs-12 col-sm-5">
						<form:input id="username" class="form-control" path="username" />
					</div>
					<form:errors cssClass="help-block col-xs-12 col-sm-reset inline" path="username">
					</form:errors>
				</field:wrapper>

				<field:wrapper class="form-group" path="password">
					<label for="password" class="col-xs-12 col-sm-3 col-md-3 control-label no-padding-right"> 密码 </label>
					<div class="col-xs-12 col-sm-5">
						<form:input id="password" class="form-control" path="password"  placeholder="${administratorForm.id>0?'留空表示不修改密码':''}" />
					</div>
					<form:errors cssClass="help-block col-xs-12 col-sm-reset inline" path="password">
					</form:errors>
				</field:wrapper>
						
					<div class="form-group">
						<label class="col-sm-3 control-label no-padding-right" for="roles">角色</label>
						<div class="col-sm-9">
							<form:select  id="roles" path="roles">
								<form:option value="7" label="管理员"/>
								<form:option value="3" label="校友管理员"/>
								<form:option value="-1" label="超级管理员"/>
							</form:select>
						</div>
					</div>

				<div class="clearfix form-actions">
					<div class="col-md-offset-3 col-md-9">
						<button class="btn btn-info" type="submit">
							<i class="icon-ok bigger-110"></i> 提交
						</button>
						&nbsp; &nbsp; &nbsp; 
						<a class="btn" href="<utils:returnUrl url="/administrators.html" />"> 
							<i class="icon-undo bigger-110"></i> 返回 
						</a>
					</div>
				</div>
			</fieldset>
		</form:form>
	</div>
</layout:override>



<%@ include file="../layouts/main.jsp"%>