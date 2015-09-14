package educommerce.control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import webshop.model.ProductInfo;
import webshop.model.dto.Product;


public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");
		
		if(action.equalsIgnoreCase("add")){
			try {
			    addToCart(request);
			} catch (NumberFormatException | InstantiationException
				| IllegalAccessException
				| ClassNotFoundException | SQLException e) {
    			   System.err.println();
			}
			response.sendRedirect("index.jsp");
		}
		if(action.equalsIgnoreCase("update")){
			try {
			    updateCart(request);
			} catch (NumberFormatException | InstantiationException
				| IllegalAccessException
				| ClassNotFoundException | SQLException e) {
			    System.err.println();
			}
			response.sendRedirect("cart.jsp");
		}
		
		
		
	}
	
	protected void addToCart(HttpServletRequest request) throws NumberFormatException, InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
		
		//take all the input parameter from the request object and organize them into correct formatted variables
		String productID = request.getParameter("productID");
		float unitCost = Float.parseFloat(request.getParameter("unitCost"));
		int quantity = Integer.parseInt(request.getParameter("quantity"));
		System.out.println("product ID: " +productID+ " unit Cost: " +unitCost+" Quantity:"  +quantity);
		
		//important: name of the session is product ID and quantity is represented by the value
		
		HttpSession session = request.getSession();
		
		if(null == session.getAttribute(productID)){
		    if(checkInventory(request, quantity)){
			session.setAttribute(productID, quantity);
		    }else
			session.setAttribute(productID, 0);
		}
		else{
			int bufferQuantity = (int) session.getAttribute(productID);
			bufferQuantity += quantity;
        			if(checkInventory(request, bufferQuantity)){
        			    session.setAttribute(productID, bufferQuantity);
        			    HttpSession customerSession = request.getSession();
    	        		    customerSession.setAttribute("message", "your shopping cart was updated");
    	        		    System.out.println(customerSession.getAttribute("message"));
    	        		    customerSession.setAttribute("color", "green");
        			}
		}
		
		System.out.println(session.getAttribute(productID));
		
	}
	
	protected void updateCart(HttpServletRequest request) throws NumberFormatException, InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
		
		//take all the input parameter from the request object and organize them into correct formatted variables
		String productID = request.getParameter("productID");
		float unitCost = Float.parseFloat(request.getParameter("unitCost"));
		int quantity = Integer.parseInt(request.getParameter("quantity"));
		System.out.println("product ID: " +productID+ " unit Cost: " +unitCost+" Quantity:"  +quantity);
		
		//important: name of the session is product ID and quantity is represented by the value
		
		if(checkInventory(request, quantity)){
		    HttpSession session = request.getSession();
		    session.setAttribute(productID, quantity);
		    System.out.println(session.getAttribute(productID));
		}
		
	}
	
	protected boolean checkInventory(HttpServletRequest request, int checkValue) throws NumberFormatException, InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
		
		//take all the input parameter from the request object and organize them into correct formatted variables
		String productID = request.getParameter("productID");
		float unitCost = Float.parseFloat(request.getParameter("unitCost"));
		int quantity = Integer.parseInt(request.getParameter("quantity"));
		System.out.println("product ID: " +productID+ " unit Cost: " +unitCost+" Quantity:"  +quantity);
		
		//important: name of the session is product ID and quantity is represented by the value
		
		Product product = new Product();
		product = ProductInfo.getInfoByProductID(Integer.parseInt(productID));
		
		boolean checkFlag = false;
		System.out.println(checkValue);
		System.out.println(product.getQuantity());
		if(checkValue<=product.getQuantity())
		{
		checkFlag = true;
		}else{
        		HttpSession customerSession = request.getSession();
        		customerSession.setAttribute("message", "you have selected more than the available amount");
        		System.out.println(customerSession.getAttribute("message"));
        		customerSession.setAttribute("color", "red");
    		}
		
		return checkFlag;
		
	}

}
