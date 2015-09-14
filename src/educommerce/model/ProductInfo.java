package educommerce.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import educommerce.model.dto.Product;

public class ProductInfo
{

	public static Product getInfoByProductID(int productID) throws InstantiationException, IllegalAccessException, ClassNotFoundException,
			SQLException
	{

		Product product = new Product();

		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;

		connection = DBUtil.getConnection();
		statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		try
		{
			resultSet = statement.executeQuery("SELECT * FROM product WHERE product_id = " + productID);

			while (resultSet.next())
			{
				product.setName(resultSet.getString("product_name"));
				product.setPrice(resultSet.getFloat("product_price"));
				product.setQuantity(resultSet.getInt("stock_qty"));
				product.setVendorID(resultSet.getString("vendor_id"));
				product.setWeight(resultSet.getFloat("product_weight"));
				product.setAvailability(resultSet.getInt("restock_time"));
			}
		}
		catch (Exception e)
		{
			System.err.println(e);
		}
		finally
		{
			connection.close();
			statement.close();
			resultSet.close();
		}

		return product;

	}

}
