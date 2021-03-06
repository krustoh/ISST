<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="pagination" uri="/pagination"%>
<%@ taglib uri="/jsp_layout" prefix="layout"%>
<%@ taglib uri="/field" prefix="field"%>
<%@ taglib uri="/navigation" prefix="navigation"%>
<%@ taglib prefix="utils" uri="/utils"%>

<navigation:setNavigationActiveKey key="restaurants"/>
<navigation:setPageTitle label="添加菜单"/>

<layout:override name="content">

			<div class="col-xs-12">
				<form:form class="form-horizontal isst-form" modelAttribute="restaurantMenuForm" enctype="multipart/form-data">
					
					<fieldset>
						<field:wrapper class="form-group" path="name">
							<label for="name" class="col-xs-12 col-sm-3 col-md-3 control-label no-padding-right">
							菜品名称
							</label>
							<div class="col-xs-12 col-sm-5">
								<form:input id="name" class="width-100" path="name" />
							</div>
							<form:errors cssClass="help-block col-xs-12 col-sm-reset inline" path="name">
							</form:errors>
						</field:wrapper>
						
						<field:wrapper class="form-group" path="price">
							<label for="price" class="col-xs-12 col-sm-3 col-md-3 control-label no-padding-right">
							菜品价格
							</label>
							<div class="col-xs-12 col-sm-5">
								<form:input id="price" class="width-100" path="price" />
							</div>
							<form:errors cssClass="help-block col-xs-12 col-sm-reset inline" path="price">
							</form:errors>
						</field:wrapper>
						
						<field:wrapper class="form-group" path="description">
							<label for="description" class="col-xs-12 col-sm-3 col-md-3 control-label no-padding-right">
						  		菜品描述
							</label>
							<div class="col-xs-12 col-sm-5">
								<form:textarea path="description" id="description"></form:textarea>
							</div>
						</field:wrapper>
						
						<div class="form-group">
							<label class="col-xs-12 col-sm-3 col-md-3 control-label no-padding-right" for="pictureFile">菜品图片</label>
							<div class="col-xs-12 col-sm-5">
								<form:input type="file" id="pictureFile" path="pictureFile" />
						
								<c:if test="${not empty restaurantMenuForm.picture}">
								<div class="center">
									<a href="${restaurantMenuForm.picture}" data-rel="colorbox">
										<img src="${restaurantMenuForm.picture}" class="width-95"/>
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
								<a class="btn" href="<utils:returnUrl url="/restaurants/${restaurant.id}/menus.html" />">
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
		jQuery(function($){
			$('#pictureFile').ace_file_input({
					no_file:'No File ...',
					btn_choose:'Choose',
					btn_change:'Change',
					droppable:false,
					onchange:null,
					thumbnail:false //| true | large
					//whitelist:'gif|png|jpg|jpeg'
					//blacklist:'exe|php'
					//onchange:''
					//
				});
		});
	</script>
</layout:override>



<%@ include file="../../layouts/main.jsp"%>