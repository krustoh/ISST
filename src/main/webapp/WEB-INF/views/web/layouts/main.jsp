<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="/jsp_layout" prefix="layout"%>

<layout:override name="stylesheets">
	<script src="resources/admin/js/ace-extra.min.js"></script>
</layout:override>

<layout:override name="javascripts">
	<script src="resources/admin/js/ace-elements.min.js"></script>
	<script src="resources/admin/js/ace.min.js"></script>
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

					<li class="light-blue"><a data-toggle="dropdown" href="#"
						class="dropdown-toggle"> <img class="nav-user-photo"
							src="resources/admin/avatars/user.jpg" alt="Jason's Photo" /> <span
							class="user-info"> <small>Welcom, </small> ${user.name} </span> <i
							class="icon-caret-down"></i> </a>

						<ul
							class="user-menu pull-right dropdown-menu dropdown-yellow dropdown-caret dropdown-close">

							<li><a href="#"> <i class="icon-key"></i> 修改密码 </a>
							</li>

							<li class="divider"></li>

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
				<ul class="nav nav-list">
					<li class="active"><a href="#" class="dropdown-toggle">软院生活</a>
						<ul class="submenu">
							<li class="active"><a href="web/archives/list.html">快讯</a></li>
							<li><a href="web/archives/list.html">百科</a></li>
							<li><a href="web/archives/list.html">在校活动</a></li>
							<li><a href="web/archives/list.html">便捷服务</a></li>
						</ul>
					</li>
					<li><a href="#" class="dropdown-toggle">职场生活</a>
						<ul class="submenu" style="display:none;">
							<li><a href="web/archives/list.html">实习</a></li>
							<li><a href="web/archives/list.html">就业</a></li>
							<li><a href="web/archives/list.html">内推</a></li>
							<li><a href="web/archives/list.html">经验交流</a></li>
						</ul>
					</li>
					<li><a href="#" class="dropdown-toggle">通讯录</a>
						<ul class="submenu" style="display:none;">
							<li><a href="web/archives/list.html">编辑个人信息</a></li>
							<li><a href="web/archives/list.html">查看个人信息</a></li>
							<li><a href="web/archives/list.html">查询校友信息</a></li>
						</ul>
					</li>
					<li><a href="#" class="dropdown-toggle">同城</a>
						<ul class="submenu" style="display:none;">
							<li><a href="#">城主</a></li>
							<li><a href="#">同城活动</a></li>
							<li><a href="#">同城校友</a></li>
						</ul>
					</li>
				</ul>
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
							href="web/archives/list.html">首页</a></li>
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
				<layout:block name="content">33333333</layout:block>


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


<%@ include file="../layouts/html.jsp"%>