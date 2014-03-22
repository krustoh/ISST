<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/jsp_layout" prefix="layout"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/resources/admin/";
%>
<!DOCTYPE html>
<html>
<head>
    <base href="<%=basePath%>" />
    <meta charset="utf-8">
    <title>
    <layout:block name="title"></layout:block>
    </title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <layout:block name="head"></layout:block>
    
    <link href="css/bootstrap.min.css" rel="stylesheet" />
	<link rel="stylesheet" href="css/font-awesome.min.css"  />
	
	<!--[if IE 7]>
	<link rel="stylesheet" href="css/font-awesome-ie7.min.css" />
	<![endif]-->
	
	<!-- page specific plugin styles -->
	
	<!-- fonts -->
	
	<link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Open+Sans:400,300" />
	
	<!-- ace styles -->
	
	<link rel="stylesheet" href="css/ace.min.css" />
	<link rel="stylesheet" href="css/ace-rtl.min.css" />
	<link rel="stylesheet" href="css/ace-skins.min.css" />
	<link rel="stylesheet" href="css/common.css"  />
	<!--[if lte IE 8]>
	<link rel="stylesheet" href="css/ace-ie.min.css" />
	<![endif]-->
	
	<!-- inline styles related to this page -->
	
	<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
	
	<!--[if lt IE 9]>
	<script src="js/html5shiv.js"></script>
	<script src="js/respond.min.js"></script>
	<![endif]-->
	
    <layout:block name="stylesheets"></layout:block>
</head>
<body class="${bodyCssClass}">

<layout:block name="body"></layout:block>

<script src="js/jquery-2.0.3.min.js"></script>

<!-- <![endif]-->

<!--[if IE]>
<script src="js/jquery-1.10.2.min.js"></script>
<![endif]-->

<!--[if !IE]> -->

<script type="text/javascript">
    window.jQuery || document.write("<script src='js/jquery-2.0.3.min.js'>"+"<"+"/script>");
</script>

<!-- <![endif]-->

<!--[if IE]>
<script type="text/javascript">
    window.jQuery || document.write("<script src='js/jquery-1.10.2.min.js'>"+"<"+"/script>");
</script>
<![endif]-->

<script src="js/common.js"></script>

<layout:block name="javascripts"></layout:block>
</body>
</html>