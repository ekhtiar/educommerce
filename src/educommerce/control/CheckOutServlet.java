package educommerce.control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import webshop.model.CustomerClass;
import webshop.model.GetPaymentID;
import webshop.model.Order;
import webshop.model.ParseXML;
import webshop.model.Payment;
import webshop.model.dto.CheckoutCart;
import webshop.model.dto.OrderResult;
import webshop.model.service.payment.PaymentService;

/*
 * This Servlet takes in the request input request to check out the customers cart
 * and uses the CreateOrderID to create the order ID and map the items and quantity
 * to the database.
 */

public class CheckOutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    
	    //get the shipping type from the post method
	    String shippingType = request.getParameter("shippingType");
	    
	    //get the customerID by retrieving the email address from session
	    HttpSession customerSession = request.getSession();
	    String emailAddress = (String) customerSession.getAttribute("email");
	    int customerID = CustomerClass.getCustomerIDbyCustomerEmail(emailAddress);
	    
	    //redirect the customer to homepage, if the get method isnt the one we want otherwise we continue
	    if(!((shippingType.equalsIgnoreCase("express"))||(shippingType.equalsIgnoreCase("regular")))){
		response.sendRedirect("index.jsp");
	    }
	    
	    else{
		
	    //check for customer ID if it is 0 then the user hasn't logged in and therefore
	    //we redirect him back to the login page
	    if(customerID == 0){
		response.sendRedirect("login.jsp");
	    }
	    
	    //we will be using the DTO checkoutCart which has two integers, productID and quantity.
	    HttpSession session = request.getSession();
	    List <CheckoutCart> checkoutCart = new ArrayList<>();
	    
	    //store the items to the list of the DTO checkoutCart
	    for(int i=1;i<15;i++) {
		if(session.getAttribute(Integer.toString(i)) != null){
		int quantity = (Integer) session.getAttribute(Integer.toString(i));
			if (quantity > 0) {
			    CheckoutCart bufferCheckoutCart = new CheckoutCart();
			    bufferCheckoutCart.setProductID(i);
			    bufferCheckoutCart.setQuantity(quantity);
			    checkoutCart.add(bufferCheckoutCart);
			}
		}
	    }
	    
	    //used to print the shopping cart received console
	    System.out.println("Cart checked out with following order for the customerID: " +customerID );
	    for(int j = 0; j<checkoutCart.size(); j++){
		CheckoutCart bufferCheckoutCart = new CheckoutCart();
		bufferCheckoutCart = checkoutCart.get(j);
		System.out.println(bufferCheckoutCart.getProductID());
		System.out.println(bufferCheckoutCart.getQuantity());
	    }

	    System.out.println("Shipping Type: " +shippingType);
	    
	    //pass to create order ID here and get paymentID
	    try {
		OrderResult orderResult = Order.onCheckout(checkoutCart, shippingType, customerID);
		float totalPrice = orderResult.getTotalPrice();
		int orderID = orderResult.getOrderID();
		
		//get the paymentID from Propay (third party payment service running on REST)
		String xmlReply = GetPaymentID.fromPropay(orderID, totalPrice);
		
		//parse XML to get payment ID
		int paymentID = ParseXML.getPaymentID(xmlReply);
		System.out.println(paymentID);
		
		//store PaymentID to the Payment Table
		Payment.storePaymentID(paymentID, orderID);
		
		//redirect to payment page with payment ID
		response.sendRedirect("payment/paymentform.jsp?payment_id="+paymentID+"&shipping_type="+shippingType);
		    
	    } catch (InstantiationException | IllegalAccessException
		    | ClassNotFoundException | SQLException e) {
		System.err.println(e);
	    }
	    
	    } //end else
	    
	}


}
