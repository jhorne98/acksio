<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>



<html>
	<head>
		<title>Index</title>
		<link rel="stylesheet" type="text/css" href="default.css"/>
		<link href="https://fonts.googleapis.com/css?family=Lobster" rel="stylesheet" type="text/css">
	</head>
	
	<body>
	<div class="top">
		<div class="index_title">
			<h3>Acksio</h3>
		</div>
	
		<form class="centered shadow" action="${pageContext.servletContext.contextPath}/index" method="post" style="width: 221px; height: 35px">
			<input class="login_button" type="Submit" name="login" value="Log In">
		</form>
	</div>
	
	<div class="black_info">
	
	</div>
	
	<div class="blue_info">
	
	</div>
	
	<div class="white_info">
	
	</div>
	
	<div class="purple_info">
	
	</div>
	
	</body>
</html>
