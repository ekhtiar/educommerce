package educommerce.control;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import educommerce.model.CustomerClass;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//this is used to log the customer out
		String action = request.getParameter("action");
		
		if(action.equalsIgnoreCase("signout")){
			
			HttpSession customerSession = request.getSession();
			
			customerSession.invalidate();
			
			response.sendRedirect("index.jsp");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//get email address and password from the user
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		//create checkFlag and validate the login
		boolean checkFlag = CustomerClass.Login(email, password) ;
		
		//check the state of customer login and store in session object
		if(checkFlag) {
			HttpSession customerSession = request.getSession();
			customerSession.setAttribute("email", email);
			response.sendRedirect("index.jsp");
		}
	}

}
