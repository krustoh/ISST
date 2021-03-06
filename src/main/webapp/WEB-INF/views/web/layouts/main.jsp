<%@ page language="java"
	import="java.util.*, cn.edu.zju.isst.taglib.NavigationLink"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="/jsp_layout" prefix="layout"%>
<%@ taglib uri="/navigation" prefix="navigation"%>
<%@ taglib uri="/flash_message" prefix="fm"%>
<%@ taglib prefix="utils" uri="/utils"%>

<layout:override name="stylesheets">
	<script src="<utils:resourceUrl path="/js/ace-extra.min.js" />"></script>
</layout:override>

<layout:override name="javascripts">
	<script src="<utils:resourceUrl path="/js/ace-elements.min.js" />"></script>
	<script src="<utils:resourceUrl path="/js/ace.min.js" />"></script>
</layout:override>

<layout:override name="body">

	<div class="navbar navbar-default" id="navbar">
		<script type="text/javascript">
			try {
				ace.settings.check('navbar', 'fixed');
			} catch (e) {
			}
		</script>

		<div class="navbar-container" id="navbar-container">
			<div class="navbar-header pull-left">
				<a href="#" class="navbar-brand"> <small>ISST</small> </a>
				<!-- /.brand -->
			</div>
			<!-- /.navbar-header -->

			<div class="navbar-header pull-right" role="navigation">
				<ul class="nav ace-nav">

					<li class="light-blue">
						<a data-toggle="dropdown" href="#" class="dropdown-toggle"> 
							<img class="nav-user-photo" src="<utils:resourceUrl path="/avatars/user.jpg" />" alt="${user.name}" /> 
							<span class="user-info"> <small>Welcome, </small>
								${user.name} </span> 
							<i class="icon-caret-down"></i> 
						</a>

						<ul class="user-menu pull-right dropdown-menu dropdown-yellow dropdown-caret dropdown-close">

							<li><a href="<utils:url url="/users/password.html" />"> <i class="icon-key"></i> 修改密码 </a>
							</li>

							<li class="divider">&nbsp;</li>

							<li><a href="<utils:url url="/logout" />"> <i class="icon-off"></i> 退出 </a>
							</li>
						</ul>
					</li>
				</ul>
				<!-- /.ace-nav -->
			</div>
			<!-- /.navbar-header -->
		</div>
		<!-- /.container -->
	</div>

	<div class="main-container" id="main-container">
		<script type="text/javascript">
			try {
				ace.settings.check('main-container', 'fixed');
			} catch (e) {
			}
		</script>

		<div class="main-container-inner">
			<a class="menu-toggler" id="menu-toggler" href="#"> <span
				class="menu-text"></span> </a>

			<div class="sidebar" id="sidebar">
				<script type="text/javascript">
					try {
						ace.settings.check('sidebar', 'fixed');
					} catch (e) {
					}
				</script>

			
				<ul class="nav nav-list">
					<navigation:item>
						<navigation:link key="campus_life" href="/archives/categories/campus.html" label="软院生活" class="dropdown-toggle">
							<i class="icon-apple" ></i>
							<span class="menu-text">软院生活</span>
						</navigation:link>
						<ul class="submenu">
							<navigation:item>
								<navigation:link key="archive_campus" href="/archives/categories/campus.html" label="软院快讯">
								软院快讯
								</navigation:link>
							</navigation:item>
							
							<navigation:item>
								<navigation:link href="/archives/categories/encyclopedia.html" key="archive_encyclopedia" label="软院百科">
									软院百科
								</navigation:link>
							</navigation:item>
							
							<navigation:item>
								<navigation:link key="archive_studying" href="/archives/categories/studying.html" label="学习园地">
								学习园地
								</navigation:link>
							</navigation:item>
							
							<navigation:item>
								<navigation:link href="/campus/activities.html" key="activities" label="在校活动">
									在校活动
								</navigation:link>
							</navigation:item>
							
							<navigation:item>
								<navigation:link href="/restaurants.html" key="services" label="便捷服务" class="dropdown-toggle">
									<i class="icon-double-angle-right"></i>
									便捷服务
									<b class="arrow icon-angle-down"></b>
								</navigation:link>
								<ul class="submenu">
									<navigation:item>
										<navigation:link href="/restaurants.html" key="restaurants" label="外卖">
											外卖
										</navigation:link>
									</navigation:item>
								</ul>
							</navigation:item>	
						</ul>
					</navigation:item>
					
					<navigation:item>
						<navigation:link key="job" href="/jobs/categories/internship.html" label="职场生活" class="dropdown-toggle">
							<i class="icon-suitcase" ></i>
							<span class="menu-text">职场信息</span>
						</navigation:link>
						<ul class="submenu">
							<navigation:item>
								<navigation:link key="job_internship" href="/jobs/categories/internship.html" label="实习">
								实习
								</navigation:link>
							</navigation:item>
							
							<navigation:item>
								<navigation:link href="/jobs/categories/employment.html" key="job_employment" label="就业">
									就业
								</navigation:link>
							</navigation:item>
							
							<navigation:item>
								<navigation:link href="/jobs/categories/recommend.html" key="job_recommend" label="内推">
									内推
								</navigation:link>
							</navigation:item>
							
							<navigation:item>
								<navigation:link href="/archives/categories/experience.html" key="job_experience" label="经验交流">
									经验交流
								</navigation:link>
							</navigation:item>	
						</ul>
					</navigation:item>
					
					<navigation:item>
						<navigation:link key="alumni" href="/alumni.html" label="通讯录" class="dropdown-toggle">
							<i class="icon-phone" ></i>
							<span class="menu-text">通讯录</span>
						</navigation:link>
					</navigation:item>
					
					<navigation:item>
						<navigation:link key="cities" href="/cities/${user.classId}/activities.html" label="同城" class="dropdown-toggle">
							<i class="icon-globe" ></i>
							<span class="menu-text">同城</span>
						</navigation:link>

						<ul class="submenu">
							
							<navigation:item>
								<navigation:link href="/activities.html" key="city_activity" label="同城活动">
											同城活动
								</navigation:link>
							</navigation:item>
							
							<c:if test="${user.cityPrincipal == true}">
							<navigation:item>
								<navigation:link href="/activities/auditing.html" key="city_activity_auditing" label="待审核活动">
											待审核活动
								</navigation:link>
							</navigation:item>
							</c:if>
						</ul>
					</navigation:item>
					
					<navigation:item>
						<navigation:link key="personal" href="/tasks.html" label="个人中心" class="dropdown-toggle">
							<i class="icon-cogs" ></i>
							<span class="menu-text">个人中心</span>
						</navigation:link>

						<ul class="submenu">
							<navigation:item >
								<navigation:link href="/alumni/${user.id}.html" key="personal_info" label="个人信息">
											个人信息
								</navigation:link>
							</navigation:item>
							
							<navigation:item>
								<navigation:link href="/tasks.html" key="student_affairs" label="学生事务" class="dropdown-toggle">
									<i class="icon-double-angle-right"></i>
									学生事务
									<b class="arrow icon-angle-down"></b>
								</navigation:link>
								<ul class="submenu">
									<navigation:item>
										<navigation:link href="/tasks.html" key="tasks" label="任务中心">
											任务中心
										</navigation:link>
									</navigation:item>
								</ul>
							</navigation:item>	
							
							<navigation:item >
								<navigation:link href="/users/archives/categories/experience.html" key="users_archive_experience" label="发布的经验">
											发布的经验
								</navigation:link>
							</navigation:item>
							
							<navigation:item >
								<navigation:link href="/users/jobs/categories/recommend.html" key="users_job_recommend" label="发布的内推">
											发布的内推
								</navigation:link>
							</navigation:item>
							
							<navigation:item >
								<navigation:link href="/users/activities.html" key="users_activities" label="发布的活动">
											发布的活动
								</navigation:link>
							</navigation:item>
							
							<navigation:item >
								<navigation:link href="/users/activities/participated.html" key="personal_activities_participant" label="参加的活动">
											参加的活动
								</navigation:link>
							</navigation:item>
					
							<navigation:item>
								<navigation:link key="nearby" href="#" label="附近的人" class="dropdown-toggle">
									<span class="menu-text">附近的人</span>
								</navigation:link>
							</navigation:item>
						</ul>
					</navigation:item>
				</ul>
			
				<!-- /.nav-list -->

				<div class="sidebar-collapse" id="sidebar-collapse">
					<i class="icon-double-angle-left"
						data-icon1="icon-double-angle-left"
						data-icon2="icon-double-angle-right"></i>
				</div>

				<script type="text/javascript">
					try {
						ace.settings.check('sidebar', 'collapsed');
					} catch (e) {
					}
				</script>
			</div>

			<div class="main-content">
				<div class="breadcrumbs" id="breadcrumbs">
					<script type="text/javascript">
						try {
							ace.settings.check('breadcrumbs', 'fixed');
						} catch (e) {
						}
					</script>

					<navigation:breadcrumbs/>

					<!-- #nav-search -->
					<div class="nav-search" id="nav-search">
						<form class="form-search">
							<span class="input-icon input-icon-right">
								<input type="text" class="nav-search-input" id="nav-search-input" autocomplete="off"> 
								<i class="icon-search nav-search-icon"></i>
								  
							</span>
							
						</form>
					</div>
				</div>
				
				<div class="page-content">
					<div class="page-header">
						<layout:block name="page-header"/>
						<navigation:pageSubTitle/>
					</div>
		
					<div class="row">
						<fm:message/>
						<layout:block name="content"></layout:block>
					</div>
				</div>
				
				<div class="ace-settings-container" id="ace-settings-container">
					<div class="btn btn-app btn-xs btn-warning ace-settings-btn"
						id="ace-settings-btn">
						<i class="icon-cog bigger-150"></i>
					</div>

					<div class="ace-settings-box" id="ace-settings-box">
						<div>
							<div class="pull-left">
								<select id="skin-colorpicker" class="hide">
									<option data-skin="default" value="#438EB9">#438EB9</option>
									<option data-skin="skin-1" value="#222A2D">#222A2D</option>
									<option data-skin="skin-2" value="#C6487E">#C6487E</option>
									<option data-skin="skin-3" value="#D0D0D0">#D0D0D0</option>
								</select>
							</div>
							<span>&nbsp; Choose Skin</span>
						</div>

						<div>
							<input type="checkbox" class="ace ace-checkbox-2"
								id="ace-settings-navbar" /> <label class="lbl"
								for="ace-settings-navbar"> Fixed Navbar</label>
						</div>

						<div>
							<input type="checkbox" class="ace ace-checkbox-2"
								id="ace-settings-sidebar" /> <label class="lbl"
								for="ace-settings-sidebar"> Fixed Sidebar</label>
						</div>

						<div>
							<input type="checkbox" class="ace ace-checkbox-2"
								id="ace-settings-breadcrumbs" /> <label class="lbl"
								for="ace-settings-breadcrumbs"> Fixed Breadcrumbs</label>
						</div>

						<div>
							<input type="checkbox" class="ace ace-checkbox-2"
								id="ace-settings-rtl" /> <label class="lbl"
								for="ace-settings-rtl"> Right To Left (rtl)</label>
						</div>

						<div>
							<input type="checkbox" class="ace ace-checkbox-2"
								id="ace-settings-add-container" /> <label class="lbl"
								for="ace-settings-add-container"> Inside <b>.container</b>
							</label>
						</div>
					</div>
				</div>
				<!-- /#ace-settings-container -->
			</div>
			<!-- /.main-container-inner -->

			<a href="javascript:scroll(0,0)" mce_href="javascript:scroll(0,0)"
				id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
				<i class="icon-double-angle-up icon-only bigger-110"></i> </a>
		</div>
	</div>
		<!-- /.main-container -->
</layout:override>


<%@ include file="html.jsp"%>