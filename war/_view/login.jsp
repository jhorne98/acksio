<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>

	<head>

		<meta name="author" content="jhorne@ycp.edu">
		<meta name="desc" content="ACKSIO dispatch draft updated 3/6/18">

		<title>Acksio: Dispatcher Solutions</title>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/_view/default.css" title="default"/>
	</head>

	<body>
		
		<div id=header>
			<h2>ACKSIO</h2>
		</div>

		

		<div id=content>
			<c:if test="${! empty errorMessage}">
				<div class="error">${errorMessage}</div><br>
			</c:if>

			<form action="${pageContext.servletContext.contextPath}/login" method="post">

				Login:<br>
				<input type="text" name="username" value="${model.username}">
				<br>

				Password:<br>
				<input type="password" name="password" value="${model.password}">
				<br><br>
				
				<input class=button type="submit" name="submit" value="Log in">
				<input class=button type="submit" name="signup" value="Sign up">
			</form>
		</div>

		<!--
		<div id=link>

			<a href='GoogleMapsTest.html'>GoogleMaps</a>
		</div>
		-->
	</body>
</html>