<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="pagination" uri="/pagination"%>
<%@ taglib uri="/jsp_layout" prefix="layout"%>
<%@ taglib uri="/field" prefix="field"%>
<%@ taglib uri="/navigation" prefix="navigation"%>
<%@ taglib uri="/utils" prefix="utils"%>

<navigation:setNavigationActiveKey key="personal_info"/>
<navigation:setPageTitle label="编辑"/>

<layout:override name="content">
	<div class="col-xs-12">
    	<form:form class="form-horizontal isst-form" modelAttribute="alumniForm" method="POST">
			<fieldset>

				<field:wrapper class="form-group" path="email">
					<label for="email" class="col-xs-12 col-sm-3 col-md-3 control-label no-padding-right">邮箱</label>					
					<div class="col-xs-8 col-sm-5">
						<form:input id="email" class="form-control" path="email" />					
						<form:errors cssClass="help-block col-xs-12 col-sm-reset inline" path="email">
						</form:errors>
					</div>
					<div class="col-xs-4 col-sm-2">
						<label class="middle"> 
							<form:checkbox class="ace" id="public-check" path="privateEmail"/> 
							<span class="lbl">不公开</span> 
						</label> 
					</div>					
				</field:wrapper>
         
                
				<field:wrapper class="form-group" path="qq">
					<label for="qq" class="col-xs-12 col-sm-3 col-md-3 control-label no-padding-right">QQ</label>					
					<div class="col-xs-8 col-sm-5">
						<form:input id="qq" class="form-control" path="qq" />
						<form:errors cssClass="help-block col-xs-12 col-sm-reset inline" path="qq">
						</form:errors>
					</div>
					<div class="col-xs-4 col-sm-2">
						<label class="middle"> 
							<form:checkbox class="ace" id="public-check" path="privateQQ"/> 
							<span class="lbl">不公开</span>
						</label>
					</div>
				</field:wrapper>
				
				<field:wrapper class="form-group" path="phone">
					<label for="phone" class="col-xs-12 col-sm-3 col-md-3 control-label no-padding-right">联系电话</label>					
					<div class="col-xs-8 col-sm-5">
						<form:input id="phone" class="form-control" path="phone" />
						<form:errors cssClass="help-block col-xs-12 col-sm-reset inline" path="phone">
						</form:errors>
					</div>
					<div class="col-xs-4 col-sm-2">
						<label class="middle"> 
							<form:checkbox class="ace" id="public-check" path="privatePhone"/> 
							<span class="lbl">不公开</span> 
						</label> 
					</div>
				</field:wrapper>
				
				<field:wrapper class="form-group" path="company">
					<label for="company" class="col-xs-12 col-sm-3 col-md-3 control-label no-padding-right">公司</label>
					<div class="col-xs-8 col-sm-5">
						<form:input id="company" class="form-control" path="company" />
						<form:errors cssClass="help-block col-xs-12 col-sm-reset inline" path="company">
						</form:errors>
					</div>
					<div class="col-xs-4 col-sm-2">
						<label class="middle"> 
							<form:checkbox class="ace" id="public-check" path="privateCompany"/> 
							<span class="lbl">不公开</span> 
						</label> 
					</div>
				</field:wrapper>
				
				<field:wrapper class="form-group" path="cityId">
					<label for="cityId" class="col-xs-12 col-sm-3 col-md-3 control-label no-padding-right">城市</label>
					<div class="col-xs-12 col-sm-5">
						<form:select  id="cityId" path="cityId" class="form-control">
							<form:option value="0" label="其他"></form:option>
							<form:options items="${cities}" itemValue="id" itemLabel="name"/>
						</form:select>
					</div>
					<form:errors cssClass="help-block col-xs-12 col-sm-reset inline" path="cityId">
					</form:errors>
				</field:wrapper>
				
				<field:wrapper class="form-group" path="position">
					<label for="position" class="col-xs-12 col-sm-3 col-md-3 control-label no-padding-right">职务</label>
					<div class="col-xs-8 col-sm-5">
						<form:input id="position" class="form-control" path="position" />
						<form:errors cssClass="help-block col-xs-12 col-sm-reset inline" path="position">
						</form:errors>
					</div>
					<div class="col-xs-4 col-sm-2">
						<label class="middle"> 
							<form:checkbox class="ace" id="public-check" path="privatePosition"/> 
							<span class="lbl">不公开</span> 
						</label> 
					</div>
				</field:wrapper>
				
				<field:wrapper class="form-group" path="signature">
					<label for="signature" class="col-xs-12 col-sm-3 col-md-3 control-label no-padding-right">个性签名</label>
					<div class="col-xs-12 col-sm-5">
						<form:textarea id="signature" class="form-control" path="signature" />
					</div>
					<form:errors cssClass="help-block col-xs-12 col-sm-reset inline" path="signature">
					</form:errors>
				</field:wrapper>

				<div class="clearfix form-actions">
					<div class="col-md-offset-3 col-md-9">
						<button class="btn btn-info" type="submit">
							<i class="icon-ok bigger-110"></i> 提交
						</button>
						&nbsp; &nbsp; &nbsp; 
						<a class="btn" href="<utils:returnUrl url="/alumni.html" />"> 
							<i class="icon-undo bigger-110"></i> 返回 
						</a>
					</div>
				</div>
			</fieldset>
		</form:form>
	</div>
</layout:override>


<%@ include file="../layouts/main.jsp"%>