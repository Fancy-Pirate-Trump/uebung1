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
		String sql = "";
		long id = 0;
		try(PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)){
			id = statement.executeUpdate();
		}catch(SQLException e){
			
		}
		return id;
	}
//	Achten Sie beim erstellen des Statements auf das Vorhandensein des Parameters
//	Statement.RETURN_GENERATED_KEYS.
//	INSERT INTO products(name,price,quantity) VALUES (?,?,?)
	public void insert(Product product){
		try(PreparedStatement statement = connection.prepareStatement(
				"INSERT INTO products (name, price, quantity) VALUES(?)"
				);) {
			statement.setObject(1, product);
			statement.executeUpdate();
		}
		catch(SQLException e){

		}
	}
//	Nach dem erfolgreichen Einfügen in die Datenbank soll das Produkt die von der Datenbank
//	generierte Id übernehmen
	public Product read(long productId){
		Product product = null;
		try(PreparedStatement statement = connection.prepareStatement(
				"SELECT * FROM Product WHERE id=?"
				)) {
			statement.setLong(1, productId);
			ResultSet result = statement.executeQuery();
			product = (Product) result.getObject(1);
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return 	product;
	}
//	SELECT id,name,price,quantity FROM products WHERE id=?

}
