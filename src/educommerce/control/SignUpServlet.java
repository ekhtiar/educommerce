package educommerce.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import educommerce.model.CustomerClass;
/*This servlet is used to sign up customers,
 * request from login.jsp to sign up as a new customer
 * is thrown to this servlet,  
 */
public class SignUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String retrypassword = request.getParameter("retryPassword");
		String gender = request.getParameter("gender");
		
		if(!password.equals(retrypassword))
			response.sendRedirect("message.jsp?errorFlag=1");
			
		boolean doRegistration = CustomerClass.Register(gender, firstName, lastName, email, password);
		System.out.println(doRegistration);
		if(doRegistration)
		{
		    HttpSession customerSession = request.getSession();
		    customerSession.setAttribute("color", "green");
		    customerSession.setAttribute("message", "your account was created");
		}
		response.sendRedirect("index.jsp");
		
	}

}
