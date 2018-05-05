<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!-- JS and gogole maps refrences are based off of two tutorials: https://www.youtube.com/watch?v=lusad8WirIc and https://www.youtube.com/watch?v=Zxf1mnP5zcw --->

<html>

  <head>
	<title>Acksio: Dispatcher Solutions</title>
 	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/_view/default.css" title="default"/> 
	
	 <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAAEj70zhdvnQtL5lctSrv4wJUWUOxY3cw&callback=initMap" type="text/javascript">
    </script>

  </head>
  <body>
    <div id=header>

			<!-- testing functionality of topbar, adjust for neatness later-->
			<h2>ACKSIO</h2>
		</div>

		<c:if test="${! empty successfulUpdate}">
			<div class="output">${successfulUpdate}</div>
		</c:if>

		<c:if test="${! empty errorMessage}">
			<div class="error">${errorMessage}</div>
		</c:if>
	</div>


	<div id='map-canvas'></div>
	
	<form action="${pageContext.servletContext.contextPath}/createJob" method="post">	
	
	<br><br>
	
	Recipient Name: <input type = "text" name="name" value="${model.name}" method="post">
	<br><br>
				
	Recipient Phone #: <input type = "text" name="phone" value="${model.phone}" method="post">
	<br><br>

	Payment per Mile: <input type="text" name="payCof" id="payCof" value="${model.payment}" method="post">	
	<br><br>
				
	Vehicle Type<br>
	<select name="vehicleType">
		<option value="car">Class C Automobile</option>
	</select>
	<br><br>

	<input type="checkbox" name="tsaCertified" value="${model.tsaCert}"> TSA certified driver needed<br><br>
			
	<p>${model.vehicleType}</p>

	Pickup Address: <input type = "text" name="start" id="start" value="${model.start}" mehod="post">
	<br><br>

	Target Address: <input type = "text" name="address" id="end" value="${model.address}" method="post">
	<br><br>

	Distance: <input type="text" name="distance" id="distance" readonly="true" value="${model.distance}" method="post">
	<br><br>

	

	<input type="submit" value="Create Job">

	</form>
	
	<button id="get">Calculate Route</button>	

	<script>
		var directionsDisplay = new google.maps.DirectionsRenderer();
		var directionsService = new google.maps.DirectionsService();
		

		var start = document.getElementById("start").value;
		var end = document.getElementById("end").value;
		var payCof = document.getElementById("payCof").value;
		var distanceInput = document.getElementById("distance");


		var map; 

		var york = new google.maps.LatLng(39.9626, -76.7277);
		var dover = new google.maps.LatLng(40.0018, -76.8502);

		var mapOptions = {
			zoom: 14,
			center: york
		};

		map = new google.maps.Map(document.getElementById('map-canvas'), mapOptions);
		
		directionsDisplay.setMap(map);

		function calcRoute(){
			var request = {
				origin: start,
				destination: end,
				travelMode: 'DRIVING'
			};
			
			directionsService.route(request, function(result, status){
				//console.log(result, status);
				if (status == "OK"){
					//renders directions
					directionsDisplay.setDirections(result);
					distanceInput.value = (result.routes[0].legs[0].distance.value / 1000) * 0.621371;

				}			
			});
			
		}

		//button click
		document.getElementById('get').onclick=function(){
			calcRoute();
		};
		
	</script>
   
  </body>
</html>
