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
		<c:if test="${! empty errorMessage}">
			<div class="error">${errorMessage}</div>
		</c:if>
		
		<div id=header>

			<!-- testing functionality of topbar, adjust for neatness later-->
			<h2>ACKSIO</h2>
		</div>

		<div id=main_body>
		
			Welcome to Acksio, ${model.name}. 
			<br><br>
				
			Target Address: 
				<input type="text" name="destinationAddress" value="${model.address}" method="post">
				<br><br>
				
				Recipient Name: <input type = "text" name="recipientName" value="${model.name}" method="post">
				<br><br>
				
				Recipient Phone #: <input type = "text" name="recipientPhone" value="${model.phone}" method="post">
				<br><br>
				
				Vehicle Type<br>
				<select name="vehicleType">
					<option value="car">Class C Automobile</option>
				</select>
				<br><br>

				<input type="checkbox" name="tsaCertified" value="${model.tsaCert}"> TSA certified driver needed<br><br>

				<input type="submit" name="submit" value="Create Job">
				
				<p>${model.vehicleType}</p>
		</div>

		<div id=main_body>
			You are in charge of the following couriers: 
			<br><br>
			
			
		</div>

		<!--
		<div id=link>

			<a href='GoogleMapsTest.html'>GoogleMaps</a>
		</div>
		-->
	</body>
</html>
