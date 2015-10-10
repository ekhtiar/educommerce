<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<header id="header"><!--header-->
		
<div class="header-middle"><!--header-middle-->
	<div class="container">
		<div class="row">
		
			<div class="col-sm-4"><!-- div for displaying the logo -->
				<div class="logo pull-left">
					<a href="index.jsp"><img src="images/home/logo.png" alt="" /></a>
				</div>
			</div><!-- div for displaying the logo -->
			
			<div class="col-sm-8">
				<div class="shop-menu pull-right">
					<ul class="nav navbar-nav">
						<li><a href="index.jsp"><i class="fa fa-home"></i> Home</a></li>
						<%HttpSession customerSession = request.getSession(); 
						if(customerSession.getAttribute("email") != null)	{%>
						<li><a href="account.jsp"><i class="fa fa-user"></i> Account</a></li>
						<%} %>
						<li><a href="cart.jsp"><i class="fa fa-shopping-cart"></i> Cart</a></li>
						<li>
						
						<!-- check if a user has logged in, if they are logged in then display
						logout in the menu, else display login link in the top menu -->
						<%	
						if(customerSession.getAttribute("email") == null)		
							out.println("<a href=\"login.jsp\"><i class=\"fa fa-lock\"></i> Login</a>");
						else
							out.println("<a href=\"LoginServlet?action=signout\"><i class=\"fa fa-lock\"></i> Logout</a>");
						
						// Check for incoming payment responses (PayQueue)
						//PaymentService.checkQueue();
						 %>
						 
						</li>
					</ul>
				</div>
			</div>
		</div>
	</div>
</div><!--/header-middle-->	
</header><!--/header-->

<!-- this section is particularly for displaying the error message, using this session
object we can display different kind of error to the page. for example if a person tries
to order something more than the amount available in stock, or tries to create an account
with an email address that has been used to registry with before -->

<%	if(customerSession.getAttribute("message") != null){ %>
<div class="step-one">
	<h2 class="heading" style = "color: <%= customerSession.getAttribute("color") %>">
	<% out.println(customerSession.getAttribute("message")); %>
	</h2>
</div>
<%} 
customerSession.setAttribute("message", null);
%>
