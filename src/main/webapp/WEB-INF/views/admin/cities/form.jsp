<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="pagination" uri="/pagination"%>
<%@ taglib uri="/jsp_layout" prefix="layout"%>
<%@ taglib uri="/field" prefix="field"%>
<%@ taglib uri="/navigation" prefix="navigation"%>
<%@ taglib uri="/utils" prefix="utils"%>

<c:set var="navigationActiveKey" value="cities" scope="request"></c:set>
<navigation:setPageTitle label="${cityForm.id>0?'编辑':'添加'}"/>

<layout:override name="content">
			<div class="col-xs-12">
				<form:form class="form-horizontal isst-form" modelAttribute="jobForm" method="POST">
					<form:hidden path="categoryId"/>
					<fieldset>
						<field:wrapper class="form-group" path="name">
							<label for="inputError" class="col-xs-12 col-sm-3 col-md-3 control-label no-padding-right">
							城市
							</label>
							<div class="col-xs-12 col-sm-5">
								<form:input id="inputError" class="width-100" path="name" />
							</div>
							<form:errors cssClass="help-block col-xs-12 col-sm-reset inline" path="name">
							</form:errors>
						</field:wrapper>
						
						<field:wrapper class="form-group" path="userId">
							<label for="inputError" class="col-xs-12 col-sm-3 col-md-3 control-label no-padding-right">
							城主
							</label>
							<div class="col-xs-12 col-sm-5">
								<form:input id="inputError" class="width-100" path="userId" />
							</div>
							<form:errors cssClass="help-block col-xs-12 col-sm-reset inline" path="userId">
							</form:errors>
						</field:wrapper>
						
					<div class="form-group">
						<label class="col-sm-3 control-label no-padding-right" for="status">状态</label>
						<div class="col-sm-9">
							<form:select  id="status" path="status">
								<form:option value="0" label="隐藏"/>
								<form:option value="1" label="发布"/>
							</form:select>
						</div>
					</div>
					
						<div class="clearfix form-actions">
							<div class="col-md-offset-3 col-md-9">
								<button class="btn btn-info" type="submit">
									<i class="icon-ok bigger-110"></i> 提交
								</button>

								&nbsp; &nbsp; &nbsp;
								<a class="btn" href="<utils:returnUrl url="/cities.html" />">
									<i class="icon-undo bigger-110"></i> 返回
								</a>
							</div>
						</div>
					</fieldset>
				</form:form>
			</div>
					<!-- /.col -->
	
</layout:override>


<%@ include file="../layouts/main.jsp"%>