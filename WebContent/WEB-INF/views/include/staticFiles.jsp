<%@ page contentType="text/html; charset=utf-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="i18n/header" />
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="robots" content="index, follow">
    
    <title><fmt:message key="TITLE"/></title>
	
	<link rel="stylesheet" href="<c:url value='/css/default.css'/>">
	
	<!-- Favicon -->
	<link rel="icon" href="data:;base64,iVBORw0KGgo=">
    
    <!-- Essential styles -->
    <link rel="stylesheet" href="<c:url value='/assets/bootstrap/css/bootstrap.min.css'/>">
    <link rel="stylesheet" href="<c:url value='/font-awesome/css/font-awesome.min.css'/>"> 
    <link rel="stylesheet" href="<c:url value='/css/jquery.fancybox.css?v=2.1.5" media="screen'/>"> 

    <!-- Boomerang styles -->
    <link id="wpStylesheet" rel="stylesheet" type="text/css" href="<c:url value='/css/global-style.css'/>" media="screen">  
    <link rel="stylesheet" type="text/css" href="<c:url value='/css/side-menu.css'/>" media="screen">  

    <script src="//code.jquery.com/jquery-1.11.3.min.js"></script>
    <script src="<c:url value='/js/jquery-ui.min.js'/>"></script>

	<!-- Essentials -->

	<script async src="<c:url value='/js/jquery.MultiFile.js'/>"></script> 
	<script async src="<c:url value='/assets/bootstrap/js/bootstrap.min.js'/>"></script>
	<script async src="<c:url value='/js/jquery.hoverup.js'/>"></script>
	<script async src="<c:url value='/js/jquery.hoverdir.js'/>"></script>
	<script async src="<c:url value='/js/jquery.stellar.js'/>"></script>

</head>