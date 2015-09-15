<%@page import="educommerce.model.DBUtil"%>
<%@page import="educommerce.model.ProductInfo"%>
<%@page import="educommerce.model.dto.Product"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<table class="table table-condensed">
					<thead>
						<tr class="cart_menu">
							<td class="cart_product" width="15%">Item</td>
							<td class="cart_description" width="30%">Description</td>
							<td class="cart_price">Price</td>
							<td class="cart_quantity">Quantity</td>
							<td class="cart_total">Total</td>
							<td class="cart_delete"> </td>
						</tr>
					</thead>
					<tbody>
						<% 	float totalOrderPrice = 0;
							float totalOrderWeight = 0;
							for(int i=1;i<15;i++) {
							if(session.getAttribute(Integer.toString(i)) != null){
							int quantity = (Integer) session.getAttribute(Integer.toString(i));
							if (quantity > 0) { %>
						
						<%//create the product DTO for the product ID
												Product product = new Product();
												product = ProductInfo.getInfoByProductID(i);%>
						<tr>
							<td class="cart_product">
								<img src="images/home/products/<%=i %>.jpg" alt=""  width="35%">
							</td>
							<td class="cart_description">
								<p style = "color : orange"><strong><%=product.getName() %></strong></p>
								<p>Product ID: <%=i %></p>
							</td>
							<td class="cart_price">
								<p>EUR. <%=product.getPrice() %></p>
							</td>
							<td class="cart_quantity">
								<div class="cart_quantity_button">
									
									<form action = "CartServlet" method = post>
									
									<!-- this section generates the availability of stock under the update button
									if the stock is available we show the stock in green font. otherwise we show that
									the item is not available in red and show an estimated date of availability 
									
									this piece of code takes care of concurrency issues, where initially customer
									ordered an item from the front page and the item was available in stock, however
									by the time they proceed to the cart, it goes out of stock-->	
									
									<%
									
									if(product.getQuantity()>=quantity) {%>
												
									<input class="cart_quantity_input" type="text" name="quantity" value="<%=quantity %>" autocomplete="off" size="2">
									<input type="hidden" name="action" value="update">
									<input type="hidden" name="productID" value="<%=i%>">
									<input type="hidden" name="unitCost" value="<%=product.getPrice() %>">
									<button type="submit" class="btn">Update</button>
									</form>
												
									
									<span style="color:green"> 
										<em>Available Stock: <%=product.getQuantity() %></em>
									</span>
									
									<!-- if the item isn't available then we show the following message -->
									<%} else { %>
									
									<span style="color: red"> 
										<em>Stock Unavailable Currently!
										<br/>We should have it back in: <%=product.getAvailability()/24 %> days
									</span>
									<span style="color: black">	
										<br/>Please remove this item<br/> from the shopping cart</em>
									</span>
									<%} %>
									
									
							</td>
							<td class="cart_total">
								<p class="cart_total_price"><%=quantity * product.getPrice() %></p>
							</td>
							<td class="cart_delete">
								<form action = "CartServlet" method = post>
								<input type="hidden" name="action" value="update">
								<input type="hidden" name="quantity" value="0">
								<input type="hidden" name="productID" value="<%=i%>">
								<input type="hidden" name="unitCost" value="<%=product.getPrice() %>">
								<button type="submit" class="cart_quantity_delete"><i class="fa fa-times"></i></button>
								</form>
							</td>
						</tr>
						<%}}} %>
						
					</tbody>
				</table>
				
			</div>
		</div>
</section> <!--/#cart_items-->

	<!-- Calculation for different shipping cost -->
	

	<section id="do_action">
		<div class="container">
				
				<div class="col-sm-6">
					<div class="total_area">
						<ul>
							<li>Cart Sub Total <span>1</span></li>
							<li>Total Weight <span>1 KG</span></li>
							<li>Normal Shipping Cost <span>1</span></li>
							<li>Estimated Arrival <span>1 Days</span></li>
							<li>Total <span>1</span></li>
						</ul>
							<a class="btn btn-default update" href="cart.jsp">Update</a>
							<a class="btn btn-default check_out" href="/acme_g1/checkout.jsp?shippingType=regular">Confirm To Check Out (Regular Shipping)</a>
					</div>
				</div>
				
				<div class="col-sm-6">
					<div class="total_area">
						<ul>
							<li>Cart Sub Total <span>1</span></li>
							<li>Total Weight <span>1 KG</span></li>
							<li>Express Shipping Cost <span>1</span></li>
							<li>Estimated Arrival <span>1 Days</span>
							<li>Total <span>1</span></li>
						</ul>
							<a class="btn btn-default update" href="cart.jsp">Update</a>
							<a class="btn btn-default check_out" href="/acme_g1/checkout.jsp?shippingType=express">Confirm To Check Out (Express Shipping)</a>
					</div>
				</div>
				
			</div>
		</div>
	</section><!--/#do_action-->

</body>
</html>