
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page buffer="100kb" autoFlush="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta content="width=device-width, initial-scale=1" name="viewport">
<link type="text/css" rel="stylesheet" href="css/style.css" />
<script type="text/javascript" src="cordova.js"></script>
</head>
<body>

	<c:if test="${not empty error}">
		<div class="error">${error}</div>
	</c:if>
	<c:if test="${not empty msg}">
		<div class="msg">${msg}</div>
	</c:if>
	
	<form name='loginForm' action="<c:url value='/login' />" method='POST'>
		<div class="wrapper">
			<div class="newLogo">
				<div class="newLogoCon">
					<img src="images/logo.png" alt="Logo" />
				</div>
			</div>
			<div class="login">
				<div class="textBox">
					<input type="text" name="username"
						placeholder="User name or Mobile No." />
				</div>
				<div class="textBox">
					<input type="password" name="password" placeholder="Password" />
				</div>
				<a href="forgotpassword.html" class="link">Forgot Password?</a>
				<div class="btn"><input class="btn" name="submit" type="submit" value="Sign in" /></div>
				
				<div class="btn" id="signup_btn"
					onclick="window.location.href = 'signup.html'">Sign up</div>
			</div>
		</div>
	</form>
	<!-- 	<script type="text/javascript" src="js/jquery.min.js"></script> -->
	<!-- 	<script type="text/javascript" src="js/app.js"></script> -->
	<script type="text/javascript">
		initLogin();
	</script>

</body>
</html>
