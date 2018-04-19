		
		// This file exists as a temporary fix to a conflict caused by using raw javascript in dispatcher.jsp  

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
