<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:url var="resources" value="/resources/" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script src="${resources}javascript/myScripts.js" type="text/javascript"></script>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.0/jquery.min.js" type="text/javascript" charset="utf-8"></script>

<script src="http://code.jquery.com/jquery-1.8.2.min.js"></script>
<script src="${resources}js/bootstrap-transition.js"></script>
<script src="${resources}js/bootstrap-alert.js"></script>
<script src="${resources}js/bootstrap-modal.js"></script>
<script src="${resources}js/bootstrap-dropdown.js"></script>
<script src="${resources}js/bootstrap-scrollspy.js"></script>
<script src="${resources}js/bootstrap-tab.js"></script>
<script src="${resources}js/bootstrap-tooltip.js"></script>
<script src="${resources}js/bootstrap-popover.js"></script>
<script src="${resources}js/bootstrap-button.js"></script>
<script src="${resources}js/bootstrap-collapse.js"></script>
<script src="${resources}js/bootstrap-carousel.js"></script>
<script src="${resources}js/bootstrap-typeahead.js"></script>

<link href="${resources}css/basic.css" rel="stylesheet">
<link href="${resources}css/bootstrap.css" rel="stylesheet">
<link href="${resources}css/bootstrap.css" rel="stylesheet">
<link href="${resources}css/bootstrap-responsive.css" rel="stylesheet">

<title><tiles:insertAttribute name="title" /></title>
</head>
<body>
	<!-- Use the /surprise link -->
	<tiles:insertAttribute name="navbar" />
	<tiles:insertAttribute name="body" />
	<tiles:insertAttribute name="footer" />
</body>
</html>