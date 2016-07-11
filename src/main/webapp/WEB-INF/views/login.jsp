
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page buffer="100kb" autoFlush="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta content="width=device-width, initial-scale=1" name="viewport">
<link type="text/css" rel="stylesheet" href="static/css/style.css" />
<script type="text/javascript" src="cordova.js"></script>


<style>
input, select {
	width: 100%;
	padding: 12px 20px;
	margin: 8px 0;
	display: inline-block;
	border: 1px solid #ccc;
	border-radius: 4px;
	box-sizing: border-box;
}

input[type=submit] {
	width: 100%;
	background-color: #4CAF50;
	color: white;
	padding: 14px 20px;
	margin: 8px 0;
	border: none;
	border-radius: 4px;
	cursor: pointer;
}

input[type=submit]:hover {
	background-color: #423020;
	font-size: large;
}
</style>
</head>
<body bgcolor="green">

	<c:if test="${not empty error}">
		<div align="center" style="color: red; font-weight: bold;">${error}</div>
	</c:if>
	<c:if test="${not empty msg}">
		<div class="msg">${msg}</div>
	</c:if>
	<div align="center">
		<form name='loginForm' action="<c:url value='/login' />" method='POST'>
			<div class="wrapper">
				<div class="newLogo">
					<div class="newLogoCon">
						<img src="static/images/school.jpg" alt="Logo" />
					</div>
				</div>
				<div class="login">
					<div>
						<input type="text" id="username" name="username"
							placeholder="Enter UserName">
					</div>
					<div>
						<input type="password" name="password" placeholder="Password" />
					</div>
					<a href="/forgotpassword" class="link">Forgot Password?</a>
					<div>
						<input class="btn" name="submit" type="submit" value="Sign in" />
					</div>


					<div class="btn" id="signup_btn"
						onclick="window.location.href = 'signup'">Sign up</div>
				</div>
			</div>
		</form>
	</div>
	<!-- 	<script type="text/javascript" src="js/jquery.min.js"></script> -->
	<!-- 	<script type="text/javascript" src="js/app.js"></script> -->
	<script type="text/javascript">
		initLogin();
	</script>
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>
