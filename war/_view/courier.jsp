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
			
			<form action="${pageContext.servletContext.contextPath}/courier" method="post">
				<input class=logout_button type="submit" name="logout" value="Log out">
			</form>
		</div>
		
		<c:if test="${! empty errorMessage}">
			<div class="error">${errorMessage}</div>
		</c:if>

		<div id=content>
			<p>Welcome, ${name}</p>
		
			<form action="${pageContext.servletContext.contextPath}/courier" method="post">
				<input class=button type="submit" name="edit" value="Edit Information"><br><br>
				<input class=button type="submit" name="insertvehicle" value="Add a Vehicle"><br>
			</form>
			
			<br>You are currently:
			<div id=avail>
				<c:if test="${available == 0}">
					NOT
				</c:if>AVAILABLE
			</div><br>
			
			<form action="${pageContext.servletContext.contextPath}/courier" method="post">
				<input class=button type="submit" name="availablebutton" value="${availablestring}"><br>
			</form>
		</div>
		
		<div id=couriervehicles>
			<p>Your Vehicles</p>
		
			<c:forEach var="vehicleloop" items="${loop}" varStatus="loop">
				${vehicleloop.licensePlate}, ${vehicleloop.year}, ${vehicleloop.make}, ${vehicleloop.model}, ${vehicleloop.type},
				<c:if test="${vehicleloop.active == 0}">
					NOT 
				</c:if>ACTIVE
				<form action="${pageContext.servletContext.contextPath}/courier" method="post">
					<input class=button type="submit" name="toggle${loop.index}" value="Toggle Active">
					<input class=button type="submit" name="delete${loop.index}" value="Delete Vehicle">
				</form><br>
			</c:forEach>
			
		</div>

		<!--
		<div id=link>

			<a href='GoogleMapsTest.html'>GoogleMaps</a>
		</div>
		-->
	</body>
</html>
