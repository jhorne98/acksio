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
	
	<div class="index_title">
		<h3>Acksio</h3>
	</div>
	
		<form class="login_button" action="${pageContext.servletContext.contextPath}/index" method="post">
			<input type="Submit" name="login" value="Log In">
			<input type="Submit" name="dispatcher" value="Dispatcher">
			<input type="Submit" name="courier" value="Courier">
			<input type="Submit" name="createJob" value="Create Job">
		</form>
	
	</body>
</html>
