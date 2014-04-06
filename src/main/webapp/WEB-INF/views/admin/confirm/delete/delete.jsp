<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="pagination" uri="/pagination"%>
<%@ taglib uri="/jsp_layout" prefix="layout"%>

<layout:override name="content">
	<div class="page-content">
		<div class="page-header">
			<div class="pull-right" style="margin-right: 5%;">
				<a style="color:white" class="btn btn-primary" href="${baseUrl}archives/categories/campus/add.html">
					<i class="icon-plus align-top bigger-125"></i>
						添加快讯
				</a>
			</div>
						<h1 style="margin-top: 5px;">
							软院生活 <small><i class="icon-double-angle-right"></i> 快讯</small>
						</h1>
		</div>
		<div class="row">
			<div class="col-xs-12">
					<form:form action="" class="isst-table-form">
						<fieldset>
						<div class="alert alert-warnin">
							<button type="button" class="close" data-dismiss="alert">
								<i class="icon-remove"></i>
							</button>
							<strong>Warning!</strong>该操作不可逆，请再次确认！
							<br />
						</div>
						<div class="span-6"></div>
						
						<ul>
							<li>第一条</li>
							<li>第一条</li>
							<li>第一条</li>
							<li>第一条</li>
						</ul>
						
						<div class="clearfix form-actions">
							<div class="col-md-offset-3 col-md-9">
								<button class="btn btn-info" type="submit">
									<i class="icon-ok bigger-110"></i> 提交
								</button>

								&nbsp; &nbsp; &nbsp;
								<a class="btn" href="${baseUrl}archives/categories/campus/add.html">
									<i class="icon-undo bigger-110"></i> 返回
								</a>
							</div>
						</div>
						</fieldset>
					</form>
				</div>

			</div>
					<!-- /.col -->
		</div>
		<!-- /.row -->
	</div>
	<!-- /.page-content -->
	</div>
	<!-- /.main-content -->
</layout:override>

<layout:override name="javascript">
	<script src="${resourceUrl}js/ace-elements.min.js"></script>
	<script src="${resourceUrl}js/ace.min.js"></script>
		
</layout:override>

<%@ include file="../layouts/main.jsp"%>