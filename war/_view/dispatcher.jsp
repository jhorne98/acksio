<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>

	<head>

		<meta name="author" content="jhorne@ycp.edu">
		<meta name="desc" content="ACKSIO dispatch draft updated 3/6/18">

		<title>Acksio: Dispatcher Solutions</title>
		<link rel="stylesheet" type="text/css" href="default.css" title="default"/>
	</head>

	<body>
		<c:if test="${! empty errorMessage}">
			<div class="error">${errorMessage}</div>
		</c:if>
		
		<div id=header>

			<!-- testing functionality of topbar, adjust for neatness later-->
			<h2>ACKSIO</h2>
		</div>

		<div id=main_body>
				
			<form action="${pageContext.servletContext.contextPath}/dispatcher" method="post">
				
				Vehicle Type<br>
				<select name="vehicleType">
					<option value="${model.vehicleType}">Class C Automobile</option>
				</select>
				<br><br>

				<input type="checkbox" name="tsaCertified" value="${model.tsaCert}"> TSA certified driver needed<br><br>

				<input type="submit" name="submit" value="Create Job">
			</form>	
		</div>

		<!--
		<div id=link>

			<a href='GoogleMapsTest.html'>GoogleMaps</a>
		</div>
		-->
	</body>
</html>
