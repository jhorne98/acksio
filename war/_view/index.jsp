<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>



<html>
	<head>
		<title>Index</title>
		<link rel="stylesheet" type="text/css" href="default.css"/>
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
        <img class="info_left" src="images/nav.jpg"></img>
        <p class="info_right">Test paragraph</p>
      </div>
    </div>
    
    <div class="col-xs-6 blue_info">
      <div class="well">
        <p class="info_left">Test paragraph</p>
         <img class="info_right" src="images/nav.jpg"></img>
      </div>
    </div>
    
    <div class="col-xs-6 purple_info">
      <div class="well">
        <img class="info_left" src="images/nav.jpg"></img>
        <p class="info_right">Test paragraph</p>
      </div>
    </div>
    
    <div class="col-xs-6 white_info">
      <div class="well">
        <p class="info_left">Test paragraph</p>
         <img class="info_right" src="images/nav.jpg"></img>
      </div>
    </div>
    
  </div>
	
	</body>
</html>
