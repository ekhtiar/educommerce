<!-- this is a include file which is used to generate the view of the product display
box. it takes the input of the productID in the MySQL database and generates the view
for that product along with the animated overlay, when included, it needs to be
passed on with the parameter productNumber which is a integer and is the productID-->
<%@page import="educommerce.model.ProductInfo"%>
<%@page import="educommerce.model.dto.Product"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html">

<div class="col-sm-4">
<div class="product-image-wrapper">
<div class="single-products">
	<div class="productinfo text-center">
		<img src="images/home/products/<%=request.getParameter("productNumber")%>.jpg" alt="" />
	
		<%//create the product DTO for the product ID
		Product product = new Product();
		//below the jsp:include param: productNumber is passed to get the values associated with the
		//productID that was passed to the include
		product = ProductInfo.getInfoByProductID(Integer.parseInt(request.getParameter("productNumber")));%>
		<h2>EUR. <%=product.getPrice() %></h2>
			<!-- a simple algorithm is used below to render the output name in the front end
				we know that any name will have more than 20 character, after that we find the
				first whitespace and use subSequence to end it at that point so we dont
				display the name in middle of a word -->
			<p><%=product.getName().subSequence(0, product.getName().indexOf(" ", 20)) %></p>
			<a href="#" class="btn btn-default add-to-cart"><i class="fa fa-shopping-cart"></i>Add to cart</a>
	</div>

	<div class="product-overlay"> <!-- animated overlay display -->
		<div class="overlay-content">
			<h2>EUR. <%=product.getPrice() %></h2>
			<p>
			<br/><h4><%=product.getName() %></h4>
			<br/>Producer Name:	<%=product.getVendorID() %>
			<br/>Weight (in kilogram): <%=product.getWeight() %>	
			<br/>Amount in Stock: <%=product.getQuantity()%>
			</p>
			<%if(product.getQuantity()>0){ %>
													
			<form action = "CartServlet" method = post>
			<div class="cart_quantity_button">
				<p>
					Quantity: <input type="text" name="quantity" value="1" min="0" max="10" autocomplete="off" size="2">
				</p>
			</div><br />
				<input type="hidden" name="action" value="add">
				<input type="hidden" name="productID" value="<%=request.getParameter("productNumber")%>">
				<input type="hidden" name="unitCost" value="<%=product.getPrice() %>">
				<button type="submit" class="btn btn-default add-to-cart"><i class="fa fa-shopping-cart"></i>Add to cart</button>
			</form> 
			<%} else{%>
				<strong style = "color:red">Sorry we are out of stock :( <br/><br/></strong>
			<%} %>
		</div>
	</div><!-- animated overlay display -->
</div>
</div>
</div>
