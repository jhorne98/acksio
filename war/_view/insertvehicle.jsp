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
			
			<form action="${pageContext.servletContext.contextPath}/courier" method="post">
				<input type="submit" name="logout" value="Log out">
			</form>
		</div>

		<c:if test="${! empty successfulUpdate}">
			<div class="output">${successfulUpdate}</div>
		</c:if>

		<c:if test="${! empty errorMessage}">
			<div class="error">${errorMessage}</div>
		</c:if>

		<div id=content>

			<c:if test="${! empty successfulinsert}">
				<div class="error">${successfulinsert}</div>
			</c:if>

			<form action="${pageContext.servletContext.contextPath}/insertvehicle" method="post">

				License Plate Number:<br>
				<input type="text" name="licenseplate">
				<br><br>

				Model Year:<br>
				<input type="text" name="year">
				<br><br>
				
				Manufacturer (Make):<br>
				<input type="text" name="make">
				<br><br>
				
				Model:<br>
				<input type="text" name="model">
				<br><br>
				
				Type (REQUIRED):<br>
				<select name="type">
					<option value="BICYCLE">Bicycle</option>
					<option value="MOTORCYCLE">Motorcycle</option>
					<option value="CAR">Car</option>
					<option value="SUV">SUV</option>
					<option value="VAN">Van</option>
					<option value="PICKUP">Pickup Truck</option>
					<option value="SPRINTER">Sprinter</option>
					<option value="SEMI">Semi Truck</option>
				</select><br><br>
				
				<input type="submit" name="submit" value="Insert new Vehicle">
				<input type="submit" name="back" value="Back">
			</form>

		</div>

		<!--
		<div id=link>

			<a href='GoogleMapsTest.html'>GoogleMaps</a>
		</div>
		-->
	</body>
</html>