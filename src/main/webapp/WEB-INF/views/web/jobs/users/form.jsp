<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="pagination" uri="/pagination"%>
<%@ taglib uri="/jsp_layout" prefix="layout"%>
<%@ taglib uri="/field" prefix="field"%>
<%@ taglib uri="/navigation" prefix="navigation"%>
<%@ taglib uri="/utils" prefix="utils"%>

<navigation:setNavigationActiveKey key="users_job_recommend"/>
<navigation:setPageTitle label="${jobForm.id>0?'编辑':'添加'}"/>

<layout:override name="content">
			<div class="col-xs-12">
				<form:form class="form-horizontal isst-form" modelAttribute="jobForm" method="POST">
					<form:hidden path="categoryId"/>
					<fieldset>
						<field:wrapper class="form-group" path="title">
							<label for="title" class="col-xs-12 col-sm-3 col-md-3 control-label no-padding-right">
							标题
							</label>
							<div class="col-xs-12 col-sm-5">
								<form:input id="title" class="width-100" path="title" />
							</div>
							<form:errors cssClass="help-block col-xs-12 col-sm-reset inline" path="title">
							</form:errors>
						</field:wrapper>
						
						<field:wrapper class="form-group" path="company">
							<label for="company" class="col-xs-12 col-sm-3 col-md-3 control-label no-padding-right">
							公司
							</label>
							<div class="col-xs-12 col-sm-5">
								<form:input id="company" class="width-100" path="company" />
							</div>
							<form:errors cssClass="help-block col-xs-12 col-sm-reset inline" path="company">
							</form:errors>
						</field:wrapper>
						
						<field:wrapper class="form-group" path="position">
							<label for="position" class="col-xs-12 col-sm-3 col-md-3 control-label no-padding-right">
							职务
							</label>
							<div class="col-xs-12 col-sm-5">
								<form:input id="position" class="width-100" path="position" />
							</div>
							<form:errors cssClass="help-block col-xs-12 col-sm-reset inline" path="position">
							</form:errors>
						</field:wrapper>
						
						<field:wrapper class="form-group" path="cityId">
							<label for="cityId" class="col-xs-12 col-sm-3 col-md-3 control-label no-padding-right">
							城市
							</label>
							<div class="col-sm-9">
								<form:select  id="cityId" path="cityId">
									<form:options items="${cities}" itemValue="id" itemLabel="name"/>
								</form:select>
							</div>
							<form:errors cssClass="help-block col-xs-12 col-sm-reset inline" path="cityId">
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
								<a class="btn" href="<utils:returnUrl url="/users/jobs/${category.alias}.html" />">
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
});
</script>
</layout:override>



<%@ include file="../../layouts/main.jsp"%>