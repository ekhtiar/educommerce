package educommerce.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import educommerce.model.CustomerClass;

public class AccountServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request,
	    HttpServletResponse response) throws ServletException, IOException {
	// TODO Auto-generated method stub
    }

    protected void doPost(HttpServletRequest request,
	    HttpServletResponse response) throws ServletException, IOException {

	String action = request.getParameter("action");

	if (action.equalsIgnoreCase("updateCustomerDetails")) {
	    updateCustomerDetails(request);
	}

    }

    protected void updateCustomerDetails(HttpServletRequest request)
	    throws ServletException, IOException {
	
	//initialize all the variables and capture them
	String gender = request.getParameter("gender");
	String firstName = request.getParameter("firstName");
	String middleName = request.getParameter("middleName"); 
	String lastName = request.getParameter("lastName");
	String emailAddress = request.getParameter("emailAddress");
	String loginPassword = request.getParameter("loginPassword");
	String loginName = request.getParameter("loginName");
	String phoneNumber = request.getParameter("phoneNumber");
	
	//call the function to store / update the data in the mysql database
	boolean flag = CustomerClass.UpdateCustomerData(gender, firstName, middleName, lastName, emailAddress, loginName, loginPassword, phoneNumber);

	
    }

}
