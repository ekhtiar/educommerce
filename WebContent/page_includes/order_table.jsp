<!--/this page is used to generate the table view in the order list menu
Here we take the order object and use a loop to render the view of the
entire table-->
<%@page import="com.autoinc.buffalo.model.PostOrderObject"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.autoinc.buffalo.model.OrderObject"%>
<%@page import="com.autoinc.buffalo.model.CustomerClass"%>
<%@page import="com.autoinc.buffalo.model.OrderClass"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!--/header--><%@include file ="/page_includes/header.jsp" %><!--/header-->

	<section id="cart_items">
		<div class="container">
			<div class="breadcrumbs">
				<ol class="breadcrumb">
				  <li><a href="#">Home</a></li>
				  <li class="active">Orders</li>
				</ol>
			</div>
			<div class="table-responsive cart_info">

<table class="table table-condensed">

<%//get order list

String email = (String) customerSession.getAttribute("email");
int customerId = CustomerClass.getCustomerIDbyCustomerEmail(email);
ArrayList<PostOrderObject> orderList = OrderClass.getOrdersByCustomerId(customerId);

%>
					<thead>
						<tr class="cart_menu">
							<td class="order_id" >Order Id</td>
							<td class="model">Model</td>
							<td class="edition">Edition</td>
							<td class="color">Color</td>
							<td class="quantity">Quantity</td>
							<td class="total">Total Price</td>
							<td class="status">Order Status</td>
							<td class="status">Order Information</td>
							<td>Action</td>
						</tr>
					</thead>
					<tbody>
						
					<%for (PostOrderObject orderObject : orderList) { %>	
						<tr>
							<td class="order_id">
								<%=orderObject.getOrders_id() %>
							</td>
							<td class="model">
								<div style = "color : orange"><%=orderObject.getModel() %></div>
							</td>
							<td class="edition">
								<%=orderObject.getEdition() %>
							</td>
							<td class="color"><%=orderObject.getColor()%></td>
							<td class="quantity"><%=orderObject.getQuantity() %></td>
							<td class="total"><%=orderObject.getAmount() %></td>
							<td class="status">	<span style="color:orange">Process Status:</span> <%=orderObject.getStatus()%><br/>
												<span style="color:orange">Payment Status:</span> <%=orderObject.getPayment()%><br/>
												<span style="color:orange">In Warehouse:</span> <%=orderObject.getWarehouse()%></td>
							<td class="information"><span style="color:orange">Transaction Id:</span> <%=orderObject.getTransaction_id() %><br/>
							<span style="color:orange">Order Received On:</span> <%=orderObject.getOrder_date() %><br/>
							<span style="color:orange">Expected Delivery Date:</span> <%=orderObject.getDelivery_date() %></td>
							
							
							<td class="cart_delete">
								<%
								Calendar cal = Calendar.getInstance();
								cal.add(Calendar.DATE, -2);
								Date compare = cal.getTime();
								if ((!orderObject.getOrder_date().before(compare))&&(!(orderObject.getStatus().equalsIgnoreCase("canceled")))) {%>
								<form action = "CancelOrderServlet" method = post>
								<input type="hidden" name="customerId" value="<%=orderObject.getCustomer_id()%>">
								<input type="hidden" name="orderId" value="<%=orderObject.getOrders_id()%>">
								<input type="hidden" name="transactionId" value="<%=orderObject.getTransaction_id()%>">
								<button type="submit" class="cart_quantity_delete"><i class="fa fa-times"></i></button>
								</form>
								<%} %>
							</td>
						</tr>
						<% } %>
					</tbody>
				</table>
				
			</div>
		</div>
</section> <!--/#cart_items-->

	</section><!--/#do_action-->

</body>
</html>