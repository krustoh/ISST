<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %> 
<%@ taglib uri="/jsp_layout" prefix="layout"%>
<%@ taglib uri="/field" prefix="field"%>
<%@ taglib uri="/flash_message" prefix="fm"%>

<c:set var="bodyCssClass" value="login-layout" scope="request" />

<layout:override name="body">
<div class="main-container">
    <div class="main-content">
        <div class="row">
            <div class="col-sm-10 col-sm-offset-1" >
                <div class="login-container">
                    <div class="center">
                        <h1 style=" padding: 5px 0;">
                            <img src="images/logo.png" />
                            <span class="white">ISST</span>
                        </h1>
                    </div>

                    <div class="space-6"></div>

                    <div class="position-relative" style="height: 340px;">
                        <div id="login-box" class="login-box visible widget-box no-border">
                            <div class="widget-body">
                                <div class="widget-main">
                                    <h4 class="header blue lighter bigger">
                                        <i class="icon-coffee green"></i> 欢迎使用，请登录
                                    </h4>

                                    <div class="space-6"></div>
                                    <fm:message/>
									<form:form modelAttribute="administratorLoginForm" method="POST">
										
                                        <fieldset>
                                        <field:wrapper path="username">
                                            <label class="block clearfix">
                                <span class="block input-icon input-icon-right">
                                    <form:input class="form-control" placeholder="用户名" path="username" value="" />
                                    <i class="icon-user"></i>
                                </span>
                                            </label>
                                </field:wrapper>
                                
                                <field:wrapper path="password">
                                            <label class="block clearfix">
                                <span class="block input-icon input-icon-right">
                                    <form:password class="form-control" placeholder="密码" path="password" />
                                    <i class="icon-lock"></i>
                                </span>
                                            </label>
                                </field:wrapper>
                                            <div class="space"></div>

                                            <div class="clearfix">
                                                <label class="inline ace-checkbox">
                                                    <form:checkbox class="ace" path="rememberMe" />
                                                    <span class="lbl"> 保持登录</span>
                                                </label>
                                                <button type="submit" class="width-35 pull-right btn btn-sm btn-primary">
                                                    <i class="icon-key"></i> 登录
                                                </button>
                                            </div>

                                            <div class="space-4"></div>
                                        </fieldset>
                                    </form:form>
                                </div><!-- /widget-main -->

                                <div class="toolbar clearfix">
                                    <div>
                                        <a href="#" onclick="show_box('forgot-box'); return false;" class="forgot-password-link">
                                            <i class="icon-arrow-left"></i>
                                            忘记密码？
                                        </a>
                                    </div>
                                </div>
                            </div><!-- /widget-body -->
                        </div><!-- /login-box -->

                        <div id="forgot-box" class="forgot-box widget-box no-border">
                            <div class="widget-body">
                                <div class="widget-main">
                                    <h4 class="header red lighter bigger">
                                        <i class="icon-key"></i>
                                        取回密码
                                    </h4>

                                    <div class="space-6"></div>
                                    <p>
                                        请输入您的邮箱地址，以获取密码修改链接
                                    </p>

                                    <form action="" method="post">
                                        <fieldset>
                                            <label class="block clearfix">
                                <span class="block input-icon input-icon-right">
                                    <input type="email" class="form-control" placeholder="Email" />
                                    <i class="icon-envelope"></i>
                                </span>
                                            </label>

                                            <div class="clearfix">
                                                <button type="button" class="width-35 pull-right btn btn-sm btn-danger">
                                                    <i class="icon-lightbulb"></i>
                                                    提交
                                                </button>
                                            </div>
                                        </fieldset>
                                    </form>
                                </div><!-- /widget-main -->

                                <div class="toolbar center">
                                    <a href="#" onclick="show_box('login-box'); return false;" class="back-to-login-link">
                                        返回登录
                                        <i class="icon-arrow-right"></i>
                                    </a>
                                </div>
                            </div><!-- /widget-body -->
                        </div><!-- /forgot-box -->
                    </div><!-- /position-relative -->

                </div>
            </div><!-- /.col -->
        </div><!-- /.row -->
    </div>
</div><!-- /.main-container -->
</layout:override>

<layout:override name="javascripts">
<script type="text/javascript">
    function show_box(id) {
        jQuery('.widget-box.visible').removeClass('visible');
        jQuery('#'+id).addClass('visible');
    }
</script>
</layout:override>

<%@ include file="layouts/html.jsp" %>