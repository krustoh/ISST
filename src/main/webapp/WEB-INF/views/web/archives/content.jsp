<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="/jsp_layout" prefix="layout"%>

<layout:override name="content">
	<div class="page-content">
		<div class="page-header">
			<div class="pull-right" style="margin-right: 10%;"></div>

			<h1 style="margin-top: 5px;">
				软院生活 <small><i class="icon-double-angle-right"></i> 快讯</small>
			</h1>
		</div>
		<div class="row">
			<div class="col-xs-12">
				<%@ include file="../blocks/message.jsp"%>
				<h2 class="center"> 关于软件学院2014届优秀毕业生获奖名单公示通知</h2>
				<div class="center">
					发布人：<span>研工办</span></t>
					时间：<span>2014-03-01</span>
				</div>
				<div>
					<p>各位研究生： 根据评奖细则，经班级推荐、学院奖学金评奖委员会评审，2014届优秀毕业生名单已确定，现将名单公示，具体名单见附件《软件学院2014届优秀毕业生获奖名单》。各位同学如有异议，请于3月1日前与学院研工办联系。</p>
					<p>附件：软件学院2014届优秀毕业生获奖名单</p>
					<p>联系邮箱：E-mail-cheny@cst.zju.edu.cn</p>
					<p>联系电话：0574-27830898</p>
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
		<script src="resources/admin/js/ace-elements.min.js"></script>
	<script src="resources/admin/js/ace.min.js"></script>
		<script>
			$(document).ready(function () {
				var options = {
					currentPage: 1,
					totalPages: 1,
					bootstrapMajorVersion: 3
				}
				$("#paginator").bootstrapPaginator(options);
			});
		</script>	
</layout:override>

<%@ include file="../layouts/main.jsp"%>