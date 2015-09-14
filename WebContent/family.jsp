<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>Product Details | Family Car</title>
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
	
		
		<!--header-->
	<!-- the header file contains the all the html and css tags for the header section
	as well as the logic initiating session, and detecting whether a user is logged in
	or not. if an user is logged in we display logout option and if they are not logged
	in we display log in in the top menu from this JSP as well -->
	<jsp:include page="page_includes/header.jsp" />
	<!--header-->
	
	<section>
		<div class="container">
			<div class="row">
				
				
				<div class="col-sm-9 padding-right">
					<div class="product-details"><!--product-details-->
						<div class="col-sm-5">
							<div class="view-product">
								<img src="images/home/products/family_long.jpg" alt="" />
								
							</div>
							

						</div>
						<div class="col-sm-7">
							<div class="product-information"><!--/product-information-->
								<h2>Family Sedan</h2>
								<span>
									<span>US $10,000</span><br/>
									<span><h5>+3,500 for comfort edition</h5></span><br/>
								</span>
								
								<p><b>Engine:</b> 4 cylinder, 2362cc</p>
								<p><b>Transmission:</b> Manual</p>
								<p><b>Features:</b> ABS brakes, Alloy wheels, Central locking,
													Driver airbag, Passenger airbag, Power steering</p>
								
								<br />
								<form action="family_confirm.jsp" method = "get">
								<label>Color:</label>
								<select name ="color">
								  <option value="black">Black</option>
								  <option value="white">White</option>
								  <option value="red">Red</option>
								  <option value="blue">Blue</option>
								</select>
								<br/>
								<label>Edition:</label>
								<select name ="edition">
								  <option value="standard">Standard</option>
								  <option value="comfort">Comfort - With AC</option>
								</select>
								<br/>
								<br/>
								<label>Quantity:</label>
									<input type="text" name="quantity" value="100" />
									<%HttpSession customerSession = request.getSession(); 
									if(customerSession.getAttribute("email") != null)	{%>
									<a href="login.jsp"><button type="submit" class="btn btn-default">Buy Now</button></a>
									<% }%>
								</form>
								<%if(customerSession.getAttribute("email") == null) { %>
									<br />
									<a href="login.jsp">
									<button type="button" class="btn btn-default">Log In</button></a>
									<% }%>
							</div><!--/product-information-->
						</div>
					</div><!--/product-details-->
					
					
					
					
				</div>
			</div>
		</div>
	</section>

		
		<div class="footer-widget">
			<div class="container">
				<div class="row">
					
					
					
					
					
				</div>
			</div>
		</div>
		
		<div class="footer-bottom">
			<div class="container">
				
			</div>
		</div>
		
	</footer><!--/Footer-->
	

  
    <script src="js/jquery.js"></script>
	<script src="js/price-range.js"></script>
    <script src="js/jquery.scrollUp.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
    <script src="js/jquery.prettyPhoto.js"></script>
    <script src="js/main.js"></script>
</body>
</html>