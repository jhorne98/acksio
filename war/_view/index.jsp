<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>



<html>
	<head>
		<title>Index</title>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/_view/default.css"/>
		<link href="https://fonts.googleapis.com/css?family=Lobster" rel="stylesheet" type="text/css">
	</head>
	
	<body>
	<div class="top">
		<div class="index_title">
			<h3>Acksio</h3>
		</div>
	
		<form class="centered shadow" action="${pageContext.servletContext.contextPath}/index" method="post" style="width: 221px; height: 35px">
			<input class="login_button" type="Submit" name="login" value="Log In">
		</form>
	</div>
	
	<div class="row">
    <div class="col-xs-6 black_info">
      <div class="well">
        <img class="info_left" src="${pageContext.servletContext.contextPath}/_view/images/nav.jpg"></img>
        <p class="info_right">Acksio is an automated courier dispatch service. Using real time location
        tracking, your clients will always get the closest available driver, delivering 
        a superior customer experience. With Acksio, dispatchers can be assured that 
        the right driver is always on the job. </p>
      </div>
    </div>
    
    <div class="col-xs-6 blue_info">
      
    </div>
    
    <div class="col-xs-6 purple_info">
      
    </div>
    
    <div class="col-xs-6 white_info">
      
    </div>
    
  </div>
	
	</body>
</html>
