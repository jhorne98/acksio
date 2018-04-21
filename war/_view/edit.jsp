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

			<form action="${pageContext.servletContext.contextPath}/edit" method="post">

				Change your Username:<br>
				<input type="text" name="username" value="${model.username}">
				<br><br>

				Change your Password:<br>
				<input type="text" name="password" value="${model.password}">
				<br><br>
				
				Change your Email:<br>
				<input type="text" name="email" value="${model.email}">
				<br><br>
				
				Change your Name:<br>
				<input type="text" name="name" value="${model.name}">
				<br><br>
				
				<input type="submit" name="submit" value="Update Info">
			</form>
		</div>
		
		<c:if test="${! empty successfulUpdate}">
			<div class="output">${successfulUpdate}</div>
		</c:if>

		<!--
		<div id=link>

			<a href='GoogleMapsTest.html'>GoogleMaps</a>
		</div>
		-->
	</body>
</html>