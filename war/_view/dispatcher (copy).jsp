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

		var xhttp = new XMLHttpRequest();
xhttp.onreadystatechange = function() {
    if (this.readyState == 4 && this.status == 200) {
       // Typical action to be performed when the document is ready:
       document.getElementById("demo").innerHTML = xhttp.responseText;
    }
};
xhttp.open("GET", "filename", true);
xhttp.send();

		var directionDisplay;
		var directionsService = new google.maps.DirectionsService();
		var map;
		
		function initialize() {
			directionsDisplay = new google.maps.DirectionsRenderer();
			var York = new google.maps.LatLng(39.9626, -76.7277);
			var myOptions = {
				zoom:12,
				mapTypeId: google.maps.MapTypeId.ROADMAP,
				center: York
			}

			map = new google.maps.Map(document.getElementById("map_canvas"), myOptions);
			directionsDisplay.setMap(map);
		}
		function test(){
			var number = 2;			
			var test = document.getElementbyId("distance");
			number = number + number;
			return number; 
		}

		function calcRoute() {
			var start = document.getElementById("start").value;
			var end = document.getElementById("end").value;
			var payCof = document.getElementById("payCof").value; 

			var distanceInput = document.getElementById("distance");
			var payment = document.getElementbyId("payment");

			
			
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


			payment = payCof * distanceInput;
		}
		</script>
	</head>

	<body onload="initialize()">
		<c:if test="${! empty errorMessage}">
			<div class="error">${errorMessage}</div>
		</c:if>
		
		<div id=header>

			<!-- testing functionality of topbar, adjust for neatness later-->
			<h2>ACKSIO</h2>
		</div>

		<div id=main_body>
				

			<!-- for generic HTML -->

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

				<!--- <input type=" ---->
				
				<!-- for google maps functions --->				

				<label for="payCof">Payment Coefficient (cost/mile) : </label>
				<input type "text" name="payCof" id="payCof" />
				<br><br>

				<label for="start">Pickup Address : </label>
				<input type="text" name="start" id="start" />
				
				<label for="end">Delivery Location : </label>
				<input type="text" name="end" id="end" />
				
				<input type="submit" value="Calculate Route" onclick= "calcRoute()" />

				
				<p><label for="distance">Distance Estimate: </label>
				<input type="text" name="distance" id="distance" readonly="true" value = "${model.distance}"/></p>

				<p><label for="payment">Payment Estimate : </label>
				<input type="text" name="payment" id="payment" readonly="true" value = "${model.payment}"/></p>

				<input type="submit" value="Create Job">


				<!--- <input type="submit" name="submit" value="Create Job"> --->

				
				<p>${model.vehicleType}</p>
		</div>
		<div id="map_canvas"></div>
	</body>
</html>
