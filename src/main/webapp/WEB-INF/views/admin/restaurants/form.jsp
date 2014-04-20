<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="pagination" uri="/pagination"%>
<%@ taglib uri="/jsp_layout" prefix="layout"%>
<%@ taglib uri="/field" prefix="field"%>
<%@ taglib uri="/navigation" prefix="navigation"%>
<%@ taglib prefix="utils" uri="/utils"%>

<navigation:setNavigationActiveKey key="restaurants"/>

<layout:override name="content">
	<div class="col-xs-12">
		<form:form class="form-horizontal isst-form" modelAttribute="restaurantForm" enctype="multipart/form-data">
			<fieldset>
				<field:wrapper class="form-group" path="name">
					<label for="inputError" class="col-xs-12 col-sm-3 col-md-3 control-label no-padding-right"> 餐馆名称 </label>
					<div class="col-xs-12 col-sm-5">
						<form:input id="inputError" class="form-control" path="name" />
					</div>
					<form:errors cssClass="help-block col-xs-12 col-sm-reset inline" path="name">
					</form:errors>
				</field:wrapper>

				<field:wrapper class="form-group" path="hotline">
					<label for="inputError" class="col-xs-12 col-sm-3 col-md-3 control-label no-padding-right"> 订餐电话 </label>
					<div class="col-xs-12 col-sm-5">
						<form:input id="inputError" class="form-control" path="hotline" />
					</div>
					<form:errors cssClass="help-block col-xs-12 col-sm-reset inline" path="hotline">
					</form:errors>
				</field:wrapper>

				<field:wrapper class="form-group" path="businessHours">
					<label for="inputError" class="col-xs-12 col-sm-3 col-md-3 control-label no-padding-right"> 营业时间 </label>
					<div class="col-xs-12 col-sm-5">
						<form:input id="inputError" class="form-control" path="businessHours" />
					</div>
					<form:errors cssClass="help-block col-xs-12 col-sm-reset inline" path="businessHours">
					</form:errors>
				</field:wrapper>

				<field:wrapper class="form-group" path="address">
					<label for="inputError" class="col-xs-12 col-sm-3 col-md-3 control-label no-padding-right"> 地址 </label>
					<div class="col-xs-12 col-sm-5">
						<form:input id="inputError" class="form-control" path="address" />
					</div>
					<form:errors cssClass="help-block col-xs-12 col-sm-reset inline" path="address">
					</form:errors>
				</field:wrapper>

				<field:wrapper class="form-group" path="description">
					<label for="inputError" class="col-xs-12 col-sm-3 col-md-3 control-label no-padding-right"> 餐馆描述 </label>
					<div class="col-xs-12 col-sm-5">
						<form:textarea path="description" id="description" class="form-control"></form:textarea>
					</div>
				</field:wrapper>

				<div class="form-group">
					<label class="col-xs-12 col-sm-3 col-md-3 control-label no-padding-right" for="form-field-1">餐馆图标</label>
					<div class="col-xs-12 col-sm-5">
						<form:input type="file" id="pictureFile" path="pictureFile" />
						
						<c:if test="${not empty restaurantForm.picture}">
						<div class="center">
							<a href="${restaurantForm.picture}" data-rel="colorbox">
								<img src="${restaurantForm.picture}" class="width-95"/>
							</a>
						</div>
						</c:if>
					</div>
				</div>

				<div class="clearfix form-actions">
					<div class="col-md-offset-3 col-md-9">
						<button class="btn btn-info" type="submit">
							<i class="icon-ok bigger-110"></i> 提交
						</button>
						&nbsp; &nbsp; &nbsp; 
						<a class="btn" href="<utils:returnUrl url="/restaurants.html" />"> 
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