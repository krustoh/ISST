<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/jsp_layout" prefix="layout"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="navigation" uri="/navigation"%>
<%@ taglib prefix="utils" uri="/utils"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>
    <navigation:pageTitle/>
    <layout:block name="title"></layout:block>
    </title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <layout:block name="head"></layout:block>
    
    <link href="<utils:resourceUrl path="/css/bootstrap.min.css" />" rel="stylesheet" />
	<link rel="stylesheet" href="<utils:resourceUrl path="/css/font-awesome.min.css"/>"  />
	
	<!--[if IE 7]>
	<link rel="stylesheet" href="<utils:resourceUrl path="/css/font-awesome-ie7.min.css" />" />
	<![endif]-->
	
	<!-- page specific plugin styles -->
	<link rel="stylesheet" href="<utils:resourceUrl path="/css/colorbox.css" />" />
	<!-- fonts -->
	
	<link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Open+Sans:400,300" />
	
	
	
	<!-- ace styles -->
	
	<link rel="stylesheet" href="<utils:resourceUrl path="/css/ace.min.css" />" />
	<link rel="stylesheet" href="<utils:resourceUrl path="/css/ace-rtl.min.css" />" />
	<link rel="stylesheet" href="<utils:resourceUrl path="/css/ace-skins.min.css" />" />
	<link rel="stylesheet" href="<utils:resourceUrl path="/css/common.css" />"  />
	<!--[if lte IE 8]>
	<link rel="stylesheet" href="<utils:resourceUrl path="/css/ace-ie.min.css" />" />
	<![endif]-->
	
	<!-- inline styles related to this page -->
	
	<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
	
	<!--[if lt IE 9]>
	<script src="<utils:resourceUrl path="/js/html5shiv.js" />"></script>
	<script src="<utils:resourceUrl path="/js/respond.min.js" />"></script>
	<![endif]-->
	
    <layout:block name="stylesheets"></layout:block>
</head>
<body class="${bodyCssClass}">

<layout:block name="body"></layout:block>

<script src="<utils:resourceUrl path="/js/jquery-2.0.3.min.js" />"></script>

<!-- <![endif]-->

<!--[if IE]>
<script src="<utils:resourceUrl path="/js/jquery-1.10.2.min.js" />"></script>
<![endif]-->

<!--[if !IE]> -->

<script type="text/javascript">
    window.jQuery || document.write('<script src="<utils:resourceUrl path="/js/jquery-2.0.3.min.js" />"'+"<"+"/script>");
</script>

<!-- <![endif]-->

<!--[if IE]>
<script type="text/javascript">
    window.jQuery || document.write('<script src="<utils:resourceUrl path="/js/jquery-1.10.2.min.js" />"'+"<"+"/script>");
</script>
<![endif]-->
<script src="<utils:resourceUrl path="/js/jquery.hotkeys.min.js" />"></script>
<script src="<utils:resourceUrl path="/js/bootstrap.min.js" />"></script>
<script src="<utils:resourceUrl path="/js/bootstrap-wysiwyg.min.js" />"></script>
<script src="<utils:resourceUrl path="/js/typeahead-bs2.min.js" />"></script>
<script src="<utils:resourceUrl path="/js/jquery.colorbox-min.js" />"></script>
<script src="<utils:resourceUrl path="/js/common.js" />"></script>

<layout:block name="javascripts"></layout:block>
</body>
</html>