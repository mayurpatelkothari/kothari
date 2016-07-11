<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link type="text/css" rel="stylesheet" href="static/css/style.css" />
<title>Insert title here</title>
</head>
<body>
	<div class="all">
		<jsp:include page="header.jsp"></jsp:include>
		<div class="homePage pt-page pt-current-page" id="home_page">

			<div class="fix">
				<header class="themes">Regisation</header>
			</div>
			<br /><br/><br/>
			<div align="center">
			<font color="red">
			<label for="fname" id="error"></label>
			</font>
			</div>
			<div class="divdata">
				<label for="fname">User Name</label> <input type="text"
					id="username" name="username" placeholder="Enter first name">

				<label for="lname">Password</label> <input type="password"
					id="password" name="password" placeholder="Enter last name">
				<input type="submit" value="Submit" id="regisationData">

				<jsp:include page="footer.jsp"></jsp:include>
				<script type="text/javascript" src="static/js/jquery.min.js"></script>
				<script type="text/javascript" src="static/js/app.js"></script>
				<script type="text/javascript">initRegisation()</script>
			</div>
</body>
</html>