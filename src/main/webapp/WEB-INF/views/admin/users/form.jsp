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
    	<form:form class="form-horizontal isst-form" modelAttribute="alumniForm">
			<fieldset>
				<field:wrapper class="form-group" path="username">
					<label for="inputError" class="col-xs-12 col-sm-3 col-md-3 control-label no-padding-right">学号 </label>
					<div class="col-sm-9">
					<div class="col-xs-12 col-sm-5">
						<form:input id="inputError" class="form-control" path="username" />
					</div>
					</div>
					
					<form:errors cssClass="help-block col-xs-12 col-sm-reset inline" path="username">
					</form:errors>
				</field:wrapper>

				<field:wrapper class="form-group" path="name">
					<label for="inputError" class="col-xs-12 col-sm-3 col-md-3 control-label no-padding-right">姓名 </label>
					<div class="col-sm-9">
					<div class="col-xs-12 col-sm-5">
						<form:input id="inputError" class="form-control" path="name" />
					</div>
					</div>
					
					<form:errors cssClass="help-block col-xs-12 col-sm-reset inline" path="name">
					</form:errors>
				</field:wrapper>

				<field:wrapper class="form-group" path="gender">
					<label for="inputError" class="col-xs-12 col-sm-3 col-md-3 control-label no-padding-right">性别 </label>
					<div class="col-sm-9">
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
					</div>
					
					<form:errors cssClass="help-block col-xs-12 col-sm-reset inline" path="gender">
					</form:errors>
				</field:wrapper>
				
				<field:wrapper class="form-group" path="grade">
					<label for="inputError" class="col-xs-12 col-sm-3 col-md-3 control-label no-padding-right">年级</label>
					<div class="col-sm-9">
					<div class="col-xs-12 col-sm-5">
						<form:input id="inputError" class="form-control" path="grade" />
					</div>
					</div>
					
					<form:errors cssClass="help-block col-xs-12 col-sm-reset inline" path="grade">
					</form:errors>
				</field:wrapper>

				<field:wrapper class="form-group" path="classId">
					<label for="classId" class="col-xs-12 col-sm-3 col-md-3 control-label no-padding-right">班级</label>
					<div class="col-sm-9">
					<div class="col-xs-12 col-sm-5">
						<form:select  id="classId" path="classId" >
							<form:options items="${classes}" itemValue="id" itemLable="name"/>
						</form:select>
					</div>
					</div>
					
					<form:errors cssClass="help-block col-xs-12 col-sm-reset inline" path="classId">
					</form:errors>
				</field:wrapper>
				
				<field:wrapper class="form-group" path="majorId">
					<label for="majorId" class="col-xs-12 col-sm-3 col-md-3 control-label no-padding-right">专业</label>
					<div class="col-sm-9">
					<div class="col-xs-12 col-sm-5">
						<form:select  id="majorId" path="majorId">
							<form:options items="${majors}" itemValue="id" itemLable="name"/>
						</form:select>
					</div>
					</div>
					
					<form:errors cssClass="help-block col-xs-12 col-sm-reset inline" path="majorId">
					</form:errors>
				</field:wrapper>

				<field:wrapper class="form-group" path="email">
					<label for="inputError" class="col-xs-12 col-sm-3 col-md-3 control-label no-padding-right">邮箱</label>
					<div class="col-sm-9">
					<div class="col-xs-10 col-sm-5">
						<form:input id="inputError" class="form-control" path="email" />
					</div>
					<span class="help-inline col-xs-12 col-sm-7"> 
						<label class="middle"> 
							<form:checkbox class="ace" id="public-check" path="privateEmail"/> 
							<span class="lbl">不公开</span> 
						</label> 
					</span>
					<form:errors cssClass="help-block col-xs-12 col-sm-reset inline" path="email">
					</form:errors>
					</div>
				</field:wrapper>
         
                
				<field:wrapper class="form-group" path="qq">
					<label for="inputError" class="col-xs-12 col-sm-3 col-md-3 control-label no-padding-right">QQ</label>
					<div class="col-sm-9">
					<div class="col-xs-12 col-sm-5">
						<form:input id="inputError" class="form-control" path="qq" />
					</div>
					<span class="help-inline col-xs-12 col-sm-7"> 
						<label class="middle"> 
							<form:checkbox class="ace" id="public-check" path="privateQQ"/> 
							<span class="lbl">不公开</span>
						</label> 
					</span>
					</div>
					<form:errors cssClass="help-block col-xs-12 col-sm-reset inline" path="qq">
					</form:errors>
				</field:wrapper>
				
				<field:wrapper class="form-group" path="phone">
					<label for="inputError" class="col-xs-12 col-sm-3 col-md-3 control-label no-padding-right">联系电话</label>
					<div class="col-sm-9">
					<div class="col-xs-12 col-sm-5">
						<form:input id="inputError" class="form-control" path="phone" />
					</div>
					<span class="help-inline col-xs-12 col-sm-7"> 
						<label class="middle"> 
							<form:checkbox class="ace" id="public-check" path="privatePhone"/> 
							<span class="lbl">不公开</span> 
						</label> 
					</span>
					</div>
					<form:errors cssClass="help-block col-xs-12 col-sm-reset inline" path="phone">
					</form:errors>
				</field:wrapper>
				
				<field:wrapper class="form-group" path="company">
					<label for="inputError" class="col-xs-12 col-sm-3 col-md-3 control-label no-padding-right">工作单位</label>
					<div class="col-sm-9">
					<div class="col-xs-12 col-sm-5">
						<form:input id="inputError" class="form-control" path="company" />
					</div>
					<span class="help-inline col-xs-12 col-sm-7"> 
						<label class="middle"> 
							<form:checkbox class="ace" id="public-check" path="privateCompany"/> 
							<span class="lbl">不公开</span> 
						</label> 
					</span>
					</div>
					<form:errors cssClass="help-block col-xs-12 col-sm-reset inline" path="company">
					</form:errors>
				</field:wrapper>
				
				<field:wrapper class="form-group" path="cityId">
					<label for="cityId" class="col-xs-12 col-sm-3 col-md-3 control-label no-padding-right">工作城市</label>
					<div class="col-sm-9">
						<div class="col-xs-12 col-sm-5">
							<form:select  id="cityId" path="cityId">
								<form:options items="${cities}" itemValue="id" itemLabel="name"/>
							</form:select>
						</div>
					</div>
					
					<form:errors cssClass="help-block col-xs-12 col-sm-reset inline" path="cityId">
					</form:errors>
				</field:wrapper>
				
				<field:wrapper class="form-group" path="position">
					<label for="inputError" class="col-xs-12 col-sm-3 col-md-3 control-label no-padding-right">工作职务</label>
					<div class="col-sm-9">
					<div class="col-xs-12 col-sm-5">
						<form:input id="inputError" class="form-control" path="position" />
					</div>
					<span class="help-inline col-xs-12 col-sm-7"> 
						<label class="middle"> 
							<form:checkbox class="ace" id="public-check" path="privatePosition"/> 
							<span class="lbl">不公开</span> 
						</label> 
					</span>
					</div>
					<form:errors cssClass="help-block col-xs-12 col-sm-reset inline" path="position">
					</form:errors>
				</field:wrapper>
				
				<field:wrapper class="form-group" path="signature">
					<label for="inputError" class="col-xs-12 col-sm-3 col-md-3 control-label no-padding-right">个性签名</label>
					<div class="col-sm-9">
					<div class="col-xs-12 col-sm-5">
						<form:textarea id="signature" class="form-control" path="signature" />
					</div>
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

<layout:override name="javascripts">
	<script type="text/javascript">
		jQuery(function($) {
			$('#pictureFile').ace_file_input({
				no_file : 'No File ...',
				btn_choose : 'Choose',
				btn_change : 'Change',
				droppable : false,
				onchange : null,
				thumbnail : false
			//| true | large
			//whitelist:'gif|png|jpg|jpeg'
			//blacklist:'exe|php'
			//onchange:''
			//
			});
		});
	</script>
</layout:override>



<%@ include file="../layouts/main.jsp"%>