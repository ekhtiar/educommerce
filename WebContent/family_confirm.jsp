<%@page import="com.autoinc.buffalo.model.PaymentInformationClass"%>
<%@page import="com.autoinc.buffalo.model.CustomerClass"%>
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
	
	<!-- check if they are logged in -->
	<%HttpSession customerSession = request.getSession(); 
		if(customerSession.getAttribute("email") == null)	{
			 customerSession.setAttribute("color", "red");
			 customerSession.setAttribute("message", "please log in!");
			 response.sendRedirect("index.jsp");
		}%>
		
		
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
								</span>
								
								<p><b>Engine:</b> 4 cylinder, 2362cc</p>
								<p><b>Transmission:</b> Manual</p>
								<p><b>Features:</b> ABS brakes, Alloy wheels, Central locking,
													Driver airbag, Passenger airbag, Power steering</p>
								
								<br />
								
								<% 	//get all the necessery parameters
									String color = request.getParameter("color");
									String edition = request.getParameter("edition");
									int quantity = Integer.parseInt(request.getParameter("quantity"));
									String email = (String)	 customerSession.getAttribute("email");
									
									//get the customerId
									int customerId = CustomerClass.getCustomerIDbyCustomerEmail(email);
									//check if the payment has been set
									boolean isPaymentInformationSet = PaymentInformationClass.paymentInformationGiven(customerId);
									
									//calculate right unit price
									int unitPrice=10000;
									if(edition.equalsIgnoreCase("comfort")) unitPrice = unitPrice+3500;
									
									%>
								<form action="ReceiveOrderServlet" method="POST">
								<label>Color:</label> <%=color%>
								<br/>
								<label>Edition:</label> <%=edition%>
								<br/>
								<label>Quantity:</label> <%=quantity%>
								<br/>
								<label>Total Cost: </label> <%=quantity*unitPrice	%>
								
								<input type="hidden" name="email" value="<%=email%>">
								<input type="hidden" name="model" value="family">
								<input type="hidden" name="color" value="<%=color%>">
								<input type="hidden" name="edition" value="<%=edition %>">
								<input type="hidden" name="quantity" value="<%=quantity %>">
								
								<%if((customerSession.getAttribute("email") != null)&&(isPaymentInformationSet)) { %>
								<br />
								<button type="submit" class="btn btn-default">Confirm</button>
								<% }%>
								<%if(!isPaymentInformationSet) { %>
								<br />
								<a href="account.jsp"><button type="button" class="btn btn-default">Set Payment Details</button></a>
								<% }%>
								</form>
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