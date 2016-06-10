package database;

import java.sql.*;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import application.Product;

public class JDBCConnector {
	private DatabaseMetaData metaData;
	private Connection connection;
	private String userName = "ws1011";
	private String url = "jdbc:postgresql://java.is.uni-due.de/ws1011";
	private String password = "ftpw10";
	private long id;

	public JDBCConnector(){
		try {

			Class.forName("org.postgresql.Driver");
			connection = DriverManager.getConnection(url, userName ,password);
			metaData = connection.getMetaData();
		}
		catch(ClassNotFoundException e){
			System.out.println("Klasse nicht gefunden!");
		}
		catch(SQLException e){
			System.out.println("SQLException");
		}
	}

	public String getConnectionURL() {
		try {
			return metaData.getURL();
		}

		catch (SQLException sqle) {
			sqle.printStackTrace();
			return "https://www.google.de/";
		}
	}

	public String getConnectionUserName() {
		try {
			return metaData.getUserName();
		}

		catch (SQLException sqle) {
			sqle.printStackTrace();
			return "";
		}
	}

	public String getConnectionTableNames() {
		String tableNameQuery = "SELECT table_name FROM information_schema.tables WHERE table_schema='public' AND table_type='BASE TABLE'; ";
		String names = "";
		try (Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(tableNameQuery);) {

			while (resultSet.next()) {
				String nameVal = resultSet.getString("table_name");
				names += nameVal + "\n";
			}
			return names;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "";

	}

	public long insert(String name, double price, int quantity){

			try(PreparedStatement statement = connection.prepareStatement(
						"INSERT INTO products (name, price, quantity) VALUES(?, ?, ?)",
						PreparedStatement.RETURN_GENERATED_KEYS);) {
					statement.setString(1, name);
					statement.setDouble(2, price);
					statement.setInt(3, quantity);
					id = statement.executeUpdate();
						if(id !=0){
						ResultSet result = statement.getGeneratedKeys();
							if(result.next()){
								id = result.getLong("id");
							}
						}
				}
				catch(SQLException e){
					System.out.println("Hier ist ne SQLException");
				}

		return id;
	}

	public void insert(Product product){
		this.id = insert(product.getName(), product.getPrice(), product.getQuantity());
	}


	public Product read(long productId){
		Product product = null;
		try(PreparedStatement statement = connection.prepareStatement(
				"SELECT * FROM products WHERE id = "+ productId)) {
			ResultSet result = statement.executeQuery();
			if(result.next()){
				product = new Product(result.getString("name"),
								      result.getDouble("price"),
								      result.getInt("quantity"));

			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return product;
	}

	public long getHighestId(){
		return this.id;
	}

	public void close(){
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Verbindung konnte nicht geschlossen werden");
		}
	}

}
