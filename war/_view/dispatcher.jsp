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
		<input name="model" type="hidden" value="${model}" />
		<c:if test="${! empty errorMessage}">
			<div class="error">${errorMessage}</div>
		</c:if>
		
		<div id=header>

			<!-- testing functionality of topbar, adjust for neatness later-->
			<h2>ACKSIO</h2>
		</div>

		<form action="${pageContext.servletContext.contextPath}/dispatcher" method="post">
		
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

				<input type="submit" name="createJob" value="Create Job">
				
				<p>${model.vehicleType}</p>
				
			<form action="${pageContext.servletContext.contextPath}/courier" method="post">
				<input type="submit" name="logout" value="Log out">
			</form>
		</div>

		<div id=main_body>
			You are in charge of the following couriers: 
			<br>
			
			<div id=vehicles>
				<c:forEach var="loopCourier" items="${courierList}">
					${loopCourier.name}, who is courier number: ${loopCourier.courierID}
					<br>
					Amount due: ${loopCourier.balance} across ${loopCourier.unpaidJobs} unpaid jobs with ${loopCourier.unapprovedJobs} jobs still to be approved.
					<br>
				</c:forEach>
			</div>
			<br>
			Select a courier: <br>
			<select name="courierSelection">
				<c:forEach var="loopCourier" items="${courierList}">
					<option>${loopCourier.courierID}</option>
				</c:forEach>
			</select>
			<br>
			Select an action:
			<input type="submit" name="examineCourier" value="Examine Courier">
			<input type="submit" name="payCourier" value="Pay Courier">
		</div>
		<div id=main_body>
			You queued the following jobs: 
			<br>
			<div id=vehicles>
				<c:forEach var="loopJob" items="${jobList}">
					Job #${loopJob.jobID}, invoice is 
					<c:if test="${loopJob.approved}">
						approved, 
						courierPaid = ${loopJob.courierPaid}
					</c:if>
					<c:if test="${!loopJob.approved}">
						unapproved
					</c:if>
					<br>
				</c:forEach>
			</div>
			<br>
			Select a job: <br>
			<select name="jobSelection">
				<c:forEach var="loopJob" items="${jobList}">
					<option>${loopJob.jobID}</option>
				</c:forEach>
			</select>
			<br>
			Select an action:
			<input type="submit" name="examineJob" value="Examine Job">
			<input type="submit" name="payJob" value="Pay Job">
		</div>

		<!--
		<div id=link>

			<a href='GoogleMapsTest.html'>GoogleMaps</a>
		</div>
		-->
	</body>
</html>
