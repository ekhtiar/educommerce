<%@page import="educommerce.model.dto.CustomerObject"%>
<%@page import="educommerce.model.CustomerClass"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>ACME Web Shop - Login</title>
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/font-awesome.min.css" rel="stylesheet">
    <link href="css/prettyPhoto.css" rel="stylesheet">
    <link href="css/price-range.css" rel="stylesheet">
    <link href="css/animate.css" rel="stylesheet">
	<link href="css/main.css" rel="stylesheet">
	<link href="css/responsive.css" rel="stylesheet">
    <!--[if lt IE 9]>
    <script src="js/html5shiv.js"></script>
    <script src="js/respond.min.js"></script>
    <![endif]-->       
    <link rel="shortcut icon" href="images/ico/favicon.ico">
    <link rel="apple-touch-icon-precomposed" sizes="144x144" href="images/ico/apple-touch-icon-144-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="114x114" href="images/ico/apple-touch-icon-114-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="72x72" href="images/ico/apple-touch-icon-72-precomposed.png">
    <link rel="apple-touch-icon-precomposed" href="images/ico/apple-touch-icon-57-precomposed.png">
</head><!--/head-->

<body>
	<%@include file ="page_includes/header.jsp" %>
	
	<section id="form"><!--form-->
				<% 
				if(customerSession.getAttribute("email")!=null){
				    CustomerObject customerObject = CustomerClass.getCustomer((String)customerSession.getAttribute("email"));   
				%>
		
				<div class="col-sm-4 col-sm-offset-1">
					<div class="login-form"><!--login form-->
						<h2>Update account information</h2>
						<form action="#">
							<input type="text" value = "<%=customerObject.getFirstName() %>" placeholder="First Name" />
							<input type="text" <%if (customerObject.getMiddleName() != null){ %>value = "<%=customerObject.getMiddleName() %>" <%} %> placeholder="Middle Name" />
							<input type="text" value = "<%=customerObject.getLastName() %>" placeholder="Last Name" />
							<input type="text" value = "<%=customerObject.getEmailAddress() %>" placeholder="E-mail Address" />
							<input type="text" <%if (customerObject.getPhoneNumber() != null){ %>value = "<%=customerObject.getPhoneNumber() %>" <%} %> placeholder="Phone Number" />
							<input type="password" placeholder="Password" />
							<button type="submit" class="btn btn-default">Update</button>
						</form>
					</div><!--/login form-->
				</div>
				
				</div>
				<div class="col-sm-1">
					<h2 class="or">OR</h2>
				</div>
				<div class="col-sm-4">
					<div class="signup-form"><!--sign up form-->
						<h2>Update shipping information</h2>
						<form action="#">
							<input type="text" <%if (customerObject.getAddress1() != null){ %>value = "<%=customerObject.getAddress1() %>" <%} %> placeholder="House Number - Street Name"/>
							<input type="text" <%if (customerObject.getAddress2() != null){ %>value = "<%=customerObject.getAddress2() %>" <%} %> placeholder="Address Line 2"/>
							<input type="text" <%if (customerObject.getCity() != null){ %>value = "<%=customerObject.getCity() %>" <%} %> placeholder="City, State"/>
							<input type="text" <%if (customerObject.getZipCode() != null){ %>value = "<%=customerObject.getZipCode() %>"<%} %> placeholder="Zip Code"/>
							<select>
							<option>Germany</option>
							</select>
							<br/><br/>
							<button type="submit" class="btn btn-default">Update</button>
						</form>
					</div><!--/sign up form-->
				</div>
				<%} %>
		
	</section><!--/form-->
	
	
	
	

  
    <script src="js/jquery.js"></script>
	<script src="js/price-range.js"></script>
    <script src="js/jquery.scrollUp.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
    <script src="js/jquery.prettyPhoto.js"></script>
    <script src="js/main.js"></script>
</body>
</html>

</body>
</html>