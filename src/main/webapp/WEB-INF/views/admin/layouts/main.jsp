<%@ page language="java"
	import="java.util.*, cn.edu.zju.isst.taglib.NavigationLink"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="/jsp_layout" prefix="layout"%>
<%@ taglib uri="/navigation" prefix="navigation"%>
<%
String baseUrl = (String) request.getAttribute("baseUrl");
Map<String, NavigationLink> menu = new HashMap<String, NavigationLink>();
menu.put("archive_campus", new NavigationLink("软院生活", baseUrl+"archives/categories/campus.html"));
menu.put("archive_news", new NavigationLink("快讯", baseUrl+"archives/categories/campus.html"));
menu.put("archive_encyclopedia", new NavigationLink("百科", baseUrl+"archives/categories/encyclopedia.html"));
menu.put("activities", new NavigationLink("在校活动", baseUrl+"archives/categories/activities.html"));
menu.put("restaurants", new NavigationLink("便捷服务", baseUrl+"archives/categories/restaurants.html"));

menu.put("jobs", new NavigationLink("职场生活", "/admin/archives/categories/campus/list.html"));
menu.put("jobs_internship", new NavigationLink("实习", "/admin/jobs/categories/internship/list.html"));
menu.put("jobs_employment", new NavigationLink("就业", "/admin/jobs/categories/employment/list.html"));
menu.put("jobs_recommend", new NavigationLink("内推", "/admin/jobs/categories/recommend/list.html"));
menu.put("jobs_experience", new NavigationLink("经验交流", "/admin/jobs/categories/experience/list.html"));

menu.put("alumni", new NavigationLink("通讯录", "/admin/alumni/list.html"));
menu.put("alumni_update", new NavigationLink("编辑个人信息", "/admin/alumni/update.html"));
menu.put("alumni_check", new NavigationLink("查看个人信息", "/admin/alumni/user.html"));
menu.put("alumni_search", new NavigationLink("查询校友信息", "/admin/alumni/userlist.html"));

menu.put("cities", new NavigationLink("同城", "/admin/cities/leader.html"));
menu.put("cities_leader", new NavigationLink("城主", "/admin/cities/leader.html"));
menu.put("cities_activities", new NavigationLink("同城活动", "/admin/cities/activities/list.html"));
menu.put("cities_user", new NavigationLink("同城校友", "/admin/cities/userlist.html"));


request.setAttribute("navigationMenu", menu);
%>



<layout:override name="stylesheets">
	<script src="${resourceUrl}js/ace-extra.min.js"></script>
</layout:override>

<layout:override name="javascripts">
	<script src="${resourceUrl}js/ace-elements.min.js"></script>
	<script src="${resourceUrl}js/ace.min.js"></script>
</layout:override>

<layout:override name="body">

	<div class="navbar navbar-default" id="navbar">
		<script type="text/javascript">
			try {
				ace.settings.check('navbar', 'fixed')
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
							<img class="nav-user-photo" src="${resourceUrl}avatars/user.jpg" alt="${administrator.username}" /> 
							<span class="user-info"> <small>Welcom, </small>
								${administrator.username} </span> 
							<i class="icon-caret-down"></i> 
						</a>

						<ul class="user-menu pull-right dropdown-menu dropdown-yellow dropdown-caret dropdown-close">

							<li><a href="#"> <i class="icon-key"></i> 修改密码 </a>
							</li>

							<li class="divider">&nbsp;</li>

							<li><a href="#"> <i class="icon-off"></i> 退出 </a>
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
				ace.settings.check('main-container', 'fixed')
			} catch (e) {
			}
		</script>

		<div class="main-container-inner">
			<a class="menu-toggler" id="menu-toggler" href="#"> <span
				class="menu-text"></span> </a>

			<div class="sidebar" id="sidebar">
				<script type="text/javascript">
					try {
						ace.settings.check('sidebar', 'fixed')
					} catch (e) {
					}
				</script>

				<!-- <div class="sidebar-shortcuts" id="sidebar-shortcuts">
    <div class="sidebar-shortcuts-large" id="sidebar-shortcuts-large">
        <button class="btn btn-success">
            <i class="icon-signal"></i>
        </button>

        <button class="btn btn-info">
            <i class="icon-pencil"></i>
        </button>

        <a class="btn btn-warning" href="">
            <i class="icon-group"></i>
        </a>

        <a class="btn btn-danger" href="">
            <i class="icon-cogs"></i>
        </a>
    </div>

    <div class="sidebar-shortcuts-mini" id="sidebar-shortcuts-mini">
        <span class="btn btn-success"></span>

        <span class="btn btn-info"></span>

        <span class="btn btn-warning"></span>

        <span class="btn btn-danger"></span>
    </div>
</div> -->
				<!-- #sidebar-shortcuts -->
				<navigation:navigate active="test3" menu="${navigationMenu}"
					breadcrumbs="navigationBreadcrumbs">
					<ul class="nav nav-list">
						<navigation:item key="archive_campus">
							<a href="javascript:;" class="dropdown-toggle"><i class="icon-apple" ></i>
								<span class="menu-text">软院生活</span>
							</a>
							<ul class="submenu">
								<navigation:item key="archive_campus">
									<navigation:link key="archive_campus">快讯</navigation:link>
								</navigation:item>
								<navigation:item key="archive_encyclopedia">
									<navigation:link key="archive_encyclopedia">百科</navigation:link>
								</navigation:item>
								<navigation:item key="activities">
									<navigation:link key="activities">在校活动</navigation:link>
								</navigation:item>
								<navigation:item key="restaurants">
									<navigation:link key="restaurants">便捷服务</navigation:link>
								</navigation:item>
							</ul>
						</navigation:item>

						<navigation:item key="jobs">
							<a class="dropdown-toggle" href="javascript:;"><i class="icon-suitcase"></i>
								<span class="menu-text">职场生活</span>
							</a>
							<ul class="submenu">
								<navigation:item key="jobs_internship">
									<navigation:link key="jobs_internship">实习</navigation:link>
								</navigation:item>
								<navigation:item key="jobs_employment">
									<navigation:link key="jobs_employment">就业</navigation:link>
								</navigation:item>
								<navigation:item key="jobs_recommend">
									<navigation:link key="jobs_recommend">内推</navigation:link>
								</navigation:item>
								<navigation:item key="jobs_experience">
									<navigation:link key="jobs_experience">经验交流</navigation:link>
								</navigation:item>
							</ul>
						</navigation:item>

						<navigation:item key="alumni">
							<a class="dropdown-toggle" href="javascript:;"><i class="icon-phone"></i>
								<span class="menu-text">通讯录</span>
							</a>
							<ul class="submenu">
								<navigation:item key="alumni_update">
									<navigation:link key="alumni_update">编辑个人信息</navigation:link>
								</navigation:item>
								<navigation:item key="alumni_check">
									<navigation:link key="alumni_check">查看个人信息</navigation:link>
								</navigation:item>
								<navigation:item key="alumni_search">
									<navigation:link key="alumni_search">查询个人信息</navigation:link>
								</navigation:item>
							</ul>
						</navigation:item>

						<navigation:item key="cities">
							<a class="dropdown-toggle" href="javascript:;"><i class="icon-group"></i>
								<span class="menu-text">同城</span>
							</a>
							<ul class="submenu">
								<navigation:item key="cities_leader">
									<navigation:link key="cities_leader">城主</navigation:link>
								</navigation:item>
								<navigation:item key="cities_activities">
									<navigation:link key="cities_activities">同城活动</navigation:link>
								</navigation:item>
								<navigation:item key="cities_user">
									<navigation:link key="cities_user">同城校友</navigation:link>
								</navigation:item>
							</ul>
						</navigation:item>



					</ul>
				</navigation:navigate>


				<!-- /.nav-list -->

				<div class="sidebar-collapse" id="sidebar-collapse">
					<i class="icon-double-angle-left"
						data-icon1="icon-double-angle-left"
						data-icon2="icon-double-angle-right"></i>
				</div>

				<script type="text/javascript">
					try {
						ace.settings.check('sidebar', 'collapsed')
					} catch (e) {
					}
				</script>
			</div>

			<div class="main-content">
				<div class="breadcrumbs" id="breadcrumbs">
					<script type="text/javascript">
						try {
							ace.settings.check('breadcrumbs', 'fixed')
						} catch (e) {
						}
					</script>

					<ul class="breadcrumb">
						<li><i class="icon-home home-icon"></i> <a
							href="admin/archives/list.html">首页</a></li>
					</ul>
					<!-- .breadcrumb -->


					<!-- #nav-search -->
					<div class="nav-search" id="nav-search">
						<form class="form-search">
							<span class="input-icon"> <input type="text"
								placeholder="搜索" class="nav-search-input" id="nav-search-input"
								autocomplete="off"> <i
								class="icon-search nav-search-icon"></i> </span>
						</form>
					</div>
				</div>
				<layout:block name="content"></layout:block>


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
		<!-- /.main-container -->
</layout:override>


<%@ include file="html.jsp"%>