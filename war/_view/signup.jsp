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

			<!-- testing functionality of topbar, adjust for neatness later-->
			<h2>ACKSIO</h2>
		</div>

		<c:if test="${! empty errorMessage}">
			<div class="error">${errorMessage}</div>
		</c:if>

		<div id=main_body>

			<form action="${pageContext.servletContext.contextPath}/signup" method="post">

				Enter an Email Address:<br>
				<input type="text" name="email" value="${model.email}">
				<br>

				Enter a User Name:<br>
				<input type="text" name="username" value="${model.username}">
				<br>

				Enter a Password:<br>
				<input type="password" name="password" value="${model.password}">
				<br>
				
				Account Type:<br>
				<input type="radio" name="accountType" value="courier"> Courier<br>
				<input type="radio" name="accountType" value="dispatcher"> Dispatcher<br><br>
				
				<input type="submit" name="submit" value="Create Account">
				<input type="submit" name="login" value="Back to Login">
			</form>
		</div>

		<!--
		<div id=link>

			<a href='GoogleMapsTest.html'>GoogleMaps</a>
		</div>
		-->
	</body>
</html>