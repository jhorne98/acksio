<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!-- JS and gogole maps refrences are based off of two tutorials: https://www.youtube.com/watch?v=lusad8WirIc and https://www.youtube.com/watch?v=Zxf1mnP5zcw --->

<html>

	<head>
		<meta name="viewport" content="initial-scale=1.0, user-scalable=no"/>
		<meta http-equiv="X-UA-Compatible" content="ie=edge">

		<meta name="author" content="jhorne@ycp.edu">
		<meta name="desc" content="ACKSIO dispatch draft updated 3/6/18">

		<title>Acksio: Dispatcher Solutions</title>
		<script type="text/javascript" src="https://maps.google.com/maps/api/js?sensor=false"></script>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/_view/default.css" title="default"/>

		<script type="text/javascript">




		 	function getXmlHttpRequestObject2() {
		  
			    if (window.XMLHttpRequest) {
			    	return new XMLHttpRequest(); //To support the browsers IE7+, Firefox, Chrome, Opera, Safari
			    }
			    else if(window.ActiveXObject) {
			    	return new ActiveXObject("Microsoft.XMLHTTP"); // For the browsers IE6, IE5
			    }
			    else {
			    	alert("Error due to old verion of browser upgrade your browser");
			    }
		    }



		    var rcvReq2 = getXmlHttpRequestObject2();
		    



		    function alterContent2() {
		    if (rcvReq2.readyState == 4 || rcvReq2.readyState == 0) {


		    rcvReq2.open("GET", 'dispatcher', true);
		    rcvReq2.onreadystatechange = handleAlterContent2;
		    rcvReq2.send(null);
		    }
		    }
		    



		    function handleAlterContent2() {
		    if (rcvReq2.readyState == 4) {
		   
			var responseTextVar = rcvReq2.responseText;


			var item=responseTextVar.split(',');
			var lat =item[0];
			var lng =item[1];
		       



		var directionDisplay;
		var directionsService = new google.maps.DirectionsService();
		var map;
		
		function initialize() {
			directionsDisplay = new google.maps.DirectionsRenderer();
			//var York = new google.maps.LatLng(39.9626, -76.7277);

			var York = new google.maps.LatLng(lat, long); 			
			var myOptions = {
				zoom:12,
				mapTypeId: google.maps.MapTypeId.ROADMAP,
				center: York
			}

			map = new google.maps.Map(document.getElementById("map_canvas"), myOptions);
			directionsDisplay.setMap(map);
		}
		
		function calcRoute() {
			var start = document.getElementById("start").value;
			var end = document.getElementById("end").value; 
			var distanceInput = document.getElementById("distance");

			
			
			var request = {
				origin:start, 
				destination:end,
				travelMode: google.maps.DirectionsTravelMode.DRIVING
				
			};
			
			directionsService.route(request, function(response, status) {
				if (status == google.maps.DirectionsStatus.OK) {
					directionsDisplay.setDirections(response);
					distanceInput.value = response.routes[0].legs[0].distance.value / 1000;
				}
			});
		}
		</script>
	</head>

	<body onload="initialize()">
		<c:if test="${! empty errorMessage}">
			<div class="error">${errorMessage}</div>
		</c:if>
		
		<div id=header>
			<h2>ACKSIO</h2>
			
			<form action="${pageContext.servletContext.contextPath}/dispatcher" method="post">
				<input class=logout_button type="submit" name="logout" value="Log out">
			</form>
		</div>

		<div id=content>
			Welcome to Acksio, ${name}. 
			<br><br>
			
			<div id=courierinfo>
				You are in charge of the following couriers: 
				<br>
				
				<div id=courierinfo>
					<c:forEach var="loopCourier" items="${courierList}">
						${loopCourier.courierID}, ${loopCourier.name}
						<br>
						Amount due: ${loopCourier.balance} across ${loopCourier.unpaidJobs} unpaid jobs with ${loopCourier.unapprovedJobs} jobs still to be approved.
						<br><br>
					</c:forEach>
				</div>
				<form action="${pageContext.servletContext.contextPath}/dispatcher" method="post">
					Select a courier: <br>
					<select name="courierSelection">
						<c:forEach var="loopCourier" items="${courierList}">
							<option>${loopCourier.courierID}</option>
						</c:forEach>
					</select>
					<br>
					Select an action:
					<input class=button type="submit" name="examineCourier" value="Examine Courier">
					<input class=button type="submit" name="payCourier" value="Pay Courier">
				</form>
				<hr>
			</div>
			
			<div id=jobinfo>
				You queued the following jobs: 
				<br>
				<div id=vehicles>
					<c:forEach var="loopJob" items="${jobList}" varStatus="jobs">
						Job #${loopJob.jobID}, invoice is 
						<c:if test="${loopJob.approved}">
							approved, 
							courierPaid = ${loopJob.courierPaid}
						</c:if>
						<c:if test="${!loopJob.approved}">
							unapproved
						</c:if>
						<br><br>
					</c:forEach>
				</div>
				<form action="${pageContext.servletContext.contextPath}/dispatcher" method="post">
					Select a job: <br>
					<select name="jobSelection">
						<c:forEach var="loopJob" items="${jobList}" varStatus="jobs">
							<option>${loopJob.jobID}</option>
						</c:forEach>
					</select>
					<br>
					Select an action:
					<input class=button type="submit" name="examineJob" value="Examine Job">
					<input class=button type="submit" name="payJob" value="Pay Job">
				</form>
				<br><hr>
			</div>
			
			<form action="${pageContext.servletContext.contextPath}/dispatcher" method="post">
				<input class=button type="submit" name="createJob" value="Create Job"><br><br>
				<input class=button type="submit" name="edit" value="Edit Information">
			</form>
		</div>

		
		
		
		<!-- moved all of create job function to separate jsp -->
		<!-- <div>
				

				<label for="start">Pickup Address : </label>
				<input type="text" name="start" id="start" />
				
				<label for="end">Delivery Location : </label>
				<input type="text" name="end" id="end" />
				
				<input type="submit" value="Calculate Route" onclick="calcRoute()" />

				
				<p><label for="distance">Distance Estimate: </label>
				<input type="text" name="distance" id="distance" readonly="true" value = "${model.distance}"/></p>

				<p><label for="payment">Payment Estimate : </label>
				<input type="text" name="payment" id="payment" readonly="true" value = "${model.payment}"/></p>

				

				<input type="submit" value="Create Job">



				
				<p>${model.vehicleType}</p>
				
				<input type="submit" name="edit" value="Edit Information">
		</div>
		
		
		<div id="map_canvas"></div>
		-->
	</body>
</html>
