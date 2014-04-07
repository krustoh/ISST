<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="pagination" uri="/pagination"%>
<%@ taglib uri="/jsp_layout" prefix="layout"%>
<%@ taglib uri="/field" prefix="field"%>
<%@ taglib uri="/navigation" prefix="navigation"%>


<layout:override name="content">
			<div class="col-xs-12">
				<div class="tabbable">
					<ul class="nav nav-tabs" id="myTab">
						<li class="active"><a data-toggle="tab" href="#home"> <i
						class="green icon-home bigger-110"></i>基本信息 </a></li>

						<li><a data-toggle="tab" href="#profile">餐馆图标</a></li>
					</ul>

				<div class="tab-content">
					<div id="基本信息" class="tab-pane in active">
						<div class="col-xs-12">
				<form:form class="form-horizontal isst-form" modelAttribute="archiveForm" method="POST">
					
					<fieldset>
						<field:wrapper class="form-group" path="name">
							<label for="inputError" class="col-xs-12 col-sm-3 col-md-3 control-label no-padding-right">
							餐馆名称
							</label>
							<div class="col-xs-12 col-sm-5">
								<form:input id="inputError" class="width-100" path="name" />
							</div>
							<form:errors cssClass="help-block col-xs-12 col-sm-reset inline" path="name">
							</form:errors>
						</field:wrapper>
						
						<field:wrapper class="form-group" path="hotline">
							<label for="inputError" class="col-xs-12 col-sm-3 col-md-3 control-label no-padding-right">
							订餐电话
							</label>
							<div class="col-xs-12 col-sm-5">
								<form:input id="inputError" class="width-100" path="hotline" />
							</div>
							<form:errors cssClass="help-block col-xs-12 col-sm-reset inline" path="hotline">
							</form:errors>
						</field:wrapper>
						
						<field:wrapper class="form-group" path="businessHours">
							<label for="inputError" class="col-xs-12 col-sm-3 col-md-3 control-label no-padding-right">
							营业时间
							</label>
							<div class="col-xs-12 col-sm-5">
								<form:input id="inputError" class="width-100" path="businessHours" />
							</div>
							<form:errors cssClass="help-block col-xs-12 col-sm-reset inline" path="businessHours">
							</form:errors>
						</field:wrapper>
						
						<field:wrapper class="form-group" path="address">
							<label for="inputError" class="col-xs-12 col-sm-3 col-md-3 control-label no-padding-right">
							地址
							</label>
							<div class="col-xs-12 col-sm-5">
								<form:input id="inputError" class="width-100" path="address" />
							</div>
							<form:errors cssClass="help-block col-xs-12 col-sm-reset inline" path="address">
							</form:errors>
						</field:wrapper>
						
						
						<field:wrapper class="form-group" path="description">
							<label for="inputError" class="col-xs-12 col-sm-3 col-md-3 control-label no-padding-right">
						  		餐馆描述
							</label>
							<div class="col-xs-12 col-sm-5">
								<form:textarea path="description" id="description"></form:textarea>
							</div>
						</field:wrapper>
					
					    <div class="form-group">
							<label class="col-sm-3 control-label no-padding-right" for="form-field-1">餐馆图标</label>
							<div class="col-sm-9">
								<div class="widget-body">
									<div class="widget-main">
										<form:input type="file" id="id-input-file-2" path="picture"/>
									</div>
								</div>
							</div>
						</div>

						<div class="clearfix form-actions">
							<div class="col-md-offset-3 col-md-9">
								<button class="btn btn-info" type="submit">
									<i class="icon-ok bigger-110"></i> 提交
								</button>

								&nbsp; &nbsp; &nbsp;
								<a class="btn" href="${baseUrl}archives/categories/${category.alias}.html">
									<i class="icon-undo bigger-110"></i> 返回
								</a>
							</div>
						</div>
					</fieldset>
				</form:form>
			</div>
		</div>

					<div id="餐馆图标" class="tab-pane">
						<div class="col-xs-12">
							<form:form class="form-horizontal isst-form" modelAttribute="archiveForm" method="POST">
					    <fieldset>
						
					    <div class="form-group">
							<label class="col-sm-3 control-label no-padding-right" for="form-field-1">餐馆图标</label>
							<div class="col-sm-9">
								<div class="widget-body">
									<div class="widget-main">
										<form:input type="file" id="id-input-file-2" path="picture"/>
									</div>
								</div>
							</div>
						</div>
						
						

						<div class="clearfix form-actions">
							<div class="col-md-offset-3 col-md-9">
								<button class="btn btn-info" type="submit">
									<i class="icon-ok bigger-110"></i> 提交
								</button>

								&nbsp; &nbsp; &nbsp;
								<a class="btn" href="${baseUrl}archives/categories/${category.alias}.html">
									<i class="icon-undo bigger-110"></i> 返回
								</a>
							</div>
						</div>
					</fieldset>
				</form:form>
						</div>
					</div>
			</div>
		</div>
	</div>
		
	
</layout:override>

<layout:override name="javascripts">
	<script type="text/javascript">
		jQuery(function($){
			$('#id-input-file-1 , #id-input-file-2').ace_file_input({
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



<%@ include file="../layouts/main.jsp"%>