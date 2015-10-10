package educommerce.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import educommerce.model.dto.CustomerObject;
import educommerce.model.dto.GetProductByProductID;

public class CustomerClass
{

	public static CustomerObject getCustomer(String emailAddress)
	{

		String gender = "";
		String firstName = "";
		String middleName = "";
		String lastName = "";

		String loginName = "";
		String loginPassword = "";
		String phoneNumber = "";

		String address1 = "";
		String address2 = "";
		String city = "";
		String zipCode = "";
		String country = "";

		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;

		try
		{

			connection = DBUtil.getConnection();

			statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			resultSet = statement.executeQuery("SELECT * FROM customer WHERE email_address= '" + emailAddress + "'");
			while (resultSet.next())
			{

				gender = resultSet.getString("gender");
				firstName = resultSet.getString("first_name");
				middleName = resultSet.getString("middle_name");
				lastName = resultSet.getString("last_name");
				// We already have e-mail
				loginName = resultSet.getString("login_name");
				loginPassword = resultSet.getString("login_password");
				phoneNumber = resultSet.getString("phone_number");

				address1 = resultSet.getString("address1");
				address2 = resultSet.getString("address2");
				city = resultSet.getString("city");
				zipCode = resultSet.getString("zip_code");
				country = resultSet.getString("country");

			}

		}
		catch (SQLException e)
		{
			System.err.println(e);
		}
		catch (InstantiationException | IllegalAccessException | ClassNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			try
			{
				resultSet.close();
				statement.close();
				connection.close();
			}
			catch (SQLException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		CustomerObject reply = new CustomerObject(
				gender,
				firstName,
				middleName,
				lastName,
				emailAddress,
				loginName,
				loginPassword,
				phoneNumber,
				address1,
				address2,
				city,
				zipCode,
				country);

		return reply;
	}

	// ------------------------------------------------------------
	// Method is called when a customer wants to log in
	// Called from = educommerce.control.LoginServlet
	// ------------------------------------------------------------
	public static Boolean Login(String email_address, String enteredPassword)
	{	/*
		In the educommerce database the customer table contains all the customer information.
		This method first runs a query to retrieve the stored password based on the email address provided.
		Then the result is returned in boolean true / false
		*/
		//create boolean variable to return result
		boolean result = false;
		//build the query
		String query = "SELECT login_password from customer WHERE email_address ='" + email_address + "';";
		//query is run and result is returned using our DBUtil class and getStringResult method
		String storedPassword = DBUtil.getStringResult(query, "login_password");
		//we have to handle the case of bad email address here (as it will probably throw a null string 
		//exception. if the result is returned null, we store "notFound" in storedPassword (for future use 
		//perhaps) and move on. As the result boolean variable is originally in false state.
		if(storedPassword.isEmpty()){
			storedPassword = "notFound";
		}
		//if the storedPassword is not null then we try to match the password, here if the password
		else{
		result = BCrypt.checkpw(enteredPassword, storedPassword);
		}
		return result;
	}

	// ------------------------------------------------------------
	// Method is called when a new customer wants to register
	// ------------------------------------------------------------
	public static Boolean Register(String gender, String firstName, String lastName, String emailAddress, String loginPassword)
	{
		Boolean result = false;
		String passwordHash = BCrypt.hashpw(loginPassword, BCrypt.gensalt(12));

		Connection connection = null;
		PreparedStatement preparedStmt = null;

		try
		{

			connection = DBUtil.getConnection();

			// the mysql insert statement
			String query = " insert into customer (gender, first_name, middle_name, last_name, "
					+ "email_address, login_name, login_password, phone_number)" + " values (?, ?, ?, ?, ?, ?, ?, ? )";

			preparedStmt = connection.prepareStatement(query);

			// create the mysql prepared statement

			preparedStmt.setString(1, gender);
			preparedStmt.setString(2, firstName);
			preparedStmt.setString(3, "");
			preparedStmt.setString(4, lastName);
			preparedStmt.setString(5, emailAddress);
			preparedStmt.setString(6, "");
			preparedStmt.setString(7, passwordHash);
			preparedStmt.setString(8, "");

			// execute the prepared statement
			result = preparedStmt.execute();
			result = true;

		}
		catch (SQLException e)
		{
			System.err.println(e);
		}
		catch (InstantiationException | IllegalAccessException | ClassNotFoundException e)
		{
			// TODO Auto-generated catch block
			System.err.println(e);
		}
		finally
		{

			try
			{
				preparedStmt.close();
				connection.close();
			}
			catch (SQLException e)
			{
				// TODO Auto-generated catch block
				System.err.println(e);
			}
		}

		return result;
	}

	// ------------------------------------------------------------
	// Updating customer base profile data in "customer" table
	// ------------------------------------------------------------
	public static Boolean UpdateCustomerData(String gender, String firstName, String middleName, String lastName, String emailAddress,
			String loginName, String loginPassword, String phoneNumber)
	{

		Boolean result = false;
		String passwordHash = "";

		// If loginPassword is not empty then user set a new password,
		// re-hashing is needed
		if (loginPassword != "")
		{
			passwordHash = BCrypt.hashpw(loginPassword, BCrypt.gensalt(12));
		}
		// otherwise just query the current password hash
		else
		{
			String query = "SELECT login_password FROM customer WHERE email_address = '" + emailAddress + "'";
			passwordHash = DBUtil.getStringResult(query, "login_password");
		}

		Connection connection = null;
		PreparedStatement preparedStmt = null;

		try
		{

			connection = DBUtil.getConnection();

			// the mysql insert statement
			String query = " UPDATE customer SET gender=?, first_name=?, middle_name=?, last_name=?, "
					+ "email_address=?, login_name=?, login_password=?, phone_number=?" + "WHERE email_address = ?;";

			preparedStmt = connection.prepareStatement(query);

			// create the mysql prepared statement

			preparedStmt.setString(1, gender);
			preparedStmt.setString(2, firstName);
			preparedStmt.setString(3, middleName);
			preparedStmt.setString(4, lastName);
			preparedStmt.setString(5, emailAddress);
			preparedStmt.setString(6, loginName);
			preparedStmt.setString(7, passwordHash);
			preparedStmt.setString(8, phoneNumber);
			preparedStmt.setString(9, emailAddress);

			// execute the preparedstatement
			result = preparedStmt.execute();

		}
		catch (SQLException e)
		{
			System.err.println(e);
		}
		catch (InstantiationException | IllegalAccessException | ClassNotFoundException e)
		{
			// TODO Auto-generated catch block
			System.err.println(e);
		}
		finally
		{

			try
			{
				preparedStmt.close();
				connection.close();
			}
			catch (SQLException e)
			{
				// TODO Auto-generated catch block
				System.err.println(e);
			}
		}

		return result;
	}

	// ------------------------------------------------------------
	// Updating shipment-related data in "customer" table
	// ------------------------------------------------------------
	public static Boolean UpdateShipmentData(String emailAddress, String address1, String address2, String city, String zipCode,
			String country)
	{

		Boolean result = false;

		Connection connection = null;
		PreparedStatement preparedStmt = null;

		try
		{
			connection = DBUtil.getConnection();

			// the mysql statement
			String query = " UPDATE customer SET address1=?, address2=?, city=?, zip_code=?, country=? WHERE email_address = ?;";

			preparedStmt = connection.prepareStatement(query);

			// create the mysql prepared statement
			preparedStmt.setString(1, address1);
			preparedStmt.setString(2, address2);
			preparedStmt.setString(3, city);
			preparedStmt.setString(4, zipCode);
			preparedStmt.setString(5, country);
			preparedStmt.setString(6, emailAddress);

			// execute the preparedstatement
			result = preparedStmt.execute();

		}
		catch (SQLException e)
		{
			System.err.println(e);
		}
		catch (InstantiationException | IllegalAccessException | ClassNotFoundException e)
		{
			// TODO Auto-generated catch block
			System.err.println(e);
		}
		finally
		{

			try
			{
				preparedStmt.close();
				connection.close();
			}
			catch (SQLException e)
			{
				// TODO Auto-generated catch block
				System.err.println(e);
			}
		}

		return result;
	}

	public static Boolean IsUserRegistered(String emailAddress)
	{

		Boolean reply = false;

		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;

		try
		{
			connection = DBUtil.getConnection();
		}
		catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try
		{
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		}
		catch (SQLException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try
		{
			// Check if an orderId has a corresponding shipment table entry
			resultSet = statement.executeQuery("SELECT email_address FROM customer WHERE email_address = '" + emailAddress + "' ;");

			// If resultset is not empty, then we have this shipment entry
			if (resultSet.next())
			{
				reply = true;
			}

		}
		catch (SQLException e)
		{
			System.err.println(e);
		}
		finally
		{

			try
			{
				resultSet.close();
				statement.close();
				connection.close();
			}
			catch (SQLException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.err.println(e);
			}

		}
		return reply;

	}

	public static int getCustomerIDbyCustomerEmail(String emailAddress)
	{

		int customerID = 0;

		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;

		try
		{
			connection = DBUtil.getConnection();
		}
		catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try
		{
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		}
		catch (SQLException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try
		{
			// Check if an orderId has a corresponding shipment table entry
			resultSet = statement.executeQuery("SELECT customer_id FROM customer WHERE email_address = '" + emailAddress + "' ;");
			while (resultSet.next())
			{
				customerID = resultSet.getInt("customer_id");
			}

		}
		catch (SQLException e)
		{
			System.err.println(e);
		}
		finally
		{

			try
			{
				resultSet.close();
				statement.close();
				connection.close();
			}
			catch (SQLException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.err.println(e);
			}

		}
		return customerID;

	}

}
