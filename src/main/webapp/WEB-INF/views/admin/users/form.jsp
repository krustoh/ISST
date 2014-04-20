<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="pagination" uri="/pagination"%>
<%@ taglib uri="/jsp_layout" prefix="layout"%>
<%@ taglib uri="/field" prefix="field"%>
<%@ taglib uri="/navigation" prefix="navigation"%>

<navigation:setNavigationActiveKey key="alumni"/>
<navigation:setPageTitle label="编辑"/>

<layout:override name="content">
	<div class="col-xs-12">
    	<form:form class="form-horizontal isst-form" modelAttribute="studentUserForm" method="POST">
			<fieldset>
				<field:wrapper class="form-group" path="username">
					<label for="username" class="col-xs-12 col-sm-3 col-md-3 control-label no-padding-right">学号 </label>
					
					<div class="col-xs-12 col-sm-5">
						<form:input id="username" class="form-control" path="username" />
					</div>
					<form:errors cssClass="help-block col-xs-12 col-sm-reset inline" path="username">
					</form:errors>
				</field:wrapper>

				<field:wrapper class="form-group" path="name">
					<label for="name" class="col-xs-12 col-sm-3 col-md-3 control-label no-padding-right">姓名 </label>
					
					<div class="col-xs-12 col-sm-5">
						<form:input id="name" class="form-control" path="name" />
					</div>
					
					<form:errors cssClass="help-block col-xs-12 col-sm-reset inline" path="name">
					</form:errors>
				</field:wrapper>

				<field:wrapper class="form-group" path="gender">
					<label for="gender" class="col-xs-12 col-sm-3 col-md-3 control-label no-padding-right">性别 </label>
				
					<div class="col-xs-12 col-sm-5">
							<label>
								<form:radiobutton name="form-field-radio" class="ace" path="gender" value="1"/>
								<span class="lbl">男</span> 
							</label>

							<label> 
								<form:radiobutton name="form-field-radio" class="ace" path="gender" value="2"/> 
								<span class="lbl">女</span> 
							</label>
					</div>
					
					<form:errors cssClass="help-block col-xs-12 col-sm-reset inline" path="gender">
					</form:errors>
				</field:wrapper>
				
				<field:wrapper class="form-group" path="classId">
					<label for="classId" class="col-xs-12 col-sm-3 col-md-3 control-label no-padding-right">班级</label>
					<div class="col-xs-12 col-sm-5">
						<form:select  id="classId" path="classId" class="form-control">
							<form:options items="${classes}" itemValue="id" itemLable="name"/>
						</form:select>
					</div>
					<form:errors cssClass="help-block col-xs-12 col-sm-reset inline" path="classId">
					</form:errors>
				</field:wrapper>
				
				<field:wrapper class="form-group" path="major">
					<label for="major" class="col-xs-12 col-sm-3 col-md-3 control-label no-padding-right">专业</label>					
					<div class="col-xs-12 col-sm-5">
						<form:select  id="major" path="major" class="form-control">
							<form:options items="${majors}" itemValue="name" itemLable="name"/>
						</form:select>
					</div>
					
					<form:errors cssClass="help-block col-xs-12 col-sm-reset inline" path="major">
					</form:errors>
				</field:wrapper>

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
						<a class="btn" href="${baseUrl}alumni.html"> 
							<i class="icon-undo bigger-110"></i> 返回 
						</a>
					</div>
				</div>
			</fieldset>
		</form:form>
	</div>
</layout:override>


<%@ include file="../layouts/main.jsp"%>