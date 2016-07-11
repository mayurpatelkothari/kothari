<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>home</title>
<link type="text/css" rel="stylesheet" href="static/css/style.css" />

</head>
<body>
<div class="all">
	<jsp:include page="header.jsp"></jsp:include>
	<div class="homePage pt-page pt-current-page" id="home_page">
	
		<div class="fix">
			<header class="themes">DashBoard</header>
		</div>
		<div class="absolute noBottom">
			<div class="home">
				<div class="dashBoard">
					<ul>
						<li>
							<p data-page="new_page">
								<i class="icon icon-book"></i> <b>Birth Register</b>
							</p>
						</li>
						<!-- 	<li>
							<p>
								<i class="icon icon-users"></i> <b>Group</b>
							</p>
						</li>
						<li>
							<p>
								<i class="icon icon-envelope-o"></i> <b>SMS</b>
							</p>
						</li> -->
						<li>
							<p data-page="setting">
								<i class="icon icon-cog"></i> <b>Setting</b>
							</p>
						</li>
						<li>
							<p>
								<i class="icon icon-power-off"></i> <b>Log Out</b>
							</p>
						</li>
					</ul>
					<div class="clr"></div>
				</div>
			</div>
		</div>
	</div>
	<div id="new_page" class="birthfrom pt-page">
	<div class="fix">
			<header class="themes">Birth Register From</header>
		</div>
		<br/><br/>
		    <input type="submit" value="View" id="showdata">
		
	<br/>
	
	
	<div class="divdata">
    <label for="fname">First Name</label>
    <input type="text" id="firstname" name="firstname" placeholder="Enter first name">

    <label for="lname">Last Name</label>
    <input type="text" id="lastname" name="lastname" placeholder= "Enter last name">

    <label for="country">Gender</label>
    <select id="gender" name="gender">
      <option value="male">Male</option>
      <option value="female">Female</option>
    </select>
  <label for="fname">Birthday</label>
  <input type="date" id="dateofbirth" name="dateofbirth">
  
  <label for="fname">Father Name</label>
    <input type="text" id="nameoffather" name="nameoffather" placeholder="Enter father name">
    
     <label for="fname">Mother Name</label>
    <input type="text" id="nameofmother" name="nameofmother" placeholder="Enter mouther name">
    
    <label for="fname">Birth palce</label>
    <input type="text" id="birthpalce" name="birthpalce" placeholder="Enter birth palce">
    
     <label for="fname">Parmanent Address</label>
    <textarea rows="4" cols="50" id="parmanentaddress" name="parmanentaddress" placeholder="Enter parmanent Address">
    </textarea>
     <label for="fname">Registratio No</label>
    <input type="text" id="registrationno" name="registrationno" placeholder="Enter registration number">
    <label for="fname">Remarks</label>
    <textarea rows="4" cols="50" id="remarks" name="remarks">
    
    </textarea>
  <label for="fname">Date of Registration</label>
  <input type="text" id="dateofregistration" name="dateofregistration" placeholder="Enter Date of registration">
  
   <label for="fname">Zone</label>
    <input type="text" id="zone" name="zone" placeholder="Enter zone">
    
    <label for="fname">ward</label>
    <input type="text" id="ward" name="ward" placeholder="Enetr ward"> 
    
      <label for="fname">birthmonth</label>
    <input type="text" id="birthmonth" name="birthmonth">
    
    <label for="fname">Sun</label>
    <input type="text" id="sun" name="sun" placeholder="Enter sun">
    <input type="submit" value="Submit" id="submitdata">
  <jsp:include page="footer.jsp"></jsp:include>
</div>


</div>
<div id="setting">

   <input type="password" name="oldpassword" placeholder="Old Password" />
   <input type="password" name="newpassword" placeholder="New Password" />
   <input type="submit" value="Changes Password" id="chnagedpassword">
</div>
	<jsp:include page="footer.jsp"></jsp:include>
	<script type="text/javascript" src="static/js/jquery.min.js"></script>
	<script type="text/javascript" src="static/js/app.js"></script>
	<script type="text/javascript">initSubmit()</script>
	</div>
	  
</body>
</html>