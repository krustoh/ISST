<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="pagination" uri="/pagination"%>
<%@ taglib uri="/jsp_layout" prefix="layout"%>
<%@ taglib uri="/field" prefix="field"%>
<%@ taglib uri="/navigation" prefix="navigation"%>

<navigation:setNavigationActiveKey key="alumni"/>
<navigation:setPageTitle label="查看"/>

<layout:override name="content">
	<div class="col-xs-12">
		<%@ include file="../blocks/message.jsp"%>
		<div class="table-responsive">
			<form action="" class="isst-table-form">
				<fieldset>
					<table class="table table-striped table-bordered table-hover">
						<thead><tr style="font-weight:bold"><td colspan="6">基本信息</td></tr></thead>
						<tbody>
							<tr>
								<td>学号</td><td>${alumnus.username}</td>
							  	<td>姓名</td><td>${alumnus.name}</td>
							  	<td>性别</td><td>${alumnus.gender==1?'男':'女'}</td>
							</tr>	
							<tr>
							  	<td>年级</td><td>${alumnus.grade}</td>
							  	<td>班级</td><td>${alumnus.classId}</td>
								<td>专业</td><td>${alumnus.majorId}</td>
							</tr>
							<tr>
								<td>QQ</td><td>${alumnus.qq}</td>
							  	<td>邮箱</td><td>${alumnus.email}</td>
							  	<td>联系电话</td><td>${alumnus.email}</td>
							</tr>
						</tbody>
						
						<thead><tr style="font-weight:bold"><td colspan="6">工作信息</td></tr></thead>
						<tbody>
							<tr>
								<td>工作单位</td><td colspan="5">${alumnus.company}</td>
							</tr>	
							<tr>
							  	<td>工作职务</td><td colspan="5">${alumnus.position}</td>
							</tr>
							<tr>
								<td>工作城市</td><td colspan="5">${alumnus.cityId}</td>
							</tr>
						</tbody>
					</table>

					<div class="clearfix form-actions">
						<div class="col-md-offset-3 col-md-9 ">
							<a class="btn" href="${baseUrl}alumni/${user.id}.html"> 
								<i class="icon-edit bigger-120"></i>编辑 
							</a>
							&nbsp; &nbsp; &nbsp; 
							<a class="btn" href="${baseUrl}alumni.html"> 
								<i class="icon-download bigger-120"></i>导出 
							</a>
							&nbsp; &nbsp; &nbsp; 
							<a class="btn" href="${baseUrl}alumni.html"> 
								<i class="icon-undo bigger-120"></i>返回 
							</a>
						</div>
					</div>
				</fieldset>
			</form>
		</div>
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