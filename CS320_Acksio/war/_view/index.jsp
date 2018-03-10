<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
	<head>
		<title>Index</title>
	</head>

	<body>
		<form action="${pageContext.servletContext.contextPath}/index" method="post">
			<input type="Submit" name="mult" value="Multiply Numbers!">
			<input type="Submit" name="add" value="Add Numbers!">
			<input type="Submit" name="guess" value="Guessing Numbers!">
		</form>
	</body>
</html>
