package educommerce.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtil {

	public static final String USERNAME = "acme";
	public static final String PASSWORD = "acme123";
	public static final String CONNECTION = "jdbc:mysql://localhost:3306/educommerce";

	public static Connection getConnection() throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {

		Class.forName("com.mysql.jdbc.Driver").newInstance();

		Connection connection = DriverManager.getConnection(CONNECTION, USERNAME, PASSWORD);

		return connection;
	}

	public static String getStringResult(String query, String columnName) {

		String result = "";

		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;

		try {
			connection = DBUtil.getConnection();
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			resultSet = statement.executeQuery(query);
			while (resultSet.next()) {
				result = resultSet.getString(columnName);
			}

			resultSet.close();
			statement.close();
			connection.close();

		}
		catch (SQLException e) {
			System.err.println(e);
		}
		catch (InstantiationException | IllegalAccessException | ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			System.err.println(e1);
		}
		finally {

			try {
				resultSet.close();
				statement.close();
				connection.close();
			}
			catch (SQLException e) {
				// TODO Auto-generated catch block
				System.err.println(e);
			}
		}
		return result;

	}

	public static int getIntResult(String query, String columnName) {

		int result = -1;

		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;

		try {
			connection = DBUtil.getConnection();
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			resultSet = statement.executeQuery(query);
			while (resultSet.next()) {
				result = resultSet.getInt(columnName);
			}

			resultSet.close();
			statement.close();
			connection.close();

		}
		catch (SQLException e) {
			System.err.println(e);
		}
		catch (InstantiationException | IllegalAccessException | ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			System.err.println(e1);
		}
		finally {

			try {
				resultSet.close();
				statement.close();
				connection.close();
			}
			catch (SQLException e) {
				// TODO Auto-generated catch block
				System.err.println(e);
			}
		}
		return result;

	}

}
