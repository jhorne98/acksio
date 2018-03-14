<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
	<head>
		<title>Index</title>
	</head>

	<body>
		<form action="${pageContext.servletContext.contextPath}/index" method="post">
			<input type="Submit" name="login" value="Log In">
			<input type="Submit" name="dispatcher" value="Dispatcher">
			<input type="Submit" name="courier" value="Courier">
			<input type="Submit" name="createJob" value="Create Job">
		</form>
	</body>
</html>
